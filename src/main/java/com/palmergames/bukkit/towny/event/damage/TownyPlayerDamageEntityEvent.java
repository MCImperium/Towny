package com.palmergames.bukkit.towny.event.damage;

import net.minecraft.core.BlockPos;
import org.bukkit.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import com.palmergames.bukkit.towny.object.TownBlock;

/**
 * Part of the API which lets Towny's war and other plugins modify Towny's
 * plot-permission-decision outcomes.
 * 
 * @author LlmDl
 */
public class TownyPlayerDamageEntityEvent extends TownyDamageEvent {

	private static final HandlerList handlers = new HandlerList();
	private final Player player;

	/**
	 * This event is not fired and this class is only a placeholder.
	 * 
	 * @param location BlockPos of the entity being damaged.
	 * @param harmedEntity Entity getting exploded.
	 * @param cause DamageCause.
	 * @param townblock TownBlock or null if in the wilderness.
	 * @param cancelled Whether Towny will cancel this already.
	 * @param player Player causing the damage to the harmedEntity.
	 */
	public TownyPlayerDamageEntityEvent(BlockPos location, Entity harmedEntity, DamageCause cause, TownBlock townblock, boolean cancelled, Player player) {
		super(location, harmedEntity, cause, townblock, cancelled);
		this.player = player;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	public HandlerList getHandlers() {
		return handlers;
	}

	/**
	 * @return Player harming the entity.
	 */
	public Player getAttackingPlayer() {
		return player;
	}
}
