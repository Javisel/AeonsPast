package com.javisel.common.particles;

import com.javisel.common.combat.DamageTypes;
import com.javisel.common.registration.ParticleTypeRegistration;
import com.mojang.serialization.MapCodec;
import io.netty.buffer.ByteBuf;
import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentContents;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.contents.PlainTextContents;
import net.minecraft.network.codec.StreamCodec;

public class WorldTextParticleOptions implements ParticleOptions {

    private Component component;

    public static final MapCodec<WorldTextParticleOptions> CODEC = MapCodec.unit(new WorldTextParticleOptions());

    // Read and write information to the network buffer.
    public static final StreamCodec<ByteBuf, WorldTextParticleOptions> STREAM_CODEC = StreamCodec.unit(new WorldTextParticleOptions());

    @Override
    public ParticleType<?> getType() {
        return ParticleTypeRegistration.WORLD_TEXT_TYPE.get();
    }

    public Component getComponent() {
        return component;
    }

    public static WorldTextParticleOptions getWorldNumberOptionByDamage(DamageTypes type, float amount, int criticals) {


        WorldTextParticleOptions worldNumberOptions = new WorldTextParticleOptions();



        worldNumberOptions.component = MutableComponent.create(PlainTextContents.EMPTY);

        worldNumberOptions.component.copy().append(Float.toString(amount));
        worldNumberOptions.component = worldNumberOptions.component.copy().withStyle(Style.EMPTY.withColor(type.getColor()));


        for (int i = 0; i < criticals;i++ ) {
            worldNumberOptions.component = worldNumberOptions.component.copy().append("!");


        }

        if (criticals>0) {
            worldNumberOptions.component = worldNumberOptions.component.copy().withStyle(ChatFormatting.BOLD);

        }

        return worldNumberOptions;

    }

}
