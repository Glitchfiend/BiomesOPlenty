package biomesoplenty.common.core;

import static biomesoplenty.api.content.BOPCFluids.*;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.common.fluids.HoneyFluid;
import biomesoplenty.common.fluids.PoisonFluid;
import biomesoplenty.common.fluids.SpringWaterFluid;

public class BOPFluids 
{
	public static void preInit()
	{
		registerFluids();
	}
	
	public static void init()
	{
		registerFluidBlocks();
	}

	private static void registerFluids()
	{
		poison = registerFluid(new PoisonFluid("poison"));
		spring_water = registerFluid(new SpringWaterFluid("spring_water"));
		honey = registerFluid(new HoneyFluid("honey"));
	}

	private static void registerFluidBlocks()
	{
		poison.setBlock(BOPCBlocks.poison);
		spring_water.setBlock(BOPCBlocks.springWater);
		honey.setBlock(BOPCBlocks.honey);
	}
	
	public static Fluid registerFluid(Fluid fluid)
	{
		FluidRegistry.registerFluid(fluid);
		
		return fluid;
	}
}
