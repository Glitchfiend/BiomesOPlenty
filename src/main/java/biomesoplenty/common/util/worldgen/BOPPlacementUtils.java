/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.util.worldgen;

import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.core.Holder;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.function.Supplier;

public class BOPPlacementUtils
{
    public static RegistryObject<PlacedFeature> register(String key, Holder<? extends ConfiguredFeature<?, ?>> feature, List<PlacementModifier> modifiers)
    {
        return BiomesOPlenty.PLACED_FEATURE_REGISTER.register(key, () -> new PlacedFeature(Holder.hackyErase(feature), List.copyOf(modifiers)));
    }

    public static RegistryObject<PlacedFeature> register(String key, Holder<? extends ConfiguredFeature<?, ?>> feature, PlacementModifier... modifiers)
    {
        return register(key, feature, List.of(modifiers));
    }

    // NOTE: We use a supplier for modifiers as they may reference blocks which haven't been registered by Forge yet
    public static RegistryObject<PlacedFeature> register(String key, RegistryObject<? extends ConfiguredFeature<?, ?>> feature, Supplier<List<PlacementModifier>> modifiers)
    {
        return BiomesOPlenty.PLACED_FEATURE_REGISTER.register(key, () -> new PlacedFeature(Holder.hackyErase(feature.getHolder().orElseThrow()), List.copyOf(modifiers.get())));
    }

    public static RegistryObject<PlacedFeature> register(String key, RegistryObject<? extends ConfiguredFeature<?, ?>> feature)
    {
        return register(key, feature, () -> List.of());
    }
}
