/*******************************************************************************
 * Copyright 2024, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.fabric.init;

import biomesoplenty.api.block.BOPFluids;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderingRegistry;
import net.minecraft.client.renderer.block.FluidModel;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.resources.Identifier;

public class ModClientFabric
{
    public static void setup()
    {
        FluidRenderingRegistry.register(BOPFluids.BLOOD, BOPFluids.FLOWING_BLOOD, new FluidModel.Unbaked(
                new Material(Identifier.parse("biomesoplenty:block/blood_still")),
                new Material(Identifier.parse("biomesoplenty:block/blood_flow")),
                new Material(Identifier.parse("biomesoplenty:block/blood_underwater")),
                null));

        FluidRenderingRegistry.register(BOPFluids.LIQUID_NULL, BOPFluids.FLOWING_LIQUID_NULL, new FluidModel.Unbaked(
                new Material(Identifier.parse("biomesoplenty:block/liquid_null_still")),
                new Material(Identifier.parse("biomesoplenty:block/liquid_null_flow")),
                new Material(Identifier.parse("biomesoplenty:block/liquid_null_underwater")),
                null));
    }
}
