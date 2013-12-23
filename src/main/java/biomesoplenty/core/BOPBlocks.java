package biomesoplenty.core;

import biomesoplenty.blocks.BlockMud;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import cpw.mods.fml.common.registry.GameRegistry;

public class BOPBlocks 
{
	public static void init()
	{
		initializeBlocks();
	}

	private static void initializeBlocks()
	{
		// Block declaration
		//TODO:						setBlockName
		registerBlock(new BlockMud().func_149663_c("mud"));
	}
	
	public static void registerBlock(Block block)
	{
		//TODO: 								getUnlocalizedName()
	    GameRegistry.registerBlock(block, block.func_149739_a().replace("tile.", ""));
	}
}
