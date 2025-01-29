package com.javisel.common.item;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public record WeaponData(String item_type, String damage_type) {

    public static final Codec<WeaponData> BASIC_CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.STRING.fieldOf("item_type").forGetter(WeaponData::item_type),
                    Codec.STRING.fieldOf("damage_type").forGetter(WeaponData::damage_type)

            ).apply(instance, WeaponData::new)
    );
    public static final StreamCodec<ByteBuf, WeaponData> BASIC_STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8, WeaponData::item_type,
            ByteBufCodecs.STRING_UTF8, WeaponData::damage_type,

            WeaponData::new
    );
}
