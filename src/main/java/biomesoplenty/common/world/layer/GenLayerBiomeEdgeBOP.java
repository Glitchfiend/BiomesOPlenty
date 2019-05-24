/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.world.layer;

import biomesoplenty.api.biome.BOPBiomes;
import net.minecraft.init.Biomes;
import net.minecraft.util.registry.IRegistry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.IContext;
import net.minecraft.world.gen.layer.LayerUtil;
import net.minecraft.world.gen.layer.traits.ICastleTransformer;

import java.util.Optional;

public enum GenLayerBiomeEdgeBOP implements ICastleTransformer
{
    INSTANCE;

    private static final int DESERT = IRegistry.BIOME.getId(Biomes.DESERT);
    private static final int MOUNTAINS = IRegistry.BIOME.getId(Biomes.MOUNTAINS);
    private static final int WOODED_MOUNTAINS = IRegistry.BIOME.getId(Biomes.WOODED_MOUNTAINS);
    private static final int SNOWY_TUNDRA = IRegistry.BIOME.getId(Biomes.SNOWY_TUNDRA);
    private static final int JUNGLE = IRegistry.BIOME.getId(Biomes.JUNGLE);
    private static final int JUNGLE_EDGE = IRegistry.BIOME.getId(Biomes.JUNGLE_EDGE);
    private static final int BADLANDS = IRegistry.BIOME.getId(Biomes.BADLANDS);
    private static final int BADLANDS_PLATEAU = IRegistry.BIOME.getId(Biomes.BADLANDS_PLATEAU);
    private static final int WOODED_BADLANDS_PLATEAU = IRegistry.BIOME.getId(Biomes.WOODED_BADLANDS_PLATEAU);
    private static final int PLAINS = IRegistry.BIOME.getId(Biomes.PLAINS);
    private static final int GIANT_TREE_TAIGA = IRegistry.BIOME.getId(Biomes.GIANT_TREE_TAIGA);
    private static final int MOUNTAIN_EDGE = IRegistry.BIOME.getId(Biomes.MOUNTAIN_EDGE);
    private static final int SWAMP = IRegistry.BIOME.getId(Biomes.SWAMP);
    private static final int TAIGA = IRegistry.BIOME.getId(Biomes.TAIGA);
    private static final int SNOWY_TAIGA = IRegistry.BIOME.getId(Biomes.SNOWY_TAIGA);

    @Override
    public int apply(IContext context, int northBiomeId, int eastBiomeId, int southBiomeId, int westBiomeId, int biomeId)
    {
        int[] outBiomeId = new int[1];

        // line BOP alps peaks with BOP alps foothills
        if (this.replaceBiomeEdge(outBiomeId, northBiomeId, eastBiomeId, southBiomeId, westBiomeId, biomeId,  BOPBiomes.alps, BOPBiomes.alps_foothills)) { return outBiomeId[0]; }

        // line BOP redwood forest with BOP redwood forest edge
        if (this.replaceBiomeEdge(outBiomeId, northBiomeId, eastBiomeId, southBiomeId, westBiomeId, biomeId, BOPBiomes.redwood_forest, BOPBiomes.redwood_forest_edge)) { return outBiomeId[0]; }

        // line mountains with mountain edges
        if (this.replaceBiomeEdgeIfNecessary(outBiomeId, northBiomeId, eastBiomeId, southBiomeId, westBiomeId, biomeId, MOUNTAINS, MOUNTAIN_EDGE)) { return outBiomeId[0]; }

        // line special badlands with badlands
        if (this.replaceBiomeEdge(outBiomeId, northBiomeId, eastBiomeId, southBiomeId, westBiomeId, biomeId, WOODED_BADLANDS_PLATEAU, BADLANDS)) { return outBiomeId[0]; }
        if (this.replaceBiomeEdge(outBiomeId, northBiomeId, eastBiomeId, southBiomeId, westBiomeId, biomeId, BADLANDS_PLATEAU, BADLANDS)) { return outBiomeId[0]; }

        // line the giant tree taiga with taiga
        if (this.replaceBiomeEdge(outBiomeId, northBiomeId, eastBiomeId, southBiomeId, westBiomeId, biomeId, GIANT_TREE_TAIGA, TAIGA)) { return outBiomeId[0]; }

        if (biomeId == DESERT && (northBiomeId == SNOWY_TUNDRA || eastBiomeId == SNOWY_TUNDRA || westBiomeId == SNOWY_TUNDRA || southBiomeId == SNOWY_TUNDRA))
        {
            return WOODED_MOUNTAINS;
        }
        else
        {
            if (biomeId == SWAMP)
            {
                if (northBiomeId == DESERT || eastBiomeId == DESERT || westBiomeId == DESERT || southBiomeId == DESERT || northBiomeId == SNOWY_TAIGA || eastBiomeId == SNOWY_TAIGA || westBiomeId == SNOWY_TAIGA || southBiomeId == SNOWY_TAIGA || northBiomeId == SNOWY_TUNDRA || eastBiomeId == SNOWY_TUNDRA || westBiomeId == SNOWY_TUNDRA || southBiomeId == SNOWY_TUNDRA)
                {
                    return PLAINS;
                }

                if (northBiomeId == JUNGLE || southBiomeId == JUNGLE || eastBiomeId == JUNGLE || westBiomeId == JUNGLE)
                {
                    return JUNGLE_EDGE;
                }
            }
        }

        return biomeId;
    }

