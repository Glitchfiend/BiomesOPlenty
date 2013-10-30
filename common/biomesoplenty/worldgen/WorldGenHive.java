package biomesoplenty.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.Blocks;
import biomesoplenty.api.Fluids;
import biomesoplenty.entities.EntityWasp;

public class WorldGenHive extends WorldGenerator
{
	@Override
	public boolean generate(World world, Random rand, int x, int y, int z)
	{ 
        
	    int baseWidth = 4 + rand.nextInt(2);
	    int baseHeight = 8 + rand.nextInt(2);
	    
		if (world.getBlockId(x, y + 3, z) != Block.netherrack.blockID || !world.isAirBlock(x, y + 2, z))
			{
			return false;
			}
	    
	    for (int cubeno = 0; cubeno < 4; cubeno++)
	    {
	        float chance = 0.0F;
	        int meta = 0;
	        
	        switch (cubeno)
	        {
	            case 0:
	                chance = 0.25F;
	                meta = 0;
	                break;
	                
	            case 1:
	                chance = 1.0F;
	                meta = 0;
	                break;
	                
	            case 2:
	                chance = 1.0F;
	                meta = 2;
	                break;
	                
	            case 3:
	                chance = 0.5F;
	                meta = 2;
	                break;
	        }
	        
	        int honeychance = rand.nextInt(2);
	        
	        //Top
	        generateHiveCubeSmall(world, x, y + cubeno, z, (baseHeight - 11) + (cubeno * 2), (baseWidth - 1) + cubeno, cubeno, chance, meta);
	        
	        //Middle
	        generateHiveCube(world, x, (y - 2) + cubeno, z, baseHeight + (cubeno * 2), baseWidth + cubeno, cubeno, chance, honeychance, meta);
	        
	        //Bottom
	        generateHiveCubeSmall(world, x, (y - (baseHeight + 6)) + cubeno, z, (baseHeight - 10) + (cubeno * 2), (baseWidth - 1) + cubeno, cubeno, chance, meta);
	        
	        //Bottom 2
	        generateHiveCubeSmall(world, x, (y - (baseHeight + 7)) + cubeno, z, (baseHeight - 9) + (cubeno * 2), (baseWidth - 2) + cubeno, cubeno, chance, meta);
	        
	        //Bottom 3
	        generateHiveCubeSmall(world, x, (y - (baseHeight + 9)) + cubeno, z, (baseHeight - 9) + (cubeno * 2), (baseWidth - 4) + cubeno, cubeno, chance, meta);
	        
	        spawnWasps(world, rand, x, y, z);
	        
	        spawnEmptyHoneycombs(world, rand, x, y, z);
	    }
	    
	    return true;
	}
	
	public void generateHiveCube(World world, int origx, int origy, int origz, int height, int width, int cubeno, float chance, int honeychance, int meta)
	{
        for (int hLayer = 0; hLayer < height; hLayer++)
        {     
            for (int i = -width; i < width; i++)
            {
                for (int j = -width; j < width; j++)
                {
                    if ((hLayer == 0 || hLayer == (height - 1)) && (world.rand.nextFloat() <= chance)) world.setBlock(origx + i, origy - hLayer, origz + j, Blocks.hive.get().blockID, meta, 2); 
                    else if ((i == -width || i == (width - 1) || j == -width || j == (width - 1)) && (world.rand.nextFloat() <= chance)) world.setBlock(origx + i, origy - hLayer, origz + j, Blocks.hive.get().blockID, meta, 2);
                    
                    if (hLayer > (height / 2))
                    {
                        if (honeychance == 0)
                        {
                            if (cubeno < 2 && world.getBlockId(origx + i, origy - hLayer, origz + j) != Blocks.hive.get().blockID) world.setBlock(origx + i, origy - hLayer, origz + j, Fluids.honey.get().blockID, 7, 0);
                            if (cubeno < 2 && world.getBlockId(origx + i, origy - hLayer, origz + j) == Blocks.hive.get().blockID && world.getBlockMetadata(origx + i, origy - hLayer, origz + j) != 0) world.setBlock(origx + i, origy - hLayer, origz + j, Fluids.honey.get().blockID, 7, 0);
                        }
                        else
                        {
                            if (cubeno < 2 && world.getBlockId(origx + i, origy - hLayer, origz + j) != Blocks.hive.get().blockID) world.setBlockToAir(origx + i, origy - hLayer, origz + j);
                            if (cubeno < 2 && world.getBlockId(origx + i, origy - hLayer, origz + j) == Blocks.hive.get().blockID && world.getBlockMetadata(origx + i, origy - hLayer, origz + j) != 0) world.setBlockToAir(origx + i, origy - hLayer, origz + j);
                        }
                    }
                    else
                    {
                        if (cubeno < 2 && world.getBlockId(origx + i, origy - hLayer, origz + j) != Blocks.hive.get().blockID) world.setBlockToAir(origx + i, origy - hLayer, origz + j);
                        if (cubeno < 2 && world.getBlockId(origx + i, origy - hLayer, origz + j) == Blocks.hive.get().blockID && world.getBlockMetadata(origx + i, origy - hLayer, origz + j) != 0) world.setBlockToAir(origx + i, origy - hLayer, origz + j);
                    }
                }
            }
        }
	}
	
	public void generateHiveCubeSmall(World world, int origx, int origy, int origz, int height, int width, int cubeno, float chance, int meta)
	{
        for (int hLayer = 0; hLayer < height; hLayer++)
        {     
            for (int i = -width; i < width; i++)
            {
                for (int j = -width; j < width; j++)
                {
                    if ((hLayer == 0 || hLayer == (height - 1)) && (world.rand.nextFloat() <= chance)) world.setBlock(origx + i, origy - hLayer, origz + j, Blocks.hive.get().blockID, meta, 2); 
                    else if ((i == -width || i == (width - 1) || j == -width || j == (width - 1)) && (world.rand.nextFloat() <= chance)) world.setBlock(origx + i, origy - hLayer, origz + j, Blocks.hive.get().blockID, meta, 2);
                }
            }
        }
	}
	
	public void spawnWasps(World world, Random rand, int x, int y, int z)
	{
		for (int spawn = 0; spawn < 20; spawn++)
		{
			int spawnx = (x - 12) + rand.nextInt(24);
			int spawny = y - rand.nextInt(24);
			int spawnz = (z - 12) + rand.nextInt(24);
			
			if (world.getBlockId(spawnx, spawny, spawnz) == Blocks.hive.get().blockID)
			{
				if (world.getBlockMetadata(spawnx, spawny, spawnz) == 0)
				{
				    world.setBlock(spawnx, spawny, spawnz, Blocks.hive.get().blockID, 1, 0);
				}
				
				if (world.getBlockMetadata(spawnx, spawny, spawnz) == 2)
				{
				    world.setBlock(spawnx, spawny, spawnz, Blocks.hive.get().blockID, 3, 0);
				}
			}
		}
	}
	
	public void spawnEmptyHoneycombs(World world, Random rand, int x, int y, int z)
	{
		for (int spawn = 0; spawn < 50; spawn++)
		{
			int spawnx = (x - 8) + rand.nextInt(16);
			int spawny = y - rand.nextInt(12);
			int spawnz = (z - 8) + rand.nextInt(16);
			
			if (world.getBlockId(spawnx, spawny, spawnz) == Blocks.hive.get().blockID)
			{
				if (world.getBlockMetadata(spawnx, spawny, spawnz) == 0)
				{
				    world.setBlock(spawnx, spawny, spawnz, Blocks.hive.get().blockID, 4, 0);
				}
			}
		}
	}
}
