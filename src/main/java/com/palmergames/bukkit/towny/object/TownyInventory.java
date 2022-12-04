package com.palmergames.bukkit.towny.object;

import com.palmergames.bukkit.towny.Towny;
import com.palmergames.bukkit.towny.utils.ResidentUtil;
import com.palmergames.bukkit.util.Colors;
import com.palmergames.forge.GuiContainer;
import com.palmergames.forge.GuiUtils;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.NotNull;

public class TownyInventory {
	
	private static final Sound clickSound = Sound.sound(Key.key(Key.MINECRAFT_NAMESPACE, "block.stone_button.click_on"), Sound.Source.MASTER, 1.0f, 1.0f);

	private final GuiContainer inv;
	
	public TownyInventory(Resident res, GuiContainer inv, String name) {
		this.inv = new GuiContainer(54);
		// name
		this.inv.setContents(inv); 
		GuiUtils.openGui(res.getPlayer(), this.inv, name);
	}

	//@Override
	public @NotNull GuiContainer getInventory() {
		return inv;
	}
	
	public void playClickSound(Player player) {
		Towny.getAdventure().player(player).playSound(clickSound);
	}
	
	public void tryPaginate(ItemStack clickedItem, Player player, Resident resident, String title) {
		int currentPage = resident.getGUIPageNum();

		try {
			// If the pressed item was a nextpage button
			if (clickedItem.getHoverName().getString().equals(Colors.Gold + "Next")) {
				// If there is no next page, don't do anything
				if (resident.getGUIPageNum() >= resident.getGUIPages().size() - 1) {
					return;
				} else {
					// Next page exists, flip the page
					resident.setGUIPageNum(++currentPage);
					new TownyInventory(resident, resident.getGUIPage(), title);
					playClickSound(player);
				}
				// if the pressed item was a previous page button
			} else if (clickedItem.getHoverName().getString().equals(Colors.Gold + "Back")) {
				// If the page number is more than 0 (So a previous page exists)
				if (resident.getGUIPageNum() > 0) {
					// Flip to previous page
					resident.setGUIPageNum(--currentPage);
					new TownyInventory(resident, resident.getGUIPage(), title);
					playClickSound(player);
				} else if (resident.getGUIPageNum() == 0) {
					// No page to go back from: go back to the SelectionGUI for the SelectionType
					// that the resident is currently browsing, let them choose a different plot type.
					playClickSound(player);
					ResidentUtil.openSelectionGUI(resident, resident.getGUISelectionType());
				}
			}
		} catch (Exception ignored) {}
	}
}
