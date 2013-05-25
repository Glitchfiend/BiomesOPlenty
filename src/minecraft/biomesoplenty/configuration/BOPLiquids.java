package biomesoplenty.configuration;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.liquids.LiquidContainerData;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.liquids.LiquidStack;
import biomesoplenty.api.Liquids;
import biomesoplenty.items.ItemBOPBucket;
import biomesoplenty.liquids.BlockLiquidPoisonFlowing;
import biomesoplenty.liquids.BlockLiquidPoisonStill;
import biomesoplenty.liquids.BlockSpringWaterFlowing;
import biomesoplenty.liquids.BlockSpringWaterStill;

import com.google.common.base.Optional;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class BOPLiquids 
{
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
		Liquids.springWaterFlowing = Optional.of(new BlockSpringWaterFlowing(BOPConfiguration.springWaterStillID - 1).setUnlocalizedName("springWaterFlowing"));
		Liquids.springWaterStill = Optional.of(new BlockSpringWaterStill(BOPConfiguration.springWaterStillID).setUnlocalizedName("springWaterStill"));	
		Liquids.liquidPoisonFlowing = Optional.of(new BlockLiquidPoisonFlowing(BOPConfiguration.liquidPoisonStillID - 1).setUnlocalizedName("liquidPoisonFlowing"));
		Liquids.liquidPoisonStill = Optional.of(new BlockLiquidPoisonStill(BOPConfiguration.liquidPoisonStillID).setUnlocalizedName("liquidPoisonStill"));	
		
		Liquids.springWaterLiquid = Optional.of(LiquidDictionary.getOrCreateLiquid("Spring Water", new LiquidStack(Liquids.springWaterStill.get(), 1)));
		Liquids.liquidPoisonLiquid = Optional.of(LiquidDictionary.getOrCreateLiquid("Liquid Poison", new LiquidStack(Liquids.liquidPoisonStill.get(), 1)));
	}

	private static void initializeContainers()
	{
		Liquids.bopBucket = Optional.of((new ItemBOPBucket(BOPConfiguration.bopBucketID).setMaxStackSize(1).setUnlocalizedName("bopBucket").setContainerItem(Item.bucketEmpty)));
		
		LiquidContainerRegistry.registerLiquid(new LiquidContainerData(LiquidDictionary.getLiquid("Spring Water", LiquidContainerRegistry.BUCKET_VOLUME), new ItemStack(Liquids.bopBucket.get(), 1, 0), new ItemStack(Item.bucketEmpty)));
		LiquidContainerRegistry.registerLiquid(new LiquidContainerData(LiquidDictionary.getLiquid("Liquid Poison", LiquidContainerRegistry.BUCKET_VOLUME), new ItemStack(Liquids.bopBucket.get(), 1, 1), new ItemStack(Item.bucketEmpty)));
	}

	private static void registerLiquids()
	{
		GameRegistry.registerBlock(Liquids.springWaterFlowing.get(), "springWaterFlowing");
		GameRegistry.registerBlock(Liquids.springWaterStill.get(), "springWaterStill");
		
		GameRegistry.registerBlock(Liquids.liquidPoisonFlowing.get(), "liquidPoisonFlowing");
		GameRegistry.registerBlock(Liquids.liquidPoisonStill.get(), "liquidPoisonStill");
	}

	private static void registerContainerNames()
	{
		LanguageRegistry.addName(new ItemStack(Liquids.bopBucket.get(), 1, 0), "Spring Water Bucket");
		LanguageRegistry.addName(new ItemStack(Liquids.bopBucket.get(), 1, 1), "Liquid Poison Bucket");
	}

	private static void registerLiquidNames()
	{
		LanguageRegistry.addName(Liquids.springWaterFlowing.get(), "Spring Water");
		LanguageRegistry.addName(Liquids.springWaterStill.get(), "Spring Water");
		
		LanguageRegistry.addName(Liquids.liquidPoisonFlowing.get(), "Liquid Poison");
		LanguageRegistry.addName(Liquids.liquidPoisonStill.get(), "Liquid Poison");
	}
}
