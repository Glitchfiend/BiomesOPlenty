package biomesoplenty.common.core;

import static biomesoplenty.common.core.BOPBlocks.registerBlock;
import static biomesoplenty.common.core.BOPItems.registerItem;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.fluids.HoneyFluid;
import biomesoplenty.common.fluids.PoisonFluid;
import biomesoplenty.common.fluids.SpringWaterFluid;
import biomesoplenty.common.fluids.blocks.BlockHoneyFluid;
import biomesoplenty.common.fluids.blocks.BlockPoisonFluid;
import biomesoplenty.common.fluids.blocks.BlockSpringWaterFluid;
import biomesoplenty.common.items.ItemBOPBucket;

public class BOPFluids 
{
	public static void init()
	{
		registerFluids();
		registerFluidBlocks();
		registerFluidItems();
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
		registerBlock(new BlockPoisonFluid().setBlockName("poison"));
		registerBlock(new BlockSpringWaterFluid().setBlockName("springWater"));
		registerBlock(new BlockHoneyFluid().setBlockName("honey"));
	}

	private static void registerFluidItems()
	{
		registerItem(new ItemBOPBucket().setUnlocalizedName("bopBucket"));
	}
	
	public static void registerFluid(Fluid fluid)
	{
		FluidRegistry.registerFluid(fluid);
	}
}
