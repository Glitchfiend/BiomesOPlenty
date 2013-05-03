package biomesoplenty.helpers;

import net.minecraft.block.Block;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.BonemealEvent;
import biomesoplenty.api.Blocks;
import biomesoplenty.blocks.BlockBOPColorizedSapling;
import biomesoplenty.blocks.BlockBOPSapling;
import biomesoplenty.worldgen.WorldGenGiantFlowerRed;
import biomesoplenty.worldgen.WorldGenGiantFlowerYellow;

public class BonemealUse
{
	
	@ForgeSubscribe
	public void onUseBonemeal(BonemealEvent event)
	{
		int meta = event.world.getBlockMetadata(event.X, event.Y, event.Z);
		
		if (event.ID == Blocks.saplings.get().blockID)
		{
			event.setResult(Result.ALLOW);
			
			if (!event.world.isRemote)
			{
			    
			    switch (meta) 
			    {
			        case 3: // Magic Sapling
			            if ((double)event.world.rand.nextFloat() < 0.10D)
		                    ((BlockBOPSapling)Blocks.saplings.get()).growTree(event.world, event.X, event.Y, event.Z, event.world.rand);
			            break;
			            
			        case 7: // Holy Sapling
			            if ((double)event.world.rand.nextFloat() < 0.15D)
		                    ((BlockBOPSapling)Blocks.saplings.get()).growTree(event.world, event.X, event.Y, event.Z, event.world.rand);
			            break;
			            
			        case 9: // Origin Sapling
			            ((BlockBOPSapling)Blocks.saplings.get()).growTree(event.world, event.X, event.Y, event.Z, event.world.rand);
			            break;
			            
			        default:
			            if ((double)event.world.rand.nextFloat() < 0.45D)
		                    ((BlockBOPSapling)Blocks.saplings.get()).growTree(event.world, event.X, event.Y, event.Z, event.world.rand);
			            break;
			    }
			    
				
			}
		}
		else if (event.ID == Blocks.colorizedSaplings.get().blockID)
		{
			event.setResult(Result.ALLOW);
			
			if (!event.world.isRemote)
			{
				if ((double)event.world.rand.nextFloat() < 0.45D)
				{
					((BlockBOPColorizedSapling)Blocks.colorizedSaplings.get()).growTree(event.world, event.X, event.Y, event.Z, event.world.rand);
				}
			}
		}
		else if (event.ID == Block.plantRed.blockID)
		{
			event.setResult(Result.ALLOW);
			
			if (!event.world.isRemote)
			{
				if ((double)event.world.rand.nextFloat() < 0.45D)
				{
		           WorldGenGiantFlowerRed worldgengiantflowerred = new WorldGenGiantFlowerRed();
		           worldgengiantflowerred.generate(event.world, event.world.rand, event.X, event.Y - 1, event.Z);
				}
			}
		}
		else if (event.ID == Block.plantYellow.blockID)
		{
			event.setResult(Result.ALLOW);
			
			if (!event.world.isRemote)
			{
				if ((double)event.world.rand.nextFloat() < 0.45D)
				{
		           WorldGenGiantFlowerYellow worldgengiantfloweryellow = new WorldGenGiantFlowerYellow();
		           worldgengiantfloweryellow.generate(event.world, event.world.rand, event.X, event.Y - 1, event.Z);
				}
			}
		}
		else if (event.ID == Blocks.holyGrass.get().blockID && event.world.getBlockMetadata(event.X, event.Y, event.Z) == 0)
		{
			int var13 = event.X;
			int var14 = event.Y + 1;
			int var15 = event.Z;

			for (int i1 = 0; i1 < 128; ++i1)
			{

				for (int i2 = 0; i2 < i1 / 16; ++i2)
				{
					var13 += event.world.rand.nextInt(3) - 1;
					var14 += (event.world.rand.nextInt(3) - 1) * event.world.rand.nextInt(3) / 2;
					var15 += event.world.rand.nextInt(3) - 1;
				}

				if (event.world.getBlockId(var13, var14, var15) == 0)
				{
					if (Blocks.plants.get().canBlockStay(event.world, var13, var14, var15))
					{
						event.setResult(Result.ALLOW);

						if (!event.world.isRemote)
						{
							event.world.setBlock(var13, var14, var15, Blocks.plants.get().blockID, 4, 0x02);
						}      
					}
				}
			}
		}
	}
}
