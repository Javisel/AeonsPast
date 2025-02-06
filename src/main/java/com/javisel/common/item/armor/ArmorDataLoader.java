package com.javisel.common.item.armor;

import com.google.common.collect.ImmutableMap;
import com.google.gson.*;
import com.javisel.AeonsPast;
import com.javisel.common.item.armor.ArmorStatisticalData;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.JsonOps;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimplePreparableReloadListener;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.item.ItemStack;
import net.neoforged.fml.loading.FMLPaths;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class ArmorDataLoader extends SimplePreparableReloadListener<Map<ResourceLocation, ArmorStatisticalData>> {
    public static final Map<ResourceLocation, ArmorStatisticalData> Armor_STATISTICAL_DATA = new HashMap<>();
    private static final Gson GSON_INSTANCE = new GsonBuilder().setPrettyPrinting().create();
    private static final String folder = "items/armor";

    @Override
    protected Map<ResourceLocation, ArmorStatisticalData> prepare(ResourceManager resourceManager, ProfilerFiller profilerFiller) {
        ImmutableMap.Builder<ResourceLocation, ArmorStatisticalData> builder = ImmutableMap.builder();

        ResourceLocation resourceLocation = ResourceLocation.tryBuild(AeonsPast.MODID, "tags/item/armor/armor.json");

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

                        System.out.println("Loco: " + res.toString());

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
                        ArmorStatisticalData stats = getItemProperties(location, jsonElement);
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
    protected void apply(Map<ResourceLocation, ArmorStatisticalData> ItemStatisticsMap, ResourceManager resourceManager, ProfilerFiller profilerFiller) {
        Armor_STATISTICAL_DATA.clear();
        Armor_STATISTICAL_DATA.putAll(ItemStatisticsMap);
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
    private ArmorStatisticalData getItemProperties(ResourceLocation location, JsonElement jsonElement) {
        if (jsonElement == null || !jsonElement.isJsonObject()) {
            return null;
        }
        JsonObject json = jsonElement.getAsJsonObject();

        // Try parsing with the new codec
        DataResult<ArmorStatisticalData> result =
                ArmorStatisticalData.CODEC.parse(JsonOps.INSTANCE, json);

        // If parsing was successful, return the parsed data
        return result.result().orElseThrow();
    }



    public static ArmorStatisticalData getByItemStack(ItemStack stack) {

        ResourceLocation location = ResourceLocation.bySeparator(stack.getItemHolder().getRegisteredName(),':');

        return Armor_STATISTICAL_DATA.get(location);



    }








}