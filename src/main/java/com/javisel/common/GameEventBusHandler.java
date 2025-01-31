package com.javisel.common;

import com.javisel.common.combat.*;
import com.javisel.common.entity.EntityDataLoader;
import com.javisel.common.entity.projectile.ProjectileData;
import com.javisel.common.entity.projectile.ProjectileDataLoader;
import com.javisel.common.entity.projectile.ProjectileStatisticalData;
import com.javisel.common.item.WeaponData;
import com.javisel.common.item.WeaponDataLoader;
import com.javisel.common.item.WeaponStatisticalData;
import com.javisel.common.particles.WorldTextParticleOptions;
import com.javisel.common.registration.AttributeRegistration;
import com.javisel.common.registration.DataAttachmentRegistration;
import com.javisel.common.registration.DataComponentsRegistration;
import com.javisel.utilities.StringKeys;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.ItemAttributeModifierEvent;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingEquipmentChangeEvent;

import java.util.Random;


@EventBusSubscriber
public class GameEventBusHandler {
    @SubscribeEvent
    public static void newAttributeToDamage(LivingDamageEvent.Pre event) {

        float amount = event.getOriginalDamage();

        DamageSource source = event.getSource();
        LivingEntity victim = event.getEntity();

        float base;
        double multiplier = 0;
        if (source.getDirectEntity() instanceof LivingEntity living) {


            ItemStack weapon = source.getWeaponItem();
            base = (float) living.getAttributeValue(AttributeRegistration.WEAPON_POWER.getDelegate());

            double chance = living.getAttributeValue(AttributeRegistration.CRITICAL_CHANCE);

            int crits = CombatEngine.criticals((int) chance);

            if (crits > 0) {

                multiplier = (crits * (living.getAttributeValue(AttributeRegistration.CRITICAL_DAMAGE) - 1));

            }




            if (WeaponDataLoader.getByItemStack(weapon) != null) {

                WeaponStatisticalData weaponStatisticalData = WeaponDataLoader.getByItemStack(weapon);


                if (weaponStatisticalData.attack_type().equals(StringKeys.RANGED)) {
                    multiplier *= 0.1;

                }

            }

            float total = base;
            total += (float) (base * multiplier);

            event.setNewDamage(total);

            double schance =  living.getAttributeValue(AttributeRegistration.STATUS_CHANCE);
            boolean status = false;
            Random random = new Random();
            if (random.nextInt(101)<=schance) {

                //DO STATUS
                status = true;


            }
            victim.setData(DataAttachmentRegistration.ENTITY_COMBAT_DATA, new EntityCombatData(crits,status));

        } else if (source.getDirectEntity() instanceof Projectile projectile) {

            ProjectileData data = projectile.getData(DataAttachmentRegistration.PROJECTILE_DATA);


            double damage = projectile.getData(DataAttachmentRegistration.PROJECTILE_DATA).damage();

            double chance = data.critical_chance();

            int crits = CombatEngine.criticals((int) chance);
            double schance = data.status_chance();
            boolean status = false;
            Random random = new Random();
            if (random.nextInt(101)<=schance) {

                //DO STATUS
                status = true;


            }
            multiplier = crits > 0 ? (crits * data.critical_damage()) : 1;

            victim.setData(DataAttachmentRegistration.ENTITY_COMBAT_DATA, new EntityCombatData(crits,status));

            base = (float) damage;

            System.out.println("Base: " + base);

            event.setNewDamage((float) ((amount + base) * multiplier));
        }


    }


