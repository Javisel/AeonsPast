package com.javisel.common.effects.Debuffs;

import com.javisel.common.effects.ComplexEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class TauntDebuff extends ComplexEffect {
    public TauntDebuff() {
        super(MobEffectCategory.HARMFUL, 1);
    }
}
