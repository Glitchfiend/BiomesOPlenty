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
import biomesoplenty.api.enums.BOPClimates;
import biomesoplenty.common.biome.BiomeBOP;
import biomesoplenty.common.world.biome.BiomeFeatureHelper;
import biomesoplenty.common.world.gen.feature.BOPBiomeFeatures;
import biomesoplenty.common.world.gen.feature.StandardGrassFeature;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.structure.MineshaftConfig;
import net.minecraft.world.gen.feature.structure.MineshaftStructure;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class GhostForestBiome extends BiomeBOP
{
    public GhostForestBiome()
    {
        super((new Builder()).surfaceBuilder(new ConfiguredSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.CONFIG_GRASS)).precipitation(RainType.RAIN).biomeCategory(Category.SWAMP).depth(-0.125F).scale(0.0F).temperature(0.5F).downfall(0.6F).waterColor(0x448FBD).waterFogColor(0x061326).parent((String)null));

        // Structures
        this.addStructureStart(Feature.MINESHAFT.configured(new MineshaftConfig(0.004D, MineshaftStructure.Type.NORMAL)));
        this.addStructureStart(Feature.STRONGHOLD.configured(IFeatureConfig.NONE));

        // Underground
        DefaultBiomeFeatures.addDefaultCarvers(this);
        DefaultBiomeFeatures.addStructureFeaturePlacement(this);
        DefaultBiomeFeatures.addDefaultLakes(this);

        this.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, Feature.LAKE.configured(new BlockStateFeatureConfig(Blocks.CLAY.defaultBlockState())).decorated(Placement.WATER_LAKE.configured(new ChanceConfig(3))));

        DefaultBiomeFeatures.addDefaultMonsterRoom(this);
        DefaultBiomeFeatures.addDefaultUndergroundVariety(this);
        DefaultBiomeFeatures.addDefaultOres(this);

        this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.DISK.configured(new SphereReplaceConfig(Blocks.CLAY.defaultBlockState(), 5, 1, Lists.newArrayList(Blocks.DIRT.defaultBlockState(), Blocks.GRASS_BLOCK.defaultBlockState()))).decorated(Placement.COUNT_TOP_SOLID.configured(new FrequencyConfig(2))));
        this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.DISK.configured(new SphereReplaceConfig(Blocks.GRAVEL.defaultBlockState(), 6, 2, Lists.newArrayList(Blocks.DIRT.defaultBlockState(), Blocks.GRASS_BLOCK.defaultBlockState()))).decorated(Placement.COUNT_TOP_SOLID.configured(new FrequencyConfig(2))));
        this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.DISK.configured(new SphereReplaceConfig(BOPBlocks.mud.defaultBlockState(), 4, 2, Lists.newArrayList(new BlockState[]{Blocks.DIRT.defaultBlockState(), Blocks.GRASS_BLOCK.defaultBlockState()}))).decorated(Placement.COUNT_TOP_SOLID.configured(new FrequencyConfig(2))));

        ////////////////////////////////////////////////////////////

        // Vegetation
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(BOPBiomeFeatures.DARK_OAK_TWIGLET_TREE.configured(DefaultBiomeFeatures.NORMAL_TREE_CONFIG).weighted(0.4F), BOPBiomeFeatures.DEAD_TREE.configured(DefaultBiomeFeatures.NORMAL_TREE_CONFIG).weighted(0.3F), BOPBiomeFeatures.DEAD_TWIGLET_TREE_TALL.configured(DefaultBiomeFeatures.NORMAL_TREE_CONFIG).weighted(0.1F)), BOPBiomeFeatures.DEAD_TWIGLET_TREE.configured(DefaultBiomeFeatures.NORMAL_TREE_CONFIG))).decorated(Placement.COUNT_EXTRA_HEIGHTMAP.configured(new AtSurfaceWithExtraConfig(4, 0.5F, 1))));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, new StandardGrassFeature(NoFeatureConfig::deserialize).configured(IFeatureConfig.NONE).decorated(Placement.COUNT_HEIGHTMAP_DOUBLE.configured(new FrequencyConfig(8))));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, BOPBiomeFeatures.SCRUB.configured(IFeatureConfig.NONE).decorated(Placement.COUNT_HEIGHTMAP_DOUBLE.configured(new FrequencyConfig(25))));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.configured(BiomeFeatureHelper.createClusterConfigurationDoubleWater(BOPBlocks.watergrass.defaultBlockState())).decorated(Placement.COUNT_HEIGHTMAP_DOUBLE.configured(new FrequencyConfig(25))));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.configured(BiomeFeatureHelper.createClusterConfigurationDoubleWater(BOPBlocks.reed.defaultBlockState())).decorated(Placement.COUNT_HEIGHTMAP_DOUBLE.configured(new FrequencyConfig(2))));

        ////////////////////////////////////////////////////////////

        // Other Features
        DefaultBiomeFeatures.addDefaultSprings(this);
        DefaultBiomeFeatures.addSurfaceFreezing(this);

        // Entities
        this.addSpawn(EntityClassification.AMBIENT, new SpawnListEntry(EntityType.BAT, 10, 8, 8));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.SPIDER, 100, 4, 4));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.ZOMBIE, 95, 4, 4));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.ZOMBIE_VILLAGER, 5, 1, 1));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.SKELETON, 100, 4, 4));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.CREEPER, 100, 4, 4));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.SLIME, 100, 4, 4));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.ENDERMAN, 10, 1, 4));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.WITCH, 5, 1, 1));
        
        this.addWeight(BOPClimates.WET_TEMPERATE, 2);
        this.setBeachBiome(BOPBiomes.gravel_beach);
    }
    
    @OnlyIn(Dist.CLIENT)
    @Override
    public int getGrassColor(double x, double z)
    {
    	return 0x9BAF6B;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public int getFoliageColor()
    {
    	return 0x88AD66;
    }
}
