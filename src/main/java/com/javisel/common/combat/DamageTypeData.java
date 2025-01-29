package com.javisel.common.combat;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import java.util.List;

public record DamageTypeData(

        List<String> damage_types

) {
   ;

    // Codec definition for serialization/deserialization
    public static final Codec<DamageTypeData> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.STRING.listOf().fieldOf("values")
                            .forGetter(DamageTypeData::damage_types)



            ).apply(instance, DamageTypeData::new)
    );



}
