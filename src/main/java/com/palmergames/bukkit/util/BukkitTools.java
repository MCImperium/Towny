package com.palmergames.bukkit.util;

import com.google.common.base.Charsets;
import com.palmergames.bukkit.towny.Towny;
import com.palmergames.bukkit.towny.TownySettings;
import com.palmergames.bukkit.towny.event.CancellableTownyEvent;
import com.palmergames.bukkit.towny.exceptions.CancelledEventException;

import net.citizensnpcs.api.CitizensAPI;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import org.bukkit.Bukkit;
import net.minecraft.core.BlockPos;
import org.bukkit.Material;
import org.bukkit.Server;
import net.minecraft.world.level.Level;
import org.bukkit.command.Player;
import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginManager;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scoreboard.Criteria;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * A class of functions related to Bukkit in general.
 * 
 * @author Shade (Chris H, ElgarL)
 * @version 1.0
 */

public class BukkitTools {

	private static Towny plugin = null;
	private static MinecraftServer server = null;
	
	public static void initialize(Towny plugin) {
		BukkitTools.plugin = plugin;
		BukkitTools.server = plugin.getServer();
	}
	
	/**
	 * Get an array of all online players
	 * 
	 * @return array of online players
	 */
	public static Collection<? extends ServerPlayer> getOnlinePlayers() {
		return getServer().getPlayerList().getPlayers();
	}
	
	public static List<Player> matchPlayer(String name) {
		List<Player> matchedPlayers = new ArrayList<>();
		
		for (Player iterPlayer : Bukkit.getOnlinePlayers()) {
			String iterPlayerName = iterPlayer.getName();
			if (checkCitizens(iterPlayer)) {
				continue;
			}
			if (name.equalsIgnoreCase(iterPlayerName)) {
				// Exact match
				matchedPlayers.clear();
				matchedPlayers.add(iterPlayer);
				break;
			}
			if (iterPlayerName.toLowerCase(java.util.Locale.ENGLISH).contains(name.toLowerCase(java.util.Locale.ENGLISH))) {
				// Partial match
				matchedPlayers.add(iterPlayer);
			}
		}
		
		return matchedPlayers;
	}
	
	/**
	 * Given a name this method should only return a UUID that is stored in the server cache,
	 * without pinging Mojang servers.
	 * 
	 * @param name - Resident/Player name to get a UUID for.
	 * @return UUID of player or null if the player is not in the cache.
	 */
	/*public static UUID getUUIDSafely(String name) {
		if (hasPlayedBefore(name))
			return getOfflinePlayer(name).getUUID();
		else
			return null;
	}*/
	
	@Nullable
	public static ServerPlayer getPlayerExact(String name) {
		return getServer().getPlayerList().getPlayerByName(name);
	}
	
	@Nullable
	public static Player getPlayer(String playerId) {
		return getServer().getPlayerList().getPlayerByName(playerId);
	}
	
	@Nullable
	public static Player getPlayer(UUID playerUUID) {
		return server.getPlayerList().getPlayer(playerUUID);
	}
	
	public static Collection<? extends Player> getVisibleOnlinePlayers(Player sender) {
		if (!(sender instanceof Player player))
			return Bukkit.getOnlinePlayers();
		
		return Bukkit.getOnlinePlayers().stream()
			.filter(player::canSee)
			.collect(Collectors.toCollection(ArrayList::new));
	}
	
	/**
	 * Tests if this player is online.
	 * 
	 * @param name the name of the player.
	 * @return a true value if online
	 */
	public static boolean isOnline(String name) {
		return Bukkit.getPlayerExact(name) != null;
	}
	
	public static Iterable<ServerLevel> getWorlds() {
		return getServer().getAllLevels();
	}
	
