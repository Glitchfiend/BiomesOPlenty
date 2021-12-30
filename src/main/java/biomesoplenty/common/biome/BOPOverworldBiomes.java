/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.biome;

import biomesoplenty.common.worldgen.carver.BOPConfiguredCarvers;
import biomesoplenty.common.worldgen.placement.BOPCavePlacements;
import biomesoplenty.common.worldgen.placement.BOPMiscOverworldPlacements;
import biomesoplenty.common.worldgen.placement.BOPVegetationPlacements;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.Carvers;
import net.minecraft.data.worldgen.placement.AquaticPlacements;
import net.minecraft.data.worldgen.placement.MiscOverworldPlacements;
import net.minecraft.data.worldgen.placement.OrePlacements;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.Musics;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;

import javax.annotation.Nullable;

public class BOPOverworldBiomes
{
    @Nullable
    private static final Music NORMAL_MUSIC = null;

    protected static int calculateSkyColor(float color)
    {
        float $$1 = color / 3.0F;
        $$1 = Mth.clamp($$1, -1.0F, 1.0F);
        return Mth.hsvToRgb(0.62222224F - $$1 * 0.05F, 0.5F + $$1 * 0.1F, 1.0F);
    }

    private static Biome biome(Biome.Precipitation precipitation, Biome.BiomeCategory category, float temperature, float downfall, MobSpawnSettings.Builder spawnBuilder, BiomeGenerationSettings.Builder biomeBuilder, @Nullable Music music)
    {
        return biome(precipitation, category, temperature, downfall, 4159204, 329011, spawnBuilder, biomeBuilder, music);
    }

    private static Biome biomeWithColorOverrides(Biome.Precipitation precipitation, Biome.BiomeCategory category, float temperature, float downfall, int grassColor, int foliageColor, MobSpawnSettings.Builder spawnBuilder, BiomeGenerationSettings.Builder biomeBuilder, @Nullable Music music)
    {
        return biomeWithColorOverrides(precipitation, category, temperature, downfall, 4159204, 329011, grassColor, foliageColor, spawnBuilder, biomeBuilder, music);
    }

