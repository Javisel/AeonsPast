package com.javisel.common.effects;

import com.javisel.AeonsPast;
import com.javisel.utilities.StringKeys;
import com.mojang.serialization.Codec;
import com.mojang.serialization.Dynamic;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.UUIDUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ComplexEffectInstance {

    public static final String STATUS_FLAGS = "status_flags";
    public final UUID instanceID;
    public final UUID source;
    public double power;
    public float duration;
    public float initialDuration;
    public float tickRate = 20;
    public boolean remove = false;
    List<StatusFlags> statusFlags = new ArrayList<>();

    public static ComplexEffectInstance of(UUID instance,UUID source,double power,float duration) {
        return new ComplexEffectInstance(instance,source,power,duration,duration,false,new ArrayList<>());
    }

    public ComplexEffectInstance(UUID instanceID, UUID source, double power, float duration, float initialDuration, boolean remove, List<StatusFlags> statusFlags) {
        this.instanceID = instanceID;
        this.source = source;
        this.power = power;
        this.duration = duration;
        this.initialDuration = initialDuration;
        this.statusFlags = statusFlags;
    }


    public static ComplexEffectInstance fromNBT(CompoundTag tag) {
        return CODEC.parse(new Dynamic<>(NbtOps.INSTANCE,tag)).resultOrPartial(AeonsPast.LOGGER::error).orElseThrow();
    }

    public CompoundTag toNBT() {
        return (CompoundTag) CODEC.encodeStart(NbtOps.INSTANCE,this).resultOrPartial(AeonsPast.LOGGER::error).orElseThrow();
    }

    public UUID getInstanceID() {
        return instanceID;
    }

    public UUID getSource() {
        return source;
    }

    public double getPower() {
        return power;
    }

    public float getDuration() {
        return duration;
    }

    public float getInitialDuration() {
        return initialDuration;
    }


    public static final Codec<ComplexEffectInstance> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                            UUIDUtil.CODEC.fieldOf(StringKeys.INSTANCE_ID).forGetter(ComplexEffectInstance::getInstanceID),
                            UUIDUtil.CODEC.fieldOf(StringKeys.SOURCE_ID).forGetter(ComplexEffectInstance::getSource),
                            Codec.DOUBLE.fieldOf(StringKeys.POWER).forGetter(ComplexEffectInstance::getPower),
                            Codec.FLOAT.fieldOf(StringKeys.DURATION).forGetter(ComplexEffectInstance::getDuration),
                            Codec.FLOAT.fieldOf(StringKeys.INITIAL_DURATION).forGetter(ComplexEffectInstance::getInitialDuration),
                            Codec.BOOL.fieldOf(StringKeys.REMOVE).forGetter(complexEffectInstance -> complexEffectInstance.remove),
                    StatusFlags.LIST_CODEC.fieldOf(STATUS_FLAGS).forGetter(complexEffectInstance -> complexEffectInstance.statusFlags)
                            )
                    .apply(instance, ComplexEffectInstance::new)
    );






}
