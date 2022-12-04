package com.palmergames.forge;

import com.palmergames.bukkit.towny.Towny;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;

public class GuiChestMenu extends AbstractContainerMenu {

	private static final int SLOTS_PER_ROW = 9;
	private Container container;
	private int containerRows;

	public GuiChestMenu(int containerId, Inventory playerInventory) {
		this(Towny.GUI_CHEST_MENU.get(), containerId, playerInventory, 6);
	}

	@Override
	public void clicked(int p_150400_, int p_150401_, ClickType p_150402_, Player p_150403_) {
		//super.clicked(p_150400_, p_150401_, p_150402_, p_150403_);
		// don't do anything
	}

	public GuiChestMenu(MenuType<?> type, int id, Inventory inv, int rows) {
		super(type, id);
		doConstruct(type, id, inv, new GuiContainer(9 * rows), rows);
	}

	public GuiChestMenu(int id, Inventory inv, GuiContainer container, int rows) {
		super(Towny.GUI_CHEST_MENU.get(), id);
		doConstruct(Towny.GUI_CHEST_MENU.get(), id, inv, container, rows);
	}

	private void doConstruct(MenuType<?> type, int id, Inventory inv, GuiContainer guiContainer, int rows) {
		checkContainerSize(guiContainer, rows * 9);
		this.container = guiContainer;
		this.containerRows = rows;
		guiContainer.startOpen(inv.player);
		int i = (this.containerRows - 4) * 18;

		for (int j = 0; j < this.containerRows; ++j) {
			for (int k = 0; k < 9; ++k) {
				this.addSlot(new GuiSlot(guiContainer, k + j * 9, 8 + k * 18, 18 + j * 18));
			}
		}

		for (int l = 0; l < 3; ++l) {
			for (int j1 = 0; j1 < 9; ++j1) {
				this.addSlot(new GuiSlot(inv, j1 + l * 9 + 9, 8 + j1 * 18, 103 + l * 18 + i));
			}
		}

		for (int i1 = 0; i1 < 9; ++i1) {
			this.addSlot(new GuiSlot(inv, i1, 8 + i1 * 18, 161 + i));
		}
	}

	public static GuiChestMenu guiChest(int i, Inventory inventory) {
		return new GuiChestMenu(Towny.GUI_CHEST_MENU.get(), i, inventory, 6);
	}

	@Override
	public ItemStack quickMoveStack(Player p_38941_, int p_38942_) {
		return ItemStack.EMPTY;
	}

	@Override
	public boolean stillValid(Player p) {
		return this.container.stillValid(p);
	}

	public void removed(Player p_39251_) {
		super.removed(p_39251_);
		this.container.stopOpen(p_39251_);
	}

	public Container getContainer() {
		return this.container;
	}

	public int getRowCount() {
		return this.containerRows;
	}
}
