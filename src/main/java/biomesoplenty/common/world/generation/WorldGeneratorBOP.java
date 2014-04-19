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
    
	public abstract boolean generate(World world, Random rand, int x, int y, int z);
}
