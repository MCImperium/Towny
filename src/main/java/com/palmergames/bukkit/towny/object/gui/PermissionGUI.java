package com.palmergames.bukkit.towny.object.gui;

import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.TownBlock;
import com.palmergames.bukkit.towny.object.TownyInventory;
import com.palmergames.bukkit.util.Colors;
import com.palmergames.forge.GuiContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class PermissionGUI extends TownyInventory {
	private final TownBlock townBlock;
	private final boolean canEdit;
	
	public PermissionGUI(Resident res, GuiContainer inv, String name, TownBlock townBlock, boolean canEdit) {
		super(res, inv, name);
		this.townBlock = townBlock;
		this.canEdit = canEdit;
	}

	public TownBlock getTownBlock() {
		return townBlock;
	}

	public boolean canEdit() {
		return canEdit;
	}

	@Override
	public void tryPaginate(ItemStack clickedItem, Player player, Resident resident, String title) {
		int currentPage = resident.getGUIPageNum();

		try {
			// If the pressed item was a nextpage button
			if (clickedItem.getHoverName().getString().equals(Colors.Gold + "Next")) {
				if (resident.getGUIPageNum() <= resident.getGUIPages().size() - 1) {
					// Next page exists, flip the page
					resident.setGUIPageNum(++currentPage);
					new PermissionGUI(resident, resident.getGUIPage(), title, townBlock, canEdit);
					playClickSound(player);
				}
				// if the pressed item was a previous page button
			} else if (clickedItem.getHoverName().getString().equals(Colors.Gold + "Back")) {
				// If the page number is more than 0 (So a previous page exists)
				if (resident.getGUIPageNum() > 0) {
					// Flip to previous page
					resident.setGUIPageNum(--currentPage);
					new PermissionGUI(resident, resident.getGUIPage(), title, townBlock, canEdit);
					playClickSound(player);
				}
			}
		} catch (Exception ignored) {}
	}
}
