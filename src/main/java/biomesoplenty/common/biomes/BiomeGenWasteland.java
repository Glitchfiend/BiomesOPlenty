package biomesoplenty.common.biomes;

import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.configuration.BOPConfigurationMisc;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;
import biomesoplenty.common.world.features.trees.WorldGenDeadTree1;
import net.minecraft.init.Blocks;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import java.util.Random;

public class BiomeGenWasteland extends BOPBiome
{
    private static final Height biomeHeight = new Height(0.1F, 0.1F);

    public BiomeGenWasteland(int id)
    {
        super(id);

        this.setHeight(biomeHeight);
        this.setDisableRain();
        this.setColor(5919808);
        this.setTemperatureRainfall(2.0F, 0.05F);
        
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        
        this.waterColorMultiplier = 15073024;
        
        this.topBlock = BOPBlockHelper.get("driedDirt");
        this.fillerBlock = BOPBlockHelper.get("driedDirt");
        this.theBiomeDecorator.treesPerChunk = 0;
        this.theBiomeDecorator.grassPerChunk = 20;
        
       //TODO: FEATURE customBiomeDecorator.poisonWaterPerChunk = 10;
        this.bopWorldFeatures.setFeature("waterLakesPerChunk", 2);
        this.bopWorldFeatures.setFeature("wasteland1PerChunk", 1);
        this.bopWorldFeatures.setFeature("wasteland2PerChunk", 1);
        this.bopWorldFeatures.setFeature("wasteland3PerChunk", 1);
        this.bopWorldFeatures.setFeature("wasteland4PerChunk", 1);

        weightedGrassGen.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("plants"), 0), 1D);
    }

    @Override
    //TODO:                     getRandomWorldGenForTrees()
    public WorldGenAbstractTree func_150567_a(Random random)
    {
        return new WorldGenDeadTree1(false, Blocks.dirt, Blocks.grass, BOPBlockHelper.get("grass"), BOPBlockHelper.get("driedDirt"), BOPBlockHelper.get("redRock"));
    }

    @Override
    public int getBiomeGrassColor(int p_150558_1_, int p_150558_2_, int p_150558_3_)
    {
        return 10330232;
    }

    @Override
    public int getBiomeFoliageColor(int x, int y, int z)
    {
        return 10067541;
    }
    
    @Override
    public int getSkyColorByTemp(float par1)
    {
        if (BOPConfigurationMisc.skyColors) return 9477744;
        else return super.getSkyColorByTemp(par1);
    }

    /*@Override
    public int getFogColour()
    {
        return 12106885;
    }
    */

    /*@Override
    public float getFogCloseness()
    {
        // TODO Auto-generated method stub
        return 0.3F;
    }
    */
}
