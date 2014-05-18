package biomesoplenty.common.biomes.overworld.sub;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTaiga2;
import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.biomes.BOPSubBiome;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;
import biomesoplenty.common.world.features.trees.WorldGenBOPTaiga2;

public class BiomeGenSpruceWoods extends BOPSubBiome
{
    public BiomeGenSpruceWoods(int id)
    {
        super(id);
        
        this.zoom = 0.25D;
		this.threshold = 0.25D;

        this.setColor(6396257);
        this.setTemperatureRainfall(0.7F, 0.8F);

        this.spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 8, 4, 4));

        this.theBiomeDecorator.treesPerChunk = 20;
        this.theBiomeDecorator.grassPerChunk = 6;
        this.theBiomeDecorator.mushroomsPerChunk = 4;

        this.bopWorldFeatures.setFeature("poisonIvyPerChunk", 1);
        this.bopWorldFeatures.setFeature("sproutsPerChunk", 3);
        this.bopWorldFeatures.setFeature("berryBushesPerChunk", 3);
        this.bopWorldFeatures.setFeature("wildCarrotsPerChunk", 1);
        this.bopWorldFeatures.setFeature("shrubsPerChunk", 5);
        this.bopWorldFeatures.setFeature("waterReedsPerChunk", 2);
        this.bopWorldFeatures.setFeature("leafPilesPerChunk", 6);
        this.bopWorldFeatures.setFeature("deadLeafPilesPerChunk", 3);
        this.bopWorldFeatures.setFeature("algaePerChunk", 2);

        this.bopWorldFeatures.setFeature("bopGrassPerChunk", 100);

        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 10), 0.5D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 11), 0.5D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 1), 1D);
    }

    @Override
    //TODO:                     getRandomWorldGenForTrees()
    public WorldGenAbstractTree func_150567_a(Random random)
    {
        return random.nextInt(3) == 0 ? new WorldGenBOPTaiga2(Blocks.log, Blocks.leaves, 1, 1, false, 9, 9, 6) : 
        (random.nextInt(2) == 0 ? worldGeneratorTrees : new WorldGenTaiga2(false));
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
				world.setBlock(x, y, z, BOPBlockHelper.get("gemOre"), 14, 2);
			}
		}
	}
}
