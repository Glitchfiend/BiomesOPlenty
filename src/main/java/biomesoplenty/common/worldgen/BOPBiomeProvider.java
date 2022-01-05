/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.worldgen;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.common.biome.BOPOverworldBiomeBuilder;
import biomesoplenty.common.util.biome.BiomeUtil;
import biomesoplenty.core.BiomesOPlenty;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.levelgen.SurfaceRules;
import terrablender.api.BiomeProvider;
import terrablender.api.BiomeProviders;
import terrablender.worldgen.BiomeProviderUtils;
import terrablender.worldgen.TBClimate;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class BOPBiomeProvider extends BiomeProvider
{
    public static final ResourceLocation LOCATION = new ResourceLocation(BiomesOPlenty.MOD_ID, "biome_provider");
    public static final ResourceLocation RARE_LOCATION = new ResourceLocation(BiomesOPlenty.MOD_ID, "rare_biome_provider");

    public BOPBiomeProvider(int weight)
    {
        super(LOCATION, weight);
    }

    @Override
    public void addOverworldBiomes(Registry<Biome> registry, Consumer<Pair<TBClimate.ParameterPoint, ResourceKey<Biome>>> mapper)
    {
        (new BOPOverworldBiomeBuilder()).addBiomes(registry, mapper);
    }

    @Override
    public void addNetherBiomes(Registry<Biome> registry, Consumer<Pair<TBClimate.ParameterPoint, ResourceKey<Biome>>> mapper)
    {
        this.addVanillaBiome(mapper, Climate.Parameter.point(0.0F), Climate.Parameter.point(0.0F), Climate.Parameter.point(0.0F), Climate.Parameter.point(0.0F), Climate.Parameter.point(0.0F), Climate.Parameter.point(0.0F), 0.0F, Biomes.NETHER_WASTES);
        this.addVanillaBiome(mapper, Climate.Parameter.point(0.0F), Climate.Parameter.point(-0.5F), Climate.Parameter.point(0.0F), Climate.Parameter.point(0.0F), Climate.Parameter.point(0.0F), Climate.Parameter.point(0.0F), 0.0F, Biomes.SOUL_SAND_VALLEY);
        this.addVanillaBiome(mapper, Climate.Parameter.point(0.4F), Climate.Parameter.point(0.0F), Climate.Parameter.point(0.0F), Climate.Parameter.point(0.0F), Climate.Parameter.point(0.0F), Climate.Parameter.point(0.0F), 0.0F, Biomes.CRIMSON_FOREST);
        this.addVanillaBiome(mapper, Climate.Parameter.point(0.0F), Climate.Parameter.point(0.5F), Climate.Parameter.point(0.0F), Climate.Parameter.point(0.0F), Climate.Parameter.point(0.0F), Climate.Parameter.point(0.0F), 0.375F, Biomes.WARPED_FOREST);
        this.addVanillaBiome(mapper, Climate.Parameter.point(-0.5F), Climate.Parameter.point(0.0F), Climate.Parameter.point(0.0F), Climate.Parameter.point(0.0F), Climate.Parameter.point(0.0F), Climate.Parameter.point(0.0F), 0.175F, Biomes.BASALT_DELTAS);

        this.addBiome(mapper, Climate.Parameter.point(0.0F), Climate.Parameter.point(0.0F), Climate.Parameter.point(0.0F), Climate.Parameter.point(0.0F), Climate.Parameter.point(0.0F), Climate.Parameter.point(0.0F), 0.0F, BiomeUtil.biomeOrFallback(registry, BOPBiomes.WITHERED_ABYSS, Biomes.NETHER_WASTES));
        this.addBiome(mapper, Climate.Parameter.point(0.0F), Climate.Parameter.point(-0.5F), Climate.Parameter.point(0.0F), Climate.Parameter.point(0.0F), Climate.Parameter.point(0.0F), Climate.Parameter.point(0.0F), 0.0F, BiomeUtil.biomeOrFallback(registry, BOPBiomes.CRYSTALLINE_CHASM, Biomes.SOUL_SAND_VALLEY));
        this.addBiome(mapper, Climate.Parameter.point(0.4F), Climate.Parameter.point(0.0F), Climate.Parameter.point(0.0F), Climate.Parameter.point(0.0F), Climate.Parameter.point(0.0F), Climate.Parameter.point(0.0F), 0.0F, BiomeUtil.biomeOrFallback(registry, BOPBiomes.UNDERGROWTH, Biomes.CRIMSON_FOREST));
        this.addBiome(mapper, Climate.Parameter.point(0.0F), Climate.Parameter.point(0.5F), Climate.Parameter.point(0.0F), Climate.Parameter.point(0.0F), Climate.Parameter.point(0.0F), Climate.Parameter.point(0.0F), 0.375F, BiomeUtil.biomeOrFallback(registry, BOPBiomes.VISCERAL_HEAP, Biomes.WARPED_FOREST));
        this.addBiome(mapper, Climate.Parameter.point(-0.5F), Climate.Parameter.point(0.0F), Climate.Parameter.point(0.0F), Climate.Parameter.point(0.0F), Climate.Parameter.point(0.0F), Climate.Parameter.point(0.0F), 0.175F, BiomeUtil.biomeOrFallback(registry, BOPBiomes.ERUPTING_INFERNO, Biomes.BASALT_DELTAS));
    }

    @Override
    public Optional<SurfaceRules.RuleSource> getOverworldSurfaceRules()
    {
        return Optional.of(BOPSurfaceRuleData.overworld());
    }

    @Override
    public Optional<SurfaceRules.RuleSource> getNetherSurfaceRules()
    {
        return Optional.of(BOPSurfaceRuleData.nether());
    }

    @Override
    public List<TBClimate.ParameterPoint> getSpawnTargets()
    {
        return (new BOPOverworldBiomeBuilder()).spawnTarget();
    }

    private final void addVanillaBiome(Consumer<Pair<TBClimate.ParameterPoint, ResourceKey<Biome>>> mapper, Climate.Parameter temperature, Climate.Parameter humidity, Climate.Parameter continentalness, Climate.Parameter erosion, Climate.Parameter weirdness, Climate.Parameter depth, float offset, ResourceKey<Biome> biome)
    {
        Climate.Parameter vanillaUniqueness = BiomeProviderUtils.getUniquenessParameter(BiomeProviders.getIndex(BiomeProviders.DEFAULT_PROVIDER_LOCATION));
        mapper.accept(Pair.of(TBClimate.parameters(temperature, humidity, continentalness, erosion, depth, weirdness, vanillaUniqueness, offset), biome));
    }
}
