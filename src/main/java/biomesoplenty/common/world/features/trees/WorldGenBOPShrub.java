package biomesoplenty.common.world.features.trees;
 
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenTrees;

public class WorldGenBOPShrub extends WorldGenTrees
{
	private Block wood;
	private Block leaves;
	
	private int metaWood;
	private int metaLeaves;
	
	private int minHeight;
	private int maxHeight;
	
	private List soilBlocks;
	
	public WorldGenBOPShrub(Block wood, Block leaves, int metaWood, int metaLeaves, Block... soilBlocks)
	{
		this(wood, leaves, metaWood, metaLeaves, 0, 256, soilBlocks);
	}
	
    public WorldGenBOPShrub(Block wood, Block leaves, int metaWood, int metaLevaes, int minHeight, int maxHeight, Block... soilBlocks)
    {
        super(false);
        
        this.wood = wood;
        this.leaves = leaves;
        
        this.metaWood = metaWood;
        this.metaLeaves = metaLeaves;
        
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
        
        this.soilBlocks = Arrays.asList(soilBlocks);
    }

    @Override
	public boolean generate(World world, Random random, int x, int y, int z)
    {
        Block block;

        do
        {
            block = world.func_147439_a(x, y, z);
            if (!(block.isLeaves(world, x, y, z) || block.isAir(world, x, y, z)))
            {
                break;
            }
            --y;
        } while (y > 0);

        Block block1 = world.func_147439_a(x, y, z);

        if (soilBlocks.contains(block1))
        {
            ++y;
            
            if (y > minHeight && y < maxHeight)
            {
            	this.func_150516_a(world, x, y, z, wood, metaWood);

            	for (int l = y; l <= y + 2; ++l)
            	{
            		int i1 = l - y;
            		int j1 = 2 - i1;

            		for (int k1 = x - j1; k1 <= x + j1; ++k1)
            		{
            			int l1 = k1 - x;

            			for (int i2 = z - j1; i2 <= z + j1; ++i2)
            			{
            				int j2 = i2 - z;

            				if ((Math.abs(l1) != j1 || Math.abs(j2) != j1 || random.nextInt(2) != 0) && world.func_147439_a(k1, l, i2).canBeReplacedByLeaves(world, k1, l, i2))
            				{
            					this.func_150516_a(world, k1, l, i2, leaves, metaLeaves);
            				}
            			}
            		}
            	}
            }
        }

        return true;
    }
}
