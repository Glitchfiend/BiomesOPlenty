package biomesoplenty.common.biomes;

import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenWasteland extends BOPBiome
{

    public BiomeGenWasteland(int par1)
    {
        super(par1);
        /*
        topBlock = (byte) Blocks.driedDirt.get().blockID;
        fillerBlock = (byte) Blocks.driedDirt.get().blockID;
        theBiomeDecorator = new BiomeDecoratorBOP(this);
        customBiomeDecorator = (BiomeDecoratorBOP) theBiomeDecorator;
        customBiomeDecorator.treesPerChunk = 0;
        customBiomeDecorator.grassPerChunk = 20;
        customBiomeDecorator.deadGrassPerChunk = 14;
        customBiomeDecorator.poisonWaterPerChunk = 10;
        customBiomeDecorator.waterLakesPerChunk = 2;
        waterColorMultiplier = 15073024;
        spawnableCreatureList.clear();
        spawnableWaterCreatureList.clear();
        */
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    /*
    @Override
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
        return par1Random.nextInt(6) == 0 ? new WorldGenDeadTree3(false)
                : (par1Random.nextInt(2) == 0 ? new WorldGenWasteland2()
                        : new WorldGenWasteland());
    }
    */
    
	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
    /*
	@Override
	public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
	{
		return par1Random.nextInt(2) == 0 ? new WorldGenWasteland4() : new WorldGenWasteland3();
	}
	*/

    /**
     * Provides the basic grass color based on the biome temperature and
     * rainfall
     */
    /*
    @Override
    public int getBiomeGrassColor()
    {
        return 10330232;
    }
    */

    /**
     * Provides the basic foliage color based on the biome temperature and
     * rainfall
     */
    /*
    @Override
    public int getBiomeFoliageColor()
    {
        return 10067541;
    }
    */

    /**
     * Fog Color
     */
    /*
    @Override
    public int getFogColour()
    {
        return 12106885;
    }
    */

    /**
     * takes temperature, returns color
     */
    /*
    @Override
    public int getSkyColorByTemp(float par1)
    {
        if (BOPConfigurationMisc.skyColors)
        {
            return 9477744;
        }
        else
        {
            par1 /= 3.0F;

            if (par1 < -1.0F)
            {
                par1 = -1.0F;
            }

            if (par1 > 1.0F)
            {
                par1 = 1.0F;
            }

            return Color.getHSBColor(0.62222224F - par1 * 0.05F,
                    0.5F + par1 * 0.1F, 1.0F).getRGB();
        }
    }

    @Override
    public float getFogCloseness()
    {
        // TODO Auto-generated method stub
        return 0.3F;
    }
    */
}
