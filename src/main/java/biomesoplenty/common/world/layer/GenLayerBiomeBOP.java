package biomesoplenty.common.world.layer;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.util.WeightedRandom;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerBiome;
import net.minecraft.world.gen.layer.IntCache;
import biomesoplenty.api.BOPBiomeHelper.TemperatureType;
import biomesoplenty.common.configuration.BOPConfigurationBiomeGen;
import biomesoplenty.common.world.BOPBiomeManager;
import biomesoplenty.common.world.BOPBiomeManager.BiomeEntry;
import cpw.mods.fml.common.ObfuscationReflectionHelper;

public class GenLayerBiomeBOP extends GenLayerBiome
{
	private List<BiomeEntry> desertBiomes = new ArrayList<BiomeEntry>();
	private List<BiomeEntry> warmBiomes = new ArrayList<BiomeEntry>();
	private List<BiomeEntry> coolBiomes = new ArrayList<BiomeEntry>();
	private List<BiomeEntry> icyBiomes = new ArrayList<BiomeEntry>();
	
	public GenLayerBiomeBOP(long seed, GenLayer parentLayer, WorldType worldType) 
	{
		super(seed, parentLayer, worldType);
		
		BiomeGenBase[] vanillaDesertBiomes = (BiomeGenBase[])ObfuscationReflectionHelper.getPrivateValue(GenLayerBiome.class, this, "field_151623_c");
		BiomeGenBase[] vanillaWarmBiomes = (BiomeGenBase[])ObfuscationReflectionHelper.getPrivateValue(GenLayerBiome.class, this, "field_151621_d");
		BiomeGenBase[] vanillaCoolBiomes = (BiomeGenBase[])ObfuscationReflectionHelper.getPrivateValue(GenLayerBiome.class, this, "field_151622_e");
		BiomeGenBase[] vanillaIcyBiomes = (BiomeGenBase[])ObfuscationReflectionHelper.getPrivateValue(GenLayerBiome.class, this, "field_151620_f");
		
		for (BiomeGenBase biome : vanillaDesertBiomes)
		{
			if (biome != null)
			{
				BiomeEntry entry = new BiomeEntry(biome, TemperatureType.HOT, 10);

				desertBiomes.add(entry);
			}
		}

		for (BiomeGenBase biome : vanillaWarmBiomes)
		{
			if (biome != null)
			{
				BiomeEntry entry = new BiomeEntry(biome, TemperatureType.WARM, 10);

				warmBiomes.add(entry);
			}
		}

		for (BiomeGenBase biome : vanillaCoolBiomes)
		{
			if (biome != null)
			{
				BiomeEntry entry = new BiomeEntry(biome, TemperatureType.COOL, 10);

				coolBiomes.add(entry);
			}
		}

		for (BiomeGenBase biome : vanillaIcyBiomes)
		{
			if (biome != null)
			{
				BiomeEntry entry = new BiomeEntry(biome, TemperatureType.ICY, 10);

				icyBiomes.add(entry);
			}
		}
		
		this.desertBiomes.addAll(BOPBiomeManager.desertBiomes);
		this.warmBiomes.addAll(BOPBiomeManager.warmBiomes);
		this.coolBiomes.addAll(BOPBiomeManager.coolBiomes);
		this.icyBiomes.addAll(BOPBiomeManager.icyBiomes);
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
                //   				111100000000
                int l1 = (biomeID & 3840) >> 8;
                biomeID &= -3841;

                if (isBiomeOceanic(biomeID))
                {
                    aint1[j1 + i1 * par3] = biomeID;
                }
                else if (biomeID == BiomeGenBase.mushroomIsland.biomeID && BOPConfigurationBiomeGen.mushroomIslandGen)
                {
                    aint1[j1 + i1 * par3] = biomeID;
                }
                else if (biomeID == 1)
                {
                    if (l1 > 0)
                    {
                        if (this.nextInt(3) == 0 && BOPConfigurationBiomeGen.mesaPlateauGen)
                        {
                            aint1[j1 + i1 * par3] = BiomeGenBase.mesaPlateau.biomeID;
                        }
                        else if (BOPConfigurationBiomeGen.mesaPlateauFGen)
                        {
                            aint1[j1 + i1 * par3] = BiomeGenBase.mesaPlateau_F.biomeID;
                        }
                    }
                    else
                    {
                        aint1[j1 + i1 * par3] = BOPBiomeManager.getWeightedRandomBiome(desertBiomes, this.nextInt(WeightedRandom.getTotalWeight(desertBiomes))).biome.biomeID;
                    }
                }
                else if (biomeID == 2)
                {
                    if (l1 > 0 && BOPConfigurationBiomeGen.jungleGen)
                    {
                        aint1[j1 + i1 * par3] = BiomeGenBase.jungle.biomeID;
                    }
                    else
                    {
                        aint1[j1 + i1 * par3] = BOPBiomeManager.getWeightedRandomBiome(warmBiomes, this.nextInt(WeightedRandom.getTotalWeight(warmBiomes))).biome.biomeID;
                    }
                }
                else if (biomeID == 3)
                {
                    if (l1 > 0 && BOPConfigurationBiomeGen.megaTaigaGen)
                    {
                        aint1[j1 + i1 * par3] = BiomeGenBase.megaTaiga.biomeID;
                    }
                    else
                    {
                        aint1[j1 + i1 * par3] = BOPBiomeManager.getWeightedRandomBiome(coolBiomes, this.nextInt(WeightedRandom.getTotalWeight(coolBiomes))).biome.biomeID;
                    }
                }
                else if (biomeID == 4)
                {
                    aint1[j1 + i1 * par3] = BOPBiomeManager.getWeightedRandomBiome(icyBiomes, this.nextInt(WeightedRandom.getTotalWeight(icyBiomes))).biome.biomeID;
                }
                else if (BOPConfigurationBiomeGen.mushroomIslandGen)
                {
                    aint1[j1 + i1 * par3] = BiomeGenBase.mushroomIsland.biomeID;
                }
            }
        }

        return aint1;
    }
}
