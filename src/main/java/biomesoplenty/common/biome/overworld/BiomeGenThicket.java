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
import biomesoplenty.api.utils.BiomeUtils;
import biomesoplenty.common.biome.BOPOverworldBiome;
import biomesoplenty.common.world.features.WorldGenBOPFlora;

public class BiomeGenThicket extends BOPOverworldBiome
{
	private static final Height biomeHeight = new Height(0.1F, 0.1F);
	
	public BiomeGenThicket(int id)
	{
		super(id);
		
        this.setHeight(biomeHeight);
        this.setColor(7248193);
        this.setTemperatureRainfall(0.6F, 0.2F);

		this.theBiomeDecorator.treesPerChunk = 17;
		this.theBiomeDecorator.grassPerChunk = 1;

		this.theBiomeDecorator.bopFeatures.gravelPerChunk = 6;
        this.theBiomeDecorator.bopFeatures.bopFlowersPerChunk = 5;
        this.theBiomeDecorator.bopFeatures.thornsPerChunk = 55;
        this.theBiomeDecorator.bopFeatures.shrubsPerChunk = 5;
        this.theBiomeDecorator.bopFeatures.leafPilesPerChunk = 5;
        this.theBiomeDecorator.bopFeatures.deadLeafPilesPerChunk = 10;

        this.theBiomeDecorator.bopFeatures.weightedFlowerGen.put(new WorldGenBOPFlora(Blocks.red_flower, 2), 4);
	}

	@Override
	public WorldGenAbstractTree func_150567_a(Random random)
	{
		return random.nextInt(5) == 0 ? worldGeneratorTrees : new WorldGenShrub(0, 0);
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

			if (block != null && block.isReplaceableOreGen(world, x, y, z, Blocks.stone) && BiomeUtils.oreWithMetaEnabled(14))
			{
				world.setBlock(x, y, z, BOPCBlocks.gemOre, 14, 2);
			}
		}
	}
	
	@Override
    public int getBiomeGrassColor(int p_150558_1_, int p_150558_2_, int p_150558_3_)
    {
		return 11049591;
	}

	@Override
    public int getBiomeFoliageColor(int x, int y, int z)
    {
		return 10854765;
	}
}
