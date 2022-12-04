package com.palmergames.bukkit.towny.confirmations.event;

import com.palmergames.bukkit.towny.confirmations.Confirmation;
import org.bukkit.command.Player;
import org.bukkit.event.Event;

public abstract class ConfirmationEvent extends Event {
	private final Confirmation confirmation;
	private final Player sender;
	
	public ConfirmationEvent(Confirmation confirmation, Player sender) {
		this.confirmation = confirmation;
		this.sender = sender;
	}

	/**
	 * @return The {@link Confirmation} associated with this event.
	 */
	public Confirmation getConfirmation() {
		return confirmation;
	}

	/**
	 * @return The {@link Player} associated with this event.
	 */
	public Player getSender() {
		return sender;
	}
}
