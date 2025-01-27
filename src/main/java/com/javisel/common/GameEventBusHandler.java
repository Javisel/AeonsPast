package com.javisel.common;

import com.javisel.common.combat.DamageEngine;
import com.javisel.common.combat.DamageTypes;
import com.javisel.common.particles.WorldTextParticleOptions;
import com.javisel.common.registration.ParticleTypeRegistration;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

import java.util.Random;


@EventBusSubscriber
public class GameEventBusHandler {

    @SubscribeEvent
    public static void newDamageTypes(LivingIncomingDamageEvent event) {

        String name = event.getSource().typeHolder().getRegisteredName();
        LivingEntity entity = event.getEntity();

        DamageSource source = event.getSource();
        Entity sourceEntity =  event.getSource().getEntity();
        Entity directEntity = event.getSource().getDirectEntity();


        if (sourceEntity instanceof ServerPlayer serverPlayer) {

         ServerLevel serverLevel = serverPlayer.serverLevel();

         WorldTextParticleOptions textParticleOptions = WorldTextParticleOptions.getWorldNumberOptionByDamage(DamageTypes.ARCANE,15,1);



            double xpos = entity.getX();
            double ypos = entity.getY() + entity.getBbHeight() + 0.1;
            double zpos = entity.getZ();

            double xd = 0;
            double yd = 0;
            double zd = 0;

            serverLevel.sendParticles(serverPlayer, textParticleOptions, true, true, xpos, ypos, zpos, 1, xd, yd, zd, 0d);




        }





    }









}
