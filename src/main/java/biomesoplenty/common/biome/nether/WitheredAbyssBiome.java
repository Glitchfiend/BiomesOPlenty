package biomesoplenty.common.biome.nether;

import biomesoplenty.api.block.BOPBlocks;
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

public class WitheredAbyssBiome extends NetherBiomeBOP
{
    public WitheredAbyssBiome()
    {
        super((new Builder()).surfaceBuilder(BOPBiomeFeatures.WITHERED_ABYSS_SURFACE_BUILDER, BOPBiomeFeatures.BLACKSTONE_SURFACE).precipitation(RainType.NONE).biomeCategory(Category.NETHER).depth(0.1F).scale(0.2F).temperature(2.0F).downfall(0.0F).specialEffects((new BiomeAmbience.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(0x09060F).ambientParticle(new ParticleEffectAmbience(ParticleTypes.SMOKE, 0.00357F)).ambientLoopSound(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_LOOP).ambientMoodSound(new MoodSoundAmbience(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_MOOD, 6000, 8, 2.0D)).backgroundMusic(BackgroundMusicTracks.createGameMusic(SoundEvents.MUSIC_BIOME_BASALT_DELTAS)).build()).parent((String)null).optimalParameters(ImmutableList.of(new Attributes(0.0F, 1.0F, 0.0F, 0.0F, 0.0F))));

        //Terrain
        this.addStructureStart(DefaultBiomeFeatures.RUINED_PORTAL_NETHER);
        this.addStructureStart(DefaultBiomeFeatures.NETHER_BRIDGE);
        this.addStructureStart(DefaultBiomeFeatures.BASTION_REMNANT);
        this.addCarver(GenerationStage.Carving.AIR, makeCarver(WorldCarver.NETHER_CAVE, new ProbabilityConfig(0.2F)));

        //Decoration
        this.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, BOPBiomeFeatures.OBSIDIAN_SPLATTER.configured(IFeatureConfig.NONE).decorated(Placement.COUNT_HEIGHTMAP_DOUBLE.configured(new FrequencyConfig(12))));

        DefaultBiomeFeatures.addNetherDefaultOres(this);

        //Entities
        this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.ENDERMAN, 1, 4, 4));
        this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.WITHER_SKELETON, 1, 1, 1));

        this.addWeight(BOPClimates.NETHER, 7);
    }
}
