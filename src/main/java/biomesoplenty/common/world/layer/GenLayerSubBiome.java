package biomesoplenty.common.world.layer;

import java.util.List;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

import org.apache.commons.lang3.tuple.Pair;

import biomesoplenty.api.BOPBiomeManager;
import biomesoplenty.api.BOPBiomeManager.BiomeEntry;
import biomesoplenty.common.biomes.BOPSubBiome;
import biomesoplenty.common.world.noise.SimplexNoise;

public class GenLayerSubBiome extends GenLayer
{
	private final int OFFSET_RANGE = 500000;
	
	private Pair<Integer, Integer>[] offsets = new Pair[BiomeGenBase.getBiomeGenArray().length];
	
	public GenLayerSubBiome(long seed, GenLayer parent) 
	{
		super(seed);
		
		this.parent = parent;
	}
	
    @Override
	public int[] getInts(int x, int z, int width, int length)
    {
        int[] inputBiomeIDs = this.parent.getInts(x - 1, z - 1, width + 2, length + 2);
        int[] outputBiomeIDs = IntCache.getIntCache(width * length);

        for (int xi = 0; xi < width; ++xi) 
        {
        	for (int zi = 0; zi < length; ++zi) 
        	{
        		this.initChunkSeed(xi + x, zi + z);
        		int currentBiomeId = inputBiomeIDs[xi + 1 + (zi + 1) * (width + 2)];
        		
        		List<BiomeEntry> currentSubBiomes = BOPBiomeManager.overworldSubBiomes[currentBiomeId];
        		BOPSubBiome selectedSubBiome = currentSubBiomes != null ? (BOPSubBiome)currentSubBiomes.get(this.nextInt(currentSubBiomes.size())).biome : null;
        		
        		if (selectedSubBiome != null)
        		{
        			Pair<Integer, Integer> offset = getOffset(selectedSubBiome);
        			
        			if (SimplexNoise.noise((xi + x + offset.getLeft()) * selectedSubBiome.zoom, (zi + z + offset.getRight()) * selectedSubBiome.zoom) > selectedSubBiome.threshold)  
        			{
        				outputBiomeIDs[xi + zi * width] = selectedSubBiome.biomeID;
        			}
        			else outputBiomeIDs[xi + zi * width] = currentBiomeId;
        		}
        		else
        		{
        			outputBiomeIDs[xi + zi * width] = currentBiomeId;
        		}
        	}
        }

        return outputBiomeIDs;
    }
    
    private Pair<Integer, Integer> getOffset(BiomeGenBase biome)
    {
    	Pair<Integer, Integer> offset = offsets[biome.biomeID];
    	
    	if (offset != null) return offset;
    	else
    	{
    		return offsets[biome.biomeID] = Pair.of(this.nextInt(OFFSET_RANGE) - (OFFSET_RANGE / 2), this.nextInt(OFFSET_RANGE) - (OFFSET_RANGE / 2));
    	}
    }
}
