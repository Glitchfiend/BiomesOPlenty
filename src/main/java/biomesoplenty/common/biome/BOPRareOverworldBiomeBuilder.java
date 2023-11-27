/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.biome;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.common.util.biome.BiomeUtil;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;

import java.util.function.Consumer;

public class BOPRareOverworldBiomeBuilder extends BOPOverworldBiomeBuilder
{
    private final ResourceKey<Biome>[][] RARE_BIOMES_BOP = new ResourceKey[][]{
            {null, null, null,                    BOPBiomes.AURORAL_GARDEN, null},
            {null, null, BOPBiomes.OMINOUS_WOODS, null,                     null},
            {null, null, null,                    BOPBiomes.MYSTIC_GROVE,   null},
            {null, null, null,                    null,                     BOPBiomes.FUNGAL_JUNGLE},
            {null, null, null,                    null,                     null}
    };

    private final ResourceKey<Biome>[][] RARE_BIOMES_VARIANT_BOP = new ResourceKey[][]{
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null}
    };

    @Override
    protected ResourceKey<Biome> pickMiddleBiomeBOP(Registry<Biome> biomeRegistry, int temperatureIndex, int humidityIndex, Climate.Parameter weirdness)
    {
        ResourceKey<Biome> middleBiome = BiomeUtil.biomeOrFallback(biomeRegistry, this.RARE_BIOMES_BOP[temperatureIndex][humidityIndex], this.MIDDLE_BIOMES_BOP[temperatureIndex][humidityIndex], this.MIDDLE_BIOMES[temperatureIndex][humidityIndex]);

        if (weirdness.max() < 0) return middleBiome;
        else
        {
            return BiomeUtil.biomeOrFallback(biomeRegistry, this.RARE_BIOMES_VARIANT_BOP[temperatureIndex][humidityIndex], this.RARE_BIOMES_BOP[temperatureIndex][humidityIndex], this.MIDDLE_BIOMES_VARIANT_BOP[temperatureIndex][humidityIndex], middleBiome);
        }
    }

    @Override
    protected ResourceKey<Biome> pickPlateauBiomeBOP(Registry<Biome> biomeRegistry, int temperatureIndex, int humidityIndex, Climate.Parameter weirdness)
    {
        if (weirdness.max() < 0L) return BiomeUtil.biomeOrFallback(biomeRegistry, this.RARE_BIOMES_BOP[temperatureIndex][humidityIndex], this.PLATEAU_BIOMES_BOP[temperatureIndex][humidityIndex], this.PLATEAU_BIOMES[temperatureIndex][humidityIndex]);
        else return BiomeUtil.biomeOrFallback(biomeRegistry, this.RARE_BIOMES_BOP[temperatureIndex][humidityIndex], this.PLATEAU_BIOMES_VARIANT_BOP[temperatureIndex][humidityIndex], this.PLATEAU_BIOMES_BOP[temperatureIndex][humidityIndex], this.PLATEAU_BIOMES_VARIANT[temperatureIndex][humidityIndex], this.PLATEAU_BIOMES[temperatureIndex][humidityIndex]);
    }

    @Override
    protected ResourceKey<Biome> pickRiverBiomeBOP(Registry<Biome> biomeRegistry, int temperatureIndex, int humidityIndex)
    {
        return BiomeUtil.biomeOrFallback(biomeRegistry, this.RARE_BIOMES_BOP[temperatureIndex][humidityIndex], this.RIVER_BIOMES_BOP[temperatureIndex][humidityIndex], temperatureIndex == 0 ? Biomes.FROZEN_RIVER : Biomes.RIVER);
    }

    @Override
    protected ResourceKey<Biome> pickSwampBiomeBOP(Registry<Biome> biomeRegistry, int temperatureIndex, int humidityIndex, Climate.Parameter weirdness)
    {
        return BiomeUtil.biomeOrFallback(biomeRegistry, this.RARE_BIOMES_BOP[temperatureIndex][humidityIndex], this.SWAMP_BIOMES_BOP[temperatureIndex][humidityIndex], this.pickSwampBiomeVanilla(temperatureIndex, humidityIndex, weirdness));
    }
}