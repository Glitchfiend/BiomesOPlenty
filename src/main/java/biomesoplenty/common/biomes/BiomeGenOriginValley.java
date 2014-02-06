package biomesoplenty.common.biomes;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.configuration.BOPConfigurationMisc;
import biomesoplenty.common.world.features.trees.WorldGenOriginalTree;

public class BiomeGenOriginValley extends BOPBiome
{
    private static final Height biomeHeight = new Height(0.1F, 0.3F);

	public BiomeGenOriginValley(int id)
	{
		super(id);
		
        //TODO: setHeight()
        this.setHeight(biomeHeight);
        //TODO: setColor()
        this.setColor(10341485);
        this.setTemperatureRainfall(0.7F, 0.8F);
		
		this.topBlock = BOPBlockHelper.get("originGrass");
		this.theBiomeDecorator.treesPerChunk = 4;
		this.theBiomeDecorator.grassPerChunk = -999;
		this.theBiomeDecorator.sandPerChunk = 0;
		this.theBiomeDecorator.sandPerChunk2 = 0;
		this.theBiomeDecorator.clayPerChunk = 0;

		this.bopWorldFeatures.rootsPerChunk = -999;
		this.bopWorldFeatures.stalagmitesPerChunk = -999;
		this.bopWorldFeatures.stalactitesPerChunk = -999;
		this.bopWorldFeatures.minersDelightPerChunk = -999;
		//TODO: FEATURE this.theBiomeDecorator.generateUndergroundLakes = false;
	    this.bopWorldFeatures.generatePumpkins = false;
	}

	@Override
    //TODO:                     getRandomWorldGenForTrees()
    public WorldGenAbstractTree func_150567_a(Random random)
    {
		return new WorldGenOriginalTree(Blocks.log, BOPBlockHelper.get("leaves3"), 0, 0, false, 5, 3, false);
	}

	@Override
    //TODO:     getBiomeGrassColor()
    public int getBiomeGrassColor(int p_150558_1_, int p_150558_2_, int p_150558_3_)
    {
		return 10682207;
	}

	@Override
    //TODO:     getBiomeFoliageColor()
    public int getBiomeFoliageColor(int x, int y, int z)
    {
		return 3866368;
	}

	@Override
	public int getSkyColorByTemp(float par1)
	{
		if (BOPConfigurationMisc.skyColors) return 8441086;
		else return super.getSkyColorByTemp(par1);
	}
}
