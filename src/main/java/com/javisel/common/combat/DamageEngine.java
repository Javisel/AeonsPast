package com.javisel.common.combat;

import com.javisel.AeonsPast;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;

import java.util.HashMap;

public class DamageEngine {

    /*
    public static final ResourceKey<DamageType> heat =
            ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.fromNamespaceAndPath(AeonsPast.MODID, "heat"));
    public static final ResourceKey<DamageType> infire_heat =
            ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.fromNamespaceAndPath(AeonsPast.MODID, "infire_heat"));


     */

    public static final HashMap<String, ResourceKey<DamageType>> DAMAGE_MAP = new HashMap<String, ResourceKey<DamageType>>();









}
