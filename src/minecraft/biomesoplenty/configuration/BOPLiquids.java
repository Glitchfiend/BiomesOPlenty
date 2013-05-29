package biomesoplenty.configuration;

import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.liquids.LiquidStack;
import biomesoplenty.api.Liquids;
import biomesoplenty.ftfluidsapi.FluidContainerRegistry;
import biomesoplenty.ftfluidsapi.FluidRegistry;
import biomesoplenty.items.ItemBOPBucket;
import biomesoplenty.liquids.BlockFluidLiquidPoison;
import biomesoplenty.liquids.BlockFluidSpringWater;
import biomesoplenty.liquids.LiquidPoisonFluid;
import biomesoplenty.liquids.SpringWaterFluid;

import com.google.common.base.Optional;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class BOPLiquids 
{	
	public static void init()
	{
		registerFluids();
		initializeLiquids();
		initializeContainers();
		registerBlocks();
		registerContainerNames();
		registerLiquidNames();
	}
	
	private static void registerFluids()
	{
		Liquids.liquidPoisonFluid = Optional.of(new LiquidPoisonFluid("Liquid Poison").setBlockID(BOPConfiguration.liquidPoisonStillID));
		
		FluidRegistry.registerFluid(Liquids.liquidPoisonFluid.get());
		
		Liquids.springWaterFluid = Optional.of(new SpringWaterFluid("Spring Water").setBlockID(BOPConfiguration.springWaterStillID));
		
		FluidRegistry.registerFluid(Liquids.springWaterFluid.get());
	}

	private static void initializeLiquids()
	{
		Liquids.liquidPoison = Optional.of(new BlockFluidLiquidPoison(BOPConfiguration.liquidPoisonStillID, Liquids.liquidPoisonFluid.get(), Material.water).setUnlocalizedName("Liquid Poison"));
		
		Liquids.liquidPoisonLiquidStack = Optional.of(LiquidDictionary.getOrCreateLiquid("Liquid Poison", new LiquidStack(Liquids.liquidPoisonFluid.get().getBlockID(), 1)));
		
		Liquids.springWater = Optional.of(new BlockFluidSpringWater(BOPConfiguration.springWaterStillID, Liquids.springWaterFluid.get(), Material.water).setUnlocalizedName("Spring Water"));
		
		Liquids.springWaterLiquidStack = Optional.of(LiquidDictionary.getOrCreateLiquid("Spring Water", new LiquidStack(Liquids.springWaterFluid.get().getBlockID(), 1)));
	}

	private static void initializeContainers()
	{
		Liquids.bopBucket = Optional.of((new ItemBOPBucket(BOPConfiguration.bopBucketID).setMaxStackSize(1).setUnlocalizedName("bopBucket").setContainerItem(Item.bucketEmpty)));
		
		FluidContainerRegistry.registerFluidContainer(Liquids.springWaterFluid.get(), new ItemStack(Liquids.bopBucket.get(), 1, 0), new ItemStack(Item.bucketEmpty));
		FluidContainerRegistry.registerFluidContainer(Liquids.liquidPoisonFluid.get(), new ItemStack(Liquids.bopBucket.get(), 1, 1), new ItemStack(Item.bucketEmpty));
	}

	private static void registerBlocks()
	{
		GameRegistry.registerBlock(Liquids.springWater.get(), "springWater");		
		GameRegistry.registerBlock(Liquids.liquidPoison.get(), "liquidPoison");
	}

	private static void registerContainerNames()
	{
		LanguageRegistry.addName(new ItemStack(Liquids.bopBucket.get(), 1, 0), "Spring Water Bucket");
		LanguageRegistry.addName(new ItemStack(Liquids.bopBucket.get(), 1, 1), "Liquid Poison Bucket");
	}

	private static void registerLiquidNames()
	{
		LanguageRegistry.addName(Liquids.springWater.get(), "Spring Water");	
		LanguageRegistry.addName(Liquids.liquidPoison.get(), "Liquid Poison");
	}
}
