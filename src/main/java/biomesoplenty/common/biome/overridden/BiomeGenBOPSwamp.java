package biomesoplenty.common.biome.overridden;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenTaiga2;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.biome.BOPInheritedBiome;
import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.common.biome.BOPInheritedOverworldBiome;
import biomesoplenty.common.world.features.WorldGenBOPFlora;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;

public class BiomeGenBOPSwamp extends BOPInheritedOverworldBiome
{
	public BiomeGenBOPSwamp(int biomeID, BiomeGenBase inheritedBiome) 
	{
		super(biomeID, inheritedBiome);

		this.theBiomeDecorator.bopFeatures.mudPerChunk = 3;
		this.theBiomeDecorator.bopFeatures.seaweedPerChunk = 10;
		this.theBiomeDecorator.bopFeatures.cattailsPerChunk = 10;
		this.theBiomeDecorator.bopFeatures.highCattailsPerChunk = 5;
		this.theBiomeDecorator.bopFeatures.algaePerChunk = 3;
		this.theBiomeDecorator.bopFeatures.koruPerChunk = 25;
		this.theBiomeDecorator.bopFeatures.waterReedsPerChunk = 5;
		this.theBiomeDecorator.bopFeatures.toadstoolsPerChunk = 1;
		this.theBiomeDecorator.bopFeatures.blueMilksPerChunk = 1;
        this.theBiomeDecorator.bopFeatures.leafPilesPerChunk = 2;
        this.theBiomeDecorator.bopFeatures.deadLeafPilesPerChunk = 4;
             
        this.theBiomeDecorator.bopFeatures.bopGrassPerChunk = 5;
             
        this.theBiomeDecorator.bopFeatures.weightedFlowerGen.put(new WorldGenBOPFlora(BOPCBlocks.flowers, 1), 15);
             
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 1), 1D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 1), 0.5D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 2), 0.5D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 10), 0.5D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 11), 0.5D);
	}
}
