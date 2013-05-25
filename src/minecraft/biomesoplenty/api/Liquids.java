package biomesoplenty.api;

import com.google.common.base.Optional;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.liquids.LiquidStack;

public class Liquids 
{
    public static Optional<? extends Block> springWaterFlowing        = Optional.absent();
    public static Optional<? extends Block> springWaterStill          = Optional.absent();
    
    public static Optional<? extends Item> 	bucketSpringWater         = Optional.absent();
    
    public static Optional<? extends LiquidStack> springWaterLiquid   = Optional.absent();
}
