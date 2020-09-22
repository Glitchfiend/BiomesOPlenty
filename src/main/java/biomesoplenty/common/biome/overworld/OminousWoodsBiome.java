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
import com.google.common.collect.ImmutableList;
import net.minecraft.block.Blocks;
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

public class OminousWoodsBiome extends BiomeTemplate
{
    public OminousWoodsBiome()
    {
        this.addWeight(BOPClimates.WET_BOREAL, 1);
        this.setBeachBiome(null);
        this.setRiverBiome(null);
    }

    @Override
    protected void configureBiome(Biome.Builder builder)
    {
        builder.precipitation(Biome.RainType.RAIN).biomeCategory(Biome.Category.TAIGA).depth(0.25F).scale(0.15F).temperature(0.6F).downfall(0.6F);

        builder.specialEffects((new BiomeAmbience.Builder()).waterColor(0x312346).waterFogColor(0x0A030C).fogColor(0x7881A5).skyColor(0x84A1CC).grassColorOverride(0x4C4A70).foliageColorOverride(0x6B487C).ambientMoodSound(MoodSoundAmbience.LEGACY_CAVE_SETTINGS).build());
    }

    @Override
    protected void configureGeneration(BiomeGenerationSettingsRegistryBuilder builder)
    {
        builder.surfaceBuilder(new ConfiguredSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.CONFIG_GRASS));

        // Structures
        builder.addStructureStart(StructureFeatures.WOODLAND_MANSION);
        builder.addStructureStart(StructureFeatures.SWAMP_HUT);
        DefaultBiomeFeatures.addDefaultOverworldLandStructures(builder);
        builder.addStructureStart(StructureFeatures.RUINED_PORTAL_STANDARD);

        // Underground
        DefaultBiomeFeatures.addDefaultCarvers(builder);

        builder.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, Features.LAKE_WATER);

        DefaultBiomeFeatures.addDefaultMonsterRoom(builder);
        DefaultBiomeFeatures.addDefaultUndergroundVariety(builder);
        DefaultBiomeFeatures.addDefaultOres(builder);

        builder.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, BOPConfiguredFeatures.BLACK_SAND_DISK);

        ////////////////////////////////////////////////////////////

        // Vegetation
        builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(BOPFeatures.DEAD_TREE.configured(Features.OAK.config()).weighted(0.05F), BOPFeatures.DYING_TREE.configured(Features.OAK.config()).weighted(0.15F), BOPFeatures.WILLOW_TREE.configured(Features.OAK.config()).weighted(0.15F), BOPFeatures.TALL_UMBRAN_TREE.configured(Features.OAK.config()).weighted(0.7F)), BOPFeatures.UMBRAN_TREE.configured(Features.OAK.config()))).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(7, 0.5F, 1))));
        builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, BOPFeatures.OMINOUS_WOODS_FLOWERS.configured(IFeatureConfig.NONE).decorated(Features.Placements.ADD_32).decorated(Features.Placements.HEIGHTMAP_SQUARE.count(3)));

        builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, BOPConfiguredFeatures.BRAMBLE);
        builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, BOPConfiguredFeatures.DEAD_GRASS_2);
        builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, BOPConfiguredFeatures.STANDARD_GRASS_6);
        builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.BROWN_MUSHROOM_NORMAL);
        builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.PATCH_PUMPKIN);
        builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.RED_MUSHROOM_NORMAL);

        ////////////////////////////////////////////////////////////

        // Other Features
        DefaultBiomeFeatures.addDefaultSprings(builder);
        DefaultBiomeFeatures.addSurfaceFreezing(builder);
    }

    @Override
    protected void configureMobSpawns(MobSpawnInfo.Builder builder)
    {
        // Entities
        builder.addSpawn(EntityClassification.AMBIENT, new MobSpawnInfo.Spawners(EntityType.BAT, 10, 8, 8));
        builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.EVOKER, 50, 2, 4));
        builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.SPIDER, 100, 4, 4));
        builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.SLIME, 100, 4, 4));
        builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.ENDERMAN, 50, 1, 4));
        builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.WITCH, 25, 1, 1));
    }
}
