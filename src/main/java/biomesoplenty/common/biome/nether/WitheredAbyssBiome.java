package biomesoplenty.common.biome.nether;

import biomesoplenty.api.enums.BOPClimates;
import biomesoplenty.api.sound.BOPSounds;
import biomesoplenty.common.biome.NetherBiomeTemplate;
import biomesoplenty.common.world.gen.feature.BOPConfiguredFeatures;
import biomesoplenty.common.world.gen.surfacebuilders.BOPConfiguredSurfaceBuilders;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.Carvers;
import net.minecraft.data.worldgen.StructureFeatures;
import net.minecraft.sounds.Musics;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;

public class WitheredAbyssBiome extends NetherBiomeTemplate
{
    public WitheredAbyssBiome()
    {
        this.addWeight(BOPClimates.NETHER, 3);
    }

    @Override
    protected void configureBiome(Biome.BiomeBuilder builder)
    {
        builder.precipitation(Biome.Precipitation.NONE).biomeCategory(Biome.BiomeCategory.NETHER).depth(0.1F).scale(0.2F).temperature(2.0F).downfall(0.0F);

        builder.specialEffects((new BiomeSpecialEffects.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(0x0A0711).skyColor(calculateSkyColor(2.0F)).ambientParticle(new AmbientParticleSettings(ParticleTypes.SMOKE, 0.00357F)).ambientLoopSound(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_LOOP).ambientMoodSound(new AmbientMoodSettings(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_MOOD, 6000, 8, 2.0D)).backgroundMusic(Musics.createGameMusic(BOPSounds.MUSIC_BIOME_WITHERED_ABYSS)).build());
    }

    @Override
    protected void configureGeneration(BiomeGenerationSettings.Builder builder)
    {
        builder.surfaceBuilder(BOPConfiguredSurfaceBuilders.WITHERED_ABYSS);

        //Terrain
        builder.addStructureStart(StructureFeatures.NETHER_BRIDGE);
        builder.addStructureStart(StructureFeatures.BASTION_REMNANT);
        builder.addCarver(GenerationStep.Carving.AIR, Carvers.NETHER_CAVE);

        //Decoration
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, BOPConfiguredFeatures.OBSIDIAN_SPLATTER);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, BOPConfiguredFeatures.BLACKSTONE_SPINES);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, BOPConfiguredFeatures.BLACKSTONE_BULBS);

        BiomeDefaultFeatures.addNetherDefaultOres(builder);
    }

    @Override
    protected void configureMobSpawns(MobSpawnSettings.Builder builder)
    {
        //Entities
        builder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.ENDERMAN, 1, 4, 4));
        builder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.WITHER_SKELETON, 1, 1, 1));
    }
}
