package biomesoplenty.common.eventhandler.misc;

import biomesoplenty.api.content.BOPCBlocks;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraftforge.event.entity.player.UseHoeEvent;

public class UseHoeEventHandler {
	
	@SubscribeEvent
	public void useHoe(UseHoeEvent event) {
		int meta = event.world.getBlockMetadata(event.x, event.y, event.z);
		if(event.world.getBlock(event.x, event.y, event.z) == BOPCBlocks.newBopDirt) {
			if((meta & 1) == 1) {
				event.world.setBlockMetadataWithNotify(event.x, event.y, event.z, meta - 1, 2);
			}
		}
	}
}
