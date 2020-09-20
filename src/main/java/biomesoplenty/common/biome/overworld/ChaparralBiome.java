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
import biomesoplenty.common.world.gen.feature.BOPFeatures;
import biomesoplenty.common.world.gen.surfacebuilders.BOPSurfaceBuilders;
import com.google.common.collect.ImmutableList;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.structure.StructureFeatures;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;

public class ChaparralBiome extends BiomeTemplate
{
    public ChaparralBiome()
    {
        this.addWeight(BOPClimates.WARM_TEMPERATE, 5);
    }

    @Override
    protected void configureBiome(Biome.Builder builder)
    {
        builder.precipitation(Biome.RainType.RAIN).biomeCategory(Biome.Category.PLAINS).depth(1.1F).scale(0.3F).temperature(0.8F).downfall(0.45F);

        builder.specialEffects((new BiomeAmbience.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(12638463).skyColor(calculateSkyColor(0.8F)).ambientMoodSound(MoodSoundAmbience.LEGACY_CAVE_SETTINGS).build());
    }

    @Override
    protected void configureGeneration(BiomeGenerationSettingsRegistryBuilder builder)
    {
        builder.surfaceBuilder(new ConfiguredSurfaceBuilder(BOPSurfaceBuilders.CHAPARRAL, SurfaceBuilder.CONFIG_GRASS));

        // Structures
        DefaultBiomeFeatures.addDefaultOverworldLandStructures(builder);
        builder.addStructureStart(StructureFeatures.RUINED_PORTAL_MOUNTAIN);

        // Underground
        DefaultBiomeFeatures.addDefaultCarvers(builder);
        DefaultBiomeFeatures.addDefaultLakes(builder);
        DefaultBiomeFeatures.addDefaultMonsterRoom(builder);
        DefaultBiomeFeatures.addDefaultUndergroundVariety(builder);
        DefaultBiomeFeatures.addDefaultOres(builder);
        DefaultBiomeFeatures.addDefaultSoftDisks(builder);

        ////////////////////////////////////////////////////////////

        // Vegetation
        builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(BOPFeatures.TWIGLET_TREE.configured(Features.OAK.config()).weighted(0.1F)), BOPFeatures.BUSH.configured(Features.OAK.config()))).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(12, 0.3F, 1))));

        builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, BOPFeatures.CHAPARRAL_FLOWERS.configured(IFeatureConfig.NONE).decorated(Features.Placements.ADD_32).decorated(Features.Placements.HEIGHTMAP_SQUARE.count(17)));
        builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, BOPFeatures.STANDARD_GRASS.configured(IFeatureConfig.NONE).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(8)));
        builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.configured(BiomeFeatureHelper.createClusterConfiguration(BOPBlocks.bush.defaultBlockState())).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(6)));

        ////////////////////////////////////////////////////////////

        // Other Features
        DefaultBiomeFeatures.addDefaultSprings(builder);
        DefaultBiomeFeatures.addSurfaceFreezing(builder);
    }

    @Override
    protected void configureMobSpawns(MobSpawnInfo.Builder builder)
    {
        // Entities
        builder.addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityType.SHEEP, 12, 4, 4));
        builder.addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityType.PIG, 10, 4, 4));
        builder.addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityType.CHICKEN, 10, 4, 4));
        builder.addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityType.COW, 8, 4, 4));
        builder.addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityType.HORSE, 5, 2, 6));
        builder.addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityType.DONKEY, 1, 1, 3));
        builder.addSpawn(EntityClassification.AMBIENT, new MobSpawnInfo.Spawners(EntityType.BAT, 10, 8, 8));
        builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.SPIDER, 100, 4, 4));
        builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.ZOMBIE, 95, 4, 4));
        builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.ZOMBIE_VILLAGER, 5, 1, 1));
        builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.SKELETON, 100, 4, 4));
        builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.CREEPER, 100, 4, 4));
        builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.SLIME, 100, 4, 4));
        builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.ENDERMAN, 10, 1, 4));
        builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.WITCH, 5, 1, 1));
    }
}
