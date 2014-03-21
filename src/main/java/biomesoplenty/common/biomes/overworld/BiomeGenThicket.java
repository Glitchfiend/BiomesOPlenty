package biomesoplenty.common.biomes.overworld;

import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.biomes.BOPBiome;
import biomesoplenty.common.world.features.WorldGenBOPFlora;
import net.minecraft.init.Blocks;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class BiomeGenThicket extends BOPBiome
{
	private static final Height biomeHeight = new Height(0.1F, 0.1F);
	
	public BiomeGenThicket(int id)
	{
		super(id);
		
        //TODO: setHeight()
        this.setHeight(biomeHeight);
        //TODO:	setColor()
        this.setColor(7248193);
        this.setTemperatureRainfall(0.6F, 0.2F);

		this.theBiomeDecorator.treesPerChunk = 17;
		this.theBiomeDecorator.grassPerChunk = 1;

        this.bopWorldFeatures.setFeature("bopFlowersPerChunk", 5);
        this.bopWorldFeatures.setFeature("thornsPerChunk", 55);
        this.bopWorldFeatures.setFeature("shrubsPerChunk", 5);
        this.bopWorldFeatures.setFeature("leafPilesPerChunk", 5);
        this.bopWorldFeatures.setFeature("deadLeafPilesPerChunk", 10);

        this.bopWorldFeatures.weightedFlowerGen.put(new WorldGenBOPFlora(Blocks.red_flower, 2), 4);
	}

	@Override
	//TODO:						getRandomWorldGenForTrees()
	public WorldGenAbstractTree func_150567_a(Random random)
	{
		return random.nextInt(5) == 0 ? worldGeneratorTrees : new WorldGenShrub(0, 0);
	}

	@Override
	public WorldGenerator getRandomWorldGenForGrass(Random random)
	{
		return random.nextInt(4) == 0 ? new WorldGenTallGrass(Blocks.tallgrass, 1) : new WorldGenTallGrass(BOPBlockHelper.get("foliage"), 10);
	}
	
	@Override
    //TODO:     getBiomeGrassColor()
    public int getBiomeGrassColor(int p_150558_1_, int p_150558_2_, int p_150558_3_)
    {
		return 11049591;
	}

	@Override
    //TODO:     getBiomeFoliageColor()
    public int getBiomeFoliageColor(int x, int y, int z)
    {
		return 10854765;
	}
}
