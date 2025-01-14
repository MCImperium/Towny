package com.palmergames.bukkit.towny.event;

import com.palmergames.bukkit.towny.TownyUniverse;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.TownBlock;
import org.bukkit.Bukkit;
import net.minecraft.world.entity.player.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/*
 * This event runs Async. Be aware of such.
 */
public class TownClaimEvent extends Event  {

    private static final HandlerList handlers = new HandlerList();
    
    private final TownBlock townBlock;
    private Resident resident;

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
    
    public static HandlerList getHandlerList() {
		return handlers;
	}

    public TownClaimEvent(TownBlock townBlock, Player player) {
    	super(!Bukkit.getServer().isPrimaryThread());
        this.townBlock = townBlock;
        this.resident = TownyUniverse.getInstance().getResident(player.getUUID());
	}

    /**
     *
     * @return the new TownBlock.
     */
    public TownBlock getTownBlock() {
        return townBlock;
    }

	/**
	 * Gets the resident claiming the townblock.
	 * 
	 * @return The resident claiming.
	 */
	public Resident getResident() {
		return resident;
	}
}