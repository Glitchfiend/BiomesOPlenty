package biomesoplenty.worldgen;

import java.util.Random;

import biomesoplenty.api.Blocks;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenDesertCactus extends WorldGenerator
{
    @Override
    public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
    {
        int var999 = par1World.getBlockId(par3, par4, par5);
        int var998 = par1World.getBlockMetadata(par3, par4, par5);

        if (var999 == Blocks.plants.get().blockID && var998 == 12)
        {
            int l1 = 1 + par2Random.nextInt(par2Random.nextInt(3) + 1);

            for (int i2 = 0; i2 < l1; ++i2)
            {
                if (Block.cactus.canBlockStay(par1World, par3, par4 + i2, par5))
                {
                    par1World.setBlock(par3, par4 + i2, par5, Block.cactus.blockID, 0, 2);
                }
            }
        }

        return true;
    }
}
