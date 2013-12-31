package biomesoplenty.common.world.generators;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenBOPFlowers extends WorldGenerator
{
    private Block flower;
    private int flowerMeta;
    private int groupCount = 64;

    public WorldGenBOPFlowers(Block flower, int flowerMeta)
    {
        this.flower = flower;
        this.flowerMeta = flowerMeta;
    }
    
    public WorldGenBOPFlowers(Block flower, int flowerMeta, int groupCount)
    {
        this.flower = flower;
        this.flowerMeta = flowerMeta;
        this.groupCount = groupCount;
    }

    @Override
	public boolean generate(World world, Random random, int x, int y, int z)
    {
        for (int l = 0; l < groupCount; ++l)
        {
            int i1 = x + random.nextInt(8) - random.nextInt(8);
            int j1 = y + random.nextInt(4) - random.nextInt(4);
            int k1 = z + random.nextInt(8) - random.nextInt(8);

            //TODO:	  isAirBlock()																		 canReplace()
            if (world.func_147437_c(i1, j1, k1) && (!world.provider.hasNoSky || j1 < 255) && this.flower.func_149705_a(world, i1, j1, k1, 0, new ItemStack(flower, 1, flowerMeta)))
            {
            	//TODO:	setBlock()
                world.func_147465_d(i1, j1, k1, this.flower, this.flowerMeta, 2);
            }
        }

        return true;
    }
}