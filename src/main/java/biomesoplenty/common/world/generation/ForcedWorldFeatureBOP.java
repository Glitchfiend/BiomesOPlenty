package biomesoplenty.common.world.generation;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public abstract class ForcedWorldFeatureBOP implements IBOPWorldGenerator
{
    private WorldGenerator worldGenerator;

    public ForcedWorldFeatureBOP(WorldGenerator worldGenerator)
    {
        this.worldGenerator = worldGenerator;
    }

    @Override
    public boolean generate(World world, Random random, int x, int y, int z)
    {
        return worldGenerator.generate(world, random, x, y, z);
    }
}
