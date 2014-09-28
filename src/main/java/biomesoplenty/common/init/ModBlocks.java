/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.init;

import static biomesoplenty.api.block.BOPBlocks.ash_block;
import static biomesoplenty.api.block.BOPBlocks.planks;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.IStringSerializable;
import biomesoplenty.api.block.BOPBlock;
import biomesoplenty.client.util.ModelHelper;
import biomesoplenty.common.block.BOPBlockPlanks;
import biomesoplenty.common.block.BlockAsh;
import biomesoplenty.common.item.ItemBlockWithVariants;
import biomesoplenty.common.util.RegistryUtil;
import biomesoplenty.core.BiomesOPlenty;

import com.google.common.collect.ImmutableList;

public class ModBlocks
{
	public static void init()
	{
		ash_block = registerBlock(new BlockAsh(), "ash_block");
		planks = registerBlock(new BOPBlockPlanks(), "planks");
	}
	
	private static Block registerBlock(BOPBlock block, String name)
	{
		block.setUnlocalizedName(name);

		if (block.hasVariants())
		{
			RegistryUtil.registerBlock(block, ItemBlockWithVariants.class, name);
			
			for (Enum variant : block.getVariants())
			{
				String variantName = ((IStringSerializable)variant).getName() + "_" + name;
				
				ModelHelper.addVariantName(Item.getItemFromBlock(block), BiomesOPlenty.MOD_ID + ":" + variantName);
				BiomesOPlenty.proxy.registerBlockForMeshing(block, variant.ordinal(), variantName);
			}
		}
		else
		{
			RegistryUtil.registerBlock(block, name);

			ModelHelper.addVariantName(Item.getItemFromBlock(block), BiomesOPlenty.MOD_ID + ":" + name);
			BiomesOPlenty.proxy.registerBlockForMeshing(block, 0, name);
		}
		
		return block;
	}
}
