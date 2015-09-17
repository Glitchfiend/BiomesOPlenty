package biomesoplenty.common.biome.overworld;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase.Height;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.common.biome.BOPOverworldBiome;
import biomesoplenty.common.configuration.BOPConfigurationMisc;
import biomesoplenty.common.world.features.WorldGenBOPDoubleFlora;
import biomesoplenty.common.world.features.trees.WorldGenPineTree;

public class BiomeGenJadeCliffs extends BOPOverworldBiome
{
	private static final Height biomeHeight = new Height(0.5F, 1.0F);
	
	public BiomeGenJadeCliffs(int id)
	{
		super(id);

        this.setHeight(biomeHeight);
        this.setColor(9096298);
        this.setTemperatureRainfall(0.8F, 0.9F);

		this.theBiomeDecorator.treesPerChunk = 12;
		this.theBiomeDecorator.grassPerChunk = 3;

        this.theBiomeDecorator.bopFeatures.bopFlowersPerChunk = 3;
        this.theBiomeDecorator.bopFeatures.wildCarrotsPerChunk = 1;
        this.theBiomeDecorator.bopFeatures.leafPilesPerChunk = 4;
        this.theBiomeDecorator.bopFeatures.deadLeafPilesPerChunk = 2;

        this.theBiomeDecorator.bopFeatures.bopGrassPerChunk = 3;

        this.theBiomeDecorator.bopFeatures.weightedFlowerGen.put(new WorldGenBOPDoubleFlora(1, 5), 6);
	}
	
	@Override
	public WorldGenAbstractTree func_150567_a(Random random)
	{
		return random.nextInt(4) == 0 ? new WorldGenShrub(0, 1) : new WorldGenPineTree();
	}

	@Override
	public WorldGenerator getRandomWorldGenForGrass(Random random)
	{
		return random.nextInt(4) == 0 ? new WorldGenTallGrass(Blocks.tallgrass, 1) : new WorldGenTallGrass(BOPCBlocks.foliage, 10);
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

	@Override
	public int getSkyColorByTemp(float par1)
	{
		if (BOPConfigurationMisc.skyColors) return 12045485;
		else return super.getSkyColorByTemp(par1);
	}

	@Override
	public int getBiomeGrassColor(int x, int y, int z)
	{
		return 8168808;
	}

	@Override
	public int getBiomeFoliageColor(int x, int y, int z)
	{
		return 9096298;
	}
}
