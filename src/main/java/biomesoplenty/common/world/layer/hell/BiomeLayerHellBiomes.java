package biomesoplenty.common.world.layer.hell;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.util.WeightedRandom;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.IntCache;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import biomesoplenty.common.world.BOPBiomeManager;

public class BiomeLayerHellBiomes extends BiomeLayerHell
{
	public List<BiomeEntry> netherBiomes = new ArrayList();
	
    public BiomeLayerHellBiomes(long par1, BiomeLayerHell par3GenLayer)
    {
        super(par1);
		parent = par3GenLayer;

		this.netherBiomes.add(new BiomeEntry(BiomeGenBase.hell, 10));
		this.netherBiomes.addAll(BOPBiomeManager.netherBiomes);
    } 

    @Override
	public int[] getInts(int par1, int par2, int par3, int par4)
    {
        int[] var5 = this.parent.getInts(par1, par2, par3, par4);
        int[] var6 = IntCache.getIntCache(par3 * par4);

        for (int var7 = 0; var7 < par4; ++var7)
        {
            for (int var8 = 0; var8 < par3; ++var8)
            {
                this.initChunkSeed((long)(var8 + par1), (long)(var7 + par2));
                int var9 = var5[var8 + var7 * par3];
				
				var6[var8 + var7 * par3] = getWeightedBiomeFromList(netherBiomes);
            }
        }
        return var6;
    }
    
    private int getWeightedBiomeFromList(List<BiomeEntry> biomeList)
    {
    	return ((BiomeEntry)WeightedRandom.getItem(biomeList, (int)this.nextLong(WeightedRandom.getTotalWeight(biomeList) / 10) * 10)).biome.biomeID;
    }
}