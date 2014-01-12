package biomesoplenty.common.biomes;

import java.util.HashMap;
import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase.Height;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.configuration.BOPConfigurationMisc;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;
import biomesoplenty.common.world.features.trees.WorldGenDeadTree1;

public class BiomeGenWasteland extends BOPBiome
{
    private static final Height biomeHeight = new Height(0.3F, 0.4F);

    public BiomeGenWasteland(int id)
    {
        super(id);
        
        //TODO: setHeight()
        this.func_150570_a(biomeHeight);
        this.setDisableRain();
        //TODO: setColor()
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
        this.bopWorldFeatures.waterLakesPerChunk = 2;
        this.bopWorldFeatures.wasteland1PerChunk = 1;
        this.bopWorldFeatures.wasteland2PerChunk = 1;
        this.bopWorldFeatures.wasteland3PerChunk = 1;
        this.bopWorldFeatures.wasteland4PerChunk = 1;
    }

    @Override
    //TODO:                     getRandomWorldGenForTrees()
    public WorldGenAbstractTree func_150567_a(Random random)
    {
        return new WorldGenDeadTree1(false, Blocks.dirt, Blocks.grass, BOPBlockHelper.get("holyGrass"), BOPBlockHelper.get("driedDirt"), BOPBlockHelper.get("redRock"));
    }
    
    @Override
    public HashMap<WorldGenerator, Double> getWeightedWorldGenForGrass()
    {
        HashMap<WorldGenerator, Double> grassMap = new HashMap();
        
        grassMap.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("plants"), 0), 1D);
        
        return grassMap;
    }

    @Override
    //TODO:     getBiomeGrassColor()
    public int func_150558_b(int p_150558_1_, int p_150558_2_, int p_150558_3_)
    {
        return 10330232;
    }

    @Override
    //TODO:     getBiomeFoliageColor()
    public int func_150571_c(int x, int y, int z)
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
