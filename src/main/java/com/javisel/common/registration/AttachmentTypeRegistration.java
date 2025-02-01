package com.javisel.common.registration;

import com.javisel.AeonsPast;
import com.javisel.common.attatchments.EntityData;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class AttachmentTypeRegistration {
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, AeonsPast.MODID);

    public static final DeferredHolder<AttachmentType<?>,AttachmentType<EntityData>> ENTITY_DATA = ATTACHMENT_TYPES.register("entity_data", () -> AttachmentType.builder(EntityData::new).serialize(EntityData.CODEC).build());
}
