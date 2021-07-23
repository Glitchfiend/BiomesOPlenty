/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.biome;

import biomesoplenty.api.enums.BOPClimates;
import com.google.common.collect.ImmutableMap;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.function.BiFunction;

public class BiomeMetadata
{
    private final ImmutableMap<BOPClimates, Integer> weightMap;

    @Nullable
    private final ResourceKey<Biome> beachBiome;

    @Nullable
    private final ResourceKey<Biome> riverBiome;

    @Nullable
    private final BiFunction<Double, Double, Integer> foliageColorFunction;

    @Nullable
    private final BiFunction<Double, Double, Integer> grassColorFunction;

    @Nullable
    private final BiFunction<Double, Double, Integer> waterColorFunction;

    protected BiomeMetadata(Map<BOPClimates, Integer> weights, @Nullable ResourceKey<Biome> beachBiome, @Nullable ResourceKey<Biome> riverBiome, BiFunction<Double, Double, Integer> foliageColorFunction, BiFunction<Double, Double, Integer> grassColorFunction, BiFunction<Double, Double, Integer> waterColorFunction)
    {
        this.weightMap = ImmutableMap.copyOf(weights);
        this.beachBiome = beachBiome;
        this.riverBiome = riverBiome;
        this.foliageColorFunction = foliageColorFunction;
        this.grassColorFunction = grassColorFunction;
        this.waterColorFunction = waterColorFunction;
    }

    public Map<BOPClimates, Integer> getWeightMap()
    {
        return this.weightMap;
    }

    @Nullable
    public ResourceKey<Biome> getBeachBiome()
    {
        return this.beachBiome;
    }

    @Nullable
    public ResourceKey<Biome> getRiverBiome()
    {
        return this.riverBiome;
    }

    @Nullable
    public BiFunction<Double, Double, Integer> getFoliageColorFunction()
    {
        return this.foliageColorFunction;
    }

    @Nullable
    public BiFunction<Double, Double, Integer> getGrassColorFunction()
    {
        return this.grassColorFunction;
    }

    @Nullable
    public BiFunction<Double, Double, Integer> getWaterColorFunction()
    {
        return this.waterColorFunction;
    }

    public boolean hasWeights()
    {
        return !this.weightMap.isEmpty() && !this.weightMap.entrySet().stream().allMatch((entry) -> entry.getValue().equals(0));
    }
}
