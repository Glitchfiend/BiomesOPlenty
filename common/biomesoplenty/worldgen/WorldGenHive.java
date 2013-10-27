package biomesoplenty.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.Blocks;

public class WorldGenHive extends WorldGenerator
{
	@Override
	public boolean generate(World var1, Random var2, int var3, int var4, int var5)
	{ 
		if (var1.getBlockId(var3, var4 + 9, var5) != Block.netherrack.blockID)
			{
			if (!var1.isAirBlock(var3, var4 + 8, var5))
				{
					return false;
				}
			}
		
        int x = var3;
        int y = var4 + 9;
        int z = var5;
        
	    int baseWidth = 4 + var1.rand.nextInt(2);
	    int baseHeight = 8 + var1.rand.nextInt(2);
	    
	    for (int cubeno = 0; cubeno < 3; cubeno++)
	    {
	        float chance = 0.0F;
	        
	        switch (cubeno)
	        {
	            case 0:
	                chance = 0.25F;
	                break;
	                
	            case 1:
	                chance = 1.0F;
	                break;
	                
	            case 2:
	                chance = 0.75F;
	                break;
	        }
	        
	        
	        //Top
	        generateHiveCubeSmall(var1, x, (y + 2) + cubeno, z, (baseHeight - 8) + (cubeno * 2), (baseWidth - 1) + cubeno, cubeno, chance);
	        
	        //Middle
	        generateHiveCube(var1, x, y + cubeno, z, baseHeight + (cubeno * 2), baseWidth + cubeno, cubeno, chance);
	        
	        //Bottom
	        generateHiveCubeSmall(var1, x, (y - (baseHeight + 2)) + cubeno, z, (baseHeight - 8) + (cubeno * 2), (baseWidth - 1) + cubeno, cubeno, chance);
	        
	        //Bottom 2
	        generateHiveCubeSmall(var1, x, (y - (baseHeight + 3)) + cubeno, z, (baseHeight - 7) + (cubeno * 2), (baseWidth - 2) + cubeno, cubeno, chance);
	        
	        //Bottom 3
	        generateHiveCubeSmall(var1, x, (y - (baseHeight + 5)) + cubeno, z, (baseHeight - 7) + (cubeno * 2), (baseWidth - 4) + cubeno, cubeno, chance);
	    }
	    
	    return true;
	}
	
	public void generateHiveCube(World world, int origx, int origy, int origz, int height, int width, int cubeno, float chance)
	{
        for (int hLayer = 0; hLayer < height; hLayer++)
        {     
            for (int i = -width; i < width; i++)
            {
                for (int j = -width; j < width; j++)
                {
                    if ((hLayer == 0 || hLayer == (height - 1)) && (world.rand.nextFloat() <= chance)) world.setBlock(origx + i, origy - hLayer, origz + j, Blocks.hive.get().blockID); 
                    else if ((i == -width || i == (width - 1) || j == -width || j == (width - 1)) && (world.rand.nextFloat() <= chance)) world.setBlock(origx + i, origy - hLayer, origz + j, Blocks.hive.get().blockID);
                    
                    if (cubeno < 2 && world.getBlockId(origx + i, origy - hLayer, origz + j) != Blocks.hive.get().blockID) world.setBlockToAir(origx + i, origy - hLayer, origz + j);
                }
            }
        }
	}
	
	public void generateHiveCubeSmall(World world, int origx, int origy, int origz, int height, int width, int cubeno, float chance)
	{
        for (int hLayer = 0; hLayer < height; hLayer++)
        {     
            for (int i = -width; i < width; i++)
            {
                for (int j = -width; j < width; j++)
                {
                    if ((hLayer == 0 || hLayer == (height - 1)) && (world.rand.nextFloat() <= chance)) world.setBlock(origx + i, origy - hLayer, origz + j, Blocks.hive.get().blockID); 
                    else if ((i == -width || i == (width - 1) || j == -width || j == (width - 1)) && (world.rand.nextFloat() <= chance)) world.setBlock(origx + i, origy - hLayer, origz + j, Blocks.hive.get().blockID);
                }
            }
        }
	}
}
