package biomesoplenty.world.layer;

import net.minecraft.world.gen.layer.IntCache;

public class BiomeLayerShore extends BiomeLayer
{
    public BiomeLayerShore(long par1, BiomeLayer par3GenLayer)
    {
        super(par1);
        this.parent = par3GenLayer;
    }

    public int[] getInts(int par1, int par2, int par3, int par4)
    {
        int[] var5 = this.parent.getInts(par1 - 1, par2 - 1, par3 + 2, par4 + 2);
        int[] var6 = IntCache.getIntCache(par3 * par4);

        for (int var7 = 0; var7 < par4; ++var7)
        {
            for (int var8 = 0; var8 < par3; ++var8)
            {
                this.initChunkSeed((long)(var8 + par1), (long)(var7 + par2));
                int var9 = var5[var8 + 1 + (var7 + 1) * (par3 + 2)];
                int var10;
                int var11;
                int var12;
                int var13;

				var6[var8 + var7 * par3] = var9;
            }
        }
        return var6;
    }
}