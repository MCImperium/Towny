package com.palmergames.bukkit.towny.object;

import com.palmergames.bukkit.towny.exceptions.TownyException;
import net.minecraft.core.BlockPos;

/**
 * Provides the spawn location and access to setter for spawn locations.
 */
public interface SpawnBlockPos {
	/**
	 * Get the spawn location of this object.
	 * @return The spawn location
	 * 
	 * @throws TownyException If no location is found.
	 */
	BlockPos getSpawn() throws TownyException;

	/**
	 * Checks whether a spawn point exists.
	 * 
	 * @return A boolean indicating if the spawn exists.
	 */
	default boolean hasSpawn(){
		try {
			return getSpawn() != null;
		} catch (TownyException e) {
			return false;
		}
	}

	/**
	 * Sets the spawn point of this object.
	 * @param spawn The BlockPos of the new spawn.
	 *                 
	 * @throws TownyException If the spawn could not be set.
	 */
	void setSpawn(BlockPos spawn) throws TownyException;
	
	default BlockPos getSpawnOrNull() {
		try {
			return getSpawn();
		} catch (TownyException e) {
			return null;
		}
	}
}
