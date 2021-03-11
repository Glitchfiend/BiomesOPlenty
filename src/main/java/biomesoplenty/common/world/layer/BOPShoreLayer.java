/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.world.layer;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.common.biome.BiomeMetadata;
import biomesoplenty.common.biome.BiomeTemplate;
import biomesoplenty.common.util.biome.BiomeUtil;
import biomesoplenty.common.world.BOPLayerUtil;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.ICastleTransformer;

import java.util.Optional;

public enum BOPShoreLayer implements ICastleTransformer
{
    INSTANCE;

    private static final int BEACH = BiomeUtil.getBiomeId(Biomes.BEACH);
    private static final int SNOWY_BEACH = BiomeUtil.getBiomeId(Biomes.SNOWY_BEACH);
    private static final int DESERT = BiomeUtil.getBiomeId(Biomes.DESERT);
    private static final int MOUNTAINS = BiomeUtil.getBiomeId(Biomes.MOUNTAINS);
    private static final int WOODED_MOUNTAINS = BiomeUtil.getBiomeId(Biomes.WOODED_MOUNTAINS);
    private static final int FOREST = BiomeUtil.getBiomeId(Biomes.FOREST);
    private static final int JUNGLE = BiomeUtil.getBiomeId(Biomes.JUNGLE);
    private static final int JUNGLE_EDGE = BiomeUtil.getBiomeId(Biomes.JUNGLE_EDGE);
    private static final int JUNGLE_HILLS = BiomeUtil.getBiomeId(Biomes.JUNGLE_HILLS);
    private static final int BADLANDS = BiomeUtil.getBiomeId(Biomes.BADLANDS);
    private static final int WOODED_BADLANDS_PLATEAU = BiomeUtil.getBiomeId(Biomes.WOODED_BADLANDS_PLATEAU);
    private static final int BADLANDS_PLATEAU = BiomeUtil.getBiomeId(Biomes.BADLANDS_PLATEAU);
    private static final int ERODED_BADLANDS = BiomeUtil.getBiomeId(Biomes.ERODED_BADLANDS);
    private static final int MODIFIED_WOODED_BADLANDS_PLATEAU = BiomeUtil.getBiomeId(Biomes.MODIFIED_WOODED_BADLANDS_PLATEAU);
    private static final int MODIFIED_BADLANDS_PLATEAU = BiomeUtil.getBiomeId(Biomes.MODIFIED_BADLANDS_PLATEAU);
    private static final int MUSHROOM_FIELDS = BiomeUtil.getBiomeId(Biomes.MUSHROOM_FIELDS);
    private static final int MUSHROOM_FIELD_SHORE = BiomeUtil.getBiomeId(Biomes.MUSHROOM_FIELD_SHORE);
    private static final int RIVER = BiomeUtil.getBiomeId(Biomes.RIVER);
    private static final int MOUNTAIN_EDGE = BiomeUtil.getBiomeId(Biomes.MOUNTAIN_EDGE);
    private static final int STONE_SHORE = BiomeUtil.getBiomeId(Biomes.STONE_SHORE);
    private static final int SWAMP = BiomeUtil.getBiomeId(Biomes.SWAMP);
    private static final int TAIGA = BiomeUtil.getBiomeId(Biomes.TAIGA);

