package biomesoplenty.world.layer;

import net.minecraft.world.gen.layer.IntCache;
import biomesoplenty.api.Biomes;
import biomesoplenty.configuration.configfile.BOPConfigurationBiomeGen;
import biomesoplenty.configuration.configfile.BOPConfigurationTerrainGen;

public class BiomeLayerCreate extends BiomeLayer
{
	final private int ABYSS_CHANCE = 2;
	final private int CORAL_CHANCE = 5;
	final private int KELP_CHANCE = 5;
	
	public BiomeLayerCreate(long par1, boolean o)
	{
		super(par1);
	}

	@Override
	public int[] getInts(int par1, int par2, int par3, int par4)
	{
		int[] var5 = IntCache.getIntCache(par3 * par4);

		boolean ocean = BOPConfigurationBiomeGen.oceanGen;
		boolean abyss = Biomes.oceanAbyss.isPresent();
        boolean coral = Biomes.oceanCoral.isPresent();
        boolean kelp = Biomes.oceanKelp.isPresent();

		for (int var6 = 0; var6 < par4; ++var6)
		{
			for (int var7 = 0; var7 < par3; ++var7)
			{
				this.initChunkSeed(par1 + var7, par2 + var6);
				if(ocean)
				{
					if (this.nextInt(100) < BOPConfigurationTerrainGen.landmassPercentage)
					{
						var5[var7 + var6 * par3] = 1;
					}
					else
					{
						int oceanType = 0;
						if (abyss && this.nextInt(100) < ABYSS_CHANCE) oceanType = Biomes.oceanAbyss.get().biomeID;
						else if (coral && this.nextInt(100) < CORAL_CHANCE) oceanType = Biomes.oceanCoral.get().biomeID;
						else if (kelp && this.nextInt(100) < KELP_CHANCE) oceanType = Biomes.oceanKelp.get().biomeID;
							
						var5[var7 + var6 * par3] = oceanType;
					}
				}
				else
				{
					var5[var7 + var6 * par3] = 1;
				}
			}
		}

		if (par1 > -par3 && par1 <= 0 && par2 > -par4 && par2 <= 0)
		{
			var5[-par1 + -par2 * par3] = 1;
		}

		return var5;
	}
}