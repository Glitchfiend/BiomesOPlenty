/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.init;

import static biomesoplenty.api.block.BOPBlocks.*;
import net.minecraft.block.Block;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import biomesoplenty.api.block.BOPBlock;
import biomesoplenty.api.block.IBOPVariant;
import biomesoplenty.client.util.ModelHelper;
import biomesoplenty.common.block.BOPBlockPlanks;
import biomesoplenty.common.block.BlockAsh;
import biomesoplenty.common.block.BlockBOPFlower;
import biomesoplenty.common.block.BlockBOPFlower2;
import biomesoplenty.common.block.BlockBOPLog;
import biomesoplenty.common.block.BlockBOPLog2;
import biomesoplenty.common.block.BlockBOPLog3;
import biomesoplenty.common.block.BlockBOPLog4;
import biomesoplenty.common.item.ItemBlockWithVariants;
import biomesoplenty.core.BiomesOPlenty;

public class ModBlocks
{
	public static void init()
	{
		ash_block = registerBlock(new BlockAsh(), "ash_block");
		flower = registerBlock(new BlockBOPFlower(), "flower");
		flower2 = registerBlock(new BlockBOPFlower2(), "flower2");
		log = registerBlock(new BlockBOPLog(), "log");
		log2 = registerBlock(new BlockBOPLog2(), "log2");
		log3 = registerBlock(new BlockBOPLog3(), "log3");
		log4 = registerBlock(new BlockBOPLog4(), "log4");
		planks = registerBlock(new BOPBlockPlanks(), "planks");
	}
	
	private static Block registerBlock(BOPBlock block, String name)
	{
		block.setUnlocalizedName(name);

		if (block.hasVariants())
		{
			GameRegistry.registerBlock(block, ItemBlockWithVariants.class, name);
			
			for (IBOPVariant variant : block.getVariants())
			{
				String variantName = variant.getName() + (variant.getBaseName() != null ? "_" + variant.getBaseName() : "");
				
				ModelBakery.addVariantName(Item.getItemFromBlock(block), BiomesOPlenty.MOD_ID + ":" + variantName);
				BiomesOPlenty.proxy.registerBlockForMeshing(block, variant.getDefaultMetadata(), variantName);
			}
		}
		else
		{
			GameRegistry.registerBlock(block, name);

			ModelBakery.addVariantName(Item.getItemFromBlock(block), BiomesOPlenty.MOD_ID + ":" + name);
			BiomesOPlenty.proxy.registerBlockForMeshing(block, 0, name);
		}
		
		return block;
	}
}
