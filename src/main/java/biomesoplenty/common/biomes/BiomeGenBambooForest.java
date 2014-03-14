package biomesoplenty.common.biomes;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.world.features.WorldGenBOPDoubleFlora;
import biomesoplenty.common.world.features.trees.WorldGenBulbTree;

public class BiomeGenBambooForest extends BOPBiome
{
	private static final Height biomeHeight = new Height(0.1F, 0.3F);
	
	public BiomeGenBambooForest(int id)
	{
		super(id);

        //TODO: setHeight()
        this.setHeight(biomeHeight);
        //TODO:	setColor()
        this.setColor(10739795);
        this.setTemperatureRainfall(1.2F, 0.9F);
        
		this.theBiomeDecorator.treesPerChunk = 30;
		this.theBiomeDecorator.grassPerChunk = 5;
		this.theBiomeDecorator.flowersPerChunk = -999;
		
		this.bopWorldFeatures.riverCanePerChunk = 6;
		this.bopWorldFeatures.shrubsPerChunk = 6;
		this.bopWorldFeatures.bushesPerChunk = 5;
		this.bopWorldFeatures.leafPilesPerChunk = 15;
		
		this.bopWorldFeatures.generatePumpkins = false;
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
			
			//TODO:				getBlock()
			Block block = world.getBlock(x, y, z);

			if (block != null && block.isReplaceableOreGen(world, x, y, z, Blocks.stone))
			{
				//TODO:	setBlock()
				world.setBlock(x, y, z, BOPBlockHelper.get("gemOre"), 6, 2);
			}
		}
	}
	
    public void genTerrainBlocks(World p_150573_1_, Random p_150573_2_, Block[] p_150573_3_, byte[] p_150573_4_, int p_150573_5_, int p_150573_6_, double p_150573_7_)
    {
        this.topBlock = Blocks.dirt;
        this.field_150604_aj = 2;

        this.genBiomeTerrain(p_150573_1_, p_150573_2_, p_150573_3_, p_150573_4_, p_150573_5_, p_150573_6_, p_150573_7_);
    }

	@Override
	//TODO:						getRandomWorldGenForTrees()
	public WorldGenAbstractTree func_150567_a(Random random)
	{
		return random.nextInt(3) == 0 ? new WorldGenShrub(0, 0) : new WorldGenBulbTree(BOPBlockHelper.get("bamboo"), BOPBlockHelper.get("leaves1"), 0, 1, false, 10, 12, false);
	}

	@Override
	public WorldGenerator getRandomWorldGenForGrass(Random random)
	{
		return random.nextInt(4) == 0 ? new WorldGenTallGrass(Blocks.tallgrass, 2) : (random.nextInt(2) == 0 ? new WorldGenTallGrass(BOPBlockHelper.get("foliage"), 10) : (random.nextInt(2) == 0 ? new WorldGenTallGrass(BOPBlockHelper.get("foliage"), 11) : (random.nextInt(8) == 0 ? new WorldGenBOPDoubleFlora(3) : new WorldGenTallGrass(BOPBlockHelper.get("foliage"), 1))));
	}

	@Override
	//TODO:		getBiomeGrassColor()
    public int getBiomeGrassColor(int p_150558_1_, int p_150558_2_, int p_150558_3_)
    {
        double d0 = plantNoise.func_151601_a((double)p_150558_1_ * 0.0225D, (double)p_150558_3_ * 0.0225D);
        return d0 < -0.7D ? 13949781 : (d0 < -0.3 ? 12311892 : 10739795);
    }

	@Override
	//TODO:		getBiomeFoliageColor()
	public int getBiomeFoliageColor(int x, int y, int z)
	{
		double d0 = plantNoise.func_151601_a((double)x * 0.0225D, (double)z * 0.0225D);
        return d0 < -0.7D ? 13949781 : (d0 < -0.3 ? 12311892 : 10739795);
	}

	/*@Override
	public int getFogColour()
	{
		return 13428852;
	}

    @Override
    public float getFogCloseness()
    {
        // TODO Auto-generated method stub
        return 0.8F;
    }
	 */
}
