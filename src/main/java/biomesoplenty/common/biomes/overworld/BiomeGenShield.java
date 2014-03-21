package biomesoplenty.common.biomes.overworld;

import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.biomes.BOPBiome;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;
import biomesoplenty.common.world.features.WorldGenMoss;
import biomesoplenty.common.world.features.trees.WorldGenBOPTaiga2;
import biomesoplenty.common.world.features.trees.WorldGenPineTree;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenShrub;

import java.util.Random;

public class BiomeGenShield extends BOPBiome
{
    private static final Height biomeHeight = new Height(0.0F, 0.2F);

	public BiomeGenShield(int id)
	{
		super(id);

        this.setHeight(biomeHeight);
        this.setColor(6586168);
        this.setTemperatureRainfall(0.5F, 0.8F);

		this.theBiomeDecorator.treesPerChunk = 7;
		this.theBiomeDecorator.grassPerChunk = 12;
		this.theBiomeDecorator.mushroomsPerChunk = 4;
		this.theBiomeDecorator.sandPerChunk = -999;
		this.theBiomeDecorator.sandPerChunk2 = -999;

        this.bopWorldFeatures.setFeature("shrubsPerChunk", 4);
        this.bopWorldFeatures.setFeature("waterReedsPerChunk", 4);
        this.bopWorldFeatures.setFeature("leafPilesPerChunk", 10);
        this.bopWorldFeatures.setFeature("deadLeafPilesPerChunk", 5);
        this.bopWorldFeatures.setFeature("seaweedPerChunk", 5);
        this.bopWorldFeatures.setFeature("generateStoneInGrass2", true);

        this.bopWorldFeatures.setFeature("bopGrassPerChunk", 12);

        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 10), 0.5D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 11), 0.5D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 1), 1D);
	}

	@Override
    //TODO:                     getRandomWorldGenForTrees()
    public WorldGenAbstractTree func_150567_a(Random random)
    {
		return random.nextInt(2) == 0 ? new WorldGenShrub(0, 0) : 
		(random.nextInt(4) == 0 ? new WorldGenPineTree() : 
		(random.nextInt(6) == 0 ? new WorldGenBOPTaiga2(BOPBlockHelper.get("logs1"), BOPBlockHelper.get("leaves2"), 3, 1, false, 10, 10, 5) : 
		new WorldGenBOPTaiga2(Blocks.log, Blocks.leaves, 1, 1, false, 9, 9, 6)));
	}

    @Override
    public void decorate(World world, Random random, int chunkX, int chunkZ)
    {
        super.decorate(world, random, chunkX, chunkZ);

        for (int i = 0; i < 20; i++)
        {
            int x = chunkX + random.nextInt(16) + 8;
            short y = 58;
            int z = chunkZ + random.nextInt(16) + 8;

            new WorldGenMoss().generate(world, random, x, y, z);
        }
    }

	@Override
    public int getBiomeGrassColor(int x, int y, int z)
    {
		return 6586168;
	}
	
	@Override
    public int getBiomeFoliageColor(int x, int y, int z)
    {
		return 7902787;
	}
}
