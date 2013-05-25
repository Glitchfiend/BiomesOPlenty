package biomesoplenty.world.layer;

public class BiomeLayerCreate extends BiomeLayer
{
	private boolean ocean;
	
    public BiomeLayerCreate(long par1, boolean o)
    {
        super(par1);
        ocean = o;
    }

    public int[] getInts(int par1, int par2, int par3, int par4)
    {
        int[] var5 = LayerIntCache.getIntCache(par3 * par4);

        for (int var6 = 0; var6 < par4; ++var6)
        {
            for (int var7 = 0; var7 < par3; ++var7)
            {
                this.initChunkSeed((long)(par1 + var7), (long)(par2 + var6));
                if(ocean)
                {
                	var5[var7 + var6 * par3] = this.nextInt(10) == 0 ? 1 : 0;
                }
                else
                {
                    var5[var7 + var6 * par3] = 1;
                }
            }
        }

        if(ocean)
        {
			if (par1 > -par3 && par1 <= 0 && par2 > -par4 && par2 <= 0)
			{
				var5[-par1 + -par2 * par3] = 1;
			}	
        }
        
        return var5;
    }
}