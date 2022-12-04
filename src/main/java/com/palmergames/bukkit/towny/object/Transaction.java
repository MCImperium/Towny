package com.palmergames.bukkit.towny.object;

import net.minecraft.world.entity.player.Player;

public class Transaction {
	private final TransactionType type;
	private final Player player;
	private final double amount;
	
	public Transaction(TransactionType type, Player player, double amount) {
		this.type = type;
		this.player = player;
		this.amount = amount;
	}

	public TransactionType getType() {
		return type;
	}

	public Player getPlayer() {
		return player;
	}

	public double getAmount() {
		return amount;
	}
}
