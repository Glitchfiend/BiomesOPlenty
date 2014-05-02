package biomesoplenty.common.biomes.overworld;

import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.biomes.BOPBiome;
import biomesoplenty.common.world.features.WorldGenBOPDoubleFlora;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;
import biomesoplenty.common.world.features.trees.WorldGenBOPTaiga2;
import biomesoplenty.common.world.features.trees.WorldGenBOPTaiga3;
import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import java.util.Random;

public class BiomeGenConiferousForest extends BOPBiome
{
    private static final Height biomeHeight = new Height(0.1F, 0.3F);

	public BiomeGenConiferousForest(int id)
	{
		super(id);
		
        //TODO: setHeight()
        this.setHeight(biomeHeight);
        //TODO: setColor()
        this.setColor(5410656);
        this.setTemperatureRainfall(0.5F, 0.5F);

		this.spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 8, 4, 4));

		this.theBiomeDecorator.treesPerChunk = 8;
		this.theBiomeDecorator.grassPerChunk = 10;
		this.theBiomeDecorator.mushroomsPerChunk = 8;
		//                     gravelPerChunk
		this.theBiomeDecorator.sandPerChunk = -999;
		this.theBiomeDecorator.sandPerChunk2 = -999;

        this.bopWorldFeatures.setFeature("toadstoolsPerChunk", 3);
        this.bopWorldFeatures.setFeature("blueMilksPerChunk", 1);
        this.bopWorldFeatures.setFeature("poisonIvyPerChunk", 1);
        this.bopWorldFeatures.setFeature("berryBushesPerChunk", 1);
        this.bopWorldFeatures.setFeature("shrubsPerChunk", 8);
        this.bopWorldFeatures.setFeature("waterReedsPerChunk", 2);
        this.bopWorldFeatures.setFeature("cloverPatchesPerChunk", 10);
        this.bopWorldFeatures.setFeature("leafPilesPerChunk", 4);
        this.bopWorldFeatures.setFeature("deadLeafPilesPerChunk", 8);
        this.bopWorldFeatures.setFeature("seaweedPerChunk", 5);
        this.bopWorldFeatures.setFeature("algaePerChunk", 2);

        this.bopWorldFeatures.setFeature("bopGrassPerChunk", 10);

        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 1), 0.5D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 2), 1D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 10), 0.5D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 11), 0.5D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 2), 0.5D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPDoubleFlora(3, 64), 0.5D);
	}

	@Override
    //TODO:                     getRandomWorldGenForTrees()
    public WorldGenAbstractTree func_150567_a(Random random)
    {
		return random.nextInt(6) == 0 ? new WorldGenBOPTaiga2(BOPBlockHelper.get("logs1"), BOPBlockHelper.get("leaves2"), 3, 1, false, 10, 10, 5) :
		(random.nextInt(3) == 0 ? new WorldGenBOPTaiga2(BOPBlockHelper.get("logs1"), BOPBlockHelper.get("leaves2"), 3, 1, false, 20, 15, 4) : 
		new WorldGenBOPTaiga3(BOPBlockHelper.get("logs1"), BOPBlockHelper.get("leaves2"), 3, 1, false, 35, 10, 0));
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
            
            //TODO:             getBlock()
            Block block = world.getBlock(x, y, z);

            if (block != null && block.isReplaceableOreGen(world, x, y, z, Blocks.stone))
            {
                //TODO: setBlock()
                world.setBlock(x, y, z, Blocks.emerald_ore, 0, 2);
            }
        }
    }
}
