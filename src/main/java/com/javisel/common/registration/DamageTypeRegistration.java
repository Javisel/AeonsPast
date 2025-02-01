package com.javisel.common.registration;

import com.javisel.AeonsPast;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageType;

public class DamageTypeRegistration {

    public static final ResourceKey<DamageType> VOID = create("void");
    public static final ResourceKey<DamageType> ENDER = create("ender");
    public static final ResourceKey<DamageType> ARCANE = create("arcane");
    public static final ResourceKey<DamageType> IMPACT = create("impact");
    public static final ResourceKey<DamageType> PUNCTURE = create("puncture");
    public static final ResourceKey<DamageType> SLASH = create("slash");
    public static final ResourceKey<DamageType> HEAT = create("heat");
    public static final ResourceKey<DamageType> BURN = create("burn");
    public static final ResourceKey<DamageType> COLD = create("cold");
    public static final ResourceKey<DamageType> ELECTRIC = create("electric");
    public static final ResourceKey<DamageType> RADIANT = create("radiant");
    public static final ResourceKey<DamageType> POISON = create("poison");
    public static final ResourceKey<DamageType> WITHER = create("wither");
    public static final ResourceKey<DamageType> KINETIC = create("kinetic");
    public static final ResourceKey<DamageType> BLEED = create("bleed");
    public static final ResourceKey<DamageType> BLAST = create("blast");
    public static final ResourceKey<DamageType> TRUE = create("true");

    static ResourceKey<DamageType> create(String s) {
        return ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.fromNamespaceAndPath(AeonsPast.MODID,s));
    }
}
