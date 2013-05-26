package biomesoplenty.worldgen;

import java.util.Random;

import biomesoplenty.api.Blocks;
import biomesoplenty.configuration.BOPBlocks;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenNetherGrass extends WorldGenerator
{
    /** Stores ID for WorldGenTallGrass */
    private int tallGrassID;
    private int tallGrassMetadata;

    public WorldGenNetherGrass(int par1, int par2)
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
			int var99 = par2Random.nextInt(6);

            if (par1World.isAirBlock(var8, var9, var10) && par1World.getBlockId(var8, var9 - 1, var10) == Block.netherrack.blockID)
            {
                par1World.setBlock(var8, var9 - 1, var10, Block.grass.blockID, 0, 2);
				
				if (var99 == 0)
				{
					par1World.setBlock(var8, var9, var10, Block.mushroomRed.blockID, 0, 2);
				}
				
				if (var99 == 1)
				{
					par1World.setBlock(var8, var9, var10, Block.mushroomBrown.blockID, 0, 2);
				}
				
				if (var99 == 2)
				{
					par1World.setBlock(var8, var9, var10, Block.tallGrass.blockID, 0, 2);
				}
				
				if (var99 == 3)
				{
					par1World.setBlock(var8, var9, var10, Blocks.logs4.get().blockID, 1, 2);
					if (par1World.isAirBlock(var8, var9 + 1, var10))
					{
						par1World.setBlock(var8, var9 + 1, var10, Blocks.logs4.get().blockID, 1, 2);
					}
					if (par1World.isAirBlock(var8 + 1, var9 + 1, var10))
					{
						par1World.setBlock(var8 + 1, var9 + 1, var10, Blocks.leaves2.get().blockID, 4, 2);
					}
					if (par1World.isAirBlock(var8 - 1, var9 + 1, var10))
					{
						par1World.setBlock(var8 - 1, var9 + 1, var10, Blocks.leaves2.get().blockID, 4, 2);
					}
					if (par1World.isAirBlock(var8, var9 + 1, var10 + 1))
					{
						par1World.setBlock(var8, var9 + 1, var10 + 1, Blocks.leaves2.get().blockID, 4, 2);
					}
					if (par1World.isAirBlock(var8, var9 + 1, var10 - 1))
					{
						par1World.setBlock(var8, var9 + 1, var10 - 1, Blocks.leaves2.get().blockID, 4, 2);
					}
					if (par1World.isAirBlock(var8, var9 + 2, var10))
					{
						par1World.setBlock(var8, var9 + 2, var10, Blocks.leaves2.get().blockID, 4, 2);
					}
				}
				
				if (var99 == 4)
				{
					par1World.setBlock(var8, var9, var10, Blocks.logs4.get().blockID, 1, 2);
					if (par1World.isAirBlock(var8, var9 + 1, var10))
					{
						par1World.setBlock(var8, var9 + 1, var10, Blocks.logs4.get().blockID, 1, 2);
					}
					if (par1World.isAirBlock(var8 + 1, var9 + 1, var10))
					{
						par1World.setBlock(var8 + 1, var9 + 1, var10, Blocks.leaves2.get().blockID, 4, 2);
					}
					if (par1World.isAirBlock(var8 - 1, var9 + 1, var10))
					{
						par1World.setBlock(var8 - 1, var9 + 1, var10, Blocks.leaves2.get().blockID, 4, 2);
					}
					if (par1World.isAirBlock(var8, var9 + 1, var10 + 1))
					{
						par1World.setBlock(var8, var9 + 1, var10 + 1, Blocks.leaves2.get().blockID, 4, 2);
					}
					if (par1World.isAirBlock(var8, var9 + 1, var10 - 1))
					{
						par1World.setBlock(var8, var9 + 1, var10 - 1, Blocks.leaves2.get().blockID, 4, 2);
					}
					if (par1World.isAirBlock(var8, var9 + 2, var10))
					{
						par1World.setBlock(var8, var9 + 2, var10, Blocks.leaves2.get().blockID, 4, 2);
					}
				}
            }
        }

        return true;
    }
}
