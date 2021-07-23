/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.biome;

import biomesoplenty.api.enums.BOPClimates;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.Mth;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class BiomeTemplate
{
    private Map<BOPClimates, Integer> weightMap = new HashMap<BOPClimates, Integer>();
    private ResourceKey<Biome> beachBiome = Biomes.BEACH;
    private ResourceKey<Biome> riverBiome = Biomes.RIVER;
    private BiFunction<Double, Double, Integer> foliageColorFunction;
    private BiFunction<Double, Double, Integer> grassColorFunction;
    private BiFunction<Double, Double, Integer> waterColorFunction;

    protected void configureBiome(Biome.BiomeBuilder builder) {}
    protected void configureGeneration(BiomeGenerationSettings.Builder builder) {}
    protected void configureMobSpawns(MobSpawnSettings.Builder builder) {}

    protected void configureDefaultMobSpawns(MobSpawnSettings.Builder builder)
    {
        builder.setPlayerCanSpawn();
    }

    public final Biome build()
    {
        Biome.BiomeBuilder biomeBuilder = new Biome.BiomeBuilder();

        // Configure the biome generation
        BiomeGenerationSettings.Builder biomeGenBuilder = new BiomeGenerationSettings.Builder();
        this.configureGeneration(biomeGenBuilder);
        biomeBuilder.generationSettings(biomeGenBuilder.build());

        // Configure mob spawning
        MobSpawnSettings.Builder mobSpawnBuilder = new MobSpawnSettings.Builder();
        this.configureDefaultMobSpawns(mobSpawnBuilder);
        this.configureMobSpawns(mobSpawnBuilder);
        biomeBuilder.mobSpawnSettings(mobSpawnBuilder.build());

        // Configure and build the biome
        this.configureBiome(biomeBuilder);
        return biomeBuilder.build();
    }

    public final BiomeMetadata buildMetadata()
    {
        return new BiomeMetadata(this.weightMap, this.beachBiome, this.riverBiome, this.foliageColorFunction, this.grassColorFunction, this.waterColorFunction);
    }

    public void addWeight(BOPClimates climate, int weight)
    {
        this.weightMap.put(climate, weight);
    }

    public void setBeachBiome(ResourceKey<Biome> biome)
    {
        this.beachBiome = biome;
    }

    public void setRiverBiome(ResourceKey<Biome> biome)
    {
        this.riverBiome = biome;
    }

    public void setFoliageColorFunction(BiFunction<Double, Double, Integer> func)
    {
        this.foliageColorFunction = func;
    }

    public void setGrassColorFunction(BiFunction<Double, Double, Integer> func)
    {
        this.grassColorFunction = func;
    }

    public void setWaterColorFunction(BiFunction<Double, Double, Integer> func)
    {
        this.waterColorFunction = func;
    }

    public static int calculateSkyColor(float temperature)
    {
        float lvt_1_1_ = temperature / 3.0F;
        lvt_1_1_ = Mth.clamp(lvt_1_1_, -1.0F, 1.0F);
        return Mth.hsvToRgb(0.62222224F - lvt_1_1_ * 0.05F, 0.5F + lvt_1_1_ * 0.1F, 1.0F);
    }
}
