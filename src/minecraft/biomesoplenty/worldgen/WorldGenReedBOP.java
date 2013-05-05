package biomesoplenty.worldgen;

import biomesoplenty.api.Blocks;
import biomesoplenty.configuration.BOPBlocks;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenReedBOP extends WorldGenerator
{
    public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
    {
        for (int l = 0; l < 50; ++l)
        {
            int i1 = par3 + par2Random.nextInt(8) - par2Random.nextInt(8);
            int j1 = par4;
            int k1 = par5 + par2Random.nextInt(8) - par2Random.nextInt(8);

            if (par1World.isAirBlock(i1, par4, k1))
            {
                int l1 = 2 + par2Random.nextInt(par2Random.nextInt(2) + 2);

                for (int i2 = 0; i2 < l1; ++i2)
                {
                    if (Blocks.plants.get().canBlockStay(par1World, i1, j1 + i2, k1))
                    {
                        par1World.setBlock(i1, j1 + i2, k1, Blocks.plants.get().blockID, 8, 2);
                    }
                }
            }
        }

        return true;
    }
}
