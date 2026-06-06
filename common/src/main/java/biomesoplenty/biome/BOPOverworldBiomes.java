/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.biome;

import biomesoplenty.api.sound.BOPSounds;
import biomesoplenty.worldgen.carver.BOPConfiguredCarvers;
import biomesoplenty.worldgen.placement.BOPCavePlacements;
import biomesoplenty.worldgen.placement.BOPMiscOverworldPlacements;
import biomesoplenty.worldgen.placement.BOPVegetationPlacements;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.Carvers;
import net.minecraft.data.worldgen.placement.AquaticPlacements;
import net.minecraft.data.worldgen.placement.MiscOverworldPlacements;
import net.minecraft.data.worldgen.placement.OrePlacements;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.Musics;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.ARGB;
import net.minecraft.util.Mth;
import net.minecraft.world.attribute.*;
import net.minecraft.world.attribute.modifier.FloatModifier;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class BOPOverworldBiomes
{
    protected static int calculateSkyColor(float color)
    {
        float $$1 = color / 3.0F;
        $$1 = Mth.clamp($$1, -1.0F, 1.0F);
        return Mth.hsvToRgb(0.62222224F - $$1 * 0.05F, 0.5F + $$1 * 0.1F, 1.0F);
    }

    private static Biome.BiomeBuilder baseBiome(float p_453538_, float p_455956_) {
        return new Biome.BiomeBuilder()
            .hasPrecipitation(true)
            .temperature(p_453538_)
            .downfall(p_455956_)
            .setAttribute(EnvironmentAttributes.SKY_COLOR, calculateSkyColor(p_453538_))
            .specialEffects(new BiomeSpecialEffects.Builder().waterColor(4159204).build());
    }

    private static void addFeature(BiomeGenerationSettings.Builder builder, GenerationStep.Decoration step, ResourceKey<PlacedFeature> feature)
    {
        builder.addFeature(step, feature);
    }

    private static void globalOverworldGeneration(BiomeGenerationSettings.Builder builder)
    {
        BiomeDefaultFeatures.addDefaultCarversAndLakes(builder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(builder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(builder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(builder);
        BiomeDefaultFeatures.addDefaultSprings(builder);
        BiomeDefaultFeatures.addSurfaceFreezing(builder);
    }

    private static void globalOverworldGenerationNoLavaLakes(BiomeGenerationSettings.Builder builder)
    {
        builder.addCarver(Carvers.CAVE);
        builder.addCarver(Carvers.CAVE_EXTRA_UNDERGROUND);
        builder.addCarver(Carvers.CANYON);
        builder.addFeature(GenerationStep.Decoration.LAKES, MiscOverworldPlacements.LAKE_LAVA_UNDERGROUND);

        BiomeDefaultFeatures.addDefaultCrystalFormations(builder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(builder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(builder);
        BiomeDefaultFeatures.addDefaultSprings(builder);
        BiomeDefaultFeatures.addSurfaceFreezing(builder);
    }

    public static Biome auroralGarden(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.caveSpawns(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.CREATURE, 8, new MobSpawnSettings.SpawnerData(EntityType.FOX, 2, 4));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        globalOverworldGeneration(biomeBuilder);
        addFeature(biomeBuilder, GenerationStep.Decoration.LOCAL_MODIFICATIONS, BOPVegetationPlacements.PRISMARINE_SPIKE);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_AURORAL_GARDEN);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_VIOLET);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_ICY_IRIS);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_12);

        return baseBiome(-0.25F, 0.5F)
            .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 0x050533)
            .setAttribute(EnvironmentAttributes.FOG_COLOR, 0xF0FDFD)
            .setAttribute(EnvironmentAttributes.SKY_COLOR, 0xBDECFC)
            .setAttribute(EnvironmentAttributes.CLOUD_COLOR, ARGB.white(0.2F))
            .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(SoundEvents.MUSIC_BIOME_CHERRY_GROVE))
            .specialEffects(
                new BiomeSpecialEffects.Builder()
                    .waterColor(0x3938C9)
                    .grassColorOverride(0xDBFCFC)
                    .foliageColorOverride(0xD1FFFF)
                    .build()
            )
            .mobSpawnSettings(spawnBuilder.build())
            .generationSettings(biomeBuilder.build()).build();
    }

    public static Biome bayou(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder, 70);
        spawnBuilder.addSpawn(MobCategory.CREATURE, 10, new MobSpawnSettings.SpawnerData(EntityType.PIG, 4, 4));
        spawnBuilder.addSpawn(MobCategory.CREATURE, 10, new MobSpawnSettings.SpawnerData(EntityType.FROG, 2, 5));
        spawnBuilder.addSpawn(MobCategory.MONSTER, 1, new MobSpawnSettings.SpawnerData(EntityType.SLIME, 1, 1));
        spawnBuilder.addSpawn(MobCategory.MONSTER, 30, new MobSpawnSettings.SpawnerData(EntityType.BOGGED, 4, 4));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addSwampClayDisk(biomeBuilder);
        addFeature(biomeBuilder, GenerationStep.Decoration.UNDERGROUND_ORES, BOPMiscOverworldPlacements.DISK_MUD);
        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_SUGAR_CANE);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, AquaticPlacements.SEAGRASS_SWAMP);

        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_BAYOU);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_CATTAIL_NORMAL);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_FERN_8);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_LARGE_FERN_NORMAL);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.LILY_PAD_10);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_12);

        return baseBiome(0.95F, 0.9F)
            .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 0x0C211C)
            .modifyAttribute(EnvironmentAttributes.WATER_FOG_END_DISTANCE, FloatModifier.MULTIPLY, 0.85F)
            .modifyAttribute(EnvironmentAttributes.FOG_END_DISTANCE, FloatModifier.MULTIPLY, 0.5F)
            .modifyAttribute(EnvironmentAttributes.SKY_FOG_END_DISTANCE, FloatModifier.MULTIPLY, 0.5F)
            .modifyAttribute(EnvironmentAttributes.CLOUD_FOG_END_DISTANCE, FloatModifier.MULTIPLY, 0.5F)
            .setAttribute(EnvironmentAttributes.INCREASED_FIRE_BURNOUT, true)
            .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(SoundEvents.MUSIC_BIOME_SWAMP))
            .specialEffects(
                new BiomeSpecialEffects.Builder()
                    .waterColor(0x62AF84)
                    .grassColorOverride(0x6FAA50)
                    .foliageColorOverride(0x8BDB67)
                    .build()
            )
            .mobSpawnSettings(spawnBuilder.build())
            .generationSettings(biomeBuilder.build()).build();
    }

    public static Biome bog(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder, 70);
        spawnBuilder.addSpawn(MobCategory.MONSTER, 1, new MobSpawnSettings.SpawnerData(EntityType.SLIME, 1, 1));
        spawnBuilder.addSpawn(MobCategory.MONSTER, 30, new MobSpawnSettings.SpawnerData(EntityType.BOGGED, 4, 4));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        globalOverworldGeneration(biomeBuilder);
        addFeature(biomeBuilder, GenerationStep.Decoration.LAKES, BOPMiscOverworldPlacements.LAKE_WATER);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addSwampClayDisk(biomeBuilder);
        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        BiomeDefaultFeatures.addSwampExtraVegetation(biomeBuilder);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_BOG);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_BOG_BERRY_BUSH);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_BUSH_4);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_FERN_4);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_REED_10);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_6);

        return baseBiome(0.2F, 0.5F)
            .modifyAttribute(EnvironmentAttributes.WATER_FOG_END_DISTANCE, FloatModifier.MULTIPLY, 0.85F)
            .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(BOPSounds.MUSIC_BIOME_NOSTALGIC))
            .specialEffects(
                new BiomeSpecialEffects.Builder()
                    .waterColor(4159204)
                    .grassColorOverride(0xA89557)
                    .foliageColorOverride(0xC67F5B)
                    .build()
            )
            .mobSpawnSettings(spawnBuilder.build())
            .generationSettings(biomeBuilder.build()).build();
    }

    public static Biome coldDesert(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        spawnBuilder.creatureGenerationProbability(0.07F);
        BiomeDefaultFeatures.snowySpawns(spawnBuilder, true);
        spawnBuilder.addSpawn(MobCategory.CREATURE, 2, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 2, 4));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        globalOverworldGeneration(biomeBuilder);
        addFeature(biomeBuilder, GenerationStep.Decoration.LOCAL_MODIFICATIONS, BOPVegetationPlacements.COLD_DESERT_ROCKS);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.WASTELAND_GRASS_1);

        return baseBiome(0.25F, 0.0F)
            .hasPrecipitation(false)
            .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(BOPSounds.MUSIC_BIOME_NOSTALGIC))
            .specialEffects(
                new BiomeSpecialEffects.Builder()
                    .waterColor(4159204)
                    .grassColorOverride(0xAD9364)
                    .foliageColorOverride(0xB5A76C)
                    .build()
            )
            .mobSpawnSettings(spawnBuilder.build())
            .generationSettings(biomeBuilder.build()).build();
    }

    public static Biome coniferousForest(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter, boolean isSnowy)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        if (!isSnowy) BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.CREATURE, 5, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 4, 4));
        spawnBuilder.addSpawn(MobCategory.CREATURE, 4, new MobSpawnSettings.SpawnerData(EntityType.RABBIT, 2, 3));
        spawnBuilder.addSpawn(MobCategory.CREATURE, 8, new MobSpawnSettings.SpawnerData(EntityType.FOX, 2, 4));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        globalOverworldGenerationNoLavaLakes(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(biomeBuilder, false);
        BiomeDefaultFeatures.addRareBerryBushes(biomeBuilder);

        if (isSnowy)
        {
            addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_SNOWY_CONIFEROUS_FOREST);
            addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_SNOWY);
        }
        else
        {
            addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_CONIFEROUS_FOREST);
            addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_CONIFEROUS_FOREST);
        }

        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_FERN_2);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TOADSTOOL_NORMAL);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_12);

        return baseBiome(isSnowy ? -0.25F : 0.45F, 0.5F)
            .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(BOPSounds.MUSIC_BIOME_NOSTALGIC))
            .mobSpawnSettings(spawnBuilder.build())
            .generationSettings(biomeBuilder.build()).build();
    }

    public static Biome crag(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.CREATURE, 10, new MobSpawnSettings.SpawnerData(EntityType.GOAT, 4, 6));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        BiomeDefaultFeatures.addExtraEmeralds(biomeBuilder);
        BiomeDefaultFeatures.addInfestedStone(biomeBuilder);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPMiscOverworldPlacements.CRAG_MOSS);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPMiscOverworldPlacements.CRAG_SPLATTER);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_12);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_TALL_GRASS_24);

        return baseBiome(0.6F, 0.6F)
            .modifyAttribute(EnvironmentAttributes.FOG_END_DISTANCE, FloatModifier.MULTIPLY, 0.5F)
            .modifyAttribute(EnvironmentAttributes.SKY_FOG_END_DISTANCE, FloatModifier.MULTIPLY, 0.5F)
            .modifyAttribute(EnvironmentAttributes.CLOUD_FOG_END_DISTANCE, FloatModifier.MULTIPLY, 0.5F)
            .setAttribute(EnvironmentAttributes.INCREASED_FIRE_BURNOUT, true)
            .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(SoundEvents.MUSIC_BIOME_JAGGED_PEAKS))
            .mobSpawnSettings(spawnBuilder.build())
            .generationSettings(biomeBuilder.build()).build();
    }

    public static Biome deadForest(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_DEAD_BUSH_2);
        BiomeDefaultFeatures.addLeafLitterPatch(biomeBuilder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(biomeBuilder, false);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_DEAD_FOREST);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_3);

        return baseBiome(0.2F, 0.3F)
            .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(BOPSounds.MUSIC_BIOME_NOSTALGIC))
            .specialEffects(
                new BiomeSpecialEffects.Builder()
                    .waterColor(4159204)
                    .grassColorOverride(0xBAAD64)
                    .foliageColorOverride(0xB7B763)
                    .build()
            )
            .mobSpawnSettings(spawnBuilder.build())
            .generationSettings(biomeBuilder.build()).build();
    }

    public static Biome dryland(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.CREATURE, 10, new MobSpawnSettings.SpawnerData(EntityType.CHICKEN, 4, 4));
        spawnBuilder.addSpawn(MobCategory.CREATURE, 8, new MobSpawnSettings.SpawnerData(EntityType.COW, 4, 4));
        spawnBuilder.addSpawn(MobCategory.CREATURE, 4, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 2, 4));
        spawnBuilder.addSpawn(MobCategory.MONSTER, 80, new MobSpawnSettings.SpawnerData(EntityType.HUSK, 4, 4));
        spawnBuilder.addSpawn(MobCategory.MONSTER, 50, new MobSpawnSettings.SpawnerData(EntityType.PARCHED, 4, 4));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);

        // Vegetation
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_DRY_GRASS_DESERT);
        BiomeDefaultFeatures.addLeafLitterPatch(biomeBuilder);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_DRYLAND);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_6);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_DEAD_BUSH_4);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_TINY_CACTUS);

        return baseBiome(0.85F, 0.05F)
            .hasPrecipitation(false)
            .setAttribute(EnvironmentAttributes.FOG_COLOR, 12638463)
            .setAttribute(EnvironmentAttributes.SKY_COLOR, 0x9E9DFF)
            .setAttribute(EnvironmentAttributes.SNOW_GOLEM_MELTS, true)
            .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(BOPSounds.MUSIC_BIOME_NOSTALGIC))
            .specialEffects(
                new BiomeSpecialEffects.Builder()
                    .waterColor(4159204)
                    .grassColorOverride(0xE5DFA9)
                    .foliageColorOverride(0xDAE0B3)
                    .build()
            )
            .mobSpawnSettings(spawnBuilder.build())
            .generationSettings(biomeBuilder.build()).build();
    }

    public static Biome duneBeach(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        addFeature(biomeBuilder, GenerationStep.Decoration.UNDERGROUND_ORES, MiscOverworldPlacements.DISK_SAND);
        addFeature(biomeBuilder, GenerationStep.Decoration.UNDERGROUND_ORES, MiscOverworldPlacements.DISK_CLAY);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_DUNE_GRASS);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_SEA_OATS);

        return baseBiome(0.7F, 0.4F)
            .mobSpawnSettings(spawnBuilder.build())
            .generationSettings(biomeBuilder.build()).build();
    }

    public static Biome field(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter, boolean forest)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.CREATURE, 8, new MobSpawnSettings.SpawnerData(EntityType.FOX, 2, 4));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(biomeBuilder, false);
        BiomeDefaultFeatures.addRareBerryBushes(biomeBuilder);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.ROSE_BUSH_NORMAL);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.SUNFLOWER);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_FIELD);

        // Vegetation
        if (forest)
        {
            addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_FIELD_FOREST);
            addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_SPROUTS_15);
        }
        else
        {
            addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_FIELD);
            addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_TALL_WHITE_LAVENDER);
            addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_SPROUTS_10);
        }

        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_PURPLE_WILDFLOWERS);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_6);

        return baseBiome(0.4F, 0.7F)
            .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(SoundEvents.MUSIC_BIOME_FOREST))
            .specialEffects(
                new BiomeSpecialEffects.Builder()
                    .waterColor(4159204)
                    .grassColorOverride(0x63B26D)
                    .foliageColorOverride(0x63B26D)
                    .build()
            )
            .mobSpawnSettings(spawnBuilder.build())
            .generationSettings(biomeBuilder.build()).build();
    }

    public static Biome firClearing(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter, boolean snowy)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.CREATURE, 5, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 4, 4));
        spawnBuilder.addSpawn(MobCategory.CREATURE, 4, new MobSpawnSettings.SpawnerData(EntityType.RABBIT, 2, 3));
        spawnBuilder.addSpawn(MobCategory.CREATURE, 8, new MobSpawnSettings.SpawnerData(EntityType.FOX, 2, 4));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        globalOverworldGenerationNoLavaLakes(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(biomeBuilder, false);
        BiomeDefaultFeatures.addRareBerryBushes(biomeBuilder);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_FIR_CLEARING);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FALLEN_FIR_LOG);

        if (!snowy)
        {
            addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_CONIFEROUS_FOREST);
            addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_FERN_4);
            addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TOADSTOOL_NORMAL);
            addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_12);
        }
        else
        {
            addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_FERN_2);
            addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_1);
        }

        return baseBiome(snowy ? -0.25F : 0.45F, 0.5F)
            .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(BOPSounds.MUSIC_BIOME_NOSTALGIC))
            .mobSpawnSettings(spawnBuilder.build())
            .generationSettings(biomeBuilder.build()).build();
    }

    public static Biome floodplain(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder, 70);
        spawnBuilder.addSpawn(MobCategory.CREATURE, 10, new MobSpawnSettings.SpawnerData(EntityType.FROG, 2, 5));
        spawnBuilder.addSpawn(MobCategory.MONSTER, 1, new MobSpawnSettings.SpawnerData(EntityType.SLIME, 1, 1));
        spawnBuilder.addSpawn(MobCategory.MONSTER, 30, new MobSpawnSettings.SpawnerData(EntityType.BOGGED, 4, 4));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        globalOverworldGeneration(biomeBuilder);
        addFeature(biomeBuilder, GenerationStep.Decoration.LAKES, BOPMiscOverworldPlacements.LAKE_WATER_EXTRA);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, AquaticPlacements.SEAGRASS_SWAMP);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_RAINFOREST_FLOODPLAIN);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_RAINFOREST);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.LILY_PAD_15);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.HUGE_LILY_PAD);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.WATERLILY_FLOWERS);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_12);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_TALL_GRASS_24);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_WATERGRASS_50);

        return baseBiome(1.2F, 2.0F)
            .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 0x041A2C)
            .modifyAttribute(EnvironmentAttributes.WATER_FOG_END_DISTANCE, FloatModifier.MULTIPLY, 0.85F)
            .setAttribute(EnvironmentAttributes.INCREASED_FIRE_BURNOUT, true)
            .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(SoundEvents.MUSIC_BIOME_JUNGLE))
            .specialEffects(
                new BiomeSpecialEffects.Builder()
                    .waterColor(0x3FAABE)
                    .grassColorOverride(0x7FD43D)
                    .foliageColorOverride(0x5BCD25)
                    .build()
            )
            .mobSpawnSettings(spawnBuilder.build())
            .generationSettings(biomeBuilder.build()).build();
    }

    public static Biome fungalJungle(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.CREATURE, 10, new MobSpawnSettings.SpawnerData(EntityType.FROG, 2, 5));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_SUGAR_CANE_SWAMP);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, AquaticPlacements.SEAGRASS_SWAMP);

        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_FUNGAL_JUNGLE);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.HUGE_TOADSTOOL);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.SMALL_TOADSTOOL);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TOADSTOOL_EXTRA);

        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.DRIPSTONE_SPLATTER);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_SPROUTS_5);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_12);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_TALL_GRASS_12);

        return baseBiome(0.9F, 0.9F)
            .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 0x042F18)
            .modifyAttribute(EnvironmentAttributes.WATER_FOG_END_DISTANCE, FloatModifier.MULTIPLY, 0.85F)
            .setAttribute(EnvironmentAttributes.FOG_COLOR, 0xE1ED63)
            .setAttribute(EnvironmentAttributes.SKY_COLOR, 0x62DCEA)
            .setAttribute(EnvironmentAttributes.CLOUD_COLOR, ARGB.color(0.4F, 0xFF7FBF))
            .setAttribute(EnvironmentAttributes.INCREASED_FIRE_BURNOUT, true)
            .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(SoundEvents.MUSIC_BIOME_JUNGLE))
            .specialEffects(
                new BiomeSpecialEffects.Builder()
                    .waterColor(0x3FDF64)
                    .grassColorOverride(0xCCE525)
                    .foliageColorOverride(0xE0E522)
                    .build()
            )
            .mobSpawnSettings(spawnBuilder.build())
            .generationSettings(biomeBuilder.build()).build();
    }

    public static Biome glowingGrotto(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter)
    {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);

        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addPlainGrass(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        BiomeDefaultFeatures.addPlainVegetation(biomeBuilder);
        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPCavePlacements.GLOWING_GROTTO_VEGETATION);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPCavePlacements.GLOWING_GROTTO_MUD);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPCavePlacements.GLOWWORM_SILK_STRANDS);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPCavePlacements.GIANT_GLOWSHROOM_CAVE);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPCavePlacements.HUGE_GLOWSHROOM_CAVE);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPCavePlacements.MEDIUM_GLOWSHROOM_CAVE);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPCavePlacements.SMALL_GLOWSHROOM_CAVE);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPCavePlacements.EXTRA_GLOW_LICHEN);

        return baseBiome(0.5F, 0.5F)
            .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 0x1F78B7)
            .setAttribute(EnvironmentAttributes.FOG_COLOR, 0x408ADA)
            .setAttribute(EnvironmentAttributes.BLOCK_LIGHT_TINT, 0x408ADA)
            .modifyAttribute(EnvironmentAttributes.FOG_END_DISTANCE, FloatModifier.MULTIPLY, 0.75F)
            .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(SoundEvents.MUSIC_BIOME_LUSH_CAVES))
            .specialEffects(
                new BiomeSpecialEffects.Builder()
                    .waterColor(0x87F9FF)
                    .build()
            )
            .mobSpawnSettings(spawnBuilder.build())
            .generationSettings(biomeBuilder.build()).build();
    }

    public static Biome gravelBeach(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        addFeature(biomeBuilder, GenerationStep.Decoration.UNDERGROUND_ORES, MiscOverworldPlacements.DISK_CLAY);
        addFeature(biomeBuilder, GenerationStep.Decoration.UNDERGROUND_ORES, MiscOverworldPlacements.DISK_GRAVEL);

        return baseBiome(0.45F, 0.5F)
            .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 329011)
            .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(BOPSounds.MUSIC_BIOME_NOSTALGIC))
            .specialEffects(
                new BiomeSpecialEffects.Builder()
                    .waterColor(4020182)
                    .build()
            )
            .mobSpawnSettings(spawnBuilder.build())
            .generationSettings(biomeBuilder.build()).build();
    }

    public static Biome highland(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.CREATURE, 10, new MobSpawnSettings.SpawnerData(EntityType.GOAT, 4, 6));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        globalOverworldGeneration(biomeBuilder);
        addFeature(biomeBuilder, GenerationStep.Decoration.LOCAL_MODIFICATIONS, BOPVegetationPlacements.HIGHLAND_ROCKS);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        BiomeDefaultFeatures.addExtraEmeralds(biomeBuilder);
        BiomeDefaultFeatures.addInfestedStone(biomeBuilder);

        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_BUSH_1);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_24);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_TALL_GRASS_64);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.SCATTERED_ROCKS_HIGHLAND);

        return baseBiome(0.6F, 0.6F)
            .modifyAttribute(EnvironmentAttributes.FOG_END_DISTANCE, FloatModifier.MULTIPLY, 0.625F)
            .modifyAttribute(EnvironmentAttributes.SKY_FOG_END_DISTANCE, FloatModifier.MULTIPLY, 0.625F)
            .modifyAttribute(EnvironmentAttributes.CLOUD_FOG_END_DISTANCE, FloatModifier.MULTIPLY, 0.625F)
            .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(SoundEvents.MUSIC_BIOME_JAGGED_PEAKS))
            .mobSpawnSettings(spawnBuilder.build())
            .generationSettings(biomeBuilder.build()).build();
    }

    public static Biome hotSprings(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.CREATURE, 8, new MobSpawnSettings.SpawnerData(EntityType.FOX, 2, 4));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        globalOverworldGeneration(biomeBuilder);
        addFeature(biomeBuilder, GenerationStep.Decoration.LAKES, BOPMiscOverworldPlacements.LAKE_HOT_SPRING);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);

        addFeature(biomeBuilder, GenerationStep.Decoration.UNDERGROUND_DECORATION, BOPMiscOverworldPlacements.DISK_HOT_SPRING_GRAVEL);
        addFeature(biomeBuilder, GenerationStep.Decoration.UNDERGROUND_DECORATION, BOPMiscOverworldPlacements.DISK_HOT_SPRING_CALCITE);
        addFeature(biomeBuilder, GenerationStep.Decoration.UNDERGROUND_DECORATION, BOPMiscOverworldPlacements.DISK_HOT_SPRING_BASALT);
        addFeature(biomeBuilder, GenerationStep.Decoration.UNDERGROUND_DECORATION, BOPMiscOverworldPlacements.DISK_HOT_SPRING_PACKED_MUD);
        addFeature(biomeBuilder, GenerationStep.Decoration.UNDERGROUND_DECORATION, BOPMiscOverworldPlacements.DISK_HOT_SPRING_THERMAL_CALCITE);

        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_HOT_SPRINGS);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.THIN_BAMBOO);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_BUSH_1);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_FERN_2);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_LARGE_FERN_NORMAL);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_6);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.HOT_SPRING_VENTS);

        return baseBiome(0.17F, 0.5F)
            .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 270131)
            .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(BOPSounds.MUSIC_BIOME_NOSTALGIC))
            .specialEffects(
                new BiomeSpecialEffects.Builder()
                    .waterColor(4445678)
                    .grassColorOverride(0x80B497)
                    .foliageColorOverride(0x60A17B)
                    .build()
            )
            .mobSpawnSettings(spawnBuilder.build())
            .generationSettings(biomeBuilder.build()).build();
    }

    public static Biome jadeCliffs(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.CREATURE, 1, new MobSpawnSettings.SpawnerData(EntityType.PANDA, 1, 2));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        addFeature(biomeBuilder, GenerationStep.Decoration.RAW_GENERATION, BOPMiscOverworldPlacements.JADE_CLIFFS_ERODED_PILLARS);
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        BiomeDefaultFeatures.addExtraEmeralds(biomeBuilder);
        BiomeDefaultFeatures.addInfestedStone(biomeBuilder);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_SUGAR_CANE);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_JADE_CLIFFS);

        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_12);

        return baseBiome(0.8F, 0.85F)
            .setAttribute(EnvironmentAttributes.FOG_COLOR, 0xBBD1D5)
            .setAttribute(EnvironmentAttributes.SKY_COLOR, 0xB7CCAD)
            .setAttribute(EnvironmentAttributes.INCREASED_FIRE_BURNOUT, true)
            .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(SoundEvents.MUSIC_BIOME_JAGGED_PEAKS))
            .specialEffects(
                new BiomeSpecialEffects.Builder()
                    .waterColor(4159204)
                    .grassColorOverride(0x7CA568)
                    .foliageColorOverride(0x8BB76E)
                    .build()
            )
            .mobSpawnSettings(spawnBuilder.build())
            .generationSettings(biomeBuilder.build()).build();
    }

    public static Biome lavenderField(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.plainsSpawns(spawnBuilder);

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(biomeBuilder, true);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_LAVENDER_FIELD);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FALLEN_JACARANDA_LOG);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_24);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_TALL_GRASS_64);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_LAVENDER);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_TALL_LAVENDER);

        return baseBiome(0.8F, 0.7F)
            .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(SoundEvents.MUSIC_BIOME_CHERRY_GROVE))
            .specialEffects(
                new BiomeSpecialEffects.Builder()
                    .waterColor(4159204)
                    .grassColorOverride(0xA1C36D)
                    .foliageColorOverride(0xA1C36D)
                    .build()
            )
            .mobSpawnSettings(spawnBuilder.build())
            .generationSettings(biomeBuilder.build()).build();
    }

    public static Biome lushDesert(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        BiomeDefaultFeatures.desertSpawns(spawnBuilder);

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        globalOverworldGeneration(biomeBuilder);
        addFeature(biomeBuilder, GenerationStep.Decoration.LAKES, BOPMiscOverworldPlacements.LAKE_HOT_SPRING);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        addFeature(biomeBuilder, GenerationStep.Decoration.FLUID_SPRINGS, BOPMiscOverworldPlacements.SPRING_WATER_EXTRA);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_SUGAR_CANE);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_CACTUS_DECORATED);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_LUSH_DESERT);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_LUSH_DESERT);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_DRY_GRASS_EXTRA);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.SPARSE_DUNE_GRASS);

        return baseBiome(0.9F, 0.5F)
            .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 267827)
            .setAttribute(EnvironmentAttributes.SNOW_GOLEM_MELTS, true)
            .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(SoundEvents.MUSIC_BIOME_DESERT))
            .specialEffects(
                new BiomeSpecialEffects.Builder()
                    .waterColor(4566514)
                    .grassColorOverride(0xEFE182)
                    .foliageColorOverride(0xD3D156)
                    .build()
            )
            .mobSpawnSettings(spawnBuilder.build())
            .generationSettings(biomeBuilder.build()).build();
    }

    public static Biome lushSavanna(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.plainsSpawns(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.CREATURE, 4, new MobSpawnSettings.SpawnerData(EntityType.RABBIT, 2, 3));
        spawnBuilder.addSpawn(MobCategory.MONSTER, 80, new MobSpawnSettings.SpawnerData(EntityType.HUSK, 4, 4));
        spawnBuilder.addSpawn(MobCategory.MONSTER, 50, new MobSpawnSettings.SpawnerData(EntityType.PARCHED, 4, 4));
        spawnBuilder.addSpawn(MobCategory.CREATURE, 6, new MobSpawnSettings.SpawnerData(EntityType.ARMADILLO, 1, 2));
        spawnBuilder.addSpawn(MobCategory.CREATURE, 8, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 4, 8));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_DRY_GRASS_BADLANDS);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_SUGAR_CANE);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_POPPY);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.ROSE_BUSH_EXTRA);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_FLOWER_BUDS_EXTRA);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_SPROUTS_5);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_24);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_TALL_GRASS_24);

        return baseBiome(0.9F, 0.5F)
            .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 267827)
            .setAttribute(EnvironmentAttributes.SNOW_GOLEM_MELTS, true)
            .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(SoundEvents.MUSIC_BIOME_DESERT))
            .specialEffects(
                new BiomeSpecialEffects.Builder()
                    .waterColor(4566514)
                    .grassColorOverride(0xEFE182)
                    .foliageColorOverride(0xD3D156)
                    .build()
            )
            .mobSpawnSettings(spawnBuilder.build())
            .generationSettings(biomeBuilder.build()).build();
    }

    public static Biome mapleWoods(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter, boolean snowy)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.CREATURE, 5, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 4, 4));
        spawnBuilder.addSpawn(MobCategory.CREATURE, 4, new MobSpawnSettings.SpawnerData(EntityType.RABBIT, 2, 3));
        spawnBuilder.addSpawn(MobCategory.CREATURE, 8, new MobSpawnSettings.SpawnerData(EntityType.FOX, 2, 4));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        BiomeDefaultFeatures.addLeafLitterPatch(biomeBuilder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(biomeBuilder, false);
        BiomeDefaultFeatures.addRareBerryBushes(biomeBuilder);

        if (!snowy)
        {
            addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_MAPLE_FOREST);
            addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_SNOWY);
            addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_FERN_2);
            addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_6);
            addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.RED_MAPLE_LEAF_LITTER);
        }
        else
        {
            addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_SNOWY_MAPLE_FOREST);
            addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_SNOWY);
            addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_FERN_2);
            addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_3);
        }

        return baseBiome(snowy ? -0.25F : 0.25F, 0.5F)
            .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(BOPSounds.MUSIC_BIOME_NOSTALGIC))
            .mobSpawnSettings(spawnBuilder.build())
            .generationSettings(biomeBuilder.build()).build();
    }

    public static Biome marsh(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder, 70);
        spawnBuilder.addSpawn(MobCategory.CREATURE, 10, new MobSpawnSettings.SpawnerData(EntityType.PIG, 4, 4));
        spawnBuilder.addSpawn(MobCategory.CREATURE, 10, new MobSpawnSettings.SpawnerData(EntityType.CHICKEN, 4, 4));
        spawnBuilder.addSpawn(MobCategory.MONSTER, 1, new MobSpawnSettings.SpawnerData(EntityType.SLIME, 1, 1));
        spawnBuilder.addSpawn(MobCategory.MONSTER, 30, new MobSpawnSettings.SpawnerData(EntityType.BOGGED, 4, 4));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        globalOverworldGeneration(biomeBuilder);
        addFeature(biomeBuilder, GenerationStep.Decoration.LAKES, BOPMiscOverworldPlacements.LAKE_WATER_MARSH);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, AquaticPlacements.SEAGRASS_SWAMP);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_FIREFLY_BUSH_NEAR_WATER);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_REED_50);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_24);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_TALL_GRASS_128);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_WATERGRASS_250);

        return baseBiome(0.65F, 0.4F)
            .modifyAttribute(EnvironmentAttributes.WATER_FOG_END_DISTANCE, FloatModifier.MULTIPLY, 0.85F)
            .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(SoundEvents.MUSIC_BIOME_SWAMP))
            .mobSpawnSettings(spawnBuilder.build())
            .generationSettings(biomeBuilder.build()).build();
    }

    public static Biome mediterraneanForest(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        BiomeDefaultFeatures.farmAnimals(spawnBuilder);

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        BiomeDefaultFeatures.addLeafLitterPatch(biomeBuilder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(biomeBuilder, true);
        addFeature(biomeBuilder, GenerationStep.Decoration.UNDERGROUND_ORES, BOPMiscOverworldPlacements.DISK_CALCITE);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_MEDITERRANEAN_FOREST);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_DEFAULT_EXTENDED_6);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_BUSH_1);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PEONY);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_3);

        return baseBiome(0.8F, 0.275F)
            .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 267827)
            .specialEffects(
                new BiomeSpecialEffects.Builder()
                    .waterColor(4566514)
                    .build()
            )
            .mobSpawnSettings(spawnBuilder.build())
            .generationSettings(biomeBuilder.build()).build();
    }

    public static Biome moor(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.CREATURE, 10, new MobSpawnSettings.SpawnerData(EntityType.GOAT, 4, 6));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        BiomeDefaultFeatures.addExtraEmeralds(biomeBuilder);
        BiomeDefaultFeatures.addInfestedStone(biomeBuilder);

        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, AquaticPlacements.SEAGRASS_RIVER);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_MOOR);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_VIOLET);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.SCRUB_NORMAL);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_BUSH_1);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_12);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_TALL_GRASS_12);

        return baseBiome(0.6F, 0.6F)
            .modifyAttribute(EnvironmentAttributes.WATER_FOG_END_DISTANCE, FloatModifier.MULTIPLY, 0.85F)
            .modifyAttribute(EnvironmentAttributes.FOG_END_DISTANCE, FloatModifier.MULTIPLY, 0.5F)
            .modifyAttribute(EnvironmentAttributes.SKY_FOG_END_DISTANCE, FloatModifier.MULTIPLY, 0.5F)
            .modifyAttribute(EnvironmentAttributes.CLOUD_FOG_END_DISTANCE, FloatModifier.MULTIPLY, 0.5F)
            .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(SoundEvents.MUSIC_BIOME_JAGGED_PEAKS))
            .specialEffects(
                new BiomeSpecialEffects.Builder()
                    .waterColor(4159204)
                    .grassColorOverride(0x71AA70)
                    .foliageColorOverride(0x78C46D)
                    .build()
            )
            .mobSpawnSettings(spawnBuilder.build())
            .generationSettings(biomeBuilder.build()).build();
    }

    public static Biome muskeg(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.CREATURE, 10, new MobSpawnSettings.SpawnerData(EntityType.FROG, 2, 5));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        addFeature(biomeBuilder, GenerationStep.Decoration.UNDERGROUND_ORES, MiscOverworldPlacements.DISK_CLAY);
        addFeature(biomeBuilder, GenerationStep.Decoration.UNDERGROUND_ORES, MiscOverworldPlacements.DISK_GRAVEL);
        addFeature(biomeBuilder, GenerationStep.Decoration.UNDERGROUND_ORES, BOPMiscOverworldPlacements.DISK_MUD);

        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_MUSKEG);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.SMALL_BROWN_MUSHROOM);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPMiscOverworldPlacements.MUD_SPLATTER);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_1);

        return baseBiome(0.0F, 0.6F)
            .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(BOPSounds.MUSIC_BIOME_NOSTALGIC))
            .specialEffects(
                new BiomeSpecialEffects.Builder()
                    .waterColor(4159204)
                    .grassColorOverride(0x94966E)
                    .foliageColorOverride(0x8D9B6B)
                    .build()
            )
            .mobSpawnSettings(spawnBuilder.build())
            .generationSettings(biomeBuilder.build()).build();
    }

    public static Biome mysticGrove(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.caveSpawns(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.CREATURE, 4, new MobSpawnSettings.SpawnerData(EntityType.RABBIT, 2, 3));
        spawnBuilder.addSpawn(MobCategory.CREATURE, 4, new MobSpawnSettings.SpawnerData(EntityType.FOX, 2, 4));
        spawnBuilder.addSpawn(MobCategory.CREATURE, 8, new MobSpawnSettings.SpawnerData(EntityType.ALLAY, 1, 1));
        spawnBuilder.addSpawn(MobCategory.CREATURE, 4, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 1, 1));
        spawnBuilder.addSpawn(MobCategory.MONSTER, 5, new MobSpawnSettings.SpawnerData(EntityType.ZOMBIE_VILLAGER, 1, 1));
        spawnBuilder.addSpawn(MobCategory.MONSTER, 100, new MobSpawnSettings.SpawnerData(EntityType.SLIME, 4, 4));
        spawnBuilder.addSpawn(MobCategory.MONSTER, 20, new MobSpawnSettings.SpawnerData(EntityType.WITCH, 1, 1));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        globalOverworldGenerationNoLavaLakes(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        addFeature(biomeBuilder, GenerationStep.Decoration.UNDERGROUND_ORES, MiscOverworldPlacements.DISK_CLAY);
        addFeature(biomeBuilder, GenerationStep.Decoration.UNDERGROUND_ORES, BOPMiscOverworldPlacements.DISK_WHITE_SAND);
        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(biomeBuilder, true);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_MYSTIC_GROVE);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_MYSTIC_GROVE);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_BLUE_HYDRANGEA_EXTRA);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.HUGE_RED_MUSHROOM_EXTRA);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.SMALL_RED_MUSHROOM);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.CLOVER_NORMAL);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.LILAC_NORMAL);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_SPROUTS_15);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_12);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_WATERGRASS_10);

        return baseBiome(0.7F, 0.8F)
            .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 0x2E0533)
            .modifyAttribute(EnvironmentAttributes.WATER_FOG_END_DISTANCE, FloatModifier.MULTIPLY, 0.85F)
            .setAttribute(EnvironmentAttributes.FOG_COLOR, 0xFFC9DA)
            .setAttribute(EnvironmentAttributes.SKY_COLOR, 0xAAEFFF)
            .setAttribute(EnvironmentAttributes.CLOUD_COLOR, ARGB.color(0.6F, 0x7FFFF4))
            .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(SoundEvents.MUSIC_BIOME_CHERRY_GROVE))
            .specialEffects(
                new BiomeSpecialEffects.Builder()
                    .waterColor(0x9C3FE4)
                    .grassColorOverride(0x69CFDB)
                    .foliageColorOverride(0x70E0B5)
                    .dryFoliageColorOverride(0x8F9A6F)
                    .build()
            )
            .mobSpawnSettings(spawnBuilder.build())
            .generationSettings(biomeBuilder.build()).build();
    }

    public static Biome oldGrowthDeadForest(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_DEAD_BUSH_2);
        BiomeDefaultFeatures.addLeafLitterPatch(biomeBuilder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(biomeBuilder, false);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_OLD_GROWTH_DEAD_FOREST);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FALLEN_DEAD_LOG);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_6);

        return baseBiome(0.3F, 0.3F)
            .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(BOPSounds.MUSIC_BIOME_NOSTALGIC))
            .specialEffects(
                new BiomeSpecialEffects.Builder()
                    .waterColor(4159204)
                    .grassColorOverride(0xBAAD64)
                    .foliageColorOverride(0xB7B763)
                    .build()
            )
            .mobSpawnSettings(spawnBuilder.build())
            .generationSettings(biomeBuilder.build()).build();
    }

    public static Biome ominousWoods(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.caveSpawns(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.MONSTER, 50, new MobSpawnSettings.SpawnerData(EntityType.ILLUSIONER, 2, 4));
        spawnBuilder.addSpawn(MobCategory.MONSTER, 100, new MobSpawnSettings.SpawnerData(EntityType.SPIDER, 4, 4));
        spawnBuilder.addSpawn(MobCategory.MONSTER, 100, new MobSpawnSettings.SpawnerData(EntityType.SLIME, 4, 4));
        spawnBuilder.addSpawn(MobCategory.MONSTER, 10, new MobSpawnSettings.SpawnerData(EntityType.ENDERMAN, 1, 4));
        spawnBuilder.addSpawn(MobCategory.MONSTER, 5, new MobSpawnSettings.SpawnerData(EntityType.WITCH, 1, 1));
        spawnBuilder.addSpawn(MobCategory.CREATURE, 2, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 1, 1));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        globalOverworldGenerationNoLavaLakes(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(biomeBuilder, false);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_OMINOUS_WOODS);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_OMINOUS_WOODS);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_DEAD_GRASS);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_6);

        return baseBiome(0.6F, 0.6F)
            .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 0x0A030C)
            .modifyAttribute(EnvironmentAttributes.WATER_FOG_END_DISTANCE, FloatModifier.MULTIPLY, 0.5F)
            .setAttribute(EnvironmentAttributes.FOG_COLOR, 0x7881A5)
            .setAttribute(EnvironmentAttributes.SKY_COLOR, 0x84A1CC)
            .setAttribute(EnvironmentAttributes.CLOUD_COLOR, ARGB.color(0.8F, 0x666D8C))
            .modifyAttribute(EnvironmentAttributes.FOG_END_DISTANCE, FloatModifier.MULTIPLY, 0.15F)
            .modifyAttribute(EnvironmentAttributes.SKY_FOG_END_DISTANCE, FloatModifier.MULTIPLY, 0.15F)
            .modifyAttribute(EnvironmentAttributes.CLOUD_FOG_END_DISTANCE, FloatModifier.MULTIPLY, 0.15F)
            .specialEffects(
                new BiomeSpecialEffects.Builder()
                    .waterColor(0x312346)
                    .grassColorOverride(0x4C4A70)
                    .foliageColorOverride(0x6B487C)
                    .dryFoliageColorOverride(0x856D62)
                    .build()
            )
            .mobSpawnSettings(spawnBuilder.build())
            .generationSettings(biomeBuilder.build()).build();
    }

    public static Biome orchard(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        BiomeDefaultFeatures.farmAnimals(spawnBuilder);

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(biomeBuilder, true);

        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_ORCHARD);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_DEFAULT_EXTENDED_15);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.CLOVER_NORMAL);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.ROSE_BUSH_NORMAL);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_SPROUTS_5);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_12);

        return baseBiome(0.8F, 0.4F)
            .specialEffects(
                new BiomeSpecialEffects.Builder()
                    .waterColor(4159204)
                    .grassColorOverride(0xA9DB69)
                    .foliageColorOverride(0xC9F75D)
                    .build()
            )
            .mobSpawnSettings(spawnBuilder.build())
            .generationSettings(biomeBuilder.build()).build();
    }

    public static Biome originValley(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter, boolean snowy)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.MONSTER, 100, new MobSpawnSettings.SpawnerData(EntityType.SPIDER, 4, 4));
        spawnBuilder.addSpawn(MobCategory.MONSTER, 95, new MobSpawnSettings.SpawnerData(EntityType.ZOMBIE, 4, 4));
        spawnBuilder.addSpawn(MobCategory.MONSTER, 100, new MobSpawnSettings.SpawnerData(EntityType.SKELETON, 4, 4));
        spawnBuilder.addSpawn(MobCategory.MONSTER, 100, new MobSpawnSettings.SpawnerData(EntityType.CREEPER, 4, 4));
        spawnBuilder.addSpawn(MobCategory.MONSTER, 100, new MobSpawnSettings.SpawnerData(EntityType.SLIME, 4, 4));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        biomeBuilder.addCarver(BOPConfiguredCarvers.ORIGIN_CAVE);
        BiomeDefaultFeatures.addDefaultMonsterRoom(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSprings(biomeBuilder);
        BiomeDefaultFeatures.addSurfaceFreezing(biomeBuilder);
        addFeature(biomeBuilder, GenerationStep.Decoration.TOP_LAYER_MODIFICATION, BOPMiscOverworldPlacements.ORIGIN_GRAVEL_CLIFFS);
        addFeature(biomeBuilder, GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_DIRT);
        addFeature(biomeBuilder, GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_GRAVEL);
        addFeature(biomeBuilder, GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_COAL_UPPER);
        addFeature(biomeBuilder, GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_COAL_LOWER);
        addFeature(biomeBuilder, GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_IRON_UPPER);
        addFeature(biomeBuilder, GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_IRON_MIDDLE);
        addFeature(biomeBuilder, GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_IRON_SMALL);
        addFeature(biomeBuilder, GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_GOLD);
        addFeature(biomeBuilder, GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_GOLD_LOWER);
        addFeature(biomeBuilder, GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_REDSTONE);
        addFeature(biomeBuilder, GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_REDSTONE_LOWER);
        addFeature(biomeBuilder, GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_DIAMOND);
        addFeature(biomeBuilder, GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_DIAMOND_MEDIUM);
        addFeature(biomeBuilder, GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_DIAMOND_LARGE);
        addFeature(biomeBuilder, GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_DIAMOND_BURIED);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_SUGAR_CANE);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_ORIGIN_VALLEY);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_ORIGIN_VALLEY);

        return baseBiome(snowy ? -0.25F : 0.6F, 0.6F)
            .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 0x050533)
            .setAttribute(EnvironmentAttributes.FOG_COLOR, 0xC0D8FF)
            .setAttribute(EnvironmentAttributes.SKY_COLOR, 0x88BBFF)
            //.setAttribute(EnvironmentAttributes.CLOUD_HEIGHT, 108.33F)
            .setAttribute(EnvironmentAttributes.CAN_PILLAGER_PATROL_SPAWN, false)
            .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(BOPSounds.MUSIC_BIOME_ORIGIN_VALLEY))
            .specialEffects(
                new BiomeSpecialEffects.Builder()
                    .waterColor(0x2032FF)
                    .grassColorOverride(0x9AFF5F)
                    .foliageColorOverride(0x3AFF00)
                    .dryFoliageColorOverride(0x3AFF00)
                    .build()
            )
            .mobSpawnSettings(spawnBuilder.build())
            .generationSettings(biomeBuilder.build()).build();
    }

    public static Biome overgrownGreens(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        BiomeDefaultFeatures.farmAnimals(spawnBuilder);

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);

        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_OVERGROWN_GREENS);

        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.HUGE_FLOWER);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_FLOWER_BUDS);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_24);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_TALL_GRASS_64);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.HIGH_GRASS);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.CLOVER_EXTRA);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.HUGE_CLOVER);

        return baseBiome(0.6F, 0.9F)
            .specialEffects(
                new BiomeSpecialEffects.Builder()
                    .waterColor(4159204)
                    .grassColorOverride(0x5EBF6A)
                    .foliageColorOverride(0x55BC71)
                    .build()
            )
            .mobSpawnSettings(spawnBuilder.build())
            .generationSettings(biomeBuilder.build()).build();
    }

    public static Biome pasture(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.plainsSpawns(spawnBuilder);

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_BARLEY);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_24);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_TALL_GRASS_64);

        return baseBiome(0.8F, 0.3F)
            .specialEffects(
                new BiomeSpecialEffects.Builder()
                    .waterColor(4159204)
                    .grassColorOverride(0xE4EA77)
                    .foliageColorOverride(0xC7E672)
                    .build()
            )
            .mobSpawnSettings(spawnBuilder.build())
            .generationSettings(biomeBuilder.build()).build();
    }

    public static Biome prairie(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.plainsSpawns(spawnBuilder);

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.WILDFLOWERS_BIRCH_FOREST);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_PRAIRIE);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_CONIFEROUS_FOREST);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.ROOTED_STUMP);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.GOLDENROD);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_24);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_TALL_GRASS_24);

        return baseBiome(0.8F, 0.3F)
            .specialEffects(
                new BiomeSpecialEffects.Builder()
                    .waterColor(4159204)
                    .grassColorOverride(0xE4EA77)
                    .foliageColorOverride(0xC7E672)
                    .build()
            )
            .mobSpawnSettings(spawnBuilder.build())
            .generationSettings(biomeBuilder.build()).build();
    }

    public static Biome pumpkinPatch(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.CREATURE, 5, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 4, 4));
        spawnBuilder.addSpawn(MobCategory.CREATURE, 8, new MobSpawnSettings.SpawnerData(EntityType.FOX, 2, 4));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_SEASONAL_PUMPKIN_PATCH);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_PUMPKIN_PATCH);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FALLEN_BIRCH_LOG);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_BARLEY_SPARSE);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.BIG_PUMPKIN);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PUMPKIN_PATCH);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TOADSTOOL_NORMAL);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_SPROUTS_10);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_REED_5);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_6);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.YELLOW_MAPLE_LEAF_LITTER);

        return baseBiome(0.4F, 0.8F)
            .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(SoundEvents.MUSIC_BIOME_FOREST))
            .specialEffects(
                new BiomeSpecialEffects.Builder()
                    .waterColor(4159204)
                    .grassColorOverride(0xDDBF4A)
                    .foliageColorOverride(0xCED14A)
                    .build()
            )
            .mobSpawnSettings(spawnBuilder.build())
            .generationSettings(biomeBuilder.build()).build();
    }

    public static Biome rainforest(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.CREATURE, 10, new MobSpawnSettings.SpawnerData(EntityType.CHICKEN, 4, 4));
        spawnBuilder.addSpawn(MobCategory.CREATURE, 10, new MobSpawnSettings.SpawnerData(EntityType.PIG, 4, 4));
        spawnBuilder.addSpawn(MobCategory.CREATURE, 40, new MobSpawnSettings.SpawnerData(EntityType.PARROT, 1, 2));
        spawnBuilder.addSpawn(MobCategory.MONSTER, 2, new MobSpawnSettings.SpawnerData(EntityType.OCELOT, 1, 1));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        addFeature(biomeBuilder, GenerationStep.Decoration.UNDERGROUND_ORES, BOPMiscOverworldPlacements.DISK_ORANGE_SAND);
        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_SUGAR_CANE);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, AquaticPlacements.SEAGRASS_SWAMP);
        BiomeDefaultFeatures.addJungleMelons(biomeBuilder);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_RAINFOREST);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_RAINFOREST);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_PITCHER_PLANT);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.BIG_DRIPLEAF);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_BUSH_4);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_FERN_8);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_LARGE_FERN_EXTRA);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.LILY_PAD_10);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.SMALL_DRIPLEAF);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_12);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_TALL_GRASS_12);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_WATERGRASS_10);

        return baseBiome(1.2F, 2.0F)
            .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 0x042F26)
            .modifyAttribute(EnvironmentAttributes.WATER_FOG_END_DISTANCE, FloatModifier.MULTIPLY, 0.85F)
            .setAttribute(EnvironmentAttributes.INCREASED_FIRE_BURNOUT, true)
            .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(SoundEvents.MUSIC_BIOME_JUNGLE))
            .specialEffects(
                new BiomeSpecialEffects.Builder()
                    .waterColor(0x3FDF99)
                    .grassColorOverride(0xA7E140)
                    .foliageColorOverride(0x88E140)
                    .build()
            )
            .mobSpawnSettings(spawnBuilder.build())
            .generationSettings(biomeBuilder.build()).build();
    }

    public static Biome rockyRainforest(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.CREATURE, 10, new MobSpawnSettings.SpawnerData(EntityType.CHICKEN, 4, 4));
        spawnBuilder.addSpawn(MobCategory.CREATURE, 40, new MobSpawnSettings.SpawnerData(EntityType.PARROT, 1, 2));
        spawnBuilder.addSpawn(MobCategory.MONSTER, 2, new MobSpawnSettings.SpawnerData(EntityType.OCELOT, 1, 1));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        addFeature(biomeBuilder, GenerationStep.Decoration.UNDERGROUND_ORES, BOPMiscOverworldPlacements.DISK_ORANGE_SAND);
        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        addFeature(biomeBuilder, GenerationStep.Decoration.FLUID_SPRINGS, BOPMiscOverworldPlacements.SPRING_WATER_EXTRA);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_SUGAR_CANE);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_RAINFOREST_CLIFFS);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_RAINFOREST);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_BUSH_4);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_FERN_8);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_LARGE_FERN_NORMAL);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.RAINFOREST_CLIFFS_VINES);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_SPROUTS_10);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_12);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_TALL_GRASS_24);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_WATERGRASS_10);

        return baseBiome(1.2F, 2.0F)
            .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 0x042F26)
            .modifyAttribute(EnvironmentAttributes.WATER_FOG_END_DISTANCE, FloatModifier.MULTIPLY, 0.85F)
            .setAttribute(EnvironmentAttributes.INCREASED_FIRE_BURNOUT, true)
            .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(SoundEvents.MUSIC_BIOME_JUNGLE))
            .specialEffects(
                new BiomeSpecialEffects.Builder()
                    .waterColor(0x3FDF99)
                    .grassColorOverride(0xA7E140)
                    .foliageColorOverride(0x88E140)
                    .build()
            )
            .mobSpawnSettings(spawnBuilder.build())
            .generationSettings(biomeBuilder.build()).build();
    }

    public static Biome redwoodForest(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.CREATURE, 5, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 4, 4));
        spawnBuilder.addSpawn(MobCategory.CREATURE, 4, new MobSpawnSettings.SpawnerData(EntityType.RABBIT, 2, 3));
        spawnBuilder.addSpawn(MobCategory.CREATURE, 8, new MobSpawnSettings.SpawnerData(EntityType.FOX, 2, 4));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        globalOverworldGenerationNoLavaLakes(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);

        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_REDWOOD_FOREST);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_BUSH_1);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_FERN_4);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_LARGE_FERN_EXTRA);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.MOSS_SPLATTER);

        return baseBiome(0.8F, 0.6F)
            .modifyAttribute(EnvironmentAttributes.FOG_END_DISTANCE, FloatModifier.MULTIPLY, 0.625F)
            .modifyAttribute(EnvironmentAttributes.SKY_FOG_END_DISTANCE, FloatModifier.MULTIPLY, 0.625F)
            .modifyAttribute(EnvironmentAttributes.CLOUD_FOG_END_DISTANCE, FloatModifier.MULTIPLY, 0.625F)
            .setAttribute(EnvironmentAttributes.INCREASED_FIRE_BURNOUT, true)
            .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(SoundEvents.MUSIC_BIOME_FOREST))
            .specialEffects(
                new BiomeSpecialEffects.Builder()
                    .waterColor(4159204)
                    .grassColorOverride(0xB5D55C)
                    .foliageColorOverride(0x8EBF42)
                    .build()
            )
            .mobSpawnSettings(spawnBuilder.build())
            .generationSettings(biomeBuilder.build()).build();
    }

    public static Biome rockyShrubland(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.CREATURE, 5, new MobSpawnSettings.SpawnerData(EntityType.LLAMA, 4, 6));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_ROCKY_SHRUBLAND);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_SHRUBLAND);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_BUSH_4);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_6);

        return baseBiome(0.6F, 0.05F)
            .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(BOPSounds.MUSIC_BIOME_NOSTALGIC))
            .mobSpawnSettings(spawnBuilder.build())
            .generationSettings(biomeBuilder.build()).build();
    }

    public static Biome scrubland(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.plainsSpawns(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.CREATURE, 6, new MobSpawnSettings.SpawnerData(EntityType.ARMADILLO, 1, 2));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        addFeature(biomeBuilder, GenerationStep.Decoration.UNDERGROUND_DECORATION, BOPVegetationPlacements.TERMITE_MOUND);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_DRY_GRASS_BADLANDS);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_SCRUBLAND);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_SCRUBLAND);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.SCRUB_EXTRA);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_DESERT_GRASS);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_12);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_TALL_GRASS_6);

        return baseBiome(1.1F, 0.15F)
            .hasPrecipitation(false)
            .setAttribute(EnvironmentAttributes.SNOW_GOLEM_MELTS, true)
            .mobSpawnSettings(spawnBuilder.build())
            .generationSettings(biomeBuilder.build()).build();
    }

    public static Biome shrubland(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.plainsSpawns(spawnBuilder);

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_SHRUBLAND);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_SHRUBLAND);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_BUSH_4);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.LILAC_NORMAL);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_REED_5);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_6);

        return baseBiome(0.6F, 0.05F)
            .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(BOPSounds.MUSIC_BIOME_NOSTALGIC))
            .mobSpawnSettings(spawnBuilder.build())
            .generationSettings(biomeBuilder.build()).build();
    }

    public static Biome seasonalForest(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.CREATURE, 5, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 4, 4));
        spawnBuilder.addSpawn(MobCategory.CREATURE, 8, new MobSpawnSettings.SpawnerData(EntityType.FOX, 2, 4));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        BiomeDefaultFeatures.addDefaultFlowers(biomeBuilder);
        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        BiomeDefaultFeatures.addLeafLitterPatch(biomeBuilder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(biomeBuilder, false);

        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_SEASONAL_FOREST);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_SEASONAL_FOREST);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TOADSTOOL_NORMAL);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_REED_5);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_12);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.RED_MAPLE_LEAF_LITTER);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.ORANGE_MAPLE_LEAF_LITTER);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.YELLOW_MAPLE_LEAF_LITTER);

        return baseBiome(0.4F, 0.8F)
            .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(BOPSounds.MUSIC_BIOME_NOSTALGIC))
            .specialEffects(
                new BiomeSpecialEffects.Builder()
                    .waterColor(4159204)
                    .grassColorOverride(0xDD9A4A)
                    .foliageColorOverride(0xD1B24A)
                    .build()
            )
            .mobSpawnSettings(spawnBuilder.build())
            .generationSettings(biomeBuilder.build()).build();
    }

    public static Biome snowblossomGrove(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.CREATURE, 12, new MobSpawnSettings.SpawnerData(EntityType.SHEEP, 4, 4));
        spawnBuilder.addSpawn(MobCategory.CREATURE, 4, new MobSpawnSettings.SpawnerData(EntityType.RABBIT, 2, 3));
        spawnBuilder.addSpawn(MobCategory.CREATURE, 8, new MobSpawnSettings.SpawnerData(EntityType.FOX, 2, 4));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addMossyStoneBlock(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);

        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_SNOWBLOSSOM_GROVE);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_SNOWBLOSSOM_GROVE);

        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_3);

        return baseBiome(-0.25F, 0.8F)
            .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(SoundEvents.MUSIC_BIOME_CHERRY_GROVE))
            .specialEffects(
                new BiomeSpecialEffects.Builder()
                    .waterColor(4159204)
                    .grassColorOverride(0x8EA88B)
                    .foliageColorOverride(0x75936E)
                    .build()
            )
            .mobSpawnSettings(spawnBuilder.build())
            .generationSettings(biomeBuilder.build()).build();
    }

    public static Biome spiderNest(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter)
    {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.MONSTER, 50, new MobSpawnSettings.SpawnerData(EntityType.CAVE_SPIDER, 1, 2));

        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addPlainGrass(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        BiomeDefaultFeatures.addPlainVegetation(biomeBuilder);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPCavePlacements.HANGING_COBWEBS);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPCavePlacements.CORNER_COBWEBS);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPCavePlacements.WEBBING);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPCavePlacements.SPIDER_EGGS);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPCavePlacements.STRINGY_COBWEB);

        return baseBiome(0.5F, 0.5F)
            .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(SoundEvents.MUSIC_BIOME_DRIPSTONE_CAVES))
            .mobSpawnSettings(spawnBuilder.build())
            .generationSettings(biomeBuilder.build()).build();
    }

    public static Biome subtropics(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.plainsSpawns(spawnBuilder);

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addSwampClayDisk(biomeBuilder);
        addFeature(biomeBuilder, GenerationStep.Decoration.UNDERGROUND_ORES, BOPMiscOverworldPlacements.DISK_SURFACE_CLAY);
        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);

        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_SUGAR_CANE_DESERT);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_FIREFLY_BUSH_NEAR_WATER);

        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_SUBTROPICS);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_SUBTROPICS);

        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_BLUE_HYDRANGEA);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_SPROUTS_1);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_12);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_TALL_GRASS_6);

        return baseBiome(0.6F, 0.7F)
            .specialEffects(
                new BiomeSpecialEffects.Builder()
                    .waterColor(4159204)
                    .grassColorOverride(0x86C87C)
                    .foliageColorOverride(0x67B96C)
                    .build()
            )
            .mobSpawnSettings(spawnBuilder.build())
            .generationSettings(biomeBuilder.build()).build();
    }

    public static Biome tropics(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.CREATURE, 10, new MobSpawnSettings.SpawnerData(EntityType.CHICKEN, 4, 4));
        spawnBuilder.addSpawn(MobCategory.CREATURE, 10, new MobSpawnSettings.SpawnerData(EntityType.PIG, 4, 4));
        spawnBuilder.addSpawn(MobCategory.CREATURE, 40, new MobSpawnSettings.SpawnerData(EntityType.PARROT, 1, 2));
        spawnBuilder.addSpawn(MobCategory.WATER_AMBIENT, 15, new MobSpawnSettings.SpawnerData(EntityType.PUFFERFISH, 1, 3));
        spawnBuilder.addSpawn(MobCategory.WATER_AMBIENT, 25, new MobSpawnSettings.SpawnerData(EntityType.TROPICAL_FISH, 8, 8));
        spawnBuilder.addSpawn(MobCategory.WATER_CREATURE, 10, new MobSpawnSettings.SpawnerData(EntityType.SQUID, 4, 4));
        spawnBuilder.addSpawn(MobCategory.WATER_CREATURE, 2, new MobSpawnSettings.SpawnerData(EntityType.DOLPHIN, 1, 2));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        addFeature(biomeBuilder, GenerationStep.Decoration.UNDERGROUND_ORES, BOPMiscOverworldPlacements.DISK_WHITE_SAND_EXTRA);
        addFeature(biomeBuilder, GenerationStep.Decoration.UNDERGROUND_ORES, BOPMiscOverworldPlacements.DISK_WHITE_SANDSTONE);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_SUGAR_CANE);
        BiomeDefaultFeatures.addJungleMelons(biomeBuilder);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_TROPICS);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_TROPICS);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_BLUE_HYDRANGEA_EXTRA);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.ROSE_BUSH_NORMAL);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.SHORT_BAMBOO);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_BUSH_1);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_12);

        return baseBiome(0.95F, 1.0F)
            .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 270131)
            .setAttribute(EnvironmentAttributes.FOG_COLOR, 0xB2EDFF)
            .setAttribute(EnvironmentAttributes.SKY_COLOR, 0x66BCFF)
            .setAttribute(EnvironmentAttributes.CLOUD_COLOR, ARGB.white(0.0F))
            .setAttribute(EnvironmentAttributes.INCREASED_FIRE_BURNOUT, true)
            .setAttribute(EnvironmentAttributes.CAN_PILLAGER_PATROL_SPAWN, false)
            .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(SoundEvents.MUSIC_BIOME_JUNGLE))
            .specialEffects(
                new BiomeSpecialEffects.Builder()
                    .waterColor(4445678)
                    .build()
            )
            .mobSpawnSettings(spawnBuilder.build())
            .generationSettings(biomeBuilder.build()).build();
    }

    public static Biome tundra(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.CREATURE, 5, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 4, 4));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        addFeature(biomeBuilder, GenerationStep.Decoration.UNDERGROUND_ORES, BOPMiscOverworldPlacements.DISK_GRAVEL_EXTRA);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_TUNDRA);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_BUSH_1);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_FERN_2);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_REED_5);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_6);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_TUNDRA_SHRUBS);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.SCATTERED_ROCKS);

        return baseBiome(0.2F, 0.5F)
            .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(BOPSounds.MUSIC_BIOME_NOSTALGIC))
            .specialEffects(
                new BiomeSpecialEffects.Builder()
                    .waterColor(4159204)
                    .grassColorOverride(0xC08359)
                    .foliageColorOverride(0xC5975C)
                    .build()
            )
            .mobSpawnSettings(spawnBuilder.build())
            .generationSettings(biomeBuilder.build()).build();
    }

    public static Biome volcano(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        globalOverworldGeneration(biomeBuilder);
        addFeature(biomeBuilder, GenerationStep.Decoration.LAKES, BOPMiscOverworldPlacements.LAKE_LAVA_SURFACE_EXTRA);
        addFeature(biomeBuilder, GenerationStep.Decoration.FLUID_SPRINGS, BOPMiscOverworldPlacements.SPRING_LAVA_VOLCANO);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);

        addFeature(biomeBuilder, GenerationStep.Decoration.UNDERGROUND_DECORATION, BOPMiscOverworldPlacements.DISK_VOLCANO_SMOOTH_BASALT);
        addFeature(biomeBuilder, GenerationStep.Decoration.UNDERGROUND_DECORATION, BOPMiscOverworldPlacements.DISK_VOLCANO_BLACK_SANDSTONE);
        addFeature(biomeBuilder, GenerationStep.Decoration.UNDERGROUND_DECORATION, BOPMiscOverworldPlacements.DISK_VOLCANO_MAGMA);

        BiomeDefaultFeatures.addExtraEmeralds(biomeBuilder);
        BiomeDefaultFeatures.addInfestedStone(biomeBuilder);

        return baseBiome(0.95F, 0.3F)
            .hasPrecipitation(false)
            .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 267827)
            .modifyAttribute(EnvironmentAttributes.WATER_FOG_END_DISTANCE, FloatModifier.MULTIPLY, 0.85F)
            .setAttribute(EnvironmentAttributes.FOG_COLOR, 0x7F7F7F)
            .setAttribute(EnvironmentAttributes.CLOUD_COLOR, ARGB.color(1.0F, 0x7F7F7F))
            .modifyAttribute(EnvironmentAttributes.FOG_END_DISTANCE, FloatModifier.MULTIPLY, 0.25F)
            .modifyAttribute(EnvironmentAttributes.SKY_FOG_END_DISTANCE, FloatModifier.MULTIPLY, 0.25F)
            .modifyAttribute(EnvironmentAttributes.CLOUD_FOG_END_DISTANCE, FloatModifier.MULTIPLY, 0.25F)
            .setAttribute(EnvironmentAttributes.SNOW_GOLEM_MELTS, true)
            .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(SoundEvents.MUSIC_BIOME_JAGGED_PEAKS))
            .setAttribute(EnvironmentAttributes.AMBIENT_PARTICLES, AmbientParticle.of(ParticleTypes.WHITE_ASH, 0.059046667F))
            .specialEffects(
                new BiomeSpecialEffects.Builder()
                    .waterColor(4566514)
                    .grassColorOverride(0x4A703B)
                    .foliageColorOverride(0x547D42)
                    .dryFoliageColorOverride(0x977A6A)
                    .build()
            )
            .mobSpawnSettings(spawnBuilder.build())
            .generationSettings(biomeBuilder.build()).build();
    }

    public static Biome volcanicPlains(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        globalOverworldGeneration(biomeBuilder);
        addFeature(biomeBuilder, GenerationStep.Decoration.LAKES, BOPMiscOverworldPlacements.LAKE_OBSIDIAN);
        addFeature(biomeBuilder, GenerationStep.Decoration.FLUID_SPRINGS, BOPMiscOverworldPlacements.SPRING_LAVA_VOLCANO);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_VOLCANIC_PLAINS);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPMiscOverworldPlacements.MOSSY_BLACK_SAND_SPLATTER);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_BUSH_1);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_SPROUTS_10);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_12);

        return baseBiome(0.95F, 0.3F)
            .hasPrecipitation(false)
            .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 267827)
            .modifyAttribute(EnvironmentAttributes.WATER_FOG_END_DISTANCE, FloatModifier.MULTIPLY, 0.85F)
            .setAttribute(EnvironmentAttributes.CLOUD_COLOR, ARGB.color(0.8F, 0xBEBEBE))
            .modifyAttribute(EnvironmentAttributes.FOG_END_DISTANCE, FloatModifier.MULTIPLY, 0.5F)
            .modifyAttribute(EnvironmentAttributes.SKY_FOG_END_DISTANCE, FloatModifier.MULTIPLY, 0.5F)
            .modifyAttribute(EnvironmentAttributes.CLOUD_FOG_END_DISTANCE, FloatModifier.MULTIPLY, 0.5F)
            .setAttribute(EnvironmentAttributes.SNOW_GOLEM_MELTS, true)
            .specialEffects(
                new BiomeSpecialEffects.Builder()
                    .waterColor(4566514)
                    .grassColorOverride(0x4A703B)
                    .foliageColorOverride(0x547D42)
                    .dryFoliageColorOverride(0xA06F5D)
                    .build()
            )
            .mobSpawnSettings(spawnBuilder.build())
            .generationSettings(biomeBuilder.build()).build();
    }

    public static Biome wasteland(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawnWithZombieHorse(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.MONSTER, 80, new MobSpawnSettings.SpawnerData(EntityType.HUSK, 4, 4));
        spawnBuilder.addSpawn(MobCategory.MONSTER, 50, new MobSpawnSettings.SpawnerData(EntityType.PARCHED, 4, 4));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);

        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_WASTELAND);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_WASTELAND);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.WASTELAND_GRASS_2);

        return baseBiome(2.0F, 0.0F)
            .hasPrecipitation(false)
            .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 0x0C0C03)
            .modifyAttribute(EnvironmentAttributes.WATER_FOG_END_DISTANCE, FloatModifier.MULTIPLY, 0.25F)
            .setAttribute(EnvironmentAttributes.FOG_COLOR, 0xDBDDC1)
            .setAttribute(EnvironmentAttributes.SKY_COLOR, 0x89A3B7)
            .modifyAttribute(EnvironmentAttributes.FOG_END_DISTANCE, FloatModifier.MULTIPLY, 0.25F)
            .modifyAttribute(EnvironmentAttributes.SKY_FOG_END_DISTANCE, FloatModifier.MULTIPLY, 0.25F)
            .modifyAttribute(EnvironmentAttributes.CLOUD_FOG_END_DISTANCE, FloatModifier.MULTIPLY, 0.25F)
            .setAttribute(EnvironmentAttributes.SNOW_GOLEM_MELTS, true)
            .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(SoundEvents.MUSIC_BIOME_DESERT))
            .setAttribute(EnvironmentAttributes.AMBIENT_PARTICLES, AmbientParticle.of(ParticleTypes.MYCELIUM, 0.00357F))
            .specialEffects(
                new BiomeSpecialEffects.Builder()
                    .waterColor(0x433721)
                    .grassColorOverride(0xAD9364)
                    .foliageColorOverride(0xB5A76C)
                    .dryFoliageColorOverride(0xA08963)
                    .build()
            )
            .mobSpawnSettings(spawnBuilder.build())
            .generationSettings(biomeBuilder.build()).build();
    }

    public static Biome wastelandSteppe(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawnWithZombieHorse(spawnBuilder);

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);

        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_DEAD_GRASS);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.WASTELAND_GRASS_2);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_6);

        return baseBiome(2.0F, 0.0F)
            .hasPrecipitation(false)
            .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 0x08081B)
            .modifyAttribute(EnvironmentAttributes.WATER_FOG_END_DISTANCE, FloatModifier.MULTIPLY, 0.5F)
            .setAttribute(EnvironmentAttributes.FOG_COLOR, 0xCDDADF)
            .setAttribute(EnvironmentAttributes.SKY_COLOR, 0x7CA8D6)
            .modifyAttribute(EnvironmentAttributes.FOG_END_DISTANCE, FloatModifier.MULTIPLY, 0.375F)
            .modifyAttribute(EnvironmentAttributes.SKY_FOG_END_DISTANCE, FloatModifier.MULTIPLY, 0.375F)
            .modifyAttribute(EnvironmentAttributes.CLOUD_FOG_END_DISTANCE, FloatModifier.MULTIPLY, 0.375F)
            .setAttribute(EnvironmentAttributes.SNOW_GOLEM_MELTS, true)
            .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(SoundEvents.MUSIC_BIOME_DESERT))
            .specialEffects(
                new BiomeSpecialEffects.Builder()
                    .waterColor(0x405682)
                    .grassColorOverride(0xC9B986)
                    .foliageColorOverride(0xC7C38F)
                    .dryFoliageColorOverride(0xA18454)
                    .build()
            )
            .mobSpawnSettings(spawnBuilder.build())
            .generationSettings(biomeBuilder.build()).build();
    }

    public static Biome wetland(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder, 70);
        spawnBuilder.addSpawn(MobCategory.CREATURE, 10, new MobSpawnSettings.SpawnerData(EntityType.PIG, 4, 4));
        spawnBuilder.addSpawn(MobCategory.CREATURE, 8, new MobSpawnSettings.SpawnerData(EntityType.FOX, 2, 4));
        spawnBuilder.addSpawn(MobCategory.CREATURE, 10, new MobSpawnSettings.SpawnerData(EntityType.FROG, 2, 5));
        spawnBuilder.addSpawn(MobCategory.MONSTER, 1, new MobSpawnSettings.SpawnerData(EntityType.SLIME, 1, 1));
        spawnBuilder.addSpawn(MobCategory.MONSTER, 30, new MobSpawnSettings.SpawnerData(EntityType.BOGGED, 4, 4));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addSwampClayDisk(biomeBuilder);
        addFeature(biomeBuilder, GenerationStep.Decoration.UNDERGROUND_ORES, BOPMiscOverworldPlacements.DISK_MUD);
        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        BiomeDefaultFeatures.addLeafLitterPatch(biomeBuilder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(biomeBuilder, true);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_WETLAND);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_WETLAND);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_CATTAIL_EXTRA);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_FERN_8);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.LILY_PAD_10);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TOADSTOOL_NORMAL);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_REED_5);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_12);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_WATERGRASS_10);

        return baseBiome(0.6F, 0.7F)
            .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 0x0C031B)
            .modifyAttribute(EnvironmentAttributes.WATER_FOG_END_DISTANCE, FloatModifier.MULTIPLY, 0.85F)
            .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(SoundEvents.MUSIC_BIOME_SWAMP))
            .specialEffects(
                new BiomeSpecialEffects.Builder()
                    .waterColor(0x272179)
                    .grassColorOverride(0x5A935F)
                    .foliageColorOverride(0x4F9657)
                    .build()
            )
            .mobSpawnSettings(spawnBuilder.build())
            .generationSettings(biomeBuilder.build()).build();
    }

    public static Biome woodland(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter, boolean dense)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        BiomeDefaultFeatures.farmAnimals(spawnBuilder);

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(biomeBuilder, true);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_DEFAULT_EXTENDED_6);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.ROSE_BUSH_NORMAL);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FALLEN_LOG);

        if (dense)
        {
            addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_DENSE_WOODLAND);
            addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_12);
            addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_TALL_GRASS_12);
        }
        else
        {
            addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_6);
            addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_TALL_GRASS_6);
            addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_WOODLAND);
        }

        return baseBiome(0.8F, 0.5F)
            .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(BOPSounds.MUSIC_BIOME_NOSTALGIC))
            .specialEffects(
                new BiomeSpecialEffects.Builder()
                    .waterColor(4159204)
                    .grassColorOverride(0x9CC439)
                    .foliageColorOverride(0x85B408)
                    .build()
            )
            .mobSpawnSettings(spawnBuilder.build())
            .generationSettings(biomeBuilder.build()).build();
    }
}
