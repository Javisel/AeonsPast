package com.javisel.common.effects.buffs.generic;

import com.javisel.AeonsPast;
import com.javisel.common.effects.ComplexStatChangeEffect;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class AttackSpeedBuff extends ComplexStatChangeEffect {


    public AttackSpeedBuff() {

        super(Attributes.ATTACK_SPEED, ResourceLocation.fromNamespaceAndPath(AeonsPast.MODID,"attack_speed"), MobEffectCategory.BENEFICIAL, 0, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
    }


}
