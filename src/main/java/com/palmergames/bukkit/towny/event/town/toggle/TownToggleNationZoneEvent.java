package com.palmergames.bukkit.towny.event.town.toggle;

import org.bukkit.command.Player;
import com.palmergames.bukkit.towny.object.Town;

public class TownToggleNationZoneEvent extends TownToggleStateEvent {
	
	public TownToggleNationZoneEvent(Player sender, Town town, boolean admin, boolean newState) {
		super(sender, town, admin, town.isNationZoneEnabled(), newState);
	}

}