/*******************************************************************************
 * Copyright 2024, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.forge.mixin;

import biomesoplenty.block.LiquidNullFluid;
import biomesoplenty.forge.init.ModFluidTypes;
import net.minecraft.world.level.material.FlowingFluid;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(LiquidNullFluid.class)
public abstract class MixinLiquidNullFluid extends FlowingFluid
{
    @Override
    public net.minecraftforge.fluids.FluidType getFluidType()
    {
        return ModFluidTypes.LIQUID_NULL_TYPE.get();
    }
}
