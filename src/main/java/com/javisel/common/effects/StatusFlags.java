package com.javisel.common.effects;

import com.mojang.serialization.Codec;
import net.minecraft.util.StringRepresentable;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public enum StatusFlags implements StringRepresentable{


    MULTIPLICATIVE_BASE(0),
    MULTIPLICATIVE_TOTAL(1),

    ADDITIVE(2);


    public static final StatusFlags[] STATUS_FLAGS = Arrays.stream(values()).sorted(Comparator.comparingInt(StatusFlags::getId)).toArray(StatusFlags[]::new);
    int id;

    public static final Codec<List<StatusFlags>> LIST_CODEC = StringRepresentable.fromValues(StatusFlags::values).listOf();


    StatusFlags(int id) {
        this.id = id;
    }


    public int getId() {
        return id;
    }

    @Override
    public String getSerializedName() {
        return name();
    }
}
