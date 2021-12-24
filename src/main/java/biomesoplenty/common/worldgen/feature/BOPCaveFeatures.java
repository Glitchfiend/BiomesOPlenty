/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.worldgen.feature;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.util.valueproviders.WeightedListInt;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.BlockColumnConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

import java.util.List;

public class BOPCaveFeatures
{
    // Spider Nest
    public static final ConfiguredFeature<BlockColumnConfiguration, ?> HANGING_COBWEB = register("hanging_cobweb", Feature.BLOCK_COLUMN.configured(new BlockColumnConfiguration(List.of(BlockColumnConfiguration.layer(new WeightedListInt(SimpleWeightedRandomList.<IntProvider>builder().add(UniformInt.of(0, 15), 2).add(UniformInt.of(0, 3), 3).add(UniformInt.of(0, 7), 3).build()), BlockStateProvider.simple(BOPBlocks.HANGING_COBWEB_STRAND)), BlockColumnConfiguration.layer(ConstantInt.of(1), BlockStateProvider.simple(BOPBlocks.HANGING_COBWEB))), Direction.DOWN, BlockPredicate.ONLY_IN_AIR_PREDICATE, true)));
    public static final ConfiguredFeature<?, ?> CORNER_COBWEBS = register("corner_cobwebs", BOPBaseFeatures.CORNER_COBWEBS.configured(NoneFeatureConfiguration.INSTANCE));
    public static final ConfiguredFeature<SimpleBlockConfiguration, ?> SPIDER_EGG = register("spider_egg", Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(BlockStateProvider.simple(BOPBlocks.SPIDER_EGG))));
    public static final ConfiguredFeature<?, ?> WEBBING = register("webbing", BOPBaseFeatures.WEBBING.configured(NoneFeatureConfiguration.INSTANCE));

    private static <FC extends FeatureConfiguration> ConfiguredFeature<FC, ?> register(String key, ConfiguredFeature<FC, ?> feature)
    {
        return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(BiomesOPlenty.MOD_ID, key), feature);
    }
}
