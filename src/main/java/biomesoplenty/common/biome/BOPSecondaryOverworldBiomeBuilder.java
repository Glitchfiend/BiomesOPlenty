/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.biome;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.common.util.biome.BiomeUtil;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;

public class BOPSecondaryOverworldBiomeBuilder extends BOPOverworldBiomeBuilder
{
    private final ResourceKey<Biome>[][] MIDDLE_BIOMES_BOP = new ResourceKey[][]{
            {BOPBiomes.COLD_DESERT,          BOPBiomes.MUSKEG,               BOPBiomes.MUSKEG,           BOPBiomes.MAPLE_WOODS,          BOPBiomes.MAPLE_WOODS},
            {BOPBiomes.TUNDRA,               BOPBiomes.TUNDRA,               BOPBiomes.DEAD_FOREST,      BOPBiomes.DEAD_FOREST,          BOPBiomes.SEASONAL_FOREST},
            {BOPBiomes.LAVENDER_FIELD,       BOPBiomes.LAVENDER_FIELD,       BOPBiomes.REDWOOD_FOREST,   BOPBiomes.CHERRY_BLOSSOM_GROVE, BOPBiomes.CHERRY_BLOSSOM_GROVE},
            {BOPBiomes.MEDITERRANEAN_FOREST, BOPBiomes.MEDITERRANEAN_FOREST, BOPBiomes.WOODLAND,         BOPBiomes.RAINFOREST,           BOPBiomes.RAINFOREST},
            {BOPBiomes.WASTELAND,            BOPBiomes.WASTELAND,            BOPBiomes.WOODED_WASTELAND, BOPBiomes.VOLCANIC_PLAINS,      BOPBiomes.VOLCANIC_PLAINS}
    };

    private final ResourceKey<Biome>[][] MIDDLE_BIOMES_VARIANT_BOP = new ResourceKey[][]{
            {null, null,                       null,                          BOPBiomes.SNOWY_MAPLE_WOODS,      null},
            {null, null,                       null,                          BOPBiomes.OLD_GROWTH_DEAD_FOREST, BOPBiomes.BOREAL_FOREST},
            {null, BOPBiomes.LAVENDER_FOREST,  BOPBiomes.ORCHARD,             null,                             BOPBiomes.BAMBOO_GROVE},
            {null, null,                       BOPBiomes.OLD_GROWTH_WOODLAND, BOPBiomes.ROCKY_RAINFOREST,       BOPBiomes.FLOODPLAIN},
            {null, BOPBiomes.WOODED_WASTELAND, BOPBiomes.WASTELAND,           null,                             null}
    };

    private final ResourceKey<Biome>[][] PLATEAU_BIOMES_BOP = new ResourceKey[][]{
            {BOPBiomes.COLD_DESERT,          BOPBiomes.MUSKEG,               BOPBiomes.MUSKEG,         BOPBiomes.SNOWY_MAPLE_WOODS, BOPBiomes.MAPLE_WOODS},
            {BOPBiomes.TUNDRA,               BOPBiomes.TUNDRA,               BOPBiomes.DEAD_FOREST,    BOPBiomes.DEAD_FOREST,       BOPBiomes.BOREAL_FOREST},
            {BOPBiomes.LAVENDER_FIELD,       BOPBiomes.LAVENDER_FIELD,       BOPBiomes.REDWOOD_FOREST, BOPBiomes.HIGHLAND,          BOPBiomes.HIGHLAND},
            {BOPBiomes.MEDITERRANEAN_FOREST, BOPBiomes.MEDITERRANEAN_FOREST, BOPBiomes.WOODLAND,       BOPBiomes.ROCKY_RAINFOREST,  BOPBiomes.RAINFOREST},
            {BOPBiomes.WASTELAND,            BOPBiomes.WASTELAND,            BOPBiomes.WASTELAND,      BOPBiomes.VOLCANIC_PLAINS,   BOPBiomes.VOLCANO}
    };

    private final ResourceKey<Biome>[][] PLATEAU_BIOMES_VARIANT_BOP = new ResourceKey[][]{
            {null,                      null, null,                             null,                   BOPBiomes.SNOWY_MAPLE_WOODS},
            {null,                      null, BOPBiomes.OLD_GROWTH_DEAD_FOREST, null,                   null},
            {BOPBiomes.LAVENDER_FOREST, null, BOPBiomes.ORCHARD,                BOPBiomes.BAMBOO_GROVE, BOPBiomes.HIGHLAND_MOOR},
            {null,                      null, BOPBiomes.OLD_GROWTH_WOODLAND,    null,                   BOPBiomes.ROCKY_RAINFOREST},
            {null,                      null, BOPBiomes.WOODED_WASTELAND,       BOPBiomes.VOLCANO,      null}
    };

    @Override
    protected ResourceKey<Biome> pickMiddleBiomeBOP(Registry<Biome> biomeRegistry, int temperatureIndex, int humidityIndex, Climate.Parameter weirdness)
    {
        ResourceKey<Biome> middleBiome = BiomeUtil.biomeOrFallback(biomeRegistry, this.MIDDLE_BIOMES_BOP[temperatureIndex][humidityIndex], this.MIDDLE_BIOMES[temperatureIndex][humidityIndex]);

        if (weirdness.max() < 0) return middleBiome;
        else
        {
            return BiomeUtil.biomeOrFallback(biomeRegistry, this.MIDDLE_BIOMES_VARIANT_BOP[temperatureIndex][humidityIndex], middleBiome);
        }
    }

    @Override
    protected ResourceKey<Biome> pickPlateauBiomeBOP(Registry<Biome> biomeRegistry, int temperatureIndex, int humidityIndex, Climate.Parameter weirdness)
    {
        if (weirdness.max() < 0L) return BiomeUtil.biomeOrFallback(biomeRegistry, this.PLATEAU_BIOMES_BOP[temperatureIndex][humidityIndex], this.PLATEAU_BIOMES[temperatureIndex][humidityIndex]);
        else return BiomeUtil.biomeOrFallback(biomeRegistry, this.PLATEAU_BIOMES_VARIANT_BOP[temperatureIndex][humidityIndex], this.PLATEAU_BIOMES_BOP[temperatureIndex][humidityIndex], this.PLATEAU_BIOMES[temperatureIndex][humidityIndex]);
    }
}
