package com.palmergames.bukkit.towny.object;

import net.minecraft.core.BlockPos;

/**
 * A worldname, x, y, z location used to validate SpawnPoints. 
 */
public class SpawnPointBlockPos {
	private final String world; 
	private final int x;
	private final int y;
	private final int z;
	
	public SpawnPointBlockPos(BlockPos loc) {
		this.world = loc.getWorld().getName();
		this.x = loc.getBlockX();
		this.y = loc.getBlockY();
		this.z = loc.getBlockZ();
	}

	public String getWorld() {
		return world;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getZ() {
		return z;
	}
	
	public static SpawnPointBlockPos parseSpawnPointBlockPos(BlockPos loc) {
		return new SpawnPointBlockPos(loc);
	}
	
	public String toString() {
		return world + "," + x + "," + y + "," + z;  
	}
	
	public boolean equals(SpawnPointBlockPos loc) {
		return world.equalsIgnoreCase(loc.getWorld()) && x == loc.getX() && y == loc.getY() && z == loc.getZ();
	}
}
