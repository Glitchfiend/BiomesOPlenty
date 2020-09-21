/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.biome.overworld;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.enums.BOPClimates;
import biomesoplenty.common.biome.BiomeTemplate;
import biomesoplenty.common.world.biome.BiomeFeatureHelper;
import biomesoplenty.common.world.gen.feature.BOPConfiguredFeatures;
import biomesoplenty.common.world.gen.feature.BOPFeatures;
import biomesoplenty.common.world.gen.surfacebuilders.BOPSurfaceBuilders;
import com.google.common.collect.ImmutableList;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.structure.StructureFeatures;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;

public class MuskegBiome extends BiomeTemplate
{
    public MuskegBiome()
    {
        this.addWeight(BOPClimates.TUNDRA, 2);
        this.setBeachBiome(null);
    }

    @Override
    protected void configureBiome(Biome.Builder builder)
    {
        builder.precipitation(Biome.RainType.SNOW).biomeCategory(Biome.Category.ICY).depth(-0.175F).scale(-0.05F).temperature(-0.25F).downfall(0.6F);

        builder.specialEffects((new BiomeAmbience.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(12638463).skyColor(calculateSkyColor(-0.25F)).grassColorOverride(0x94966E).foliageColorOverride(0x8D9B6B).ambientMoodSound(MoodSoundAmbience.LEGACY_CAVE_SETTINGS).build());
    }

    @Override
    protected void configureGeneration(BiomeGenerationSettingsRegistryBuilder builder)
    {
        builder.surfaceBuilder(new ConfiguredSurfaceBuilder(BOPSurfaceBuilders.PODZOL, SurfaceBuilder.CONFIG_GRASS));

        // Structures
        DefaultBiomeFeatures.addDefaultOverworldLandStructures(builder);
        builder.addStructureStart(StructureFeatures.RUINED_PORTAL_SWAMP);

        // Underground
        DefaultBiomeFeatures.addDefaultCarvers(builder);
        DefaultBiomeFeatures.addDefaultLakes(builder);

        builder.addFeature(GenerationStage.Decoration.LAKES, Feature.LAKE.configured(new BlockStateFeatureConfig(Blocks.WATER.defaultBlockState())).decorated(Placement.WATER_LAKE.configured(new ChanceConfig(2))));

        DefaultBiomeFeatures.addDefaultMonsterRoom(builder);
        DefaultBiomeFeatures.addDefaultUndergroundVariety(builder);
        DefaultBiomeFeatures.addDefaultOres(builder);

        builder.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.DISK.configured(new SphereReplaceConfig(Blocks.CLAY.defaultBlockState(), FeatureSpread.of(4, 2), 1, ImmutableList.of(Blocks.DIRT.defaultBlockState(), Blocks.CLAY.defaultBlockState()))).decorated(Features.Placements.TOP_SOLID_HEIGHTMAP_SQUARE).count(2));
        builder.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.DISK.configured(new SphereReplaceConfig(Blocks.GRAVEL.defaultBlockState(), FeatureSpread.of(4, 2), 1, ImmutableList.of(Blocks.DIRT.defaultBlockState(), Blocks.GRASS_BLOCK.defaultBlockState()))).decorated(Features.Placements.TOP_SOLID_HEIGHTMAP_SQUARE).count(2));
        builder.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.DISK.configured(new SphereReplaceConfig(BOPBlocks.mud.defaultBlockState(), FeatureSpread.of(4, 2), 1, ImmutableList.of(Blocks.DIRT.defaultBlockState(), Blocks.GRASS_BLOCK.defaultBlockState()))).decorated(Features.Placements.TOP_SOLID_HEIGHTMAP_SQUARE).count(2));

        ////////////////////////////////////////////////////////////

        // Vegetation
        builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(BOPFeatures.DARK_OAK_TWIGLET_TREE.configured(Features.OAK.config()).weighted(0.4F), BOPFeatures.DEAD_TREE.configured(Features.OAK.config()).weighted(0.3F), BOPFeatures.DYING_TREE.configured(Features.OAK.config()).weighted(0.075F), BOPFeatures.DEAD_TWIGLET_TREE_TALL.configured(Features.OAK.config()).weighted(0.1F)), BOPFeatures.DEAD_TWIGLET_TREE.configured(Features.OAK.config()))).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(1, 0.2F, 1))));
        builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, BOPConfiguredFeatures.STANDARD_GRASS_3);
        builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, BOPFeatures.SCRUB.configured(IFeatureConfig.NONE).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(10)));
        builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, BOPConfiguredFeatures.WATERGRASS_5);
        builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, BOPConfiguredFeatures.REED_10);

        ////////////////////////////////////////////////////////////

        // Other Features
        DefaultBiomeFeatures.addDefaultSprings(builder);
        DefaultBiomeFeatures.addSurfaceFreezing(builder);
    }

    @Override
    protected void configureMobSpawns(MobSpawnInfo.Builder builder)
    {
        // Entities
        builder.addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityType.RABBIT, 4, 2, 3));
        builder.addSpawn(EntityClassification.AMBIENT, new MobSpawnInfo.Spawners(EntityType.BAT, 10, 8, 8));
        builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.SPIDER, 100, 4, 4));
        builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.ZOMBIE, 95, 4, 4));
        builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.ZOMBIE_VILLAGER, 5, 1, 1));
        builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.SKELETON, 100, 4, 4));
        builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.CREEPER, 100, 4, 4));
        builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.SLIME, 100, 4, 4));
        builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.ENDERMAN, 10, 1, 4));
        builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.WITCH, 5, 1, 1));
        builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.STRAY, 20, 4, 4));
    }
}
