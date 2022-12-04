package com.palmergames.bukkit.towny.event;

import com.palmergames.bukkit.towny.object.WorldCoord;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class PreNewTownEvent extends CancellableTownyEvent {
	private static final HandlerList HANDLER_LIST = new HandlerList();

	private final Player player;
	private final String townName;
	private final BlockPos spawnBlockPos;
	private final WorldCoord worldCoord;

	public PreNewTownEvent(Player player, String townName, BlockPos spawnBlockPos) {
		this.player = player;
		this.townName = townName;
		this.spawnBlockPos = spawnBlockPos;
		this.worldCoord = WorldCoord.parseWorldCoord(spawnBlockPos);
	}

	public Player getPlayer() {
		return player;
	}

	public String getTownName() {
		return townName;
	}
	
	public BlockPos getTownBlockPos() {
		return this.spawnBlockPos;
	}
	
	public WorldCoord getTownWorldCoord() {
		return this.worldCoord;
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
