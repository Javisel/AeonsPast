package com.javisel.common.entity.projectile;

import com.javisel.AeonsPast;
import com.javisel.common.registration.AttributeRegistration;
import com.javisel.common.registration.DataComponentsRegistration;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;

import javax.annotation.Nullable;
import java.util.Random;
import java.util.UUID;

public record ProjectileStatisticalData(

        boolean override,
        double damage,
         
        String damage_type
       

) {

    // Codec definition for serialization/deserialization
    public static final Codec<ProjectileStatisticalData> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.BOOL.fieldOf("override").forGetter(ProjectileStatisticalData::override),
                    Codec.DOUBLE.fieldOf("damage")
                            .forGetter(ProjectileStatisticalData::damage),
                    Codec.STRING.fieldOf("damage_type").forGetter(ProjectileStatisticalData::damage_type)
                
                  


            ).apply(instance, ProjectileStatisticalData::new)
    );



}