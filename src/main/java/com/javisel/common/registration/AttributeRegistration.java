package com.javisel.common.registration;

import com.javisel.AeonsPast;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class AttributeRegistration {

    public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(Registries.ATTRIBUTE, AeonsPast.MODID);


    //Six Primary Attributes
    public static final DeferredHolder<Attribute, Attribute> STRENGTH = ATTRIBUTES.register("strength", () -> new RangedAttribute("generic.strength", 0, 0, Double.MAX_VALUE).setSyncable(true));
    public static final DeferredHolder<Attribute, Attribute> DEXTERITY = ATTRIBUTES.register("dexterity", () -> new RangedAttribute("generic.dexterity", 0, 0, Double.MAX_VALUE).setSyncable(true));
    public static final DeferredHolder<Attribute, Attribute> CONSTITUTION = ATTRIBUTES.register("constitution", () -> new RangedAttribute("generic.constitution", 0, 0, Double.MAX_VALUE).setSyncable(true));
    public static final DeferredHolder<Attribute, Attribute> INTELLIGENCE = ATTRIBUTES.register("intelligence", () -> new RangedAttribute("generic.INTELLIGENCE", 0, 0, Double.MAX_VALUE).setSyncable(true));
    public static final DeferredHolder<Attribute, Attribute> WISDOM = ATTRIBUTES.register("wisdom", () -> new RangedAttribute("generic.wisdom", 0, 0, Double.MAX_VALUE).setSyncable(true));
    public static final DeferredHolder<Attribute, Attribute> CHARISMA = ATTRIBUTES.register("charisma", () -> new RangedAttribute("generic.charisma", 0, 0, Double.MAX_VALUE).setSyncable(true));

    //Secondary Attributes
    public static final DeferredHolder<Attribute, Attribute> WEAPON_POWER = ATTRIBUTES.register("weapon_power", () -> new RangedAttribute("generic.weapon_power", 0, 0, Double.MAX_VALUE).setSyncable(true));
    public static final DeferredHolder<Attribute, Attribute> MELEE_POWER = ATTRIBUTES.register("melee_power", () -> new RangedAttribute("generic.melee_power", 0, 0, Double.MAX_VALUE).setSyncable(true));
    public static final DeferredHolder<Attribute, Attribute> RANGED_POWER = ATTRIBUTES.register("ranged_power", () -> new RangedAttribute("generic.ranged_power", 0, 0, Double.MAX_VALUE).setSyncable(true));





    public static final DeferredHolder<Attribute, Attribute> HEALTH_REGENERATION = ATTRIBUTES.register("health_regeneration", () -> new RangedAttribute("generic.health_regeneration", 0, 0, Double.MAX_VALUE).setSyncable(true));
    public static final DeferredHolder<Attribute, Attribute> CRITICAL_CHANCE = ATTRIBUTES.register("critical_chance", () -> new RangedAttribute("generic.critical_chance", 0, 0, Double.MAX_VALUE).setSyncable(true));
    public static final DeferredHolder<Attribute, Attribute> CRITICAL_DAMAGE = ATTRIBUTES.register("critical_damage", () -> new RangedAttribute("generic.critical_damage", 0, 0, Double.MAX_VALUE).setSyncable(true));
    public static final DeferredHolder<Attribute, Attribute> STATUS_CHANCE = ATTRIBUTES.register("status_chance", () -> new RangedAttribute("generic.status_chance", 0, 0, Double.MAX_VALUE).setSyncable(true));


    //Intake Mods
    public static final DeferredHolder<Attribute, Attribute> CRITICAL_CHANCE_INTAKE = ATTRIBUTES.register("critical_chance_intake", () -> new RangedAttribute("generic.critical_chance_intake", 0, -100, Double.MAX_VALUE).setSyncable(true));
    public static final DeferredHolder<Attribute, Attribute> STATUS_CHANCE_INTAKE = ATTRIBUTES.register("status_chance_intake", () -> new RangedAttribute("generic.status_chance_intake", 0, -100, Double.MAX_VALUE).setSyncable(true));

    public static final DeferredHolder<Attribute, Attribute> SLASH_DAMAGE_INTAKE = ATTRIBUTES.register("slash_damage_intake", () -> new RangedAttribute("generic.slash_damage_intake", 1, 0, Double.MAX_VALUE).setSyncable(true));
    public static final DeferredHolder<Attribute, Attribute> BLEED_CHANCE_INTAKE = ATTRIBUTES.register("bleed_chance_intake", () -> new RangedAttribute("generic.status_chance_intake", 0, -100, Double.MAX_VALUE).setSyncable(true));

    public static final DeferredHolder<Attribute, Attribute> PUNCTURE_DAMAGE_INTAKE = ATTRIBUTES.register("puncture_damage_intake", () -> new RangedAttribute("generic.slash_damage_intake", 1, 0, Double.MAX_VALUE).setSyncable(true));
    public static final DeferredHolder<Attribute, Attribute> PERFORATE_CHANCE_INTAKE = ATTRIBUTES.register("perforate_chance_intake", () -> new RangedAttribute("generic.status_chance_intake", 0, -100, Double.MAX_VALUE).setSyncable(true));

    public static final DeferredHolder<Attribute, Attribute> IMPACT_DAMAGE_INTAKE = ATTRIBUTES.register("impact_damage_intake", () -> new RangedAttribute("generic.slash_damage_intake", 1, 0, Double.MAX_VALUE).setSyncable(true));
    public static final DeferredHolder<Attribute, Attribute> STAGGER_CHANCE_INTAKE = ATTRIBUTES.register("stagger_chance_intake", () -> new RangedAttribute("generic.status_chance_intake", 0, -100, Double.MAX_VALUE).setSyncable(true));

    public static final DeferredHolder<Attribute, Attribute> COLD_DAMAGE_INTAKE = ATTRIBUTES.register("cold_damage_intake", () -> new RangedAttribute("generic.slash_damage_intake", 1, 0, Double.MAX_VALUE).setSyncable(true));
    public static final DeferredHolder<Attribute, Attribute> FREEZE_CHANCE_INTAKE = ATTRIBUTES.register("freeze_chance_intake", () -> new RangedAttribute("generic.status_chance_intake", 0, -100, Double.MAX_VALUE).setSyncable(true));











    //NPC Attributes. Unused on player
    public static final DeferredHolder<Attribute, Attribute> NPC_EXPERIENCE = ATTRIBUTES.register("npc_experience", () -> new RangedAttribute("generic.npc_experience", 0, 0, Double.MAX_VALUE).setSyncable(true));
    public static final DeferredHolder<Attribute, Attribute> NPC_LEVEL = ATTRIBUTES.register("npc_level", () -> new RangedAttribute("generic.npc_level", 0, 0, Double.MAX_VALUE).setSyncable(true));



}
