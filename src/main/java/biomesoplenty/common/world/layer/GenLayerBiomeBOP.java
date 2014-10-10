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
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import biomesoplenty.common.configuration.BOPConfigurationBiomeGen;
import biomesoplenty.common.core.BOPBiomes;
import biomesoplenty.common.world.BOPBiomeManager;

public class GenLayerBiomeBOP extends GenLayerBiome
{
	//Desert, Warm, Cool, Icy
	public final List<BiomeEntry>[] biomeLists = new ArrayList[] { new ArrayList(), new ArrayList(), new ArrayList(), new ArrayList() };
	private final int[] totalWeights = new int[biomeLists.length];
	
	public GenLayerBiomeBOP(long seed, GenLayer parentLayer, WorldType worldType) 
	{
		super(seed, parentLayer, worldType);
		
		this.biomeLists[0].addAll(BOPBiomeManager.overworldBiomes[0]);
		this.biomeLists[1].addAll(BOPBiomeManager.overworldBiomes[1]);
		this.biomeLists[2].addAll(BOPBiomeManager.overworldBiomes[2]);
		this.biomeLists[3].addAll(BOPBiomeManager.overworldBiomes[3]);
		
		if (BiomeManager.getBiomes(BiomeManager.BiomeType.DESERT) != null)
		{
	            this.biomeLists[0].addAll(BiomeManager.getBiomes(BiomeManager.BiomeType.DESERT));
	        }
	
	        if (BiomeManager.getBiomes(BiomeManager.BiomeType.WARM) != null)
	        {
	            this.biomeLists[1].addAll(BiomeManager.getBiomes(BiomeManager.BiomeType.WARM));
	        }
	
	        if (BiomeManager.getBiomes(BiomeManager.BiomeType.COOL) != null)
	        {
	            this.biomeLists[2].addAll(BiomeManager.getBiomes(BiomeManager.BiomeType.COOL));
	        }
	
	        if (BiomeManager.getBiomes(BiomeManager.BiomeType.ICY) != null)
	        {
	            this.biomeLists[3].addAll(BiomeManager.getBiomes(BiomeManager.BiomeType.ICY));
	        }
		
        this.biomeLists[0].add(new BiomeEntry(BiomeGenBase.desert, 30));
        this.biomeLists[0].add(new BiomeEntry(BiomeGenBase.savanna, 20));
        this.biomeLists[0].add(new BiomeEntry(BiomeGenBase.plains, 10));
        
        for (int i = 0; i < biomeLists.length; i++)
        {
        	List<BiomeEntry> currentBiomeList = biomeLists[i];
        	
        	if (currentBiomeList.isEmpty()) currentBiomeList.addAll(createMixedList());
        	
        	totalWeights[i] = WeightedRandom.getTotalWeight(currentBiomeList);
        }
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
                else
                {
                	outputBiomeIDs[j1 + i1 * width] = getBiomeIdFromList(getRandomValidList());
                }
            }
        }

        return outputBiomeIDs;
    }
    
    private int getRandomValidList()
    {
    	List<Integer> validLists = new ArrayList();
    	
    	for (int i = 0; i < biomeLists.length; i++) validLists.add(i);
    	
    	int list = -1;
    	
    	while (list == -1)
    	{
    		int validListCount = validLists.size();
    		
    		if (validListCount > 0)
    		{
    			int randList = validLists.get(this.nextInt(validListCount));
    			List<BiomeEntry> currentList = biomeLists[randList];

    			if (currentList != null && !currentList.isEmpty()) list = randList;
    			else validLists.remove((Object)randList);
    		}
    		else throw new RuntimeException("No biomes are enabled!");
    	}

    	return list;
    }

    private int getBiomeIdFromList(int listId)
    {
    	List<BiomeEntry> currentBiomeList = biomeLists[listId];

    	if (!currentBiomeList.isEmpty())
    	{
    		int weight = nextInt(totalWeights[listId]);

    		return ((BiomeEntry)WeightedRandom.getItem(biomeLists[listId], weight)).biome.biomeID;
    	}
    	else throw new RuntimeException("No biomes are enabled!");
    }
    
    private List<BiomeEntry> createMixedList()
    {
    	List<BiomeEntry> combinedList = new ArrayList();
    	List<BiomeEntry> mixedList = new ArrayList();
    	
    	int maxSize = 0;
    	
    	for (List<BiomeEntry> biomeList : biomeLists)
    	{
    		if (biomeList != null && !biomeList.isEmpty()) 
    		{
    			combinedList.addAll(biomeList);
    			if (biomeList.size() > maxSize) maxSize = biomeList.size();
    		}
    	}
    	
    	while (mixedList.size() < maxSize)
    	{
    		mixedList.add((BiomeEntry)WeightedRandom.getItem(combinedList, this.nextInt(WeightedRandom.getTotalWeight(combinedList))));
    	}
    	
    	return mixedList;
    }
    
    private boolean isBiomeOceanicAndEnabled(int biomeId)
    {
        return (biomeId == BiomeGenBase.ocean.biomeID  && BOPConfigurationBiomeGen.oceanGen)|| (biomeId == BiomeGenBase.deepOcean.biomeID && BOPConfigurationBiomeGen.deepOceanGen) || (biomeId == BiomeGenBase.frozenOcean.biomeID && BOPConfigurationBiomeGen.frozenOceanGen);
    }
}
