package com.javisel.common.registration;

import com.javisel.AeonsPast;
import com.mojang.serialization.Codec;
import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;


public class DataAttachmentRegistration {

    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, AeonsPast.MODID);



    public static final Supplier<AttachmentType<String>> VULNERABILITIES = ATTACHMENT_TYPES.register(
            "vulnerabilities", () -> AttachmentType.builder(() -> "").serialize(Codec.STRING).build()
    );
    public static final Supplier<AttachmentType<String>> RESISTANCES = ATTACHMENT_TYPES.register(
            "resistances", () -> AttachmentType.builder(() -> "").serialize(Codec.STRING).build()
    );
    public static final Supplier<AttachmentType<String>> IMMUNITIES = ATTACHMENT_TYPES.register(
            "immunities", () -> AttachmentType.builder(() -> "").serialize(Codec.STRING).build()
    );

}
