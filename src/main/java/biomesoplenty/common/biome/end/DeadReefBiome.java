package biomesoplenty.common.biome.end;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.biome.EndBiomeBOP;
import biomesoplenty.common.world.gen.feature.BOPBiomeFeatures;
import net.minecraft.block.AbstractCoralPlantBlock;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.BushConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;

public class DeadReefBiome extends EndBiomeBOP
{
	public DeadReefBiome()
	{
        super((new Builder()).surfaceBuilder(new ConfiguredSurfaceBuilder<>(SurfaceBuilder.DEFAULT, SurfaceBuilder.END_STONE_CONFIG)).precipitation(RainType.NONE).category(Category.THEEND).depth(0.1F).scale(0.2F).temperature(0.5F).downfall(0.5F).waterColor(4159204).waterFogColor(329011).parent((String)null));

        this.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, Biome.createDecoratedFeature(Feature.END_CITY, IFeatureConfig.NO_FEATURE_CONFIG, Placement.NOPE, IPlacementConfig.NO_PLACEMENT_CONFIG));

        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(BOPBiomeFeatures.DEAD_CORAL_SPIKES, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(5)));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(BOPBiomeFeatures.DEAD_CORAL_SPLATTER, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(200)));

        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.BUSH, new BushConfig(Blocks.DEAD_BRAIN_CORAL.getDefaultState().with(AbstractCoralPlantBlock.WATERLOGGED, false)), Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(75)));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.BUSH, new BushConfig(Blocks.DEAD_BUBBLE_CORAL.getDefaultState().with(AbstractCoralPlantBlock.WATERLOGGED, false)), Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(75)));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.BUSH, new BushConfig(Blocks.DEAD_FIRE_CORAL.getDefaultState().with(AbstractCoralPlantBlock.WATERLOGGED, false)), Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(75)));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.BUSH, new BushConfig(Blocks.DEAD_HORN_CORAL.getDefaultState().with(AbstractCoralPlantBlock.WATERLOGGED, false)), Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(75)));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.BUSH, new BushConfig(Blocks.DEAD_TUBE_CORAL.getDefaultState().with(AbstractCoralPlantBlock.WATERLOGGED, false)), Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(75)));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.BUSH, new BushConfig(Blocks.DEAD_BRAIN_CORAL_FAN.getDefaultState().with(AbstractCoralPlantBlock.WATERLOGGED, false)), Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(75)));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.BUSH, new BushConfig(Blocks.DEAD_BUBBLE_CORAL_FAN.getDefaultState().with(AbstractCoralPlantBlock.WATERLOGGED, false)), Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(75)));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.BUSH, new BushConfig(Blocks.DEAD_FIRE_CORAL_FAN.getDefaultState().with(AbstractCoralPlantBlock.WATERLOGGED, false)), Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(75)));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.BUSH, new BushConfig(Blocks.DEAD_HORN_CORAL_FAN.getDefaultState().with(AbstractCoralPlantBlock.WATERLOGGED, false)), Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(75)));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.BUSH, new BushConfig(Blocks.DEAD_TUBE_CORAL_FAN.getDefaultState().with(AbstractCoralPlantBlock.WATERLOGGED, false)), Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(75)));

        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.ENDERMAN, 10, 4, 4));
	}
}
