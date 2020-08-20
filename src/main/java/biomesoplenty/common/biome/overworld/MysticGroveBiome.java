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
import biomesoplenty.common.world.gen.feature.BOPBiomeFeatures;
import biomesoplenty.common.world.gen.feature.StandardGrassFeature;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.biome.MoodSoundAmbience;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placement.*;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class MysticGroveBiome extends BiomeTemplate
{
    public MysticGroveBiome()
    {
        super((new Biome.Builder()).surfaceBuilder(new ConfiguredSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.CONFIG_GRASS)).precipitation(Biome.RainType.RAIN).biomeCategory(Biome.Category.FOREST).depth(0.1F).scale(0.1F).temperature(0.7F).downfall(0.8F).specialEffects((new BiomeAmbience.Builder()).waterColor(0x9C3FE4).waterFogColor(0x2E0533).fogColor(0xFFC9DA).ambientMoodSound(MoodSoundAmbience.LEGACY_CAVE_SETTINGS).build()).parent((String)null));

        // Structures
        DefaultBiomeFeatures.addDefaultOverworldLandStructures(this);
        this.addStructureStart(DefaultBiomeFeatures.RUINED_PORTAL_STANDARD);

        // Underground
        DefaultBiomeFeatures.addDefaultCarvers(this);

        this.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, Feature.LAKE.configured(new BlockStateFeatureConfig(Blocks.WATER.defaultBlockState())).decorated(Placement.WATER_LAKE.configured(new ChanceConfig(4))));

        DefaultBiomeFeatures.addDefaultMonsterRoom(this);
        DefaultBiomeFeatures.addDefaultUndergroundVariety(this);
        DefaultBiomeFeatures.addDefaultOres(this);

        this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.DISK.configured(new SphereReplaceConfig(BOPBlocks.white_sand.defaultBlockState(), 7, 2, Lists.newArrayList(Blocks.DIRT.defaultBlockState(), Blocks.GRASS_BLOCK.defaultBlockState()))).decorated(Placement.COUNT_TOP_SOLID.configured(new FrequencyConfig(3))));
        this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.DISK.configured(new SphereReplaceConfig(Blocks.CLAY.defaultBlockState(), 4, 1, Lists.newArrayList(Blocks.DIRT.defaultBlockState(), Blocks.CLAY.defaultBlockState()))).decorated(Placement.COUNT_TOP_SOLID.configured(new FrequencyConfig(1))));

        ////////////////////////////////////////////////////////////

        // Vegetation
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(BOPBiomeFeatures.GIANT_TREE.configured(DefaultBiomeFeatures.NORMAL_TREE_CONFIG).weighted(0.01F), BOPBiomeFeatures.TALL_SWAMP_TREE.configured(DefaultBiomeFeatures.NORMAL_TREE_CONFIG).weighted(0.1F), BOPBiomeFeatures.BIG_FLOWERING_OAK_TREE.configured(DefaultBiomeFeatures.NORMAL_TREE_CONFIG).weighted(0.2F), BOPBiomeFeatures.JACARANDA_TREE.configured(DefaultBiomeFeatures.NORMAL_TREE_WITH_BEES_0002_CONFIG).weighted(0.3F), BOPBiomeFeatures.BIG_MAGIC_TREE.configured(DefaultBiomeFeatures.NORMAL_TREE_CONFIG).weighted(0.3F)), BOPBiomeFeatures.MAGIC_TREE.configured(DefaultBiomeFeatures.NORMAL_TREE_CONFIG))).decorated(Placement.COUNT_EXTRA_HEIGHTMAP.configured(new AtSurfaceWithExtraConfig(10, 0.5F, 1))));

        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, BOPBiomeFeatures.MYSTIC_GROVE_FLOWERS.configured(IFeatureConfig.NONE).decorated(Placement.COUNT_HEIGHTMAP_32.configured(new FrequencyConfig(18))));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.HUGE_RED_MUSHROOM.configured(DefaultBiomeFeatures.HUGE_RED_MUSHROOM_CONFIG).decorated(Placement.COUNT_EXTRA_HEIGHTMAP.configured(new AtSurfaceWithExtraConfig(1, 0.1F, 1))));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, BOPBiomeFeatures.SMALL_RED_MUSHROOM.configured(IFeatureConfig.NONE).decorated(Placement.COUNT_HEIGHTMAP_DOUBLE.configured(new FrequencyConfig(1))));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.configured(BiomeFeatureHelper.createClusterConfigurationDouble(BOPBlocks.blue_hydrangea.defaultBlockState())).decorated(Placement.COUNT_HEIGHTMAP_32.configured(new FrequencyConfig(4))));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.configured(BiomeFeatureHelper.createClusterConfigurationDouble(Blocks.LILAC.defaultBlockState())).decorated(Placement.COUNT_HEIGHTMAP_32.configured(new FrequencyConfig(1))));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, new StandardGrassFeature(NoFeatureConfig.CODEC).configured(IFeatureConfig.NONE).decorated(Placement.COUNT_HEIGHTMAP_DOUBLE.configured(new FrequencyConfig(15))));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.configured(BiomeFeatureHelper.createClusterConfiguration(BOPBlocks.sprout.defaultBlockState())).decorated(Placement.COUNT_HEIGHTMAP_DOUBLE.configured(new FrequencyConfig(15))));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.configured(BiomeFeatureHelper.createClusterConfigurationDoubleWater(BOPBlocks.watergrass.defaultBlockState())).decorated(Placement.COUNT_HEIGHTMAP_DOUBLE.configured(new FrequencyConfig(10))));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.configured(BiomeFeatureHelper.createClusterConfiguration(Blocks.BROWN_MUSHROOM.defaultBlockState())).decorated(Placement.CHANCE_HEIGHTMAP_DOUBLE.configured(new ChanceConfig(10))));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.configured(BiomeFeatureHelper.createClusterConfiguration(Blocks.RED_MUSHROOM.defaultBlockState())).decorated(Placement.CHANCE_HEIGHTMAP_DOUBLE.configured(new ChanceConfig(20))));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.SEAGRASS.configured(new SeaGrassConfig(32, 0.4D)).decorated(Placement.TOP_SOLID_HEIGHTMAP.configured(IPlacementConfig.NONE)));

        ////////////////////////////////////////////////////////////

        // Other Features
        DefaultBiomeFeatures.addDefaultSprings(this);
        DefaultBiomeFeatures.addSurfaceFreezing(this);

        // Entities
        this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.RABBIT, 4, 2, 3));
        this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.FOX, 8, 2, 4));
        this.addSpawn(EntityClassification.AMBIENT, new Biome.SpawnListEntry(EntityType.BAT, 10, 8, 8));
        this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.ZOMBIE_VILLAGER, 5, 1, 1));
        this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.SLIME, 100, 4, 4));
        this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.WITCH, 20, 1, 1));
        
        this.addWeight(BOPClimates.WET_TEMPERATE, 1);
        this.setBeachBiome((Biome)null);
        this.setRiverBiome((Biome)null);
    }
    
    @OnlyIn(Dist.CLIENT)
    @Override
    public int getSkyColor()
    {
       return 0x66DBFF;
    }
    
    @OnlyIn(Dist.CLIENT)
    @Override
    public int getGrassColor(double x, double z)
    {
    	return 0x69CFDB;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public int getFoliageColor()
    {
    	return 0x70E0B5;
    }
}
