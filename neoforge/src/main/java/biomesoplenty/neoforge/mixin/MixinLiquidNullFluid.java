/*******************************************************************************
 * Copyright 2024, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.neoforge.mixin;

import biomesoplenty.block.LiquidNullFluid;
import biomesoplenty.neoforge.init.ModFluidTypes;
import net.minecraft.world.level.material.FlowingFluid;
import net.neoforged.neoforge.fluids.FluidType;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(LiquidNullFluid.class)
public abstract class MixinLiquidNullFluid extends FlowingFluid
{
    @Override
    public FluidType getFluidType()
    {
        return ModFluidTypes.LIQUID_NULL_TYPE.value();
    }
}
