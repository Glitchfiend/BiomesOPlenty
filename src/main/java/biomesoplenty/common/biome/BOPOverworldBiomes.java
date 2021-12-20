/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.biome;

import biomesoplenty.common.worldgen.placement.BOPMiscOverworldPlacements;
import biomesoplenty.common.worldgen.placement.BOPVegetationPlacements;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.placement.AquaticPlacements;
import net.minecraft.data.worldgen.placement.MiscOverworldPlacements;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.sounds.Music;
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

    public static Biome bambooBlossomGrove()
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.PANDA, 1, 1, 2));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder();
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);

        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_SUGAR_CANE);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AquaticPlacements.SEAGRASS_COLD);

        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_BAMBOO_BLOSSOM_GROVE);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_CHERRY_BLOSSOM_GROVE);

        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.CLOVER_3);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.LILAC_2);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.LILY_PAD_5);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.SHORT_BAMBOO_5);
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
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_CATTAIL_6);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_FERN_GRASS_8);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_LARGE_FERN_7);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.LILY_PAD_10);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_6);

        return biomeWithColorOverrides(Biome.Precipitation.RAIN, Biome.BiomeCategory.SWAMP, 0.95F, 0.9F, 0x62AF84, 0x0C211C, 12638463, 0x6FAA50, 0x8BDB67, calculateSkyColor(0.95F), spawnBuilder, biomeBuilder, NORMAL_MUSIC);
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

        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.CLOVER_3);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.LILAC_4);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PEONY_1);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_12);

        return biomeWithColorOverrides(Biome.Precipitation.RAIN, Biome.BiomeCategory.FOREST, 0.6F, 0.9F, 0x85CE71, 0x63BF66, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
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
        // TODO: Extra iron?

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
        globalOverworldGeneration(biomeBuilder);
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

        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_FERN_GRASS_4);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TOADSTOOL_NORMAL);

        return biome(Biome.Precipitation.RAIN, Biome.BiomeCategory.TAIGA, isSnowy ? -0.25F : 0.45F, 0.5F, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
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
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_BUSH_10);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_6);

        return biomeWithColorOverrides(Biome.Precipitation.NONE, Biome.BiomeCategory.SAVANNA, 0.85F, 0.05F, 4159204, 329011, 12638463, 0xE5DFA9, 0xDAE0B3, 0x9E9DFF, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
    }

    public static Biome dryBoneyard()
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.SKELETON_HORSE, 3, 1, 1));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder();
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);

        // Vegetation
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_DRY_BONEYARD);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPMiscOverworldPlacements.BONE_SPINE);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_BUSH_5);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_DEAD_GRASS_5);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_6);

        return biomeWithColorOverrides(Biome.Precipitation.NONE, Biome.BiomeCategory.SAVANNA, 0.85F, 0.05F, 4159204, 329011, 12638463, 0xE5DFA9, 0xDAE0B3, 0x9E9DFF, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
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
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.LILAC_1);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.ROSE_BUSH_1);

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
            biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.SUNFLOWER_1);
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
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_PUMPKIN);
        BiomeDefaultFeatures.addRareBerryBushes(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_FIR_CLEARING);

        if (!snowy)
        {
            biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_CONIFEROUS_FOREST);
            biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_FERN_GRASS_8);
            biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TOADSTOOL_NORMAL);
            return biome(Biome.Precipitation.RAIN, Biome.BiomeCategory.TAIGA, 0.45F, 0.5F, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
        }
        else
        {
            return biome(Biome.Precipitation.SNOW, Biome.BiomeCategory.ICY, -0.25F, 0.5F, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
        }
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
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.CLOVER_6);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_3);

        return biomeWithColorOverrides(Biome.Precipitation.RAIN, Biome.BiomeCategory.PLAINS, 0.6F, 0.7F, 0x88C57F, 0x6AB66F, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
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
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_DEAD_GRASS_2);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_DESERT_GRASS_10);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_DUNE_GRASS_128);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_SPROUTS_5);

        return biomeWithColorOverrides(Biome.Precipitation.RAIN, Biome.BiomeCategory.DESERT, 0.9F, 0.5F, 4566514, 267827, 0xEFE182, 0xD3D156, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
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
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_12);
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
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_BUSH_15);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PEONY_1);
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
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_BUSH_15);
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
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.ROSE_BUSH_1);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_SPROUTS_5);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_22);

        return biomeWithColorOverrides(Biome.Precipitation.RAIN, Biome.BiomeCategory.FOREST, 0.8F, 0.4F, 0xA9DB69, 0xC9F75D, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
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
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.GOLDENROD_10);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_24);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_TALL_GRASS_24);

        return biomeWithColorOverrides(Biome.Precipitation.RAIN, Biome.BiomeCategory.PLAINS, 0.8F, 0.3F, 0xE4EA77, 0xC7E672, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
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
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_FERN_GRASS_16);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_LARGE_FERN_25);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.LILY_PAD_10);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.SMALL_DRIPLEAF);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_SPROUTS_5);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_TALL_GRASS_12);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_WATERGRASS_10);

        // TODO: Extra copper?

        return biomeWithColorOverrides(Biome.Precipitation.RAIN, Biome.BiomeCategory.JUNGLE, 1.2F, 2.0F, 0x3FDF99, 0x042F26, 0xA7E140, 0x88E140, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
    }

    public static Biome rainforestFloodplain()
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
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.BIG_DRIPLEAF);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_FERN_GRASS_16);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_LARGE_FERN_7);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.LILY_PAD_15);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.SMALL_DRIPLEAF);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_TALL_GRASS_24);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_WATERGRASS_250);

        // TODO: Extra copper?

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
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);

        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_REDWOOD_FOREST);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_FERN_GRASS_5);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_LARGE_FERN_25);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.MOSS_SPLATTER);

        return biomeWithColorOverrides(Biome.Precipitation.RAIN, Biome.BiomeCategory.TAIGA, 0.8F, 0.6F, 0xB5D55C, 0x8EBF42, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
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
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_DESERT_GRASS_10);

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
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_BUSH_10);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.LILAC_1);
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
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_12);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_REED_5);

        return biomeWithColorOverrides(Biome.Precipitation.RAIN, Biome.BiomeCategory.FOREST, 0.4F, 0.8F, 0xDD9A4A, 0xD1B24A, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
    }

    public static Biome seasonalPumpkinPatch()
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
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_6);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_REED_5);

        return biomeWithColorOverrides(Biome.Precipitation.RAIN, Biome.BiomeCategory.FOREST, 0.4F, 0.8F, 0xDD9A4A, 0xD1B24A, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
    }

    public static Biome snowyMapleForest()
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
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_SNOWY_MAPLE_FOREST);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_SNOWY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_FERN_GRASS_4);

        return biome(Biome.Precipitation.SNOW, Biome.BiomeCategory.ICY, -0.25F, 0.5F, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
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
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_FERN_GRASS_4);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_REED_5);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.SCATTERED_ROCKS);

        // TODO: Extra coal?
        return biomeWithColorOverrides(Biome.Precipitation.RAIN, Biome.BiomeCategory.TAIGA, 0.2F, 0.5F, 0xC08359, 0xC5975C, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
    }

    public static Biome wasteland()
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.HUSK, 80, 4, 4));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder();
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_WASTELAND);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_WASTELAND);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.WASTELAND_GRASS_2);

        return biomeWithColorOverridesAndParticles(Biome.Precipitation.NONE, Biome.BiomeCategory.DESERT, 2.0F, 0.0F, 0x433721, 0x0C0C03, 0xDBDDC1, 0xAD9364, 0xB5A76C, 0x70ADEF, spawnBuilder, biomeBuilder, ParticleTypes.MYCELIUM, 0.00357F, NORMAL_MUSIC);
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
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.ROSE_BUSH_2);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.GOLDENROD_2);

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
