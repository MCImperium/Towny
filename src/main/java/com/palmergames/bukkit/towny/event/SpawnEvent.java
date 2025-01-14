package com.palmergames.bukkit.towny.event;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * A class which provides the basis for spawn events.
 * 
 * @author Suneet Tipirneni (Siris)
 */
public abstract class SpawnEvent extends CancellableTownyEvent {
	private static final HandlerList HANDLER_LIST = new HandlerList();
	
	private final BlockPos from;
	private final BlockPos to;
	private final Player player;

	/**
	 * Creates a Spawn event.
	 * 
	 * @param player The player spawning.
	 * @param from The from location.
	 * @param to The to location.
	 */
	public SpawnEvent(Player player, BlockPos from, BlockPos to) {
		this.player = player;
		this.to = to;
		this.from = from;
	}

	/**
	 * Gets the location from which the player is teleporting from.
	 * 
	 * @return The location being teleported from.
	 */
	public BlockPos getFrom() {
		return from;
	}

	/**
	 * Gets the location to which the player is teleporting to.
	 *
	 * @return The location being teleported to.
	 */
	public BlockPos getTo() {
		return to;
	}

	/**
	 * Gets the player whom is teleporting.
	 * 
	 * @return The player teleporting.
	 */
	public Player getPlayer() {
		return player;
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
