/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.world.layer;

import com.google.common.base.Optional;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.api.generation.BOPGenLayer;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerBiomeEdgeBOP extends BOPGenLayer
{

    public GenLayerBiomeEdgeBOP(long seed, GenLayer parent)
    {
        super(seed);
        this.parent = parent;
    }

    @Override
    public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight)
    {
        int[] parentVals = this.parent.getInts(areaX - 1, areaY - 1, areaWidth + 2, areaHeight + 2);
        int[] out = IntCache.getIntCache(areaWidth * areaHeight);

        for (int y = 0; y < areaHeight; ++y)
        {
            for (int x = 0; x < areaWidth; ++x)
            {
                this.initChunkSeed((long)(x + areaX), (long)(y + areaY));
                int biomeId = parentVals[x + 1 + (y + 1) * (areaWidth + 2)];
                
                // line BOP mountain peaks with BOP mountain foothills
                if (this.replaceBiomeEdge(parentVals, out, x, y, areaWidth, biomeId, BOPBiomes.mountain, BOPBiomes.mountain_foothills)) {continue;}
                
                // line BOP wasteland with vanilla desert
                if (this.replaceBiomeEdge(parentVals, out, x, y, areaWidth, biomeId, BOPBiomes.wasteland, Optional.of(Biomes.DESERT))) {continue;}
                
                // line extreme hills with extreme hills edge
                if (this.replaceBiomeEdgeIfNecessary(parentVals, out, x, y, areaWidth, biomeId, Biome.getIdForBiome(Biomes.EXTREME_HILLS), Biome.getIdForBiome(Biomes.EXTREME_HILLS_EDGE))) {continue;}
                
                // line mesa plateau with mesa
                if (this.replaceBiomeEdge(parentVals, out, x, y, areaWidth, biomeId, Biome.getIdForBiome(Biomes.MESA_ROCK), Biome.getIdForBiome(Biomes.MESA))) {continue;}
                if (this.replaceBiomeEdge(parentVals, out, x, y, areaWidth, biomeId, Biome.getIdForBiome(Biomes.MESA_CLEAR_ROCK), Biome.getIdForBiome(Biomes.MESA))) {continue;}
                
                // line mega taiga with ordinary taiga
                if (this.replaceBiomeEdge(parentVals, out, x, y, areaWidth, biomeId, Biome.getIdForBiome(Biomes.REDWOOD_TAIGA), Biome.getIdForBiome(Biomes.TAIGA))) {continue;}
                
                int northBiomeId;
                int eastBiomeId;
                int westBiomeId;
                int southBiomeId;

                if (biomeId == Biome.getIdForBiome(Biomes.DESERT))
                {
                    // if desert is next to ice plains turn it into extremeGillsPlus (separate the ice and desert with a big mountain)
                    northBiomeId = parentVals[x + 1 + (y + 1 - 1) * (areaWidth + 2)];
                    eastBiomeId = parentVals[x + 1 + 1 + (y + 1) * (areaWidth + 2)];
                    westBiomeId = parentVals[x + 1 - 1 + (y + 1) * (areaWidth + 2)];
                    southBiomeId = parentVals[x + 1 + (y + 1 + 1) * (areaWidth + 2)];

                    if (northBiomeId != Biome.getIdForBiome(Biomes.ICE_PLAINS) && eastBiomeId != Biome.getIdForBiome(Biomes.ICE_PLAINS) && westBiomeId != Biome.getIdForBiome(Biomes.ICE_PLAINS) && southBiomeId != Biome.getIdForBiome(Biomes.ICE_PLAINS))
                    {
                        out[x + y * areaWidth] = biomeId;
                    }
                    else
                    {
                        out[x + y * areaWidth] = Biome.getIdForBiome(Biomes.EXTREME_HILLS_WITH_TREES);
                    }
                }
                else if (biomeId == Biome.getIdForBiome(Biomes.SWAMPLAND))
                {
                    // if swamp is next to desert, cold taiga or ice planes, turn it into plains
                    // if swamp is next to jungle, turn it into jungle edge
                    northBiomeId = parentVals[x + 1 + (y + 1 - 1) * (areaWidth + 2)];
                    eastBiomeId = parentVals[x + 1 + 1 + (y + 1) * (areaWidth + 2)];
                    westBiomeId = parentVals[x + 1 - 1 + (y + 1) * (areaWidth + 2)];
                    southBiomeId = parentVals[x + 1 + (y + 1 + 1) * (areaWidth + 2)];

                    if (northBiomeId != Biome.getIdForBiome(Biomes.DESERT) && eastBiomeId != Biome.getIdForBiome(Biomes.DESERT) && westBiomeId != Biome.getIdForBiome(Biomes.DESERT) && southBiomeId != Biome.getIdForBiome(Biomes.DESERT) && northBiomeId != Biome.getIdForBiome(Biomes.COLD_TAIGA) && eastBiomeId != Biome.getIdForBiome(Biomes.COLD_TAIGA) && westBiomeId != Biome.getIdForBiome(Biomes.COLD_TAIGA) && southBiomeId != Biome.getIdForBiome(Biomes.COLD_TAIGA) && northBiomeId != Biome.getIdForBiome(Biomes.ICE_PLAINS) && eastBiomeId != Biome.getIdForBiome(Biomes.ICE_PLAINS) && westBiomeId != Biome.getIdForBiome(Biomes.ICE_PLAINS) && southBiomeId != Biome.getIdForBiome(Biomes.ICE_PLAINS))
                    {
                        if (northBiomeId != Biome.getIdForBiome(Biomes.JUNGLE) && southBiomeId != Biome.getIdForBiome(Biomes.JUNGLE) && eastBiomeId != Biome.getIdForBiome(Biomes.JUNGLE) && westBiomeId != Biome.getIdForBiome(Biomes.JUNGLE))
                        {
                            out[x + y * areaWidth] = biomeId;
                        }
                        else
                        {
                            out[x + y * areaWidth] = Biome.getIdForBiome(Biomes.JUNGLE_EDGE);
                        }
                    }
                    else
                    {
                        out[x + y * areaWidth] = Biome.getIdForBiome(Biomes.PLAINS);
                    }
                }
                else
                {
                    out[x + y * areaWidth] = biomeId;
                }
            }
        }

        return out;
    }

    private boolean replaceBiomeEdgeIfNecessary(int[] parentVals, int[] out, int x, int y, int areaWidth, int biomeId, int fromBiomeId, int toBiomeId)
    {
        if (!biomesEqualOrMesaPlateau(biomeId, fromBiomeId))
        {
            return false;
        }
        else
        {
            int northBiomeId = parentVals[x + 1 + (y + 1 - 1) * (areaWidth + 2)];
            int eastBiomeId = parentVals[x + 1 + 1 + (y + 1) * (areaWidth + 2)];
            int westBiomeId = parentVals[x + 1 - 1 + (y + 1) * (areaWidth + 2)];
            int southBiomeId = parentVals[x + 1 + (y + 1 + 1) * (areaWidth + 2)];

            if (this.canBiomesBeNeighbors(northBiomeId, fromBiomeId) && this.canBiomesBeNeighbors(eastBiomeId, fromBiomeId) && this.canBiomesBeNeighbors(westBiomeId, fromBiomeId) && this.canBiomesBeNeighbors(southBiomeId, fromBiomeId))
            {
                out[x + y * areaWidth] = biomeId;
            }
            else
            {
                out[x + y * areaWidth] = toBiomeId;
            }

            return true;
        }
    }

    private boolean replaceBiomeEdge(int[] parentVals, int[] out, int x, int y, int areaWidth, int biomeId, Optional<Biome> fromBiome, Optional<Biome> toBiome)
    {
        return fromBiome.isPresent() && toBiome.isPresent() && this.replaceBiomeEdge(parentVals, out, x, y, areaWidth, biomeId, Biome.getIdForBiome(fromBiome.get()), Biome.getIdForBiome(toBiome.get()));
    }
    
    private boolean replaceBiomeEdge(int[] parentVals, int[] out, int x, int y, int areaWidth, int biomeId, int fromBiomeId, int toBiomeId)
    {
        if (biomeId != fromBiomeId)
        {
            return false;
        }
        else
        {
            int northBiomeId = parentVals[x + 1 + (y + 1 - 1) * (areaWidth + 2)];
            int eastBiomeId = parentVals[x + 1 + 1 + (y + 1) * (areaWidth + 2)];
            int westBiomeId = parentVals[x + 1 - 1 + (y + 1) * (areaWidth + 2)];
            int southBiomeId = parentVals[x + 1 + (y + 1 + 1) * (areaWidth + 2)];

            if (biomesEqualOrMesaPlateau(northBiomeId, fromBiomeId) && biomesEqualOrMesaPlateau(eastBiomeId, fromBiomeId) && biomesEqualOrMesaPlateau(westBiomeId, fromBiomeId) && biomesEqualOrMesaPlateau(southBiomeId, fromBiomeId))
            {
                out[x + y * areaWidth] = biomeId;
            }
            else
            {
                out[x + y * areaWidth] = toBiomeId;
            }

            return true;
        }
    }

    private boolean canBiomesBeNeighbors(int biomeA, int biomeB)
    {
        if (biomesEqualOrMesaPlateau(biomeA, biomeB))
        {
            return true;
        }
        else
        {
            Biome BiomeA = Biome.getBiome(biomeA);
            Biome BiomeB = Biome.getBiome(biomeB);

            if (BiomeA != null && BiomeB != null)
            {
            	Biome.TempCategory tempcategory = BiomeA.getTempCategory();
                Biome.TempCategory tempcategory1 = BiomeB.getTempCategory();
                return tempcategory == tempcategory1 || tempcategory == Biome.TempCategory.MEDIUM || tempcategory1 == Biome.TempCategory.MEDIUM;
            }
            else
            {
                return false;
            }
        }
    }
}