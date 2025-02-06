package com.javisel.common.network.stacksyncmessage;

import com.javisel.common.item.ItemEngine;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class StackSyncHandler {



    public static void handleDataOnMain(final StackSyncMessage data, final IPayloadContext context) {

        context.enqueueWork(() -> {

            Player player = context.player();


            for (ItemStack stack : player.getInventory().items) {

                if (!stack.isEmpty()) {

                    ItemEngine.initializeItem(player, stack);

                }


            }

        });

    }





}
