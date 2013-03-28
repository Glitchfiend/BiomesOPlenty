package tdwp_ftw.biomesop.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenBush extends WorldGenerator
{
    /** The ID of the plant block used in this plant generator. */
    private int plantBlockId;

    public WorldGenBush(int par1)
    {
        this.plantBlockId = par1;
    }

    public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
    {
        for (int var6 = 0; var6 < 64; ++var6)
        {
            int var7 = par3 + par2Random.nextInt(8) - par2Random.nextInt(8);
            int var8 = par4 + par2Random.nextInt(4) - par2Random.nextInt(4);
            int var9 = par5 + par2Random.nextInt(8) - par2Random.nextInt(8);

            if (par1World.isAirBlock(var7, var8, var9) && (!par1World.provider.hasNoSky || var8 < 127) && Block.blocksList[this.plantBlockId].canBlockStay(par1World, var7, var8, var9))
            {
                par1World.setBlockAndMetadata(var7, var8, var9, this.plantBlockId, 1);
            }
        }

        return true;
    }
}
