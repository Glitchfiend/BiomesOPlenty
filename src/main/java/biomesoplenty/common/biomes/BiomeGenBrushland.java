package biomesoplenty.common.biomes;

import java.util.HashMap;
import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;
import biomesoplenty.common.world.features.trees.WorldGenBrush1;
import biomesoplenty.common.world.features.trees.WorldGenBrush2;
import biomesoplenty.common.world.features.trees.WorldGenChaparral2;

public class BiomeGenBrushland extends BOPBiome
{
	private static final Height biomeHeight = new Height(0.3F, 0.3F);
	
	public BiomeGenBrushland(int id)
	{
		super(id);
		
        //TODO: setHeight()
        this.func_150570_a(biomeHeight);
        //TODO:	setColor()
        this.setColor(13222271);
        this.setTemperatureRainfall(2.0F, 0.05F);

		this.theBiomeDecorator.treesPerChunk = 10;
		this.theBiomeDecorator.grassPerChunk = 6;
		this.theBiomeDecorator.flowersPerChunk = -999;
		
		this.bopWorldFeatures.thornsPerChunk = 4;
		this.bopWorldFeatures.shrubsPerChunk = 30;
		this.bopWorldFeatures.waterReedsPerChunk = 2;
		this.bopWorldFeatures.generateQuicksand = true;
	}

	@Override
	//TODO:						getRandomWorldGenForTrees()
	public WorldGenAbstractTree func_150567_a(Random random)
	{
		return random.nextInt(2) == 0 ? new WorldGenBrush2() : (random.nextInt(5) == 0 ?  new WorldGenBrush1() : new WorldGenChaparral2());
	}
	
    @Override
	public HashMap<WorldGenerator, Double> getWeightedWorldGenForGrass()
    {
    	HashMap<WorldGenerator, Double> grassMap = new HashMap();
    	
    	grassMap.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 10), 0.5D);
    	grassMap.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 11), 0.5D);
    	grassMap.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 1), 1D);
    	
    	return grassMap;
    }

	@Override
	//TODO:		getBiomeGrassColor()
    public int func_150558_b(int p_150558_1_, int p_150558_2_, int p_150558_3_)
    {
		return 13222271;
	}

	@Override
	//TODO:		getBiomeFoliageColor()
	public int func_150571_c(int x, int y, int z)
	{
		return 11716223;
	}
}
