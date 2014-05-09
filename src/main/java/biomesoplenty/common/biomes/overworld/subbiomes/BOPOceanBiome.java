package biomesoplenty.common.biomes.overworld.subbiomes;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import biomesoplenty.common.biomes.BOPSubBiome;

public abstract class BOPOceanBiome extends BOPSubBiome
{
	public BOPOceanBiome(int biomeID) 
	{
		super(biomeID);
		
		this.setHeight(BiomeGenBase.height_Oceans);
		
        this.spawnableCreatureList.clear();
	}
}
