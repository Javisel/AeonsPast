package com.javisel.common.effects;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;

import java.util.function.Function;

public class APEffect extends MobEffect {
    protected APEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    protected APEffect(MobEffectCategory category, int color, ParticleOptions particle) {
        super(category, color, particle);
    }

    protected APEffect(MobEffectCategory p_category, int p_color, Function<MobEffectInstance, ParticleOptions> particleFactory) {
        super(p_category, p_color, particleFactory);
    }
}
