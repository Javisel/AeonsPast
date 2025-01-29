package com.javisel.common;

import com.javisel.common.combat.DamageTypeDataLoader;
import com.javisel.common.combat.DamageTypes;
import com.javisel.common.entity.EntityDataLoader;
import com.javisel.common.item.WeaponData;
import com.javisel.common.item.WeaponDataLoader;
import com.javisel.common.particles.WorldTextParticleOptions;
import com.javisel.common.registration.AttributeRegistration;
import com.javisel.common.registration.DataComponentsRegistration;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.ItemAttributeModifierEvent;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.ProjectileImpactEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingEquipmentChangeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;


@EventBusSubscriber
public class GameEventBusHandler {

     @SubscribeEvent
    public static void newAttributeToDamage(LivingDamageEvent.Pre event) {

        float amount = event.getOriginalDamage();

        float boost = 0;

        if (event.getSource().getDirectEntity() instanceof  LivingEntity living) {

            boost = (float) living.getAttributeValue(AttributeRegistration.MELEE_POWER.getDelegate());

        }
        else {

            Entity attacker =  event.getSource().getEntity();

           if (attacker instanceof LivingEntity living) {

               boost = (float) living.getAttributeValue(AttributeRegistration.RANGED_POWER.getDelegate());




           }
        }

        event.setNewDamage(amount+boost);


    }


    @SubscribeEvent
    public static void damageNumbers(LivingDamageEvent.Post event) {


        if (event.getSource().getEntity() instanceof ServerPlayer serverPlayer) {

            LivingEntity entity = event.getEntity();


            DamageSource source = event.getSource();
            ItemStack weapon = event.getSource().getWeaponItem();
            Component component = Component.empty();
            MutableComponent mutableComponent = component.copy();
            mutableComponent.append(String.format("%.2f", event.getNewDamage()));

            DamageTypes type = DamageTypeDataLoader.getByVanillaType(source);

            if (weapon == ItemStack.EMPTY || source.typeHolder().getRegisteredName().equals("minecraft:player_attack")) {
                type= DamageTypes.IMPACT;
            }


            int color=0;
            if (type == null) {
                System.out.println("Type is Null: " + source.typeHolder().getRegisteredName());


                if (weapon != null) {

                    WeaponData weaponData = weapon.get(DataComponentsRegistration.WEAPON_DATA);

                    if (weaponData !=null) {

                        type = DamageTypes.getByString(weaponData.damage_type());
                        color = type.getColor();
                    }

                }

            }
            else {

                color = type.getColor();
            }
            mutableComponent = mutableComponent.withColor(color);


            ServerLevel level = (ServerLevel) entity.level();
            WorldTextParticleOptions worldTextParticleOptions = new WorldTextParticleOptions(mutableComponent, 0, level.registryAccess());
            level.sendParticles(serverPlayer, worldTextParticleOptions, true, true, entity.getX(), entity.getEyeY(), entity.getZ(), 1, 0, 0, 0, 0);


        }


    }

    @SubscribeEvent
    public static void itemCrafted(PlayerEvent.ItemCraftedEvent event) {
        ResourceLocation location = event.getCrafting().getItemHolder().getKey().location();


        if (WeaponDataLoader.Item_STATISTICAL_DATA.get(location) != null) {


            ItemStack stack = event.getCrafting();

            if (stack.get(DataComponentsRegistration.WEAPON_DATA) == null) {
                WeaponDataLoader.Item_STATISTICAL_DATA.get(location).loadToItem(event.getEntity(), stack);


            }


        }
    }

    @SubscribeEvent
    public static void attributechange(ItemAttributeModifierEvent event) {


        ResourceLocation location = event.getItemStack().getItemHolder().getKey().location();


        if (WeaponDataLoader.Item_STATISTICAL_DATA.get(location) != null) {


            ItemStack stack = event.getItemStack();

            if (stack.get(DataComponentsRegistration.WEAPON_DATA) == null) {
                WeaponDataLoader.Item_STATISTICAL_DATA.get(location).loadToItem(null, stack);


            }


        }
    }


    @SubscribeEvent
    public static void updateEntityStats(EntityJoinLevelEvent event) {


        int id = BuiltInRegistries.ENTITY_TYPE.getId(event.getEntity().getType());
        ResourceLocation location = BuiltInRegistries.ENTITY_TYPE.get(id).get().getKey().location();


        if (EntityDataLoader.ENTITY_STATISTICAL_DATA.get(location) != null) {


            if (event.getEntity() instanceof LivingEntity living) {

                if (living.getAttributeValue(AttributeRegistration.NPC_LEVEL) == 0) {
                    EntityDataLoader.ENTITY_STATISTICAL_DATA.get(location).loadtoEntity(living, 1);


                }


            }
        }


    }

    @SubscribeEvent
    public static void attributeUpdate(LivingEquipmentChangeEvent event) {




    }




}










