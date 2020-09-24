/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.biome;

import biomesoplenty.api.enums.BOPClimates;
import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class BiomeTemplate
{
    private Map<BOPClimates, Integer> weightMap = new HashMap<BOPClimates, Integer>();
    private RegistryKey<Biome> beachBiome = Biomes.BEACH;
    private RegistryKey<Biome> riverBiome = Biomes.RIVER;
    private BiFunction<Double, Double, Integer> foliageColorFunction;
    private BiFunction<Double, Double, Integer> grassColorFunction;
    private BiFunction<Double, Double, Integer> waterColorFunction;

    protected void configureBiome(Biome.Builder builder) {}
    protected void configureGeneration(BiomeGenerationSettingsRegistryBuilder builder) {}
    protected void configureMobSpawns(MobSpawnInfo.Builder builder) {}

    protected void configureDefaultMobSpawns(MobSpawnInfo.Builder builder)
    {
        builder.setPlayerCanSpawn();
    }

    public final Biome build()
    {
        Biome.Builder biomeBuilder = new Biome.Builder();

        // Configure the biome generation
        BiomeGenerationSettingsRegistryBuilder biomeGenBuilder = new BiomeGenerationSettingsRegistryBuilder();
        this.configureGeneration(biomeGenBuilder);
        biomeBuilder.generationSettings(biomeGenBuilder.build());

        // Configure mob spawning
        MobSpawnInfo.Builder mobSpawnBuilder = new MobSpawnInfo.Builder();
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

    public void setBeachBiome(RegistryKey<Biome> biome)
    {
        this.beachBiome = biome;
    }

    public void setRiverBiome(RegistryKey<Biome> biome)
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
        lvt_1_1_ = MathHelper.clamp(lvt_1_1_, -1.0F, 1.0F);
        return MathHelper.hsvToRgb(0.62222224F - lvt_1_1_ * 0.05F, 0.5F + lvt_1_1_ * 0.1F, 1.0F);
    }

    protected static class BiomeGenerationSettingsRegistryBuilder extends BiomeGenerationSettings.Builder
    {
        public BiomeGenerationSettings.Builder addFeature(GenerationStage.Decoration stage, ConfiguredFeature<?, ?> feature)
        {
            // As a temporary measure, register configured features as we go
            // Thanks for continuing to fuck us over Mojang.
            if (!WorldGenRegistries.CONFIGURED_FEATURE.getResourceKey(feature).isPresent())
            {
                Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(BiomesOPlenty.MOD_ID, "" + feature.hashCode()), feature);
            }

            return super.addFeature(stage, feature);
        }
    }
}
