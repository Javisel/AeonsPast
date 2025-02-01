package com.javisel.common.effects.buffs.spell;

import com.javisel.aeonspast.common.combat.damage.instances.DamageInstance;
import com.javisel.aeonspast.common.combat.damage.sources.APDamageSource;
import com.javisel.aeonspast.common.effects.ComplexEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;

public class UppercutSpellBuff extends ComplexEffect {

    public UppercutSpellBuff() {
        super(MobEffectCategory.BENEFICIAL, 5);
    }


    @Override
    public void onHitEffect(LivingEntity attacker, LivingEntity blocker, APDamageSource damageSource) {


        DamageInstance instance = damageSource.instance;

        if (instance.isMelee() && instance.doesProcWeaponHitEffects() && instance.getDamageDevice() instanceof ItemStack) {


            double power = .8;
            float ratioX = 0;
            float ratioZ = 0;
            power *= 1.0D - blocker.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE);

            if (!(power <= 0.0D)) {
                blocker.hasImpulse = true;
                Vec3 vec3 = blocker.getDeltaMovement();
                Vec3 vec31 = (new Vec3(ratioX, power, ratioZ));
                blocker.setDeltaMovement(vec3.x / 2.0D - vec31.x, power, vec3.z / 2.0D - vec31.z);

            }

            consumeEffect(attacker);

        }
    }


}
