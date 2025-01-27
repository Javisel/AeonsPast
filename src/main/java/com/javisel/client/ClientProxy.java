package com.javisel.client;

import com.javisel.client.particles.DamageTypeParticle;
import com.javisel.client.particles.WorldTextParticle;
import com.javisel.common.registration.ParticleTypeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleEngine;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;

public class ClientProxy {







    @OnlyIn(Dist.CLIENT)
    @EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class modBusEvents {


        @SubscribeEvent
        public static void particleProviderRegistration(RegisterParticleProvidersEvent event) {


            Minecraft minecraft = Minecraft.getInstance();
            ParticleEngine particleEngine = minecraft.particleEngine;

            particleEngine.register(ParticleTypeRegistration.WORLD_TEXT_PARTICLE.get(), new WorldTextParticle.Provider());
            event.registerSpriteSet(ParticleTypeRegistration.DAMAGE_TYPE_PARTICLE.get(), DamageTypeParticle.DamageTypeParticleProvider::new);

        }


    }
}
