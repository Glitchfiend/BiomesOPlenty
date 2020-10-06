/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.biome.overworld;

import biomesoplenty.common.biome.BiomeTemplate;
import biomesoplenty.common.world.gen.carver.BOPConfiguredCarvers;
import biomesoplenty.common.world.gen.carver.BOPWorldCarvers;
import biomesoplenty.common.world.gen.feature.BOPConfiguredFeatures;
import biomesoplenty.common.world.gen.feature.BOPFeatures;
import biomesoplenty.common.world.gen.surfacebuilders.BOPSurfaceBuilders;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;

public class OriginValleyBiome extends BiomeTemplate
{
    public OriginValleyBiome()
    {
        this.setBeachBiome(null);
        this.setRiverBiome(null);
    }

    @Override
    protected void configureBiome(Biome.Builder builder)
    {
        builder.precipitation(Biome.RainType.RAIN).biomeCategory(Biome.Category.NONE).depth(0.1F).scale(0.2F).temperature(0.6F).downfall(0.6F);

        builder.specialEffects((new BiomeAmbience.Builder()).waterColor(0x0E31FF).waterFogColor(0x070059).fogColor(0xB0CFFF).skyColor(0x8CBDFF).grassColorOverride(0x9AFF5F).foliageColorOverride(0x3AFF00).ambientMoodSound(MoodSoundAmbience.LEGACY_CAVE_SETTINGS).build());
    }

    @Override
    protected void configureGeneration(BiomeGenerationSettings.Builder builder)
    {
        builder.surfaceBuilder(new ConfiguredSurfaceBuilder(BOPSurfaceBuilders.ORIGIN_VALLEY, BOPSurfaceBuilders.ORIGIN_GRASS_SURFACE));

        // Underground
        builder.addCarver(GenerationStage.Carving.AIR, BOPConfiguredCarvers.ORIGIN_CAVE);
        DefaultBiomeFeatures.addDefaultMonsterRoom(builder);

        builder.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Features.DISK_CLAY);

        builder.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Features.ORE_DIRT);
        builder.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Features.ORE_GRAVEL);
        builder.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Features.ORE_COAL);
        builder.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Features.ORE_IRON);
        builder.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Features.ORE_GOLD);
        builder.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Features.ORE_REDSTONE);
        builder.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Features.ORE_DIAMOND);

        ////////////////////////////////////////////////////////////

        // Vegetation
        builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, BOPConfiguredFeatures.ORIGIN_VALLEY_TREES);
        builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, BOPConfiguredFeatures.ORIGIN_FLOWERS);

        builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.PATCH_SUGAR_CANE);

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
        builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.SPIDER, 100, 4, 4));
        builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.ZOMBIE, 95, 4, 4));
        builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.SKELETON, 100, 4, 4));
        builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.CREEPER, 100, 4, 4));
        builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.SLIME, 100, 4, 4));
    }
}
