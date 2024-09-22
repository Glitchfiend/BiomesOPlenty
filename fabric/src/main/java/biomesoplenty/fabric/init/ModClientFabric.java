/*******************************************************************************
 * Copyright 2024, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.fabric.init;

import biomesoplenty.api.block.BOPFluids;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.minecraft.resources.ResourceLocation;

public class ModClientFabric
{
    public static void setup()
    {
        FluidRenderHandlerRegistry.INSTANCE.register(BOPFluids.BLOOD, BOPFluids.FLOWING_BLOOD, new SimpleFluidRenderHandler(
                new ResourceLocation("biomesoplenty:block/blood_still"),
                new ResourceLocation("biomesoplenty:block/blood_flow"),
                new ResourceLocation("biomesoplenty:textures/block/blood_underwater.png")));
        FluidRenderHandlerRegistry.INSTANCE.register(BOPFluids.LIQUID_NULL, BOPFluids.FLOWING_LIQUID_NULL, new SimpleFluidRenderHandler(
                new ResourceLocation("biomesoplenty:block/liquid_null_still"),
                new ResourceLocation("biomesoplenty:block/liquid_null_flow"),
                new ResourceLocation("biomesoplenty:textures/block/liquid_null_underwater.png")));
    }
}
