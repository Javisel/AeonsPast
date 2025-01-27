package com.javisel.common.registration;

import com.javisel.AeonsPast;
import com.javisel.common.particles.WorldTextParticleType;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ParticleTypeRegistration {

    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(Registries.PARTICLE_TYPE, AeonsPast.MODID);



    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> DAMAGE_TYPE_PARTICLE = PARTICLE_TYPES.register(
            // The name of the particle type.
            "damage_type_particle",
            // The supplier. The boolean parameter denotes whether setting the Particles option in the
            // video settings to Minimal will affect this particle type or not; this is false for
            // most vanilla particles, but true for e.g. explosions, campfire smoke, or squid ink.
            () -> new SimpleParticleType(false)
    );
    public static final DeferredHolder<ParticleType<?>, WorldTextParticleType> WORLD_TEXT_PARTICLE = PARTICLE_TYPES.register(
            // The name of the particle type.
            "world_text_particle",

            () -> new WorldTextParticleType(true)
    );
}