package biomesoplenty.common.world.generation;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public abstract class WorldGeneratorBOP extends WorldGenerator implements IBOPWorldGenerator
{
    public WorldGeneratorBOP(boolean doBlockNotify)
    {
        super(doBlockNotify);
    }

    public WorldGeneratorBOP()
    {
        this(false);
    }

    @Override
    public boolean generate(World world, Random random, int x, int y, int z)
    {
        return false;
    }
}
