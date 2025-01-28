package com.javisel.common.registration;

import com.javisel.AeonsPast;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.neoforged.neoforge.registries.RegistryBuilder;

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
    public static final DeferredHolder<Attribute, Attribute> HEALTH_REGENERATION = ATTRIBUTES.register("health_regeneration", () -> new RangedAttribute("generic.health_regeneration", 0, 0, Double.MAX_VALUE).setSyncable(true));
    public static final DeferredHolder<Attribute, Attribute> CRITICAL_CHANCE = ATTRIBUTES.register("critical_chance", () -> new RangedAttribute("generic.critical_chance", 0, 0, Double.MAX_VALUE).setSyncable(true));
    public static final DeferredHolder<Attribute, Attribute> CRITICAL_DAMAGE = ATTRIBUTES.register("critical_damage", () -> new RangedAttribute("generic.critical_damage", 0, 0, Double.MAX_VALUE).setSyncable(true));
    public static final DeferredHolder<Attribute, Attribute> STATUS_CHANCE = ATTRIBUTES.register("status_chance", () -> new RangedAttribute("generic.status_chance", 0, 0, Double.MAX_VALUE).setSyncable(true));



    //NPC Attributes. Unused on player
    public static final DeferredHolder<Attribute, Attribute> NPC_EXPERIENCE = ATTRIBUTES.register("npc_experience", () -> new RangedAttribute("generic.npc_experience", 0, 0, Double.MAX_VALUE).setSyncable(true));
    public static final DeferredHolder<Attribute, Attribute> NPC_LEVEL = ATTRIBUTES.register("npc_level", () -> new RangedAttribute("generic.npc_level", 0, 0, Double.MAX_VALUE).setSyncable(true));



}
