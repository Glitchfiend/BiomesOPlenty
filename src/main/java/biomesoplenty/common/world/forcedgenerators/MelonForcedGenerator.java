package biomesoplenty.common.world.forcedgenerators;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.biome.BOPBiome;
import biomesoplenty.common.world.generation.ForcedWorldFeatureBOP;

public class MelonForcedGenerator extends ForcedWorldFeatureBOP
{
    public MelonForcedGenerator(WorldGenerator worldGenerator)
    {
        super(worldGenerator);
    }

    @Override
    public void setupGeneration(World world, Random random, BOPBiome biome, String featureName, int x, int z)
    {
        if ((Boolean)biome.theBiomeDecorator.bopFeatures.getFeature("generateMelons"))
        {
            int randX = x + random.nextInt(16) + 8;
            int randZ = z + random.nextInt(16) + 8;
            int randY = random.nextInt(world.getHeightValue(randX, randZ) * 2);

            this.generate(world, random, randX, randY, randZ);
        }
    }
}
