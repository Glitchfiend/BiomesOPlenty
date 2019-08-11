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
import biomesoplenty.common.biome.BiomeBOP;
import biomesoplenty.common.world.gen.feature.BOPBiomeFeatures;
import biomesoplenty.common.world.gen.feature.StandardGrassFeature;
import com.google.common.collect.Lists;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.structure.MineshaftConfig;
import net.minecraft.world.gen.feature.structure.MineshaftStructure;
import net.minecraft.world.gen.placement.*;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class MysticGroveBiome extends BiomeBOP
{
    public MysticGroveBiome()
    {
        super((new Biome.Builder()).surfaceBuilder(new ConfiguredSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_DIRT_GRAVEL_CONFIG)).precipitation(Biome.RainType.RAIN).category(Biome.Category.FOREST).depth(0.1F).scale(0.1F).temperature(0.7F).downfall(0.8F).waterColor(0x9C3FE4).waterFogColor(0x2E0533).parent((String)null));

        // Structures
        this.addStructure(Feature.MINESHAFT, new MineshaftConfig(0.004D, MineshaftStructure.Type.NORMAL));
        this.addStructure(Feature.STRONGHOLD, IFeatureConfig.NO_FEATURE_CONFIG);

        // Underground
        DefaultBiomeFeatures.addCarvers(this);
        DefaultBiomeFeatures.addStructures(this);

        this.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, Biome.createDecoratedFeature(Feature.LAKE, new LakesConfig(Blocks.WATER.getDefaultState()), Placement.WATER_LAKE, new LakeChanceConfig(4)));

        DefaultBiomeFeatures.addMonsterRooms(this);
        DefaultBiomeFeatures.addStoneVariants(this);
        DefaultBiomeFeatures.addOres(this);

        this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.DISK, new SphereReplaceConfig(BOPBlocks.white_sand.getDefaultState(), 7, 2, Lists.newArrayList(Blocks.DIRT.getDefaultState(), Blocks.GRASS_BLOCK.getDefaultState())), Placement.COUNT_TOP_SOLID, new FrequencyConfig(3)));
        this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.DISK, new SphereReplaceConfig(Blocks.CLAY.getDefaultState(), 4, 1, Lists.newArrayList(Blocks.DIRT.getDefaultState(), Blocks.CLAY.getDefaultState())), Placement.COUNT_TOP_SOLID, new FrequencyConfig(1)));

        ////////////////////////////////////////////////////////////

        // Vegetation
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.RANDOM_SELECTOR, new MultipleRandomFeatureConfig(new Feature[]{BOPBiomeFeatures.GIANT_TREE, BOPBiomeFeatures.TALL_SWAMP_TREE, BOPBiomeFeatures.BIG_FLOWERING_OAK_TREE, BOPBiomeFeatures.JACARANDA_TREE, BOPBiomeFeatures.BIG_MAGIC_TREE}, new IFeatureConfig[]{IFeatureConfig.NO_FEATURE_CONFIG, IFeatureConfig.NO_FEATURE_CONFIG, IFeatureConfig.NO_FEATURE_CONFIG, IFeatureConfig.NO_FEATURE_CONFIG, IFeatureConfig.NO_FEATURE_CONFIG}, new float[]{0.025F, 0.1F, 0.2F, 0.3F, 0.3F}, BOPBiomeFeatures.MAGIC_TREE, IFeatureConfig.NO_FEATURE_CONFIG), Placement.COUNT_EXTRA_HEIGHTMAP, new AtSurfaceWithExtraConfig(10, 0.5F, 1)));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(BOPBiomeFeatures.MYSTIC_GROVE_FLOWERS, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_HEIGHTMAP_32, new FrequencyConfig(18)));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.HUGE_RED_MUSHROOM, new BigMushroomFeatureConfig(false), Placement.COUNT_EXTRA_HEIGHTMAP, new AtSurfaceWithExtraConfig(1, 0.1F, 1)));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.DOUBLE_PLANT, new DoublePlantConfig(Blocks.LILAC.getDefaultState()), Placement.COUNT_HEIGHTMAP_32, new FrequencyConfig(1)));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(new StandardGrassFeature(NoFeatureConfig::deserialize), IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(15)));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.BUSH, new BushConfig(BOPBlocks.sprout.getDefaultState()), Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(15)));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.BUSH, new BushConfig(BOPBlocks.watergrass.getDefaultState()), Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(10)));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.BUSH, new BushConfig(Blocks.BROWN_MUSHROOM.getDefaultState()), Placement.CHANCE_HEIGHTMAP_DOUBLE, new ChanceConfig(15)));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.BUSH, new BushConfig(Blocks.RED_MUSHROOM.getDefaultState()), Placement.CHANCE_HEIGHTMAP_DOUBLE, new ChanceConfig(30)));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.BUSH, new BushConfig(BOPBlocks.glowshroom.getDefaultState()), Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(5)));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.SEAGRASS, new SeaGrassConfig(32, 0.4D), Placement.TOP_SOLID_HEIGHTMAP, IPlacementConfig.NO_PLACEMENT_CONFIG));

        ////////////////////////////////////////////////////////////

        // Other Features
        DefaultBiomeFeatures.addSprings(this);
        DefaultBiomeFeatures.addFreezeTopLayer(this);

        // Entities
        this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.RABBIT, 4, 2, 3));
        this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.FOX, 8, 2, 4));
        this.addSpawn(EntityClassification.AMBIENT, new Biome.SpawnListEntry(EntityType.BAT, 10, 8, 8));
        this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.SLIME, 100, 4, 4));
        this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.WITCH, 15, 1, 1));
        
        this.addWeight(BOPClimates.WET_TEMPERATE, 1);
        this.setBeachBiome((Biome)null);
        this.setRiverBiome((Biome)null);
    }
    
    @OnlyIn(Dist.CLIENT)
    @Override
    public int getSkyColorByTemp(float currentTemperature)
    {
       return 0x66DBFF;
    }
    
    @OnlyIn(Dist.CLIENT)
    @Override
    public int getGrassColor(BlockPos pos)
    {
    	return 0x69CFDB;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public int getFoliageColor(BlockPos pos)
    {
    	return 0x70E0B5;
    }
}
