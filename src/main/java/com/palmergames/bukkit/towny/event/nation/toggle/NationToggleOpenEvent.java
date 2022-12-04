package com.palmergames.bukkit.towny.event.nation.toggle;

import org.bukkit.command.Player;
import com.palmergames.bukkit.towny.object.Nation;

public class NationToggleOpenEvent extends NationToggleStateEvent {
	
	public NationToggleOpenEvent(Player sender, Nation nation, boolean admin, boolean newState) {
		super(sender, nation, admin, nation.isOpen(), newState);
	}
	
}
