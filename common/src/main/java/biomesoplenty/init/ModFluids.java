/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.init;

import biomesoplenty.block.BloodFluid;
import biomesoplenty.block.LiquidNullFluid;
import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;

import java.util.function.BiConsumer;

import static biomesoplenty.api.block.BOPFluids.*;

public class ModFluids
{
    public static void registerFluids(BiConsumer<ResourceLocation, Fluid> func)
    {
        FLOWING_BLOOD = (FlowingFluid)register(func, new BloodFluid.Flowing(), "flowing_blood");
        BLOOD = register(func, new BloodFluid.Source(), "blood");
        FLOWING_LIQUID_NULL = (FlowingFluid)register(func, new LiquidNullFluid.Flowing(), "flowing_liquid_null");
        LIQUID_NULL = register(func, new LiquidNullFluid.Source(), "liquid_null");
    }

    private static Fluid register(BiConsumer<ResourceLocation, Fluid> func, Fluid fluid, String name)
    {
        func.accept(ResourceLocation.fromNamespaceAndPath(BiomesOPlenty.MOD_ID, name), fluid);
        return fluid;
    }
}