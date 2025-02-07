package com.javisel.common.item.armor;

import com.javisel.common.item.AttributeStatistic;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.Attribute;

import java.util.List;

public record ArmorStatisticalData(ArmorType type, EquipmentSlot equipmentSlot, List<AttributeStatistic> statistics, List<String> properties ) {

    public static final Codec<ArmorStatisticalData> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(

                    ArmorType.CODEC.fieldOf("armor_type").forGetter(ArmorStatisticalData::type),
                   EquipmentSlot.CODEC.fieldOf("equipment_slot")
                            .forGetter(ArmorStatisticalData::equipmentSlot),

                    AttributeStatistic.ATTRIBUTE_PAIR_CODEC.listOf().fieldOf("statistics").forGetter(ArmorStatisticalData::statistics),
                    Codec.STRING.listOf().fieldOf("properties")
                            .forGetter(ArmorStatisticalData::properties)

            ).apply(instance, ArmorStatisticalData::new)
    );

public AttributeStatistic getStatisticPair(Attribute attribute) {


    for (AttributeStatistic statisticsPair : statistics) {
        if (statisticsPair.attribute() == attribute) {

            return statisticsPair;
        }


    }

    return null;
}




}
