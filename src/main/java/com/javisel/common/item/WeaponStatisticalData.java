package com.javisel.common.item;

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

public record WeaponStatisticalData(
        String item_type,
        double attack_power,
        double attack_speed,
        double range,
        double critical_chance,
        double critical_damage,
        double status_chance,
        double durability,
        String damageType,
        String attack_type

) {

    // Codec definition for serialization/deserialization
    public static final Codec<WeaponStatisticalData> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.STRING.fieldOf("item_type").forGetter(WeaponStatisticalData::item_type),
                    Codec.DOUBLE.fieldOf("attack_power")
                            .forGetter(WeaponStatisticalData::attack_power),
                    Codec.DOUBLE.fieldOf("attack_speed")
                            .forGetter(WeaponStatisticalData::attack_speed),
                    Codec.DOUBLE.fieldOf("range")
                            .forGetter(WeaponStatisticalData::range),
                    Codec.DOUBLE.fieldOf("critical_chance")
                            .forGetter(WeaponStatisticalData::critical_chance),
                    Codec.DOUBLE.fieldOf("critical_damage")
                            .forGetter(WeaponStatisticalData::critical_damage),
                    Codec.DOUBLE.fieldOf("status_chance")
                            .forGetter(WeaponStatisticalData::status_chance),
                    Codec.DOUBLE.fieldOf("durability")
                            .forGetter(WeaponStatisticalData::durability),
                    Codec.STRING.fieldOf("damage_type").forGetter(WeaponStatisticalData::damageType),
                    Codec.STRING.fieldOf("attack_type").forGetter(WeaponStatisticalData::attack_type)


            ).apply(instance, WeaponStatisticalData::new)
    );


public  void  loadToItem(LivingEntity entity, ItemStack stack) {


    stack.set(DataComponentsRegistration.WEAPON_DATA,new WeaponData(item_type,damageType));

    stack.remove(DataComponents.ATTRIBUTE_MODIFIERS);
    AttributeModifier attackmod = new AttributeModifier(Item.BASE_ATTACK_DAMAGE_ID,getValue(entity ,+attack_power) , AttributeModifier.Operation.ADD_VALUE);
    AttributeModifier attkspdmod = new AttributeModifier(Item.BASE_ATTACK_SPEED_ID,getValue(entity ,-4+attack_speed), AttributeModifier.Operation.ADD_VALUE);
    AttributeModifier critchance = new AttributeModifier(ResourceLocation.fromNamespaceAndPath(AeonsPast.MODID,"base_stats"),getValue(entity ,critical_chance), AttributeModifier.Operation.ADD_VALUE);
    AttributeModifier critdmg = new AttributeModifier(ResourceLocation.fromNamespaceAndPath(AeonsPast.MODID,"base_stats"),getValue(entity ,critical_damage), AttributeModifier.Operation.ADD_VALUE);
    AttributeModifier statuschance = new AttributeModifier(ResourceLocation.fromNamespaceAndPath(AeonsPast.MODID,"base_stats"),getValue(entity ,status_chance), AttributeModifier.Operation.ADD_VALUE);

stack.set(DataComponents.ATTRIBUTE_MODIFIERS,ItemAttributeModifiers.EMPTY);




    stack.update(DataComponents.ATTRIBUTE_MODIFIERS,ItemAttributeModifiers.EMPTY,itemAttributeModifiers -> ItemAttributeModifiers.builder()
            .add(AttributeRegistration.WEAPON_POWER, attackmod, EquipmentSlotGroup.MAINHAND)
            .add(Attributes.ATTACK_SPEED, attkspdmod, EquipmentSlotGroup.MAINHAND)
            .add(AttributeRegistration.CRITICAL_CHANCE,critchance,EquipmentSlotGroup.MAINHAND)
            .add(AttributeRegistration.CRITICAL_DAMAGE,critdmg,EquipmentSlotGroup.MAINHAND)
            .add(AttributeRegistration.STATUS_CHANCE,statuschance,EquipmentSlotGroup.MAINHAND)



            .build());




}


public double getValue(@Nullable LivingEntity entity, double value) {


    if (entity==null) {

        return value;
    }

    else {
       double luck = entity.getAttributeValue(Attributes.LUCK);

       return value * rollLuck(luck);
    }






}

private float rollLuck(double luck) {

    float result = 0;

    Random random = new Random();
    int lowerBound = 1;
    int upperBound = 101;

    int randomNumber =  random.nextInt(lowerBound, upperBound);

    if (randomNumber <=1) {

        result = 2.0f;

    }
    else if (randomNumber<=5) {

        if (random.nextBoolean()) {

            result = 1.8f;

        }

    }
    else if (randomNumber<=8) {

        result = 1.4f;

    }
    else if (randomNumber<=10) {

        result = 1.2f;
    }
     else if (randomNumber <= 25) {
         result = 0.5f;
    }
     else {
         result = 1.0f;
    }

     if (luck <=0) {
         return result;
     }
     else {

         float newRound = rollLuck( Math.round((luck/2)-1));
         return Math.max(newRound, result);
     }







}
}