package biomesoplenty.common.world.features.managers;

import biomesoplenty.common.world.decoration.BOPDecorationManager;
import biomesoplenty.common.world.decoration.BOPWorldFeatures;
import biomesoplenty.common.world.generation.WorldGeneratorBOP;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenBOPGrassManager extends WorldGeneratorBOP
{
    private BOPWorldFeatures biomeFeatures;

    @Override
    public boolean generate(World world, Random random, int x, int y, int z)
    {
        if (biomeFeatures != null)
        {
            if (biomeFeatures.weightedGrassGen != null && !biomeFeatures.weightedGrassGen.isEmpty())
            {
                WorldGenerator grassGenerator = BOPDecorationManager.getRandomWeightedWorldGenerator(biomeFeatures.weightedGrassGen);

                return grassGenerator.generate(world, random, x, y, z);
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
            int randY = random.nextInt(world.getHeightValue(randX, randZ) * 2);

            this.generate(world, random, randX, randY, randZ);
        }
    }
}
