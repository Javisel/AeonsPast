package com.javisel.common.effects;

import com.javisel.aeonspast.common.combat.damage.instances.DamageInstance;
import com.javisel.aeonspast.common.combat.DamageTypeEnum;
import net.minecraft.world.entity.LivingEntity;

public interface IDamageStatus {



    public DamageTypeEnum getDamageType();

    public ComplexEffectInstance getDefaultDamageInstance(LivingEntity attacker, LivingEntity victim, DamageInstance procInstance);




}
