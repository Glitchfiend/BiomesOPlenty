package biomesoplenty.handlers;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import biomesoplenty.api.Fluids;

public class FluidEventHandler
{
	@ForgeSubscribe
	public void onBucketFill(FillBucketEvent event)
	{
		ItemStack result = fillCustomBucket(event.world, event.target);

		if (result == null)
			return;

		event.result = result;
		event.setResult(Result.ALLOW);
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
		else
		{
			return null;
		}
	}
}
