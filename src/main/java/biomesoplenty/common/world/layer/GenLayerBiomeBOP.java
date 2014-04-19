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
import biomesoplenty.common.configuration.BOPConfigurationMain;
import biomesoplenty.common.utils.BOPLogger;
import biomesoplenty.common.world.BOPBiomeManager;
import biomesoplenty.common.world.BOPBiomeManager.BiomeEntry;
import cpw.mods.fml.common.ObfuscationReflectionHelper;

public class GenLayerBiomeBOP extends GenLayerBiome
{
	public List<BiomeEntry> desertBiomes = new ArrayList<BiomeEntry>();
	public List<BiomeEntry> warmBiomes = new ArrayList<BiomeEntry>();
	public List<BiomeEntry> coolBiomes = new ArrayList<BiomeEntry>();
	public List<BiomeEntry> icyBiomes = new ArrayList<BiomeEntry>();
	
	public GenLayerBiomeBOP(long seed, GenLayer parentLayer, WorldType worldType) 
	{
		super(seed, parentLayer, worldType);
		
		this.desertBiomes.addAll(BOPBiomeManager.desertBiomes);
		this.warmBiomes.addAll(BOPBiomeManager.warmBiomes);
		this.coolBiomes.addAll(BOPBiomeManager.coolBiomes);
		this.icyBiomes.addAll(BOPBiomeManager.icyBiomes);
	}

	
    @Override
	public int[] getInts(int x, int z, int width, int length)
    {
        int[] inputBiomeIDs = this.parent.getInts(x, z, width, length);
        int[] outputBiomeIDs = IntCache.getIntCache(width * length);

        for (int i1 = 0; i1 < length; ++i1)
        {
            for (int j1 = 0; j1 < width; ++j1)
            {
                this.initChunkSeed((long)(j1 + x), (long)(i1 + z));
                int currentBiomeID = inputBiomeIDs[j1 + i1 * width];
                //   				111100000000
                int l1 = (currentBiomeID & 3840) >> 8;
                currentBiomeID &= -3841;

                if (isBiomeOceanic(currentBiomeID))
                {
                    outputBiomeIDs[j1 + i1 * width] = currentBiomeID;
                }
                else if (currentBiomeID == BiomeGenBase.mushroomIsland.biomeID && BOPConfigurationBiomeGen.mushroomIslandGen)
                {
                    outputBiomeIDs[j1 + i1 * width] = currentBiomeID;
                }
                else if (currentBiomeID == 1)
                {
                    if (l1 > 0)
                    {
                        if (this.nextInt(3) == 0 && BOPConfigurationBiomeGen.mesaPlateauGen)
                        {
                            outputBiomeIDs[j1 + i1 * width] = BiomeGenBase.mesaPlateau.biomeID;
                        }
                        else if (BOPConfigurationBiomeGen.mesaPlateauFGen)
                        {
                            outputBiomeIDs[j1 + i1 * width] = BiomeGenBase.mesaPlateau_F.biomeID;
                        }
                    }
                    else
                    {
                        outputBiomeIDs[j1 + i1 * width] = BOPBiomeManager.getWeightedRandomBiome(desertBiomes, this.nextInt(WeightedRandom.getTotalWeight(desertBiomes))).biome.biomeID;
                    }
                }
                else if (currentBiomeID == 2)
                {
                    if (l1 > 0 && BOPConfigurationBiomeGen.jungleGen)
                    {
                        outputBiomeIDs[j1 + i1 * width] = BiomeGenBase.jungle.biomeID;
                    }
                    else
                    {
                        outputBiomeIDs[j1 + i1 * width] = BOPBiomeManager.getWeightedRandomBiome(warmBiomes, this.nextInt(WeightedRandom.getTotalWeight(warmBiomes))).biome.biomeID;
                    }
                }
                else if (currentBiomeID == 3)
                {
                    if (l1 > 0 && BOPConfigurationBiomeGen.megaTaigaGen)
                    {
                        outputBiomeIDs[j1 + i1 * width] = BiomeGenBase.megaTaiga.biomeID;
                    }
                    else
                    {
                        outputBiomeIDs[j1 + i1 * width] = BOPBiomeManager.getWeightedRandomBiome(coolBiomes, this.nextInt(WeightedRandom.getTotalWeight(coolBiomes))).biome.biomeID;
                    }
                }
                else if (currentBiomeID == 4)
                {
                    outputBiomeIDs[j1 + i1 * width] = BOPBiomeManager.getWeightedRandomBiome(icyBiomes, this.nextInt(WeightedRandom.getTotalWeight(icyBiomes))).biome.biomeID;
                }
                else if (BOPConfigurationBiomeGen.mushroomIslandGen)
                {
                    outputBiomeIDs[j1 + i1 * width] = BiomeGenBase.mushroomIsland.biomeID;
                }
            }
        }

        return outputBiomeIDs;
    }
}
