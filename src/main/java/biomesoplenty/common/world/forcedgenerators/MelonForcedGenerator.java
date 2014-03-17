package biomesoplenty.common.world.forcedgenerators;

import biomesoplenty.common.world.decoration.BOPDecorationManager;
import biomesoplenty.common.world.generation.ForcedWorldFeatureBOP;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class MelonForcedGenerator extends ForcedWorldFeatureBOP
{
    public MelonForcedGenerator(WorldGenerator worldGenerator)
    {
        super(worldGenerator);
    }

    @Override
    public void setupGeneration(World world, Random random, BiomeGenBase biome, String featureName, int x, int z)
    {
        if ((Boolean)BOPDecorationManager.getBiomeFeatures(biome.biomeID).getFeature("generateMelons"))
        {
            int randX = x + random.nextInt(16) + 8;
            int randZ = z + random.nextInt(16) + 8;
            int randY = random.nextInt(world.getHeightValue(randX, randZ) * 2);

            this.generate(world, random, randX, randY, randZ);
        }
    }
}
