package biomesoplenty.common.biome.nether;

import biomesoplenty.api.enums.BOPClimates;
import biomesoplenty.common.biome.NetherBiomeTemplate;
import biomesoplenty.common.world.gen.feature.BOPConfiguredFeatures;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.data.worldgen.*;
import net.minecraft.sounds.Musics;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;

public class UndergrowthBiome extends NetherBiomeTemplate
{
    public UndergrowthBiome()
    {
        this.addWeight(BOPClimates.NETHER, 10);
    }

    @Override
    protected void configureBiome(Biome.BiomeBuilder builder)
    {
        builder.precipitation(Biome.Precipitation.NONE).biomeCategory(Biome.BiomeCategory.NETHER).depth(0.1F).scale(0.2F).temperature(2.0F).downfall(0.0F);

        builder.specialEffects((new BiomeSpecialEffects.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(0x1C2109).skyColor(calculateSkyColor(2.0F)).ambientParticle(new AmbientParticleSettings(ParticleTypes.SPORE_BLOSSOM_AIR, 0.00714F)).ambientLoopSound(SoundEvents.AMBIENT_WARPED_FOREST_LOOP).ambientMoodSound(new AmbientMoodSettings(SoundEvents.AMBIENT_CRIMSON_FOREST_MOOD, 6000, 8, 2.0D)).ambientAdditionsSound(new AmbientAdditionsSettings(SoundEvents.AMBIENT_NETHER_WASTES_ADDITIONS, 0.0111D)).backgroundMusic(Musics.createGameMusic(SoundEvents.MUSIC_BIOME_NETHER_WASTES)).build());
    }

    @Override
    protected void configureGeneration(BiomeGenerationSettings.Builder builder)
    {
        builder.surfaceBuilder(SurfaceBuilders.NETHER);

        //Terrain
        builder.addStructureStart(StructureFeatures.RUINED_PORTAL_NETHER);
        builder.addStructureStart(StructureFeatures.NETHER_BRIDGE);
        builder.addStructureStart(StructureFeatures.BASTION_REMNANT);
        builder.addCarver(GenerationStep.Carving.AIR, Carvers.NETHER_CAVE);

        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, Features.SPRING_OPEN);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, Features.PATCH_FIRE);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, Features.PATCH_SOUL_FIRE);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, Features.GLOWSTONE_EXTRA);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, Features.GLOWSTONE);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, Features.BROWN_MUSHROOM_NETHER);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, Features.RED_MUSHROOM_NETHER);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, Features.ORE_MAGMA);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, Features.SPRING_CLOSED);

        //Vegetation
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, BOPConfiguredFeatures.UNDERGROWTH_TREES);

        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, BOPConfiguredFeatures.NETHER_BRAMBLE);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, BOPConfiguredFeatures.UNDERGROWTH_FLOWERS);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, BOPConfiguredFeatures.SPROUTS_UNDERGROWTH);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, BOPConfiguredFeatures.DEAD_GRASS_45);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, BOPConfiguredFeatures.NETHER_VINES);

        BiomeDefaultFeatures.addNetherDefaultOres(builder);
    }

    @Override
    protected void configureMobSpawns(MobSpawnSettings.Builder builder)
    {
        //Entities
        builder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.GHAST, 50, 4, 4));
        builder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.MAGMA_CUBE, 2, 4, 4));
        builder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.HOGLIN, 9, 3, 4));
        builder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.STRIDER, 60, 1, 2));
    }
}
