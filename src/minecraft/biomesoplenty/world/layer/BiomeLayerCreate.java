package biomesoplenty.world.layer;

public class BiomeLayerCreate extends BiomeLayer
{
    public BiomeLayerCreate(long par1)
    {
        super(par1);
    }

    public int[] getInts(int par1, int par2, int par3, int par4)
    {
        int[] var5 = LayerIntCache.getIntCache(par3 * par4);

        for (int var6 = 0; var6 < par4; ++var6)
        {
            for (int var7 = 0; var7 < par3; ++var7)
            {
                this.initChunkSeed((long)(par1 + var7), (long)(par2 + var6));
                var5[var7 + var6 * par3] = 1;
            }
        }
        return var5;
    }
}