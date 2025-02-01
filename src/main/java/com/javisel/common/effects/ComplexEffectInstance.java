package com.javisel.common.effects;

import com.javisel.aeonspast.utilities.StringKeys;
import net.minecraft.nbt.CompoundTag;

import java.util.ArrayList;
import java.util.Arrays;
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
    ArrayList<StatusFlags> statusFlags = new ArrayList<>();

    public ComplexEffectInstance(UUID instanceID, UUID source, double power, float duration) {
        this.instanceID = instanceID;
        this.source = source;
        this.power = power;
        this.duration = duration;
        this.initialDuration = duration;
    }

    public ComplexEffectInstance(UUID instanceID, UUID source, double power, float duration, StatusFlags... statusFlags) {
        this.instanceID = instanceID;
        this.source = source;
        this.power = power;
        this.duration = duration;
        this.initialDuration = duration;
        this.statusFlags = new ArrayList<StatusFlags>(Arrays.asList(statusFlags));
    }

    private ComplexEffectInstance(UUID instanceID, UUID source, double power, float duration, float initialDuration) {
        this.instanceID = instanceID;
        this.source = source;
        this.power = power;
        this.duration = duration;
        this.initialDuration = initialDuration;
    }
    public ComplexEffectInstance( UUID source, double power, float duration) {
        this.instanceID = UUID.randomUUID();
        this.source = source;
        this.power = power;
        this.duration = duration;
        this.initialDuration = duration;
    }

    public ComplexEffectInstance( UUID source, double power, float duration, StatusFlags... statusFlags) {
        this.instanceID = UUID.randomUUID();
        this.source = source;
        this.power = power;
        this.duration = duration;
        this.initialDuration = duration;
        this.statusFlags = new ArrayList<StatusFlags>(Arrays.asList(statusFlags));
    }
    public static ComplexEffectInstance fromNBT(CompoundTag tag) {


        ComplexEffectInstance instance = new ComplexEffectInstance(tag.getUUID(StringKeys.INSTANCE_ID),tag.hasUUID(StringKeys.SOURCE_ID) ?  tag.getUUID(StringKeys.SOURCE_ID) : null, tag.getDouble(StringKeys.POWER), tag.getFloat(StringKeys.DURATION), tag.getFloat(StringKeys.INITIAL_DURATION));

        instance.remove = tag.getBoolean(StringKeys.REMOVE);
        CompoundTag flags = tag.getCompound(STATUS_FLAGS);

        for (String key : flags.getAllKeys()) {


            int id = flags.getInt(key);

            StatusFlags flag = StatusFlags.STATUS_FLAGS[id];

            instance.statusFlags.add(flag);


        }


        return instance;


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

    public CompoundTag toNBT() {


        CompoundTag tag = new CompoundTag();

        tag.putUUID(StringKeys.INSTANCE_ID, instanceID);

        if (source !=null) {
            tag.putUUID(StringKeys.SOURCE, source);
        }
        tag.putDouble(StringKeys.POWER, power);
        tag.putFloat(StringKeys.DURATION, duration);
        tag.putFloat(StringKeys.INITIAL_DURATION, initialDuration);
        tag.putBoolean(StringKeys.REMOVE, remove);

        CompoundTag statusFlagTag = new CompoundTag();

        int i = 0;
        for (StatusFlags flags : statusFlags) {

            statusFlagTag.putInt("flag_" + i, flags.id);

            i++;

        }

        tag.put(STATUS_FLAGS, statusFlagTag);


        return tag;
    }


}
