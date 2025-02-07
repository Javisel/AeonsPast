package com.javisel.client;

import com.javisel.common.item.ItemEngine;
import com.javisel.common.item.ItemRarity;
import com.javisel.common.network.stacksyncmessage.StackSyncMessage;
import com.javisel.common.registration.DataComponentsRegistration;
import com.mojang.datafixers.util.Either;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderTooltipEvent;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.List;

@EventBusSubscriber(Dist.CLIENT)
public class ClientEvents {


    @SubscribeEvent
    public static void tooltip(RenderTooltipEvent.GatherComponents event) {

        Player player = Minecraft.getInstance().player;
        ItemStack stack = event.getItemStack();
        Item item = stack.getItem();

        List<Either<FormattedText, TooltipComponent>> tooltips = event.getTooltipElements();

        if (ItemEngine.isRPGItem(stack)) {

            if (!ItemEngine.isItemInit(stack)) {

                event.getTooltipElements().clear();
                tooltips.add(Either.left(stack.getHoverName()));
                MutableComponent translate = Component.translatable("tooltip.sleepingitem.desc").withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.RED);

                tooltips.add(Either.left(translate));
                PacketDistributor.sendToServer(new StackSyncMessage(1));

            } else {
                MutableComponent translate = getRarityComponent(stack);
                tooltips.add(1,Either.left(translate));

                if (ItemEngine.isWeapon(stack)) {

                    renderWeaponTooltip(tooltips,stack);


                }
                if (ItemEngine.isArmor(stack)) {

                    renderArmorTooltip(tooltips,stack);

                }


            }


        }


    }


public static MutableComponent getRarityComponent(ItemStack stack){

    ItemRarity rarity = ItemRarity.getByString(stack.get(DataComponentsRegistration.ITEM_DATA).rarity());


    MutableComponent translate = Component.translatable(stack.get(DataComponentsRegistration.ITEM_DATA).rarity()).withStyle(rarity.getChatFormat());



return translate;
}

public static void renderWeaponTooltip( List<Either<FormattedText, TooltipComponent>> tooltips, ItemStack stack) {




}

public static void renderArmorTooltip( List<Either<FormattedText, TooltipComponent>> tooltips, ItemStack stack) {

    MutableComponent type = Component.translatable(stack.get(DataComponentsRegistration.ARMOR_DATA).armorType()).withStyle(ChatFormatting.WHITE);

    tooltips.add(2,Either.left(type));



    }







}
