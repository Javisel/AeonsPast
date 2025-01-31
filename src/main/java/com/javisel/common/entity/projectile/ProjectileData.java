package com.javisel.common.entity.projectile;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.neoforged.neoforge.common.util.INBTSerializable;

public record ProjectileData(boolean override, double damage, double status_chance, double critical_chance, double critical_damage, String damage_type) implements INBTSerializable {

    private static final String OVERRIDE = "override";
    private static final String DAMAGE = "damage";
    private static final String DAMAGE_TYPE = "damage_type";
    public static final Codec<ProjectileData> BASIC_CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.BOOL.fieldOf("override").forGetter(ProjectileData::override),
                    Codec.DOUBLE.fieldOf("damage").forGetter(ProjectileData::damage),
                    Codec.DOUBLE.fieldOf("status_chance").forGetter(ProjectileData::status_chance),
                    Codec.DOUBLE.fieldOf("critical_chance").forGetter(ProjectileData::critical_chance),
                    Codec.DOUBLE.fieldOf("critical_damage").forGetter(ProjectileData::critical_damage),
                    Codec.STRING.fieldOf("damage_type").forGetter(ProjectileData::damage_type)

            ).apply(instance, ProjectileData::new)
    );
    public static final StreamCodec<ByteBuf, ProjectileData> BASIC_STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.BOOL, ProjectileData::override,
            ByteBufCodecs.DOUBLE, ProjectileData::damage,
            ByteBufCodecs.DOUBLE, ProjectileData::status_chance,
            ByteBufCodecs.DOUBLE, ProjectileData::critical_chance,
            ByteBufCodecs.DOUBLE, ProjectileData::critical_damage,
            ByteBufCodecs.STRING_UTF8, ProjectileData::damage_type,

            ProjectileData::new
    );

    @Override
    public Tag serializeNBT(HolderLookup.Provider provider) {

        CompoundTag tag =new CompoundTag();
        tag.putBoolean(OVERRIDE,override);

        tag.putDouble(DAMAGE,damage);
        tag.putString(DAMAGE_TYPE,damage_type);
        return tag;
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, Tag nbt) {




    }
}