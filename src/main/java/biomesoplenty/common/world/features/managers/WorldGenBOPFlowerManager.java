package biomesoplenty.common.world.features.managers;

import java.util.Random;

import net.minecraft.world.World;
import biomesoplenty.api.biome.BOPBiome;
import biomesoplenty.api.biome.BOPBiomeDecorator;
import biomesoplenty.api.biome.BiomeFeatures;
import biomesoplenty.common.world.features.WorldGenBOPFlora;
import biomesoplenty.common.world.generation.WorldGeneratorBOP;

public class WorldGenBOPFlowerManager extends WorldGeneratorBOP
{
    private BiomeFeatures biomeFeatures;

    @Override
    public boolean generate(World world, Random random, int x, int y, int z)
    {
        if (biomeFeatures != null)
        {
            if (biomeFeatures.weightedFlowerGen != null && !biomeFeatures.weightedFlowerGen.isEmpty())
            {
                WorldGenBOPFlora flowerGenerator = (WorldGenBOPFlora)BOPBiomeDecorator.getRandomWeightedWorldGenerator(biomeFeatures.weightedFlowerGen);

                return flowerGenerator.generate(world, random, x, y, z);
            }
        }

        return false;
    }

    @Override
    public void setupGeneration(World world, Random random, BOPBiome biome, String featureName, int x, int z)
    {
        this.biomeFeatures = biome.theBiomeDecorator.bopFeatures;

        for (int i = 0; i < (Integer)biomeFeatures.getFeature(featureName); ++i)
        {
            int randX = x + random.nextInt(16) + 8;
            int randZ = z + random.nextInt(16) + 8;
            int randY = world.provider.isHellWorld ? random.nextInt(128) : random.nextInt(world.getHeightValue(randX, randZ) + 32);

            this.generate(world, random, randX, randY, randZ);
        }
    }
}
