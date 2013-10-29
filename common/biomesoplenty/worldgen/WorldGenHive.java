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
	                chance = 0.5F;
	                break;
	        }
	        
	        int honeychance = rand.nextInt(2);
	        
	        //Top
	        generateHiveCubeSmall(world, x, y + cubeno, z, (baseHeight - 8) + (cubeno * 2), (baseWidth - 1) + cubeno, cubeno, chance);
	        
	        //Middle
	        generateHiveCube(world, x, (y - 2) + cubeno, z, baseHeight + (cubeno * 2), baseWidth + cubeno, cubeno, chance, honeychance);
	        
	        //Bottom
	        generateHiveCubeSmall(world, x, (y - (baseHeight + 4)) + cubeno, z, (baseHeight - 8) + (cubeno * 2), (baseWidth - 1) + cubeno, cubeno, chance);
	        
	        //Bottom 2
	        generateHiveCubeSmall(world, x, (y - (baseHeight + 5)) + cubeno, z, (baseHeight - 7) + (cubeno * 2), (baseWidth - 2) + cubeno, cubeno, chance);
	        
	        //Bottom 3
	        generateHiveCubeSmall(world, x, (y - (baseHeight + 7)) + cubeno, z, (baseHeight - 7) + (cubeno * 2), (baseWidth - 4) + cubeno, cubeno, chance);
	        
	        spawnWasps(world, rand, x, y, z, 15);
	    }
	    
	    return true;
	}
	
	public void generateHiveCube(World world, int origx, int origy, int origz, int height, int width, int cubeno, float chance, int honeychance)
	{
        for (int hLayer = 0; hLayer < height; hLayer++)
        {     
            for (int i = -width; i < width; i++)
            {
                for (int j = -width; j < width; j++)
                {
                    if ((hLayer == 0 || hLayer == (height - 1)) && (world.rand.nextFloat() <= chance)) world.setBlock(origx + i, origy - hLayer, origz + j, Blocks.hive.get().blockID); 
                    else if ((i == -width || i == (width - 1) || j == -width || j == (width - 1)) && (world.rand.nextFloat() <= chance)) world.setBlock(origx + i, origy - hLayer, origz + j, Blocks.hive.get().blockID);
                    
                    if (hLayer > (height / 2))
                    {
                        if (honeychance == 0)
                        {
                            if (cubeno < 2 && world.getBlockId(origx + i, origy - hLayer, origz + j) != Blocks.hive.get().blockID) world.setBlock(origx + i, origy - hLayer, origz + j, Fluids.honey.get().blockID);
                        }
                        else
                        {
                            if (cubeno < 2 && world.getBlockId(origx + i, origy - hLayer, origz + j) != Blocks.hive.get().blockID) world.setBlockToAir(origx + i, origy - hLayer, origz + j);
                        }
                    }
                    else
                    {
                        if (cubeno < 2 && world.getBlockId(origx + i, origy - hLayer, origz + j) != Blocks.hive.get().blockID) world.setBlockToAir(origx + i, origy - hLayer, origz + j);
                    }
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
	
	public void spawnWasps(World world, Random rand, int x, int y, int z, int amount)
	{
		for (int spawn = 0; spawn < amount; spawn++)
		{
			int spawnx = (x - 8) + rand.nextInt(16);
			int spawny = (y + 2) - rand.nextInt(16);
			int spawnz = (z - 8) + rand.nextInt(16);
			
			if (world.getBlockId(spawnx, spawny, spawnz) == Blocks.hive.get().blockID)
			{
			    world.setBlock(spawnx, spawny, spawnz, Block.mobSpawner.blockID);

			    TileEntityMobSpawner tileentitymobspawner = (TileEntityMobSpawner)world.getBlockTileEntity(spawnx, spawny, spawnz);

			    if (tileentitymobspawner != null)
			    {
			        tileentitymobspawner.getSpawnerLogic().setMobID("BiomesOPlenty.Wasp");
			    }
			}
		}
	}
}
