/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.init;

import static biomesoplenty.api.block.BOPBlocks.ash_block;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import biomesoplenty.common.block.BlockAsh;
import biomesoplenty.common.util.RegistryUtil;
import biomesoplenty.core.BiomesOPlenty;

import com.google.common.collect.ImmutableList;

public class ModBlocks
{
	public static void init()
	{
		ash_block = registerBlock(new BlockAsh(), "ash_block");
	}
	
	private static Block registerBlock(Block block, String name)
	{
		block.setUnlocalizedName(name);
		block = RegistryUtil.registerBlock(block, name);
		
		for (IBlockState state : (ImmutableList<IBlockState>)block.getBlockState().getValidStates())
		{
			BiomesOPlenty.proxy.registerBlockForMeshing(block, block.getMetaFromState(state), name);
		}
		
		return block;
	}
}
