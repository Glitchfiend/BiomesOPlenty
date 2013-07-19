package biomesoplenty.fluids;

import net.minecraft.util.Icon;
import net.minecraftforge.fluids.Fluid;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class LiquidPoisonFluid extends Fluid
{
	public LiquidPoisonFluid(String fluidName)
	{
		super(fluidName);

		this.setViscosity(2500);
		System.out.println(this.getLocalizedName());
	}
	
    @Override
	@SideOnly(Side.CLIENT)
    public Icon getStillIcon() 
    {
        return BlockFluidLiquidPoison.liquidPoisonStillIcon;
    }

    @Override
	@SideOnly(Side.CLIENT)
    public Icon getFlowingIcon() 
    {
        return BlockFluidLiquidPoison.liquidPoisonFlowingIcon;
    }	
}
