package com.palmergames.bukkit.towny.event.nation;

import com.palmergames.bukkit.towny.event.CancellableTownyEvent;
import com.palmergames.bukkit.towny.object.Nation;
import com.palmergames.bukkit.towny.object.Translation;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Fired when a player uses /nation set spawn
 */
public class NationSetSpawnEvent extends CancellableTownyEvent {
	private static final HandlerList HANDLER_LIST = new HandlerList();

	private final Nation nation;
	private final Player player;
	private final BlockPos oldSpawn;
	private BlockPos newSpawn;

	public NationSetSpawnEvent(Nation nation, Player player, BlockPos newSpawn) {
		this.nation = nation;
		this.player = player;
		this.oldSpawn = nation.getSpawnOrNull();
		this.newSpawn = newSpawn;
		setCancelMessage(Translation.of("msg_err_command_disable"));
	}

	/**
	 * @return The nation for which this spawn is being set.
	 */
	@NotNull
	public Nation getNation() {
		return nation;
	}

	/**
	 * @return The player that is changing the spawn.
	 */
	@NotNull
	public Player getPlayer() {
		return player;
	}

	/**
	 * @return The old spawn, or {@code null} if none has been set.
	 */
	@Nullable
	public BlockPos getOldSpawn() {
		return oldSpawn;
	}

	/**
	 * @return The location where the spawn is being set to.
	 */
	@NotNull
	public BlockPos getNewSpawn() {
		return newSpawn;
	}

	/**
	 * @param newSpawn Sets the location where the new spawn will be set to.
	 */
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
