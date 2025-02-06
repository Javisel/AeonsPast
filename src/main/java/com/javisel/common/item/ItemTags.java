package com.javisel.common.item;

import com.javisel.AeonsPast;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ItemTags {

    public static final TagKey<Item> WEAPONS = TagKey.create(
            // The registry key. The type of the registry must match the generic type of the tag.
            Registries.ITEM,
            // The location of the tag. This example will put our tag at data/examplemod/tags/blocks/example_tag.json.
            ResourceLocation.fromNamespaceAndPath(AeonsPast.MODID, "weapons/weapons")
    );
    public static final TagKey<Item> ARMOR = TagKey.create(
            // The registry key. The type of the registry must match the generic type of the tag.
            Registries.ITEM,
            // The location of the tag. This example will put our tag at data/examplemod/tags/blocks/example_tag.json.
            ResourceLocation.fromNamespaceAndPath(AeonsPast.MODID, "armor/armor")
    );

}
