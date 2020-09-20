/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.world.gen.feature;

import biomesoplenty.core.BiomesOPlenty;
import com.google.common.collect.ImmutableList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;

public class BOPConfiguredFeatures
{
    // Big trees
    public static final ConfiguredFeature<?, ?> BIG_FLOWERING_OAK_TREE = register("big_flowering_oak_tree", BOPFeatures.BIG_FLOWERING_OAK_TREE.configured(Features.OAK.config()));
    public static final ConfiguredFeature<?, ?> BIG_MAGIC_TREE = register("big_magic_tree", BOPFeatures.BIG_MAGIC_TREE.configured(Features.OAK.config()));

    // Trees
    public static final ConfiguredFeature<?, ?> GIANT_TREE = register("giant_tree", BOPFeatures.GIANT_TREE.configured(Features.OAK.config()));
    public static final ConfiguredFeature<?, ?> JACARANDA_TREE = register("jacaranda_tree", BOPFeatures.JACARANDA_TREE.configured(Features.OAK.config().withDecorators(ImmutableList.of(Features.Placements.BEEHIVE_0002))));
    public static final ConfiguredFeature<?, ?> MAGIC_TREE = register("magic_tree", BOPFeatures.MAGIC_TREE.configured(Features.OAK.config()));
    public static final ConfiguredFeature<?, ?> TALL_SWAMP_TREE = register("tall_swamp_tree", BOPFeatures.TALL_SWAMP_TREE.configured(Features.OAK.config()));

    // Tree assortments
    public static final ConfiguredFeature<?, ?> MYSTIC_GROVE_TREES = register("mystic_grove_trees", Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(GIANT_TREE.weighted(0.01F), TALL_SWAMP_TREE.weighted(0.1F), BIG_FLOWERING_OAK_TREE.weighted(0.2F), JACARANDA_TREE.weighted(0.3F), BIG_MAGIC_TREE.weighted(0.3F)), MAGIC_TREE)).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(10, 0.5F, 1))));

    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String key, ConfiguredFeature<FC, ?> feature)
    {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(BiomesOPlenty.MOD_ID, key), feature);
    }
}
