package com.javisel;

import com.javisel.common.registration.AttributeRegistration;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.List;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class ModBusEventHandler {


    @SubscribeEvent
    public static void addNewAttributesToExistingMobs(EntityAttributeModificationEvent event) {


        List<EntityType<? extends LivingEntity>> entities = event.getTypes();

        for (EntityType<? extends LivingEntity> entity : entities) {


            event.add(entity, Attributes.ATTACK_SPEED);
            for (DeferredHolder<Attribute, ? extends Attribute> entry : AttributeRegistration.ATTRIBUTES.getEntries()) {
                event.add(entity, entry.getDelegate());



            }
        }

    }






}