    private boolean replaceBiomeEdgeIfNecessary(int[] outId, int northBiomeId, int southBiomeId, int eastBiomeId, int westBiomeId, int biomeId, int fromBiome, int toBiome)
    {
        if (!LayerUtil.areBiomesSimilar(biomeId, fromBiome))
        {
            return false;
        }
        else
        {
            if (this.canBiomesBeNeighbors(northBiomeId, fromBiome) && this.canBiomesBeNeighbors(southBiomeId, fromBiome) && this.canBiomesBeNeighbors(westBiomeId, fromBiome) && this.canBiomesBeNeighbors(eastBiomeId, fromBiome))
            {
                outId[0] = biomeId;
            }
            else
            {
                outId[0] = toBiome;
            }

            return true;
        }
    }

    private boolean replaceBiomeEdge(int[] outId, int northBiomeId, int eastBiomeId, int southBiomeId, int westBiomeId, int biomeId, Optional<Biome> fromBiome, Optional<Biome> toBiome)
    {
        return fromBiome.isPresent() && toBiome.isPresent() && this.replaceBiomeEdge(outId, northBiomeId, eastBiomeId, southBiomeId, westBiomeId, biomeId, IRegistry.BIOME.getId(fromBiome.get()), IRegistry.BIOME.getId(toBiome.get()));
    }

    private boolean replaceBiomeEdge(int[] outId, int northBiomeId, int eastBiomeId, int southBiomeId, int westBiomeId, int biomeId, int fromBiome, int toBiome)
    {
        if (biomeId != fromBiome)
        {
            return false;
        }
        else
        {
            if (LayerUtil.areBiomesSimilar(northBiomeId, fromBiome) && LayerUtil.areBiomesSimilar(eastBiomeId, fromBiome) && LayerUtil.areBiomesSimilar(westBiomeId, fromBiome) && LayerUtil.areBiomesSimilar(southBiomeId, fromBiome))
            {
                outId[0] = biomeId;
            }
            else
            {
                outId[0] = toBiome;
            }

            return true;
        }
    }

    private boolean canBiomesBeNeighbors(int biomeIdA, int biomeIdB)
    {
        if (LayerUtil.areBiomesSimilar(biomeIdA, biomeIdB))
        {
            return true;
        }
        else
        {
            Biome biomeA = IRegistry.BIOME.get(biomeIdA);
            Biome biomeB = IRegistry.BIOME.get(biomeIdB);
            if (biomeA != null && biomeB != null)
            {
                Biome.TempCategory catA = biomeA.getTempCategory();
                Biome.TempCategory catB = biomeB.getTempCategory();
                return catA == catB || catA == Biome.TempCategory.MEDIUM || catB == Biome.TempCategory.MEDIUM;
            }
            else
            {
                return false;
            }
        }
    }
}
