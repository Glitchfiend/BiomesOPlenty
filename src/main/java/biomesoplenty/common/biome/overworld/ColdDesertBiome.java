/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.biome.overworld;

import com.google.common.collect.Lists;

import biomesoplenty.api.enums.BOPClimates;
import biomesoplenty.common.biome.BiomeBOP;
import biomesoplenty.common.world.gen.feature.BOPBiomeFeatures;
import biomesoplenty.common.world.gen.feature.WastelandGrassFeature;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.init.Fluids;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.LakesConfig;
import net.minecraft.world.gen.feature.LiquidsConfig;
import net.minecraft.world.gen.feature.MinableConfig;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.feature.SphereReplaceConfig;
import net.minecraft.world.gen.feature.structure.IglooConfig;
import net.minecraft.world.gen.feature.structure.MineshaftConfig;
import net.minecraft.world.gen.feature.structure.MineshaftStructure;
import net.minecraft.world.gen.feature.structure.StrongholdConfig;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.DepthAverageConfig;
import net.minecraft.world.gen.placement.DungeonRoomConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.LakeChanceConfig;
import net.minecraft.world.gen.surfacebuilders.CompositeSurfaceBuilder;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ColdDesertBiome extends BiomeBOP
{
    public ColdDesertBiome()
    {
        super((new Biome.BiomeBuilder()).surfaceBuilder(new CompositeSurfaceBuilder(BOPBiomeFeatures.COLD_DESERT_SURFACE_BUILDER, GRAVEL_SURFACE)).precipitation(Biome.RainType.NONE).category(Biome.Category.ICY).depth(0.0F).scale(0.0F).temperature(-0.25F).downfall(0.0F).waterColor(4159204).waterFogColor(329011).parent((String)null));

        // Mineshafts and Strongholds
        this.addStructure(Feature.IGLOO, new IglooConfig());
        this.addStructure(Feature.MINESHAFT, new MineshaftConfig(0.004D, MineshaftStructure.Type.NORMAL));
        this.addStructure(Feature.STRONGHOLD, new StrongholdConfig());

        this.addCarver(GenerationStage.Carving.AIR, createWorldCarverWrapper(CAVE_WORLD_CARVER, new ProbabilityConfig(0.14285715F)));
        this.addCarver(GenerationStage.Carving.AIR, createWorldCarverWrapper(CANYON_WORLD_CARVER, new ProbabilityConfig(0.02F)));

        this.addStructureFeatures();

        // Lakes
        this.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, createCompositeFeature(Feature.LAKES, new LakesConfig(Blocks.LAVA), LAVA_LAKE, new LakeChanceConfig(80)));
        
        // Underground
        this.addFeature(GenerationStage.Decoration.UNDERGROUND_STRUCTURES, createCompositeFeature(Feature.DUNGEONS, IFeatureConfig.NO_FEATURE_CONFIG, DUNGEON_ROOM, new DungeonRoomConfig(8)));
        this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, createCompositeFeature(Feature.MINABLE, new MinableConfig(MinableConfig.IS_ROCK, Blocks.DIRT.getDefaultState(), 33), COUNT_RANGE, new CountRangeConfig(10, 0, 0, 256)));
        this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, createCompositeFeature(Feature.MINABLE, new MinableConfig(MinableConfig.IS_ROCK, Blocks.GRAVEL.getDefaultState(), 33), COUNT_RANGE, new CountRangeConfig(8, 0, 0, 256)));
        this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, createCompositeFeature(Feature.MINABLE, new MinableConfig(MinableConfig.IS_ROCK, Blocks.GRANITE.getDefaultState(), 33), COUNT_RANGE, new CountRangeConfig(10, 0, 0, 80)));
        this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, createCompositeFeature(Feature.MINABLE, new MinableConfig(MinableConfig.IS_ROCK, Blocks.DIORITE.getDefaultState(), 33), COUNT_RANGE, new CountRangeConfig(10, 0, 0, 80)));
        this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, createCompositeFeature(Feature.MINABLE, new MinableConfig(MinableConfig.IS_ROCK, Blocks.ANDESITE.getDefaultState(), 33), COUNT_RANGE, new CountRangeConfig(10, 0, 0, 80)));
        this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, createCompositeFeature(Feature.MINABLE, new MinableConfig(MinableConfig.IS_ROCK, Blocks.COAL_ORE.getDefaultState(), 17), COUNT_RANGE, new CountRangeConfig(20, 0, 0, 128)));
        this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, createCompositeFeature(Feature.MINABLE, new MinableConfig(MinableConfig.IS_ROCK, Blocks.IRON_ORE.getDefaultState(), 9), COUNT_RANGE, new CountRangeConfig(20, 0, 0, 64)));
        this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, createCompositeFeature(Feature.MINABLE, new MinableConfig(MinableConfig.IS_ROCK, Blocks.GOLD_ORE.getDefaultState(), 9), COUNT_RANGE, new CountRangeConfig(2, 0, 0, 32)));
        this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, createCompositeFeature(Feature.MINABLE, new MinableConfig(MinableConfig.IS_ROCK, Blocks.REDSTONE_ORE.getDefaultState(), 8), COUNT_RANGE, new CountRangeConfig(8, 0, 0, 16)));
        this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, createCompositeFeature(Feature.MINABLE, new MinableConfig(MinableConfig.IS_ROCK, Blocks.DIAMOND_ORE.getDefaultState(), 8), COUNT_RANGE, new CountRangeConfig(1, 0, 0, 16)));
        this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, createCompositeFeature(Feature.MINABLE, new MinableConfig(MinableConfig.IS_ROCK, Blocks.LAPIS_ORE.getDefaultState(), 7), DEPTH_AVERAGE, new DepthAverageConfig(1, 16, 16)));
        this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, createCompositeFeature(Feature.SPHERE_REPLACE, new SphereReplaceConfig(Blocks.SAND, 7, 2, Lists.newArrayList(Blocks.DIRT, Blocks.GRASS_BLOCK)), TOP_SOLID, new FrequencyConfig(3)));
        this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, createCompositeFeature(Feature.SPHERE_REPLACE, new SphereReplaceConfig(Blocks.CLAY, 4, 1, Lists.newArrayList(Blocks.DIRT, Blocks.CLAY)), TOP_SOLID, new FrequencyConfig(1)));
        this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, createCompositeFeature(Feature.SPHERE_REPLACE, new SphereReplaceConfig(Blocks.GRAVEL, 6, 2, Lists.newArrayList(Blocks.DIRT, Blocks.GRASS_BLOCK)), TOP_SOLID, new FrequencyConfig(1)));
        
        // Vegetation
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, createCompositeFeature(new WastelandGrassFeature(), IFeatureConfig.NO_FEATURE_CONFIG, TWICE_SURFACE, new FrequencyConfig(1)));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, createCompositeFeature(Feature.LIQUIDS, new LiquidsConfig(Fluids.WATER), HEIGHT_BIASED_RANGE, new CountRangeConfig(50, 8, 8, 256)));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, createCompositeFeature(Feature.LIQUIDS, new LiquidsConfig(Fluids.LAVA), HEIGHT_VERY_BIASED_RANGE, new CountRangeConfig(20, 8, 16, 256)));
        this.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, createCompositeFeature(Feature.FOSSILS, IFeatureConfig.NO_FEATURE_CONFIG, WITH_CHANCE, new ChanceConfig(64)));
        this.addFeature(GenerationStage.Decoration.TOP_LAYER_MODIFICATION, createCompositeFeature(Feature.ICE_AND_SNOW, IFeatureConfig.NO_FEATURE_CONFIG, PASSTHROUGH, IPlacementConfig.NO_PLACEMENT_CONFIG));

        // Entity spawning
        this.addSpawn(EnumCreatureType.CREATURE, new Biome.SpawnListEntry(EntityType.POLAR_BEAR, 1, 1, 2));
        this.addSpawn(EnumCreatureType.AMBIENT, new Biome.SpawnListEntry(EntityType.BAT, 10, 8, 8));
        this.addSpawn(EnumCreatureType.MONSTER, new Biome.SpawnListEntry(EntityType.SPIDER, 100, 4, 4));
        this.addSpawn(EnumCreatureType.MONSTER, new Biome.SpawnListEntry(EntityType.ZOMBIE, 95, 4, 4));
        this.addSpawn(EnumCreatureType.MONSTER, new Biome.SpawnListEntry(EntityType.ZOMBIE_VILLAGER, 5, 1, 1));
        this.addSpawn(EnumCreatureType.MONSTER, new Biome.SpawnListEntry(EntityType.SKELETON, 100, 4, 4));
        this.addSpawn(EnumCreatureType.MONSTER, new Biome.SpawnListEntry(EntityType.CREEPER, 100, 4, 4));
        this.addSpawn(EnumCreatureType.MONSTER, new Biome.SpawnListEntry(EntityType.SLIME, 100, 4, 4));
        this.addSpawn(EnumCreatureType.MONSTER, new Biome.SpawnListEntry(EntityType.ENDERMAN, 10, 1, 4));
        this.addSpawn(EnumCreatureType.MONSTER, new Biome.SpawnListEntry(EntityType.WITCH, 5, 1, 1));
        this.addSpawn(EnumCreatureType.MONSTER, new Biome.SpawnListEntry(EntityType.STRAY, 80, 4, 4));
        
        this.addWeight(BOPClimates.ICE_CAP, 10);
        this.setBeachBiome(Biomes.SNOWY_BEACH);
        this.setRiverBiome(Biomes.FROZEN_RIVER);
        this.canSpawnInBiome = false;
    }
    
    @OnlyIn(Dist.CLIENT)
    public int getGrassColor(BlockPos pos)
    {
    	return 0xAD9364;
    }

    @OnlyIn(Dist.CLIENT)
    public int getFoliageColor(BlockPos pos)
    {
    	return 0xB5A76C;
    }
}
