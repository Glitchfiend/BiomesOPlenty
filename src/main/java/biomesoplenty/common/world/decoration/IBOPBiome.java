package biomesoplenty.common.world.decoration;

import biomesoplenty.common.world.features.WorldGenBOPFlora;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.HashMap;
import java.util.Random;

public interface IBOPBiome
{
    public HashMap<WorldGenerator, Double> weightedGrassGen = new HashMap<WorldGenerator, Double>();
    public HashMap<WorldGenBOPFlora, Integer> weightedFlowerGen = new HashMap<WorldGenBOPFlora, Integer>();

	public WorldGenBOPFlora getRandomWorldGenForBOPFlowers(Random random);

	public BOPWorldFeatures getBiomeFeatures();
}
