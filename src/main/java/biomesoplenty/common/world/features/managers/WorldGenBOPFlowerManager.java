package biomesoplenty.common.world.features.managers;

import biomesoplenty.common.world.decoration.BOPDecorationManager;
import biomesoplenty.common.world.decoration.BOPWorldFeatures;
import biomesoplenty.common.world.features.WorldGenBOPFlora;
import biomesoplenty.common.world.generation.WorldGeneratorBOP;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

import java.util.Random;

public class WorldGenBOPFlowerManager extends WorldGeneratorBOP
{
    private BOPWorldFeatures biomeFeatures;

    @Override
    public boolean generate(World world, Random random, int x, int y, int z)
    {
        if (biomeFeatures != null)
        {
            if (biomeFeatures.weightedFlowerGen != null && !biomeFeatures.weightedFlowerGen.isEmpty())
            {
                WorldGenBOPFlora flowerGenerator = BOPDecorationManager.getRandomWeightedWorldGenerator(biomeFeatures.weightedFlowerGen);

                return flowerGenerator.generate(world, random, x, y, z);
            }
        }

        return false;
    }

    @Override
    public void setupGeneration(World world, Random random, BiomeGenBase biome, String featureName, int x, int z)
    {
        this.biomeFeatures = BOPDecorationManager.getBiomeFeatures(biome.biomeID);

        for (int i = 0; i < (Integer)biomeFeatures.getFeature(featureName); ++i)
        {
            int randX = x + random.nextInt(16) + 8;
            int randZ = z + random.nextInt(16) + 8;
            int randY = random.nextInt(world.getHeightValue(randX, randZ) + 32);

            this.generate(world, random, randX, randY, randZ);
        }
    }
}
