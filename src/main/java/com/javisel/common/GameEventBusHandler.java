package com.javisel.common;

import com.javisel.common.combat.DamageTypes;
import com.javisel.common.entity.EntityDataLoader;
import com.javisel.common.particles.WorldTextParticleOptions;
import com.javisel.common.registration.AttributeRegistration;
import com.javisel.common.registration.DataAttachmentRegistration;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;


@EventBusSubscriber
public class GameEventBusHandler {




    @SubscribeEvent
    public static void damageNumbers(LivingDamageEvent.Post event) {


        if (event.getSource().getEntity() instanceof ServerPlayer serverPlayer) {

            LivingEntity entity = event.getEntity();



            Component component = Component.empty();
            MutableComponent mutableComponent = component.copy();
            mutableComponent.append(String.format("%.2f",event.getNewDamage()));
            mutableComponent = mutableComponent.withColor(DamageTypes.RADIANT.getColor());



            ServerLevel level = (ServerLevel) entity.level();
            WorldTextParticleOptions worldTextParticleOptions = new WorldTextParticleOptions(mutableComponent,0,level.registryAccess());
            level.sendParticles(serverPlayer,worldTextParticleOptions,true,true,entity.getX(),entity.getEyeY(),entity.getZ(),1,0,0,0,0);




        }



    }

    @SubscribeEvent
    public static void joinWorld(EntityJoinLevelEvent event) {


        int id = BuiltInRegistries.ENTITY_TYPE.getId(event.getEntity().getType());
        ResourceLocation location = BuiltInRegistries.ENTITY_TYPE.get(id).get().getKey().location();


        if (EntityDataLoader.ENTITY_STATISTICAL_DATA.get(location) !=null) {


            if (event.getEntity() instanceof  LivingEntity living) {

                if (living.getAttributeValue(AttributeRegistration.NPC_LEVEL) ==0) {
                    EntityDataLoader.ENTITY_STATISTICAL_DATA.get(location).loadtoEntity(living, 1);




                }



            }
        }






    }








}
