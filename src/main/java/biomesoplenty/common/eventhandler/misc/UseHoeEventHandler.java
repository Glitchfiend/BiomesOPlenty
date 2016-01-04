package biomesoplenty.common.eventhandler.misc;

import biomesoplenty.api.content.BOPCBlocks;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraftforge.event.entity.player.UseHoeEvent;

public class UseHoeEventHandler
{

	@SubscribeEvent
	public void useHoe(UseHoeEvent event)
	{
		int meta = event.world.getBlockMetadata(event.x, event.y, event.z);
		Block block = event.world.getBlock(event.x, event.y, event.z);
		boolean result = false;

		if (block == BOPCBlocks.newBopDirt || block == BOPCBlocks.newBopGrass)
		{
			result = true;
			if (block != BOPCBlocks.newBopGrass)
			{
				if ((meta & 1) == 1)
				{
					event.world.setBlockMetadataWithNotify(event.x, event.y, event.z, meta - 1, 2);
				}
				else
				{
					event.world.setBlock(event.x, event.y, event.z, BOPCBlocks.newBopFarmland, meta, 2);
				}
			}
			else
			{
				event.world.setBlock(event.x, event.y, event.z, BOPCBlocks.newBopFarmland, meta * 2, 2);
			}
		}

		if (result)
		{
			if (!event.entityPlayer.capabilities.isCreativeMode)
			{
				event.current.damageItem(1, event.entityLiving);
			}
			event.world.playSoundEffect(event.x + 0.5F, event.y + 0.5F, event.z + 0.5F, Block.soundTypeGravel.getStepResourcePath(), 1.0F, 0.8F);
			event.entityPlayer.swingItem();
		}
	}
}
