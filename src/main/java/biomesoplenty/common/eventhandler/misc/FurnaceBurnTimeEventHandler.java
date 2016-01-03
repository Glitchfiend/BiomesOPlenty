package biomesoplenty.common.eventhandler.misc;


import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.FuelBurnTimeEvent;
import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.api.content.BOPCItems;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

@SuppressWarnings("deprecation")
public class FurnaceBurnTimeEventHandler {

	@SubscribeEvent
	public void onFurnaceBurnTime(FuelBurnTimeEvent event) {
		ItemStack fuel = event.fuel;
		if (fuel != null) {
			Block block = Block.getBlockFromItem(fuel.getItem());

			if (block == BOPCBlocks.saplings || block == BOPCBlocks.colorizedSaplings) {
				event.burnTime = 100;
				event.setResult(Result.ALLOW);
			}

			if (block == BOPCBlocks.woodenSingleSlab1 || block == BOPCBlocks.woodenSingleSlab2) {
				event.burnTime = 150;
				event.setResult(Result.ALLOW);
			}

			if (fuel.isItemEqual(new ItemStack(BOPCItems.misc, 0, 1))) {
				event.burnTime = 400;
				event.setResult(Result.ALLOW);
			}

			if (fuel.isItemEqual(new ItemStack(BOPCBlocks.woodenSingleSlab2, 0, 3))) {
				event.burnTime = 0;
				event.setResult(Result.DENY);
			}

			if (fuel.isItemEqual(new ItemStack(BOPCBlocks.woodenDoubleSlab2, 0, 3))) {
				event.burnTime = 0;
				event.setResult(Result.DENY);
			}

			if (fuel.isItemEqual(new ItemStack(BOPCBlocks.logs4, 0, 1))) {
				event.burnTime = 0;
				event.setResult(Result.DENY);
			}

			if (fuel.isItemEqual(new ItemStack(BOPCBlocks.planks, 0, 12))) {
				event.burnTime = 0;
				event.setResult(Result.DENY);
			}

			if (fuel.isItemEqual(new ItemStack(BOPCBlocks.saplings, 0, 13))) {
				event.burnTime = 0;
				event.setResult(Result.DENY);
			}

			if (block == BOPCBlocks.hellBarkStairs) {
				event.burnTime = 0;
				event.setResult(Result.DENY);
			}
		}
	}
}
