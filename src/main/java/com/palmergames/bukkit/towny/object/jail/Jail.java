package com.palmergames.bukkit.towny.object.jail;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import net.minecraft.core.BlockPos;

import com.palmergames.bukkit.towny.object.Savable;
import com.palmergames.bukkit.towny.object.SpawnPoint;
import com.palmergames.bukkit.towny.object.Town;
import com.palmergames.bukkit.towny.object.TownBlock;
import com.palmergames.bukkit.towny.object.SpawnPointBlockPos;
import com.palmergames.bukkit.towny.object.SpawnPoint.SpawnPointType;
import com.palmergames.bukkit.towny.TownyUniverse;

public class Jail implements Savable {

	private UUID uuid;
	private Town town;
	private TownBlock townBlock;
	private Map<String, BlockPos> jailCellMap = new ConcurrentHashMap<String, BlockPos>();
	private List<BlockPos> jailCells = new ArrayList<>();
	
	public Jail(UUID uuid, Town town, TownBlock townBlock, List<BlockPos> jailCells) {
		this.uuid = uuid;
		this.town = town;
		this.townBlock = townBlock;
		if (jailCells != null)
			jailCells.stream().forEach(loc -> addJailCell(loc));
	}

	public UUID getUUID() {
		return uuid;
	}

	public void setUUID(UUID uuid) {
		this.uuid = uuid;
	}

	public Town getTown() {
		return town;
	}
	
	public void setTown(Town town) {
		this.town = town;
	}

	public TownBlock getTownBlock() {
		return townBlock;
	}

	public void setTownBlock(TownBlock townBlock) {
		this.townBlock = townBlock;
	}

	public List<BlockPos> getJailCellBlockPoss() {
		return Collections.unmodifiableList(jailCells);
	}

	public void setJailCells(List<BlockPos> jailCells) {
		this.jailCells = jailCells;
	}
	
	public void addJailCell(BlockPos location) {
		jailCells.add(location);
		jailCellMap.put(SpawnPointBlockPos.parseSpawnPointBlockPos(location).toString(), location);
		TownyUniverse.getInstance().addSpawnPoint(new SpawnPoint(location, SpawnPointType.JAIL_SPAWN));
	}
	
	public void removeJailCell(BlockPos loc) {
		TownyUniverse.getInstance().removeSpawnPoint(loc);
		String spawn = SpawnPointBlockPos.parseSpawnPointBlockPos(loc).toString();
		jailCells.remove(jailCellMap.get(spawn));
		jailCellMap.remove(spawn);
	}
	
	public void removeAllCells() {
		for (BlockPos loc : new ArrayList<>(jailCells))
			removeJailCell(loc);
	}
	
	public boolean hasJailCell(SpawnPointBlockPos loc) {
		return jailCellMap.keySet().stream().anyMatch(spawn -> spawn.equals(loc.toString()));
	}
	
	public boolean hasJailCell(int index) {
		if (jailCells == null || jailCells.size() < index)
			return false;
		return true;
	}
	
	public Map<String, BlockPos> getCellMap() {
		return jailCellMap;
	}
	
	public String getWildName() {
		return getTownBlock().getWorld().getFormattedUnclaimedZoneName();
	}
	
	@Override
	public void save() {
		TownyUniverse.getInstance().getDataSource().saveJail(this);
	}

	public boolean hasCells() {
		return !jailCells.isEmpty();
	}
	
	public boolean hasName() {
		return !getTownBlock().getName().isEmpty();
	}
	
	public String getName() {
		if (hasName())
			return getTownBlock().getName();
		return "";
	}
}
