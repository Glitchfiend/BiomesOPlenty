package biomesoplenty.common.world.decoration;

import java.util.HashMap;
import java.util.Random;

import net.minecraft.world.gen.feature.WorldGenerator;

import biomesoplenty.common.world.features.WorldGenBOPFlora;

public interface IBOPDecoration
{
	public WorldGenBOPFlora getRandomWorldGenForBOPFlowers(Random random);
	
	public BOPWorldFeatures getWorldFeatures();
	
    public HashMap<WorldGenerator, Double> getWeightedWorldGenForGrass();
    
    public HashMap<WorldGenBOPFlora, Integer> getWeightedWorldGenForBOPFlowers();
}
