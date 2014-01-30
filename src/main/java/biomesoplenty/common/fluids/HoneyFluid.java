package biomesoplenty.common.fluids;

import biomesoplenty.common.fluids.blocks.BlockPoisonFluid;
import biomesoplenty.common.fluids.blocks.BlockSpringWaterFluid;
import net.minecraftforge.fluids.Fluid;

public class HoneyFluid extends Fluid
{
	public HoneyFluid(String fluidName)
	{
		super(fluidName);

		this.setViscosity(1500);
		
		this.setIcons(BlockPoisonFluid.liquidPoisonStillIcon, BlockPoisonFluid.liquidPoisonFlowingIcon);
	}  
}
