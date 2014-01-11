package biomesoplenty.common.world.forcedworldgenerators;

import java.lang.reflect.Field;
import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.common.world.decoration.IBOPDecoration;
import biomesoplenty.common.world.generation.ForcedWorldGeneratorBOP;

public class MelonForcedGenerator extends ForcedWorldGeneratorBOP
{
    @Override
    public void doGeneration(World world, Random random, Field worldGeneratorField, WorldGenerator worldGenerator, BiomeGenBase biome, IBOPDecoration bopDecoration, int x, int z) throws Exception
    {
        if (bopDecoration.getWorldFeatures().generateMelons)
        {
            int randX = x + random.nextInt(16) + 8;
            int randZ = z + random.nextInt(16) + 8;
            int randY = random.nextInt(world.getHeightValue(randX, randZ) * 2);

            worldGenerator.generate(world, random, randX, randY, randZ);
        }
    }
}
