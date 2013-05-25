package biomesoplenty.configuration;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.liquids.LiquidStack;
import biomesoplenty.api.Blocks;
import biomesoplenty.items.ItemBOPMud;
import biomesoplenty.liquids.BlockSpringWaterFlowing;
import biomesoplenty.liquids.BlockSpringWaterStill;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class BOPLiquids {
	
	public static Block springWaterFlowing;
	public static Block springWaterStill;

	public static Item bucketSpringWater;

	public static LiquidStack springWaterLiquid;
	
	public static void init()
	{
		initializeLiquids();
		registerLiquids();
		registerNames();
	}
	
	private static void initializeLiquids()
	{
		springWaterFlowing = (new BlockSpringWaterFlowing(BOPConfiguration.springWaterStillID - 1).setUnlocalizedName("springWaterFlowing"));
		springWaterStill = (new BlockSpringWaterStill(BOPConfiguration.springWaterStillID).setUnlocalizedName("springWaterStill"));	
	}
	
	private static void registerLiquids()
	{
        GameRegistry.registerBlock(springWaterFlowing, "springWaterFlowing");
        GameRegistry.registerBlock(springWaterStill, "springWaterStill");
	}
	
	private static void registerNames()
	{
        LanguageRegistry.addName(springWaterFlowing, "Spring Water");
        LanguageRegistry.addName(springWaterStill, "Spring Water");
	}
}
