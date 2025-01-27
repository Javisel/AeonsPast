package com.javisel.common.particles;

import com.javisel.common.combat.DamageTypes;
import com.javisel.common.registration.ParticleTypeRegistration;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.ChatFormatting;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.TrailParticleOption;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentContents;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.contents.PlainTextContents;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.phys.Vec3;

public record WorldTextParticleOptions(String text, int colour) implements ParticleOptions {

    public WorldTextParticleOptions(Component component, int colour, HolderLookup.Provider registries) {
        this(Component.Serializer.toJson(component,registries),colour);
    }


    public static final MapCodec<WorldTextParticleOptions> CODEC = RecordCodecBuilder.mapCodec(
            p_382882_ -> p_382882_.group(
                            ExtraCodecs.ESCAPED_STRING.fieldOf("text").forGetter(WorldTextParticleOptions::text),
                            ExtraCodecs.RGB_COLOR_CODEC.fieldOf("colour").forGetter(WorldTextParticleOptions::colour)



                    )
                    .apply(p_382882_, WorldTextParticleOptions::new)
    );
    // Read and write information to the network buffer.
    public static final StreamCodec<RegistryFriendlyByteBuf, WorldTextParticleOptions> STREAM_CODEC = StreamCodec.composite(

            ByteBufCodecs.STRING_UTF8,
            WorldTextParticleOptions::text,
            ByteBufCodecs.VAR_INT,
            WorldTextParticleOptions::colour,
            WorldTextParticleOptions::new
    );

    @Override
    public ParticleType<?> getType() {
        return ParticleTypeRegistration.WORLD_TEXT_PARTICLE.get();
    }



    public static WorldTextParticleOptions getWorldNumberOptionByDamage(DamageTypes type, float amount, int criticals) {


        String text = String.format("%.2f", amount);


        WorldTextParticleOptions worldNumberOptions = new WorldTextParticleOptions(text, type.getColor());





            text +="!";




        return worldNumberOptions;

    }

}
