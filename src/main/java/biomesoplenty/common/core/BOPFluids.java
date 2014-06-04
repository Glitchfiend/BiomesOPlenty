package biomesoplenty.common.core;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.common.fluids.HoneyFluid;
import biomesoplenty.common.fluids.PoisonFluid;
import biomesoplenty.common.fluids.SpringWaterFluid;

public class BOPFluids 
{
	public static Fluid poison;
	public static Fluid spring_water;
	public static Fluid honey;
	
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
		registerFluid(poison = new PoisonFluid("poison"));
		registerFluid(spring_water = new SpringWaterFluid("spring_water"));
		registerFluid(honey = new HoneyFluid("honey"));
	}

	private static void registerFluidBlocks()
	{
		poison.setBlock(BOPCBlocks.poison);
		spring_water.setBlock(BOPCBlocks.springWater);
		honey.setBlock(BOPCBlocks.honey);
	}
	
	public static void registerFluid(Fluid fluid)
	{
		FluidRegistry.registerFluid(fluid);
	}
}
