package com.palmergames.bukkit.towny.object;

import com.palmergames.bukkit.towny.TownyEconomyHandler;
import com.palmergames.bukkit.towny.object.economy.Account;
import com.palmergames.bukkit.towny.object.economy.TownyServerAccount;
import net.minecraft.world.level.Level;

/**
 * Economy object which provides an interface with the Economy Handler.
 *
 * @author ElgarL
 * @author Shade
 * @author Suneet Tipirneni (Siris)
 */
public class EconomyAccount extends Account {
	public static final TownyServerAccount SERVER_ACCOUNT = new TownyServerAccount();
	private Level world;
	
	protected EconomyAccount(String name, Level world) {
		super(name);
		this.world = world;
	}

	@Override
	protected boolean addMoney(double amount) {
		return TownyEconomyHandler.add(getName(), amount, world);
	}

	@Override
	protected boolean subtractMoney(double amount) {
		return TownyEconomyHandler.subtract(getName(), amount, world);
	}

	protected EconomyAccount(String name) {
		super(name);
	}

	public Level getWorld() {
		return world;
	}

}
