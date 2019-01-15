/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.world.gen.feature;

import biomesoplenty.api.block.BOPBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class BOPBiomeFeatures
{
	public static final SurfaceBuilderConfig LOAMY_GRASS_DIRT_GRAVEL_SURFACE = new SurfaceBuilderConfig(BOPBlocks.loamy_grass_block.getDefaultState(), BOPBlocks.loamy_dirt.getDefaultState(), Blocks.GRAVEL.getDefaultState());	
}
