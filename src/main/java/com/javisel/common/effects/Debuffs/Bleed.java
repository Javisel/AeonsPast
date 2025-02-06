package com.javisel.common.effects.Debuffs;

import com.javisel.common.combat.ComplexDamageTypes;
import com.javisel.common.effects.ComplexEffect;
import com.javisel.common.effects.ComplexEffectInstance;
import com.javisel.common.effects.IDamageStatus;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.UUID;

public class Bleed extends ComplexEffect implements IDamageStatus {


    public Bleed( ) {
        super(MobEffectCategory.HARMFUL, 0xFF0000);

    }

    @Override
    public void applyTickableEffect(ComplexEffectInstance instance, LivingEntity entity) {
        super.applyTickableEffect(instance, entity);

        ServerLevel level = (ServerLevel) entity.level();
        DamageSource source = new DamageSource(ComplexDamageTypes.BLEED.getTypeHolder(level.registryAccess()), null,level.getEntity(instance.source),null);

        entity.hurtServer(level,source, (float) instance.getPower());

     }


    @Override
    public ComplexDamageTypes getDamageType() {
        return ComplexDamageTypes.SLASH;
    }

    @Override
    public ComplexEffectInstance getDefaultDamageInstance(LivingEntity attacker, LivingEntity victim, float DMG, DamageSource source) {

        double power =   DMG  * 0.15;


        return new ComplexEffectInstance(UUID.randomUUID(),attacker.getUUID(),power,(20 * 5) +1,20*5+1,false,new ArrayList<>());
    }
}
