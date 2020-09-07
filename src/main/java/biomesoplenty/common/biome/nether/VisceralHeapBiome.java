package biomesoplenty.common.biome.nether;

import biomesoplenty.api.enums.BOPClimates;
import biomesoplenty.common.biome.NetherBiomeTemplate;
import biomesoplenty.common.world.gen.feature.BOPBiomeFeatures;
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
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;

public class VisceralHeapBiome extends NetherBiomeTemplate
{
    public VisceralHeapBiome()
    {
        this.addWeight(BOPClimates.NETHER, 9);
    }

    @Override
    protected void configureBiome(Biome.Builder builder)
    {
        builder.precipitation(Biome.RainType.NONE).biomeCategory(Biome.Category.NETHER).depth(0.1F).scale(0.2F).temperature(2.0F).downfall(0.0F);

        builder.specialEffects((new BiomeAmbience.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(0x601F18).skyColor(calculateSkyColor(2.0F)).ambientParticle(new ParticleEffectAmbience(ParticleTypes.FALLING_LAVA, 0.001785F)).ambientLoopSound(SoundEvents.AMBIENT_NETHER_WASTES_LOOP).ambientMoodSound(new MoodSoundAmbience(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_MOOD, 6000, 8, 2.0D)).ambientAdditionsSound(new SoundAdditionsAmbience(SoundEvents.AMBIENT_CRIMSON_FOREST_ADDITIONS, 0.0111D)).backgroundMusic(BackgroundMusicTracks.createGameMusic(SoundEvents.MUSIC_BIOME_CRIMSON_FOREST)).build());
    }

    @Override
    protected void configureGeneration(BiomeGenerationSettings.Builder builder)
    {
        builder.surfaceBuilder(new ConfiguredSurfaceBuilder(BOPBiomeFeatures.FLESH_SURFACE_BUILDER, SurfaceBuilder.CONFIG_HELL));

        //Terrain
        builder.addStructureStart(StructureFeatures.RUINED_PORTAL_NETHER);
        builder.addStructureStart(StructureFeatures.NETHER_BRIDGE);
        builder.addStructureStart(StructureFeatures.BASTION_REMNANT);
        builder.addCarver(GenerationStage.Carving.AIR, ConfiguredCarvers.NETHER_CAVE);

        builder.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Features.SPRING_OPEN);
        builder.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Features.ORE_MAGMA);
        builder.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Features.SPRING_CLOSED);

        builder.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, BOPBiomeFeatures.FLESH_TENDON.configured(IFeatureConfig.NONE).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(65)));
        builder.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, BOPBiomeFeatures.BONE_SPINE.configured(IFeatureConfig.NONE).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(6)));

        DefaultBiomeFeatures.addNetherDefaultOres(builder);
    }

    @Override
    protected void configureMobSpawns(MobSpawnInfo.Builder builder)
    {
        //Entities
        builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.GHAST, 50, 4, 4));
        builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.ZOMBIFIED_PIGLIN, 100, 4, 4));
        builder.addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityType.STRIDER, 60, 1, 2));
    }
}
