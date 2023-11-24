/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.biome;

import biomesoplenty.api.sound.BOPSounds;
import biomesoplenty.common.worldgen.carver.BOPConfiguredCarvers;
import biomesoplenty.common.worldgen.placement.BOPCavePlacements;
import biomesoplenty.common.worldgen.placement.BOPMiscOverworldPlacements;
import biomesoplenty.common.worldgen.placement.BOPVegetationPlacements;
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
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import javax.annotation.Nullable;

public class BOPOverworldBiomes
{
    @Nullable
    private static final Music NORMAL_MUSIC = null;
    private static final Music FOREST_MUSIC = Musics.createGameMusic(SoundEvents.MUSIC_BIOME_FOREST);
    private static final Music SWAMP_MUSIC = Musics.createGameMusic(SoundEvents.MUSIC_BIOME_SWAMP);
    private static final Music JUNGLE_MUSIC = Musics.createGameMusic(SoundEvents.MUSIC_BIOME_JUNGLE);
    private static final Music CAVE_MUSIC = Musics.createGameMusic(SoundEvents.MUSIC_BIOME_DRIPSTONE_CAVES);
    private static final Music MOUNTAIN_MUSIC = Musics.createGameMusic(SoundEvents.MUSIC_BIOME_JAGGED_PEAKS);
    private static final Music DESERT_MUSIC = Musics.createGameMusic(SoundEvents.MUSIC_BIOME_DESERT);
    private static final Music MAGICAL_MUSIC = Musics.createGameMusic(SoundEvents.MUSIC_BIOME_CHERRY_GROVE);

    protected static int calculateSkyColor(float color)
    {
        float $$1 = color / 3.0F;
        $$1 = Mth.clamp($$1, -1.0F, 1.0F);
        return Mth.hsvToRgb(0.62222224F - $$1 * 0.05F, 0.5F + $$1 * 0.1F, 1.0F);
    }

    private static Biome biome(boolean hasPrecipitation, float temperature, float downfall, MobSpawnSettings.Builder spawnBuilder, BiomeGenerationSettings.Builder biomeBuilder, @Nullable Music music)
    {
        return biome(hasPrecipitation, temperature, downfall, 4159204, 329011, spawnBuilder, biomeBuilder, music);
    }

    private static Biome biomeWithColorOverrides(boolean hasPrecipitation, float temperature, float downfall, int grassColor, int foliageColor, MobSpawnSettings.Builder spawnBuilder, BiomeGenerationSettings.Builder biomeBuilder, @Nullable Music music)
    {
        return biomeWithColorOverrides(hasPrecipitation, temperature, downfall, 4159204, 329011, grassColor, foliageColor, spawnBuilder, biomeBuilder, music);
    }

