package com.javisel.common.combat;


import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.neoforged.neoforge.common.util.INBTSerializable;

import java.util.ArrayList;
import java.util.List;

public record EntityCombatData(int critsHit, boolean status, List<Double> bleeds) implements INBTSerializable {

    public static final EntityCombatData DEFAULT = new EntityCombatData(0,false, new ArrayList<>());


    public static final Codec<EntityCombatData> BASIC_CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.INT.fieldOf("crits_hit").forGetter(EntityCombatData::critsHit),
                    Codec.BOOL.fieldOf("status").forGetter(EntityCombatData::status),
                    Codec.DOUBLE.listOf().fieldOf("bleeds").forGetter(EntityCombatData::bleeds)


            ).apply(instance, EntityCombatData::new)
    );
    public static final StreamCodec<ByteBuf, EntityCombatData> BASIC_STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.INT, EntityCombatData::critsHit,
            ByteBufCodecs.BOOL, EntityCombatData::status,
            ByteBufCodecs.DOUBLE.apply(ByteBufCodecs.list()), EntityCombatData::bleeds,
            EntityCombatData::new
    );

    @Override
    public Tag serializeNBT(HolderLookup.Provider provider) {

        CompoundTag tag = new CompoundTag();

        return tag;
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, Tag nbt) {


    }
}