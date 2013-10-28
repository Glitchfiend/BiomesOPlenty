package biomesoplenty.configuration;

import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import biomesoplenty.api.Fluids;
import biomesoplenty.configuration.configfile.BOPConfigurationIDs;
import biomesoplenty.fluids.BlockFluidHoney;
import biomesoplenty.fluids.BlockFluidLiquidPoison;
import biomesoplenty.fluids.BlockFluidSpringWater;
import biomesoplenty.fluids.HoneyFluid;
import biomesoplenty.fluids.LiquidPoisonFluid;
import biomesoplenty.fluids.SpringWaterFluid;
import biomesoplenty.items.ItemBOPBucket;

import com.google.common.base.Optional;

import cpw.mods.fml.common.registry.GameRegistry;

public class BOPFluids
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
		Fluids.liquidPoisonFluid = Optional.of(new LiquidPoisonFluid("bop.liquidPoison").setBlockID(BOPConfigurationIDs.liquidPoisonStillID));
		FluidRegistry.registerFluid(Fluids.liquidPoisonFluid.get());

		Fluids.springWaterFluid = Optional.of(new SpringWaterFluid("bop.springWater").setBlockID(BOPConfigurationIDs.springWaterStillID));
		FluidRegistry.registerFluid(Fluids.springWaterFluid.get());
		
		Fluids.honeyFluid = Optional.of(new HoneyFluid("bop.honey").setBlockID(BOPConfigurationIDs.honeyStillID));
		FluidRegistry.registerFluid(Fluids.honeyFluid.get());
	}

	private static void initializeLiquids()
	{
		Fluids.liquidPoison = Optional.of(new BlockFluidLiquidPoison(BOPConfigurationIDs.liquidPoisonStillID, Fluids.liquidPoisonFluid.get(), Material.water).setUnlocalizedName("bop.liquidPoison"));

		Fluids.springWater = Optional.of(new BlockFluidSpringWater(BOPConfigurationIDs.springWaterStillID, Fluids.springWaterFluid.get(), Material.water).setUnlocalizedName("bop.springWater"));
		
		Fluids.honey = Optional.of(new BlockFluidHoney(BOPConfigurationIDs.honeyStillID, Fluids.honeyFluid.get(), Material.water).setUnlocalizedName("bop.honey"));
	}

	private static void initializeContainers()
	{
		Fluids.bopBucket = Optional.of((new ItemBOPBucket(BOPConfigurationIDs.bopBucketID).setMaxStackSize(1).setUnlocalizedName("bop.bopBucket")));
		
		FluidContainerRegistry.registerFluidContainer(Fluids.liquidPoisonFluid.get(), new ItemStack(Fluids.bopBucket.get(), 1, 1), new ItemStack(Item.bucketEmpty));
		FluidContainerRegistry.registerFluidContainer(Fluids.honeyFluid.get(), new ItemStack(Fluids.bopBucket.get(), 1, 3), new ItemStack(Item.bucketEmpty));
		FluidContainerRegistry.registerFluidContainer(Fluids.springWaterFluid.get(), new ItemStack(Fluids.bopBucket.get(), 1, 2), new ItemStack(Fluids.bopBucket.get(), 1, 0));
	}

	private static void registerBlocks()
	{
		GameRegistry.registerBlock(Fluids.liquidPoison.get(), "liquidPoison");
		GameRegistry.registerBlock(Fluids.springWater.get(), "springWater");
		GameRegistry.registerBlock(Fluids.honey.get(), "honey");
	}
}
