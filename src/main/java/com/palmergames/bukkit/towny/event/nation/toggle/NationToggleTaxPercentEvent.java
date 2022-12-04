package com.palmergames.bukkit.towny.event.nation.toggle;

import org.bukkit.command.Player;
import com.palmergames.bukkit.towny.object.Nation;

public class NationToggleTaxPercentEvent extends NationToggleStateEvent {

    public NationToggleTaxPercentEvent(Player sender, Nation nation, boolean admin, boolean newState) {
        super(sender, nation, admin, nation.isTaxPercentage(), newState);
    }
}