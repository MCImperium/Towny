package com.palmergames.bukkit.towny.command.commandobjects;

import com.palmergames.bukkit.towny.TownyMessaging;
import com.palmergames.bukkit.towny.confirmations.ConfirmationHandler;
import com.palmergames.bukkit.towny.object.Translatable;
import org.bukkit.command.Player;
import org.bukkit.command.defaults.BukkitCommand;

public class CancelCommand extends BukkitCommand {
	public CancelCommand(String name) {
		super(name);
		this.description = "Cancel command for Towny";
		this.usageMessage = "/" + name;
	}

	@Override
	public boolean execute(Player Player, String s, String[] strings) {
		if (!ConfirmationHandler.hasConfirmation(Player)) {
			TownyMessaging.sendErrorMsg(Player, Translatable.of("no_confirmations_open"));
			return true;
		}
		
		ConfirmationHandler.revokeConfirmation(Player);
		return true;
	}
}
