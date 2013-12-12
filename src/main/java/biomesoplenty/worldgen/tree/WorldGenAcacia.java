package biomesoplenty.worldgen.tree;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.Blocks;

public class WorldGenAcacia extends WorldGenerator
{
	/** The minimum height of a generated tree. */
	private final int minTreeHeight;

	/** True if this tree should grow Vines. */
	private final boolean vinesGrow;

	/** The metadata value of the wood to use in tree generation. */
	private final int metaWood;

	/** The metadata value of the leaves to use in tree generation. */
	private final int metaLeaves;

	public WorldGenAcacia(boolean par1)
	{
		this(par1, 4, 0, 0, false);
	}

	public WorldGenAcacia(boolean par1, int par2, int par3, int par4, boolean par5)
	{
		super(par1);
		minTreeHeight = par2;
		metaWood = par3;
		metaLeaves = par4;
		vinesGrow = par5;
	}

	@Override
	public boolean generate(World par1World, Random par2Random, int x, int y, int z)
	{
		int var5 = par1World.getBlockId(x, y - 1, z);
		if (var5 != Block.grass.blockID)
			return false;
		int rand = 4 + par2Random.nextInt(3);
		for(int i = 0; i < rand; i++) { par1World.setBlock(x, y + i, z, Blocks.logs1.get().blockID, 0, 2); }

		if(par2Random.nextInt(4) == 0) { //branch1
			par1World.setBlock(x + 0, y + rand + 1, z + 1, Blocks.logs1.get().blockID, 0, 2);
			par1World.setBlock(x + 1, y + rand + 2, z + 2, Blocks.logs1.get().blockID, 0, 2);
			createAcaciaLeaves(par1World, par2Random, x + 1, y + rand + 2, z + 2, 3);
			createAcaciaLeaves(par1World, par2Random, x + 1, y + rand + 3, z + 2, 2);
		}

		if(par2Random.nextInt(4) == 0) { //branch2
			par1World.setBlock(x + 1, y + rand + 0, z + 0, Blocks.logs1.get().blockID, 0, 2);
			par1World.setBlock(x + 2, y + rand + 1, z + 0, Blocks.logs1.get().blockID, 0, 2);
			par1World.setBlock(x + 3, y + rand + 2, z - 1, Blocks.logs1.get().blockID, 0, 2);
			createAcaciaLeaves(par1World, par2Random, x + 3, y + rand + 3, z - 1, 3);
			createAcaciaLeaves(par1World, par2Random, x + 3, y + rand + 4, z - 1, 2);
		}

		if(par2Random.nextInt(4) == 0) { //branch3
			par1World.setBlock(x - 1, y + rand + 0, z + 0, Blocks.logs1.get().blockID, 0, 2);
			par1World.setBlock(x - 2, y + rand + 1, z + 0, Blocks.logs1.get().blockID, 0, 2);
			par1World.setBlock(x - 3, y + rand + 2, z - 1, Blocks.logs1.get().blockID, 0, 2);
			par1World.setBlock(x - 4, y + rand + 3, z - 2, Blocks.logs1.get().blockID, 0, 2);
			createAcaciaLeaves(par1World, par2Random, x - 4, y + rand + 4, z - 2, 3);
			createAcaciaLeaves(par1World, par2Random, x - 4, y + rand + 5, z - 2, 2);
		}

		if(par2Random.nextInt(4) == 0) { //branch4
			par1World.setBlock(x + 0, y + rand + 0, z - 1, Blocks.logs1.get().blockID, 0, 2);
			par1World.setBlock(x + 1, y + rand + 1, z - 2, Blocks.logs1.get().blockID, 0, 2);
			par1World.setBlock(x + 2, y + rand + 2, z - 2, Blocks.logs1.get().blockID, 0, 2);
			par1World.setBlock(x + 3, y + rand + 3, z - 2, Blocks.logs1.get().blockID, 0, 2);
			createAcaciaLeaves(par1World, par2Random, x + 3, y + rand + 3, z - 2, 3);
			createAcaciaLeaves(par1World, par2Random, x + 3, y + rand + 4, z - 2, 2);
		}

		if(par2Random.nextInt(4) == 0) { //branch5
			par1World.setBlock(x + 0, y + rand + 0, z - 1, Blocks.logs1.get().blockID, 0, 2);
			par1World.setBlock(x + 0, y + rand + 0, z - 2, Blocks.logs1.get().blockID, 0, 2);
			par1World.setBlock(x + 1, y + rand + 1, z - 3, Blocks.logs1.get().blockID, 0, 2);
			createAcaciaLeaves(par1World, par2Random, x + 1, y + rand + 1, z - 3, 3);
			createAcaciaLeaves(par1World, par2Random, x + 1, y + rand + 2, z - 3, 2);
		}

		//branch6
		par1World.setBlock(x - 0, y + rand + 0, z + 0, Blocks.logs1.get().blockID, 0, 2);
		par1World.setBlock(x - 0, y + rand + 1, z + 0, Blocks.logs1.get().blockID, 0, 2);
		par1World.setBlock(x - 0, y + rand + 2, z - 0, Blocks.logs1.get().blockID, 0, 2);
		createAcaciaLeaves(par1World, par2Random, x + 0, y + rand + 3, z - 0, 3);
		createAcaciaLeaves(par1World, par2Random, x + 0, y + rand + 4, z - 0, 2);

		return true;

		/*int var6 = par2Random.nextInt(6) + this.minTreeHeight;
        boolean var7 = true;

        if (par4 >= 1 && par4 + var6 + 1 <= 256)
        {
            int var8;
            byte var9;
            int var11;
            int var12;

            for (var8 = par4; var8 <= par4 + 1 + var6; ++var8)
            {
                var9 = 1;

                if (var8 == par4)
                {
                    var9 = 0;
                }

                if (var8 >= par4 + 1 + var6 - 2)
                {
                    var9 = 2;
                }

                for (int var10 = par3 - var9; var10 <= par3 + var9 && var7; ++var10)
                {
                    for (var11 = par5 - var9; var11 <= par5 + var9 && var7; ++var11)
                    {
                        if (var8 >= 0 && var8 < 256)
                        {
                            var12 = par1World.getBlockId(var10, var8, var11);

                            if (var12 != 0 && var12 != Blocks.leavesColorized.get().blockID && var12 != Block.grass.blockID && var12 != Blocks.redRock.get().blockID && var12 != Block.dirt.blockID && var12 != Blocks.logs1.get().blockID)
                            {
                                var7 = false;
                            }
                        }
                        else
                        {
                            var7 = false;
                        }
                    }
                }
            }

            if (!var7)
            {
                return false;
            }
            else
            {
                var8 = par1World.getBlockId(par3, par4 - 1, par5);

                if ((var8 == Block.grass.blockID || var8 == Block.dirt.blockID || var8 == Blocks.redRock.get().blockID) && par4 < 256 - var6 - 1)
                {
                    this.setBlock(par1World, par3, par4 - 1, par5, Block.dirt.blockID);
                    var9 = 2;
                    byte var18 = 0;
                    int var13;
                    int var14;
                    int var15;

                    for (var11 = par4 - var9 + var6; var11 <= par4 + var6; ++var11)
                    {
                        var12 = var11 - (par4 + var6);
                        var13 = var18 + 1 - var12;

                        for (var14 = par3 - var13; var14 <= par3 + var13; ++var14)
                        {
                            var15 = var14 - par3;

                            for (int var16 = par5 - var13; var16 <= par5 + var13; ++var16)
                            {
                                int var17 = var16 - par5;

                                if ((Math.abs(var15) != var13 || Math.abs(var17) != var13 || par2Random.nextInt(2) != 0 && var12 != 0) && !Block.opaqueCubeLookup[par1World.getBlockId(var14, var11, var16)])
                                {
									this.setBlockAndMetadata(par1World, var14, var11, var16, Blocks.leavesColorized.get().blockID, 0);
                                }
                            }
                        }
                    }

                    for (var11 = 0; var11 < var6; ++var11)
                    {
                        var12 = par1World.getBlockId(par3, par4 + var11, par5);

                        if (var12 == 0 || var12 == Blocks.leavesColorized.get().blockID)
                        {
                            this.setBlockAndMetadata(par1World, par3, par4 + var11, par5, Blocks.logs1.get().blockID,0);

                            if (this.vinesGrow && var11 > 0)
                            {
                                if (par2Random.nextInt(3) > 0 && par1World.isAirBlock(par3 - 1, par4 + var11, par5))
                                {
                                    this.setBlockAndMetadata(par1World, par3 - 1, par4 + var11, par5, Block.vine.blockID, 8);
                                }

                                if (par2Random.nextInt(3) > 0 && par1World.isAirBlock(par3 + 1, par4 + var11, par5))
                                {
                                    this.setBlockAndMetadata(par1World, par3 + 1, par4 + var11, par5, Block.vine.blockID, 2);
                                }

                                if (par2Random.nextInt(3) > 0 && par1World.isAirBlock(par3, par4 + var11, par5 - 1))
                                {
                                    this.setBlockAndMetadata(par1World, par3, par4 + var11, par5 - 1, Block.vine.blockID, 1);
                                }

                                if (par2Random.nextInt(3) > 0 && par1World.isAirBlock(par3, par4 + var11, par5 + 1))
                                {
                                    this.setBlockAndMetadata(par1World, par3, par4 + var11, par5 + 1, Block.vine.blockID, 4);
                                }
                            }
                        }
                    }

                    if (this.vinesGrow)
                    {
                        for (var11 = par4 - 3 + var6; var11 <= par4 + var6; ++var11)
                        {
                            var12 = var11 - (par4 + var6);
                            var13 = 2 - var12 / 2;

                            for (var14 = par3 - var13; var14 <= par3 + var13; ++var14)
                            {
                                for (var15 = par5 - var13; var15 <= par5 + var13; ++var15)
                                {
                                    if (par1World.getBlockId(var14, var11, var15) == Blocks.leavesColorized.get().blockID)
                                    {
                                        if (par2Random.nextInt(4) == 0 && par1World.getBlockId(var14 - 1, var11, var15) == 0)
                                        {
                                            this.growVines(par1World, var14 - 1, var11, var15, 8);
                                        }

                                        if (par2Random.nextInt(4) == 0 && par1World.getBlockId(var14 + 1, var11, var15) == 0)
                                        {
                                            this.growVines(par1World, var14 + 1, var11, var15, 2);
                                        }

                                        if (par2Random.nextInt(4) == 0 && par1World.getBlockId(var14, var11, var15 - 1) == 0)
                                        {
                                            this.growVines(par1World, var14, var11, var15 - 1, 1);
                                        }

                                        if (par2Random.nextInt(4) == 0 && par1World.getBlockId(var14, var11, var15 + 1) == 0)
                                        {
                                            this.growVines(par1World, var14, var11, var15 + 1, 4);
                                        }
                                    }
                                }
                            }
                        }
                    }

                    return true;
                }
                else
                {
                    return false;
                }
            }
        }
        else
        {
            return false;
        }*/
	}

