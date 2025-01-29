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
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.joml.Matrix4f;

@OnlyIn(Dist.CLIENT)
public class WorldTextParticle extends Particle {

    protected float quadSize = 0.1F * (this.random.nextFloat() * 0.5F + 0.5F) * 2.0F;

    Component text;
    int color;

    public WorldTextParticle(Component text, int color, ClientLevel level, double xo, double yo, double zo) {
        this(text,color,level, xo, yo, zo,0,0,0);
    }

    public WorldTextParticle(Component text, int color, ClientLevel level, double xo, double yo, double zo, double xd, double yd, double zd) {
        super(level, xo, yo, zo, xd, yd, zd);
        this.text = text;
        this.color = color;
        this.hasPhysics = false;
        this.gravity = 0;
        lifetime = 20;

    }

    @Override
    public void renderCustom(PoseStack poseStack, MultiBufferSource bufferSource, Camera camera, float partialTick) {
        Font font = Minecraft.getInstance().font;
        float f = -font.width(text) / 2.0F;

        Vec3 cameraPos = camera.getPosition();
        float particleX = (float) (Mth.lerp(partialTick, this.xo, this.x) - cameraPos.x());
        float particleY = (float) (Mth.lerp(partialTick, this.yo, this.y) - cameraPos.y());
        float particleZ = (float) (Mth.lerp(partialTick, this.zo, this.z) - cameraPos.z());


        poseStack.pushPose();
        poseStack.translate(particleX, particleY, particleZ);
        poseStack.mulPose(camera.rotation());
        poseStack.scale(.025f,-.025f,-.025f);
        
        Matrix4f matrix4f = poseStack.last().pose();
        font.drawInBatch(
                text, f, 0, 0x80ffffff, false, matrix4f, bufferSource, Font.DisplayMode.NORMAL, 0, LightTexture.FULL_BRIGHT
        );
        poseStack.popPose();
    }

    @Override
    public void render(VertexConsumer vertexConsumer, Camera camera, float v) {
        //DOESN'T RUN
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


            return new WorldTextParticle(Component.Serializer.fromJsonLenient(worldTextOptions.text(),level.registryAccess()), worldTextOptions.colour(), level, xo, yo, zo, xd, yd, zd);
        }
    }


}