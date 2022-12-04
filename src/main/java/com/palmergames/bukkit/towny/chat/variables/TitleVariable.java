package com.palmergames.bukkit.towny.chat.variables;

import com.palmergames.bukkit.towny.TownyUniverse;
import com.palmergames.bukkit.towny.object.Resident;
import net.tnemc.tnc.core.common.chat.ChatVariable;
import net.minecraft.world.entity.player.Player;

/**
 * @author creatorfromhell
 */
public class TitleVariable extends ChatVariable {
	@Override
	public String name() {
		return "$title";
	}

	@Override
	public String parse(Player player, String message) {
		Resident res = TownyUniverse.getInstance().getResident(player.getUUID());
		return res != null ? res.getTitle() : "";
	}
}