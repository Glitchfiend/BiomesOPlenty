package biomesoplenty.common.eventhandler.misc;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import biomesoplenty.api.content.BOPCFluids;
import biomesoplenty.api.content.BOPCItems;
import biomesoplenty.common.items.ItemBOPBucket;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class BucketEventHandler 
{
	@SubscribeEvent
	public void onBucketFill(FillBucketEvent event)
	{
		ItemBOPBucket bopBucket = (ItemBOPBucket)BOPCItems.bopBucket;
		ItemStack bopBucketStack = new ItemStack(bopBucket);

		World world = event.world;
		
		int x = event.target.blockX;
		int y = event.target.blockY;
		int z = event.target.blockZ;

		Fluid fluid = FluidRegistry.lookupFluidForBlock(world.getBlock(x, y, z));

		if (fluid != null)
		{
			if ((fluid == BOPCFluids.poison && world.getBlockMetadata(x, y, z) == 0) || (fluid == BOPCFluids.blood && world.getBlockMetadata(x, y, z) == 0) || (fluid == BOPCFluids.honey && world.getBlockMetadata(x, y, z) == 7))
			{
				bopBucket.fill(bopBucketStack, new FluidStack(fluid, FluidContainerRegistry.BUCKET_VOLUME), true);

				world.setBlockToAir(x, y, z);

				event.result = bopBucketStack;
				event.setResult(Result.ALLOW);
			}
		}
	}
}
