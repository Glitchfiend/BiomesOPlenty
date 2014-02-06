package biomesoplenty.common.eventhandler.misc;

import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidBlock;
import biomesoplenty.api.BOPItemHelper;
import biomesoplenty.common.items.ItemBOPBucket;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class BucketEventHandler 
{
	@SubscribeEvent
	public void onBucketFill(FillBucketEvent event)
	{
		ItemBOPBucket bopBucket = (ItemBOPBucket)BOPItemHelper.get("bopBucket");
		ItemStack bopBucketStack = new ItemStack(bopBucket);

		World world = event.world;
		
		int x = event.target.blockX;
		int y = event.target.blockY;
		int z = event.target.blockZ;

		//TODO:				getBlock()
		Block block = world.getBlock(x, y, z);

		Fluid fluidBlockFluid = FluidRegistry.lookupFluidForBlock(block);

		if (fluidBlockFluid != null)
		{
			String fluidName = fluidBlockFluid.getName();

			if ((fluidName.equals("poison") || fluidName.equals("honey")))
			{
				bopBucket.fill(bopBucketStack, new FluidStack(fluidBlockFluid, FluidContainerRegistry.BUCKET_VOLUME), true);

				world.setBlockToAir(x, y, z);

				event.result = bopBucketStack;
				event.setResult(Result.ALLOW);
			}
		}
	}
}
