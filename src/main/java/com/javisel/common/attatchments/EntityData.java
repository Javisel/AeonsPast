package com.javisel.common.attatchments;

import com.javisel.common.effects.ComplexEffectInstance;
import com.javisel.utilities.StringKeys;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class EntityData {

    int ticks;
    int level;
    HashMap<MobEffect, ArrayList<ComplexEffectInstance>> mobEffectArrayListHashMap;

    public EntityData() {
        this(1,new HashMap<>());
    }

    public EntityData(int level,HashMap<MobEffect, ArrayList<ComplexEffectInstance>> mobEffectArrayListHashMap) {
        this.level = level;
        this.mobEffectArrayListHashMap = mobEffectArrayListHashMap;
    }


    public static final Codec<EntityData> CODEC = RecordCodecBuilder.create(            instance -> instance.group(
            Codec.INT.fieldOf(StringKeys.LEVEL).forGetter(EntityData::getLevel),

            ).apply(instance, EntityData::new));

    //TODO move spell and resource stuff out of EntityData into PlayerData!



    public CompoundTag writeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putInt(StringKeys.LEVEL, level);


        CompoundTag effects = new CompoundTag();


        for (Map.Entry<MobEffect, ArrayList<ComplexEffectInstance>> entry : mobEffectArrayListHashMap.entrySet()) {

            CompoundTag effect = new CompoundTag();

            int i = 0;
            for (ComplexEffectInstance instance : entry.getValue()) {

                effect.put("entry_" + i, instance.toNBT());

                i++;
            }
            effects.put(entry.getKey().getRegistryName().toString(), effect);

        }


        tag.put(StringKeys.EFFECTS, effects);


        return tag;
    }

    @Override
    public void readNBT(CompoundTag tag) {


        level = tag.getInt(StringKeys.LEVEL);


        if (tag.contains(StringKeys.EFFECTS)) {

            CompoundTag effects = tag.getCompound(StringKeys.EFFECTS);


            for (String key : effects.getAllKeys()) {

                ResourceLocation entryLocation = ResourceLocation.parse(key);


                CompoundTag effect = effects.getCompound(key);
                ArrayList<ComplexEffectInstance> effectInstances = new ArrayList<>();


                if (effect.isEmpty()) {
                    continue;
                }
                for (String entry : effect.getAllKeys()) {


                    System.out.println("Entry: " + entry);
                    effectInstances.add(ComplexEffectInstance.fromNBT(effect.getCompound(entry)));


                }

                mobEffectArrayListHashMap.put(ForgeRegistries.MOB_EFFECTS.getValue(entryLocation), effectInstances);


            }


        }


    }

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


    public HashMap<MobEffect, ArrayList<ComplexEffectInstance>> getMobEffectArrayListHashMap() {
        return mobEffectArrayListHashMap;
    }
}

