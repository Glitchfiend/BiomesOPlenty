package biomesoplenty.common.world.layer;

import biomesoplenty.api.content.BOPCBiomes;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerRiverMixBOP extends GenLayer
{
    private GenLayer biomePatternGeneratorChain;
    private GenLayer riverPatternGeneratorChain;
    private static final String __OBFID = "CL_00000567";

    public GenLayerRiverMixBOP(long par1, GenLayer par3GenLayer, GenLayer par4GenLayer)
    {
        super(par1);
        this.biomePatternGeneratorChain = par3GenLayer;
        this.riverPatternGeneratorChain = par4GenLayer;
    }

    public void initWorldGenSeed(long par1)
    {
        this.biomePatternGeneratorChain.initWorldGenSeed(par1);
        this.riverPatternGeneratorChain.initWorldGenSeed(par1);
        super.initWorldGenSeed(par1);
    }

    public int[] getInts(int par1, int par2, int par3, int par4)
    {
        int[] aint = this.biomePatternGeneratorChain.getInts(par1, par2, par3, par4);
        int[] aint1 = this.riverPatternGeneratorChain.getInts(par1, par2, par3, par4);
        int[] aint2 = IntCache.getIntCache(par3 * par4);

        for (int i1 = 0; i1 < par3 * par4; ++i1)
        {
            if (aint[i1] != BiomeGenBase.ocean.biomeID && aint[i1] != BiomeGenBase.deepOcean.biomeID)
            {
                if (aint1[i1] == BiomeGenBase.river.biomeID)
                {
                    if (aint[i1] == BiomeGenBase.icePlains.biomeID)
                    {
                        aint2[i1] = BiomeGenBase.frozenRiver.biomeID;
                    }
                    else if (aint[i1] == BOPCBiomes.quagmire.biomeID)
                    {
                        aint2[i1] = BOPCBiomes.quagmire.biomeID;
                    }
                    else if (aint[i1] == BOPCBiomes.sludgepit.biomeID)
                    {
                        aint2[i1] = BOPCBiomes.sludgepit.biomeID;
                    }
                    else if (aint[i1] != BiomeGenBase.mushroomIsland.biomeID && aint[i1] != BiomeGenBase.mushroomIslandShore.biomeID)
                    {
                        aint2[i1] = aint1[i1] & 255;
                    }
                    else
                    {
                        aint2[i1] = BiomeGenBase.mushroomIslandShore.biomeID;
                    }
                }
                else
                {
                    aint2[i1] = aint[i1];
                }
            }
            else
            {
                aint2[i1] = aint[i1];
            }
        }

        return aint2;
    }
}