package com.palmergames.bukkit.util;

import net.minecraft.core.BlockPos;

/**
 * @author Chris H (Zren / Shade)
 *         Date: 4/15/12
 * @deprecated Deprecated as of 0.98.3.13, please use a {@link java.util.function.Consumer} instead.
 */
@Deprecated
public interface BlockPosRunnable {

	void run(BlockPos loc);
}
