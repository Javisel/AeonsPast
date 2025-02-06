package com.javisel.common.effects;

import com.javisel.common.combat.ComplexDamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;

public interface IDamageStatus {


    public ComplexDamageTypes getDamageType();

    public ComplexEffectInstance getDefaultDamageInstance(LivingEntity attacker, LivingEntity victim, float DMG, DamageSource source);

}