    @Override
    public int apply(INoiseRandom context, int northBiomeId, int eastBiomeId, int southBiomeId, int westBiomeId, int biomeId)
    {
        Biome biome = BiomeUtil.getBiome(biomeId);
        RegistryKey<Biome> key = BiomeUtil.createKey(biome);

        if (biomeId == MUSHROOM_FIELDS)
        {
            if (BOPLayerUtil.isShallowOcean(northBiomeId) || BOPLayerUtil.isShallowOcean(eastBiomeId) || BOPLayerUtil.isShallowOcean(southBiomeId) || BOPLayerUtil.isShallowOcean(westBiomeId))
            {
                return MUSHROOM_FIELD_SHORE;
            }
        }
        else if (biome != null && biome.getBiomeCategory() == Biome.Category.JUNGLE && biomeId != getBiomeIdIfPresent(BOPBiomes.rainforest, biomeId) && biomeId != getBiomeIdIfPresent(BOPBiomes.rainforest_floodplain, biomeId) && biomeId != getBiomeIdIfPresent(BOPBiomes.rainforest_cliffs, biomeId) && biomeId != getBiomeIdIfPresent(BOPBiomes.fungal_jungle, biomeId) && biomeId != getBiomeIdIfPresent(BOPBiomes.fungal_field, biomeId) && biomeId != getBiomeIdIfPresent(BOPBiomes.overgrown_fungal_jungle, biomeId))
        {
            if (!isJungleCompatible(northBiomeId) || !isJungleCompatible(eastBiomeId) || !isJungleCompatible(southBiomeId) || !isJungleCompatible(westBiomeId))
            {
                return JUNGLE_EDGE;
            }
        }
        else if (biomeId != MOUNTAINS && biomeId != WOODED_MOUNTAINS && biomeId != MOUNTAIN_EDGE)
        {
            if (biome != null && biome.getPrecipitation() == Biome.RainType.SNOW)
            {
                if (!BOPLayerUtil.isOcean(biomeId) && (BOPLayerUtil.isOcean(northBiomeId) || BOPLayerUtil.isOcean(eastBiomeId) || BOPLayerUtil.isOcean(southBiomeId) || BOPLayerUtil.isOcean(westBiomeId)))
                {
                    if (BiomeUtil.hasMetadata(biome))
                    {
                        BiomeMetadata meta = BiomeUtil.getMetadata(biome);

                        if (meta.getBeachBiome() == null)
                            return biomeId;
                    }
                    
                    return SNOWY_BEACH;
                }
            }
            else if (biomeId != BADLANDS && biomeId != WOODED_BADLANDS_PLATEAU)
            {
                if (!BOPLayerUtil.isOcean(biomeId) && biomeId != RIVER && biomeId != SWAMP && (BOPLayerUtil.isOcean(northBiomeId) || BOPLayerUtil.isOcean(eastBiomeId) || BOPLayerUtil.isOcean(southBiomeId) || BOPLayerUtil.isOcean(westBiomeId)))
                {
                    if (BiomeUtil.hasMetadata(biome))
                    {
                        BiomeMetadata meta = BiomeUtil.getMetadata(biome);

                        if (meta.getBeachBiome() == null) return biomeId;
                        else return BiomeUtil.getBiomeId(meta.getBeachBiome());
                    }
                    else
                    {
                        if (key == Biomes.TAIGA || key == Biomes.TAIGA_MOUNTAINS || key == Biomes.TAIGA_HILLS || key == Biomes.GIANT_TREE_TAIGA || key == Biomes.GIANT_SPRUCE_TAIGA || key == Biomes.GIANT_TREE_TAIGA_HILLS || key == Biomes.GIANT_SPRUCE_TAIGA_HILLS || key == Biomes.BIRCH_FOREST_HILLS || key == Biomes.BIRCH_FOREST || key == Biomes.TALL_BIRCH_HILLS || key == Biomes.TALL_BIRCH_FOREST || key == Biomes.DARK_FOREST_HILLS || key == Biomes.DARK_FOREST)
                        {
                            return getBiomeIdIfPresent(BOPBiomes.gravel_beach, biomeId);
                        }
                    }

                    return BEACH;
                }
            }
            else if (!BOPLayerUtil.isOcean(northBiomeId) && !BOPLayerUtil.isOcean(eastBiomeId) && !BOPLayerUtil.isOcean(southBiomeId) && !BOPLayerUtil.isOcean(westBiomeId) && (!isMesa(northBiomeId) || !isMesa(eastBiomeId) || !isMesa(southBiomeId) || !isMesa(westBiomeId)))
            {
                return DESERT;
            }
        }
        else if (!BOPLayerUtil.isOcean(biomeId) && (BOPLayerUtil.isOcean(northBiomeId) || BOPLayerUtil.isOcean(eastBiomeId) || BOPLayerUtil.isOcean(southBiomeId) || BOPLayerUtil.isOcean(westBiomeId)))
        {
            return STONE_SHORE;
        }

        return biomeId;
    }

    private static int getBiomeIdIfPresent(RegistryKey<Biome> biome, int fallbackId)
    {
        return BiomeUtil.exists(biome) ? BiomeUtil.getBiomeId(biome) : fallbackId;
    }

    private static boolean isJungleCompatible(int biomeId)
    {
        Biome biome = BiomeUtil.getBiome(biomeId);

        if (biome != null && biome.getBiomeCategory() == Biome.Category.JUNGLE)
        {
            return true;
        }
        else
        {
            return biomeId == JUNGLE_EDGE || biomeId == JUNGLE || biomeId == JUNGLE_HILLS || biomeId == FOREST || biomeId == TAIGA || BOPLayerUtil.isOcean(biomeId);
        }
    }

    private static boolean isMesa(int biomeId)
    {
        return biomeId == BADLANDS || biomeId == WOODED_BADLANDS_PLATEAU || biomeId == BADLANDS_PLATEAU || biomeId == ERODED_BADLANDS || biomeId == MODIFIED_WOODED_BADLANDS_PLATEAU || biomeId == MODIFIED_BADLANDS_PLATEAU;
    }
}
