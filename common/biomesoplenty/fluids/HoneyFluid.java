package biomesoplenty.fluids;

import net.minecraft.util.Icon;
import net.minecraftforge.fluids.Fluid;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class HoneyFluid extends Fluid
{
	public HoneyFluid(String fluidName)
	{
		super(fluidName);

		this.setViscosity(1500);
	}
	
    @Override
	@SideOnly(Side.CLIENT)
    public Icon getStillIcon() 
    {
        return BlockFluidHoney.honeyStillIcon;
    }

    @Override
	@SideOnly(Side.CLIENT)
    public Icon getFlowingIcon() 
    {
        return BlockFluidHoney.honeyFlowingIcon;
    }	
}
