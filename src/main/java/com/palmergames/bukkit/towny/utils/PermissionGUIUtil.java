package com.palmergames.bukkit.towny.utils;

import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.TownyFormatter;
import com.palmergames.bukkit.towny.TownyMessaging;
import com.palmergames.bukkit.towny.TownyUniverse;
import com.palmergames.bukkit.towny.command.PlotCommand;
import com.palmergames.bukkit.towny.conversation.ResidentConversation;
import com.palmergames.bukkit.towny.exceptions.TownyException;
import com.palmergames.bukkit.towny.object.PermissionData;
import com.palmergames.bukkit.towny.object.PlotGroup;
import com.palmergames.bukkit.towny.object.Translatable;
import com.palmergames.bukkit.towny.object.WorldCoord;
import com.palmergames.bukkit.towny.object.gui.EditGUI;
import com.palmergames.bukkit.towny.object.gui.PermissionGUI;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.TownBlock;
import com.palmergames.bukkit.towny.object.TownyPermission.ActionType;
import com.palmergames.bukkit.towny.permissions.PermissionNodes;
import com.palmergames.bukkit.util.Colors;
import com.palmergames.forge.GuiContainer;
import com.palmergames.forge.ItemStackUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.WrittenBookItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.entity.player.Player;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

public class PermissionGUIUtil {
	private static final SetPermissionType[] defaultTypes = new SetPermissionType[]{SetPermissionType.UNSET, SetPermissionType.UNSET, SetPermissionType.UNSET, SetPermissionType.UNSET};
	private static final int[] woolSlots = new int[]{21, 23, 30, 32};
	
	public enum SetPermissionType {
		UNSET(Colors.Gray, Blocks.GRAY_WOOL),
		SET(Colors.Green, Blocks.LIME_WOOL),
		NEGATED(Colors.Red, Blocks.RED_WOOL);
		
		private String color;
		private Block woolColour;
		
		SetPermissionType(String color, Block woolColour) {
			this.color = color;
			this.woolColour = woolColour;
		}

		public String getColor() {
			return color;
		}

		public Block getWoolColour() {
			return woolColour;
		}
	}
	
	public static void openPermissionGUI(@NotNull Resident resident, @NotNull TownBlock townBlock) {
		boolean canEdit = true;
		try {
			PlotCommand.plotTestOwner(resident, townBlock);
		} catch (TownyException e) {
			canEdit = false;
		}
		
		GuiContainer page = ResidentUtil.getBlankPage(Translatable.of("permission_gui_header").forLocale(resident));
		ArrayList<GuiContainer> pages = new ArrayList<>();

		for (Entry<Resident, PermissionData> entry : townBlock.getPermissionOverrides().entrySet()) {
			ItemStack skull = new ItemStack(Items.PLAYER_HEAD);
			
			//if (!entry.getKey().hasUUID())
				//meta.setOwningPlayer(BukkitTools.getOfflinePlayer(entry.getKey().getName())); 
			//else
				//meta.setOwningPlayer(Bukkit.getOfflinePlayer(entry.getKey().getUUID()));

			skull.setHoverName(Component.literal(Colors.Gold + entry.getKey().getName()));

			List<String> lore = new ArrayList<>();
			lore.add(entry.getValue().getPermissionTypes()[ActionType.BUILD.getIndex()].getColor() + "Build" + Colors.Gray + "  | " + entry.getValue().getPermissionTypes()[ActionType.DESTROY.getIndex()].getColor() + "Destroy");
			lore.add(entry.getValue().getPermissionTypes()[ActionType.SWITCH.getIndex()].getColor() + "Switch" + Colors.Gray + " | " + entry.getValue().getPermissionTypes()[ActionType.ITEM_USE.getIndex()].getColor() + "Item");

			if (canEdit) {
				if (entry.getValue().getLastChangedAt() > 0 && !entry.getValue().getLastChangedBy().equals(""))
					lore.add(Translatable.of("msg_last_edited", TownyFormatter.lastOnlineFormat.format(entry.getValue().getLastChangedAt()), entry.getValue().getLastChangedBy()).forLocale(resident));
					
				lore.add(Translatable.of("msg_click_to_edit").forLocale(resident));
			}

			ItemStackUtils.setLore(skull, lore);
			
			if (page.firstEmpty() == 46) {
				pages.add(page);
				page = ResidentUtil.getBlankPage(Translatable.of("permission_gui_header").forLocale(resident));
			}

			page.addItem(skull);
		}
		
		if (canEdit) {
			ItemStack addButton = new ItemStack(Items.NAME_TAG);
			addButton.setHoverName(Component.literal(Colors.Gold + "Add Player"));

			page.setItem(46, addButton);
		}
		
		page.setItem(52, createTutorialBook());
		
		pages.add(page);
		resident.setGUIPages(pages);
		resident.setGUIPageNum(0);
		new PermissionGUI(resident, pages.get(0), Translatable.of("permission_gui_header").forLocale(resident), townBlock, canEdit);
	}
	
