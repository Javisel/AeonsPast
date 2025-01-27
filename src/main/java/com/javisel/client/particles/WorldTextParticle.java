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

    Component component;

    public WorldTextParticle(Component component, ClientLevel level, double xo, double yo, double zo) {
        super(level, xo, yo, zo);
        this.component = component;
        this.hasPhysics = false;
        this.gravity = 0;
    }

    public WorldTextParticle(Component component, ClientLevel level, double xo, double yo, double zo, double xd, double yd, double zd) {
        super(level, xo, yo, zo, xd, yd, zd);
        this.component = component;
        this.hasPhysics = false;
        this.gravity = 0;


    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.CUSTOM;
    }


    @Override
    public void render(VertexConsumer vertexConsumer, Camera camera, float ticks) {

        Minecraft minecraft = Minecraft.getInstance();
        PoseStack stack = new PoseStack();
        stack.pushPose();

        Vec3 cameraPosition = camera.getPosition();
        float x = (float) (Mth.lerp((double) ticks, this.xo, this.x) - cameraPosition.x());
        float y = (float) (Mth.lerp((double) ticks, this.yo, this.y) - cameraPosition.y());
        float z = (float) (Mth.lerp((double) ticks, this.zo, this.z) - cameraPosition.z());

        System.out.println("XYZ: " + x  +" " + y + " " + z);

        Font font = Minecraft.getInstance().font;


        float scale = 0.025f;



        RenderSystem.disableBlend();
        RenderSystem.disableDepthTest();
        RenderSystem.disableColorLogicOp();
        RenderSystem.disablePolygonOffset();

        stack.translate(x, y, z);

        stack.mulPose(camera.rotation());


        System.out.println("Render particles!");

        stack.scale(-scale, -scale, scale);

        float f = (float)(-font.width(component)) / 2.0F;
     /*
        font.drawInBatch(
                component, f, (float)0, -2130706433, false, stack.last().pose(), null, Font.DisplayMode.NORMAL, 0, 0
        );


      */

        System.out.println("Text: " + component.getContents());
        stack.popPose();

    }


    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<WorldTextParticleOptions> {

        public Provider() {

        }

        @Override
        public Particle createParticle(WorldTextParticleOptions worldTextOptions, ClientLevel level, double xo, double yo, double zo, double xd, double yd, double zd) {


            return new WorldTextParticle(worldTextOptions.getComponent(), level, xo, yo, zo, xd, yd, zd);
        }
    }


}