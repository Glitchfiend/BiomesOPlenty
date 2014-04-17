package biomesoplenty.common.world.layer.hell;

import java.util.ArrayList;

import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.IntCache;
import biomesoplenty.api.BOPBiomeHelper;

public class BiomeLayerHellBiomes extends BiomeLayerHell
{
	public static ArrayList<BiomeGenBase> netherBiomes = new ArrayList<BiomeGenBase>();
	
    public BiomeLayerHellBiomes(long par1, BiomeLayerHell par3GenLayer)
    {
        super(par1);
		parent = par3GenLayer;

		netherBiomes.add(BiomeGenBase.hell);
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
				
				var6[var8 + var7 * par3] = netherBiomes.get(this.nextInt(netherBiomes.size())).biomeID;
            }
        }
        return var6;
    }
}