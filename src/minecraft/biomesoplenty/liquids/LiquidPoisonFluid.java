package biomesoplenty.liquids;

import biomesoplenty.ftfluidsapi.Fluid;

public class LiquidPoisonFluid extends Fluid 
{
	public LiquidPoisonFluid(String fluidName) 
	{
		super(fluidName);
		
		this.stillIcon = BlockFluidLiquidPoison.liquidPoisonStillIcon;
		this.flowingIcon = BlockFluidLiquidPoison.liquidPoisonFlowingIcon;
		
		this.setViscosity(2500);
	}	
}
