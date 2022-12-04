package com.palmergames.bukkit.towny.command.commandobjects;

import com.palmergames.bukkit.towny.command.InviteCommand;
import org.bukkit.command.Player;
import org.bukkit.command.defaults.BukkitCommand;
import net.minecraft.world.entity.player.Player;

public class DenyCommand extends BukkitCommand {
	public DenyCommand(String name) {
		super(name);
		this.description = "Deny command for Towny";
		this.usageMessage = "/" + name + " <Town>";
	}

	@Override
	public boolean execute(Player Player, String s, String[] strings) {
		if (Player instanceof Player) {
			InviteCommand.parseDeny((Player) Player, strings);
			return true;
		} else {
			return true;
		}
	}
}
