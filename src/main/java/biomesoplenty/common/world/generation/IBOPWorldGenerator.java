package biomesoplenty.common.world.generation;

import java.util.Random;

import biomesoplenty.api.biome.BOPBiome;
import biomesoplenty.api.biome.BOPBiomeDecorator;
import net.minecraft.world.World;

public interface IBOPWorldGenerator
{
    public boolean generate(World world, Random random, int x, int y, int z);

    public void setupGeneration(World world, Random random, BOPBiome biome, String featureName, int x, int z);
}
