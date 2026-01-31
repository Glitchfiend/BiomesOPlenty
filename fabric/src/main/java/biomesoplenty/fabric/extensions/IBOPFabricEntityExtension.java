/*******************************************************************************
 * Copyright 2024, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.fabric.extensions;

import net.minecraft.world.level.material.FluidState;

// TODO: Migrate this to GlitchCore should any of our other mods require fluids
public interface IBOPFabricEntityExtension
{
    FluidState getFluidStateOnEyes();
}
