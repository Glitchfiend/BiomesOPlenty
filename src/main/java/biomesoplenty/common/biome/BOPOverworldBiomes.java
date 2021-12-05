/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.biome;

import biomesoplenty.common.worldgen.placement.BOPVegetationPlacements;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.placement.AquaticPlacements;
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

        return biomeWithColorOverrides(Biome.Precipitation.RAIN, Biome.BiomeCategory.FOREST, 0.6F,  0.9F, 0x85CE71, 0x63BF66, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
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

        return biomeWithColorOverrides(Biome.Precipitation.RAIN, Biome.BiomeCategory.FOREST, 0.6F,  0.9F, 0x85CE71, 0x63BF66, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
    }

    public static Biome coniferousForest()
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

        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_CONIFEROUS_FOREST);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_CONIFEROUS_FOREST);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_FERN_GRASS_4);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TOADSTOOL_NORMAL);

        return biome(Biome.Precipitation.RAIN, Biome.BiomeCategory.TAIGA, 0.45F,  0.5F,  spawnBuilder, biomeBuilder, NORMAL_MUSIC);
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
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_LARGE_FERN_2);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.MOSS_SPLATTER);

        return biomeWithColorOverrides(Biome.Precipitation.RAIN, Biome.BiomeCategory.TAIGA, 0.8F,  0.6F, 0xB5D55C, 0x8EBF42, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
    }

    public static Biome woodland()
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
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TOADSTOOL_NORMAL);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.GOLDENROD_16);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.ROSE_BUSH_16);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_6);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_TALL_GRASS_6);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_WOODLAND);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_DEFAULT_EXTENDED);

        return biomeWithColorOverrides(Biome.Precipitation.RAIN, Biome.BiomeCategory.FOREST, 0.8F, 0.5F, 0xA7C047, 0x92AF1A, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
    }

    public static Biome lavenderField()
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        BiomeDefaultFeatures.farmAnimals(spawnBuilder);

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder();
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.TREES_LAVENDER_FIELD);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.FLOWER_LAVENDER);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_GRASS_3);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_SUGAR_CANE);

        return biomeWithColorOverrides(Biome.Precipitation.RAIN, Biome.BiomeCategory.PLAINS, 0.8F, 0.7F, 0xA1C36D, 0xA1C36D, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
    }

    /* Remaining biomes:
    public static ResourceKey<Biome> bayou = register("bayou");
    public static ResourceKey<Biome> bayou_mangrove = register("bayou_mangrove");
    public static ResourceKey<Biome> burnt_forest = register("burnt_forest");
    public static ResourceKey<Biome> cherry_blossom_grove = register("cherry_blossom_grove");
    public static ResourceKey<Biome> cold_desert = register("cold_desert");
    public static ResourceKey<Biome> coniferous_lakes = register("coniferous_lakes");
    public static ResourceKey<Biome> dead_forest = register("dead_forest");
    public static ResourceKey<Biome> deep_bayou = register("deep_bayou");
    public static ResourceKey<Biome> dense_marsh = register("dense_marsh");
    public static ResourceKey<Biome> dense_woodland = register("dense_woodland");
    public static ResourceKey<Biome> dryland = register("dryland");
    public static ResourceKey<Biome> dry_boneyard = register("dry_boneyard");
    public static ResourceKey<Biome> dunes = register("dunes");
    public static ResourceKey<Biome> fir_clearing = register("fir_clearing");
    public static ResourceKey<Biome> fungal_field = register("fungal_field");
    public static ResourceKey<Biome> fungal_jungle = register("fungal_jungle");
    public static ResourceKey<Biome> golden_prairie = register("golden_prairie");
    public static ResourceKey<Biome> grassland = register("grassland");
    public static ResourceKey<Biome> grassland_clover_patch = register("grassland_clover_patch");
    public static ResourceKey<Biome> gravel_beach = register("gravel_beach");
    public static ResourceKey<Biome> grove = register("grove");
    public static ResourceKey<Biome> grove_lakes = register("grove_lakes");
    public static ResourceKey<Biome> highland = register("highland");
    public static ResourceKey<Biome> highland_crag = register("highland_crag");
    public static ResourceKey<Biome> highland_moor = register("highland_moor");
    public static ResourceKey<Biome> jade_cliffs = register("jade_cliffs");
    public static ResourceKey<Biome> lavender_forest = register("lavender_forest");
    public static ResourceKey<Biome> lush_desert = register("lush_desert");
    public static ResourceKey<Biome> lush_savanna = register("lush_savanna");
    public static ResourceKey<Biome> marsh = register("marsh");
    public static ResourceKey<Biome> meadow = register("meadow");
    public static ResourceKey<Biome> meadow_forest = register("meadow_forest");
    public static ResourceKey<Biome> muskeg = register("muskeg");
    public static ResourceKey<Biome> mystic_grove = register("mystic_grove");
    public static ResourceKey<Biome> mystic_plains = register("mystic_plains");
    public static ResourceKey<Biome> ominous_woods = register("ominous_woods");
    public static ResourceKey<Biome> ominous_mire = register("ominous_mire");
    public static ResourceKey<Biome> orchard = register("orchard");
    public static ResourceKey<Biome> origin_valley = register("origin_valley");
    public static ResourceKey<Biome> prairie = register("prairie");
    public static ResourceKey<Biome> rainbow_hills = register("rainbow_hills");
    public static ResourceKey<Biome> rainforest = register("rainforest");
    public static ResourceKey<Biome> rainforest_cliffs = register("rainforest_cliffs");
    public static ResourceKey<Biome> rainforest_floodplain = register("rainforest_floodplain");
    public static ResourceKey<Biome> scrubland = register("scrubland");
    public static ResourceKey<Biome> seasonal_forest = register("seasonal_forest");
    public static ResourceKey<Biome> seasonal_orchard = register("seasonal_orchard");
    public static ResourceKey<Biome> seasonal_pumpkin_patch = register("seasonal_pumpkin_patch");
    public static ResourceKey<Biome> shroomy_wetland = register("shroomy_wetland");
    public static ResourceKey<Biome> shrubland = register("shrubland");
    public static ResourceKey<Biome> shrubland_hills = register("shrubland_hills");
    public static ResourceKey<Biome> snowy_coniferous_forest = register("snowy_coniferous_forest");
    public static ResourceKey<Biome> snowy_fir_clearing = register("snowy_fir_clearing");
    public static ResourceKey<Biome> snowy_maple_forest = register("snowy_maple_forest");
    public static ResourceKey<Biome> tall_dead_forest = register("tall_dead_forest");
    public static ResourceKey<Biome> tropic_beach = register("tropic_beach");
    public static ResourceKey<Biome> tropics = register("tropics");
    public static ResourceKey<Biome> tundra = register("tundra");
    public static ResourceKey<Biome> tundra_basin = register("tundra_basin");
    public static ResourceKey<Biome> tundra_bog = register("tundra_bog");
    public static ResourceKey<Biome> volcanic_plains = register("volcanic_plains");
    public static ResourceKey<Biome> volcano = register("volcano");
    public static ResourceKey<Biome> wasteland = register("wasteland");
    public static ResourceKey<Biome> wetland = register("wetland");
    public static ResourceKey<Biome> wetland_forest = register("wetland_forest");
    public static ResourceKey<Biome> wooded_scrubland = register("wooded_scrubland");
    public static ResourceKey<Biome> crystalline_chasm = register("crystalline_chasm");
    public static ResourceKey<Biome> erupting_inferno = register("erupting_inferno");
    public static ResourceKey<Biome> undergrowth = register("undergrowth");
    public static ResourceKey<Biome> visceral_heap = register("visceral_heap");
    public static ResourceKey<Biome> withered_abyss = register("withered_abyss");
    public static ResourceKey<Biome> glowing_grotto = register("glowing_grotto");
    public static ResourceKey<Biome> spider_nest = register("spider_nest");
     */
}
