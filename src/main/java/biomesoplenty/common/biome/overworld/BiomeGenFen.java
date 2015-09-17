package biomesoplenty.common.biome.overworld;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.api.utils.BiomeUtils;
import biomesoplenty.client.fog.IBiomeFog;
import biomesoplenty.common.biome.BOPOverworldBiome;
import biomesoplenty.common.world.features.WorldGenBOPDoubleFlora;
import biomesoplenty.common.world.features.WorldGenBOPFlora;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;
import biomesoplenty.common.world.features.WorldGenMoss;
import biomesoplenty.common.world.features.trees.WorldGenBOPTaiga1;
import biomesoplenty.common.world.features.trees.WorldGenBOPTaiga2;
import biomesoplenty.common.world.features.trees.WorldGenDeadTree;

public class BiomeGenFen extends BOPOverworldBiome implements IBiomeFog
{
    private static final Height biomeHeight = new Height(0.1F, 0.1F);
    
	public BiomeGenFen(int id)
	{
		super(id);

        this.setHeight(biomeHeight);
        this.setColor(12240001);
        this.setTemperatureRainfall(0.4F, 0.4F);

		this.theBiomeDecorator.treesPerChunk = 10;
		this.theBiomeDecorator.mushroomsPerChunk = 8;
		this.theBiomeDecorator.grassPerChunk = 5;
		this.theBiomeDecorator.sandPerChunk = -999;
		this.theBiomeDecorator.sandPerChunk2 = -999;
		this.theBiomeDecorator.waterlilyPerChunk = 2;

		this.theBiomeDecorator.bopFeatures.bopLilyPerChunk = 2;
		this.theBiomeDecorator.bopFeatures.gravelPerChunk = 2;
        this.theBiomeDecorator.bopFeatures.bopFlowersPerChunk = 5;
        this.theBiomeDecorator.bopFeatures.cattailsPerChunk = 1;
        this.theBiomeDecorator.bopFeatures.highCattailsPerChunk = 1;
        this.theBiomeDecorator.bopFeatures.waterSpringsPerChunk = 99;
        this.theBiomeDecorator.bopFeatures.toadstoolsPerChunk = 2;
        this.theBiomeDecorator.bopFeatures.mudPerChunk = 1;
        this.theBiomeDecorator.bopFeatures.riverCanePerChunk = 5;
        this.theBiomeDecorator.bopFeatures.algaePerChunk = 3;
        this.theBiomeDecorator.bopFeatures.portobellosPerChunk = 1;
        this.theBiomeDecorator.bopFeatures.waterReedsPerChunk = 10;
        this.theBiomeDecorator.bopFeatures.koruPerChunk = 1;
        this.theBiomeDecorator.bopFeatures.shrubsPerChunk = 7;
        this.theBiomeDecorator.bopFeatures.deadLeafPilesPerChunk = 10;
        this.theBiomeDecorator.bopFeatures.seaweedPerChunk = 15;

        this.theBiomeDecorator.bopFeatures.bopGrassPerChunk = 15;

        this.theBiomeDecorator.bopFeatures.weightedFlowerGen.put(new WorldGenBOPFlora(Blocks.red_flower, 2), 6);

        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 1), 0.5D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 1), 1D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 2), 0.5D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 10), 0.5D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 11), 0.5D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPDoubleFlora(2), 0.25D);
	}

	@Override
	public WorldGenAbstractTree func_150567_a(Random random)
	{
		return random.nextInt(3) == 0 ? new WorldGenBOPTaiga2(Blocks.log2, BOPCBlocks.leaves2, 1, 0, false, 10, 12, 3, -1) : 
		(random.nextInt(20) == 0 ? new WorldGenDeadTree() : 
		new WorldGenBOPTaiga1(Blocks.log2, Blocks.leaves2, 1, 1, false, 7, 5, 1));
	}

	@Override
	public void decorate(World world, Random random, int chunkX, int chunkZ)
	{
		super.decorate(world, random, chunkX, chunkZ);
		int var5 = 12 + random.nextInt(6);

		for (int i = 0; i < var5; ++i)
		{
			int x = chunkX + random.nextInt(16);
			int y = random.nextInt(28) + 4;
			int z = chunkZ + random.nextInt(16);

			Block block = world.getBlock(x, y, z);

			if (block != null && block.isReplaceableOreGen(world, x, y, z, Blocks.stone) && BiomeUtils.oreWithMetaEnabled(10))
			{
				world.setBlock(x, y, z, BOPCBlocks.gemOre, 10, 2);
			}
		}

		for (int i = 0; i < 20; i++)
		{
			int x = chunkX + random.nextInt(16) + 8;
            short y = 58;
			int z = chunkZ + random.nextInt(16) + 8;

			new WorldGenMoss().generate(world, random, x, y, z);
		}
	}

	@Override
    public int getBiomeGrassColor(int p_150558_1_, int p_150558_2_, int p_150558_3_)
    {
		return 12240001;
	}

	@Override
	public int getBiomeFoliageColor(int x, int y, int z)
	{
		return 13547897;
	}

	@Override
	public int getFogColour(int x, int y, int z)
	{
		return 12638463;
	}
	
	@Override
	public float getFogDensity(int x, int y, int z)
	{
	    return 0.8F;
	}
}
