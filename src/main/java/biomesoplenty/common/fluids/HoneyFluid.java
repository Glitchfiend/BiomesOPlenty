package biomesoplenty.common.fluids;

import net.minecraftforge.fluids.Fluid;
import biomesoplenty.common.fluids.blocks.BlockPoisonFluid;

public class HoneyFluid extends Fluid
{
	public HoneyFluid(String fluidName)
	{
		super(fluidName);

		this.setViscosity(1500);
		
		this.setIcons(BlockPoisonFluid.liquidPoisonStillIcon, BlockPoisonFluid.liquidPoisonFlowingIcon);
	}  
}
