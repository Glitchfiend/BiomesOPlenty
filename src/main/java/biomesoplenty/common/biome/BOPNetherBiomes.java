/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.biome;

import biomesoplenty.api.sound.BOPSounds;
import biomesoplenty.common.worldgen.placement.BOPNetherPlacements;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.Carvers;
import net.minecraft.data.worldgen.placement.NetherPlacements;
import net.minecraft.data.worldgen.placement.OrePlacements;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.sounds.Musics;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;

import static biomesoplenty.common.biome.BOPOverworldBiomes.calculateSkyColor;

public class BOPNetherBiomes
{
    public static Biome crystallineChasm()
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.STRIDER, 60, 1, 2));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder();
        biomeBuilder.addCarver(GenerationStep.Carving.AIR, Carvers.NETHER_CAVE);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, NetherPlacements.SPRING_OPEN);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, NetherPlacements.GLOWSTONE_EXTRA);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, NetherPlacements.GLOWSTONE);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, VegetationPlacements.BROWN_MUSHROOM_NETHER);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, VegetationPlacements.RED_MUSHROOM_NETHER);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, OrePlacements.ORE_MAGMA);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, NetherPlacements.SPRING_CLOSED);
        BiomeDefaultFeatures.addNetherDefaultOres(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, BOPNetherPlacements.LARGE_ROSE_QUARTZ);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, BOPNetherPlacements.SMALL_CRYSTAL);

        return new Biome.BiomeBuilder()
                .precipitation(Biome.Precipitation.NONE).biomeCategory(Biome.BiomeCategory.NETHER).temperature(2.0F).downfall(0.0F)
                .specialEffects((new BiomeSpecialEffects.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(0x59002C).skyColor(calculateSkyColor(2.0F)).ambientParticle(new AmbientParticleSettings(ParticleTypes.ELECTRIC_SPARK, 0.0008925F)).ambientLoopSound(SoundEvents.AMBIENT_CRIMSON_FOREST_LOOP).ambientMoodSound(new AmbientMoodSettings(SoundEvents.AMBIENT_BASALT_DELTAS_MOOD, 6000, 8, 2.0D)).ambientAdditionsSound(new AmbientAdditionsSettings(SoundEvents.AMETHYST_BLOCK_CHIME, 0.0111D)).backgroundMusic(Musics.createGameMusic(BOPSounds.MUSIC_BIOME_CRYSTALLINE_CHASM)).build())
                .mobSpawnSettings(spawnBuilder.build()).generationSettings(biomeBuilder.build()).build();
    }

    public static Biome undergrowth()
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.GHAST, 50, 4, 4));
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.MAGMA_CUBE, 2, 4, 4));
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.HOGLIN, 9, 3, 4));
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.STRIDER, 60, 1, 2));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder();
        biomeBuilder.addCarver(GenerationStep.Carving.AIR, Carvers.NETHER_CAVE);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, NetherPlacements.SPRING_OPEN);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, NetherPlacements.PATCH_FIRE);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, NetherPlacements.PATCH_SOUL_FIRE);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, NetherPlacements.GLOWSTONE_EXTRA);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, NetherPlacements.GLOWSTONE);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, VegetationPlacements.BROWN_MUSHROOM_NETHER);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, VegetationPlacements.RED_MUSHROOM_NETHER);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, OrePlacements.ORE_MAGMA);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, NetherPlacements.SPRING_CLOSED);
        BiomeDefaultFeatures.addNetherDefaultOres(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, BOPNetherPlacements.TREES_UNDERGROWTH);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, BOPNetherPlacements.FLOWER_UNDERGROWTH);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, BOPNetherPlacements.NETHER_BRAMBLE);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, BOPNetherPlacements.SPROUTS_UNDERGROWTH);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, BOPNetherPlacements.DEAD_GRASS_45);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, BOPNetherPlacements.NETHER_VINES);

        return new Biome.BiomeBuilder()
                .precipitation(Biome.Precipitation.NONE).biomeCategory(Biome.BiomeCategory.NETHER).temperature(2.0F).downfall(0.0F)
                .specialEffects((new BiomeSpecialEffects.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(0x1C2109).skyColor(calculateSkyColor(2.0F)).ambientParticle(new AmbientParticleSettings(ParticleTypes.SPORE_BLOSSOM_AIR, 0.00357F)).ambientLoopSound(SoundEvents.AMBIENT_WARPED_FOREST_LOOP).ambientMoodSound(new AmbientMoodSettings(SoundEvents.AMBIENT_CRIMSON_FOREST_MOOD, 6000, 8, 2.0D)).ambientAdditionsSound(new AmbientAdditionsSettings(SoundEvents.AMBIENT_NETHER_WASTES_ADDITIONS, 0.0111D)).backgroundMusic(Musics.createGameMusic(BOPSounds.MUSIC_BIOME_UNDERGROWTH)).build())
                .mobSpawnSettings(spawnBuilder.build()).generationSettings(biomeBuilder.build()).build();
    }

    public static Biome visceralHeap()
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.GHAST, 50, 4, 4));
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.ZOMBIFIED_PIGLIN, 100, 4, 4));
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.STRIDER, 60, 1, 2));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder();
        biomeBuilder.addCarver(GenerationStep.Carving.AIR, Carvers.NETHER_CAVE);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, NetherPlacements.SPRING_OPEN);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, OrePlacements.ORE_MAGMA);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, NetherPlacements.SPRING_CLOSED);
        BiomeDefaultFeatures.addNetherDefaultOres(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, BOPNetherPlacements.BLOOD_LAKE);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, BOPNetherPlacements.BLOOD_SPRING);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, BOPNetherPlacements.NETHER_BONE_SPINE);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, BOPNetherPlacements.FLESH_TENDON);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, BOPNetherPlacements.HANGING_FLESH_TENDONS);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, BOPNetherPlacements.POROUS_FLESH);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, BOPNetherPlacements.PUS_BUBBLES);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, BOPNetherPlacements.HAIR);

        return new Biome.BiomeBuilder()
                .precipitation(Biome.Precipitation.NONE).biomeCategory(Biome.BiomeCategory.NETHER).temperature(2.0F).downfall(0.0F)
                .specialEffects((new BiomeSpecialEffects.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(0x601F18).skyColor(calculateSkyColor(2.0F)).ambientLoopSound(SoundEvents.AMBIENT_NETHER_WASTES_LOOP).ambientMoodSound(new AmbientMoodSettings(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_MOOD, 6000, 8, 2.0D)).ambientAdditionsSound(new AmbientAdditionsSettings(SoundEvents.AMBIENT_CRIMSON_FOREST_ADDITIONS, 0.0111D)).backgroundMusic(Musics.createGameMusic(BOPSounds.MUSIC_BIOME_VISCERAL_HEAP)).build())
                .mobSpawnSettings(spawnBuilder.build()).generationSettings(biomeBuilder.build()).build();
    }

    public static Biome witheredAbyss()
    {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.ENDERMAN, 1, 4, 4));
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.WITHER_SKELETON, 1, 1, 1));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder();
        biomeBuilder.addCarver(GenerationStep.Carving.AIR, Carvers.NETHER_CAVE);
        BiomeDefaultFeatures.addNetherDefaultOres(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, BOPNetherPlacements.OBSIDIAN_SPLATTER);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, BOPNetherPlacements.BLACKSTONE_SPINES);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, BOPNetherPlacements.BLACKSTONE_BULBS);

        return new Biome.BiomeBuilder()
            .precipitation(Biome.Precipitation.NONE).biomeCategory(Biome.BiomeCategory.NETHER).temperature(2.0F).downfall(0.0F)
            .specialEffects((new BiomeSpecialEffects.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(0x0A0711).skyColor(calculateSkyColor(2.0F)).grassColorOverride(0x312C36).foliageColorOverride(0x312C36).ambientLoopSound(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_LOOP).ambientMoodSound(new AmbientMoodSettings(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_MOOD, 6000, 8, 2.0D)).backgroundMusic(Musics.createGameMusic(BOPSounds.MUSIC_BIOME_WITHERED_ABYSS)).build())
            .mobSpawnSettings(spawnBuilder.build()).generationSettings(biomeBuilder.build()).build();
    }
}
