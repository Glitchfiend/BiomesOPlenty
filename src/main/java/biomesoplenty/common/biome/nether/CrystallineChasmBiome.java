package biomesoplenty.common.biome.nether;

import biomesoplenty.api.enums.BOPClimates;
import biomesoplenty.common.biome.NetherBiomeBOP;
import biomesoplenty.common.world.biome.BiomeFeatureHelper;
import biomesoplenty.common.world.gen.feature.BOPBiomeFeatures;
import com.google.common.collect.ImmutableList;
import net.minecraft.block.Blocks;
import net.minecraft.client.audio.BackgroundMusicTracks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.placement.ChanceRangeConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;

public class CrystallineChasmBiome extends NetherBiomeBOP
{
    public CrystallineChasmBiome()
    {
        super((new Builder()).surfaceBuilder(SurfaceBuilder.NETHER, SurfaceBuilder.CONFIG_HELL).precipitation(RainType.NONE).biomeCategory(Category.NETHER).depth(0.1F).scale(0.2F).temperature(2.0F).downfall(0.0F).specialEffects((new BiomeAmbience.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(0x59002C).ambientParticle(new ParticleEffectAmbience(ParticleTypes.INSTANT_EFFECT, 0.001785F)).ambientLoopSound(SoundEvents.AMBIENT_CRIMSON_FOREST_LOOP).ambientMoodSound(new MoodSoundAmbience(SoundEvents.AMBIENT_BASALT_DELTAS_MOOD, 6000, 8, 2.0D)).ambientAdditionsSound(new SoundAdditionsAmbience(SoundEvents.AMBIENT_BASALT_DELTAS_ADDITIONS, 0.0111D)).backgroundMusic(BackgroundMusicTracks.createGameMusic(SoundEvents.MUSIC_BIOME_NETHER_WASTES)).build()).parent((String)null).optimalParameters(ImmutableList.of(new Attributes(0.0F, 1.0F, 0.0F, 0.0F, 0.0F))));

        //Terrain
        this.addStructureStart(DefaultBiomeFeatures.RUINED_PORTAL_NETHER);
        this.addStructureStart(DefaultBiomeFeatures.NETHER_BRIDGE);
        this.addStructureStart(DefaultBiomeFeatures.BASTION_REMNANT);
        this.addCarver(GenerationStage.Carving.AIR, makeCarver(WorldCarver.NETHER_CAVE, new ProbabilityConfig(0.2F)));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.SPRING.configured(DefaultBiomeFeatures.LAVA_SPRING_CONFIG).decorated(Placement.COUNT_VERY_BIASED_RANGE.configured(new CountRangeConfig(20, 8, 16, 256))));

        DefaultBiomeFeatures.addDefaultMushrooms(this);

        this.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Feature.SPRING.configured(DefaultBiomeFeatures.OPEN_NETHER_SPRING_CONFIG).decorated(Placement.COUNT_RANGE.configured(new CountRangeConfig(8, 4, 8, 128))));
        this.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Feature.RANDOM_PATCH.configured(DefaultBiomeFeatures.FIRE_CONFIG).decorated(Placement.FIRE.configured(new FrequencyConfig(10))));
        this.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Feature.GLOWSTONE_BLOB.configured(IFeatureConfig.NONE).decorated(Placement.LIGHT_GEM_CHANCE.configured(new FrequencyConfig(10))));
        this.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Feature.GLOWSTONE_BLOB.configured(IFeatureConfig.NONE).decorated(Placement.COUNT_RANGE.configured(new CountRangeConfig(10, 0, 0, 128))));

        //Decoration
        this.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, BOPBiomeFeatures.LARGE_CRYSTAL.configured(IFeatureConfig.NONE).decorated(Placement.COUNT_HEIGHTMAP_DOUBLE.configured(new FrequencyConfig(200))));
        this.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, BOPBiomeFeatures.SMALL_CRYSTAL.configured(IFeatureConfig.NONE).decorated(Placement.COUNT_HEIGHTMAP_DOUBLE.configured(new FrequencyConfig(60))));

        //Base Decorations
        this.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Feature.RANDOM_PATCH.configured(BiomeFeatureHelper.createClusterConfiguration(Blocks.BROWN_MUSHROOM.defaultBlockState())).decorated(Placement.CHANCE_RANGE.configured(new ChanceRangeConfig(0.5F, 0, 0, 128))));
        this.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Feature.RANDOM_PATCH.configured(BiomeFeatureHelper.createClusterConfiguration(Blocks.RED_MUSHROOM.defaultBlockState())).decorated(Placement.CHANCE_RANGE.configured(new ChanceRangeConfig(0.5F, 0, 0, 128))));
        this.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Feature.ORE.configured(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NETHERRACK, Blocks.NETHER_QUARTZ_ORE.defaultBlockState(), 14)).decorated(Placement.COUNT_RANGE.configured(new CountRangeConfig(16, 10, 20, 128))));
        this.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Feature.ORE.configured(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NETHERRACK, Blocks.MAGMA_BLOCK.defaultBlockState(), 33)).decorated(Placement.MAGMA.configured(new FrequencyConfig(4))));
        this.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Feature.SPRING.configured(DefaultBiomeFeatures.CLOSED_NETHER_SPRING_CONFIG).decorated(Placement.COUNT_RANGE.configured(new CountRangeConfig(16, 10, 20, 128))));

        DefaultBiomeFeatures.addNetherDefaultOres(this);

        //Entities
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.GHAST, 50, 4, 4));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.MAGMA_CUBE, 2, 4, 4));
        this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(EntityType.STRIDER, 60, 1, 2));

        this.addWeight(BOPClimates.NETHER, 8);
    }
}
