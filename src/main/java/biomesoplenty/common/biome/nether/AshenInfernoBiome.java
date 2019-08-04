package biomesoplenty.common.biome.nether;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.biome.NetherBiomeBOP;
import biomesoplenty.common.world.gen.feature.BOPBiomeFeatures;
import biomesoplenty.common.world.gen.feature.SplotchConfig;
import com.google.common.collect.Lists;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.fluid.Fluids;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placement.*;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;

public class AshenInfernoBiome extends NetherBiomeBOP
{
    public AshenInfernoBiome()
    {
        super((new Builder()).surfaceBuilder(SurfaceBuilder.NETHER, SurfaceBuilder.NETHERRACK_CONFIG).precipitation(RainType.NONE).category(Category.NETHER).depth(0.1F).scale(0.2F).temperature(2.0F).downfall(0.0F).waterColor(4159204).waterFogColor(329011).parent((String)null));

        //Terrain
        this.addStructure(Feature.NETHER_BRIDGE, IFeatureConfig.NO_FEATURE_CONFIG);
        this.addCarver(GenerationStage.Carving.AIR, createCarver(WorldCarver.HELL_CAVE, new ProbabilityConfig(0.2F)));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, createDecoratedFeature(Feature.SPRING_FEATURE, new LiquidsConfig(Fluids.LAVA.getDefaultState()), Placement.COUNT_VERY_BIASED_RANGE, new CountRangeConfig(20, 8, 16, 256)));

        DefaultBiomeFeatures.addMushrooms(this);

        this.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, createDecoratedFeature(Feature.NETHER_BRIDGE, IFeatureConfig.NO_FEATURE_CONFIG, Placement.NOPE, IPlacementConfig.NO_PLACEMENT_CONFIG));
        this.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, createDecoratedFeature(Feature.NETHER_SPRING, new HellLavaConfig(false), Placement.COUNT_RANGE, new CountRangeConfig(24, 4, 8, 128)));
        this.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, createDecoratedFeature(Feature.HELL_FIRE, IFeatureConfig.NO_FEATURE_CONFIG, Placement.HELL_FIRE, new FrequencyConfig(400)));

        this.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, createDecoratedFeature(BOPBiomeFeatures.ASH_SPLATTER, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(250)));

        //Base Decorations
        this.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, createDecoratedFeature(Feature.ORE, new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NETHERRACK, Blocks.NETHER_QUARTZ_ORE.getDefaultState(), 14), Placement.COUNT_RANGE, new CountRangeConfig(16, 10, 20, 128)));
        this.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, createDecoratedFeature(Feature.ORE, new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NETHERRACK, Blocks.MAGMA_BLOCK.getDefaultState(), 33), Placement.MAGMA, new FrequencyConfig(10)));
        this.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, createDecoratedFeature(Feature.NETHER_SPRING, new HellLavaConfig(true), Placement.COUNT_RANGE, new CountRangeConfig(16, 10, 20, 128)));

        //Entities
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.GHAST, 40, 4, 4));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.ZOMBIE_PIGMAN, 80, 4, 4));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.MAGMA_CUBE, 20, 4, 4));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.ENDERMAN, 1, 4, 4));
    }
}
