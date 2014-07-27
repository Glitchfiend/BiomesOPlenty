package biomesoplenty.common.fluids;

import net.minecraftforge.fluids.Fluid;
import biomesoplenty.common.fluids.blocks.BlockBloodFluid;

public class BloodFluid extends Fluid
{
	public BloodFluid(String fluidName) 
	{
		super(fluidName);

		this.setIcons(BlockBloodFluid.bloodStillIcon, BlockBloodFluid.bloodFlowingIcon);
	}
}
