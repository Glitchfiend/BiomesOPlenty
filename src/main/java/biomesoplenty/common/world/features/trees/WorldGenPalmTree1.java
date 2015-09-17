package biomesoplenty.common.world.features.trees;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import biomesoplenty.api.content.BOPCBlocks;

public class WorldGenPalmTree1 extends WorldGenAbstractTree
{
	private int strengthRand;
	private int bMax;
	private double offset;
	
	public WorldGenPalmTree1()
	{
		this(35, 10, 1.3D);
	}
	
    public WorldGenPalmTree1(int strengthRand, int bMax, double offset)
    {
        super(false);
        
        this.strengthRand = strengthRand;
        this.bMax = bMax;
        this.offset = offset;
    }

    @Override
    public boolean generate(World world, Random random, int x, int y, int z)
    {
        while (world.isAirBlock(x, y, z) && y > 2)
        {
            --y;
        }

        Block block = world.getBlock(x, y, z);

        if (block != Blocks.grass && block != BOPCBlocks.newBopGrass)
        {
            return false;
        }
        else
        {
            for (int var7 = -2; var7 <= 2; ++var7)
            {
                for (int var8 = -2; var8 <= 2; ++var8)
                {
                    if (world.isAirBlock(x + var7, y - 1, z + var8) && world.isAirBlock(x + var7, y - 2, z + var8) && !world.isAirBlock(x + var7, y, z + var8))
                    {
                        return false;
                    }
                }
            }

            //settings========
                    double strength = random.nextInt(strengthRand) / 100D;
            //================

                    double xoff = 0;
            double yoff = 0;
            int r = random.nextInt(4);
            if(r == 0) { xoff = strength; }
            else if(r == 1) { xoff = -strength; }
            else if(r == 2) { yoff = strength; }
            else { yoff = -strength; }

            int h = 1;
            buildBlock(world, x, y, z, Blocks.dirt, 0);
            for(int b = 0; b < bMax; b++)
            {
                buildBlock(world, x + ((int) Math.floor(xoff)), y + h, z + ((int) Math.floor(yoff)), BOPCBlocks.logs2, 3);
                if(b == bMax - 1)
                {
                    generateTop(world, x + ((int) Math.floor(xoff)), y + h, z + ((int) Math.floor(yoff)));
                }
                else
                {
                    h++;
                    xoff *= offset;
                    yoff *= offset;
                }
            }

            return true;
        }
    }

    public void generateTop(World world, int x, int y, int z)
    {
        buildBlock(world, x + 2, y - 1, z, BOPCBlocks.colorizedLeaves1, 2);
        buildBlock(world, x - 2, y - 1, z, BOPCBlocks.colorizedLeaves1, 2);
        buildBlock(world, x, y - 1, z + 2, BOPCBlocks.colorizedLeaves1, 2);
        buildBlock(world, x, y - 1, z - 2, BOPCBlocks.colorizedLeaves1, 2);

        buildBlock(world, x + 1, y, z, BOPCBlocks.colorizedLeaves1, 2);
        buildBlock(world, x - 1, y, z, BOPCBlocks.colorizedLeaves1, 2);
        buildBlock(world, x, y, z + 1, BOPCBlocks.colorizedLeaves1, 2);
        buildBlock(world, x, y, z - 1, BOPCBlocks.colorizedLeaves1, 2);
        buildBlock(world, x + 2, y, z + 2, BOPCBlocks.colorizedLeaves1, 2);
        buildBlock(world, x - 2, y, z - 2, BOPCBlocks.colorizedLeaves1, 2);
        buildBlock(world, x + 2, y, z - 2, BOPCBlocks.colorizedLeaves1, 2);
        buildBlock(world, x - 2, y, z + 2, BOPCBlocks.colorizedLeaves1, 2);

        buildBlock(world, x + 1, y + 1, z - 1, BOPCBlocks.colorizedLeaves1, 2);
        buildBlock(world, x - 1, y + 1, z + 1, BOPCBlocks.colorizedLeaves1, 2);
        buildBlock(world, x + 1, y + 1, z + 1, BOPCBlocks.colorizedLeaves1, 2);
        buildBlock(world, x - 1, y + 1, z - 1, BOPCBlocks.colorizedLeaves1, 2);
        buildBlock(world, x, y + 1, z, BOPCBlocks.colorizedLeaves1, 2);

        buildBlock(world, x + 2, y + 2, z, BOPCBlocks.colorizedLeaves1, 2);
        buildBlock(world, x - 2, y + 2, z, BOPCBlocks.colorizedLeaves1, 2);
        buildBlock(world, x, y + 2, z + 2, BOPCBlocks.colorizedLeaves1, 2);
        buildBlock(world, x, y + 2, z - 2, BOPCBlocks.colorizedLeaves1, 2);
    }

    public void buildBlock(World world, int x, int y, int z, Block block, int meta)
    {
    	if (world.isAirBlock(x, y, z) || world.getBlock(x, y, z).isLeaves(world, x, y, z))
		{
    		world.setBlock(x, y, z, block, meta, 2);
		}
    }
}
