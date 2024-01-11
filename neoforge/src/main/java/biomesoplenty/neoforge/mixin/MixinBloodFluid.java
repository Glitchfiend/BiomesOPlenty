/*******************************************************************************
 * Copyright 2024, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.neoforge.mixin;

import biomesoplenty.block.BloodFluid;
import biomesoplenty.neoforge.init.ModFluidTypes;
import net.minecraft.world.level.material.FlowingFluid;
import net.neoforged.neoforge.fluids.FluidType;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BloodFluid.class)
public abstract class MixinBloodFluid extends FlowingFluid
{
    @Override
    public FluidType getFluidType()
    {
        return ModFluidTypes.BLOOD_TYPE.value();
    }
}
