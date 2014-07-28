package biomesoplenty.common.world.forcedgenerators;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.biome.BOPBiome;
import biomesoplenty.common.world.generation.ForcedWorldFeatureBOP;

public class SpringForcedGenerator extends ForcedWorldFeatureBOP
{
    public SpringForcedGenerator(WorldGenerator worldGenerator)
    {
        super(worldGenerator);
    }

    @Override
    public void setupGeneration(World world, Random random, BOPBiome biome, String featureName, int x, int z)
    {
        if (biome.theBiomeDecorator.generateLakes)
        {
            for (int i = 0; i < (Integer)biome.theBiomeDecorator.bopFeatures.getFeature("waterSpringsPerChunk"); ++i)
            {
                int randX = x + random.nextInt(16) + 8;
                int randY = random.nextInt(random.nextInt(120) + 8);
                int randZ = z + random.nextInt(16) + 8;

                this.generate(world, random, randX, randY, randZ);
            }

            for (int i = 0; i < (Integer)biome.theBiomeDecorator.bopFeatures.getFeature("lavaSpringsPerChunk"); ++i)
            {
                int randX = x + random.nextInt(16) + 8;
                int randY = random.nextInt(random.nextInt(random.nextInt(112) + 8) + 8);
                int randZ = z + random.nextInt(16) + 8;

                this.generate(world, random, randX, randY, randZ);
            }
            
            for (int i = 0; i < (Integer)biome.theBiomeDecorator.bopFeatures.getFeature("bloodSpringsPerChunk"); ++i)
            {
                int randX = x + random.nextInt(16) + 8;
                int randY = random.nextInt(random.nextInt(random.nextInt(112) + 8) + 8);
                int randZ = z + random.nextInt(16) + 8;

                this.generate(world, random, randX, randY, randZ);
            }
        }
    }
}
