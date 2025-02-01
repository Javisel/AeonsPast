package com.javisel.common.combat;

import com.javisel.common.registration.DamageTypeRegistration;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.neoforge.registries.DeferredHolder;

public enum ComplexDamageTypes {

  VOID(DamageTypeRegistration.VOID, 0, null, 0x0000000),
  ENDER(DamageTypeRegistration.ENDER, 1, null, 0xB200FF),
  ARCANE(DamageTypeRegistration.ARCANE, 2, null, 0x4169e1),
  IMPACT(DamageTypeRegistration.IMPACT, 3,null, 0xFFA500),
  PUNCTURE(DamageTypeRegistration.PUNCTURE, 4, null, 0xFFA500),
  SLASH(DamageTypeRegistration.SLASH, 5, null, 0x0FFA500),
  HEAT(DamageTypeRegistration.HEAT, 6,null, 0xF73718),
  BURN(DamageTypeRegistration.BURN, 6, null, 0xF73718),
  COLD(DamageTypeRegistration.COLD, 7, null, 0xd6ecef),
  ELECTRIC(DamageTypeRegistration.ELECTRIC, 8, null, 0xffff33),
  RADIANT(DamageTypeRegistration.RADIANT, 9, null, 0xFFD700),
  POISON(DamageTypeRegistration.POISON, 10, null, 0x80b692),
  WITHER(DamageTypeRegistration.WITHER, 11, null, 0x301934),
  KINETIC(DamageTypeRegistration.KINETIC, 12, null, 0xFFFFFF),
  BLEED(DamageTypeRegistration.BLEED, 13, null, 0x660000),
  BLAST(DamageTypeRegistration.BLAST, 13, null, 0x660000),

  TRUE(DamageTypeRegistration.TRUE, 14, null, 0xFFFFFF);

  private final ResourceKey<DamageType> type;
  private final int id;
  private final DeferredHolder<MobEffect, MobEffect> statusEffect;

  private final int color;

  ComplexDamageTypes(ResourceKey<DamageType> type, int id, DeferredHolder<MobEffect, MobEffect>  statusEffect, int color) {
    this.type = type;
    this.id = id;
    this.statusEffect = statusEffect;

    this.color = color;
  }



  public ResourceKey<DamageType> getTypeKey() {
    return type;
  }

  public Holder<DamageType> getTypeHolder(RegistryAccess registryAccess) {
    return registryAccess.lookupOrThrow(Registries.DAMAGE_TYPE).get(type).orElseThrow();
  }

  public int getId() {
    return id;
  }

  public int getColor() {
    return color;
  }


  public MobEffect getStatusEffect() {

    if (statusEffect == null ) {
      return  null;
    }
    return statusEffect.get();
  }

  public static ComplexDamageTypes getByType(ResourceKey<DamageType> key) {

    for (ComplexDamageTypes type : ComplexDamageTypes.values()) {
    if (type.type.equals(key)) {
      return type;
      }
    }
    return null;
  }



}
