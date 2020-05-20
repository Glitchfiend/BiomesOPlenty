/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.world.layer;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.common.util.biome.BiomeUtil;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.LayerUtil;
import net.minecraft.world.gen.layer.traits.ICastleTransformer;

import java.util.Optional;

public enum BOPBiomeEdgeLayer implements ICastleTransformer
{
    INSTANCE;

    private static final int DESERT = BiomeUtil.GetBiomeIDFromRegistry(Biomes.DESERT.getRegistryName());
    private static final int MOUNTAINS = BiomeUtil.GetBiomeIDFromRegistry(Biomes.MOUNTAINS.getRegistryName());
    private static final int WOODED_MOUNTAINS = BiomeUtil.GetBiomeIDFromRegistry(Biomes.WOODED_MOUNTAINS.getRegistryName());
    private static final int SNOWY_TUNDRA = BiomeUtil.GetBiomeIDFromRegistry(Biomes.SNOWY_TUNDRA.getRegistryName());
    private static final int JUNGLE = BiomeUtil.GetBiomeIDFromRegistry(Biomes.JUNGLE.getRegistryName());
    private static final int JUNGLE_HILLS = BiomeUtil.GetBiomeIDFromRegistry(Biomes.JUNGLE_HILLS.getRegistryName());
    private static final int JUNGLE_EDGE = BiomeUtil.GetBiomeIDFromRegistry(Biomes.JUNGLE_EDGE.getRegistryName());
    private static final int BADLANDS = BiomeUtil.GetBiomeIDFromRegistry(Biomes.BADLANDS.getRegistryName());
    private static final int BADLANDS_PLATEAU = BiomeUtil.GetBiomeIDFromRegistry(Biomes.BADLANDS_PLATEAU.getRegistryName());
    private static final int WOODED_BADLANDS_PLATEAU = BiomeUtil.GetBiomeIDFromRegistry(Biomes.WOODED_BADLANDS_PLATEAU.getRegistryName());
    private static final int PLAINS = BiomeUtil.GetBiomeIDFromRegistry(Biomes.PLAINS.getRegistryName());
    private static final int GIANT_TREE_TAIGA = BiomeUtil.GetBiomeIDFromRegistry(Biomes.GIANT_TREE_TAIGA.getRegistryName());
    private static final int MOUNTAIN_EDGE = BiomeUtil.GetBiomeIDFromRegistry(Biomes.MOUNTAIN_EDGE.getRegistryName());
    private static final int SWAMP = BiomeUtil.GetBiomeIDFromRegistry(Biomes.SWAMP.getRegistryName());
    private static final int TAIGA = BiomeUtil.GetBiomeIDFromRegistry(Biomes.TAIGA.getRegistryName());
    private static final int SNOWY_TAIGA = BiomeUtil.GetBiomeIDFromRegistry(Biomes.SNOWY_TAIGA.getRegistryName());

    @Override
    public int apply(INoiseRandom context, int northBiomeId, int eastBiomeId, int southBiomeId, int westBiomeId, int biomeId)
    {
        int[] outBiomeId = new int[1];

        // line BOP alps peaks with BOP alps foothills
        if (this.replaceBiomeEdge(outBiomeId, northBiomeId, eastBiomeId, southBiomeId, westBiomeId, biomeId,  BOPBiomes.alps, BOPBiomes.alps_foothills)) { return outBiomeId[0]; }

        // line BOP redwood forest with BOP redwood forest edge
        if (this.replaceBiomeEdge(outBiomeId, northBiomeId, eastBiomeId, southBiomeId, westBiomeId, biomeId, BOPBiomes.redwood_forest, BOPBiomes.redwood_forest_edge)) { return outBiomeId[0]; }

        // line BOP volcano with BOP volcano edge
        if (this.replaceBiomeEdge(outBiomeId, northBiomeId, eastBiomeId, southBiomeId, westBiomeId, biomeId, BOPBiomes.volcano, BOPBiomes.volcano_edge)) { return outBiomeId[0]; }

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

                if (northBiomeId == JUNGLE || southBiomeId == JUNGLE || eastBiomeId == JUNGLE || westBiomeId == JUNGLE || northBiomeId == JUNGLE_HILLS || southBiomeId == JUNGLE_HILLS || eastBiomeId == JUNGLE_HILLS || westBiomeId == JUNGLE_HILLS)
                {
                    return JUNGLE_EDGE;
                }
            }
        }

        return biomeId;
    }

    private boolean replaceBiomeEdgeIfNecessary(int[] outId, int northBiomeId, int southBiomeId, int eastBiomeId, int westBiomeId, int biomeId, int fromBiome, int toBiome)
    {
        if (!LayerUtil.isSame(biomeId, fromBiome))
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
        return fromBiome.isPresent() && toBiome.isPresent() && this.replaceBiomeEdge(outId, northBiomeId, eastBiomeId, southBiomeId, westBiomeId, biomeId, BiomeUtil.GetBiomeIDFromRegistry(fromBiome.get().getRegistryName()), BiomeUtil.GetBiomeIDFromRegistry(toBiome.get().getRegistryName()));
    }
    
    @SuppressWarnings("unused")
	private boolean replaceBiomeEdge(int[] outId, int northBiomeId, int eastBiomeId, int southBiomeId, int westBiomeId, int biomeId, Optional<Biome> fromBiome, int toBiome)
    {
        return fromBiome.isPresent() && this.replaceBiomeEdge(outId, northBiomeId, eastBiomeId, southBiomeId, westBiomeId, biomeId, BiomeUtil.GetBiomeIDFromRegistry(fromBiome.get().getRegistryName()), toBiome);
    }

    private boolean replaceBiomeEdge(int[] outId, int northBiomeId, int eastBiomeId, int southBiomeId, int westBiomeId, int biomeId, int fromBiome, int toBiome)
    {
        if (biomeId != fromBiome)
        {
            return false;
        }
        else
        {
            if (LayerUtil.isSame(northBiomeId, fromBiome) && LayerUtil.isSame(eastBiomeId, fromBiome) && LayerUtil.isSame(westBiomeId, fromBiome) && LayerUtil.isSame(southBiomeId, fromBiome))
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
        if (LayerUtil.isSame(biomeIdA, biomeIdB))
        {
            return true;
        }
        else
        {
            Biome biomeA = BiomeUtil.GetBiomeFromID(biomeIdA);
            Biome biomeB = BiomeUtil.GetBiomeFromID(biomeIdB);
            if (biomeA != null && biomeB != null)
            {
                Biome.TempCategory catA = biomeA.getTemperatureCategory();
                Biome.TempCategory catB = biomeB.getTemperatureCategory();
                return catA == catB || catA == Biome.TempCategory.MEDIUM || catB == Biome.TempCategory.MEDIUM;
            }
            else
            {
                return false;
            }
        }
    }
}
