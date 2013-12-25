package biomesoplenty.common.core;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import biomesoplenty.common.blocks.BlockAsh;
import biomesoplenty.common.blocks.BlockBOPColorizedSapling;
import biomesoplenty.common.blocks.BlockBOPLog;
import biomesoplenty.common.blocks.BlockBOPLog.LogCategory;
import biomesoplenty.common.blocks.BlockCloud;
import biomesoplenty.common.blocks.BlockHoney;
import biomesoplenty.common.blocks.BlockMud;
import biomesoplenty.common.itemblocks.ItemBlockColorizedSapling;
import biomesoplenty.common.itemblocks.ItemBlockLog;
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
		
		registerBlock(new BlockHoney().func_149663_c("honeyBlock"));
		
		registerBlock(new BlockBOPLog(LogCategory.CAT1).func_149663_c("logs1"), ItemBlockLog.class);
		registerBlock(new BlockBOPLog(LogCategory.CAT2).func_149663_c("logs2"), ItemBlockLog.class);
		registerBlock(new BlockBOPLog(LogCategory.CAT3).func_149663_c("logs3"), ItemBlockLog.class);
		registerBlock(new BlockBOPLog(LogCategory.CAT4).func_149663_c("logs4"), ItemBlockLog.class);
		
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
