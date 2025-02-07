package com.javisel.common.item;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

public record AttributeStatistic(Attribute attribute, AttributeModifier.Operation operation, float value)  {

    public static final Codec<AttributeStatistic> ATTRIBUTE_PAIR_CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                   BuiltInRegistries.ATTRIBUTE.byNameCodec().fieldOf("attribute").forGetter(AttributeStatistic::attribute),
                    AttributeModifier.Operation.CODEC.fieldOf("operation").forGetter(AttributeStatistic::operation),
                    Codec.FLOAT.fieldOf("value").forGetter(AttributeStatistic::value)


            ).apply(instance, AttributeStatistic::new)
    );

}
