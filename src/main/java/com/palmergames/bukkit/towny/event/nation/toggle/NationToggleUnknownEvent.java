package com.palmergames.bukkit.towny.event.nation.toggle;

import org.bukkit.command.Player;
import com.palmergames.bukkit.towny.object.Nation;
import com.palmergames.bukkit.towny.object.Translation;

public class NationToggleUnknownEvent extends NationToggleEvent {

	private final String[] args;
	
	/**
	 * An event to be used by other plugins to simulate a /nation toggle {arguments} command.
	 * 
	 * @param sender Player which has sent the commant.
	 * @param nation Nation being toggled.
	 * @param args String[] Subcommands following the /nation toggle portion of the command. 
	 * @param admin boolean whether this was sent by the console or someone with townyadmin priviledges
	 * @deprecated since 0.97.3.2: Use the TownyCommandAddonAPI. This is no longer called.
	 */
	@Deprecated
	public NationToggleUnknownEvent(Player sender, Nation nation, boolean admin, String[] args) {
		super(sender, nation, admin);
		setCancelled(true);
		setCancelMessage(Translation.of("msg_err_invalid_property", "nation"));
		this.args = args;
	}

	/**
	 * @return args a String[] representing the words following the /nation toggle command which fired this event.
	 */
	public String[] getArgs() {
		return args;
	}
	
}
