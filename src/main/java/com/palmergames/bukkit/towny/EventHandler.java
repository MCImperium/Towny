package com.palmergames.bukkit.towny;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.apache.logging.log4j.core.config.plugins.util.PluginManager;

public class EventHandler {
	@SubscribeEvent
	public void tickHandler(TickEvent.ServerTickEvent event) {
		
	}
	@SubscribeEvent
	private void registerEvents(RegisterCommandsEvent event) {
		CommandDispatcher dispatcher = event.getDispatcher();

		if (!isError()) {
			// Huds
			pluginManager.registerEvents(HUDManager, this);

			// Manage player deaths and death payments
			pluginManager.registerEvents(entityMonitorListener, this);
			pluginManager.registerEvents(vehicleListener, this);
			pluginManager.registerEvents(serverListener, this);
			pluginManager.registerEvents(customListener, this);
			pluginManager.registerEvents(worldListener, this);
			pluginManager.registerEvents(loginListener, this);
		}

		// Always register these events.
		pluginManager.registerEvents(playerListener, this);
		pluginManager.registerEvents(blockListener, this);
		pluginManager.registerEvents(entityListener, this);
		pluginManager.registerEvents(inventoryListener, this);

		paperEvents.register();
	}
}
