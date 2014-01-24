package biomesoplenty.common.world.forceddecorators;

import java.util.HashMap;
import java.util.Random;

import net.minecraft.world.gen.feature.WorldGenerator;

import biomesoplenty.common.world.decoration.BOPWorldFeatures;
import biomesoplenty.common.world.decoration.IBOPDecoration;
import biomesoplenty.common.world.features.WorldGenBOPFlora;

public class ForcedDecorator implements IBOPDecoration
{
	protected BOPWorldFeatures bopWorldFeatures;
	
	public ForcedDecorator()
	{
		bopWorldFeatures = new BOPWorldFeatures();
	}
	
	@Override
	public WorldGenBOPFlora getRandomWorldGenForBOPFlowers(Random random) 
	{
		return null;
	}

	@Override
	public BOPWorldFeatures getWorldFeatures() 
	{
		return bopWorldFeatures;
	}
	
    @Override
    public HashMap<WorldGenerator, Double> getWeightedWorldGenForGrass()
    {
        return null;
    }
    
    @Override
    public HashMap<WorldGenBOPFlora, Integer> getWeightedWorldGenForBOPFlowers()
    {
        return null;
    }
}
