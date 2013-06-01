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

		this.setViscosity(2500);
	}
	
    @SideOnly(Side.CLIENT)
    public Icon getStillIcon() {

        return BlockFluidLiquidPoison.liquidPoisonStillIcon;
    }

    @SideOnly(Side.CLIENT)
    public Icon getFlowingIcon() {

        return BlockFluidLiquidPoison.liquidPoisonFlowingIcon;
    }	
}