    private static Biome biome(Biome.Precipitation precipitation, Biome.BiomeCategory category, float temperature, float downfall, int waterColor, int waterFogColor, MobSpawnSettings.Builder spawnBuilder, BiomeGenerationSettings.Builder biomeBuilder, @Nullable Music music)
    {
        return (new Biome.BiomeBuilder()).precipitation(precipitation).biomeCategory(category).temperature(temperature).downfall(downfall).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(waterColor).waterFogColor(waterFogColor).fogColor(12638463).skyColor(calculateSkyColor(temperature)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).backgroundMusic(music).build()).mobSpawnSettings(spawnBuilder.build()).generationSettings(biomeBuilder.build()).build();
    }

    private static Biome biome(Biome.Precipitation precipitation, Biome.BiomeCategory category, float temperature, float downfall, int waterColor, int waterFogColor, int fogColor, int skyColor, MobSpawnSettings.Builder spawnBuilder, BiomeGenerationSettings.Builder biomeBuilder, @Nullable Music music)
    {
        return (new Biome.BiomeBuilder()).precipitation(precipitation).biomeCategory(category).temperature(temperature).downfall(downfall).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(waterColor).waterFogColor(waterFogColor).fogColor(fogColor).skyColor(skyColor).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).backgroundMusic(music).build()).mobSpawnSettings(spawnBuilder.build()).generationSettings(biomeBuilder.build()).build();
    }

    private static Biome biomeWithColorOverrides(Biome.Precipitation precipitation, Biome.BiomeCategory category, float temperature, float downfall, int waterColor, int waterFogColor, int grassColor, int foliageColor, MobSpawnSettings.Builder spawnBuilder, BiomeGenerationSettings.Builder biomeBuilder, @Nullable Music music)
    {
        return (new Biome.BiomeBuilder()).precipitation(precipitation).biomeCategory(category).temperature(temperature).downfall(downfall).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(waterColor).waterFogColor(waterFogColor).fogColor(12638463).grassColorOverride(grassColor).foliageColorOverride(foliageColor).skyColor(calculateSkyColor(temperature)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).backgroundMusic(music).build()).mobSpawnSettings(spawnBuilder.build()).generationSettings(biomeBuilder.build()).build();
    }

    private static Biome biomeWithColorOverrides(Biome.Precipitation precipitation, Biome.BiomeCategory category, float temperature, float downfall, int waterColor, int waterFogColor, int fogColor, int grassColor, int foliageColor, int skyColor, MobSpawnSettings.Builder spawnBuilder, BiomeGenerationSettings.Builder biomeBuilder, @Nullable Music music)
    {
        return (new Biome.BiomeBuilder()).precipitation(precipitation).biomeCategory(category).temperature(temperature).downfall(downfall).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(waterColor).waterFogColor(waterFogColor).fogColor(fogColor).grassColorOverride(grassColor).foliageColorOverride(foliageColor).skyColor(skyColor).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).backgroundMusic(music).build()).mobSpawnSettings(spawnBuilder.build()).generationSettings(biomeBuilder.build()).build();
    }

    private static Biome biomeWithColorOverridesAndParticles(Biome.Precipitation precipitation, Biome.BiomeCategory category, float temperature, float downfall, int waterColor, int waterFogColor, int fogColor, int grassColor, int foliageColor, int skyColor, MobSpawnSettings.Builder spawnBuilder, BiomeGenerationSettings.Builder biomeBuilder, ParticleOptions particleOptions, float particleProbability, @Nullable Music music)
    {
        return (new Biome.BiomeBuilder()).precipitation(precipitation).biomeCategory(category).temperature(temperature).downfall(downfall).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(waterColor).waterFogColor(waterFogColor).fogColor(fogColor).grassColorOverride(grassColor).foliageColorOverride(foliageColor).skyColor(skyColor).ambientParticle(new AmbientParticleSettings(particleOptions, particleProbability)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).backgroundMusic(music).build()).mobSpawnSettings(spawnBuilder.build()).generationSettings(biomeBuilder.build()).build();
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

    public static Biome bambooGrove()
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.PANDA, 2, 1, 2));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder();
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);

        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_SUGAR_CANE);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AquaticPlacements.SEAGRASS_COLD);

        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_BAMBOO_GROVE);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_CHERRY_BLOSSOM_GROVE);

        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.CLOVER_NORMAL);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.LILAC_NORMAL);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.LILY_PAD_5);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.SHORT_BAMBOO);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_12);

        return biomeWithColorOverrides(Biome.Precipitation.RAIN, Biome.BiomeCategory.FOREST, 0.6F, 0.9F, 0x85CE71, 0x63BF66, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
    }

    public static Biome bayou()
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.PIG, 10, 4, 4));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder();
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addSwampClayDisk(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, BOPMiscOverworldPlacements.DISK_MUD);
        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_SUGAR_CANE);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AquaticPlacements.SEAGRASS_SWAMP);


        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_BAYOU);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_CATTAIL_NORMAL);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_FERN_8);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_LARGE_FERN_NORMAL);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.LILY_PAD_10);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_12);

        return biomeWithColorOverrides(Biome.Precipitation.RAIN, Biome.BiomeCategory.SWAMP, 0.95F, 0.9F, 0x62AF84, 0x0C211C, 12638463, 0x6FAA50, 0x8BDB67, calculateSkyColor(0.95F), spawnBuilder, biomeBuilder, NORMAL_MUSIC);
    }

    public static Biome bog()
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder();
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addSwampClayDisk(biomeBuilder);
        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        BiomeDefaultFeatures.addCommonBerryBushes(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_BOG);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_BUSH_10);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_FERN_4);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_REED_10);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_6);

        return biomeWithColorOverrides(Biome.Precipitation.RAIN, Biome.BiomeCategory.TAIGA, 0.2F, 0.5F, 0xA89557, 0xC67F5B, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
    }

    public static Biome borealForest()
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 5, 4, 4));
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.FOX, 8, 2, 4));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder();
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        BiomeDefaultFeatures.addDefaultFlowers(biomeBuilder);
        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_PUMPKIN);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_BOREAL_FOREST);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_FERN_4);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_LARGE_FERN_NORMAL);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TOADSTOOL_EXTRA);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_REED_10);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_6);

        return biomeWithColorOverrides(Biome.Precipitation.RAIN, Biome.BiomeCategory.FOREST, 0.4F, 0.8F, 0xDDAB4A, 0xD1C24A, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
    }

    public static Biome cherryBlossomGrove()
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        BiomeDefaultFeatures.farmAnimals(spawnBuilder);

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder();
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);

        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_SUGAR_CANE);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AquaticPlacements.SEAGRASS_COLD);

        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_CHERRY_BLOSSOM_GROVE);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_CHERRY_BLOSSOM_GROVE);

        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.CLOVER_NORMAL);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.LILAC_EXTRA);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PEONY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_12);

        return biomeWithColorOverrides(Biome.Precipitation.RAIN, Biome.BiomeCategory.FOREST, 0.6F, 0.9F, 0x85CE71, 0x63BF66, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
    }

    public static Biome cloverPatch()
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.plainsSpawns(spawnBuilder);

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder();
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_SUGAR_CANE_DESERT);

        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_CLOVER_PATCH);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.CLOVER_EXTRA);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_6);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.HUGE_CLOVER);

        return biomeWithColorOverrides(Biome.Precipitation.RAIN, Biome.BiomeCategory.PLAINS, 0.6F, 0.7F, 0x88C57F, 0x6AB66F, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
    }

    public static Biome coldDesert()
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.POLAR_BEAR, 1, 1, 2));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder();
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.WASTELAND_GRASS_1);

        return biomeWithColorOverrides(Biome.Precipitation.NONE, Biome.BiomeCategory.ICY, 0.25F, 0.0F, 0xAD9364, 0xB5A76C, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
    }

    public static Biome coniferousForest(boolean isSnowy)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        if (!isSnowy) BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 5, 4, 4));
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.RABBIT, 4, 2, 3));
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.FOX, 8, 2, 4));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder();
        globalOverworldGenerationNoLavaLakes(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_PUMPKIN);
        BiomeDefaultFeatures.addRareBerryBushes(biomeBuilder);

        if (isSnowy)
        {
            biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_SNOWY_CONIFEROUS_FOREST);
            biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_SNOWY);
        }
        else
        {
            biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_CONIFEROUS_FOREST);
            biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_CONIFEROUS_FOREST);
        }

        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_FERN_2);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TOADSTOOL_NORMAL);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_12);

        return biome(Biome.Precipitation.RAIN, Biome.BiomeCategory.TAIGA, isSnowy ? -0.25F : 0.45F, 0.5F, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
    }

    public static Biome crag()
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.GOAT, 10, 4, 6));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder();
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPMiscOverworldPlacements.CRAG_SPLATTER);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_12);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_TALL_GRASS_24);

        return biome(Biome.Precipitation.RAIN, Biome.BiomeCategory.EXTREME_HILLS, 0.6F, 0.6F, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
    }

    public static Biome deadForest()
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder();
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_PUMPKIN);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_DEAD_FOREST);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_3);

        return biomeWithColorOverrides(Biome.Precipitation.RAIN, Biome.BiomeCategory.TAIGA, 0.2F, 0.3F, 0xBAAD64, 0xB7B763, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
    }

    public static Biome dryland()
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.CHICKEN, 10, 4, 4));
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.COW, 8, 4, 4));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder();
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);

        // Vegetation
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_DRYLAND);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_BUSH_1);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_6);

        return biomeWithColorOverrides(Biome.Precipitation.NONE, Biome.BiomeCategory.SAVANNA, 0.85F, 0.05F, 4159204, 329011, 12638463, 0xE5DFA9, 0xDAE0B3, 0x9E9DFF, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
    }

    public static Biome duneBeach()
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder();
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, MiscOverworldPlacements.DISK_SAND);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, MiscOverworldPlacements.DISK_CLAY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_DUNE_GRASS);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_SEA_OATS);

        return biome(Biome.Precipitation.RAIN, Biome.BiomeCategory.BEACH, 0.7F, 0.4F, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
    }

    public static Biome field(boolean forest)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.FOX, 8, 2, 4));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder();
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_PUMPKIN);
        BiomeDefaultFeatures.addRareBerryBushes(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_FIELD_1);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.LILAC_NORMAL);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.ROSE_BUSH_NORMAL);

        // Vegetation
        if (forest)
        {
            biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_FIELD_FOREST);
            biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_SPROUTS_15);
        }
        else
        {
            biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_FIELD);
            biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_FIELD_2);
            biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_SPROUTS_10);
            biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.SUNFLOWER);
        }

        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_6);

        return biomeWithColorOverrides(Biome.Precipitation.RAIN, Biome.BiomeCategory.TAIGA, 0.4F, 0.7F, 0x63B26D, 0x63B26D, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
    }

    public static Biome firClearing(boolean snowy)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 5, 4, 4));
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.RABBIT, 4, 2, 3));
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.FOX, 8, 2, 4));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder();
        globalOverworldGenerationNoLavaLakes(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_PUMPKIN);
        BiomeDefaultFeatures.addRareBerryBushes(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_FIR_CLEARING);

        if (!snowy)
        {
            biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_CONIFEROUS_FOREST);
            biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_FERN_4);
            biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TOADSTOOL_NORMAL);
            biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_12);
            return biome(Biome.Precipitation.RAIN, Biome.BiomeCategory.TAIGA, 0.45F, 0.5F, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
        }
        else
        {
            biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_SNOWY);
            biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_FERN_2);
            biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_3);
            return biome(Biome.Precipitation.SNOW, Biome.BiomeCategory.ICY, -0.25F, 0.5F, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
        }
    }

    public static Biome floodplain()
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder();
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AquaticPlacements.SEAGRASS_SWAMP);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_RAINFOREST_FLOODPLAIN);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_RAINFOREST);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.LILY_PAD_15);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_12);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_TALL_GRASS_24);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_WATERGRASS_250);

        return biomeWithColorOverrides(Biome.Precipitation.RAIN, Biome.BiomeCategory.JUNGLE, 1.2F, 2.0F, 0x3FAABE, 0x041A2C, 0x7FD43D, 0x5BCD25, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
    }

    public static Biome fungalJungle()
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        BiomeDefaultFeatures.farmAnimals(spawnBuilder);

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder();
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);

        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_SUGAR_CANE);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AquaticPlacements.SEAGRASS_SWAMP);

        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_FUNGAL_JUNGLE);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.HUGE_GLOWSHROOM);

        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.SMALL_GLOWSHROOM);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GLOWSHROOM);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.MYCELIUM_SPLATTER);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_FERN_8);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_LARGE_FERN_NORMAL);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_12);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_TALL_GRASS_12);

        return biomeWithColorOverrides(Biome.Precipitation.SNOW, Biome.BiomeCategory.ICY, 0.9F, 0.9F, 4445678, 270131, 12638463, 0x4AA2F9, 0x4ADCF9, calculateSkyColor(0.9F), spawnBuilder, biomeBuilder, NORMAL_MUSIC);
    }

    public static Biome glowingGrotto()
    {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);

        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder();
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addPlainGrass(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder, true);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        BiomeDefaultFeatures.addPlainVegetation(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPCavePlacements.GLOWING_GROTTO_VEGETATION);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPCavePlacements.GLOWING_GROTTO_MUD);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPCavePlacements.GLOWWORM_SILK_STRANDS);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPCavePlacements.GIANT_GLOWSHROOM_CAVE);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPCavePlacements.HUGE_GLOWSHROOM_CAVE);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPCavePlacements.MEDIUM_GLOWSHROOM_CAVE);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPCavePlacements.SMALL_GLOWSHROOM_CAVE);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPCavePlacements.EXTRA_GLOW_LICHEN);

        return biome(Biome.Precipitation.RAIN, Biome.BiomeCategory.UNDERGROUND, 0.5F, 0.5F, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
    }

    public static Biome grassland()
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.plainsSpawns(spawnBuilder);

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder();
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_SUGAR_CANE_DESERT);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.CLOVER_NORMAL);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_6);

        return biomeWithColorOverrides(Biome.Precipitation.RAIN, Biome.BiomeCategory.PLAINS, 0.6F, 0.7F, 0x88C57F, 0x6AB66F, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
    }

    public static Biome highland(boolean moor)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.GOAT, 10, 4, 6));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder();
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        if (moor) BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        BiomeDefaultFeatures.addExtraEmeralds(biomeBuilder);
        BiomeDefaultFeatures.addInfestedStone(biomeBuilder);

        if (moor)
        {
            biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AquaticPlacements.SEAGRASS_RIVER);
            biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_MOOR);
            biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_VIOLET);
            biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.SCRUB_NORMAL);
            biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_TALL_GRASS_6);
            biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_12);
        }
        else
        {
            biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_12);
            biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_TALL_GRASS_24);
        }

        return biome(Biome.Precipitation.RAIN, Biome.BiomeCategory.EXTREME_HILLS, 0.6F, 0.6F, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
    }

    public static Biome jadeCliffs()
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        BiomeDefaultFeatures.farmAnimals(spawnBuilder);

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder();
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_SUGAR_CANE);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_JADE_CLIFFS);

        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_6);

        return biomeWithColorOverrides(Biome.Precipitation.RAIN, Biome.BiomeCategory.EXTREME_HILLS, 0.8F, 0.85F, 4159204, 329011, 0xBBD1D5, 0x7CA568, 0x8BB76E, 0xB7CCAD, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
    }

    public static Biome lavenderField(boolean forest)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        BiomeDefaultFeatures.farmAnimals(spawnBuilder);

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder();
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_SUGAR_CANE);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_LAVENDER);

        if (forest)
        {
            biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_LAVENDER_FOREST);
            biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_24);
        }
        else
        {
            biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_LAVENDER_FIELD);
            biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_12);
        }

        return biomeWithColorOverrides(Biome.Precipitation.RAIN, Biome.BiomeCategory.PLAINS, 0.8F, 0.7F, 0xA1C36D, 0xA1C36D, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
    }

    public static Biome lushDesert()
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.RABBIT, 4, 2, 3));
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.HUSK, 80, 4, 4));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder();
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.FLUID_SPRINGS, BOPMiscOverworldPlacements.SPRING_WATER_EXTRA);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_DEAD_BUSH_2);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_SUGAR_CANE);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_LUSH_DESERT);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_LUSH_DESERT);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_DEAD_GRASS);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_DESERT_GRASS);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_DUNE_GRASS);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_SPROUTS_5);

        return biomeWithColorOverrides(Biome.Precipitation.RAIN, Biome.BiomeCategory.DESERT, 0.9F, 0.5F, 4566514, 267827, 0xEFE182, 0xD3D156, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
    }

    public static Biome lushSavanna()
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.RABBIT, 4, 2, 3));
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.DONKEY, 1, 1, 3));
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.HUSK, 80, 4, 4));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder();
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.FLUID_SPRINGS, BOPMiscOverworldPlacements.SPRING_WATER_EXTRA);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_SUGAR_CANE);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_POPPY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.ROSE_BUSH_EXTRA);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_SPROUTS_15);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_24);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_TALL_GRASS_24);

        return biomeWithColorOverrides(Biome.Precipitation.RAIN, Biome.BiomeCategory.DESERT, 0.9F, 0.5F, 4566514, 267827, 0xEFE182, 0xD3D156, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
    }

    public static Biome mapleWoods(boolean snowy)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 5, 4, 4));
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.RABBIT, 4, 2, 3));
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.FOX, 8, 2, 4));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder();
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_PUMPKIN);
        BiomeDefaultFeatures.addRareBerryBushes(biomeBuilder);

        if (!snowy)
        {
            biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_MAPLE_FOREST);
            biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_SNOWY);
            biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_FERN_2);
            biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_6);
            return biome(Biome.Precipitation.RAIN, Biome.BiomeCategory.TAIGA, 0.25F, 0.5F, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
        }
        else
        {
            biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_SNOWY_MAPLE_FOREST);
            biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_SNOWY);
            biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_FERN_2);
            biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_3);
            return biome(Biome.Precipitation.SNOW, Biome.BiomeCategory.ICY, -0.25F, 0.5F, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
        }
    }

    public static Biome marsh()
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder();
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AquaticPlacements.SEAGRASS_SWAMP);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_TALL_GRASS_250);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_REED_10);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_24);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_WATERGRASS_50);

        return biome(Biome.Precipitation.RAIN, Biome.BiomeCategory.SWAMP, 0.65F, 0.7F, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
    }

    public static Biome mediterraneanForest()
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        BiomeDefaultFeatures.farmAnimals(spawnBuilder);

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder();
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_PUMPKIN);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_MEDITERRANEAN_FOREST);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_DEFAULT_EXTENDED_6);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_BUSH_4);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PEONY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_3);

        return biome(Biome.Precipitation.RAIN, Biome.BiomeCategory.FOREST, 0.8F, 0.275F, 4566514, 267827, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
    }

    public static Biome mediterraneanLakes()
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        BiomeDefaultFeatures.farmAnimals(spawnBuilder);

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder();
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, BOPMiscOverworldPlacements.DISK_CALCITE);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_PUMPKIN);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_BUSH_4);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_3);

        return biome(Biome.Precipitation.RAIN, Biome.BiomeCategory.FOREST, 0.8F, 0.275F, 4566514, 267827, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
    }

    public static Biome muskeg()
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder();
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, MiscOverworldPlacements.DISK_CLAY);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, MiscOverworldPlacements.DISK_GRAVEL);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, BOPMiscOverworldPlacements.DISK_MUD);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_MUSKEG);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_1);

        return biomeWithColorOverrides(Biome.Precipitation.SNOW, Biome.BiomeCategory.ICY, -0.25F, 0.6F, 0x94966E, 0x8D9B6B, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
    }

    public static Biome mysticGrove()
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.caveSpawns(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.RABBIT, 4, 2, 3));
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.FOX, 8, 2, 4));
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.ZOMBIE_VILLAGER, 5, 1, 1));
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.SLIME, 100, 4, 4));
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.WITCH, 20, 1, 1));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder();
        globalOverworldGenerationNoLavaLakes(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, MiscOverworldPlacements.DISK_CLAY);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, BOPMiscOverworldPlacements.DISK_WHITE_SAND);
        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_MYSTIC_GROVE);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_MYSTIC_GROVE);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_BLUE_HYDRANGEA);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.HUGE_RED_MUSHROOM_EXTRA);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.SMALL_RED_MUSHROOM);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.CLOVER_NORMAL);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.LILAC_NORMAL);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_SPROUTS_15);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_12);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_WATERGRASS_10);

        return biomeWithColorOverridesAndParticles(Biome.Precipitation.RAIN, Biome.BiomeCategory.FOREST, 0.7F, 0.8F, 0x9C3FE4, 0x2E0533, 0xFFC9DA, 0x69CFDB, 0x70E0B5, 0xAAEFFF, spawnBuilder, biomeBuilder, ParticleTypes.END_ROD, 0.00011532552F, NORMAL_MUSIC);
    }

    public static Biome oldGrowthDeadForest()
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder();
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_PUMPKIN);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_OLD_GROWTH_DEAD_FOREST);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_6);

        return biomeWithColorOverrides(Biome.Precipitation.RAIN, Biome.BiomeCategory.TAIGA, 0.3F, 0.3F, 0xBAAD64, 0xB7B763, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
    }

    public static Biome ominousWoods()
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
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder();
        globalOverworldGenerationNoLavaLakes(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, BOPMiscOverworldPlacements.DISK_BLACK_SAND);
        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_PUMPKIN);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_OMINOUS_WOODS);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_OMINOUS_WOODS);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.BRAMBLE);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_DEAD_GRASS);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_6);

        return biomeWithColorOverrides(Biome.Precipitation.RAIN, Biome.BiomeCategory.TAIGA, 0.6F, 0.6F, 0x312346, 0x0A030C, 0x7881A5, 0x4C4A70, 0x6B487C, 0x84A1CC, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
    }

    public static Biome orchard()
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        BiomeDefaultFeatures.farmAnimals(spawnBuilder);

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder();
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_SUGAR_CANE);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_ORCHARD);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_DEFAULT_EXTENDED_15);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.ROSE_BUSH_NORMAL);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_SPROUTS_5);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_12);

        return biomeWithColorOverrides(Biome.Precipitation.RAIN, Biome.BiomeCategory.FOREST, 0.8F, 0.4F, 0xA9DB69, 0xC9F75D, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
    }

    public static Biome originValley()
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
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder();
        biomeBuilder.addCarver(GenerationStep.Carving.AIR, BOPConfiguredCarvers.ORIGIN_CAVE);
        BiomeDefaultFeatures.addDefaultMonsterRoom(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSprings(biomeBuilder);
        BiomeDefaultFeatures.addSurfaceFreezing(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_DIRT);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_GRAVEL);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_COAL_UPPER);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_COAL_LOWER);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_IRON_UPPER);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_IRON_MIDDLE);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_IRON_SMALL);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_GOLD);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_REDSTONE);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_DIAMOND);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_SUGAR_CANE);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_ORIGIN_VALLEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_ORIGIN_VALLEY);

        return biomeWithColorOverrides(Biome.Precipitation.RAIN, Biome.BiomeCategory.NONE, 0.6F, 0.6F, 0x0E31FF, 0x070059, 0xB0CFFF, 0x9AFF5F, 0x3AFF00, 0x8CBDFF, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
    }

    public static Biome pasture()
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.plainsSpawns(spawnBuilder);

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder();
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_BARLEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_24);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_TALL_GRASS_64);

        return biomeWithColorOverrides(Biome.Precipitation.RAIN, Biome.BiomeCategory.PLAINS, 0.8F, 0.3F, 0xE4EA77, 0xC7E672, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
    }

    public static Biome prairie()
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.plainsSpawns(spawnBuilder);

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder();
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_PRAIRIE);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_CONIFEROUS_FOREST);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.GOLDENROD_EXTRA);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_24);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_TALL_GRASS_24);

        return biomeWithColorOverrides(Biome.Precipitation.RAIN, Biome.BiomeCategory.PLAINS, 0.8F, 0.3F, 0xE4EA77, 0xC7E672, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
    }

    public static Biome pumpkinPatch()
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 5, 4, 4));
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.FOX, 8, 2, 4));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder();
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_SEASONAL_PUMPKIN_PATCH);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.BIG_PUMPKIN);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PUMPKIN_PATCH);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TOADSTOOL_NORMAL);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_SPROUTS_10);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_REED_5);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_6);

        return biomeWithColorOverrides(Biome.Precipitation.RAIN, Biome.BiomeCategory.FOREST, 0.4F, 0.8F, 0xDDBF4A, 0xCED14A, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
    }

    public static Biome rainbowHills()
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.caveSpawns(spawnBuilder);
        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.FOX, 8, 2, 4));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder();
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_RAINBOW_HILLS);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_6);

        return biomeWithColorOverrides(Biome.Precipitation.SNOW, Biome.BiomeCategory.FOREST, -0.25F, 0.5F, 0xDBFCFC, 0xA5F7F7, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
    }

    public static Biome rainforest()
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.CHICKEN, 10, 4, 4));
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.PARROT, 40, 1, 2));
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.OCELOT, 2, 1, 1));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder();
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, BOPMiscOverworldPlacements.DISK_ORANGE_SAND);
        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_SUGAR_CANE);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AquaticPlacements.SEAGRASS_SWAMP);
        BiomeDefaultFeatures.addJungleMelons(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_RAINFOREST);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_RAINFOREST);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.BIG_DRIPLEAF);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_FERN_16);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_LARGE_FERN_EXTRA);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.LILY_PAD_10);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.SMALL_DRIPLEAF);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_SPROUTS_5);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_12);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_TALL_GRASS_12);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_WATERGRASS_10);

        return biomeWithColorOverrides(Biome.Precipitation.RAIN, Biome.BiomeCategory.JUNGLE, 1.2F, 2.0F, 0x3FDF99, 0x042F26, 0xA7E140, 0x88E140, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
    }

    public static Biome rainforestCliffs()
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.PARROT, 40, 1, 2));
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.OCELOT, 2, 1, 1));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder();
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, BOPMiscOverworldPlacements.DISK_ORANGE_SAND);
        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        BiomeDefaultFeatures.addExtraEmeralds(biomeBuilder);
        BiomeDefaultFeatures.addInfestedStone(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_SUGAR_CANE);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_RAINFOREST_CLIFFS);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_RAINFOREST);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_FERN_16);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_LARGE_FERN_NORMAL);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.RAINFOREST_CLIFFS_VINES);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_SPROUTS_15);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_12);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_TALL_GRASS_24);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_WATERGRASS_10);

        return biomeWithColorOverrides(Biome.Precipitation.RAIN, Biome.BiomeCategory.JUNGLE, 1.2F, 2.0F, 0x3FDF99, 0x042F26, 0xA7E140, 0x88E140, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
    }

    public static Biome redwoodForest()
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 5, 4, 4));
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.RABBIT, 4, 2, 3));
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.FOX, 8, 2, 4));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder();
        globalOverworldGenerationNoLavaLakes(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);

        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_REDWOOD_FOREST);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_FERN_4);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_LARGE_FERN_EXTRA);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.MOSS_SPLATTER);

        return biomeWithColorOverrides(Biome.Precipitation.RAIN, Biome.BiomeCategory.TAIGA, 0.8F, 0.6F, 0xB5D55C, 0x8EBF42, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
    }

    public static Biome rockyShrubland()
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.LLAMA, 5, 4, 6));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder();
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_ROCKY_SHRUBLAND);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_SHRUBLAND);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_BUSH_4);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_6);

        return biome(Biome.Precipitation.RAIN, Biome.BiomeCategory.PLAINS, 0.6F, 0.05F, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
    }

    public static Biome scrubland(boolean wooded)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.plainsSpawns(spawnBuilder);

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder();
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_SCRUBLAND);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_DESERT_GRASS);

        if (wooded)
        {
            biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_WOODED_SCRUBLAND);
            biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.SCRUB_EXTRA);
            biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_TALL_GRASS_6);
            biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_12);
        }
        else
        {
            biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.SCRUB_NORMAL);
            biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_6);
        }

        return biome(Biome.Precipitation.NONE, Biome.BiomeCategory.SAVANNA, 1.1F, 0.15F, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
    }

    public static Biome shrubland()
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.plainsSpawns(spawnBuilder);

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder();
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_SHRUBLAND);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_SHRUBLAND);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_BUSH_4);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.LILAC_NORMAL);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_REED_10);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_6);

        return biome(Biome.Precipitation.RAIN, Biome.BiomeCategory.PLAINS, 0.6F, 0.05F, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
    }

    public static Biome seasonalForest()
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 5, 4, 4));
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.FOX, 8, 2, 4));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder();
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        BiomeDefaultFeatures.addDefaultFlowers(biomeBuilder);
        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_PUMPKIN);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_SEASONAL_FOREST);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TOADSTOOL_EXTRA);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_REED_5);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_12);

        return biomeWithColorOverrides(Biome.Precipitation.RAIN, Biome.BiomeCategory.FOREST, 0.4F, 0.8F, 0xDD9A4A, 0xD1B24A, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
    }

    public static Biome spiderNest()
    {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);

        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder();
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addPlainGrass(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder, true);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        BiomeDefaultFeatures.addPlainVegetation(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPCavePlacements.HANGING_COBWEBS);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPCavePlacements.CORNER_COBWEBS);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPCavePlacements.WEBBING);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPCavePlacements.SPIDER_EGGS);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPCavePlacements.STRINGY_COBWEB);

        return biome(Biome.Precipitation.RAIN, Biome.BiomeCategory.UNDERGROUND, 0.5F, 0.5F, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
    }

    public static Biome tropics()
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
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder();
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, BOPMiscOverworldPlacements.DISK_WHITE_SAND_EXTRA);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_SUGAR_CANE);
        BiomeDefaultFeatures.addJungleMelons(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_TROPICS);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_TROPICS);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_BLUE_HYDRANGEA);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.ROSE_BUSH_NORMAL);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.SHORT_BAMBOO);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_12);

        return biome(Biome.Precipitation.RAIN, Biome.BiomeCategory.NONE, 0.95F, 1.0F, 4445678, 270131, 0xB2EDFF, 0x66BCFF, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
    }

    public static Biome tundra()
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 5, 4, 4));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder();
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, BOPMiscOverworldPlacements.DISK_GRAVEL_EXTRA);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_TUNDRA);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_FERN_4);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_REED_5);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_6);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.SCATTERED_ROCKS);

        return biomeWithColorOverrides(Biome.Precipitation.RAIN, Biome.BiomeCategory.TAIGA, 0.2F, 0.5F, 0xC08359, 0xC5975C, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
    }

    public static Biome volcano()
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder();
        globalOverworldGeneration(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.LAKES, BOPMiscOverworldPlacements.LAKE_LAVA_SURFACE_EXTRA);
        biomeBuilder.addFeature(GenerationStep.Decoration.FLUID_SPRINGS, BOPMiscOverworldPlacements.SPRING_LAVA_VOLCANO);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, MiscOverworldPlacements.DISK_GRAVEL);
        BiomeDefaultFeatures.addExtraEmeralds(biomeBuilder);
        BiomeDefaultFeatures.addInfestedStone(biomeBuilder);

        return biomeWithColorOverridesAndParticles(Biome.Precipitation.NONE, Biome.BiomeCategory.NONE, 0.95F, 0.3F, 4566514, 267827, 0x7F7F7F, 0x4A703B, 0x547D42, calculateSkyColor(0.95F), spawnBuilder, biomeBuilder, ParticleTypes.WHITE_ASH, 0.059046667F, NORMAL_MUSIC);
    }

    public static Biome volcanicPlains()
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder();
        globalOverworldGeneration(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.FLUID_SPRINGS, BOPMiscOverworldPlacements.SPRING_LAVA_VOLCANO);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_VOLCANIC_PLAINS);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPMiscOverworldPlacements.GRASS_SPLATTER);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_SPROUTS_10);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_12);

        return biomeWithColorOverrides(Biome.Precipitation.NONE, Biome.BiomeCategory.NONE, 0.95F, 0.3F, 4566514, 267827, 0x4A703B, 0x547D42, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
    }

    public static Biome wasteland(boolean wooded)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.HUSK, 80, 4, 4));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder();
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);

        if (wooded)
        {
            biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_WASTELAND);
            biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_WASTELAND);
        }

        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.WASTELAND_GRASS_2);

        return biomeWithColorOverridesAndParticles(Biome.Precipitation.NONE, Biome.BiomeCategory.DESERT, 2.0F, 0.0F, 0x433721, 0x0C0C03, 0xDBDDC1, 0xAD9364, 0xB5A76C, 0x70ADEF, spawnBuilder, biomeBuilder, ParticleTypes.MYCELIUM, 0.00357F, NORMAL_MUSIC);
    }

    public static Biome wetland()
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.PIG, 10, 4, 4));
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.FOX, 8, 2, 4));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder();
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addSwampClayDisk(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, BOPMiscOverworldPlacements.DISK_MUD);
        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AquaticPlacements.SEAGRASS_SWAMP);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_WETLAND);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_WETLAND);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_CATTAIL_EXTRA);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_FERN_16);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.LILY_PAD_10);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TOADSTOOL_NORMAL);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_REED_5);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_12);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_WATERGRASS_10);

        return biomeWithColorOverrides(Biome.Precipitation.RAIN, Biome.BiomeCategory.SWAMP, 0.6F, 0.7F, 0x272179, 0x0C031B, 12638463, 0x5A935F, 0x4F9657, calculateSkyColor(0.6F), spawnBuilder, biomeBuilder, NORMAL_MUSIC);
    }

    public static Biome woodland(boolean dense)
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        BiomeDefaultFeatures.farmAnimals(spawnBuilder);

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder();
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_PUMPKIN);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_DEFAULT_EXTENDED_6);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.ROSE_BUSH_NORMAL);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.GOLDENROD_NORMAL);

        if (dense)
        {
            biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_DENSE_WOODLAND);
            biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.HUGE_TOADSTOOL);
            biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.SMALL_TOADSTOOL);
            biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TOADSTOOL_EXTRA);
            biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_12);
            biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_TALL_GRASS_12);
        }
        else
        {
            biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TOADSTOOL_NORMAL);
            biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_6);
            biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_TALL_GRASS_6);
            biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_WOODLAND);
        }

        return biomeWithColorOverrides(Biome.Precipitation.RAIN, Biome.BiomeCategory.FOREST, 0.8F, 0.5F, 0xA7C047, 0x92AF1A, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
    }
}
