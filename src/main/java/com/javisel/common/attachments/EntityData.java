package com.javisel.common.attachments;

import com.javisel.common.effects.ComplexEffectInstance;
import com.javisel.utilities.StringKeys;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class EntityData {

    int ticks;
    int level;
    Map<Holder<MobEffect>, List<ComplexEffectInstance>> mobEffectArrayListHashMap;

    public EntityData() {
        this(1,new HashMap<>());
    }

    public EntityData(int level, Map<Holder<MobEffect>, List<ComplexEffectInstance>> mobEffectArrayListHashMap) {
        this.level = level;
        this.mobEffectArrayListHashMap = mobEffectArrayListHashMap;
    }


    public static final Codec<EntityData> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
            Codec.INT.fieldOf(StringKeys.LEVEL).forGetter(EntityData::getLevel),
            Codec.unboundedMap(MobEffect.CODEC,ComplexEffectInstance.CODEC.listOf()).fieldOf("map").forGetter(entityData -> entityData.mobEffectArrayListHashMap)
            ).apply(instance, EntityData::new));

    //TODO move spell and resource stuff out of EntityData into PlayerData!

    public int getTicks() {
        return ticks;
    }

    public void tick() {
        if (ticks == 20) {
            ticks = 0;
            return;
        }
        ticks++;
    }

    public int getLevel() {
        return level;
    }

    public Map<Holder<MobEffect>, List<ComplexEffectInstance>> getMobEffectArrayListHashMap() {
        return mobEffectArrayListHashMap;
    }
}