    @SubscribeEvent
    public static void damageNumbers(LivingDamageEvent.Post event) {


        DamageSource source = event.getSource();
        if (source.getEntity() instanceof ServerPlayer serverPlayer) {

            LivingEntity victim = event.getEntity();
            Component component = Component.empty();
            MutableComponent mutableComponent = component.copy();
            mutableComponent.append(String.format("%.2f", event.getNewDamage()));
            ServerLevel level = (ServerLevel) victim.level();
            DamageTypes type = DamageTypeDataLoader.getByVanillaType(source);
            EntityCombatData combatData= victim.getData(DataAttachmentRegistration.ENTITY_COMBAT_DATA);
            int color;
            if (source.getDirectEntity() == serverPlayer) {

                ItemStack weapon = source.getWeaponItem();
                if (weapon == ItemStack.EMPTY || source.typeHolder().getRegisteredName().equals("minecraft:player_attack")) {
                    type = DamageTypes.IMPACT;
                } else {

                    WeaponData weaponData = weapon.get(DataComponentsRegistration.WEAPON_DATA);

                    if (weaponData != null) {

                        type = DamageTypes.getByString(weaponData.damage_type());

                    }


                }
            } else {
                if (source.getDirectEntity() instanceof Projectile projectile) {

                    ProjectileData data = projectile.getData(DataAttachmentRegistration.PROJECTILE_DATA);

                    type = DamageTypes.getByString(data.damage_type());
                }
            }

            color = type == null || event.getNewDamage() == 0 ? 0 : type.getColor();

            int crits = victim.getData(DataAttachmentRegistration.ENTITY_COMBAT_DATA).critsHit();


            if (crits > 0) {
                mutableComponent = mutableComponent.withStyle(ChatFormatting.BOLD);
                for (int i = 0; i < crits; i++) {

                    mutableComponent = mutableComponent.append("!");


                }
                level.playSound(null, victim.getX(), victim.getY(), victim.getZ(), SoundEvents.PLAYER_ATTACK_CRIT, SoundSource.PLAYERS, 1.0F, 1.0F);
            }

            if (combatData.status()) {




            }


            mutableComponent = mutableComponent.withColor(color);


            WorldTextParticleOptions worldTextParticleOptions = new WorldTextParticleOptions(mutableComponent, 0, level.registryAccess());
            level.sendParticles(serverPlayer, worldTextParticleOptions, true, true, victim.getX(), victim.getEyeY(), victim.getZ(), 1, 0, 0, 0, 0);

            //Resets combat data
            victim.setData(DataAttachmentRegistration.ENTITY_COMBAT_DATA, EntityCombatData.DEFAULT);

        }


    }


    @SubscribeEvent
    public static void attributechange(ItemAttributeModifierEvent event) {


        ResourceLocation location = event.getItemStack().getItemHolder().getKey().location();


        if (WeaponDataLoader.WEAPON_STATISTICAL_DATA.get(location) != null) {


            ItemStack stack = event.getItemStack();

            if (stack.get(DataComponentsRegistration.WEAPON_DATA) == null) {
                WeaponDataLoader.WEAPON_STATISTICAL_DATA.get(location).loadToItem(null, stack);


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

    @SubscribeEvent
    public static void rangedProjectileChanges(EntityJoinLevelEvent event) {

        if (event.getLevel() instanceof ServerLevel && event.getEntity() instanceof Projectile projectile) {

            Entity Owner = projectile.getOwner();

            if (Owner instanceof LivingEntity livingOwner) {

                double dmg = livingOwner.getAttributeValue(AttributeRegistration.WEAPON_POWER);

                if (projectile instanceof AbstractArrow arrow) {

                    arrow.setBaseDamage(arrow.getBaseDamage() + Math.max((dmg / 5.5) - arrow.getBaseDamage(), 0));

                    arrow.setCritArrow(false);

                }
                int id = BuiltInRegistries.ENTITY_TYPE.getId(projectile.getType());
                ResourceLocation location = BuiltInRegistries.ENTITY_TYPE.get(id).get().getKey().location();
                ProjectileStatisticalData data = ProjectileDataLoader.PROJECTILE_STATISTICAL_DATA.get(location);

                if (data != null) {
                    projectile.setData(DataAttachmentRegistration.PROJECTILE_DATA, new ProjectileData(data.override(), projectile instanceof AbstractArrow ? 0 : data.damage() + dmg, livingOwner.getAttributeValue(AttributeRegistration.STATUS_CHANCE), livingOwner.getAttributeValue(AttributeRegistration.CRITICAL_CHANCE), livingOwner.getAttributeValue(AttributeRegistration.CRITICAL_DAMAGE), data.damage_type()));


                }


            }


        }


    }


}










