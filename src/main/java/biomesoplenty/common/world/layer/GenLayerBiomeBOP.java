package biomesoplenty.common.world.layer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.minecraft.util.WeightedRandom;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerBiome;
import net.minecraft.world.gen.layer.IntCache;
import biomesoplenty.api.BOPBiomeManager;
import biomesoplenty.api.BOPBiomeManager.BiomeEntry;
import biomesoplenty.api.content.BOPCBiomes;
import biomesoplenty.common.configuration.BOPConfigurationBiomeGen;
import biomesoplenty.common.core.BOPBiomes;

public class GenLayerBiomeBOP extends GenLayerBiome
{
	//Desert, Warm, Cool, Icy
	public List<BiomeEntry>[] biomeLists = new ArrayList[] { new ArrayList(), new ArrayList(), new ArrayList(), new ArrayList() };
	
	public GenLayerBiomeBOP(long seed, GenLayer parentLayer, WorldType worldType) 
	{
		super(seed, parentLayer, worldType);
		
		this.biomeLists[0].addAll(BOPBiomeManager.overworldBiomes[0]);
		this.biomeLists[1].addAll(BOPBiomeManager.overworldBiomes[1]);
		this.biomeLists[2].addAll(BOPBiomeManager.overworldBiomes[2]);
		this.biomeLists[3].addAll(BOPBiomeManager.overworldBiomes[3]);
	}

	
    @Override
	public int[] getInts(int x, int z, int width, int length)
    {
        int[] inputBiomeIDs = this.parent.getInts(x, z, width, length);
        int[] outputBiomeIDs = IntCache.getIntCache(width * length);

        for (int i1 = 0; i1 < length; ++i1)
        {
            for (int j1 = 0; j1 < width; ++j1)
            {
                this.initChunkSeed((long)(j1 + x), (long)(i1 + z));
                int currentBiomeID = inputBiomeIDs[j1 + i1 * width];
                //   				111100000000
                int l1 = (currentBiomeID & 3840) >> 8;
                currentBiomeID &= -3841;
                
                if (BOPBiomes.onlyBiome != null)
                {
                	outputBiomeIDs[j1 + i1 * width] = BOPBiomes.onlyBiome.biomeID;
                	continue;
                }
                
                if (isBiomeOceanicAndEnabled(currentBiomeID))
                {
                    outputBiomeIDs[j1 + i1 * width] = currentBiomeID;
                }
                else if (currentBiomeID == BiomeGenBase.mushroomIsland.biomeID && BOPConfigurationBiomeGen.mushroomIslandGen)
                {
                    outputBiomeIDs[j1 + i1 * width] = currentBiomeID;
                }
                else if (currentBiomeID == 1)
                {
                    if (l1 > 0)
                    {
                        if (this.nextInt(3) == 0 && BOPConfigurationBiomeGen.mesaPlateauGen)
                        {
                            outputBiomeIDs[j1 + i1 * width] = BiomeGenBase.mesaPlateau.biomeID;
                        }
                        else if (BOPConfigurationBiomeGen.mesaPlateauFGen)
                        {
                            outputBiomeIDs[j1 + i1 * width] = BiomeGenBase.mesaPlateau_F.biomeID;
                        }
                        else
                        {
                            outputBiomeIDs[j1 + i1 * width] = getBiomeIdFromList(0);
                        }
                    }
                    else
                    {
                        outputBiomeIDs[j1 + i1 * width] = getBiomeIdFromList(0);
                    }
                }
                else if (currentBiomeID == 2)
                {
                    if (l1 > 0 && BOPConfigurationBiomeGen.jungleGen)
                    {
                        outputBiomeIDs[j1 + i1 * width] = BiomeGenBase.jungle.biomeID;
                    }
                    else
                    {
                        outputBiomeIDs[j1 + i1 * width] = getBiomeIdFromList(1);
                    }
                }
                else if (currentBiomeID == 3)
                {
                    if (l1 > 0 && BOPConfigurationBiomeGen.megaTaigaGen)
                    {
                        outputBiomeIDs[j1 + i1 * width] = BiomeGenBase.megaTaiga.biomeID;
                    }
                    else
                    {
                        outputBiomeIDs[j1 + i1 * width] = getBiomeIdFromList(2);
                    }
                }
                else if (currentBiomeID == 4)
                {
                    outputBiomeIDs[j1 + i1 * width] = getBiomeIdFromList(3);
                }
                else if (BOPConfigurationBiomeGen.mushroomIslandGen)
                {
                	outputBiomeIDs[j1 + i1 * width] = BiomeGenBase.mushroomIsland.biomeID;
                }
                else
                {
                	outputBiomeIDs[j1 + i1 * width] = getBiomeIdFromMixedList();
                }
            }
        }

        return outputBiomeIDs;
    }
    
    private int getBiomeIdFromList(int listId)
    {
    	if (!this.biomeLists[listId].isEmpty())
    	{
    		return getWeightedBiomeFromList(this.biomeLists[listId]);
    	}
    	else
    	{
    		return getBiomeIdFromMixedList(listId);
    	}
    }
    
    private int getBiomeIdFromMixedList(int... listIdExclusions)
    {
    	List listIdExclusionList = Arrays.asList(listIdExclusions);
		List<BiomeEntry> mixedBiomeList = new ArrayList();
		
		for (int i = 0; i < 4; i++)
		{
			if (!listIdExclusionList.contains(i) && !this.biomeLists[i].isEmpty()) mixedBiomeList.addAll(this.biomeLists[i]);
		}
		
		if (!mixedBiomeList.isEmpty())
		{
			return getWeightedBiomeFromList(mixedBiomeList);
		}
		else
		{
    		throw new RuntimeException("No biomes are enabled!");
		}
    }
    
    private int getWeightedBiomeFromList(List<BiomeEntry> biomeList)
    {
    	return ((BiomeEntry)WeightedRandom.getItem(biomeList, this.nextInt(WeightedRandom.getTotalWeight(biomeList)))).biome.biomeID;
    }
    
    private boolean isBiomeOceanicAndEnabled(int biomeId)
    {
        return (biomeId == BiomeGenBase.ocean.biomeID  && BOPConfigurationBiomeGen.oceanGen)|| (biomeId == BiomeGenBase.deepOcean.biomeID && BOPConfigurationBiomeGen.deepOceanGen) || (biomeId == BiomeGenBase.frozenOcean.biomeID && BOPConfigurationBiomeGen.frozenOceanGen);
    }
}
