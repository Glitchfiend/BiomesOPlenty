package biomesoplenty.configuration;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.liquids.LiquidContainerData;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.liquids.LiquidStack;
import biomesoplenty.items.ItemBOPBucket;
import biomesoplenty.liquids.BlockSpringWaterFlowing;
import biomesoplenty.liquids.BlockSpringWaterStill;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class BOPLiquids 
{
	public static Block springWaterFlowing;
	public static Block springWaterStill;

	public static Item bucketSpringWater;

	public static LiquidStack springWaterLiquid;

	public static void init()
	{
		initializeLiquids();
		initializeContainers();
		registerLiquids();
		registerContainerNames();
		registerLiquidNames();
	}

	private static void initializeLiquids()
	{
		springWaterFlowing = (new BlockSpringWaterFlowing(BOPConfiguration.springWaterStillID - 1).setUnlocalizedName("springWaterFlowing"));
		springWaterStill = (new BlockSpringWaterStill(BOPConfiguration.springWaterStillID).setUnlocalizedName("springWaterStill"));	
		
		springWaterLiquid = LiquidDictionary.getOrCreateLiquid("Spring Water", new LiquidStack(springWaterStill, 1));
	}

	private static void initializeContainers()
	{
		bucketSpringWater = (new ItemBOPBucket(BOPConfiguration.springWaterBucketID, springWaterStill.blockID)).setMaxStackSize(1).setUnlocalizedName("bucketSpringWater").setContainerItem(Item.bucketEmpty);
		
		LiquidContainerRegistry.registerLiquid(new LiquidContainerData(LiquidDictionary.getLiquid("Spring Water", LiquidContainerRegistry.BUCKET_VOLUME), new ItemStack(bucketSpringWater), new ItemStack(Item.bucketEmpty)));
	}

	private static void registerLiquids()
	{
		GameRegistry.registerBlock(springWaterFlowing, "springWaterFlowing");
		GameRegistry.registerBlock(springWaterStill, "springWaterStill");
	}

	private static void registerContainerNames()
	{
		LanguageRegistry.addName(bucketSpringWater, "Spring Water Bucket");
	}

	private static void registerLiquidNames()
	{
		LanguageRegistry.addName(springWaterFlowing, "Spring Water");
		LanguageRegistry.addName(springWaterStill, "Spring Water");
	}
}
