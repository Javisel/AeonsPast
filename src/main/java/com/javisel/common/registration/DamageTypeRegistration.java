package com.javisel.common.registration;

import com.javisel.AeonsPast;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageScaling;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class DamageTypeRegistration {

    public static final DeferredRegister<DamageType> DAMAGE_TYPES = DeferredRegister.create(Registries.DAMAGE_TYPE, AeonsPast.MODID);

    public static final DeferredHolder<DamageType, DamageType> INFIRE_HEAT = DAMAGE_TYPES.register("infire_heat", () -> new DamageType("infire_heat", DamageScaling.WHEN_CAUSED_BY_LIVING_NON_PLAYER,0.01f));




    public static final HashMap<String, DeferredHolder<DamageType, DamageType>> VANILLA_REPLACEMENTS = new HashMap<>();

    static {

    VANILLA_REPLACEMENTS.put(DamageTypes.IN_FIRE.location().toString(), INFIRE_HEAT );




    }






}
