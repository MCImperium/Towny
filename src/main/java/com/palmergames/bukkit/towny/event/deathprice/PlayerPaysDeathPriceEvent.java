package com.palmergames.bukkit.towny.event.deathprice;

import net.minecraft.world.entity.player.Player;

import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.economy.Account;

public class PlayerPaysDeathPriceEvent extends DeathPriceEvent {

	public PlayerPaysDeathPriceEvent(Account payer, double amount, Resident deadResident, Player killer) {
		super(payer, amount, deadResident, killer);
	}

	/**
	 * @return the killer
	 */
	public Player getKiller() {
		return killer;
	}
}
