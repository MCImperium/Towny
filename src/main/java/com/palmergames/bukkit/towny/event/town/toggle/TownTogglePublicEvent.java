package com.palmergames.bukkit.towny.event.town.toggle;

import org.bukkit.command.Player;
import com.palmergames.bukkit.towny.object.Town;

public class TownTogglePublicEvent extends TownToggleStateEvent {
	
	public TownTogglePublicEvent(Player sender, Town town, boolean admin, boolean newState) {
		super(sender, town, admin, town.isPublic(), newState);
	}

}
