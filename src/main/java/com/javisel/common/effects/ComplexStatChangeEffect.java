package com.javisel.common.effects;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

import java.util.UUID;

public class ComplexStatChangeEffect extends StatusEffect {


    private final Holder<Attribute> attribute;
    private final ResourceLocation effectId;
    private final AttributeModifier.Operation operation;
   protected   boolean doesStack = true;
    public ComplexStatChangeEffect(Holder<Attribute> attribute, ResourceLocation effectId, MobEffectCategory effectCategory, int colorCode, AttributeModifier.Operation operation) {
        super(effectCategory, colorCode);
        this.attribute = attribute;
        this.effectId = effectId;
        this.operation = operation;
    }



    @Override
    public void addnewComplexInstance(ComplexEffectInstance instance, LivingEntity user) {
        super.addnewComplexInstance(instance, user);


    }


    @Override
    public void recalculateInstances(LivingEntity user) {


        AttributeInstance attributeInstance = user.getAttribute(attribute);


        attributeInstance.removeModifier(effectId);


        double power = 0;

        for (ComplexEffectInstance inst : getAllInstancesOnEntity(user)) {

            if (doesStack) {
                power += inst.power;

            } else {

                power = Math.max(inst.power, power);
            }

        }


        AttributeModifier modifier = new AttributeModifier(effectId, power, operation);

        user.getAttribute(attribute).addPermanentModifier(modifier);

    }


}
