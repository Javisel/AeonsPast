package com.javisel.common.item;

import com.javisel.AeonsPast;
import com.javisel.common.item.weapon.WeaponData;
import com.javisel.common.item.weapon.WeaponDataLoader;
import com.javisel.common.item.weapon.WeaponStatisticalData;
import com.javisel.common.registration.AttributeRegistration;
import com.javisel.common.registration.DataComponentsRegistration;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Random;

public class ItemEngine {

    public static double rollStatTier(@Nullable LivingEntity roller, ItemRarity rarity) {

        RandomSource random = roller.getRandom();

        double rng = new Random().nextDouble(rarity.getMinValue(), rarity.getMaxValue());

        double luck = roller == null ? 0 : roller.getAttributeValue(Attributes.LUCK);

        while (random.nextInt(101) <= luck) {

            double newrng = new Random().nextDouble(rarity.getMinValue(), rarity.getMaxValue());
            rng = Math.max(newrng, rng);

            luck -= 100;

        }

         


        return rng;
    }

    public static void initializeItem(@Nullable LivingEntity owner,ItemStack stack) {

        if (isItemInit(stack)) {
            return;
        }
        if (isWeapon(stack)) {
        
            initweapon(owner,stack);


        }


    }

    public static void initweapon(@Nullable LivingEntity owner, ItemStack stack) {

        WeaponStatisticalData weapondata = WeaponDataLoader.getByItemStack(stack);

        stack.set(DataComponentsRegistration.WEAPON_DATA,new WeaponData(weapondata.item_type(),weapondata.damageType()));

        stack.remove(DataComponents.ATTRIBUTE_MODIFIERS);

        Random random = new Random();

        ItemRarity rarity = ItemRarity.getbyPercentChance(new Random().nextInt(101));
        stack.set(DataComponentsRegistration.ITEM_DATA,new ItemData(weapondata.item_type(), rarity.getUnlocalizedName()));



        int min = Math.max(0,rarity.getId()-1);
        int max = Math.max(6,rarity.getId()+2);      



        double ap = rollStatTier(owner, ItemRarity.getByID(random.nextInt(min,max)));
        double as = rollStatTier(owner, ItemRarity.getByID(random.nextInt(min,max)));
        double cc = rollStatTier(owner, ItemRarity.getByID(random.nextInt(min,max)));
        double cd = rollStatTier(owner, ItemRarity.getByID(random.nextInt(min,max)));
        double sc = rollStatTier(owner, ItemRarity.getByID(random.nextInt(min,max)));

        double dur = rollStatTier(owner, ItemRarity.getByID(random.nextInt(min,max)));
        ArrayList<Integer> tiers = new ArrayList<>();


        AttributeModifier attackmod = new AttributeModifier(Item.BASE_ATTACK_DAMAGE_ID, ap* weapondata.attack_power() , AttributeModifier.Operation.ADD_VALUE);
        AttributeModifier attkspdmod = new AttributeModifier(Item.BASE_ATTACK_SPEED_ID, -4 + (as* weapondata.attack_speed()), AttributeModifier.Operation.ADD_VALUE);
        AttributeModifier critchance = new AttributeModifier(ResourceLocation.fromNamespaceAndPath(AeonsPast.MODID,"base_stats"),cc*weapondata.critical_chance(), AttributeModifier.Operation.ADD_VALUE);
        AttributeModifier critdmg = new AttributeModifier(ResourceLocation.fromNamespaceAndPath(AeonsPast.MODID,"base_stats"), cd*weapondata.critical_damage(), AttributeModifier.Operation.ADD_VALUE);
        AttributeModifier statuschance = new AttributeModifier(ResourceLocation.fromNamespaceAndPath(AeonsPast.MODID,"base_stats"), sc* weapondata.status_chance(), AttributeModifier.Operation.ADD_VALUE);


        ItemAttributeModifiers modifiers = ItemAttributeModifiers.builder().build();
        stack.set(DataComponents.ATTRIBUTE_MODIFIERS,ItemAttributeModifiers.EMPTY);

        stack.set(DataComponents.MAX_DAMAGE,(int) (weapondata.durability() * dur));
        stack.set(DataComponents.DAMAGE,0);

        ItemAttributeModifiers.Builder builder=  ItemAttributeModifiers.builder()
                .add(Attributes.ATTACK_DAMAGE, attackmod, EquipmentSlotGroup.MAINHAND)
                .add(Attributes.ATTACK_SPEED, attkspdmod, EquipmentSlotGroup.MAINHAND)
                .add(AttributeRegistration.CRITICAL_CHANCE,critchance,EquipmentSlotGroup.MAINHAND)
                .add(AttributeRegistration.CRITICAL_DAMAGE,critdmg,EquipmentSlotGroup.MAINHAND)
                .add(AttributeRegistration.STATUS_CHANCE,statuschance,EquipmentSlotGroup.MAINHAND);




        stack.set(DataComponents.ATTRIBUTE_MODIFIERS,builder.build());



    }

    public static boolean isWeapon(ItemStack stack) {



        return stack.is(ItemTags.WEAPONS);
    }

    public static boolean isArmor(ItemStack stack) {


        return stack.is(ItemTags.ARMOR);
    }

    public static boolean isItemInit(ItemStack stack) {


        return  stack.has(DataComponentsRegistration.ITEM_DATA);
    }

    public static boolean isRPGItem(ItemStack stack) {


        return isWeapon(stack) || isArmor(stack);


    }
}
