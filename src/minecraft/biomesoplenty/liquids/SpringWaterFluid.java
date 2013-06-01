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
	}
	
    @SideOnly(Side.CLIENT)
    public Icon getStillIcon() {

        return BlockFluidSpringWater.springWaterStillIcon;
    }

    @SideOnly(Side.CLIENT)
    public Icon getFlowingIcon() {

        return BlockFluidSpringWater.springWaterFlowingIcon;
    }	
}
