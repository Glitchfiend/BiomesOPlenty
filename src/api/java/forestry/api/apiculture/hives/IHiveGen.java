/*******************************************************************************
 * Copyright 2011-2014 SirSengir
 *
 * This work (the API) is licensed under the "MIT" License, see LICENSE.txt for details.
 ******************************************************************************/
package forestry.api.apiculture.hives;

import net.minecraft.world.World;

public interface IHiveGen {

	/**
	 * return a Y value that the hive should try to generate at.
	 * returns negative if the hive can't be placed anywhere.
	 */
	int getYForHive(World world, int x, int z);

	/**
	 * returns true if the hive can be generated at this location.
	 * Used for advanced conditions, like checking that the ground below the hive is a certain type.
	 */
	boolean isValidLocation(World world, int x, int y, int z);

	/**
	 * returns true if the hive can safely replace the block at this location.
	 */
	boolean canReplace(World world, int x, int y, int z);

}
