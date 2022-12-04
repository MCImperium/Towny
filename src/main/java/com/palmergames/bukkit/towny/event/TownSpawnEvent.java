package com.palmergames.bukkit.towny.event;

import com.palmergames.bukkit.towny.object.Town;
import com.palmergames.bukkit.towny.object.WorldCoord;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.Nullable;

/**
 * An event called when town spawns occur.
 * 
 * @author Suneet Tipirneni (Siris)
 */
public class TownSpawnEvent extends SpawnEvent {
	
	private final Town fromTown;
	private final Town toTown;
	
	public TownSpawnEvent(Player player, BlockPos from, BlockPos to) {
		super(player, from, to);

		fromTown = WorldCoord.parseWorldCoord(from).getTownOrNull();
		toTown = WorldCoord.parseWorldCoord(to).getTownOrNull();
	}

	/**
	 * Gets the town that the player is teleporting to.
	 * 
	 * @return The Town being teleported to.
	 */
	public Town getToTown() {
		return toTown;
	}

	/**
	 * Gets the town being teleported from.
	 * 
	 * @return null if the player was not standing in a townblock, the town they were standing in otherwise.
	 */
	@Nullable
	public Town getFromTown() {
		return fromTown;
	}
}
