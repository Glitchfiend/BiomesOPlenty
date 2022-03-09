/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.util.worldgen;

import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.core.Holder;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class BOPPlacementUtils
{
    public static Holder<PlacedFeature> register(String key, Holder<? extends ConfiguredFeature<?, ?>> feature, List<PlacementModifier> modifiers)
    {
        return BuiltinRegistries.register(BuiltinRegistries.PLACED_FEATURE, new ResourceLocation(BiomesOPlenty.MOD_ID, key), new PlacedFeature(Holder.hackyErase(feature), List.copyOf(modifiers)));
    }

    public static Holder<PlacedFeature> register(String p_206514_, Holder<? extends ConfiguredFeature<?, ?>> feature, PlacementModifier... modifiers)
    {
        return register(p_206514_, feature, List.of(modifiers));
    }
}
