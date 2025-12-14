/*******************************************************************************
 * Copyright 2024, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.fabric.init;

import biomesoplenty.api.block.BOPFluids;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.minecraft.resources.Identifier;

public class ModClientFabric
{
    public static void setup()
    {
        FluidRenderHandlerRegistry.INSTANCE.register(BOPFluids.BLOOD, BOPFluids.FLOWING_BLOOD, new SimpleFluidRenderHandler(
                Identifier.parse("biomesoplenty:block/blood_still"),
                Identifier.parse("biomesoplenty:block/blood_flow"),
                Identifier.parse("biomesoplenty:textures/block/blood_underwater.png")));
        FluidRenderHandlerRegistry.INSTANCE.register(BOPFluids.LIQUID_NULL, BOPFluids.FLOWING_LIQUID_NULL, new SimpleFluidRenderHandler(
                Identifier.parse("biomesoplenty:block/liquid_null_still"),
                Identifier.parse("biomesoplenty:block/liquid_null_flow"),
                Identifier.parse("biomesoplenty:textures/block/liquid_null_underwater.png")));
    }
}
