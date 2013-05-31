package biomesoplenty.world.layer;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.IntCache;

public class BiomeLayerRiverMix extends BiomeLayer
{
	private BiomeLayer biomePatternGeneratorChain;
	private BiomeLayer riverPatternGeneratorChain;

	public BiomeLayerRiverMix(long par1, BiomeLayer par3GenLayer, BiomeLayer par4GenLayer)
	{
		super(par1);
		biomePatternGeneratorChain = par3GenLayer;
		riverPatternGeneratorChain = par4GenLayer;
	}

	@Override
	public void initWorldGenSeed(long par1)
	{
		biomePatternGeneratorChain.initWorldGenSeed(par1);
		riverPatternGeneratorChain.initWorldGenSeed(par1);
		super.initWorldGenSeed(par1);
	}

	@Override
	public int[] getInts(int par1, int par2, int par3, int par4)
	{
		int[] var5 = biomePatternGeneratorChain.getInts(par1, par2, par3, par4);
		int[] var6 = riverPatternGeneratorChain.getInts(par1, par2, par3, par4);
		int[] var7 = IntCache.getIntCache(par3 * par4);

		for (int var8 = 0; var8 < par3 * par4; ++var8)
		{
			if (var5[var8] == BiomeGenBase.ocean.biomeID)
			{
				var7[var8] = var5[var8];
			}
			else if (var6[var8] >= 0)
			{
				if (var5[var8] == BiomeGenBase.icePlains.biomeID)
				{
					var7[var8] = BiomeGenBase.frozenRiver.biomeID;
				}
				else if (var5[var8] != BiomeGenBase.mushroomIsland.biomeID && var5[var8] != BiomeGenBase.mushroomIslandShore.biomeID)
				{
					var7[var8] = var6[var8];
				}
				else
				{
					var7[var8] = BiomeGenBase.mushroomIslandShore.biomeID;
				}
			}
			else
			{
				var7[var8] = var5[var8];
			}
		}
		return var7;
	}
}