package biomesoplenty.common.core;

import static biomesoplenty.common.core.BOPBlocks.registerBlock;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.fluids.HoneyFluid;
import biomesoplenty.common.fluids.PoisonFluid;
import biomesoplenty.common.fluids.SpringWaterFluid;
import biomesoplenty.common.fluids.blocks.BlockHoneyFluid;
import biomesoplenty.common.fluids.blocks.BlockPoisonFluid;
import biomesoplenty.common.fluids.blocks.BlockSpringWaterFluid;

public class BOPFluids 
{
	public static void init()
	{
		registerFluids();
		registerFluidBlocks();
		//initializeContainers();
		//registerItems();
	}

	private static void registerFluids()
	{
		registerFluid(new PoisonFluid("poison").setBlock(BOPBlockHelper.get("poison")));
		registerFluid(new SpringWaterFluid("spring_water").setBlock(BOPBlockHelper.get("springWater")));
		registerFluid(new HoneyFluid("honey").setBlock(BOPBlockHelper.get("honey")));
	}

	private static void registerFluidBlocks()
	{
		//TODO:						setBlockName
		registerBlock(new BlockPoisonFluid().func_149663_c("poison"));
		registerBlock(new BlockSpringWaterFluid().func_149663_c("springWater"));
		registerBlock(new BlockHoneyFluid().func_149663_c("honey"));
	}

	/*private static void initializeContainers()
	{
		Fluids.bopBucket = Optional.of((new ItemBOPBucket(BOPConfigurationIDs.bopBucketID).setMaxStackSize(1).setUnlocalizedName("bop.bopBucket")));

		FluidContainerRegistry.registerFluidContainer(Fluids.liquidPoisonFluid.get(), new ItemStack(Fluids.bopBucket.get(), 1, 1), new ItemStack(Item.bucketEmpty));
		FluidContainerRegistry.registerFluidContainer(Fluids.honeyFluid.get(), new ItemStack(Fluids.bopBucket.get(), 1, 3), new ItemStack(Item.bucketEmpty));
		FluidContainerRegistry.registerFluidContainer(Fluids.springWaterFluid.get(), new ItemStack(Fluids.bopBucket.get(), 1, 2), new ItemStack(Fluids.bopBucket.get(), 1, 0));
	}

	private static void registerItems()
	{
		registerItem(Fluids.bopBucket.get());
	}*/
	
	public static void registerFluid(Fluid fluid)
	{
		FluidRegistry.registerFluid(fluid);
	}
}
