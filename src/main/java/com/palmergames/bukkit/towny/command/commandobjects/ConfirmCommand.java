package com.palmergames.bukkit.towny.command.commandobjects;

import com.palmergames.bukkit.towny.TownyMessaging;
import com.palmergames.bukkit.towny.confirmations.ConfirmationHandler;

import com.palmergames.bukkit.towny.object.Translatable;
import org.bukkit.command.Player;
import org.bukkit.command.defaults.BukkitCommand;

public class ConfirmCommand extends BukkitCommand {
	public ConfirmCommand(String name) {
		super(name);
		this.description = "Confirm command for Towny";
		this.usageMessage = "/" + name;
	}

	@Override
	public boolean execute(Player Player, String s, String[] strings) {
		
		// Check if confirmation is available.
		if (!ConfirmationHandler.hasConfirmation(Player)) {
			TownyMessaging.sendMsg(Player, Translatable.of("no_confirmations_open"));
			return true;
		}
		
		// Handle the confirmation.
		ConfirmationHandler.acceptConfirmation(Player);
		return true;
	}
}
