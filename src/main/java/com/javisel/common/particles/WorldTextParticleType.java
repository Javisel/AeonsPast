package com.javisel.common.particles;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;

public class WorldTextParticleType extends ParticleType<WorldTextParticleOptions >{
    public WorldTextParticleType(boolean overrideLimitter) {
        super(overrideLimitter);
    }

    @Override
    public MapCodec<WorldTextParticleOptions> codec() {
        return WorldTextParticleOptions.CODEC;
    }

    @Override
    public StreamCodec<? super RegistryFriendlyByteBuf, WorldTextParticleOptions> streamCodec() {
        return WorldTextParticleOptions.STREAM_CODEC;
    }
}
