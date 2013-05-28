package biomesoplenty.liquids;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.Icon;
import net.minecraftforge.fluids.Fluid;

public class SpringWaterFluid extends Fluid 
{
	public SpringWaterFluid(String fluidName) 
	{
		super(fluidName);
		
		this.stillIcon = BlockFluidSpringWater.springWaterStillIcon;
		this.flowingIcon = BlockFluidSpringWater.springWaterFlowingIcon;
	}	
}
