package biomesoplenty.liquids;

import biomesoplenty.ftfluidsapi.Fluid;

public class SpringWaterFluid extends Fluid 
{
	public SpringWaterFluid(String fluidName) 
	{
		super(fluidName);
		
		this.stillIcon = BlockFluidSpringWater.springWaterStillIcon;
		this.flowingIcon = BlockFluidSpringWater.springWaterFlowingIcon;
	}	
}
