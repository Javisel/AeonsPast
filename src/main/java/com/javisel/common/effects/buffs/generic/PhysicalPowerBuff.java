package com.javisel.common.effects.buffs.generic;

import com.javisel.aeonspast.common.effects.ComplexStatChangeEffect;
import com.javisel.aeonspast.common.registration.AttributeRegistration;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.UUID;

public class PhysicalPowerBuff extends ComplexStatChangeEffect {
    public PhysicalPowerBuff() {
        super(AttributeRegistration.PHYSICAL_POWER.get(), UUID.fromString("3545c909-9ab7-4249-a780-aeb7aa2d78bb"), MobEffectCategory.BENEFICIAL, 0x0FFA500, AttributeModifier.Operation.ADDITION);
    }
}
