package biomesoplenty.api.utils;

import biomesoplenty.common.configuration.BOPConfigurationTerrainGen;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeUtils 
{
	public static boolean areBiomesEqual(BiomeGenBase biome1, BiomeGenBase biome2)
	{
		if (biome1 != null && biome2 != null)
		{
			return biome1.biomeID == biome2.biomeID;
		}
		
		return false;
	}
	
	public static boolean oreWithMetaEnabled(int metaOfOre){
		if(!BOPConfigurationTerrainGen.genOreGeneral) return false;
		
		switch(metaOfOre){
			case 0: if(BOPConfigurationTerrainGen.genAmethystOre) return true;
					break;
			case 2: if(BOPConfigurationTerrainGen.genRubyOre) return true;
					break;
			case 4: if(BOPConfigurationTerrainGen.genPeridotOre) return true;
					break;
			case 6: if(BOPConfigurationTerrainGen.genTopazOre) return true;
					break;
			case 8: if(BOPConfigurationTerrainGen.genTanzaniteOre) return true;
					break;
			case 10: if(BOPConfigurationTerrainGen.genMalachiteOre) return true;
					 break;
			case 12: if(BOPConfigurationTerrainGen.genSapphireOre) return true;
					 break;
			case 14: if(BOPConfigurationTerrainGen.genAmberOre) return true;
			         break;
		}
		
		return false;
	}
}
