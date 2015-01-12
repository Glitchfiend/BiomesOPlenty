/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.init;

import biomesoplenty.common.world.WorldTypeBOP;

public class ModBiomes 
{
	public static WorldTypeBOP worldTypeBOP;
	
	public static void init()
	{
		worldTypeBOP = new WorldTypeBOP();
	}
}