	public static void openPermissionEditorGUI(@NotNull Resident resident, @NotNull TownBlock townBlock, @NotNull ItemStack clickedItem) {
		GuiContainer container = new GuiContainer(54);
		// name = Component.translatable("permission_gui_header")
		
		Resident skullOwner = TownyAPI.getInstance().getResident(Colors.strip(clickedItem.getHoverName().getString()));

		container.setItem(4, clickedItem);
		
		SetPermissionType[] setPermissionTypes = townBlock.getPermissionOverrides().get(skullOwner).getPermissionTypes();
		for (ActionType actionType : ActionType.values()) {
			ItemStack wool = new ItemStack(setPermissionTypes[actionType.getIndex()].getWoolColour());
			wool.setHoverName(Component.literal(setPermissionTypes[actionType.getIndex()].getColor() + ChatFormatting.BOLD + actionType.getCommonName()));

			container.setItem(woolSlots[actionType.getIndex()], wool);
		}
		
		ItemStack saveButton = new ItemStack(Items.LIME_WOOL);
		saveButton.setHoverName(Component.literal(Colors.LightGreen + ChatFormatting.BOLD + "Save"));
		
		ItemStack backButton = new ItemStack(Items.RED_WOOL);
		backButton.setHoverName(Component.literal(Colors.Red + ChatFormatting.BOLD + "Back"));
		
		ItemStack deleteButton = new ItemStack(Items.RED_WOOL);
		//backButton.setHoverName(Component.literal(Colors.Red + ChatFormatting.BOLD + "Delete"));
		deleteButton.setHoverName(Component.literal(Colors.Red + ChatFormatting.BOLD + "Delete"));

		container.setItem(48, saveButton);
		container.setItem(50, backButton);
		container.setItem(53, deleteButton);
		
		new EditGUI(resident, container, Translatable.of("permission_gui_header").forLocale(resident), townBlock, skullOwner);
	}

	public static SetPermissionType[] getDefaultTypes() {
		return defaultTypes;
	}

	public static int[] getWoolSlots() {
		return woolSlots;
	}
	
	public static ItemStack createTutorialBook() {
		ItemStack book = new ItemStack(Items.WRITTEN_BOOK);
		book.getOrCreateTag();

		List<String> pages = new ArrayList<>();
		pages.add("    §lPlot Perm GUI§r\n\nUsing the GUI, you can give or remove permissions from individual players in your plots.\n\n§l  Getting Started§r\n\nTo start, you will need to add players to the GUI. You can do this using /plot perm add.");
		pages.add("After a player has been added, you can now start editing their permissions.\n\n§l    Permissions§r\n\nAfter you've clicked on a player head, you will be able to edit their permissions.§a Green§0 means that this player has this permission.");
		pages.add("§cRed§0 means that this player does not have this permission.\n\n§7Gray§0 means that normal plot permissions apply.\n\nWhen starting out, all permissions will be gray. Note that denying permissions will not work for plot owners or mayors.");

		ListTag pagesTag = new ListTag();
		for (int i = 0; i < pages.size(); i++) {
			pagesTag.addTag(i, StringTag.valueOf(pages.get(i)));
		}
		book.getTag().putString("title", "GUI Tutorial");
		book.getTag().putInt("generation", 0);
		book.addTagElement("pages", pagesTag);
		book.getTag().putString("author", "Warriorrr");
		
		return book;
	}
	
	public static void handleConversation(Player player) {
		TownBlock startingTownBlock = WorldCoord.parseWorldCoord(player).getTownBlockOrNull();
		if (startingTownBlock == null) {
			TownyMessaging.sendErrorMsg(player, Translatable.of("msg_not_claimed_1"));
			return;
		}
		
		new ResidentConversation(player).runOnResponse((res) -> {
			if (!TownyUniverse.getInstance().getPermissionSource().testPermission(player, PermissionNodes.TOWNY_COMMAND_PLOT_PERM_ADD.getNode())) {
				TownyMessaging.sendErrorMsg(player, Translatable.of("msg_err_command_disable"));
				return;
			}
			
			Resident resident = (Resident) res;
			if (startingTownBlock.hasPlotObjectGroup()) {
				PlotGroup group = startingTownBlock.getPlotObjectGroup();
					
				if (group.getPermissionOverrides().containsKey(resident)) {
					TownyMessaging.sendErrorMsg(player, Translatable.of("msg_overrides_already_set", resident.getName(), Translatable.of("plotgroup_sing")));
					return;
				}

				group.putPermissionOverride(resident, new PermissionData(PermissionGUIUtil.getDefaultTypes(), player.getName().getString()));
			} else {
				if (startingTownBlock.getPermissionOverrides().containsKey(resident)) {
					TownyMessaging.sendErrorMsg(player, Translatable.of("msg_overrides_already_set", resident.getName(), Translatable.of("townblock")));
					return;
				}

				startingTownBlock.getPermissionOverrides().put(resident, new PermissionData(PermissionGUIUtil.getDefaultTypes(), player.getName().getString()));
				startingTownBlock.save();
			}
			
			TownyMessaging.sendMsg(player, Translatable.of("msg_overrides_added", resident.getName()));
			PermissionGUIUtil.openPermissionGUI(TownyAPI.getInstance().getResident(player), startingTownBlock);
		});
	}
}
