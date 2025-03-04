package com.javisel.common.effects.buffs.generic;

import com.javisel.AeonsPast;
import com.javisel.common.effects.ComplexStatChangeEffect;
import com.javisel.common.registration.AttributeRegistration;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

import java.util.UUID;

public class PhysicalPowerBuff extends ComplexStatChangeEffect {
    public PhysicalPowerBuff() {
        super(AttributeRegistration.STRENGTH, ResourceLocation.fromNamespaceAndPath(AeonsPast.MODID,"physical_power"), MobEffectCategory.BENEFICIAL, 0x0FFA500, AttributeModifier.Operation.ADD_VALUE);
    }
}
