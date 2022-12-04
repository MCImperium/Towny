package com.palmergames.forge;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nullable;
import java.util.List;

public class ItemStackUtils {

	public static ItemStack setLore(ItemStack itemStack, @Nullable List<String> lore) {
		CompoundTag compoundtag = itemStack.getOrCreateTagElement("display");
			if (compoundtag.contains("Lore", 9)) {
				ListTag tag = compoundtag.getList("Lore", 8);
				int length = tag.size();
				for (int i = length; i < lore.size(); i++) {
					tag.addTag(length, StringTag.valueOf(lore.get(i)));
				}
			} else if (lore != null) {
				ListTag listtag = new ListTag();
				compoundtag.put("Lore", listtag);
			}
			else {
				compoundtag.remove("Lore");
			}

		return itemStack;
	}
}
