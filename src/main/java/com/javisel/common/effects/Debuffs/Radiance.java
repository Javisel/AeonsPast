package com.javisel.common.effects.Debuffs;

import com.javisel.AeonsPast;
import com.javisel.common.combat.ComplexDamageTypes;
import com.javisel.common.effects.ComplexEffectInstance;
import com.javisel.common.effects.ComplexStatChangeEffect;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.UUID;

public class Radiance extends ComplexStatChangeEffect  {


    private static final UUID RADIANCE_ID = UUID.fromString("897b8bc7-e14f-4f0e-9656-a90a50d23287");

    public Radiance() {
        super(Attributes.ARMOR, ResourceLocation.fromNamespaceAndPath(AeonsPast.MODID,"radiance"), MobEffectCategory.HARMFUL, 0xFFd700, AttributeModifier.Operation.ADD_VALUE);
        super.doesStack=false;
    }




    @Override
    public void addnewComplexInstance(ComplexEffectInstance instance, LivingEntity user) {
        super.addnewComplexInstance(instance, user);


        MobEffectInstance glowing = new MobEffectInstance(MobEffects.GLOWING,(int)instance.duration,0,true,false,false);
        user.addEffect(glowing);


    }
}
