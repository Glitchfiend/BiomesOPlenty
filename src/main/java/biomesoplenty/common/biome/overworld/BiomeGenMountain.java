package biomesoplenty.common.biome.overworld;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase.Height;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.common.biome.BOPOverworldBiome;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;
import biomesoplenty.common.world.features.trees.WorldGenOriginalTree;
import biomesoplenty.common.world.features.trees.WorldGenPineTree;

public class BiomeGenMountain extends BOPOverworldBiome
{
	private static final Height biomeHeight = new Height(2.5F, 0.5F);

	public BiomeGenMountain(int id)
	{
		super(id);

        this.setHeight(biomeHeight);
        this.setColor(8430421);
        this.setTemperatureRainfall(0.5F, 0.1F);

		this.theBiomeDecorator.treesPerChunk = 2;
		this.theBiomeDecorator.grassPerChunk = 3;

		this.theBiomeDecorator.bopFeatures.gravelPerChunk = 4;
        this.theBiomeDecorator.bopFeatures.berryBushesPerChunk = 3;
        this.theBiomeDecorator.bopFeatures.shrubsPerChunk = 10;
        this.theBiomeDecorator.bopFeatures.waterReedsPerChunk = 4;
        this.theBiomeDecorator.bopFeatures.leafPilesPerChunk = 10;
        this.theBiomeDecorator.bopFeatures.deadLeafPilesPerChunk = 10;
        this.theBiomeDecorator.bopFeatures.algaePerChunk = 2;
        this.theBiomeDecorator.bopFeatures.flaxPerChunk = 1;
        
        this.theBiomeDecorator.bopFeatures.bopGrassPerChunk = 8;
        
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 1), 1D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 1), 0.5D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 2), 0.5D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 10), 0.5D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 11), 0.5D);
	}

	@Override
	public WorldGenAbstractTree func_150567_a(Random random)
	{
		return random.nextInt(15) == 0 ? new WorldGenOriginalTree(Blocks.log, Blocks.leaves, 0, 0, 0) : (random.nextInt(4) == 0 ? new WorldGenPineTree() : worldGeneratorTrees);
	}
	
	@Override
	public WorldGenerator getRandomWorldGenForGrass(Random random)
	{
		return random.nextInt(4) == 0 ? (random.nextInt(2) == 0 ? new WorldGenTallGrass(BOPCBlocks.foliage, 10) : new WorldGenTallGrass(BOPCBlocks.foliage, 11)) : new WorldGenTallGrass(Blocks.tallgrass, 1);
	}
	
	@Override
	public void decorate(World world, Random random, int chunkX, int chunkZ)
	{
		super.decorate(world, random, chunkX, chunkZ);
		int var5 = 12 + random.nextInt(6);

		for (int var6 = 0; var6 < var5; ++var6)
		{
			int x = chunkX + random.nextInt(16);
			int y = random.nextInt(28) + 4;
			int z = chunkZ + random.nextInt(16);

			Block block = world.getBlock(x, y, z);

			if (block != null && block.isReplaceableOreGen(world, x, y, z, Blocks.stone))
			{
				world.setBlock(x, y, z, Blocks.emerald_ore, 0, 2);
			}
		}
	}
}
