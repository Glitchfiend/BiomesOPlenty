/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.biome.overworld;

import biomesoplenty.api.enums.BOPClimates;
import biomesoplenty.common.biome.BiomeBOP;
import biomesoplenty.common.world.gen.feature.BOPBiomeFeatures;
import biomesoplenty.common.world.gen.feature.WastelandGrassFeature;
import com.google.common.collect.ImmutableList;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.MultipleRandomFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.MineshaftConfig;
import net.minecraft.world.gen.feature.structure.MineshaftStructure;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class WastelandBiome extends BiomeBOP
{
    public WastelandBiome()
    {
        super((new Biome.Builder()).surfaceBuilder(new ConfiguredSurfaceBuilder(SurfaceBuilder.DEFAULT, BOPBiomeFeatures.DRIED_SALT_SURFACE)).precipitation(Biome.RainType.NONE).biomeCategory(Biome.Category.DESERT).depth(-0.05F).scale(-0.05F).temperature(2.0F).downfall(0.0F).waterColor(0x433721).waterFogColor(0x0C0C03).parent((String)null));

        // Structures
        this.addStructureStart(Feature.MINESHAFT.configured(new MineshaftConfig(0.004D, MineshaftStructure.Type.NORMAL)));
        this.addStructureStart(Feature.STRONGHOLD.configured(IFeatureConfig.NONE));

        // Underground
        DefaultBiomeFeatures.addDefaultCarvers(this);
        DefaultBiomeFeatures.addStructureFeaturePlacement(this);
        DefaultBiomeFeatures.addDefaultLakes(this);
        DefaultBiomeFeatures.addDefaultMonsterRoom(this);
        DefaultBiomeFeatures.addDefaultUndergroundVariety(this);
        DefaultBiomeFeatures.addDefaultOres(this);

        ////////////////////////////////////////////////////////////

        // Vegetation
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(BOPBiomeFeatures.DEAD_TREE_WASTELAND.configured(DefaultBiomeFeatures.NORMAL_TREE_CONFIG).weighted(0.2F)), BOPBiomeFeatures.DYING_TREE_WASTELAND.configured(DefaultBiomeFeatures.NORMAL_TREE_CONFIG))).decorated(Placement.COUNT_EXTRA_HEIGHTMAP.configured(new AtSurfaceWithExtraConfig((int)0.75F, 0.1F, 1))));

        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, BOPBiomeFeatures.WASTELAND_FLOWERS.configured(IFeatureConfig.NONE).decorated(Placement.COUNT_HEIGHTMAP_32.configured(new FrequencyConfig(1))));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, new WastelandGrassFeature(NoFeatureConfig::deserialize).configured(IFeatureConfig.NONE).decorated(Placement.COUNT_HEIGHTMAP_DOUBLE.configured(new FrequencyConfig(2))));

        ////////////////////////////////////////////////////////////

        // Other Features
        DefaultBiomeFeatures.addDefaultSprings(this);
        DefaultBiomeFeatures.addSwampExtraDecoration(this);
        DefaultBiomeFeatures.addSurfaceFreezing(this);

        // Entities
        this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.HUSK, 80, 4, 4));
        this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.SKELETON, 100, 4, 4));
        this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.SLIME, 100, 4, 4));
        this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.ENDERMAN, 10, 1, 4));
        
        this.addWeight(BOPClimates.WASTELAND, 10);
        this.setBeachBiome((Biome)null);
        this.setRiverBiome((Biome)null);
    }
    
    @OnlyIn(Dist.CLIENT)
    @Override
    public int getSkyColor()
    {
       return 0x70ADEF;
    }
    
    @OnlyIn(Dist.CLIENT)
    @Override
    public int getGrassColor(double x, double z)
    {
    	return 0xAD9364;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public int getFoliageColor()
    {
    	return 0xB5A76C;
    }
}
