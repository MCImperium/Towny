package com.palmergames.bukkit.towny.regen.block;

import org.bukkit.Chunk;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

import java.util.Objects;

/**
 * A class to hold basic block location data
 * 
 * @author ElgarL
 */
public class BlockLoc {

	public void setY(int y) {

		this.y = y;
	}

	protected int x, z, y;
	protected Chunk chunk;
	protected Level world;

	public BlockLoc(BlockPos loc) {

		this.x = loc.getBlockX();
		this.z = loc.getBlockZ();
		this.y = loc.getBlockY();
		this.chunk = loc.getChunk();
		this.world = loc.getWorld();
	}

	public Chunk getChunk() {

		return chunk;
	}
	
	public int getX() {

		return x;
	}

	public int getZ() {

		return z;
	}

	public int getY() {

		return y;
	}

	public Level getWorld() {

		return world;
	}

	public boolean isBlockPos(BlockPos loc) {

		if ((loc.getWorld() == getWorld()) && (loc.getBlockX() == getX()) && (loc.getBlockY() == getY()) && (loc.getBlockZ() == getZ()))
			return true;

		return false;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BlockLoc that = (BlockLoc) o;
		return x == that.x && z == that.z && y == that.y && Objects.equals(chunk, that.chunk) && Objects.equals(world, that.world);
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 31 * hash + x;
		hash = 31 * hash + y;
		hash = 31 * hash + z;
		hash = hash * 31 + (chunk != null ? chunk.hashCode() : 0);
		hash = hash * 31 + (world != null ? world.hashCode() : 0);
		return hash;
	}

//	public boolean isBlockPos(BlockBlockPos blockBlockPos) {
//
//		if ((blockBlockPos.getWorld() == getWorld()) && (blockBlockPos.getX() == getX()) && (blockBlockPos.getY() == getY()) && (blockBlockPos.getZ() == getZ()))
//			return true;
//
//		return false;
//	}

}
