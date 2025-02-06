package com.javisel.common.item.armor;

import com.javisel.common.entity.EntityStatisticalData;
import com.javisel.common.item.AttributeStatisticsPair;
import com.javisel.common.item.ItemRarity;
import com.javisel.common.item.StatisticPair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.item.equipment.ArmorType;

import java.util.ArrayList;
import java.util.List;

public record ArmorStatisticalData(String armor_type, String rarity, List<AttributeStatisticsPair> statistics,List<String> properties ) {


public static final String ITEM_MOD_ID = "4703e862-a7ae-4697-aeea-f58ac8697e10";

public static final String BASE_STATS = "aeonspast:armorstats";

    public static final Codec<ArmorStatisticalData> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(

                    Codec.STRING.fieldOf("rarity")
                            .forGetter(ArmorStatisticalData::rarity),
                    Codec.STRING.fieldOf("armor_type")
                            .forGetter(ArmorStatisticalData::armor_type),

                    AttributeStatisticsPair.ATTRIBUTE_PAIR_CODEC.listOf().fieldOf("statistics").forGetter(ArmorStatisticalData::statistics),
                    Codec.STRING.listOf().fieldOf("properties")
                            .forGetter(ArmorStatisticalData::properties)

            ).apply(instance, ArmorStatisticalData::new)
    );

public AttributeStatisticsPair getStatisticPair(Attribute attribute) {


    for (AttributeStatisticsPair statisticsPair : statistics) {
        if (statisticsPair.attribute() == attribute) {

            return statisticsPair;
        }


    }

    return null;
}


public ArmorType getArmorType() {
    return ArmorType.valueOf(armor_type);
}


public EquipmentSlot getEquipmentSlot() {


    return getArmorType().getSlot();


}
}
