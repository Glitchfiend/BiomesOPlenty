package biomesoplenty.eventhandlers;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import biomesoplenty.api.Fluids;

public class FluidEventHandler
{
	@ForgeSubscribe
	public void onBucketFill(FillBucketEvent event)
	{
		World world = event.world;
		MovingObjectPosition pos = event.target;

		int blockID = world.getBlockId(pos.blockX, pos.blockY, pos.blockZ);
		int meta = world.getBlockMetadata(pos.blockX, pos.blockY, pos.blockZ);

		if ((blockID == Fluids.springWater.get().blockID) && meta == 0)
		{
			ItemStack filledContainer = FluidContainerRegistry.fillFluidContainer(new FluidStack(FluidRegistry.WATER, FluidContainerRegistry.BUCKET_VOLUME), event.current);

			if (filledContainer != null)
			{
				world.setBlock(pos.blockX, pos.blockY, pos.blockZ, 0);

				event.result = filledContainer;
				event.setResult(Result.ALLOW);
			}
			else
			{
				event.setResult(Result.DENY);
			}
		}
		else if ((blockID == Fluids.liquidPoison.get().blockID) && meta == 0)
		{
			ItemStack filledContainer = FluidContainerRegistry.fillFluidContainer(new FluidStack(Fluids.liquidPoisonFluid.get(), FluidContainerRegistry.BUCKET_VOLUME), event.current);

			if (filledContainer != null)
			{
				world.setBlock(pos.blockX, pos.blockY, pos.blockZ, 0);

				event.result = filledContainer;
				event.setResult(Result.ALLOW);
			}
			else
			{
				event.setResult(Result.DENY);
			}
		}
		else if ((blockID == Fluids.honey.get().blockID) && meta == 7)
		{
			ItemStack filledContainer = FluidContainerRegistry.fillFluidContainer(new FluidStack(Fluids.honeyFluid.get(), FluidContainerRegistry.BUCKET_VOLUME), event.current);

			if (filledContainer != null)
			{
				world.setBlock(pos.blockX, pos.blockY, pos.blockZ, 0);

				event.result = filledContainer;
				event.setResult(Result.ALLOW);
			}
			else
			{
				event.setResult(Result.DENY);
			}
		}
	}

	public ItemStack fillCustomBucket(World world, MovingObjectPosition pos)
	{
		int blockID = world.getBlockId(pos.blockX, pos.blockY, pos.blockZ);
		int meta = world.getBlockMetadata(pos.blockX, pos.blockY, pos.blockZ);

		if ((blockID == Fluids.springWater.get().blockID) && meta == 0)
		{
			world.setBlock(pos.blockX, pos.blockY, pos.blockZ, 0);

			return new ItemStack(Item.bucketWater);
		}

		if ((blockID == Fluids.liquidPoison.get().blockID) && meta == 0)
		{
			world.setBlock(pos.blockX, pos.blockY, pos.blockZ, 0);

			return new ItemStack(Fluids.bopBucket.get(), 1, 1);
		} 
		
		if ((blockID == Fluids.honey.get().blockID) && meta == 7)
		{
			world.setBlock(pos.blockX, pos.blockY, pos.blockZ, 0);
			
			return new ItemStack(Fluids.bopBucket.get(), 1, 3);
		} 
		else
		{
			return null;
		}
	}
}
