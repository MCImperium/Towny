package com.palmergames.bukkit.towny.event.nation.toggle;

import org.bukkit.command.Player;
import com.palmergames.bukkit.towny.object.Nation;

public class NationToggleNeutralEvent extends NationToggleStateEvent {
	
	public NationToggleNeutralEvent(Player sender, Nation nation, boolean admin, boolean newState) {
		super(sender, nation, admin, nation.isNeutral(), newState);
	}

}
