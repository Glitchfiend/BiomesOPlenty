package biomesoplenty.common.core;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import biomesoplenty.common.blocks.BlockAsh;
import biomesoplenty.common.blocks.BlockBOPColorizedSapling;
import biomesoplenty.common.blocks.BlockCloud;
import biomesoplenty.common.blocks.BlockMud;
import biomesoplenty.common.itemblocks.ItemBlockColorizedSapling;
import biomesoplenty.common.itemblocks.ItemBlockMud;
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
		registerBlock(new BlockMud().func_149663_c("mud"), ItemBlockMud.class);
		//TODO: Dried Dirt
		//TODO: Red Rock
		registerBlock(new BlockAsh().func_149663_c("ash"));
		
		registerBlock(new BlockCloud().func_149663_c("cloud"));
		
		registerBlock(new BlockBOPColorizedSapling().func_149663_c("colorizedSaplings"), ItemBlockColorizedSapling.class);
	}
	
	public static void registerBlock(Block block)
	{
		//TODO: 								getUnlocalizedName()
	    GameRegistry.registerBlock(block, block.func_149739_a().replace("tile.", ""));
	}
	
    public static void registerBlock(Block block, Class<? extends ItemBlock> itemBlockClass)
    {
		//TODO: 												getUnlocalizedName()
        GameRegistry.registerBlock(block, itemBlockClass, block.func_149739_a().replace("tile.", ""));
    }
}
