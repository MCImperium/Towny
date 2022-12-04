package com.palmergames.bukkit.towny.chat.types;

import com.palmergames.bukkit.towny.TownyUniverse;
import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
import com.palmergames.bukkit.towny.object.Resident;
import net.tnemc.tnc.core.common.chat.ChatType;
import net.minecraft.world.entity.player.Player;

import java.util.Collection;
import java.util.HashSet;
import java.util.UUID;

/**
 * @author creatorfromhell
 */
public class TownType extends ChatType {
	public TownType() {
		super("town", "<aqua>$display: <white>$message");
	}

	@Override
	public boolean canChat(Player player) {
		Resident res = TownyUniverse.getInstance().getResident(player.getUUID());
		return res != null && res.hasTown();
	}

	@Override
	public Collection<Player> getRecipients(Collection<Player> recipients, Player player) {
		TownyUniverse townyUniverse = TownyUniverse.getInstance();
		Resident resident = townyUniverse.getResident(player.getUUID());
		
		if (resident == null)
			return recipients;
		
		try {
			final UUID town = resident.getTown().getUUID();

			Collection<Player> newRecipients = new HashSet<>();

			for(Player p : recipients) {
				Resident playerResident = townyUniverse.getResident(p.getUUID());
				if(playerResident != null && playerResident.hasTown() && playerResident.getTown().getUUID().equals(town)) {
					newRecipients.add(p);
				}
			}
			return newRecipients;
		} catch(NotRegisteredException e) {
			e.printStackTrace();
		}
		return recipients;
	}
}