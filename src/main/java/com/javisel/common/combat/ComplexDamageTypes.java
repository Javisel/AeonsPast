package com.javisel.common.combat;

import com.javisel.common.registration.AttributeRegistration;
import com.javisel.common.registration.DamageTypeRegistration;
import com.javisel.common.registration.MobEffectRegistration;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.neoforged.neoforge.registries.DeferredHolder;

public enum ComplexDamageTypes {

  VOID(DamageTypeRegistration.VOID, 0, null, null, null, 0x0000000),
  ENDER(DamageTypeRegistration.ENDER, 1, null, null, null, 0xB200FF),
  ARCANE(DamageTypeRegistration.ARCANE, 2, null, null, null, 0x4169e1),
IMPACT(DamageTypeRegistration.IMPACT, 3,MobEffectRegistration.STAGGER, AttributeRegistration.STAGGER_CHANCE_INTAKE, AttributeRegistration.IMPACT_DAMAGE_INTAKE, 0xFFA500),
  PUNCTURE(DamageTypeRegistration.PUNCTURE, 4,  MobEffectRegistration.PERFORATE, AttributeRegistration.PERFORATE_CHANCE_INTAKE, AttributeRegistration.PUNCTURE_DAMAGE_INTAKE, 0xFFA500),
  SLASH(DamageTypeRegistration.SLASH, 5, MobEffectRegistration.BLEED, AttributeRegistration.BLEED_CHANCE_INTAKE, AttributeRegistration.SLASH_DAMAGE_INTAKE, 0x0FFA500),
  HEAT(DamageTypeRegistration.HEAT, 6,null, null, null, 0xF73718),
  BURN(DamageTypeRegistration.BURN, 6, null, null, null, 0xF73718),
  COLD(DamageTypeRegistration.COLD, 7, null, null, null, 0xd6ecef),
  ELECTRIC(DamageTypeRegistration.ELECTRIC, 8, null, null, null, 0xffff33),
  RADIANT(DamageTypeRegistration.RADIANT, 9, null, null, null, 0xFFD700),
  POISON(DamageTypeRegistration.POISON, 10, null, null, null, 0x80b692),
  WITHER(DamageTypeRegistration.WITHER, 11, null, null, null, 0x301934),
  KINETIC(DamageTypeRegistration.KINETIC, 12, null, null, null, 0xFFFFFF),
  BLEED(DamageTypeRegistration.BLEED, 13, null, null, null, 0x880808),
  BLAST(DamageTypeRegistration.BLAST, 14, null, null, null, 0x804600),
  TRUE(DamageTypeRegistration.TRUE, 15, null, null, null, 0xFFFFFF);

  private final ResourceKey<DamageType> type;
  private final int id;
  private final DeferredHolder<MobEffect, MobEffect> statusEffect;
  private final DeferredHolder<Attribute, Attribute> status_intake;
  private final DeferredHolder<Attribute, Attribute> damage_intake;
  private final int color;

  ComplexDamageTypes(ResourceKey<DamageType> type, int id, DeferredHolder<MobEffect, MobEffect>  statusEffect, DeferredHolder<Attribute, Attribute> statusIntake, DeferredHolder<Attribute, Attribute> damageIntake, int color) {
    this.type = type;
    this.id = id;
    this.statusEffect = statusEffect;
      status_intake = statusIntake;
      damage_intake = damageIntake;

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

  public static ComplexDamageTypes getbyKey(ResourceKey<DamageType> key) {

    for (ComplexDamageTypes type : ComplexDamageTypes.values()) {
    if (type.type.equals(key)) {
      return type;
      }
    }
    return null;
  }
public static ComplexDamageTypes getByString(String key) {

  for (ComplexDamageTypes type : ComplexDamageTypes.values()) {
    if (type.type.location().toString().equals(key)) {
      return type;
    }
  }
  return null;


}

  public ResourceKey<DamageType> getType() {
    return type;
  }

  public DeferredHolder<Attribute, Attribute> getStatus_intake() {
    return status_intake;
  }

  public DeferredHolder<Attribute, Attribute> getDamage_intake() {
    return damage_intake;
  }
}
