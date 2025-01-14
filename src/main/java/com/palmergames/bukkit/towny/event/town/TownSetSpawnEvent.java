package com.palmergames.bukkit.towny.event.town;

import com.palmergames.bukkit.towny.event.CancellableTownyEvent;
import com.palmergames.bukkit.towny.object.Town;
import com.palmergames.bukkit.towny.object.Translation;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Fired when a player uses /town set spawn
 */
public class TownSetSpawnEvent extends CancellableTownyEvent {
	private static final HandlerList HANDLER_LIST = new HandlerList();
	
	private final Town town;
	private final Player player;
	private final BlockPos oldSpawn;
	private BlockPos newSpawn;

	public TownSetSpawnEvent(Town town, Player player, BlockPos newSpawn) {
		this.town = town;
		this.player = player;
		this.oldSpawn = town.getSpawnOrNull();
		this.newSpawn = newSpawn;
		setCancelMessage(Translation.of("msg_err_command_disable"));
	}

	/**
	 * @return The town for which this spawn is being set.
	 */
	@NotNull
	public Town getTown() {
		return town;
	}

	/**
	 * @return The player that ran the command
	 */
	@NotNull
	public Player getPlayer() {
		return player;
	}

	/**
	 * @return The town's old spawnpoint location.
	 */
	@Nullable
	public BlockPos getOldSpawn() {
		return oldSpawn;
	}

	/**
	 * @return The new spawn location.
	 */
	@NotNull
	public BlockPos getNewSpawn() {
		return newSpawn;
	}

	public void setNewSpawn(@NotNull BlockPos newSpawn) {
		this.newSpawn = newSpawn;
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
