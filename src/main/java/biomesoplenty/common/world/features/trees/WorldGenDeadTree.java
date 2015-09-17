package biomesoplenty.common.world.features.trees;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import biomesoplenty.api.content.BOPCBlocks;

public class WorldGenDeadTree extends WorldGenAbstractTree
{
	private int strengthRand;
	private int bMax;
	private double offset;
	
	public WorldGenDeadTree()
	{
		this(35, 10, 1.3D);
	}
	
    public WorldGenDeadTree(int strengthRand, int bMax, double offset)
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

        if (block != Blocks.grass && block != Blocks.dirt && block != BOPCBlocks.driedDirt && block != Blocks.hardened_clay && block != BOPCBlocks.newBopDirt  && block != BOPCBlocks.newBopGrass)
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
            
            int var999 = random.nextInt(8);
            
            if (var999 == 0)
            {
            	buildBlock(world, x, y + 1, z, BOPCBlocks.logs3, 2);
	            buildBlock(world, x, y + 2, z, BOPCBlocks.logs3, 2);
	            buildBlock(world, x, y + 3, z, BOPCBlocks.logs3, 2);
	            buildBlock(world, x, y + 4, z, BOPCBlocks.logs3, 2);
	            buildBlock(world, x, y + 5, z, BOPCBlocks.logs3, 2);
	            buildBlock(world, x, y + 6, z, BOPCBlocks.logs3, 2);
	            buildBlock(world, x, y + 7, z, BOPCBlocks.logs3, 2);
	            buildBlock(world, x, y + 8, z, BOPCBlocks.logs3, 2);
	            buildBlock(world, x, y + 9, z, BOPCBlocks.logs3, 2);
	            
	            buildBlock(world, x + 1, y + 3, z, BOPCBlocks.logs3, 6);
	            buildBlock(world, x + 2, y + 3, z, BOPCBlocks.logs3, 6);
	            buildBlock(world, x + 1, y + 4, z, BOPCBlocks.logs3, 6);
	            buildBlock(world, x + 2, y + 5, z, BOPCBlocks.logs3, 6);
	            buildBlock(world, x + 3, y + 5, z, BOPCBlocks.logs3, 6);
	            buildBlock(world, x + 1, y + 7, z, BOPCBlocks.logs3, 6);
	            buildBlock(world, x + 2, y + 7, z, BOPCBlocks.logs3, 6);
	            
	            buildBlock(world, x - 1, y + 2, z, BOPCBlocks.logs3, 6);
	            buildBlock(world, x - 2, y + 4, z, BOPCBlocks.logs3, 6);
	            buildBlock(world, x - 1, y + 4, z, BOPCBlocks.logs3, 6);
	            buildBlock(world, x - 1, y + 6, z, BOPCBlocks.logs3, 2);
	            buildBlock(world, x - 1, y + 7, z, BOPCBlocks.logs3, 2);
	            buildBlock(world, x - 2, y + 8, z, BOPCBlocks.logs3, 6);
	            
	            buildBlock(world, x, y + 3, z + 1, BOPCBlocks.logs3, 10);
	            buildBlock(world, x, y + 3, z + 2, BOPCBlocks.logs3, 10);
	            buildBlock(world, x, y + 5, z + 1, BOPCBlocks.logs3, 10);
	            buildBlock(world, x, y + 6, z + 2, BOPCBlocks.logs3, 10);
	            buildBlock(world, x, y + 6, z + 3, BOPCBlocks.logs3, 10);
	            buildBlock(world, x, y + 8, z + 1, BOPCBlocks.logs3, 10);
	            
	            buildBlock(world, x, y + 3, z - 1, BOPCBlocks.logs3, 2);
	            buildBlock(world, x, y + 4, z - 2, BOPCBlocks.logs3, 10);
	            buildBlock(world, x, y + 6, z - 1, BOPCBlocks.logs3, 2);
	            buildBlock(world, x, y + 7, z - 2, BOPCBlocks.logs3, 10);
            }
            else if (var999 == 1)
            {
            	buildBlock(world, x, y + 1, z, BOPCBlocks.logs3, 2);
	            buildBlock(world, x, y + 2, z, BOPCBlocks.logs3, 2);
	            buildBlock(world, x, y + 3, z, BOPCBlocks.logs3, 2);
	            buildBlock(world, x, y + 4, z, BOPCBlocks.logs3, 2);
	            buildBlock(world, x, y + 5, z, BOPCBlocks.logs3, 2);
	            buildBlock(world, x, y + 6, z, BOPCBlocks.logs3, 2);
	            buildBlock(world, x, y + 7, z, BOPCBlocks.logs3, 2);
	            buildBlock(world, x, y + 8, z, BOPCBlocks.logs3, 2);
	            buildBlock(world, x, y + 9, z, BOPCBlocks.logs3, 2);
	            
	            buildBlock(world, x - 1, y + 3, z, BOPCBlocks.logs3, 6);
	            buildBlock(world, x - 2, y + 3, z, BOPCBlocks.logs3, 6);
	            buildBlock(world, x - 1, y + 4, z, BOPCBlocks.logs3, 6);
	            buildBlock(world, x - 2, y + 5, z, BOPCBlocks.logs3, 6);
	            buildBlock(world, x - 3, y + 5, z, BOPCBlocks.logs3, 6);
	            buildBlock(world, x - 1, y + 7, z, BOPCBlocks.logs3, 6);
	            buildBlock(world, x - 2, y + 7, z, BOPCBlocks.logs3, 6);
	            
	            buildBlock(world, x + 1, y + 2, z, BOPCBlocks.logs3, 6);
	            buildBlock(world, x + 2, y + 4, z, BOPCBlocks.logs3, 6);
	            buildBlock(world, x + 1, y + 4, z, BOPCBlocks.logs3, 6);
	            buildBlock(world, x + 1, y + 6, z, BOPCBlocks.logs3, 2);
	            buildBlock(world, x + 1, y + 7, z, BOPCBlocks.logs3, 2);
	            buildBlock(world, x + 2, y + 8, z, BOPCBlocks.logs3, 6);
	            
	            buildBlock(world, x, y + 3, z + 1, BOPCBlocks.logs3, 10);
	            buildBlock(world, x, y + 3, z + 2, BOPCBlocks.logs3, 10);
	            buildBlock(world, x, y + 5, z + 1, BOPCBlocks.logs3, 10);
	            buildBlock(world, x, y + 6, z + 2, BOPCBlocks.logs3, 10);
	            buildBlock(world, x, y + 6, z + 3, BOPCBlocks.logs3, 10);
	            buildBlock(world, x, y + 8, z + 1, BOPCBlocks.logs3, 10);
	            
	            buildBlock(world, x, y + 3, z - 1, BOPCBlocks.logs3, 2);
	            buildBlock(world, x, y + 4, z - 2, BOPCBlocks.logs3, 10);
	            buildBlock(world, x, y + 6, z - 1, BOPCBlocks.logs3, 2);
	            buildBlock(world, x, y + 7, z - 2, BOPCBlocks.logs3, 10);
            }
            else if (var999 == 2)
            {
            	buildBlock(world, x, y + 1, z, BOPCBlocks.logs3, 2);
	            buildBlock(world, x, y + 2, z, BOPCBlocks.logs3, 2);
	            buildBlock(world, x, y + 3, z, BOPCBlocks.logs3, 2);
	            buildBlock(world, x, y + 4, z, BOPCBlocks.logs3, 2);
	            buildBlock(world, x, y + 5, z, BOPCBlocks.logs3, 2);
	            buildBlock(world, x, y + 6, z, BOPCBlocks.logs3, 2);
	            buildBlock(world, x, y + 7, z, BOPCBlocks.logs3, 2);
	            buildBlock(world, x, y + 8, z, BOPCBlocks.logs3, 2);
	            buildBlock(world, x, y + 9, z, BOPCBlocks.logs3, 2);
	            
	            buildBlock(world, x + 1, y + 3, z, BOPCBlocks.logs3, 6);
	            buildBlock(world, x + 2, y + 3, z, BOPCBlocks.logs3, 6);
	            buildBlock(world, x + 1, y + 4, z, BOPCBlocks.logs3, 6);
	            buildBlock(world, x + 2, y + 5, z, BOPCBlocks.logs3, 6);
	            buildBlock(world, x + 3, y + 5, z, BOPCBlocks.logs3, 6);
	            buildBlock(world, x + 1, y + 7, z, BOPCBlocks.logs3, 6);
	            buildBlock(world, x + 2, y + 7, z, BOPCBlocks.logs3, 6);
	            
	            buildBlock(world, x - 1, y + 2, z, BOPCBlocks.logs3, 6);
	            buildBlock(world, x - 2, y + 4, z, BOPCBlocks.logs3, 6);
	            buildBlock(world, x - 1, y + 4, z, BOPCBlocks.logs3, 6);
	            buildBlock(world, x - 1, y + 6, z, BOPCBlocks.logs3, 2);
	            buildBlock(world, x - 1, y + 7, z, BOPCBlocks.logs3, 2);
	            buildBlock(world, x - 2, y + 8, z, BOPCBlocks.logs3, 6);
	            
	            buildBlock(world, x, y + 3, z - 1, BOPCBlocks.logs3, 10);
	            buildBlock(world, x, y + 3, z - 2, BOPCBlocks.logs3, 10);
	            buildBlock(world, x, y + 5, z - 1, BOPCBlocks.logs3, 10);
	            buildBlock(world, x, y + 6, z - 2, BOPCBlocks.logs3, 10);
	            buildBlock(world, x, y + 6, z - 3, BOPCBlocks.logs3, 10);
	            buildBlock(world, x, y + 8, z - 1, BOPCBlocks.logs3, 10);
	            
	            buildBlock(world, x, y + 3, z + 1, BOPCBlocks.logs3, 2);
	            buildBlock(world, x, y + 4, z + 2, BOPCBlocks.logs3, 10);
	            buildBlock(world, x, y + 6, z + 1, BOPCBlocks.logs3, 2);
	            buildBlock(world, x, y + 7, z + 2, BOPCBlocks.logs3, 10);
            }
            else if (var999 == 3)
            {
            	buildBlock(world, x, y + 1, z, BOPCBlocks.logs3, 2);
	            buildBlock(world, x, y + 2, z, BOPCBlocks.logs3, 2);
	            buildBlock(world, x, y + 3, z, BOPCBlocks.logs3, 2);
	            buildBlock(world, x, y + 4, z, BOPCBlocks.logs3, 2);
	            buildBlock(world, x, y + 5, z, BOPCBlocks.logs3, 2);
	            buildBlock(world, x, y + 6, z, BOPCBlocks.logs3, 2);
	            buildBlock(world, x, y + 7, z, BOPCBlocks.logs3, 2);
	            buildBlock(world, x, y + 8, z, BOPCBlocks.logs3, 2);
	            buildBlock(world, x, y + 9, z, BOPCBlocks.logs3, 2);
	            
	            buildBlock(world, x, y + 3, z - 1, BOPCBlocks.logs3, 10);
	            buildBlock(world, x, y + 3, z - 2, BOPCBlocks.logs3, 10);
	            buildBlock(world, x, y + 4, z - 1, BOPCBlocks.logs3, 10);
	            buildBlock(world, x, y + 5, z - 2, BOPCBlocks.logs3, 10);
	            buildBlock(world, x, y + 5, z - 3, BOPCBlocks.logs3, 10);
	            buildBlock(world, x, y + 7, z - 1, BOPCBlocks.logs3, 10);
	            buildBlock(world, x, y + 7, z - 2, BOPCBlocks.logs3, 10);
	            
	            buildBlock(world, x, y + 2, z + 1, BOPCBlocks.logs3, 10);
	            buildBlock(world, x, y + 4, z + 2, BOPCBlocks.logs3, 10);
	            buildBlock(world, x, y + 4, z + 1, BOPCBlocks.logs3, 10);
	            buildBlock(world, x, y + 6, z + 1, BOPCBlocks.logs3, 2);
	            buildBlock(world, x, y + 7, z + 1, BOPCBlocks.logs3, 2);
	            buildBlock(world, x, y + 8, z + 2, BOPCBlocks.logs3, 10);
	            
	            buildBlock(world, x - 1, y + 3, z, BOPCBlocks.logs3, 6);
	            buildBlock(world, x - 2, y + 3, z, BOPCBlocks.logs3, 6);
	            buildBlock(world, x - 1, y + 5, z, BOPCBlocks.logs3, 6);
	            buildBlock(world, x - 2, y + 6, z, BOPCBlocks.logs3, 6);
	            buildBlock(world, x - 3, y + 6, z, BOPCBlocks.logs3, 6);
	            buildBlock(world, x - 1, y + 8, z, BOPCBlocks.logs3, 6);
	            
	            buildBlock(world, x + 1, y + 3, z, BOPCBlocks.logs3, 2);
	            buildBlock(world, x + 2, y + 4, z, BOPCBlocks.logs3, 6);
	            buildBlock(world, x + 1, y + 6, z, BOPCBlocks.logs3, 2);
	            buildBlock(world, x + 2, y + 7, z, BOPCBlocks.logs3, 6);
            }
            else if (var999 == 4)
            {
            	buildBlock(world, x, y + 1, z, BOPCBlocks.logs3, 2);
	            buildBlock(world, x, y + 2, z, BOPCBlocks.logs3, 2);
	            buildBlock(world, x, y + 3, z, BOPCBlocks.logs3, 2);
	            buildBlock(world, x, y + 4, z, BOPCBlocks.logs3, 2);
	            buildBlock(world, x, y + 5, z, BOPCBlocks.logs3, 2);
	            buildBlock(world, x, y + 6, z, BOPCBlocks.logs3, 2);
	            buildBlock(world, x, y + 7, z, BOPCBlocks.logs3, 2);
	            buildBlock(world, x, y + 8, z, BOPCBlocks.logs3, 2);
	            buildBlock(world, x, y + 9, z, BOPCBlocks.logs3, 2);
	            
	            buildBlock(world, x, y + 3, z + 1, BOPCBlocks.logs3, 10);
	            buildBlock(world, x, y + 3, z + 2, BOPCBlocks.logs3, 10);
	            buildBlock(world, x, y + 4, z + 1, BOPCBlocks.logs3, 10);
	            buildBlock(world, x, y + 5, z + 2, BOPCBlocks.logs3, 10);
	            buildBlock(world, x, y + 5, z + 3, BOPCBlocks.logs3, 10);
	            buildBlock(world, x, y + 7, z + 1, BOPCBlocks.logs3, 10);
	            buildBlock(world, x, y + 7, z + 2, BOPCBlocks.logs3, 10);
	            
	            buildBlock(world, x, y + 2, z - 1, BOPCBlocks.logs3, 10);
	            buildBlock(world, x, y + 4, z - 2, BOPCBlocks.logs3, 10);
	            buildBlock(world, x, y + 4, z - 1, BOPCBlocks.logs3, 10);
	            buildBlock(world, x, y + 6, z - 1, BOPCBlocks.logs3, 2);
	            buildBlock(world, x, y + 7, z - 1, BOPCBlocks.logs3, 2);
	            buildBlock(world, x, y + 8, z - 2, BOPCBlocks.logs3, 10);
	            
	            buildBlock(world, x + 1, y + 3, z, BOPCBlocks.logs3, 6);
	            buildBlock(world, x + 2, y + 3, z, BOPCBlocks.logs3, 6);
	            buildBlock(world, x + 1, y + 5, z, BOPCBlocks.logs3, 6);
	            buildBlock(world, x + 2, y + 6, z, BOPCBlocks.logs3, 6);
	            buildBlock(world, x + 3, y + 6, z, BOPCBlocks.logs3, 6);
	            buildBlock(world, x + 1, y + 8, z, BOPCBlocks.logs3, 6);
	            
	            buildBlock(world, x - 1, y + 3, z, BOPCBlocks.logs3, 2);
	            buildBlock(world, x - 2, y + 4, z, BOPCBlocks.logs3, 6);
	            buildBlock(world, x - 1, y + 6, z, BOPCBlocks.logs3, 2);
	            buildBlock(world, x - 2, y + 7, z, BOPCBlocks.logs3, 6);
            }
            else if (var999 == 5)
            {
            	buildBlock(world, x, y + 1, z, BOPCBlocks.logs3, 2);
	            buildBlock(world, x, y + 2, z, BOPCBlocks.logs3, 2);
	            buildBlock(world, x, y + 3, z, BOPCBlocks.logs3, 2);
	            buildBlock(world, x, y + 4, z, BOPCBlocks.logs3, 2);
	            buildBlock(world, x, y + 5, z, BOPCBlocks.logs3, 2);
	            buildBlock(world, x, y + 6, z, BOPCBlocks.logs3, 2);
	            buildBlock(world, x, y + 7, z, BOPCBlocks.logs3, 2);
	            buildBlock(world, x, y + 8, z, BOPCBlocks.logs3, 2);
	            buildBlock(world, x, y + 9, z, BOPCBlocks.logs3, 2);
	            
	            buildBlock(world, x, y + 3, z + 1, BOPCBlocks.logs3, 10);
	            buildBlock(world, x, y + 3, z + 2, BOPCBlocks.logs3, 10);
	            buildBlock(world, x, y + 4, z + 1, BOPCBlocks.logs3, 10);
	            buildBlock(world, x, y + 5, z + 2, BOPCBlocks.logs3, 10);
	            buildBlock(world, x, y + 5, z + 3, BOPCBlocks.logs3, 10);
	            buildBlock(world, x, y + 7, z + 1, BOPCBlocks.logs3, 10);
	            buildBlock(world, x, y + 7, z + 2, BOPCBlocks.logs3, 10);
	            
	            buildBlock(world, x, y + 2, z - 1, BOPCBlocks.logs3, 10);
	            buildBlock(world, x, y + 4, z - 2, BOPCBlocks.logs3, 10);
	            buildBlock(world, x, y + 4, z - 1, BOPCBlocks.logs3, 10);
	            buildBlock(world, x, y + 6, z - 1, BOPCBlocks.logs3, 2);
	            buildBlock(world, x, y + 7, z - 1, BOPCBlocks.logs3, 2);
	            buildBlock(world, x, y + 8, z - 2, BOPCBlocks.logs3, 10);
	            
	            buildBlock(world, x - 1, y + 3, z, BOPCBlocks.logs3, 6);
	            buildBlock(world, x - 2, y + 3, z, BOPCBlocks.logs3, 6);
	            buildBlock(world, x - 1, y + 5, z, BOPCBlocks.logs3, 6);
	            buildBlock(world, x - 2, y + 6, z, BOPCBlocks.logs3, 6);
	            buildBlock(world, x - 3, y + 6, z, BOPCBlocks.logs3, 6);
	            buildBlock(world, x - 1, y + 8, z, BOPCBlocks.logs3, 6);
	            
	            buildBlock(world, x + 1, y + 3, z, BOPCBlocks.logs3, 2);
	            buildBlock(world, x + 2, y + 4, z, BOPCBlocks.logs3, 6);
	            buildBlock(world, x + 1, y + 6, z, BOPCBlocks.logs3, 2);
	            buildBlock(world, x + 2, y + 7, z, BOPCBlocks.logs3, 6);
            }
            else if (var999 == 6)
            {
            	buildBlock(world, x, y + 1, z, BOPCBlocks.logs3, 2);
	            buildBlock(world, x, y + 2, z, BOPCBlocks.logs3, 2);
	            buildBlock(world, x, y + 3, z, BOPCBlocks.logs3, 2);
	            buildBlock(world, x, y + 4, z, BOPCBlocks.logs3, 2);
	            buildBlock(world, x, y + 5, z, BOPCBlocks.logs3, 2);
	            buildBlock(world, x, y + 6, z, BOPCBlocks.logs3, 2);
	            buildBlock(world, x, y + 7, z, BOPCBlocks.logs3, 2);
	            buildBlock(world, x, y + 8, z, BOPCBlocks.logs3, 2);
	            buildBlock(world, x, y + 9, z, BOPCBlocks.logs3, 2);
	            
	            buildBlock(world, x, y + 3, z - 1, BOPCBlocks.logs3, 10);
	            buildBlock(world, x, y + 3, z - 2, BOPCBlocks.logs3, 10);
	            buildBlock(world, x, y + 4, z - 1, BOPCBlocks.logs3, 10);
	            buildBlock(world, x, y + 5, z - 2, BOPCBlocks.logs3, 10);
	            buildBlock(world, x, y + 5, z - 3, BOPCBlocks.logs3, 10);
	            buildBlock(world, x, y + 7, z - 1, BOPCBlocks.logs3, 10);
	            buildBlock(world, x, y + 7, z - 2, BOPCBlocks.logs3, 10);
	            
	            buildBlock(world, x, y + 2, z + 1, BOPCBlocks.logs3, 10);
	            buildBlock(world, x, y + 4, z + 2, BOPCBlocks.logs3, 10);
	            buildBlock(world, x, y + 4, z + 1, BOPCBlocks.logs3, 10);
	            buildBlock(world, x, y + 6, z + 1, BOPCBlocks.logs3, 2);
	            buildBlock(world, x, y + 7, z + 1, BOPCBlocks.logs3, 2);
	            buildBlock(world, x, y + 8, z + 2, BOPCBlocks.logs3, 10);
	            
	            buildBlock(world, x + 1, y + 3, z, BOPCBlocks.logs3, 6);
	            buildBlock(world, x + 2, y + 3, z, BOPCBlocks.logs3, 6);
	            buildBlock(world, x + 1, y + 5, z, BOPCBlocks.logs3, 6);
	            buildBlock(world, x + 2, y + 6, z, BOPCBlocks.logs3, 6);
	            buildBlock(world, x + 3, y + 6, z, BOPCBlocks.logs3, 6);
	            buildBlock(world, x + 1, y + 8, z, BOPCBlocks.logs3, 6);
	            
	            buildBlock(world, x - 1, y + 3, z, BOPCBlocks.logs3, 2);
	            buildBlock(world, x - 2, y + 4, z, BOPCBlocks.logs3, 6);
	            buildBlock(world, x - 1, y + 6, z, BOPCBlocks.logs3, 2);
	            buildBlock(world, x - 2, y + 7, z, BOPCBlocks.logs3, 6);
            }
            else
            {
            	buildBlock(world, x, y + 1, z, BOPCBlocks.logs3, 2);
	            buildBlock(world, x, y + 2, z, BOPCBlocks.logs3, 2);
	            buildBlock(world, x, y + 3, z, BOPCBlocks.logs3, 2);
	            buildBlock(world, x, y + 4, z, BOPCBlocks.logs3, 2);
	            buildBlock(world, x, y + 5, z, BOPCBlocks.logs3, 2);
	            buildBlock(world, x, y + 6, z, BOPCBlocks.logs3, 2);
	            buildBlock(world, x, y + 7, z, BOPCBlocks.logs3, 2);
	            buildBlock(world, x, y + 8, z, BOPCBlocks.logs3, 2);
	            buildBlock(world, x, y + 9, z, BOPCBlocks.logs3, 2);
	            
	            buildBlock(world, x - 1, y + 3, z, BOPCBlocks.logs3, 6);
	            buildBlock(world, x - 2, y + 3, z, BOPCBlocks.logs3, 6);
	            buildBlock(world, x - 1, y + 4, z, BOPCBlocks.logs3, 6);
	            buildBlock(world, x - 2, y + 5, z, BOPCBlocks.logs3, 6);
	            buildBlock(world, x - 3, y + 5, z, BOPCBlocks.logs3, 6);
	            buildBlock(world, x - 1, y + 7, z, BOPCBlocks.logs3, 6);
	            buildBlock(world, x - 2, y + 7, z, BOPCBlocks.logs3, 6);
	            
	            buildBlock(world, x + 1, y + 2, z, BOPCBlocks.logs3, 6);
	            buildBlock(world, x + 2, y + 4, z, BOPCBlocks.logs3, 6);
	            buildBlock(world, x + 1, y + 4, z, BOPCBlocks.logs3, 6);
	            buildBlock(world, x + 1, y + 6, z, BOPCBlocks.logs3, 2);
	            buildBlock(world, x + 1, y + 7, z, BOPCBlocks.logs3, 2);
	            buildBlock(world, x + 2, y + 8, z, BOPCBlocks.logs3, 6);
	            
	            buildBlock(world, x, y + 3, z - 1, BOPCBlocks.logs3, 10);
	            buildBlock(world, x, y + 3, z - 2, BOPCBlocks.logs3, 10);
	            buildBlock(world, x, y + 5, z - 1, BOPCBlocks.logs3, 10);
	            buildBlock(world, x, y + 6, z - 2, BOPCBlocks.logs3, 10);
	            buildBlock(world, x, y + 6, z - 3, BOPCBlocks.logs3, 10);
	            buildBlock(world, x, y + 8, z - 1, BOPCBlocks.logs3, 10);
	            
	            buildBlock(world, x, y + 3, z + 1, BOPCBlocks.logs3, 2);
	            buildBlock(world, x, y + 4, z + 2, BOPCBlocks.logs3, 10);
	            buildBlock(world, x, y + 6, z + 1, BOPCBlocks.logs3, 2);
	            buildBlock(world, x, y + 7, z + 2, BOPCBlocks.logs3, 10);
            }
            
            return true;
        }
    }

    public void buildBlock(World world, int x, int y, int z, Block block, int meta)
    {
    	if (world.isAirBlock(x, y, z) || world.getBlock(x, y, z).isLeaves(world, x, y, z))
		{
    		world.setBlock(x, y, z, block, meta, 2);
		}
    }
}
