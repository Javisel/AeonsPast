package com.javisel.common.item.armor;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public record ArmorData (String armorType) {

    public static final Codec<ArmorData> BASIC_CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.STRING.fieldOf("armor_type").forGetter(ArmorData::armorType)

            ).apply(instance, ArmorData::new)
    );
    public static final StreamCodec<ByteBuf, ArmorData> BASIC_STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8, ArmorData::armorType,


            ArmorData::new
    );
}