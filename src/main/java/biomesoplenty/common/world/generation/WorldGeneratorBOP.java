package biomesoplenty.common.world.generation;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

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
    
	public abstract boolean generate(World world, Random rand, int x, int y, int z);
}
