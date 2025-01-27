package com.javisel.client.particles;

import com.javisel.common.particles.WorldTextParticleOptions;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WorldTextParticle extends Particle {

    protected float quadSize = 0.1F * (this.random.nextFloat() * 0.5F + 0.5F) * 2.0F;

    String text;
    int color;

    public WorldTextParticle(String text, int color, ClientLevel level, double xo, double yo, double zo) {
        super(level, xo, yo, zo);
        this.text = text;
        this.color = color;
        this.hasPhysics = false;
        this.gravity = 0;
    }

    public WorldTextParticle(String text, int color, ClientLevel level, double xo, double yo, double zo, double xd, double yd, double zd) {
        super(level, xo, yo, zo, xd, yd, zd);
        this.text = text;
        this.color = color;
        System.out.println("New particle made!");


    }

    @Override
    public void render(VertexConsumer vertexConsumer, Camera camera, float v) {

        System.out.println("Render!");


    }

    @Override
    public ParticleRenderType getRenderType() {
    return ParticleRenderType.CUSTOM;
    }



    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<WorldTextParticleOptions> {

        public Provider() {

        }

        @Override
        public Particle createParticle(WorldTextParticleOptions worldTextOptions, ClientLevel level, double xo, double yo, double zo, double xd, double yd, double zd) {


            return new WorldTextParticle(worldTextOptions.text(), worldTextOptions.colour(), level, xo, yo, zo, xd, yd, zd);
        }
    }


}