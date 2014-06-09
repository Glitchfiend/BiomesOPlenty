package biomesoplenty.common.biome.overridden;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.common.biome.BOPInheritedNetherBiome;
import biomesoplenty.common.world.features.WorldGenBOPFlora;

public class BiomeGenBOPHell extends BOPInheritedNetherBiome
{
	public BiomeGenBOPHell(int biomeID, BiomeGenBase inheritedBiome) 
	{
		super(biomeID, inheritedBiome);
		
		this.topBlock = Blocks.netherrack;
		this.fillerBlock = Blocks.netherrack;
		
        this.theBiomeDecorator.bopFeatures.gravesPerChunk = 1;
        this.theBiomeDecorator.bopFeatures.waspHivesPerChunk = 1;
        
        this.theBiomeDecorator.bopFeatures.bopFlowersPerChunk = 4;
        
        this.theBiomeDecorator.bopFeatures.weightedFlowerGen.put(new WorldGenBOPFlora(BOPCBlocks.flowers2, 2), 10);
	}
}
