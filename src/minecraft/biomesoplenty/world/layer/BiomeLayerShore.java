package biomesoplenty.world.layer;

import java.util.ArrayList;

import biomesoplenty.api.Biomes;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.IntCache;

public class BiomeLayerShore extends BiomeLayer
{
	private static ArrayList<BiomeGenBase> beachBiomes = new ArrayList<BiomeGenBase>();
	private static ArrayList<BiomeGenBase> oceanBiomes = new ArrayList<BiomeGenBase>();
	
	public BiomeLayerShore(long par1, BiomeLayer par3GenLayer)
	{
		super(par1);
		parent = par3GenLayer;

		//BEACH BIOMES
		beachBiomes.add(BiomeGenBase.beach);
		if (Biomes.beachGravel.isPresent())
		{
			beachBiomes.add(Biomes.beachGravel.get());
		}
		if (Biomes.beachOvergrown.isPresent())
		{
			beachBiomes.add(Biomes.beachOvergrown.get());
		}
		
		//OCEAN BIOMES
		oceanBiomes.add(BiomeGenBase.ocean);
		/*if (Biomes.oceanCoral.isPresent())
		{
			oceanBiomes.add(Biomes.oceanCoral.get());
		}
		if (Biomes.oceanKelp.isPresent())
		{
			oceanBiomes.add(Biomes.oceanKelp.get());
		}*/
	}
	
	private boolean isOceanBiome(int id)
	{
		for(int i = 0; i < oceanBiomes.size(); i++)
		{
			if(oceanBiomes.get(i).biomeID == id)
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public int[] getInts(int par1, int par2, int par3, int par4)
	{
		int[] var5 = parent.getInts(par1 - 1, par2 - 1, par3 + 2, par4 + 2);
		int[] var6 = IntCache.getIntCache(par3 * par4);

		for (int var7 = 0; var7 < par4; ++var7)
		{
			for (int var8 = 0; var8 < par3; ++var8)
			{
				this.initChunkSeed(var8 + par1, var7 + par2);
				int var9 = var5[var8 + 1 + (var7 + 1) * (par3 + 2)];
				int var10;
				int var11;
				int var12;
				int var13;

				var6[var8 + var7 * par3] = var9;

				if(!isOceanBiome(var9))
				{
					var10 = var5[var8 + 1 + (var7 + 1 - 1) * (par3 + 2)];
					var11 = var5[var8 + 1 + 1 + (var7 + 1) * (par3 + 2)];
					var12 = var5[var8 + 1 - 1 + (var7 + 1) * (par3 + 2)];
					var13 = var5[var8 + 1 + (var7 + 1 + 1) * (par3 + 2)];
					
					if (isOceanBiome(var10))
					{
						var6[var8 + var7 * par3] = beachBiomes.get(this.nextInt(beachBiomes.size())).biomeID;
					}
					else if (isOceanBiome(var11))
					{
						var6[var8 + var7 * par3] = beachBiomes.get(this.nextInt(beachBiomes.size())).biomeID;
					}
					else if (isOceanBiome(var12))
					{
						var6[var8 + var7 * par3] = beachBiomes.get(this.nextInt(beachBiomes.size())).biomeID;
					}
					else if (isOceanBiome(var13))
					{
						var6[var8 + var7 * par3] = beachBiomes.get(this.nextInt(beachBiomes.size())).biomeID;
					}
				}
			}
		}
		return var6;
	}
}