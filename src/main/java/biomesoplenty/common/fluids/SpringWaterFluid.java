package biomesoplenty.common.fluids;

import net.minecraft.util.IIcon;
import net.minecraftforge.fluids.Fluid;
import biomesoplenty.common.fluids.blocks.BlockSpringWaterFluid;

public class SpringWaterFluid extends Fluid
{
	public SpringWaterFluid(String fluidName) 
	{
		super(fluidName);

		this.setIcons(BlockSpringWaterFluid.springWaterStillIcon, BlockSpringWaterFluid.springWaterFlowingIcon);
	}
}
