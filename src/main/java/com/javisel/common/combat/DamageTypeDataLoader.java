package com.javisel.common.combat;

import com.google.common.collect.ImmutableMap;
import com.google.gson.*;
import com.javisel.AeonsPast;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.JsonOps;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimplePreparableReloadListener;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.neoforged.fml.loading.FMLPaths;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class DamageTypeDataLoader extends SimplePreparableReloadListener<Map<ResourceLocation, DamageTypeData>> {
    public static final Map<ResourceLocation, DamageTypeData> DAMAGE_TYPE_DATA = new HashMap<>();
    private static final Gson GSON_INSTANCE = new GsonBuilder().setPrettyPrinting().create();
    private static final String folder = "combatengine/damage_type";

    @Override
    protected Map<ResourceLocation, DamageTypeData> prepare(ResourceManager resourceManager, ProfilerFiller profilerFiller) {
        ImmutableMap.Builder<ResourceLocation, DamageTypeData> builder = ImmutableMap.builder();

        ResourceLocation resourceLocation = ResourceLocation.tryBuild(AeonsPast.MODID, "combatengine/damage_type/types.json");

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

                    // Gather the "values" array containing Item ResourceLocation strings.
                    JsonArray entryList = jsonobject.get("values").getAsJsonArray();
                    for (JsonElement entry : entryList) {
                        String loc = entry.getAsString();
                        ResourceLocation res = ResourceLocation.tryParse(loc);
                        finalLocations.add(res);

                        System.out.println("Res: " + res.toString());


                    }
                }

                // For each Item location in the final list, parse stats from resourceList
                finalLocations.forEach(location -> {
                    try {
                        // resourceList.get(location) is the JSON element specific to that Item
                        JsonElement jsonElement = resourceManager.getResource(ResourceLocation.tryBuild(location.getNamespace(), folder + "/" + location.getPath() + ".json")).map(is -> {
                            try (BufferedReader reader = new BufferedReader(new InputStreamReader(is.open()))) {
                                return GsonHelper.fromJson(GSON_INSTANCE, reader, JsonElement.class);
                            } catch (IOException e) {
                                AeonsPast.LOGGER.error("Couldn't read Item stats {}", location, e);
                                return null;
                            }
                        }).orElse(null);
                        DamageTypeData stats = getItemProperties(location, jsonElement);
                        if (stats != null) {
                            builder.put(location, stats);
                        }
                    } catch (Exception exception) {
                        AeonsPast.LOGGER.error("Couldn't parse Item stats {}", location, exception);
                    }
                });
            }
        } catch (IOException e) {
            AeonsPast.LOGGER.error("Couldn't read Item types {}", resourceLocation, e);
        }

        return builder.build();
    }

    @Override
    protected void apply(Map<ResourceLocation, DamageTypeData> ItemStatisticsMap, ResourceManager resourceManager, ProfilerFiller profilerFiller) {
        DAMAGE_TYPE_DATA.clear();
        DAMAGE_TYPE_DATA.putAll(ItemStatisticsMap);
    }

    /**
     * Attempts to parse Item properties from JSON. It first tries to use your new codec
     * (ItemStatisticalData.CODEC). If that fails or the JSON data doesn't match the structure,
     * it falls back to parseLegacyJson, which manually reads top-level fields and converts them.
     *
     * @param location    ResourceLocation of the Item
     * @param jsonElement The JSON Element containing Item data
     * @return An ItemStatisticalData object or null if invalid JSON
     */
    private DamageTypeData getItemProperties(ResourceLocation location, JsonElement jsonElement) {
        if (jsonElement == null || !jsonElement.isJsonObject()) {
            return null;
        }
        JsonObject json = jsonElement.getAsJsonObject();

        // Try parsing with the new codec
        DataResult<DamageTypeData> result =
                DamageTypeData.CODEC.parse(JsonOps.INSTANCE, json);

        // If parsing was successful, return the parsed data
        return result.result().orElseGet(() -> parseLegacyJson(location, json));
    }

    /**
     * Fallback utility method to parse top-level JSON fields from older data that doesn't match the nested codec structure.
     *
     * @param json The JsonObject with legacy fields
     * @return A new ItemStatisticalData constructed from legacy fields
     */
    private DamageTypeData parseLegacyJson(ResourceLocation location, JsonObject json) {
        AeonsPast.LOGGER.warn("Falling back to legacy JSON parsing for Item {}", location);

        List<String> entityTraits = new ArrayList<>();
        if (json.has("values") && json.get("values").isJsonArray()) {
            for (JsonElement element : json.getAsJsonArray("values")) {
                entityTraits.add(element.getAsString());
            }
        }
        // Construct and return the new object
        var object = new DamageTypeData(
                entityTraits

        );

        // Save object to cache folder.
        Path cacheFolderPath = FMLPaths.GAMEDIR.get().resolve("cache");
        Path ItemDataPath = cacheFolderPath.resolve("Item_data");
        Path ItemPath = ItemDataPath.resolve(location.getPath() + ".json");

        if (!ItemDataPath.toFile().exists()) {
            ItemDataPath.toFile().mkdirs();
        }

        DamageTypeData.CODEC.encodeStart(JsonOps.INSTANCE, object).result().ifPresent(result -> {
            try {
                String data = GSON_INSTANCE.toJson(result);
                Files.writeString(ItemPath, data);
                AeonsPast.LOGGER.info("Saved converted Item data for {}", location);
            } catch (IOException e) {
                AeonsPast.LOGGER.error("Failed to save converted Item data for {}", location, e);
            }
        });

        return object;
    }

    public static DamageTypes getByVanillaType(DamageSource source) {

        for (ResourceLocation rkey : DAMAGE_TYPE_DATA.keySet()) {

            Holder<DamageType> typeHolder = source.typeHolder();
            String key = typeHolder.getRegisteredName();
            DamageTypeData data = DAMAGE_TYPE_DATA.get(rkey);

            for (String vt : data.damage_types()) {

                if (vt.equals(key)) {


                    return DamageTypes.getByString(rkey.toString());

                }


            }


        }

        return null;
    }
}