	/**
	 * Grows vines downward from the given block for a given length. Args: World, x, starty, z, vine-length
	 */
	/*private void growVines(World par1World, int par2, int par3, int par4, int par5)
    {
        this.setBlockAndMetadata(par1World, par2, par3, par4, Block.vine.blockID, par5);
        int var6 = 4;

        while (true)
        {
            --par3;

            if (par1World.getBlockId(par2, par3, par4) != 0 || var6 <= 0)
            {
                return;
            }

            this.setBlockAndMetadata(par1World, par2, par3, par4, Block.vine.blockID, par5);
            --var6;
        }
    }*/

	private void createAcaciaLeaves(World par1World, Random par2Random, int x, int y, int z, int size)
	{
		for(int x1 = -size + x; x1 < size + 1 + x; x1++)
		{
			for(int z1 = -size + z; z1 < size + 1 + z; z1++)
			{
				int var5 = par1World.getBlockId(x1, y, z1);
				if (var5 == 0)
				{
					if(x1 == -size + x && z1 == -size + z ){} else if(x1 == -size + x && z1 == size + z ){} else if(x1 == size + x && z1 == -size + z ){} else if(x1 == size + x && z1 == size + z ){}
					else { par1World.setBlock(x1, y, z1, Blocks.leavesColorized1.get().blockID, 0, 2); }
				}
			}
		}
		if(size==3){par1World.setBlock(x, y, z, Blocks.logs1.get().blockID, 0, 2);}
	}
}
