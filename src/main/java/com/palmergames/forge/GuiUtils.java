package com.palmergames.forge;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraftforge.network.NetworkHooks;

public class GuiUtils {
	
	public static void openGui(ServerPlayer thePlayer,  GuiContainer gui, String title) {
		NetworkHooks.openScreen(thePlayer, new SimpleMenuProvider(
			(containerId, playerInventory, player) -> new GuiChestMenu(containerId, playerInventory, gui, 6),
			Component.translatable(title)
		));
	}
}
