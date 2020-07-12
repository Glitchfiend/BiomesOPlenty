/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.biome.overworld;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.biome.BiomeBOP;
import biomesoplenty.common.world.gen.feature.BOPBiomeFeatures;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.biome.MoodSoundAmbience;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placement.*;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class OriginHillsBiome extends BiomeBOP
{
    public OriginHillsBiome()
    {
        super((new Biome.Builder()).surfaceBuilder(new ConfiguredSurfaceBuilder(SurfaceBuilder.DEFAULT, BOPBiomeFeatures.ORIGIN_GRASS_SURFACE)).precipitation(Biome.RainType.RAIN).biomeCategory(Biome.Category.NONE).depth(0.1F).scale(0.2F).temperature(0.6F).downfall(0.6F).specialEffects((new BiomeAmbience.Builder()).waterColor(0x0E31FF).waterFogColor(0x070059).fogColor(0xB0CFFF).ambientMoodSound(MoodSoundAmbience.LEGACY_CAVE_SETTINGS).build()).parent((String)null));

        // Underground
        this.addCarver(GenerationStage.Carving.AIR, Biome.makeCarver(WorldCarver.CAVE, new ProbabilityConfig(0.14285715F)));
        DefaultBiomeFeatures.addDefaultLakes(this);
        DefaultBiomeFeatures.addDefaultMonsterRoom(this);

        this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.DISK.configured(new SphereReplaceConfig(Blocks.SAND.defaultBlockState(), 7, 2, Lists.newArrayList(Blocks.DIRT.defaultBlockState(), BOPBlocks.origin_grass_block.defaultBlockState()))).decorated(Placement.COUNT_TOP_SOLID.configured(new FrequencyConfig(3))));
        this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.DISK.configured(new SphereReplaceConfig(Blocks.CLAY.defaultBlockState(), 4, 1, Lists.newArrayList(Blocks.DIRT.defaultBlockState(), Blocks.CLAY.defaultBlockState()))).decorated(Placement.COUNT_TOP_SOLID.configured(new FrequencyConfig(1))));
        this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.DISK.configured(new SphereReplaceConfig(Blocks.GRAVEL.defaultBlockState(), 6, 2, Lists.newArrayList(Blocks.DIRT.defaultBlockState(), BOPBlocks.origin_grass_block.defaultBlockState()))).decorated(Placement.COUNT_TOP_SOLID.configured(new FrequencyConfig(1))));

        this.addFeature(GenerationStage.Decoration.UNDERGROUND_STRUCTURES, Feature.MONSTER_ROOM.configured(IFeatureConfig.NONE).decorated(Placement.DUNGEONS.configured(new ChanceConfig(8))));
        this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.configured(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.DIRT.defaultBlockState(), 33)).decorated(Placement.COUNT_RANGE.configured(new CountRangeConfig(10, 0, 0, 256))));
        this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.configured(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.GRAVEL.defaultBlockState(), 33)).decorated(Placement.COUNT_RANGE.configured(new CountRangeConfig(8, 0, 0, 256))));
        this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.configured(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.COAL_ORE.defaultBlockState(), 17)).decorated(Placement.COUNT_RANGE.configured(new CountRangeConfig(20, 0, 0, 128))));
        this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.configured(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.IRON_ORE.defaultBlockState(), 9)).decorated(Placement.COUNT_RANGE.configured(new CountRangeConfig(20, 0, 0, 64))));
        this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.configured(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.GOLD_ORE.defaultBlockState(), 9)).decorated(Placement.COUNT_RANGE.configured(new CountRangeConfig(2, 0, 0, 32))));
        this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.configured(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.REDSTONE_ORE.defaultBlockState(), 8)).decorated(Placement.COUNT_RANGE.configured(new CountRangeConfig(8, 0, 0, 16))));
        this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.configured(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.DIAMOND_ORE.defaultBlockState(), 8)).decorated(Placement.COUNT_RANGE.configured(new CountRangeConfig(1, 0, 0, 16))));

        ////////////////////////////////////////////////////////////

        // Vegetation
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(BOPBiomeFeatures.BIG_ORIGIN_TREE.configured(DefaultBiomeFeatures.NORMAL_TREE_CONFIG).weighted(0.1F)), BOPBiomeFeatures.ORIGIN_TREE.configured(DefaultBiomeFeatures.NORMAL_TREE_CONFIG))).decorated(Placement.COUNT_EXTRA_HEIGHTMAP.configured(new AtSurfaceWithExtraConfig(3, 0.4F, 1))));

        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, BOPBiomeFeatures.ORIGIN_FLOWERS.configured(IFeatureConfig.NONE).decorated(Placement.COUNT_HEIGHTMAP_32.configured(new FrequencyConfig(4))));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.configured(DefaultBiomeFeatures.SUGAR_CANE_CONFIG).decorated(Placement.COUNT_HEIGHTMAP_DOUBLE.configured(new FrequencyConfig(15))));

        ////////////////////////////////////////////////////////////

        // Other Features
        DefaultBiomeFeatures.addDefaultSprings(this);
        DefaultBiomeFeatures.addSurfaceFreezing(this);

        // Entities
        this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.SHEEP, 12, 4, 4));
        this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.PIG, 10, 4, 4));
        this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.CHICKEN, 10, 4, 4));
        this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.COW, 8, 4, 4));
        this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.SPIDER, 100, 4, 4));
        this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.ZOMBIE, 95, 4, 4));
        this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.SKELETON, 100, 4, 4));
        this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.CREEPER, 100, 4, 4));
        this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.SLIME, 100, 4, 4));
        
        this.setBeachBiome(BOPBiomes.origin_beach);
        this.setRiverBiome((Biome)null);
    }
    
    @OnlyIn(Dist.CLIENT)
    @Override
    public int getSkyColor()
    {
       return 0x8CBDFF;
    }
    
    @OnlyIn(Dist.CLIENT)
    @Override
    public int getGrassColor(double x, double z)
    {
    	return 0x9AFF5F;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public int getFoliageColor()
    {
    	return 0x3AFF00;
    }
}
