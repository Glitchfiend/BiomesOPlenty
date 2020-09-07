/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.biome;

import biomesoplenty.api.enums.BOPClimates;
import biomesoplenty.common.util.biome.BiomeUtil;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.MobSpawnInfo;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class BiomeTemplate
{
    private Map<BOPClimates, Integer> weightMap = new HashMap<BOPClimates, Integer>();
    private RegistryKey<Biome> beachBiome = Biomes.BEACH;
    private RegistryKey<Biome> riverBiome = Biomes.RIVER;

    protected void configureBiome(Biome.Builder builder) {}
    protected void configureGeneration(BiomeGenerationSettings.Builder builder) {}
    protected void configureMobSpawns(MobSpawnInfo.Builder builder) {}

    protected void configureDefaultMobSpawns(MobSpawnInfo.Builder builder)
    {
        builder.setPlayerCanSpawn();
    }

    public final Biome build()
    {
        Biome.Builder biomeBuilder = new Biome.Builder();

        // Configure the biome generation
        BiomeGenerationSettings.Builder biomeGenBuilder = new BiomeGenerationSettings.Builder();
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
        return new BiomeMetadata(this.weightMap, this.beachBiome, this.riverBiome);
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

    public static int calculateSkyColor(float temperature)
    {
        float lvt_1_1_ = temperature / 3.0F;
        lvt_1_1_ = MathHelper.clamp(lvt_1_1_, -1.0F, 1.0F);
        return MathHelper.hsvToRgb(0.62222224F - lvt_1_1_ * 0.05F, 0.5F + lvt_1_1_ * 0.1F, 1.0F);
    }
}
