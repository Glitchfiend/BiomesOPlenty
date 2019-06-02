/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.world.layer;

import biomesoplenty.common.biome.BiomeBOP;
import biomesoplenty.common.world.BOPLayerUtil;
import net.minecraft.init.Biomes;
import net.minecraft.util.registry.IRegistry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.IContext;
import net.minecraft.world.gen.layer.traits.ICastleTransformer;

public enum GenLayerShoreBOP implements ICastleTransformer
{
    INSTANCE;

    private static final int BEACH = IRegistry.BIOME.getId(Biomes.BEACH);
    private static final int SNOWY_BEACH = IRegistry.BIOME.getId(Biomes.SNOWY_BEACH);
    private static final int DESERT = IRegistry.BIOME.getId(Biomes.DESERT);
    private static final int MOUNTAINS = IRegistry.BIOME.getId(Biomes.MOUNTAINS);
    private static final int WOODED_MOUNTAINS = IRegistry.BIOME.getId(Biomes.WOODED_MOUNTAINS);
    private static final int FOREST = IRegistry.BIOME.getId(Biomes.FOREST);
    private static final int JUNGLE = IRegistry.BIOME.getId(Biomes.JUNGLE);
    private static final int JUNGLE_EDGE = IRegistry.BIOME.getId(Biomes.JUNGLE_EDGE);
    private static final int JUNGLE_HILLS = IRegistry.BIOME.getId(Biomes.JUNGLE_HILLS);
    private static final int BADLANDS = IRegistry.BIOME.getId(Biomes.BADLANDS);
    private static final int WOODED_BADLANDS_PLATEAU = IRegistry.BIOME.getId(Biomes.WOODED_BADLANDS_PLATEAU);
    private static final int BADLANDS_PLATEAU = IRegistry.BIOME.getId(Biomes.BADLANDS_PLATEAU);
    private static final int ERODED_BADLANDS = IRegistry.BIOME.getId(Biomes.ERODED_BADLANDS);
    private static final int MODIFIED_WOODED_BADLANDS_PLATEAU = IRegistry.BIOME.getId(Biomes.MODIFIED_WOODED_BADLANDS_PLATEAU);
    private static final int MODIFIED_BADLANDS_PLATEAU = IRegistry.BIOME.getId(Biomes.MODIFIED_BADLANDS_PLATEAU);
    private static final int MUSHROOM_FIELDS = IRegistry.BIOME.getId(Biomes.MUSHROOM_FIELDS);
    private static final int MUSHROOM_FIELD_SHORE = IRegistry.BIOME.getId(Biomes.MUSHROOM_FIELD_SHORE);
    private static final int RIVER = IRegistry.BIOME.getId(Biomes.RIVER);
    private static final int MOUNTAIN_EDGE = IRegistry.BIOME.getId(Biomes.MOUNTAIN_EDGE);
    private static final int STONE_SHORE = IRegistry.BIOME.getId(Biomes.STONE_SHORE);
    private static final int SWAMP = IRegistry.BIOME.getId(Biomes.SWAMP);
    private static final int TAIGA = IRegistry.BIOME.getId(Biomes.TAIGA);

    @Override
    public int apply(IContext context, int northBiomeId, int eastBiomeId, int southBiomeId, int westBiomeId, int biomeId)
    {
        Biome biome = IRegistry.BIOME.get(biomeId);

        if (biomeId == MUSHROOM_FIELDS)
        {
            if (BOPLayerUtil.isShallowOcean(northBiomeId) || BOPLayerUtil.isShallowOcean(eastBiomeId) || BOPLayerUtil.isShallowOcean(southBiomeId) || BOPLayerUtil.isShallowOcean(westBiomeId))
            {
                return MUSHROOM_FIELD_SHORE;
            }
        }
        else if (biome != null && biome.getCategory() == Biome.Category.JUNGLE)
        {
            if (!isJungleCompatible(northBiomeId) || !isJungleCompatible(eastBiomeId) || !isJungleCompatible(southBiomeId) || !isJungleCompatible(westBiomeId))
            {
                return JUNGLE_EDGE;
            }

            if (BOPLayerUtil.isOcean(northBiomeId) || BOPLayerUtil.isOcean(eastBiomeId) || BOPLayerUtil.isOcean(southBiomeId) || BOPLayerUtil.isOcean(westBiomeId))
            {
                return BEACH;
            }
        }
        else if (biomeId != MOUNTAINS && biomeId != WOODED_MOUNTAINS && biomeId != MOUNTAIN_EDGE)
        {
            if (biome != null && biome.getPrecipitation() == Biome.RainType.SNOW)
            {
                if (!BOPLayerUtil.isOcean(biomeId) && (BOPLayerUtil.isOcean(northBiomeId) || BOPLayerUtil.isOcean(eastBiomeId) || BOPLayerUtil.isOcean(southBiomeId) || BOPLayerUtil.isOcean(westBiomeId)))
                {
                    return SNOWY_BEACH;
                }
            }
            else if (biomeId != BADLANDS && biomeId != WOODED_BADLANDS_PLATEAU)
            {
                if (!BOPLayerUtil.isOcean(biomeId) && biomeId != RIVER && biomeId != SWAMP && (BOPLayerUtil.isOcean(northBiomeId) || BOPLayerUtil.isOcean(eastBiomeId) || BOPLayerUtil.isOcean(southBiomeId) || BOPLayerUtil.isOcean(westBiomeId)))
                {
                    if (biome instanceof BiomeBOP)
                    {
                        BiomeBOP biomeBOP = (BiomeBOP)biome;

                        if (biomeBOP.beachBiomeId != -1)
                            return biomeBOP.beachBiomeId;
                        else
                            return biomeId;
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

    private static boolean isJungleCompatible(int biomeId)
    {
        if (IRegistry.BIOME.get(biomeId) != null && (IRegistry.BIOME.get(biomeId)).getCategory() == Biome.Category.JUNGLE)
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
