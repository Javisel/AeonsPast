package com.javisel.common.item;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

public record AttributeStatisticsPair(Attribute attribute, AttributeModifier.Operation operation, float min, float max)  {

    public static final Codec<AttributeStatisticsPair> ATTRIBUTE_PAIR_CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                   BuiltInRegistries.ATTRIBUTE.byNameCodec().fieldOf("attribute").forGetter(AttributeStatisticsPair::attribute),
                    AttributeModifier.Operation.CODEC.fieldOf("operation").forGetter(AttributeStatisticsPair::operation),
                    Codec.FLOAT.fieldOf("min").forGetter(AttributeStatisticsPair::min),
                    Codec.FLOAT.fieldOf("max").forGetter(AttributeStatisticsPair::max)


            ).apply(instance, AttributeStatisticsPair::new)
    );

}
