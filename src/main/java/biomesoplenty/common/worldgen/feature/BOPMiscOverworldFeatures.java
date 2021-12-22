/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.worldgen.feature;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.core.BiomesOPlenty;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.DiskConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SpringConfiguration;
import net.minecraft.world.level.material.Fluids;

import java.util.List;

public class BOPMiscOverworldFeatures
{
    public static final ConfiguredFeature<?, ?> BLACK_SAND_SPLATTER = register("black_sand_splatter", BOPBaseFeatures.BLACK_SAND_SPLATTER.configured(NoneFeatureConfiguration.INSTANCE));
    public static final ConfiguredFeature<?, ?> BONE_SPINE = register("bone_spine", BOPBaseFeatures.BONE_SPINE.configured(NoneFeatureConfiguration.INSTANCE));
    public static final ConfiguredFeature<?, ?> CRAG_SPLATTER = register("crag_splatter", BOPBaseFeatures.CRAG_SPLATTER.configured(NoneFeatureConfiguration.INSTANCE));
    public static final ConfiguredFeature<DiskConfiguration, ?> DISK_BLACK_SAND = register("disk_black_sand", Feature.DISK.configured(new DiskConfiguration(BOPBlocks.BLACK_SAND.defaultBlockState(), UniformInt.of(2, 6), 2, List.of(Blocks.DIRT.defaultBlockState(), Blocks.GRASS_BLOCK.defaultBlockState()))));
    public static final ConfiguredFeature<DiskConfiguration, ?> DISK_CALCITE = register("disk_calcite", Feature.DISK.configured(new DiskConfiguration(Blocks.CALCITE.defaultBlockState(), UniformInt.of(3, 7), 2, ImmutableList.of(Blocks.DIRT.defaultBlockState(), Blocks.GRASS_BLOCK.defaultBlockState(), Blocks.PODZOL.defaultBlockState(), Blocks.SAND.defaultBlockState(), Blocks.GRAVEL.defaultBlockState(), Blocks.GRANITE.defaultBlockState(), Blocks.ANDESITE.defaultBlockState(), Blocks.DIORITE.defaultBlockState(), Blocks.COAL_ORE.defaultBlockState(), Blocks.IRON_ORE.defaultBlockState(), Blocks.STONE.defaultBlockState(), Blocks.CALCITE.defaultBlockState()))));
    public static final ConfiguredFeature<DiskConfiguration, ?> DISK_GRAVEL_EXTRA = register("disk_gravel_extra", Feature.DISK.configured(new DiskConfiguration(Blocks.GRAVEL.defaultBlockState(), UniformInt.of(1, 4), 2, List.of(Blocks.DIRT.defaultBlockState(), Blocks.GRASS_BLOCK.defaultBlockState()))));
    public static final ConfiguredFeature<DiskConfiguration, ?> DISK_ORANGE_SAND = register("disk_orange_sand", Feature.DISK.configured(new DiskConfiguration(BOPBlocks.ORANGE_SAND.defaultBlockState(), UniformInt.of(2, 6), 2, List.of(Blocks.DIRT.defaultBlockState(), Blocks.GRASS_BLOCK.defaultBlockState()))));
    public static final ConfiguredFeature<DiskConfiguration, ?> DISK_WHITE_SAND = register("disk_white_sand", Feature.DISK.configured(new DiskConfiguration(BOPBlocks.WHITE_SAND.defaultBlockState(), UniformInt.of(2, 6), 2, List.of(Blocks.DIRT.defaultBlockState(), Blocks.GRASS_BLOCK.defaultBlockState()))));
    public static final ConfiguredFeature<DiskConfiguration, ?> DISK_MUD = register("disk_mud", Feature.DISK.configured(new DiskConfiguration(BOPBlocks.MUD.defaultBlockState(), UniformInt.of(4, 6), 2, List.of(Blocks.DIRT.defaultBlockState(), Blocks.GRASS_BLOCK.defaultBlockState()))));
    public static final ConfiguredFeature<SpringConfiguration, ?> SPRING_WATER_EXTRA = register("spring_water_extra", Feature.SPRING.configured(new SpringConfiguration(Fluids.WATER.defaultFluidState(), true, 4, 1, ImmutableSet.of(Blocks.DIRT, Blocks.TERRACOTTA, Blocks.STONE, Blocks.GRANITE, Blocks.DIORITE, Blocks.ANDESITE, BOPBlocks.ORANGE_SANDSTONE))));

    private static <FC extends FeatureConfiguration> ConfiguredFeature<FC, ?> register(String key, ConfiguredFeature<FC, ?> feature)
    {
        return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(BiomesOPlenty.MOD_ID, key), feature);
    }
}
