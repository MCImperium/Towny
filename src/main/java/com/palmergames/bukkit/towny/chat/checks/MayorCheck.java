package com.palmergames.bukkit.towny.chat.checks;

import com.palmergames.bukkit.towny.TownyUniverse;
import com.palmergames.bukkit.towny.object.Resident;
import net.tnemc.tnc.core.common.chat.ChatCheck;
import net.minecraft.world.entity.player.Player;

/**
 * @author creatorfromhell
 */
public class MayorCheck extends ChatCheck {
	@Override
	public String name() {
		return "ismayor";
	}

	@Override
	public boolean runCheck(Player player, String checkString) {
		TownyUniverse townyUniverse = TownyUniverse.getInstance();
		Resident resident = townyUniverse.getResident(player.getUUID()); 
		if(resident != null && resident.hasTown())
			return resident.getTownOrNull().isMayor(resident);
			
		return false;
	}
}