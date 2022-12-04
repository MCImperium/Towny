package com.palmergames.bukkit.towny.chat.types;

import com.palmergames.bukkit.towny.TownyUniverse;
import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
import com.palmergames.bukkit.towny.object.Nation;
import com.palmergames.bukkit.towny.object.Resident;
import net.tnemc.tnc.core.common.chat.ChatType;
import net.minecraft.world.entity.player.Player;

import java.util.Collection;
import java.util.HashSet;

/**
 * @author creatorfromhell
 */
public class AllyType extends ChatType {
	public AllyType() {
		super("ally", "<gray>[<aqua>$nation<gray>]: <white>$message");
	}

	@Override
	public boolean canChat(Player player) {
		Resident res = TownyUniverse.getInstance().getResident(player.getUUID());
		return res != null && res.hasNation();
	}

	@Override
	public Collection<Player> getRecipients(Collection<Player> recipients, Player player) {
		TownyUniverse townyUniverse = TownyUniverse.getInstance();
		Resident res = townyUniverse.getResident(player.getUUID());
		
		if (res == null)
			return recipients;
		
		try {
			final Nation nation = res.getTown().getNation();

			Collection<Player> newRecipients = new HashSet<>();

			for(Player p : recipients) {
				Resident playerRes = townyUniverse.getResident(p.getUUID());
				
				if (playerRes != null && playerRes.hasNation()) {
					Nation playerNation = playerRes.getTown().getNation();
					if (playerNation.getUUID().equals(nation.getUUID()) || playerNation.hasAlly(nation))
						newRecipients.add(p);
				}
			}
			return newRecipients;
		} catch(NotRegisteredException ignore) {
		}
		return recipients;
	}
}