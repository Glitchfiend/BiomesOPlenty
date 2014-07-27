package biomesoplenty.common.core;

import static biomesoplenty.api.content.BOPCFluids.blood;
import static biomesoplenty.api.content.BOPCFluids.honey;
import static biomesoplenty.api.content.BOPCFluids.poison;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.common.fluids.BloodFluid;
import biomesoplenty.common.fluids.HoneyFluid;
import biomesoplenty.common.fluids.PoisonFluid;

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
		blood = registerFluid(new BloodFluid("blood"));
		honey = registerFluid(new HoneyFluid("honey"));
	}

	private static void registerFluidBlocks()
	{
		poison.setBlock(BOPCBlocks.poison);
		blood.setBlock(BOPCBlocks.blood);
		honey.setBlock(BOPCBlocks.honey);
	}
	
	public static Fluid registerFluid(Fluid fluid)
	{
		FluidRegistry.registerFluid(fluid);
		
		return fluid;
	}
}
