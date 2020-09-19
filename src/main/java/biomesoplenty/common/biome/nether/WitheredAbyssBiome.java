package biomesoplenty.common.biome.nether;

import biomesoplenty.api.enums.BOPClimates;
import biomesoplenty.common.biome.NetherBiomeTemplate;
import biomesoplenty.common.world.gen.feature.BOPFeatures;
import biomesoplenty.common.world.gen.surfacebuilders.BOPSurfaceBuilders;
import net.minecraft.client.audio.BackgroundMusicTracks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.carver.ConfiguredCarvers;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.structure.StructureFeatures;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;

public class WitheredAbyssBiome extends NetherBiomeTemplate
{
    public WitheredAbyssBiome()
    {
        this.addWeight(BOPClimates.NETHER, 7);
    }

    @Override
    protected void configureBiome(Biome.Builder builder)
    {
        builder.precipitation(Biome.RainType.NONE).biomeCategory(Biome.Category.NETHER).depth(0.1F).scale(0.2F).temperature(2.0F).downfall(0.0F);

        builder.specialEffects((new BiomeAmbience.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(0x09060F).skyColor(calculateSkyColor(2.0F)).ambientParticle(new ParticleEffectAmbience(ParticleTypes.SMOKE, 0.00357F)).ambientLoopSound(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_LOOP).ambientMoodSound(new MoodSoundAmbience(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_MOOD, 6000, 8, 2.0D)).backgroundMusic(BackgroundMusicTracks.createGameMusic(SoundEvents.MUSIC_BIOME_BASALT_DELTAS)).build());
    }

    @Override
    protected void configureGeneration(BiomeGenerationSettingsRegistryBuilder builder)
    {
        builder.surfaceBuilder(new ConfiguredSurfaceBuilder(BOPSurfaceBuilders.WITHERED_ABYSS, BOPSurfaceBuilders.BLACKSTONE_SURFACE));

        //Terrain
        builder.addStructureStart(StructureFeatures.RUINED_PORTAL_NETHER);
        builder.addStructureStart(StructureFeatures.NETHER_BRIDGE);
        builder.addStructureStart(StructureFeatures.BASTION_REMNANT);
        builder.addCarver(GenerationStage.Carving.AIR, ConfiguredCarvers.NETHER_CAVE);

        //Decoration
        builder.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, BOPFeatures.OBSIDIAN_SPLATTER.configured(IFeatureConfig.NONE).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(12)));

        DefaultBiomeFeatures.addNetherDefaultOres(builder);
    }

    @Override
    protected void configureMobSpawns(MobSpawnInfo.Builder builder)
    {
        //Entities
        builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.ENDERMAN, 1, 4, 4));
        builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.WITHER_SKELETON, 1, 1, 1));
    }
}
