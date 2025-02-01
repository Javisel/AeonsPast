package com.javisel.common.effects.Debuffs;

import com.javisel.aeonspast.common.combat.damage.instances.DamageInstance;
import com.javisel.aeonspast.common.combat.DamageTypeEnum;
import com.javisel.aeonspast.common.effects.ComplexEffectInstance;
import com.javisel.aeonspast.common.effects.ComplexStatChangeEffect;
import com.javisel.aeonspast.common.effects.IDamageStatus;
import com.javisel.aeonspast.common.registration.AttributeRegistration;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

import java.util.UUID;

public class Radiance extends ComplexStatChangeEffect  implements IDamageStatus {


    private static final UUID RADIANCE_ID = UUID.fromString("897b8bc7-e14f-4f0e-9656-a90a50d23287");

    public Radiance() {
        super(AttributeRegistration.DAMAGE_INTAKE.get(), RADIANCE_ID, MobEffectCategory.HARMFUL, 0xFFd700, AttributeModifier.Operation.ADDITION);
        super.doesStack=false;
    }


    @Override
    public DamageTypeEnum getDamageType() {
        return DamageTypeEnum.RADIANT;
    }

    @Override
    public ComplexEffectInstance getDefaultDamageInstance(LivingEntity attacker, LivingEntity victim, DamageInstance procInstance) {






        ComplexEffectInstance instance = new ComplexEffectInstance(UUID.randomUUID(),attacker.getUUID(),5,(20 * 5) +1);






        return instance;
    }


    @Override
    public void addnewComplexInstance(ComplexEffectInstance instance, LivingEntity user) {
        super.addnewComplexInstance(instance, user);


        MobEffectInstance glowing = new MobEffectInstance(MobEffects.GLOWING,(int)instance.duration,0,true,false,false);
        user.addEffect(glowing);


    }
}
