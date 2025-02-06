package com.javisel.common.item.weapon;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import java.util.ArrayList;
import java.util.List;

public record WeaponStatisticalData(
        String item_type,
        double attack_power,
        double attack_speed,
        double range,
        double critical_chance,
        double critical_damage,
        double status_chance,
        double durability,
        String damageType,
        String attack_type,
        List<String> properties


) {

    public static final WeaponStatisticalData DEFAULT = new WeaponStatisticalData("misc",1,1,4,0,0,0,0,"aeonspast:impact","melee", new ArrayList<>());


    // Codec definition for serialization/deserialization
    public static final Codec<WeaponStatisticalData> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.STRING.fieldOf("item_type").forGetter(WeaponStatisticalData::item_type),
                    Codec.DOUBLE.fieldOf("attack_power")
                            .forGetter(WeaponStatisticalData::attack_power),
                    Codec.DOUBLE.fieldOf("attack_speed")
                            .forGetter(WeaponStatisticalData::attack_speed),
                    Codec.DOUBLE.fieldOf("range")
                            .forGetter(WeaponStatisticalData::range),
                    Codec.DOUBLE.fieldOf("critical_chance")
                            .forGetter(WeaponStatisticalData::critical_chance),
                    Codec.DOUBLE.fieldOf("critical_damage")
                            .forGetter(WeaponStatisticalData::critical_damage),
                    Codec.DOUBLE.fieldOf("status_chance")
                            .forGetter(WeaponStatisticalData::status_chance),
                    Codec.DOUBLE.fieldOf("durability")
                            .forGetter(WeaponStatisticalData::durability),
                    Codec.STRING.fieldOf("damage_type").forGetter(WeaponStatisticalData::damageType),
                    Codec.STRING.fieldOf("attack_type").forGetter(WeaponStatisticalData::attack_type),
                    Codec.STRING.listOf().fieldOf("properties")
                            .forGetter(WeaponStatisticalData::properties)


            ).apply(instance, WeaponStatisticalData::new)
    );









}
