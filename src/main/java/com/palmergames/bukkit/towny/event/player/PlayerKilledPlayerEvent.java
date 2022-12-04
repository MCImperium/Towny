package com.palmergames.bukkit.towny.event.player;

import org.bukkit.Bukkit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.PlayerDeathEvent;

import com.palmergames.bukkit.towny.object.Resident;

public class PlayerKilledPlayerEvent extends Event {

	private static final HandlerList handlers = new HandlerList();
	private Player killer;
	private Player victim;
	private Resident killerRes;
	private Resident victimRes;
	private BlockPos location;
	private PlayerDeathEvent deathEvent;
	
	/**
	 * An event fired from a EventPriority.MONITOR listener. 
	 * Do not use to un-kill someone or you're likely to cause 
	 * issues with other plugins.
	 * 
	 * @param killer {@link Player} that killed the victim.
	 * @param victim {@link Player} that died.
	 * @param killerRes {@link Resident} that killed the victim.
	 * @param victimRes {@link Resident} that died.
	 * @param location {@link BlockPos} of the player that died.
	 * @param event The {@link PlayerDeathEvent}.
	 */
	public PlayerKilledPlayerEvent(Player killer, Player victim, Resident killerRes, Resident victimRes, BlockPos location, PlayerDeathEvent event) {
		super(!Bukkit.getServer().isPrimaryThread());
		this.killer = killer;
		this.victim = victim;
		this.killerRes = killerRes;
		this.victimRes = victimRes;
		this.location = location;
		this.deathEvent = event;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	
	public static HandlerList getHandlerList() {
		return handlers;
	}

	public Player getKiller() {
		return killer;
	}

	public Player getVictim() {
		return victim;
	}

	public Resident getKillerRes() {
		return killerRes;
	}

	public Resident getVictimRes() {
		return victimRes;
	}
	public BlockPos getBlockPos() {
		return location;
	}
	
	public PlayerDeathEvent getPlayerDeathEvent() {
		return deathEvent;
	}

}
