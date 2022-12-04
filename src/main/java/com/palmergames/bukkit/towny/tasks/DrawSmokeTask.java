package com.palmergames.bukkit.towny.tasks;

import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.object.Resident;
import org.bukkit.Bukkit;
import net.minecraft.world.entity.player.Player;

import com.palmergames.bukkit.towny.Towny;
import com.palmergames.bukkit.towny.object.CellBorder;
import com.palmergames.bukkit.towny.object.Coord;
import com.palmergames.bukkit.towny.object.WorldCoord;
import com.palmergames.bukkit.towny.utils.BorderUtil;
import com.palmergames.bukkit.util.DrawSmokeTaskFactory;

public class DrawSmokeTask extends TownyTimerTask {

	public DrawSmokeTask(Towny plugin) {
		super(plugin);
	}

	@Override
	public void run() {
		for (Player player : Bukkit.getOnlinePlayers()) {
			Resident resident = TownyAPI.getInstance().getResident(player);
			if (resident == null || !resident.hasMode("constantplotborder"))
				continue;

			WorldCoord wc = new WorldCoord(player.getWorld().getName(), Coord.parseCoord(player.getBlockPos()));
			CellBorder cellBorder = BorderUtil.getPlotBorder(wc);
			
			cellBorder.runBorderedOnSurface(1, 2, DrawSmokeTaskFactory.showToPlayer(player, DrawSmokeTaskFactory.getAffiliationColor(resident, wc)));
		}
	}
}
