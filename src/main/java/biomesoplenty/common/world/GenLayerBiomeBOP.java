package biomesoplenty.common.world;

import java.util.ArrayList;

import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerBiome;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerBiomeBOP extends GenLayerBiome
{
    public static ArrayList<BiomeGenBase> desertBiomes = new ArrayList();
    public static ArrayList<BiomeGenBase> warmBiomes = new ArrayList();
    public static ArrayList<BiomeGenBase> coldBiomes = new ArrayList();
    public static ArrayList<BiomeGenBase> icyBiomes = new ArrayList();
	
	public GenLayerBiomeBOP(long par1, GenLayer parentLayer, WorldType worldType) 
	{
		super(par1, parentLayer, worldType);
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

                //TODO:	isOceanBiome()
                if (func_151618_b(biomeID))
                {
                    aint1[j1 + i1 * par3] = biomeID;
                }
                else if (biomeID == BiomeGenBase.mushroomIsland.biomeID)
                {
                    aint1[j1 + i1 * par3] = biomeID;
                }
                else if (biomeID == 1)
                {
                    if (l1 > 0)
                    {
                        if (this.nextInt(3) == 0)
                        {
                        	//TODO:								 mesaPlateau
                            aint1[j1 + i1 * par3] = BiomeGenBase.field_150608_ab.biomeID;
                        }
                        else
                        {
                        	//TODO:								 mesaPlateau F
                            aint1[j1 + i1 * par3] = BiomeGenBase.field_150607_aa.biomeID;
                        }
                    }
                    else
                    {
                        aint1[j1 + i1 * par3] = this.desertBiomes.get(this.nextInt(this.desertBiomes.size())).biomeID;
                    }
                }
                else if (biomeID == 2)
                {
                    if (l1 > 0)
                    {
                        aint1[j1 + i1 * par3] = BiomeGenBase.jungle.biomeID;
                    }
                    else
                    {
                        aint1[j1 + i1 * par3] = this.warmBiomes.get(this.nextInt(this.warmBiomes.size())).biomeID;
                    }
                }
                else if (biomeID == 3)
                {
                    if (l1 > 0)
                    {
                    	//TODO:								 megaTaiga
                        aint1[j1 + i1 * par3] = BiomeGenBase.field_150578_U.biomeID;
                    }
                    else
                    {
                        aint1[j1 + i1 * par3] = this.coldBiomes.get(this.nextInt(this.coldBiomes.size())).biomeID;
                    }
                }
                else if (biomeID == 4)
                {
                    aint1[j1 + i1 * par3] = this.icyBiomes.get(this.nextInt(this.icyBiomes.size())).biomeID;
                }
                else
                {
                    aint1[j1 + i1 * par3] = BiomeGenBase.mushroomIsland.biomeID;
                }
            }
        }

        return aint1;
    }
}
