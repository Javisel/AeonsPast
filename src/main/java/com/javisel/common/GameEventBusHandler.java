package com.javisel.common;

import com.javisel.common.registration.DamageTypeRegistration;
import net.minecraft.client.renderer.item.properties.numeric.Damage;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

@EventBusSubscriber
public class GameEventBusHandler {

    @SubscribeEvent
    public static void newDamageTypes(LivingIncomingDamageEvent event) {

        String name = event.getSource().typeHolder().getRegisteredName();
        LivingEntity entity = event.getEntity();
        System.out.println("Type Name: " + name);
        DamageSource source = event.getSource();
        System.out.println("Registry Name: " + DamageTypes.IN_FIRE.location());

        if (DamageTypeRegistration.VANILLA_REPLACEMENTS.containsKey(name)) {

            event.setCanceled(true);


        if (source.getEntity() != null) {




        }
        else {


            if (entity.level() instanceof ServerLevel) {
                entity.hurtOrSimulate(new DamageSource(DamageTypeRegistration.INFIRE_HEAT.getDelegate()),1);

            }
        }

        }


    }









}
