package biomesoplenty.common.biomes;

import java.util.HashMap;

import net.minecraft.init.Blocks;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.configuration.BOPConfigurationMisc;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;

public class BiomeGenWasteland extends BOPBiome
{

    public BiomeGenWasteland(int id)
    {
        super(id);
        
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        
        this.waterColorMultiplier = 15073024;
        
        this.topBlock = BOPBlockHelper.get("driedDirt");
        this.fillerBlock = BOPBlockHelper.get("driedDirt");
        this.theBiomeDecorator.treesPerChunk = 0;
        this.theBiomeDecorator.grassPerChunk = 20;
        
       //TODO: FEATURE customBiomeDecorator.poisonWaterPerChunk = 10;
        this.bopWorldFeatures.waterLakesPerChunk = 2;
    }

    /*@Override
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
        return par1Random.nextInt(6) == 0 ? new WorldGenDeadTree3(false)
                : (par1Random.nextInt(2) == 0 ? new WorldGenWasteland2()
                        : new WorldGenWasteland());
    }*/
    
    @Override
    public HashMap<WorldGenerator, Double> getWeightedWorldGenForGrass()
    {
        HashMap<WorldGenerator, Double> grassMap = new HashMap();
        
        grassMap.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("plants"), 0), 1D);
        
        return grassMap;
    }
    
    
	/*@Override
	public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
	{
		return par1Random.nextInt(2) == 0 ? new WorldGenWasteland4() : new WorldGenWasteland3();
	}*/

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
