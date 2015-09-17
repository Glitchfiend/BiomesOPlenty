package biomesoplenty.common.biome.overworld;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase.Height;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.api.utils.BiomeUtils;
import biomesoplenty.common.biome.BOPOverworldBiome;
import biomesoplenty.common.world.features.WorldGenBOPDoubleFlora;
import biomesoplenty.common.world.features.WorldGenBOPFlora;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;
import biomesoplenty.common.world.features.trees.WorldGenMiniShrub;
import biomesoplenty.common.world.features.trees.WorldGenPoplar;
import biomesoplenty.common.world.features.trees.WorldGenPoplar2;

public class BiomeGenGrove extends BOPOverworldBiome
{
    private static final Height biomeHeight = new Height(0.1F, 0.2F);

	public BiomeGenGrove(int id)
	{
		super(id);
		
        this.setHeight(biomeHeight);
        this.setColor(5341009);
        this.setTemperatureRainfall(0.7F, 0.8F);

		this.theBiomeDecorator.treesPerChunk = 3;
		this.theBiomeDecorator.flowersPerChunk = 5;
		this.theBiomeDecorator.grassPerChunk = 8;

        this.theBiomeDecorator.bopFeatures.bopFlowersPerChunk = 50;
        this.theBiomeDecorator.bopFeatures.sproutsPerChunk = 1;
        this.theBiomeDecorator.bopFeatures.berryBushesPerChunk = 2;
        this.theBiomeDecorator.bopFeatures.shrubsPerChunk = 3;
        this.theBiomeDecorator.bopFeatures.cloverPatchesPerChunk = 20;
        this.theBiomeDecorator.bopFeatures.leafPilesPerChunk = 5;
        this.theBiomeDecorator.bopFeatures.generatePumpkins = false;

        this.theBiomeDecorator.bopFeatures.bopGrassPerChunk = 8;

        this.theBiomeDecorator.bopFeatures.weightedFlowerGen.put(new WorldGenBOPFlora(BOPCBlocks.flowers, 0), 16);
        this.theBiomeDecorator.bopFeatures.weightedFlowerGen.put(new WorldGenBOPFlora(BOPCBlocks.flowers, 9), 6);
        this.theBiomeDecorator.bopFeatures.weightedFlowerGen.put(new WorldGenBOPDoubleFlora(5, 3), 4);

        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 1), 0.5D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 2), 1D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 10), 0.5D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 11), 0.5D);
	}

	@Override
    public WorldGenAbstractTree func_150567_a(Random random)
    {
		return random.nextInt(2) == 0 ? new WorldGenMiniShrub(Blocks.log, Blocks.leaves, 0, 0, Blocks.grass, Blocks.sand) : random.nextInt(3) == 0 ? new WorldGenPoplar2() : new WorldGenPoplar();
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
		return d0 < -0.1D ? 5341009 : 6331992;
	}

	@Override
	public int getBiomeFoliageColor(int x, int y, int z)
	{
		double d0 = plantNoise.func_151601_a((double)x * 0.0225D, (double)z * 0.0225D);
		return d0 < -0.1D ? 6396257 : 7714153;
	}
}
