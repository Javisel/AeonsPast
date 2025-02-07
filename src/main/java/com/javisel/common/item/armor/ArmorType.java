package com.javisel.common.item.armor;

import com.mojang.serialization.Codec;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.EquipmentSlot;

import java.util.List;

public enum ArmorType implements StringRepresentable {

    LIGHT("light"),
    MEDIUM("medium"),
    HEAVY("heavy");

    public static final Codec<ArmorType> CODEC = StringRepresentable.fromValues(ArmorType::values);

    final String name;
    public static final List<ArmorType> VALUES = List.of(values());

      ArmorType(String name){

        this.name=name;

    }

    @Override
    public String getSerializedName() {
        return this.name;
    }
}
