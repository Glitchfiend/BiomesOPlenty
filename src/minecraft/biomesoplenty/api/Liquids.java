package biomesoplenty.api;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.liquids.LiquidStack;

import com.google.common.base.Optional;

public class Liquids
{
	public static Optional<? extends Item> 	bopBucket         		        = Optional.absent();

	public static Optional<? extends Block> springWater                     = Optional.absent();
	public static Optional<? extends Block> liquidPoison                    = Optional.absent();

	public static Optional<? extends Fluid> springWaterFluid                = Optional.absent();
	public static Optional<? extends Fluid> liquidPoisonFluid               = Optional.absent();

	public static Optional<? extends LiquidStack> springWaterLiquidStack    = Optional.absent();
	public static Optional<? extends LiquidStack> liquidPoisonLiquidStack   = Optional.absent();
}
