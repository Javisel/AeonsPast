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


}
