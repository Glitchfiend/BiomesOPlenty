package biomesoplenty.worldgen;

import java.util.Random;

import biomesoplenty.api.Blocks;
import biomesoplenty.configuration.BOPBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenHighCattail extends WorldGenerator
{
    public boolean generate(World world, Random par2Random, int par3, int par4, int par5)
    {
        int var11;

        for (boolean var6 = false; ((var11 = world.getBlockId(par3, par4, par5)) == 0 || var11 == Block.leaves.blockID) && par4 > 0; --par4)
        {
            ;
        }

        for (int var7 = 0; var7 < 128; ++var7)
        {
            int x = par3 + par2Random.nextInt(8) - par2Random.nextInt(8);
            int y = par4 + par2Random.nextInt(4) - par2Random.nextInt(4);
            int z = par5 + par2Random.nextInt(8) - par2Random.nextInt(8);

            if (world.isAirBlock(x, y + 1, z) && Blocks.plants.get().canBlockStay(world, x, y, z))
            {
                if (world.getBlockMaterial(x - 1, y - 1, z) == Material.water ? true : (world.getBlockMaterial(x + 1, y - 1, z) == Material.water ? true : (world.getBlockMaterial(x, y - 1, z - 1) == Material.water ? true : world.getBlockMaterial(x, y - 1, z + 1) == Material.water)))
                {
					world.setBlock(x, y, z, Blocks.plants.get().blockID, 9, 0);
					world.setBlock(x, y + 1, z, Blocks.plants.get().blockID, 8, 0);
				}
            }
        }

        return true;
    }
}
