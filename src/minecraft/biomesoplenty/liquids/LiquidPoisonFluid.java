package biomesoplenty.liquids;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.Icon;
import net.minecraftforge.fluids.Fluid;

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
