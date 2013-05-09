package biomesoplenty.world.layer;

import java.util.ArrayList;

import biomesoplenty.api.Biomes;

import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeLayerBiomes extends BiomeLayer
{
	public static ArrayList<BiomeGenBase> AllowedBiomes = new ArrayList<BiomeGenBase>();
	
    public BiomeLayerBiomes(long par1, BiomeLayer par3GenLayer, WorldType par4WorldType)
    {
        super(par1);
		parent = par3GenLayer;
		
		if (Biomes.promisedLandForest.isPresent())
		{
			AllowedBiomes.add(Biomes.promisedLandForest.get());
		}
		
		if (Biomes.promisedLandPlains.isPresent())
		{
			AllowedBiomes.add(Biomes.promisedLandPlains.get());
		}
		
		if (Biomes.promisedLandDesert.isPresent())
		{
			AllowedBiomes.add(Biomes.promisedLandDesert.get());
		}
		
		if (Biomes.promisedLandSwamp.isPresent())
		{
			AllowedBiomes.add(Biomes.promisedLandSwamp.get());
		}
		
		if (Biomes.promisedLandCrystals.isPresent())
		{
			AllowedBiomes.add(Biomes.promisedLandCrystals.get());
		}
	} 

    public int[] getInts(int par1, int par2, int par3, int par4)
    {
        int[] var5 = this.parent.getInts(par1, par2, par3, par4);
        int[] var6 = LayerIntCache.getIntCache(par3 * par4);

        for (int var7 = 0; var7 < par4; ++var7)
        {
            for (int var8 = 0; var8 < par3; ++var8)
            {
                this.initChunkSeed((long)(var8 + par1), (long)(var7 + par2));
                int var9 = var5[var8 + var7 * par3];
				var6[var8 + var7 * par3] = AllowedBiomes.get(this.nextInt(AllowedBiomes.size())).biomeID;
            }
        }
        return var6;
    }
}