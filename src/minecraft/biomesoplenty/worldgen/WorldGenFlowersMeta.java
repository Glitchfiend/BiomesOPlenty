package biomesoplenty.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenFlowersMeta extends WorldGenerator
{
    public final int plantID;
    public final int metadata;
    public int chances = 64;

    public WorldGenFlowersMeta(int id, int meta)
    {
        this.plantID = id;
        this.metadata = meta;
    }    

    public WorldGenFlowersMeta(int id, int meta, int count)
    {
        this.plantID = id;
        this.metadata = meta;
        this.chances = count;
    }

    public boolean generate(World world, Random random, int x, int y, int z)
    {
        for (int iter = 0; iter < chances; ++iter)
        {
            int posX = x + random.nextInt(8) - random.nextInt(8);
            int posY = y + random.nextInt(4) - random.nextInt(4);
            int posZ = z + random.nextInt(8) - random.nextInt(8);

            if (world.isAirBlock(posX, posY, posZ) && (!world.provider.hasNoSky || posY < 127) && Block.blocksList[this.plantID].canBlockStay(world, posX, posY, posZ))
            {
                world.setBlock(posX, posY, posZ, this.plantID, this.metadata, 2);
            }
        }

        return true;
    }
}
