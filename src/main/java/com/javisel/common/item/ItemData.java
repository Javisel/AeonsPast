package com.javisel.common.item;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public record ItemData(String item_type, String rarity) {

    public static final Codec<ItemData> BASIC_CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.STRING.fieldOf("item_type").forGetter(ItemData::item_type),
                    Codec.STRING.fieldOf("rarity").forGetter(ItemData::rarity)


            ).apply(instance, ItemData::new)
    );
    public static final StreamCodec<ByteBuf, ItemData> BASIC_STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8, ItemData::item_type,
            ByteBufCodecs.STRING_UTF8, ItemData::rarity,
            ItemData::new
    );
}