/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.worldgen.feature;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.worldgen.feature.configurations.TaigaTreeConfiguration;
import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public class BOPTreeFeatures
{
    public static final ConfiguredFeature<TreeConfiguration, ?> FIR = register("fir", BOPBaseFeatures.TAIGA_TREE.configured(createFir().minHeight(5).maxHeight(28).build()));
    public static final ConfiguredFeature<TreeConfiguration, ?> LARGE_FIR = register("large_fir", BOPBaseFeatures.TAIGA_TREE.configured(createFir().minHeight(20).maxHeight(40).trunkWidth(2).build()));

    private static TaigaTreeConfiguration.Builder createFir()
    {
        return new TaigaTreeConfiguration.Builder().trunk(BlockStateProvider.simple(BOPBlocks.FIR_LOG)).foliage(BlockStateProvider.simple(BOPBlocks.FIR_LEAVES));
    }

    private static <FC extends FeatureConfiguration> ConfiguredFeature<FC, ?> register(String key, ConfiguredFeature<FC, ?> feature)
    {
        return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(BiomesOPlenty.MOD_ID, key), feature);
    }
}
