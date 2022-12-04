package com.palmergames.forge;

import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class GuiSlot extends Slot {
	public GuiSlot(Container container, int slot, int x, int y) {
		super(container, slot, x, y);
	}

	@Override
	public boolean mayPickup(Player p) {
		return false;
	}

	@Override
	public boolean mayPlace(ItemStack i) {
		return false;
	}
}
