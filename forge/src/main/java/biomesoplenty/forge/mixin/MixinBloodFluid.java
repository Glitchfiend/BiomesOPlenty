/*******************************************************************************
 * Copyright 2024, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.forge.mixin;

import biomesoplenty.block.BloodFluid;
import biomesoplenty.forge.init.ModFluidTypes;
import net.minecraft.world.level.material.FlowingFluid;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BloodFluid.class)
public abstract class MixinBloodFluid extends FlowingFluid
{
    @Override
    public net.minecraftforge.fluids.FluidType getFluidType()
    {
        return ModFluidTypes.BLOOD_TYPE.get();
    }
}
