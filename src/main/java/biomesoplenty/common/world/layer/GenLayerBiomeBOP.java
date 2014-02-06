package biomesoplenty.common.world.layer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import net.minecraft.util.WeightedRandom;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerBiome;
import net.minecraft.world.gen.layer.IntCache;
import biomesoplenty.api.BOPBiomeHelper.BOPBiomeEntry;
import biomesoplenty.common.configuration.BOPConfigurationBiomeGen;

public class GenLayerBiomeBOP extends GenLayerBiome
{
    public static List<BOPBiomeEntry> desertBiomes = new ArrayList<BOPBiomeEntry>();
    public static List<BOPBiomeEntry> warmBiomes = new ArrayList<BOPBiomeEntry>();
    public static List<BOPBiomeEntry> coldBiomes = new ArrayList<BOPBiomeEntry>();
    public static List<BOPBiomeEntry> icyBiomes = new ArrayList<BOPBiomeEntry>();
	
	public GenLayerBiomeBOP(long seed, GenLayer parentLayer, WorldType worldType) 
	{
		super(seed, parentLayer, worldType);
	}

	
    @Override
	public int[] getInts(int par1, int par2, int par3, int par4)
    {
        int[] aint = this.parent.getInts(par1, par2, par3, par4);
        int[] aint1 = IntCache.getIntCache(par3 * par4);

        for (int i1 = 0; i1 < par4; ++i1)
        {
            for (int j1 = 0; j1 < par3; ++j1)
            {
                this.initChunkSeed((long)(j1 + par1), (long)(i1 + par2));
                int biomeID = aint[j1 + i1 * par3];
                //TODO:				111100000000
                int l1 = (biomeID & 3840) >> 8;
                biomeID &= -3841;

                if (isOceanBiome(biomeID))
                {
                    aint1[j1 + i1 * par3] = biomeID;
                }
                else if (biomeID == BiomeGenBase.mushroomIsland.biomeID && BOPConfigurationBiomeGen.mushroomIslandGen)
                {
                    aint1[j1 + i1 * par3] = biomeID;
                }
                else if (biomeID == 1)
                {
                    if (l1 > 0)
                    {
                        if (this.nextInt(3) == 0 && BOPConfigurationBiomeGen.mesaPlateauGen)
                        {
                        	//TODO:								 mesaPlateau
                            aint1[j1 + i1 * par3] = BiomeGenBase.mesaPlateau.biomeID;
                        }
                        else if (BOPConfigurationBiomeGen.mesaPlateauFGen)
                        {
                        	//TODO:								 mesaPlateau F
                            aint1[j1 + i1 * par3] = BiomeGenBase.mesaPlateau_F.biomeID;
                        }
                    }
                    else
                    {
                        aint1[j1 + i1 * par3] = getRandomBiome(desertBiomes).biome.biomeID;
                    }
                }
                else if (biomeID == 2)
                {
                    if (l1 > 0 && BOPConfigurationBiomeGen.jungleGen)
                    {
                        aint1[j1 + i1 * par3] = BiomeGenBase.jungle.biomeID;
                    }
                    else
                    {
                        aint1[j1 + i1 * par3] = getRandomBiome(warmBiomes).biome.biomeID;
                    }
                }
                else if (biomeID == 3)
                {
                    if (l1 > 0 && BOPConfigurationBiomeGen.megaTaigaGen)
                    {
                    	//TODO:								 megaTaiga
                        aint1[j1 + i1 * par3] = BiomeGenBase.megaTaiga.biomeID;
                    }
                    else
                    {
                        aint1[j1 + i1 * par3] = getRandomBiome(coldBiomes).biome.biomeID;
                    }
                }
                else if (biomeID == 4)
                {
                    aint1[j1 + i1 * par3] = getRandomBiome(icyBiomes).biome.biomeID;
                }
                else if (BOPConfigurationBiomeGen.mushroomIslandGen)
                {
                    aint1[j1 + i1 * par3] = BiomeGenBase.mushroomIsland.biomeID;
                }
            }
        }

        return aint1;
    }
    
    public BOPBiomeEntry getRandomBiome(Collection weightedItems)
    {
    	return getRandomBiome(weightedItems, WeightedRandom.getTotalWeight(weightedItems));
    }
    
    public BOPBiomeEntry getRandomBiome(Collection weightedItems, int totalWeight)
    {
        if (totalWeight <= 0)
        {
            throw new IllegalArgumentException();
        }
        else
        {
            int j = this.nextInt(totalWeight);
            Iterator iterator = weightedItems.iterator();
            BOPBiomeEntry item;

            do
            {
                if (!iterator.hasNext())
                {
                    return null;
                }

                item = (BOPBiomeEntry)iterator.next();
                j -= item.itemWeight;
            }
            while (j >= 0);

            return item;
        }
    }
    
    protected static boolean isOceanBiome(int biomeID)
    {
        return biomeID == BiomeGenBase.ocean.biomeID && BOPConfigurationBiomeGen.oceanGen || biomeID == BiomeGenBase.deepOcean.biomeID && BOPConfigurationBiomeGen.deepOceanGen || biomeID == BiomeGenBase.frozenOcean.biomeID && BOPConfigurationBiomeGen.frozenOceanGen;
    }
}
