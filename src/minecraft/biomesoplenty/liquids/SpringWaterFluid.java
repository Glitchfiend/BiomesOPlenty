package biomesoplenty.liquids;

import net.minecraft.util.Icon;
import net.minecraftforge.fluids.Fluid;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class SpringWaterFluid extends Fluid
{
	public SpringWaterFluid(String fluidName)
	{
		super(fluidName);
	}
	
    @Override
	@SideOnly(Side.CLIENT)
    public Icon getStillIcon() 
    {
        return BlockFluidSpringWater.springWaterStillIcon;
    }

    @Override
	@SideOnly(Side.CLIENT)
    public Icon getFlowingIcon() 
    {
        return BlockFluidSpringWater.springWaterFlowingIcon;
    }	
}
