package biomesoplenty.common.biome.overworld;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.api.utils.BiomeUtils;
import biomesoplenty.common.biome.BOPOverworldBiome;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;
import biomesoplenty.common.world.features.trees.WorldGenBOPHugeTree;
import biomesoplenty.common.world.features.trees.WorldGenBOPShrub;

public class BiomeGenDenseForest extends BOPOverworldBiome
{
    private static final Height biomeHeight = new Height(0.075F, 0.05F);
    
    public BiomeGenDenseForest(int id)
    {
        super(id);

        this.setHeight(biomeHeight);
        this.setColor(8246897);
        this.setTemperatureRainfall(0.7F, 0.7F);

        this.theBiomeDecorator.treesPerChunk = 7;
        this.theBiomeDecorator.grassPerChunk = 5;

        this.theBiomeDecorator.bopFeatures.bopFlowersPerChunk = 5;
        this.theBiomeDecorator.bopFeatures.bushesPerChunk = 1;
        this.theBiomeDecorator.bopFeatures.berryBushesPerChunk = 1;
        this.theBiomeDecorator.bopFeatures.shrubsPerChunk = 1;
        this.theBiomeDecorator.bopFeatures.waterReedsPerChunk = 2;
        this.theBiomeDecorator.bopFeatures.leafPilesPerChunk = 15;

        this.theBiomeDecorator.bopFeatures.bopGrassPerChunk = 10;

        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 10), 0.5D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 11), 0.5D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 2), 0.5D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 1), 1D);
    }

    @Override
    public WorldGenAbstractTree func_150567_a(Random random)
    {
        return random.nextInt(2) == 0 ? new WorldGenBOPHugeTree(Blocks.log, Blocks.leaves, 0, 0) : new WorldGenBOPShrub(Blocks.log, Blocks.leaves, 0, 0, Blocks.dirt, Blocks.grass);
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
    
    public void genTerrainBlocks(World p_150573_1_, Random p_150573_2_, Block[] p_150573_3_, byte[] p_150573_4_, int p_150573_5_, int p_150573_6_, double p_150573_7_)
    {

        if (p_150573_7_ > 1.75D)
        {
            this.topBlock = Blocks.dirt;
            this.field_150604_aj = 1;
        }
        else if (p_150573_7_ > -0.95D)
        {
            this.topBlock = Blocks.grass;
            this.field_150604_aj = 0;
        }

        this.genBiomeTerrain(p_150573_1_, p_150573_2_, p_150573_3_, p_150573_4_, p_150573_5_, p_150573_6_, p_150573_7_);
    }
    
    @Override
    public int getBiomeGrassColor(int p_150558_1_, int p_150558_2_, int p_150558_3_)
    {
		return 8246897;
	}

	@Override
    public int getBiomeFoliageColor(int x, int y, int z)
    {
		return 10022742;
	}
}
