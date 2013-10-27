package biomesoplenty.world.layer;

import java.util.ArrayList;

import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.IntCache;
import biomesoplenty.api.Biomes;
import biomesoplenty.configuration.configfile.BOPConfigurationBiomeGen;

public class BiomeLayerBiomes extends BiomeLayer
{
	private int dimension = 0; 
	
	private BiomeGenBase[] surfaceBiomes;
	private static ArrayList<BiomeGenBase> oceanBiomes = new ArrayList<BiomeGenBase>();
	private static ArrayList<BiomeGenBase> netherBiomes = new ArrayList<BiomeGenBase>();
	private static ArrayList<BiomeGenBase> promisedBiomes = new ArrayList<BiomeGenBase>();
	
    public BiomeLayerBiomes(long par1, BiomeLayer par3GenLayer, WorldType par4WorldType, int dim)
    {
        super(par1);
		parent = par3GenLayer;
		dimension = dim;
		
		//SURFACE BIOMES
		surfaceBiomes = par4WorldType.getBiomesForWorldType();
		
		//OCEAN BIOMES
		oceanBiomes.add(BiomeGenBase.ocean);
		if (Biomes.oceanAbyss.isPresent())
		{
			if (BOPConfigurationBiomeGen.oceanicAbyssGen)
			{
				oceanBiomes.add(Biomes.oceanAbyss.get());
			}
		}
		if (Biomes.oceanCoral.isPresent())
		{
			if (BOPConfigurationBiomeGen.coralReefGen)
			{
				oceanBiomes.add(Biomes.oceanCoral.get());
			}
		}
		if (Biomes.oceanKelp.isPresent())
		{
			if (BOPConfigurationBiomeGen.kelpForestGen)
			{
				oceanBiomes.add(Biomes.oceanKelp.get());
			}
		}

		//NETHER BIOMES
		if (Biomes.netherBase.isPresent())
		{
			netherBiomes.add(Biomes.netherBase.get());
		}
		if (Biomes.netherGarden.isPresent())
		{
			if (BOPConfigurationBiomeGen.undergardenGen)
			{
				netherBiomes.add(Biomes.netherGarden.get());
			}
		}
		if (Biomes.netherDesert.isPresent())
		{
			if (BOPConfigurationBiomeGen.corruptedSandsGen)
			{
				netherBiomes.add(Biomes.netherDesert.get());
			}
		}
		if (Biomes.netherLava.isPresent())
		{
			if (BOPConfigurationBiomeGen.phantasmagoricInfernoGen)
			{
				netherBiomes.add(Biomes.netherLava.get());
			}
		}
		if (Biomes.netherBone.isPresent())
		{
			if (BOPConfigurationBiomeGen.boneyardGen)
			{
				netherBiomes.add(Biomes.netherBone.get());
			}
		}
		if (Biomes.netherBlood.isPresent())
		{
			if (BOPConfigurationBiomeGen.bloodyHeapGen)
			{
				netherBiomes.add(Biomes.netherBlood.get());
			}
		}
		if (Biomes.netherHive.isPresent())
		{
			if (BOPConfigurationBiomeGen.hellishHiveGen)
			{
				netherBiomes.add(Biomes.netherHive.get());
			}
		}
		if (Biomes.netherBiomes.size() > 0)
		{
		    netherBiomes.addAll(Biomes.netherBiomes);
		}
		
		//PROMISED BIOMES
		if (Biomes.promisedLandForest.isPresent())
		{
			promisedBiomes.add(Biomes.promisedLandForest.get());
		}
		if (Biomes.promisedLandPlains.isPresent())
		{
			promisedBiomes.add(Biomes.promisedLandPlains.get());
		}
		if (Biomes.promisedLandSwamp.isPresent())
		{
			promisedBiomes.add(Biomes.promisedLandSwamp.get());
		}
	} 

    @Override
	public int[] getInts(int par1, int par2, int par3, int par4)
    {
        int[] var5 = this.parent.getInts(par1, par2, par3, par4);
        int[] var6 = IntCache.getIntCache(par3 * par4);
        
        boolean abyss = Biomes.oceanAbyss.isPresent();
        boolean coral = Biomes.oceanCoral.isPresent();
        boolean kelp = Biomes.oceanKelp.isPresent();
        
        for (int var7 = 0; var7 < par4; ++var7)
        {
            for (int var8 = 0; var8 < par3; ++var8)
            {
                this.initChunkSeed((long)(var8 + par1), (long)(var7 + par2));
                int var9 = var5[var8 + var7 * par3];
				if(dimension == 0) //SURFACE BIOMES
				{
					if (var9 == 0)
					{
						var6[var8 + var7 * par3] = 0;
					}
					else 
					{
						var6[var8 + var7 * par3] = surfaceBiomes[this.nextInt(surfaceBiomes.length)].biomeID;
					}
					
					if (abyss)
					{
						if(var9 == Biomes.oceanAbyss.get().biomeID)
						{
							var6[var8 + var7 * par3] = oceanBiomes.get(this.nextInt(oceanBiomes.size())).biomeID;
						}
					}
					
					if (coral)
					{
						if(var9 == Biomes.oceanCoral.get().biomeID)
						{
							var6[var8 + var7 * par3] = oceanBiomes.get(this.nextInt(oceanBiomes.size())).biomeID;
						}
					}
					
					if (kelp)
					{
						if(var9 == Biomes.oceanKelp.get().biomeID)
						{
							var6[var8 + var7 * par3] = oceanBiomes.get(this.nextInt(oceanBiomes.size())).biomeID;
						}
					}

				}
				else if(dimension == 1) //HELL BIOMES
				{
					var6[var8 + var7 * par3] = netherBiomes.get(this.nextInt(netherBiomes.size())).biomeID;
				}
				else if(dimension == 2) //PROMISED BIOMES
				{
					var6[var8 + var7 * par3] = promisedBiomes.get(this.nextInt(promisedBiomes.size())).biomeID;
				}
				else
				{
					var6[var8 + var7 * par3] = surfaceBiomes[this.nextInt(surfaceBiomes.length)].biomeID;
				}
            }
        }
        return var6;
    }
}