package biomesoplenty.common.world.generation;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public interface IBOPWorldGenerator
{
    public boolean generate(World world, Random random, int x, int y, int z);

    public void setupGeneration(World world, Random random, BiomeGenBase biome, String featureName, int x, int z);
}
