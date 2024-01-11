/*******************************************************************************
 * Copyright 2024, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.fabric.core;

import biomesoplenty.api.block.BOPFluids;
import biomesoplenty.core.BiomesOPlenty;
import glitchcore.fabric.GlitchCoreInitializer;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariantAttributeHandler;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariantAttributes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import terrablender.api.TerraBlenderApi;

public class BiomesOPlentyFabric implements GlitchCoreInitializer, TerraBlenderApi
{
    @Override
    public void onInitialize()
    {
        BiomesOPlenty.init();
    }

    @Override
    public void onInitializeClient()
    {
        BiomesOPlenty.setupClient();
        FluidRenderHandlerRegistry.INSTANCE.register(BOPFluids.BLOOD, BOPFluids.FLOWING_BLOOD, new SimpleFluidRenderHandler(new ResourceLocation("biomesoplenty:block/blood_still"), new ResourceLocation("biomesoplenty:block/blood_flow")));
    }

    @Override
    public void onTerraBlenderInitialized()
    {
        BiomesOPlenty.setupTerraBlender();
    }
}