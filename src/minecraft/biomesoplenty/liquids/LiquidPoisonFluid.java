package biomesoplenty.liquids;

import net.minecraftforge.fluids.Fluid;

public class LiquidPoisonFluid extends Fluid
{
	public LiquidPoisonFluid(String fluidName)
	{
		super(fluidName);

		stillIcon = BlockFluidLiquidPoison.liquidPoisonStillIcon;
		flowingIcon = BlockFluidLiquidPoison.liquidPoisonFlowingIcon;

		this.setViscosity(2500);
	}
}
