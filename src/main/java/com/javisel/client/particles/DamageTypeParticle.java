package com.javisel.client.particles;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DamageTypeParticle extends TextureSheetParticle {
    private final SpriteSet spriteSet;

    // First four parameters are self-explanatory. The SpriteSet parameter is provided by the
    // ParticleProvider, see below. You may also add additional parameters as needed, e.g. xSpeed/ySpeed/zSpeed.
    public DamageTypeParticle(ClientLevel level, double x, double y, double z, SpriteSet spriteSet) {
        super(level, x, y, z);
        this.spriteSet = spriteSet;
        this.gravity = 0; // Our particle floats in midair now, because why not.

        // We set the initial sprite here since ticking is not guaranteed to set the sprite
        // before the render method is called.
        this.setSpriteFromAge(spriteSet);
    }

    @Override
    public void tick() {
        // Set the sprite for the current particle age, i.e. advance the animation.
        this.setSpriteFromAge(spriteSet);
        // Let super handle further movement. You may replace this with your own movement if needed.
        // You may also override move() if you only want to modify the built-in movement.
        super.tick();
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }



    public static class DamageTypeParticleProvider implements ParticleProvider<SimpleParticleType> {
        // A set of particle sprites.
        private final SpriteSet spriteSet;

        // The registration function passes a SpriteSet, so we accept that and store it for further use.
        public DamageTypeParticleProvider(SpriteSet spriteSet) {
            this.spriteSet = spriteSet;
        }

        // This is where the magic happens. We return a new particle each time this method is called!
        // The type of the first parameter matches the generic type passed to the super interface.
        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel level,
                                       double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            // We don't use the type and speed, and pass in everything else. You may of course use them if needed.
            return new DamageTypeParticle(level, x, y, z, spriteSet);
        }
    }






}