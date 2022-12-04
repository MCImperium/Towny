package com.palmergames.bukkit.towny.event.teleport;

import net.minecraft.core.BlockPos;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.Town;

public class OutlawTeleportEvent extends Event implements Cancellable {

	private static final HandlerList handlers = new HandlerList();
	private boolean isCancelled = false;
	private Resident outlaw;
	private Town town;
	private BlockPos outlawBlockPos;
	
    public OutlawTeleportEvent(Resident outlaw, Town town, BlockPos loc) {
        this.outlaw = outlaw;
        this.town = town;
        this.outlawBlockPos = loc;
    }

	@NotNull
	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
    
    @Override
    public boolean isCancelled() {
        return isCancelled;
    }

	@Override
	public void setCancelled(boolean cancelled) {
		isCancelled = cancelled;
	}

	public Resident getOutlaw() {
		return outlaw;
	}

	public Town getTown() {
		return town;
	}

	@Nullable
	public Town getOutlawTownOrNull() {
		return outlaw.getTownOrNull();
	}

	public BlockPos getOutlawBlockPos() {
		return outlawBlockPos;
	}

}