	public static ServerLevel getWorld(String name) {
		//return (ServerLevel) getServer().registryAccess().registry(Registry.DIMENSION_REGISTRY).get().get(new ResourceLocation(name));
		return getServer().getLevel(ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(name)));
	}
	
	public static Level getWorld(UUID worldUID) {
		return getServer().getLevel(worldUID);
	}
	
	public static MinecraftServer getServer() {
		synchronized(server) {
			return server;
		}
	}
	
	public static PluginManager getPluginManager() {
		return getServer().getPluginManager();
	}
	
	public static BukkitScheduler getScheduler() {
		return getServer().getScheduler();
	}
	
	/**
	 * Accepts a Runnable object and a delay (-1 for no delay)
	 * 
	 * @param task runnable object
	 * @param delay ticks to delay starting
	 * @return -1 if unable to schedule or an index to the task is successful.
	 */
	public static int scheduleSyncDelayedTask(Runnable task, long delay) {
		return getScheduler().scheduleSyncDelayedTask(plugin, task, delay);
	}
	
	/**
	 * Accepts a {@link Runnable} object and a delay (-1 for no delay)
	 * 
	 * @param task - Runnable
	 * @param delay - ticks to delay starting ({@link Long})
	 * @return -1 if unable to schedule or an index to the task is successful.
	 */
	public static int scheduleAsyncDelayedTask(Runnable task, long delay) {
		return getScheduler().runTaskLaterAsynchronously(plugin, task, delay).getTaskId();
	}
	
	/**
	 * Accepts a {@link Runnable} object with a delay/repeat (-1 for no delay)
	 * 
	 * @param task runnable object
	 * @param delay ticks to delay starting ({@link Long})
	 * @param repeat ticks to repeat after ({@link Long})
	 * @return -1 if unable to schedule or an index to the task is successful.
	 */
	public static int scheduleSyncRepeatingTask(Runnable task, long delay, long repeat) {
		return getScheduler().scheduleSyncRepeatingTask(plugin, task, delay, repeat);
	}
	
	/**
	 * Accepts a {@link Runnable} object with a delay/repeat (-1 for no delay)
	 * 
	 * @param task runnable object
	 * @param delay ticks to delay starting ({@link Long})
	 * @param repeat ticks to repeat after ({@link Long})
	 * @return -1 if unable to schedule or an index to the task is successful.
	 */
	public static int scheduleAsyncRepeatingTask(Runnable task, long delay, long repeat) {
		return getScheduler().runTaskTimerAsynchronously(plugin, task, delay, repeat).getTaskId();
	}
	
	/**
	 * Count the number of players online in each world
	 * 
	 * @return Map of world to online players.
	 */
	public static HashMap<String, Integer> getPlayersPerWorld() {

		HashMap<String, Integer> m = new HashMap<>();
		for (Level world : getServer().getWorlds())
			m.put(world.getName(), 0);
		for (Player player :  getServer().getOnlinePlayers())
			m.put(player.getWorld().getName(), m.get(player.getWorld().getName()) + 1);
		return m;
	}

	/**
	 * Accepts an X or Z value and returns the associated Towny plot value.
	 * 
	 * @param value - Value to calculate for X or Z ({@link Integer})
	 * @return int of the relevant townblock x/z.
	 */
	public static int calcChunk(int value) {

		return (value * TownySettings.getTownBlockSize()) / 16;
	}


	/*@SuppressWarnings("deprecation")
	public static boolean hasPlayedBefore(String name) {
		return getServer().getOfflinePlayer(name).hasPlayedBefore();
	}*/
	
	/**
	 * Do not use without first using {@link #hasPlayedBefore(String)}
	 * 
	 * @param name - name of resident
	 * @return OfflinePlayer
	 */
	/*@SuppressWarnings("deprecation")
	public static OfflinePlayer getOfflinePlayer(String name) {

		return Bukkit.getOfflinePlayer(name);
	}
	
	public static OfflinePlayer getOfflinePlayerForVault(String name) {

		return Bukkit.getOfflinePlayer(UUID.nameUUIDFromBytes(("OfflinePlayer:" + name).getBytes(Charsets.UTF_8)));
	}*/
	
	public static String convertCoordtoXYZ(BlockPos loc) {
		return loc.getWorld().getName() + " " + loc.getBlockX() + "," + loc.getBlockY() + "," + loc.getBlockZ();
	}
	
	public static List<String> getWorldNames() {
		return getWorlds().stream().map(Level::getName).collect(Collectors.toList());
	}
	
	public static List<String> getWorldNames(boolean lowercased) {
		return lowercased ? getWorlds().stream().map(world -> world.getName().toLowerCase()).collect(Collectors.toList()) : getWorldNames();
	}
	
	/**
	 * Check if the entity is a Citizens NPC.
	 * 
	 * Catches the NoClassDefFoundError thrown when Citizens is present 
	 * but failed to start up correctly.
	 * 
	 * @param entity Entity to check.
	 * @return true if the entity is an NPC.
	 */
	public static boolean checkCitizens(Entity entity) {
		if (plugin.isCitizens2()) {
			try {
				return CitizensAPI.getNPCRegistry().isNPC(entity);
			} catch (NoClassDefFoundError e) {
				plugin.setCitizens2(false);
			}
			
		}
		return false;
	}
	
	@SuppressWarnings("deprecation")
	public static Objective objective(Scoreboard board, @NotNull String name, @NotNull String displayName) {
		Objective objective;
		try {
			objective = board.registerNewObjective(name, Criteria.DUMMY, displayName);
		} catch (NoClassDefFoundError e) {
			// TODO: Remove when 1.19.2 is the lowest supported version.
			objective = board.registerNewObjective(name, "dummy", displayName);
		}
		return objective;
	}

	/**
	 * @param event The event to call
	 * @return {@code true} if the event is cancellable and was cancelled, otherwise {@code false}.
	 */
	public static boolean isEventCancelled(@NotNull Event event) {
		fireEvent(event);
		
		if (event instanceof Cancellable cancellable)
			return cancellable.isCancelled();
		else
			return false;
	}

	/**
	 * @param event CancellableTownyEvent to be fired which might be cancelled.
	 * @throws CancelledEventException with the Event's cancelMessage.
	 */
	public static void ifCancelledThenThrow(@NotNull CancellableTownyEvent event) throws CancelledEventException {
		fireEvent(event);
		if (event.isCancelled())
			throw new CancelledEventException(event);
	}

	public static void fireEvent(@NotNull Event event) {
		Bukkit.getPluginManager().callEvent(event);
	}

	/**
	 * Used to parse user-inputted material names into valid material names.
	 * 
	 * @param name String which should be a material. 
	 * @return String name of the material or null if no match could be made.
	 */
	@Nullable
	public static String matchMaterialName(String name) {
		Material mat = Material.matchMaterial(name.trim().toUpperCase(Locale.ROOT));
		return mat == null ? null : mat.name(); 
	}
}