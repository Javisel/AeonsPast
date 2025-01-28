package com.javisel.common.entity;

import com.google.common.collect.ImmutableMap;
import com.google.gson.*;
import com.javisel.AeonsPast;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.JsonOps;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimplePreparableReloadListener;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.profiling.ProfilerFiller;
import net.neoforged.fml.loading.FMLPaths;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class EntityDataLoader extends SimplePreparableReloadListener<Map<ResourceLocation, EntityStatisticalData>> {
    public static final Map<ResourceLocation, EntityStatisticalData> ENTITY_STATISTICAL_DATA = new HashMap<>();
    private static final Gson GSON_INSTANCE = new GsonBuilder().setPrettyPrinting().create();
    private static final String folder = "entities";

    @Override
    protected Map<ResourceLocation, EntityStatisticalData> prepare(ResourceManager resourceManager, ProfilerFiller profilerFiller) {
        ImmutableMap.Builder<ResourceLocation, EntityStatisticalData> builder = ImmutableMap.builder();

        ResourceLocation resourceLocation = ResourceLocation.tryBuild(AeonsPast.MODID, "tags/entity_types/entity_types.json");

        HashSet<ResourceLocation> finalLocations = new HashSet<>();

        try {
            for (Resource resource : resourceManager.getResourceStack(resourceLocation)) {
                InputStream stream = resource.open();
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8))) {
                    JsonObject jsonobject = GsonHelper.fromJson(GSON_INSTANCE, reader, JsonObject.class);
                    boolean replace = jsonobject.get("replace").getAsBoolean();

                    // If the JSON specifies "replace": true, reset the list of existing resource locations.
                    if (replace) {
                        finalLocations.clear();
                    }

                    // Gather the "values" array containing entity ResourceLocation strings.
                    JsonArray entryList = jsonobject.get("values").getAsJsonArray();
                    for (JsonElement entry : entryList) {
                        String loc = entry.getAsString();
                        ResourceLocation res = ResourceLocation.tryParse(loc);
                        finalLocations.add(res);
                    }
                }

                // For each entity location in the final list, parse stats from resourceList
                finalLocations.forEach(location -> {
                    try {
                        // resourceList.get(location) is the JSON element specific to that entity
                        JsonElement jsonElement = resourceManager.getResource(ResourceLocation.tryBuild(location.getNamespace(), folder + "/" + location.getPath() + ".json")).map(is -> {
                            try (BufferedReader reader = new BufferedReader(new InputStreamReader(is.open()))) {
                                return GsonHelper.fromJson(GSON_INSTANCE, reader, JsonElement.class);
                            } catch (IOException e) {
                                AeonsPast.LOGGER.error("Couldn't read entity stats {}", location, e);
                                return null;
                            }
                        }).orElse(null);
                        EntityStatisticalData stats = getEntityProperties(location, jsonElement);
                        if (stats != null) {
                            builder.put(location, stats);
                        }
                    } catch (Exception exception) {
                        AeonsPast.LOGGER.error("Couldn't parse entity stats {}", location, exception);
                    }
                });
            }
        } catch (IOException e) {
            AeonsPast.LOGGER.error("Couldn't read entity types {}", resourceLocation, e);
        }

        return builder.build();
    }

    @Override
    protected void apply(Map<ResourceLocation, EntityStatisticalData> entityStatisticsMap, ResourceManager resourceManager, ProfilerFiller profilerFiller) {
        ENTITY_STATISTICAL_DATA.clear();
        ENTITY_STATISTICAL_DATA.putAll(entityStatisticsMap);
    }

    /**
     * Attempts to parse entity properties from JSON. It first tries to use your new codec
     * (EntityStatisticalData.CODEC). If that fails or the JSON data doesn't match the structure,
     * it falls back to parseLegacyJson, which manually reads top-level fields and converts them.
     *
     * @param location    ResourceLocation of the entity
     * @param jsonElement The JSON Element containing entity data
     * @return An EntityStatisticalData object or null if invalid JSON
     */
    private EntityStatisticalData getEntityProperties(ResourceLocation location, JsonElement jsonElement) {
        if (jsonElement == null || !jsonElement.isJsonObject()) {
            return null;
        }
        JsonObject json = jsonElement.getAsJsonObject();

        // Try parsing with the new codec
        DataResult<EntityStatisticalData> result =
                EntityStatisticalData.CODEC.parse(JsonOps.INSTANCE, json);

        // If parsing was successful, return the parsed data
        return result.result().orElseGet(() -> parseLegacyJson(location, json));
    }

    /**
     * Fallback utility method to parse top-level JSON fields from older data that doesn't match the nested codec structure.
     *
     * @param json The JsonObject with legacy fields
     * @return A new EntityStatisticalData constructed from legacy fields
     */
    private EntityStatisticalData parseLegacyJson(ResourceLocation location, JsonObject json) {
        AeonsPast.LOGGER.warn("Falling back to legacy JSON parsing for entity {}", location);

        double attackDamage = GsonHelper.getAsDouble(json, "attack_damage");
        double attackDamageScaling = GsonHelper.getAsDouble(json, "attack_damage_scaling");
        double attackSpeed = GsonHelper.getAsDouble(json, "attack_speed");
        double attackSpeedScaling = GsonHelper.getAsDouble(json, "attack_speed_scaling");
        double physicalPower = GsonHelper.getAsDouble(json, "physical_power");
        double physicalPowerScaling = GsonHelper.getAsDouble(json, "physical_power_scaling");
        double magicPower = GsonHelper.getAsDouble(json, "magic_power");
        double magicPowerScaling = GsonHelper.getAsDouble(json, "magic_power_scaling");
        double maxHealth = GsonHelper.getAsDouble(json, "max_health");
        double maxHealthScaling = GsonHelper.getAsDouble(json, "max_health_scaling");
        double healthRegen = GsonHelper.getAsDouble(json, "health_regeneration");
        double healthRegenScaling = GsonHelper.getAsDouble(json, "health_regeneration_scaling");
        double armor = GsonHelper.getAsDouble(json, "armor");
        double armorScaling = GsonHelper.getAsDouble(json, "armor_scaling");
        double magicResist = GsonHelper.getAsDouble(json, "magic_resist");
        double magicResistScaling = GsonHelper.getAsDouble(json, "magic_resist_scaling");
        double movementSpeed = GsonHelper.getAsDouble(json, "movement_speed");
        double movementSpeedScaling = GsonHelper.getAsDouble(json, "movement_speed_scaling");
        double experience = GsonHelper.getAsDouble(json, "experience");
        double experienceScaling = GsonHelper.getAsDouble(json, "experience_scaling");

        // For traits, you might store them as a simple array of strings in the legacy format.
        List<String> entityTraits = new ArrayList<>();
        if (json.has("entity_traits") && json.get("entity_traits").isJsonArray()) {
            for (JsonElement element : json.getAsJsonArray("entity_traits")) {
                entityTraits.add(element.getAsString());
            }
        }

        // Construct and return the new object
        var object = new EntityStatisticalData(
                new EntityStatisticalData.OffenseStats(
                        attackDamage,
                        attackDamageScaling,
                        attackSpeed,
                        attackSpeedScaling,
                        physicalPower,
                        physicalPowerScaling,
                        magicPower,
                        magicPowerScaling
                ),
                new EntityStatisticalData.DefenseStats(
                        maxHealth,
                        maxHealthScaling,
                        healthRegen,
                        healthRegenScaling,
                        armor,
                        armorScaling,
                        magicResist,
                        magicResistScaling
                ),
                movementSpeed,
                movementSpeedScaling,
                experience,
                experienceScaling,
                entityTraits
        );

        // Save object to cache folder.
        Path cacheFolderPath = FMLPaths.GAMEDIR.get().resolve("cache");
        Path entityDataPath = cacheFolderPath.resolve("entity_data");
        Path entityPath = entityDataPath.resolve(location.getPath() + ".json");

        if (!entityDataPath.toFile().exists()) {
            entityDataPath.toFile().mkdirs();
        }

        EntityStatisticalData.CODEC.encodeStart(JsonOps.INSTANCE, object).result().ifPresent(result -> {
            try {
                String data = GSON_INSTANCE.toJson(result);
                Files.writeString(entityPath, data);
                AeonsPast.LOGGER.info("Saved converted entity data for {}", location);
            } catch (IOException e) {
                AeonsPast.LOGGER.error("Failed to save converted entity data for {}", location, e);
            }
        });

        return object;
    }
}