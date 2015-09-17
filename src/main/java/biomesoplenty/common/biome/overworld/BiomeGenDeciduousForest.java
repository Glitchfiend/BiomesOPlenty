package biomesoplenty.common.biome.overworld;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenShrub;
import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.api.utils.BiomeUtils;
import biomesoplenty.common.biome.BOPOverworldBiome;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;
import biomesoplenty.common.world.features.trees.WorldGenBulbTree;

public class BiomeGenDeciduousForest extends BOPOverworldBiome
{
	//private static final Height biomeHeight = new Height(); Not set?
	
	public BiomeGenDeciduousForest(int id)
	{
		super(id);
		
        //this.setHeight(biomeHeight); Not set?
        this.setColor(12695369);
        this.setTemperatureRainfall(0.6F, 0.8F);
		
		this.theBiomeDecorator.treesPerChunk = 18;
		this.theBiomeDecorator.grassPerChunk = 10;
		this.theBiomeDecorator.flowersPerChunk = -999;

        this.theBiomeDecorator.bopFeatures.toadstoolsPerChunk = 1;
        this.theBiomeDecorator.bopFeatures.bushesPerChunk = 8;
        this.theBiomeDecorator.bopFeatures.berryBushesPerChunk = 2;
        this.theBiomeDecorator.bopFeatures.blueMilksPerChunk = 2;
        this.theBiomeDecorator.bopFeatures.poisonIvyPerChunk = 1;
        this.theBiomeDecorator.bopFeatures.shrubsPerChunk = 10;
        this.theBiomeDecorator.bopFeatures.waterReedsPerChunk = 2;
        this.theBiomeDecorator.bopFeatures.leafPilesPerChunk = 10;
        this.theBiomeDecorator.bopFeatures.deadLeafPilesPerChunk = 10;
        this.theBiomeDecorator.bopFeatures.algaePerChunk = 3;
        this.theBiomeDecorator.bopFeatures.flaxPerChunk = 1;

        this.theBiomeDecorator.bopFeatures.bopGrassPerChunk = 10;

        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 1), 1D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 2), 1D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 10), 0.5D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 11), 0.5D);
	}

	@Override
	public WorldGenAbstractTree func_150567_a(Random random)
	{
		return random.nextInt(6) == 0 ? new WorldGenShrub(2,2) : (random.nextInt(3) == 0 ? new WorldGenShrub(0,0) : new WorldGenBulbTree(Blocks.log, Blocks.leaves, 0, 0, false, 10, 15, false));
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

			if (block != null && block.isReplaceableOreGen(world, x, y, z, Blocks.stone) && BiomeUtils.oreWithMetaEnabled(14))
			{
				world.setBlock(x, y, z, BOPCBlocks.gemOre, 14, 2);
			}
		}
	}

	@Override
    public int getBiomeGrassColor(int x, int y, int z)
    {
		double d0 = plantNoise.func_151601_a((double)x * 0.0225D, (double)z * 0.0225D);
		return d0 < -0.1D ? 12695369 : 11374145;
	}

	@Override
	public int getBiomeFoliageColor(int x, int y, int z)
	{
		double d0 = plantNoise.func_151601_a((double)x * 0.0225D, (double)z * 0.0225D);
		return d0 < -0.1D ? 12896570 : 11510344;
	}
}
