package tdwp_ftw.biomesop.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenPotatoes extends WorldGenerator
{
    /** Stores ID for WorldGenTallGrass */
    private int tallGrassID;
    private int tallGrassMetadata;

    public WorldGenPotatoes(int par1, int par2)
    {
        this.tallGrassID = par1;
        this.tallGrassMetadata = par2;
    }

    public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
    {
        int var11;

        for (boolean var6 = false; ((var11 = par1World.getBlockId(par3, par4, par5)) == 0 || var11 == Block.leaves.blockID) && par4 > 0; --par4)
        {
            ;
        }

        for (int var7 = 0; var7 < 128; ++var7)
        {
            int var8 = par3 + par2Random.nextInt(8) - par2Random.nextInt(8);
            int var9 = par4 + par2Random.nextInt(4) - par2Random.nextInt(4);
            int var10 = par5 + par2Random.nextInt(8) - par2Random.nextInt(8);
			int var99 = par2Random.nextInt(7);
			
			if (par1World.getBlockMaterial(var8 - 1, var9 - 1, var10) != Material.water)
			{
				if (par1World.getBlockMaterial(var8 + 1, var9 - 1, var10) != Material.water)
				{
					if (par1World.getBlockMaterial(var8, var9 - 1, var10 - 1) != Material.water)
					{
						if (par1World.getBlockMaterial(var8, var9 - 1, var10 + 1) != Material.water)
						{
							return false;
						}
					}
				}
			}

            if (par1World.isAirBlock(var8, var9, var10) && Block.blocksList[this.tallGrassID].canBlockStay(par1World, var8, var9, var10))
            {
				par1World.setBlock(var8, var9 - 1, var10, Block.tilledField.blockID);
				par1World.setBlockAndMetadata(var8, var9, var10, Block.potato.blockID, var99);
            }
        }

        return true;
    }
}
