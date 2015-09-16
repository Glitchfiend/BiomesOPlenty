/*******************************************************************************
 * Copyright 2011-2014 SirSengir
 *
 * This work (the API) is licensed under the "MIT" License, see LICENSE.txt for details.
 ******************************************************************************/
package forestry.api.apiculture.hives;

import net.minecraft.block.Block;

public interface IHiveGenHelper {

	/**
	 * Returns a hiveGen for a hive that spawns on the ground.
	 * validGroundBlocks specifies which block materials it can spawn on.
	 */
	IHiveGen ground(Block... validGroundBlocks);

	/**
	 * Returns a hiveGen for a hive that spawns in trees.
	 */
	IHiveGen tree();

}
