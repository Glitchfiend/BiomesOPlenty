package biomesoplenty.common.fluids;

import net.minecraftforge.fluids.Fluid;
import biomesoplenty.common.fluids.blocks.BlockPoisonFluid;

public class PoisonFluid extends Fluid
{
	public PoisonFluid(String fluidName) 
	{
		super(fluidName);

		this.setIcons(BlockPoisonFluid.liquidPoisonStillIcon, BlockPoisonFluid.liquidPoisonFlowingIcon);
	}
}
