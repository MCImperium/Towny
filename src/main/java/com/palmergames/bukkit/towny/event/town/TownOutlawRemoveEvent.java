package com.palmergames.bukkit.towny.event.town;

import com.palmergames.bukkit.towny.event.CancellableTownyEvent;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.Town;
import com.palmergames.bukkit.towny.object.Translation;
import org.bukkit.command.Player;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class TownOutlawRemoveEvent extends CancellableTownyEvent {
	private static final HandlerList HANDLER_LIST = new HandlerList();
	
	private final Town town;
	private final Resident outlawedResident;
	private final Player sender;
	
	public TownOutlawRemoveEvent(Player sender, Resident outlawedResident, Town town) {
		this.town = town;
		this.outlawedResident = outlawedResident;
		this.sender = sender;
		setCancelMessage(Translation.of("msg_err_command_disable"));
	}

	/**
	 * @return The town where the resident is being removed as an outlaw.
	 */
	public Town getTown() {
		return town;
	}

	/**
	 * @return The resident that is being removed as an outlaw.
	 */
	public Resident getOutlawedResident() {
		return outlawedResident;
	}

	@NotNull
	public Player getSender() {
		return sender;
	}

	public static HandlerList getHandlerList() {
		return HANDLER_LIST;
	}

	@NotNull
	@Override
	public HandlerList getHandlers() {
		return HANDLER_LIST;
	}
}
