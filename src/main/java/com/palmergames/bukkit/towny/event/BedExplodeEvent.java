package com.palmergames.bukkit.towny.event;

import org.bukkit.Bukkit;
import net.minecraft.core.BlockPos;
import org.bukkit.Material;
import net.minecraft.world.entity.player.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class BedExplodeEvent extends Event {

	private static final HandlerList handlers = new HandlerList();
	
	private final Player player;
	private final BlockPos location1;
	private final BlockPos location2;
	private final Material material;
	
	public BedExplodeEvent(Player player, BlockPos loc1, BlockPos loc2, Material mat) {
		super(!Bukkit.getServer().isPrimaryThread());
		this.player = player;
		this.location1 = loc1;
		this.location2 = loc2;
		this.material = mat;
	}
	
	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	
    public static HandlerList getHandlerList() {

		return handlers;
	}
	
	public BlockPos getBlockPos() {
		return location1;
	}
	
	public BlockPos getBlockPos2() {
		return location2;
	}
	
	public Material getMaterial() {
		return material;
	}
	
	public Player getPlayer() {
		return player;
	}

}
