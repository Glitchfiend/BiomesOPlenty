package biomesoplenty.configuration;

import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.liquids.LiquidContainerData;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.liquids.LiquidStack;
import biomesoplenty.api.Liquids;
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
		Liquids.liquidPoison = Optional.of(new BlockFluidLiquidPoison(BOPConfiguration.liquidPoisonStillID, Liquids.liquidPoisonFluid.get(), Material.water).setUnlocalizedName("bop.liquidPoison"));
		Liquids.liquidPoisonLiquidStack = Optional.of(LiquidDictionary.getOrCreateLiquid("Liquid Poison", new LiquidStack(Liquids.liquidPoisonFluid.get().getBlockID(), 1)));

		Liquids.springWater = Optional.of(new BlockFluidSpringWater(BOPConfiguration.springWaterStillID, Liquids.springWaterFluid.get(), Material.water).setUnlocalizedName("bop.springWater"));
		Liquids.springWaterLiquidStack = Optional.of(LiquidDictionary.getOrCreateLiquid("Spring Water", new LiquidStack(Liquids.springWaterFluid.get().getBlockID(), 1)));
	}

	private static void initializeContainers()
	{
		Liquids.bopBucket = Optional.of((new ItemBOPBucket(BOPConfiguration.bopBucketID).setMaxStackSize(1).setUnlocalizedName("bop.bopBucket").setContainerItem(Item.bucketEmpty)));

		//TODO: Remove upon Fluid API being integrated into Forge
		LiquidContainerRegistry.registerLiquid(new LiquidContainerData(LiquidDictionary.getLiquid("Liquid Poison", LiquidContainerRegistry.BUCKET_VOLUME), new ItemStack(Liquids.bopBucket.get(), 1, 1), new ItemStack(Item.bucketEmpty)));
		LiquidContainerRegistry.registerLiquid(new LiquidContainerData(LiquidDictionary.getLiquid("Spring Water", LiquidContainerRegistry.BUCKET_VOLUME), new ItemStack(Liquids.bopBucket.get(), 1, 0), new ItemStack(Item.bucketEmpty)));
		
		FluidContainerRegistry.registerFluidContainer(Liquids.liquidPoisonFluid.get(), new ItemStack(Liquids.bopBucket.get(), 1, 1), new ItemStack(Item.bucketEmpty));
		FluidContainerRegistry.registerFluidContainer(Liquids.springWaterFluid.get(), new ItemStack(Liquids.bopBucket.get(), 1, 0), new ItemStack(Item.bucketEmpty));
	}

	private static void registerBlocks()
	{
		GameRegistry.registerBlock(Liquids.liquidPoison.get(), "liquidPoison");
		GameRegistry.registerBlock(Liquids.springWater.get(), "springWater");
	}
}
