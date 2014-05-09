package biomesoplenty.common.biomes.overworld.subbiomes.ocean;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase.Height;
import biomesoplenty.common.biomes.overworld.subbiomes.BOPOceanBiome;

public class BiomeGenKelpForest extends BOPOceanBiome
{
	public BiomeGenKelpForest(int biomeID) 
	{
		super(biomeID);
		
		this.setColor(53);
		
        this.waterColorMultiplier = 11506176;
	}
}
