package com.javisel.common.registration;

import com.javisel.AeonsPast;
import com.javisel.common.attachments.EntityData;
import com.javisel.common.combat.EntityCombatData;
import com.javisel.common.entity.projectile.ProjectileData;
import com.mojang.serialization.Codec;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredHolder;
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

    public static final Supplier<AttachmentType<ProjectileData>> PROJECTILE_DATA = ATTACHMENT_TYPES.register(
            "projectile_data", () -> AttachmentType.serializable(() -> new ProjectileData(false,0,0,0,0,"")).build()
    );

    public static final Supplier<AttachmentType<EntityCombatData>> ENTITY_COMBAT_DATA = ATTACHMENT_TYPES.register(
            "entity_combat_data", () -> AttachmentType.serializable(() -> new EntityCombatData(0,false)).build()
    );
    public static final DeferredHolder<AttachmentType<?>,AttachmentType<EntityData>> ENTITY_DATA = ATTACHMENT_TYPES.register("entity_data", () -> AttachmentType.builder(EntityData::new).serialize(EntityData.CODEC).build());

}
