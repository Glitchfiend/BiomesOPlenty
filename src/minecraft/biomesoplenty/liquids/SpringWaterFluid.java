package biomesoplenty.liquids;

import net.minecraftforge.fluids.Fluid;

public class SpringWaterFluid extends Fluid
{
	public SpringWaterFluid(String fluidName)
	{
		super(fluidName);

		stillIcon = BlockFluidSpringWater.springWaterStillIcon;
		flowingIcon = BlockFluidSpringWater.springWaterFlowingIcon;
	}
}