    private static Biome biome(boolean hasPrecipitation, float temperature, float downfall, int waterColor, int waterFogColor, MobSpawnSettings.Builder spawnBuilder, BiomeGenerationSettings.Builder biomeBuilder, @Nullable Music music)
    {
        return (new Biome.BiomeBuilder()).hasPrecipitation(hasPrecipitation).temperature(temperature).downfall(downfall).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(waterColor).waterFogColor(waterFogColor).fogColor(12638463).skyColor(calculateSkyColor(temperature)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).backgroundMusic(music).build()).mobSpawnSettings(spawnBuilder.build()).generationSettings(biomeBuilder.build()).build();
    }

    private static Biome biome(boolean hasPrecipitation, float temperature, float downfall, int waterColor, int waterFogColor, int fogColor, int skyColor, MobSpawnSettings.Builder spawnBuilder, BiomeGenerationSettings.Builder biomeBuilder, @Nullable Music music)
    {
        return (new Biome.BiomeBuilder()).hasPrecipitation(hasPrecipitation).temperature(temperature).downfall(downfall).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(waterColor).waterFogColor(waterFogColor).fogColor(fogColor).skyColor(skyColor).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).backgroundMusic(music).build()).mobSpawnSettings(spawnBuilder.build()).generationSettings(biomeBuilder.build()).build();
    }

    private static Biome biomeWithColorOverrides(boolean hasPrecipitation, float temperature, float downfall, int waterColor, int waterFogColor, int grassColor, int foliageColor, MobSpawnSettings.Builder spawnBuilder, BiomeGenerationSettings.Builder biomeBuilder, @Nullable Music music)
    {
        return (new Biome.BiomeBuilder()).hasPrecipitation(hasPrecipitation).temperature(temperature).downfall(downfall).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(waterColor).waterFogColor(waterFogColor).fogColor(12638463).grassColorOverride(grassColor).foliageColorOverride(foliageColor).skyColor(calculateSkyColor(temperature)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).backgroundMusic(music).build()).mobSpawnSettings(spawnBuilder.build()).generationSettings(biomeBuilder.build()).build();
    }

    private static Biome biomeWithColorOverrides(boolean hasPrecipitation, float temperature, float downfall, int waterColor, int waterFogColor, int fogColor, int grassColor, int foliageColor, int skyColor, MobSpawnSettings.Builder spawnBuilder, BiomeGenerationSettings.Builder biomeBuilder, @Nullable Music music)
    {
        return (new Biome.BiomeBuilder()).hasPrecipitation(hasPrecipitation).temperature(temperature).downfall(downfall).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(waterColor).waterFogColor(waterFogColor).fogColor(fogColor).grassColorOverride(grassColor).foliageColorOverride(foliageColor).skyColor(skyColor).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).backgroundMusic(music).build()).mobSpawnSettings(spawnBuilder.build()).generationSettings(biomeBuilder.build()).build();
    }

    private static Biome biomeWithColorOverridesAndParticles(boolean hasPrecipitation, float temperature, float downfall, int waterColor, int waterFogColor, int fogColor, int grassColor, int foliageColor, int skyColor, MobSpawnSettings.Builder spawnBuilder, BiomeGenerationSettings.Builder biomeBuilder, ParticleOptions particleOptions, float particleProbability, @Nullable Music music)
    {
        return (new Biome.BiomeBuilder()).hasPrecipitation(hasPrecipitation).temperature(temperature).downfall(downfall).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(waterColor).waterFogColor(waterFogColor).fogColor(fogColor).grassColorOverride(grassColor).foliageColorOverride(foliageColor).skyColor(skyColor).ambientParticle(new AmbientParticleSettings(particleOptions, particleProbability)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).backgroundMusic(music).build()).mobSpawnSettings(spawnBuilder.build()).generationSettings(biomeBuilder.build()).build();
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
        builder.addCarver(GenerationStep.Carving.AIR, Carvers.CAVE);
        builder.addCarver(GenerationStep.Carving.AIR, Carvers.CAVE_EXTRA_UNDERGROUND);
        builder.addCarver(GenerationStep.Carving.AIR, Carvers.CANYON);
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
        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.FOX, 8, 2, 4));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_AURORAL_GARDEN);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_VIOLET);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_ICY_IRIS);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_12);

        return biomeWithColorOverrides(true, -0.25F, 0.5F, 0x3938C9, 0x050533, 0xF0FDFD, 0xDBFCFC, 0xA5F7F7, 0xBDECFC, spawnBuilder, biomeBuilder, MAGICAL_MUSIC);
    }

    public static Biome bayou(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.PIG, 10, 4, 4));
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.FROG, 10, 2, 5));

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

        return biomeWithColorOverrides(true, 0.95F, 0.9F, 0x62AF84, 0x0C211C, 12638463, 0x6FAA50, 0x8BDB67, calculateSkyColor(0.95F), spawnBuilder, biomeBuilder, SWAMP_MUSIC);
    }

    public static Biome bog(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        globalOverworldGeneration(biomeBuilder);
        addFeature(biomeBuilder, GenerationStep.Decoration.LAKES, BOPMiscOverworldPlacements.LAKE_WATER);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addSwampClayDisk(biomeBuilder);
        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        BiomeDefaultFeatures.addCommonBerryBushes(biomeBuilder);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_BOG);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_BUSH_10);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_FERN_4);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_REED_10);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_6);

        return biomeWithColorOverrides(true, 0.2F, 0.5F, 0xA89557, 0xC67F5B, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
    }

    public static Biome cloverPatch(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.plainsSpawns(spawnBuilder);

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_SUGAR_CANE_DESERT);

        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_CLOVER_PATCH);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.CLOVER_EXTRA);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_6);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.HUGE_CLOVER);

        return biomeWithColorOverrides(true, 0.6F, 0.7F, 0x88C57F, 0x6AB66F, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
    }

    public static Biome coldDesert(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.POLAR_BEAR, 1, 1, 2));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        globalOverworldGeneration(biomeBuilder);
        addFeature(biomeBuilder, GenerationStep.Decoration.LOCAL_MODIFICATIONS, BOPVegetationPlacements.COLD_DESERT_ROCKS);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.WASTELAND_GRASS_1);

        return biomeWithColorOverrides(false, 0.25F, 0.0F, 0xAD9364, 0xB5A76C, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
    }

    public static Biome coniferousForest(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter, boolean isSnowy)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        if (!isSnowy) BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 5, 4, 4));
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.RABBIT, 4, 2, 3));
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.FOX, 8, 2, 4));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        globalOverworldGenerationNoLavaLakes(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_PUMPKIN);
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

        return biome(true, isSnowy ? -0.25F : 0.45F, 0.5F, spawnBuilder, biomeBuilder, FOREST_MUSIC);
    }

    public static Biome crag(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.GOAT, 10, 4, 6));

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

        return biome(true, 0.6F, 0.6F, spawnBuilder, biomeBuilder, MOUNTAIN_MUSIC);
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
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_PUMPKIN);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_DEAD_FOREST);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_3);

        return biomeWithColorOverrides(true, 0.2F, 0.3F, 0xBAAD64, 0xB7B763, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
    }

    public static Biome dryland(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.CHICKEN, 10, 4, 4));
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.COW, 8, 4, 4));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);

        // Vegetation
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_DEAD_BUSH_2);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_DRYLAND);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_BUSH_1);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_6);

        return biomeWithColorOverrides(false, 0.85F, 0.05F, 4159204, 329011, 12638463, 0xE5DFA9, 0xDAE0B3, 0x9E9DFF, spawnBuilder, biomeBuilder, DESERT_MUSIC);
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

        return biome(true, 0.7F, 0.4F, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
    }

    public static Biome field(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter, boolean forest)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.FOX, 8, 2, 4));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_PUMPKIN);
        BiomeDefaultFeatures.addRareBerryBushes(biomeBuilder);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_FIELD_1);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.LILAC_NORMAL);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.ROSE_BUSH_NORMAL);

        // Vegetation
        if (forest)
        {
            addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_FIELD_FOREST);
            addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_SPROUTS_15);
        }
        else
        {
            addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_FIELD);
            addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_FIELD_2);
            addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_SPROUTS_10);
            addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.SUNFLOWER);
        }

        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_6);

        return biomeWithColorOverrides(true, 0.4F, 0.7F, 0x63B26D, 0x63B26D, spawnBuilder, biomeBuilder, FOREST_MUSIC);
    }

    public static Biome firClearing(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter, boolean snowy)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 5, 4, 4));
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.RABBIT, 4, 2, 3));
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.FOX, 8, 2, 4));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        globalOverworldGenerationNoLavaLakes(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_PUMPKIN);
        BiomeDefaultFeatures.addRareBerryBushes(biomeBuilder);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_FIR_CLEARING);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FALLEN_FIR_LOG);

        if (!snowy)
        {
            addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_CONIFEROUS_FOREST);
            addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_FERN_4);
            addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TOADSTOOL_NORMAL);
            addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_12);
            return biome(true, 0.45F, 0.5F, spawnBuilder, biomeBuilder, FOREST_MUSIC);
        }
        else
        {
            addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_FERN_2);
            addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_1);
            return biome(true, -0.25F, 0.5F, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
        }
    }

    public static Biome floodplain(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.FROG, 10, 2, 5));

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

        return biomeWithColorOverrides(true, 1.2F, 2.0F, 0x3FAABE, 0x041A2C, 0x7FD43D, 0x5BCD25, spawnBuilder, biomeBuilder, JUNGLE_MUSIC);
    }

    public static Biome fungalJungle(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.FROG, 10, 2, 5));

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
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.MYCELIUM_SPLATTER);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_SPROUTS_5);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_12);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_TALL_GRASS_12);

        return biomeWithColorOverrides(true, 0.9F, 0.9F, 0x3FDF64, 0x042F18, 0xE1ED63, 0xCCE525, 0xE0E522, 0x62DCEA, spawnBuilder, biomeBuilder, JUNGLE_MUSIC);
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

        return biome(true, 0.5F, 0.5F, spawnBuilder, biomeBuilder, CAVE_MUSIC);
    }

    public static Biome grassland(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        BiomeDefaultFeatures.farmAnimals(spawnBuilder);

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);

        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_GRASSLAND);

        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_24);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_TALL_GRASS_64);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.HIGH_GRASS);

        return biomeWithColorOverrides(true, 0.6F, 0.9F, 0x5EBF6A, 0x55BC71, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
    }

    public static Biome highland(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.GOAT, 10, 4, 6));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        globalOverworldGeneration(biomeBuilder);
        addFeature(biomeBuilder, GenerationStep.Decoration.LOCAL_MODIFICATIONS, BOPVegetationPlacements.HIGHLAND_ROCKS);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        BiomeDefaultFeatures.addExtraEmeralds(biomeBuilder);
        BiomeDefaultFeatures.addInfestedStone(biomeBuilder);

        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_24);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_TALL_GRASS_64);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.SCATTERED_ROCKS_HIGHLAND);

        return biome(true, 0.6F, 0.6F, spawnBuilder, biomeBuilder, MOUNTAIN_MUSIC);
    }

    public static Biome hotSprings(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.FOX, 8, 2, 4));

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

        return biomeWithColorOverrides(true, 0.17F, 0.5F, 4445678, 270131, 0x80B497, 0x60A17B, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
    }

    public static Biome jadeCliffs(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.PANDA, 1, 1, 2));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        BiomeDefaultFeatures.addExtraEmeralds(biomeBuilder);
        BiomeDefaultFeatures.addInfestedStone(biomeBuilder);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_SUGAR_CANE);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_JADE_CLIFFS);

        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_6);

        return biomeWithColorOverrides(true, 0.8F, 0.85F, 4159204, 329011, 0xBBD1D5, 0x7CA568, 0x8BB76E, 0xB7CCAD, spawnBuilder, biomeBuilder, MOUNTAIN_MUSIC);
    }

    public static Biome lavenderField(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter, boolean forest)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        BiomeDefaultFeatures.farmAnimals(spawnBuilder);

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_SUGAR_CANE);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_24);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_TALL_GRASS_64);

        if (forest)
        {
            addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_LAVENDER_FOREST);
            addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_TALL_LAVENDER_FOREST);
            addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_LAVENDER_FOREST);
        }
        else
        {
            addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_LAVENDER);
            addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_TALL_LAVENDER);
            addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_LAVENDER_FIELD);
        }

        return biomeWithColorOverrides(true, 0.8F, 0.7F, 0xA1C36D, 0xA1C36D, spawnBuilder, biomeBuilder, MAGICAL_MUSIC);
    }

    public static Biome lushDesert(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.RABBIT, 4, 2, 3));
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.HUSK, 80, 4, 4));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        addFeature(biomeBuilder, GenerationStep.Decoration.FLUID_SPRINGS, BOPMiscOverworldPlacements.SPRING_WATER_EXTRA);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_SUGAR_CANE);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_LUSH_DESERT);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_LUSH_DESERT);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_DESERT_GRASS_EXTRA);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_DUNE_GRASS);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_SPROUTS_1);

        return biomeWithColorOverrides(true, 0.9F, 0.5F, 4566514, 267827, 0xEFE182, 0xD3D156, spawnBuilder, biomeBuilder, DESERT_MUSIC);
    }

    public static Biome lushSavanna(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.RABBIT, 4, 2, 3));
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.DONKEY, 1, 1, 3));
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.HUSK, 80, 4, 4));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        addFeature(biomeBuilder, GenerationStep.Decoration.FLUID_SPRINGS, BOPMiscOverworldPlacements.SPRING_WATER_EXTRA);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_SUGAR_CANE);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_POPPY);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.ROSE_BUSH_EXTRA);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_SPROUTS_5);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_24);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_TALL_GRASS_24);

        return biomeWithColorOverrides(true, 0.9F, 0.5F, 4566514, 267827, 0xEFE182, 0xD3D156, spawnBuilder, biomeBuilder, DESERT_MUSIC);
    }

    public static Biome mapleWoods(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter, boolean snowy)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 5, 4, 4));
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.RABBIT, 4, 2, 3));
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.FOX, 8, 2, 4));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_PUMPKIN);
        BiomeDefaultFeatures.addRareBerryBushes(biomeBuilder);

        if (!snowy)
        {
            addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_MAPLE_FOREST);
            addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_SNOWY);
            addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_FERN_2);
            addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_6);
            return biome(true, 0.25F, 0.5F, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
        }
        else
        {
            addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_SNOWY_MAPLE_FOREST);
            addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_SNOWY);
            addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_FERN_2);
            addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_3);
            return biome(true, -0.25F, 0.5F, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
        }
    }

    public static Biome marsh(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        globalOverworldGeneration(biomeBuilder);
        addFeature(biomeBuilder, GenerationStep.Decoration.LAKES, BOPMiscOverworldPlacements.LAKE_WATER_MARSH);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, AquaticPlacements.SEAGRASS_SWAMP);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_TALL_GRASS_250);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_REED_10);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_24);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_WATERGRASS_250);

        return biome(true, 0.65F, 0.7F, spawnBuilder, biomeBuilder, SWAMP_MUSIC);
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
        addFeature(biomeBuilder, GenerationStep.Decoration.UNDERGROUND_ORES, BOPMiscOverworldPlacements.DISK_CALCITE);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_PUMPKIN);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_MEDITERRANEAN_FOREST);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_DEFAULT_EXTENDED_6);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_BUSH_1);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PEONY);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_3);

        return biome(true, 0.8F, 0.275F, 4566514, 267827, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
    }

    public static Biome moor(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.GOAT, 10, 4, 6));

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
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_12);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_TALL_GRASS_12);

        return biomeWithColorOverrides(true, 0.6F, 0.6F, 0x71AA70, 0x78C46D, spawnBuilder, biomeBuilder, MOUNTAIN_MUSIC);
    }

    public static Biome muskeg(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.FROG, 10, 2, 5));

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

        return biomeWithColorOverrides(true, 0.0F, 0.6F, 0x94966E, 0x8D9B6B, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
    }

    public static Biome mysticGrove(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.caveSpawns(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.RABBIT, 4, 2, 3));
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.FOX, 8, 2, 4));
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.ALLAY, 10, 1, 1));
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.ZOMBIE_VILLAGER, 5, 1, 1));
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.SLIME, 100, 4, 4));
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.WITCH, 20, 1, 1));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        globalOverworldGenerationNoLavaLakes(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        addFeature(biomeBuilder, GenerationStep.Decoration.UNDERGROUND_ORES, MiscOverworldPlacements.DISK_CLAY);
        addFeature(biomeBuilder, GenerationStep.Decoration.UNDERGROUND_ORES, BOPMiscOverworldPlacements.DISK_WHITE_SAND);
        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_MYSTIC_GROVE);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_MYSTIC_GROVE);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_BLUE_HYDRANGEA);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.HUGE_RED_MUSHROOM_EXTRA);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.SMALL_RED_MUSHROOM);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.CLOVER_NORMAL);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.LILAC_NORMAL);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_SPROUTS_15);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_12);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_WATERGRASS_10);

        return biomeWithColorOverridesAndParticles(true, 0.7F, 0.8F, 0x9C3FE4, 0x2E0533, 0xFFC9DA, 0x69CFDB, 0x70E0B5, 0xAAEFFF, spawnBuilder, biomeBuilder, ParticleTypes.END_ROD, 0.00011532552F, MAGICAL_MUSIC);
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
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_PUMPKIN);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_OLD_GROWTH_DEAD_FOREST);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_6);

        return biomeWithColorOverrides(true, 0.3F, 0.3F, 0xBAAD64, 0xB7B763, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
    }

    public static Biome ominousWoods(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.caveSpawns(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.ILLUSIONER, 50, 2, 4));
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.SPIDER, 100, 4, 4));
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.SLIME, 100, 4, 4));
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.ENDERMAN, 10, 1, 4));
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.WITCH, 5, 1, 1));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        globalOverworldGenerationNoLavaLakes(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_PUMPKIN);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_OMINOUS_WOODS);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_OMINOUS_WOODS);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_DEAD_GRASS);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_6);

        return biomeWithColorOverrides(true, 0.6F, 0.6F, 0x312346, 0x0A030C, 0x7881A5, 0x4C4A70, 0x6B487C, 0x84A1CC, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
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
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_SUGAR_CANE);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_ORCHARD);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_DEFAULT_EXTENDED_15);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.CLOVER_NORMAL);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.ROSE_BUSH_NORMAL);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_SPROUTS_5);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_12);

        return biomeWithColorOverrides(true, 0.8F, 0.4F, 0xA9DB69, 0xC9F75D, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
    }

    public static Biome originValley(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.SPIDER, 100, 4, 4));
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.ZOMBIE, 95, 4, 4));
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.SKELETON, 100, 4, 4));
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.CREEPER, 100, 4, 4));
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.SLIME, 100, 4, 4));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        biomeBuilder.addCarver(GenerationStep.Carving.AIR, BOPConfiguredCarvers.ORIGIN_CAVE);
        BiomeDefaultFeatures.addDefaultMonsterRoom(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSprings(biomeBuilder);
        BiomeDefaultFeatures.addSurfaceFreezing(biomeBuilder);
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

        return biomeWithColorOverrides(true, 0.6F, 0.6F, 0x0E31FF, 0x070059, 0xB0CFFF, 0x9AFF5F, 0x3AFF00, 0x8CBDFF, spawnBuilder, biomeBuilder, Musics.createGameMusic(BOPSounds.MUSIC_BIOME_ORIGIN_VALLEY.getHolder().orElseThrow()));
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

        return biomeWithColorOverrides(true, 0.8F, 0.3F, 0xE4EA77, 0xC7E672, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
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
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_PRAIRIE);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_CONIFEROUS_FOREST);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.ROOTED_STUMP);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.GOLDENROD);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_24);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_TALL_GRASS_24);

        return biomeWithColorOverrides(true, 0.8F, 0.3F, 0xE4EA77, 0xC7E672, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
    }

    public static Biome pumpkinPatch(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 5, 4, 4));
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.FOX, 8, 2, 4));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_SEASONAL_PUMPKIN_PATCH);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.BIG_PUMPKIN);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PUMPKIN_PATCH);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TOADSTOOL_NORMAL);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_SPROUTS_10);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_REED_5);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_6);

        return biomeWithColorOverrides(true, 0.4F, 0.8F, 0xDDBF4A, 0xCED14A, spawnBuilder, biomeBuilder, FOREST_MUSIC);
    }

    public static Biome rainforest(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.CHICKEN, 10, 4, 4));
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.PARROT, 40, 1, 2));
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.OCELOT, 2, 1, 1));

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
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.BIG_DRIPLEAF);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_FERN_16);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_LARGE_FERN_EXTRA);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.LILY_PAD_10);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.SMALL_DRIPLEAF);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_12);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_TALL_GRASS_12);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_WATERGRASS_10);

        return biomeWithColorOverrides(true, 1.2F, 2.0F, 0x3FDF99, 0x042F26, 0xA7E140, 0x88E140, spawnBuilder, biomeBuilder, JUNGLE_MUSIC);
    }

    public static Biome rockyRainforest(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.PARROT, 40, 1, 2));
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.OCELOT, 2, 1, 1));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        addFeature(biomeBuilder, GenerationStep.Decoration.UNDERGROUND_ORES, BOPMiscOverworldPlacements.DISK_ORANGE_SAND);
        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_SUGAR_CANE);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_RAINFOREST_CLIFFS);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_RAINFOREST);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_FERN_16);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_LARGE_FERN_NORMAL);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.RAINFOREST_CLIFFS_VINES);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_SPROUTS_15);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_12);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_TALL_GRASS_24);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_WATERGRASS_10);

        return biomeWithColorOverrides(true, 1.2F, 2.0F, 0x3FDF99, 0x042F26, 0xA7E140, 0x88E140, spawnBuilder, biomeBuilder, JUNGLE_MUSIC);
    }

    public static Biome redwoodForest(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 5, 4, 4));
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.RABBIT, 4, 2, 3));
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.FOX, 8, 2, 4));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        globalOverworldGenerationNoLavaLakes(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);

        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_REDWOOD_FOREST);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_FERN_4);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_LARGE_FERN_EXTRA);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.MOSS_SPLATTER);

        return biomeWithColorOverrides(true, 0.8F, 0.6F, 0xB5D55C, 0x8EBF42, spawnBuilder, biomeBuilder, FOREST_MUSIC);
    }

    public static Biome rockyShrubland(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.LLAMA, 5, 4, 6));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_ROCKY_SHRUBLAND);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_SHRUBLAND);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_BUSH_4);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_6);

        return biome(true, 0.6F, 0.05F, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
    }

    public static Biome scrubland(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.plainsSpawns(spawnBuilder);

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_SCRUBLAND);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_SCRUBLAND);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.SCRUB_EXTRA);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_DESERT_GRASS);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_TALL_GRASS_6);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_12);

        return biome(false, 1.1F, 0.15F, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
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
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_REED_10);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_6);

        return biome(true, 0.6F, 0.05F, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
    }

    public static Biome seasonalForest(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 5, 4, 4));
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.FOX, 8, 2, 4));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        BiomeDefaultFeatures.addDefaultFlowers(biomeBuilder);
        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_PUMPKIN);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_SEASONAL_FOREST);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TOADSTOOL_NORMAL);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_REED_5);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_12);

        return biomeWithColorOverrides(true, 0.4F, 0.8F, 0xDD9A4A, 0xD1B24A, spawnBuilder, biomeBuilder, FOREST_MUSIC);
    }

    public static Biome seasonalOrchard(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 5, 4, 4));
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.FOX, 8, 2, 4));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        BiomeDefaultFeatures.addDefaultFlowers(biomeBuilder);
        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_PUMPKIN);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_SEASONAL_ORCHARD);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_BARLEY_SPARSE);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_FERN_4);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_LARGE_FERN_NORMAL);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TOADSTOOL_NORMAL);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_REED_10);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_6);

        return biomeWithColorOverrides(true, 0.4F, 0.8F, 0xDDAB4A, 0xD1C24A, spawnBuilder, biomeBuilder, FOREST_MUSIC);
    }

    public static Biome snowblossomGrove(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.RABBIT, 4, 2, 3));
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.FOX, 8, 2, 4));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addMossyStoneBlock(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);

        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_SNOWBLOSSOM_GROVE);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_SNOWBLOSSOM_GROVE);

        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_3);

        return biomeWithColorOverrides(true, -0.25F, 0.8F, 0x8EA88B, 0x75936E, spawnBuilder, biomeBuilder, MAGICAL_MUSIC);
    }

    public static Biome spiderNest(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter)
    {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.CAVE_SPIDER, 50, 1, 2));

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

        return biome(true, 0.5F, 0.5F, spawnBuilder, biomeBuilder, CAVE_MUSIC);
    }

    public static Biome tropics(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.CHICKEN, 10, 4, 4));
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.PIG, 10, 4, 4));
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.PARROT, 40, 1, 2));
        spawnBuilder.addSpawn(MobCategory.WATER_AMBIENT, new MobSpawnSettings.SpawnerData(EntityType.PUFFERFISH, 15, 1, 3));
        spawnBuilder.addSpawn(MobCategory.WATER_AMBIENT, new MobSpawnSettings.SpawnerData(EntityType.TROPICAL_FISH, 25, 8, 8));
        spawnBuilder.addSpawn(MobCategory.WATER_CREATURE, new MobSpawnSettings.SpawnerData(EntityType.SQUID, 10, 4, 4));
        spawnBuilder.addSpawn(MobCategory.WATER_CREATURE, new MobSpawnSettings.SpawnerData(EntityType.DOLPHIN, 2, 1, 2));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        addFeature(biomeBuilder, GenerationStep.Decoration.UNDERGROUND_ORES, BOPMiscOverworldPlacements.DISK_WHITE_SAND_EXTRA);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_SUGAR_CANE);
        BiomeDefaultFeatures.addJungleMelons(biomeBuilder);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_TROPICS);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_TROPICS);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_BLUE_HYDRANGEA);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.ROSE_BUSH_NORMAL);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.SHORT_BAMBOO);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_12);

        return biome(true, 0.95F, 1.0F, 4445678, 270131, 0xB2EDFF, 0x66BCFF, spawnBuilder, biomeBuilder, JUNGLE_MUSIC);
    }

    public static Biome tundra(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 5, 4, 4));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        addFeature(biomeBuilder, GenerationStep.Decoration.UNDERGROUND_ORES, BOPMiscOverworldPlacements.DISK_GRAVEL_EXTRA);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_TUNDRA);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_FERN_2);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_REED_5);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_6);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_TUNDRA_SHRUBS);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.SCATTERED_ROCKS);

        return biomeWithColorOverrides(true, 0.2F, 0.5F, 0xC08359, 0xC5975C, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
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
        addFeature(biomeBuilder, GenerationStep.Decoration.UNDERGROUND_ORES, MiscOverworldPlacements.DISK_GRAVEL);
        BiomeDefaultFeatures.addExtraEmeralds(biomeBuilder);
        BiomeDefaultFeatures.addInfestedStone(biomeBuilder);

        return biomeWithColorOverridesAndParticles(false, 0.95F, 0.3F, 4566514, 267827, 0x7F7F7F, 0x4A703B, 0x547D42, calculateSkyColor(0.95F), spawnBuilder, biomeBuilder, ParticleTypes.WHITE_ASH, 0.059046667F, MOUNTAIN_MUSIC);
    }

    public static Biome volcanicPlains(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        globalOverworldGeneration(biomeBuilder);
        addFeature(biomeBuilder, GenerationStep.Decoration.FLUID_SPRINGS, BOPMiscOverworldPlacements.SPRING_LAVA_VOLCANO);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_VOLCANIC_PLAINS);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPMiscOverworldPlacements.MOSSY_BLACK_SAND_SPLATTER);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_SPROUTS_10);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_12);

        return biomeWithColorOverrides(false, 0.95F, 0.3F, 4566514, 267827, 0x4A703B, 0x547D42, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
    }

    public static Biome wasteland(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.HUSK, 80, 4, 4));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);

        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_WASTELAND);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_WASTELAND);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.WASTELAND_GRASS_2);

        return biomeWithColorOverridesAndParticles(false, 2.0F, 0.0F, 0x433721, 0x0C0C03, 0xDBDDC1, 0xAD9364, 0xB5A76C, 0x70ADEF, spawnBuilder, biomeBuilder, ParticleTypes.MYCELIUM, 0.00357F, DESERT_MUSIC);
    }

    public static Biome wastelandSteppe(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);

        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_DEAD_GRASS);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_DESERT_GRASS_EXTRA);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.WASTELAND_GRASS_2);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_6);

        return biomeWithColorOverrides(false, 2.0F, 0.0F, 0x405682, 0x08081B, 0xCDDADF, 0xC9B986, 0xC7C38F, 0x6FAEF6, spawnBuilder, biomeBuilder, DESERT_MUSIC);
    }

    public static Biome wetland(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.PIG, 10, 4, 4));
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.FOX, 8, 2, 4));
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.FROG, 10, 2, 5));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addSwampClayDisk(biomeBuilder);
        addFeature(biomeBuilder, GenerationStep.Decoration.UNDERGROUND_ORES, BOPMiscOverworldPlacements.DISK_MUD);
        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(biomeBuilder);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, AquaticPlacements.SEAGRASS_SWAMP);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_WETLAND);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_WETLAND);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_CATTAIL_EXTRA);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_FERN_8);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.LILY_PAD_10);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TOADSTOOL_NORMAL);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_REED_5);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_12);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_WATERGRASS_10);

        return biomeWithColorOverrides(true, 0.6F, 0.7F, 0x272179, 0x0C031B, 12638463, 0x5A935F, 0x4F9657, calculateSkyColor(0.6F), spawnBuilder, biomeBuilder, SWAMP_MUSIC);
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
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_PUMPKIN);
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

        return biomeWithColorOverrides(true, 0.8F, 0.5F, 0x9CC439, 0x85B408, spawnBuilder, biomeBuilder, FOREST_MUSIC);
    }
}
