/*******************************************************************************
 * Copyright 2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.world.layer;

import com.google.common.base.Predicate;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.api.biome.IExtendedBiome;
import biomesoplenty.api.generation.BOPGenLayer;
import biomesoplenty.common.util.biome.BiomeUtils;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeJungle;
import net.minecraft.world.biome.BiomeMesa;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerShoreBOP extends BOPGenLayer
{
    public GenLayerShoreBOP(long seed, GenLayer parent)
    {
        super(seed);
        this.parent = parent;
    }

    @Override
    public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight)
    {
        int[] biomeIds = this.parent.getInts(areaX - 1, areaY - 1, areaWidth + 2, areaHeight + 2);
        int[] out = IntCache.getIntCache(areaWidth * areaHeight);

        for (int z = 0; z < areaHeight; ++z)
        {
            for (int x = 0; x < areaWidth; ++x)
            {
                this.initChunkSeed((long)(x + areaX), (long)(z + areaY));
                //The biome we're going to attempt to put a beach beside
                int biomeId = biomeIds[x + 1 + (z + 1) * (areaWidth + 2)];
                Biome biome = Biome.getBiome(biomeId);

                if (biomeId == Biome.getIdForBiome(Biomes.MUSHROOM_ISLAND))
                {
                    setBiomeWithAdjacent(biomeIds, out, x, z, areaWidth, biomeId, Biome.getIdForBiome(Biomes.MUSHROOM_ISLAND_SHORE), OCEAN_PREDICATE);
                }
                else if (biome != null && biome.getBiomeClass() == BiomeJungle.class)
                {
                    int biomeNorth = biomeIds[x + 1 + (z + 1 - 1) * (areaWidth + 2)];
                    int biomeEast = biomeIds[x + 1 + 1 + (z + 1) * (areaWidth + 2)];
                    int biomeWest = biomeIds[x + 1 - 1 + (z + 1) * (areaWidth + 2)];
                    int biomeSouth = biomeIds[x + 1 + (z + 1 + 1) * (areaWidth + 2)];
                    
                    //Ensure the biomes surrounding the jungle are all suitable before generating a beach
                    if (JUNGLE_BORDER_PREDICATE.apply(biomeNorth) && JUNGLE_BORDER_PREDICATE.apply(biomeEast) && JUNGLE_BORDER_PREDICATE.apply(biomeWest) && JUNGLE_BORDER_PREDICATE.apply(biomeSouth))
                    {
                        setBiomeWithAdjacent(biomeIds, out, x, z, areaWidth, biomeId, Biome.getIdForBiome(Biomes.BEACH), OCEANIC_PREDICATE);
                    }
                    else //There is a non-jungle/ocean/taiga/forest next to the jungle, generate an edge biome
                    {
                        out[x + z * areaWidth] = Biome.getIdForBiome(Biomes.JUNGLE_EDGE);
                    }
                }
                else if (biomeId != Biome.getIdForBiome(Biomes.EXTREME_HILLS) && biomeId != Biome.getIdForBiome(Biomes.EXTREME_HILLS_WITH_TREES) && biomeId != Biome.getIdForBiome(Biomes.EXTREME_HILLS_EDGE))
                {
                    if (biome != null && biome.isSnowyBiome()) //Snowy biomes should have cold beaches
                    {
                        //Frozen ocean should not have a beach
                        if (isBiomeOceanic(biomeId))
                        {
                            out[x + z * areaWidth] = biomeId;
                        }
                        else
                        {
                            Biome beachBiome = null;
                            if (BOPBiomes.REG_INSTANCE.getExtendedBiome(biome) != null)
                            {
                                IExtendedBiome extBiome = BOPBiomes.REG_INSTANCE.getExtendedBiome(biome);
                                beachBiome = BiomeUtils.getBiomeForLoc(extBiome.getBeachLocation());
                            }
                            setBiomeWithAdjacent(biomeIds, out, x, z, areaWidth, biomeId, beachBiome == null ? biomeId : Biome.getIdForBiome(Biomes.COLD_BEACH), OCEANIC_PREDICATE);
                        }
                    }
                    else if (biomeId != Biome.getIdForBiome(Biomes.MESA) && biomeId != Biome.getIdForBiome(Biomes.MESA_ROCK))
                    {
                        if (biomeId != Biome.getIdForBiome(Biomes.OCEAN) && biomeId != Biome.getIdForBiome(Biomes.DEEP_OCEAN) && biomeId != Biome.getIdForBiome(Biomes.RIVER) && biomeId != Biome.getIdForBiome(Biomes.SWAMPLAND))
                        {
                            //Generate custom beaches for our biomes
                            if (biome != null && BOPBiomes.REG_INSTANCE.getExtendedBiome(biome) != null)
                            {
                                IExtendedBiome extBiome = BOPBiomes.REG_INSTANCE.getExtendedBiome(biome);
                                Biome beachBiome = BiomeUtils.getBiomeForLoc(extBiome.getBeachLocation());
                                setBiomeWithAdjacent(biomeIds, out, x, z, areaWidth, biomeId, beachBiome == null ? biomeId : Biome.getIdForBiome(beachBiome), OCEANIC_PREDICATE);
                            }
                            else
                            {
                                setBiomeWithAdjacent(biomeIds, out, x, z, areaWidth, biomeId, Biome.getIdForBiome(Biomes.BEACH), OCEANIC_PREDICATE);
                            }
                        }
                        else //Biome is watery, don't put any beaches next to it
                        {
                            out[x + z * areaWidth] = biomeId;
                        }
                    }
                    else //Biome is a variant of the mesa
                    {
                        int biomeNorth = biomeIds[x + 1 + (z + 1 - 1) * (areaWidth + 2)];
                        int biomeEast = biomeIds[x + 1 + 1 + (z + 1) * (areaWidth + 2)];
                        int biomeWest = biomeIds[x + 1 - 1 + (z + 1) * (areaWidth + 2)];
                        int biomeSouth = biomeIds[x + 1 + (z + 1 + 1) * (areaWidth + 2)];
                        
                        //Ensure that none of the surrounding biomes are ocean
                        if (!isBiomeOceanic(biomeNorth) && !isBiomeOceanic(biomeEast) && !isBiomeOceanic(biomeWest) && !isBiomeOceanic(biomeSouth))
                        {
                            //If at least one of the surrounding biomes is a non-mesa, set it to desert
                            setBiomeWithAdjacent(biomeIds, out, x, z, areaWidth, biomeId, Biome.getIdForBiome(Biomes.DESERT), MESA_PREDICATE);
                        }
                        else
                        {
                            out[x + z * areaWidth] = biomeId;
                        }
                    }
                }
                else //Biome is a variant of the extreme hills
                {
                    this.setBiomeWithAdjacent(biomeIds, out, x, z, areaWidth, biomeId, Biome.getIdForBiome(Biomes.STONE_BEACH), OCEANIC_PREDICATE);
                }
            }
        }

        return out;
    }

    private void setBiomeWithAdjacent(int[] biomeIds, int[] out, int x, int z, int areaWidth, int biomeId, int beachId, Predicate<Integer> adjacentPredicate)
    {
        int biomeNorth = biomeIds[x + 1 + (z + 1 - 1) * (areaWidth + 2)];
        int biomeEast = biomeIds[x + 1 + 1 + (z + 1) * (areaWidth + 2)];
        int biomeWest = biomeIds[x + 1 - 1 + (z + 1) * (areaWidth + 2)];
        int biomeSouth = biomeIds[x + 1 + (z + 1 + 1) * (areaWidth + 2)];

        if (adjacentPredicate.apply(biomeNorth) || adjacentPredicate.apply(biomeEast) || adjacentPredicate.apply(biomeWest) || adjacentPredicate.apply(biomeSouth))
        {
            out[x + z * areaWidth] = beachId;
        }
        else
        {
            out[x + z * areaWidth] = biomeId;
        }
    }
    
    private static final Predicate<Integer> OCEAN_PREDICATE = new Predicate<Integer>()
    {
        @Override
        public boolean apply(Integer input) 
        {
            return input == Biome.getIdForBiome(Biomes.OCEAN);
        }
    };
    
    private static final Predicate<Integer> OCEANIC_PREDICATE = new Predicate<Integer>()
    {
        @Override
        public boolean apply(Integer input) 
        {
            return isBiomeOceanic(input);
        }
    };
    
    private static final Predicate<Integer> JUNGLE_BORDER_PREDICATE = new Predicate<Integer>()
    {
        @Override
        public boolean apply(Integer input) 
        {
            return Biome.getBiome(input) != null && Biome.getBiome(input).getBiomeClass() == BiomeJungle.class || (input == Biome.getIdForBiome(Biomes.JUNGLE_EDGE) || input == Biome.getIdForBiome(Biomes.JUNGLE) || input == Biome.getIdForBiome(Biomes.JUNGLE_HILLS) || input == Biome.getIdForBiome(Biomes.FOREST) || input == Biome.getIdForBiome(Biomes.TAIGA) || isBiomeOceanic(input));
        }
    };
    
    private static final Predicate<Integer> MESA_PREDICATE = new Predicate<Integer>()
    {
        @Override
        public boolean apply(Integer input) 
        {
            return !(Biome.getBiome(input) instanceof BiomeMesa);
        }
    };
}
