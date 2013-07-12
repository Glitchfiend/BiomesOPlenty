package biomesoplenty.handlers;

import biomesoplenty.configuration.BOPItems;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;

public class AxeChopHandler 
{
	@ForgeSubscribe
	public void chopTree(PlayerInteractEvent event)
	{
		ItemStack itemstack = event.entityPlayer.getCurrentEquippedItem();
		EntityPlayer player = event.entityPlayer;
		World world = player.worldObj;

		if (event.action == Action.RIGHT_CLICK_BLOCK)
		{
			if (itemstack != null)
			{
				Item item = Item.itemsList[itemstack.itemID];

				if (item instanceof ItemAxe)
				{						
					if (Block.blocksList[world.getBlockId(event.x, event.y, event.z)].isWood(world, event.x, event.y, event.z))
					{
						EnumToolMaterial toolMaterial = EnumToolMaterial.valueOf(((ItemAxe)item).getToolMaterialName());

						player.swingItem();
						player.playSound("dig.wood", 1.0F, 0.25F);

						if (toolMaterial != EnumToolMaterial.WOOD && toolMaterial != EnumToolMaterial.STONE && toolMaterial != BOPItems.EnumToolMaterialMud)
						{
							int firstBlockId = world.getBlockId(event.x, event.y, event.z);
							int logNo = 0;
							
							itemstack.damageItem(4, player);
							
							for (int l = 0; world.getBlockId(event.x, event.y + l, event.z) == firstBlockId; l++)
							{
								if (!world.isRemote)
								{
									logNo += l * 2;
								}
							}

							if (world.rand.nextInt(25 + logNo) == 0)
							{								
								for (int i = 0; world.getBlockId(event.x, event.y + i, event.z) == firstBlockId; i++)
								{
									if (!world.isRemote)
									{
										itemstack.damageItem(2, player);
										world.destroyBlock(event.x, event.y + i, event.z, true);
									}
								}
							}
						}
						else
						{
							itemstack.damageItem(7, player);
						}
					}
				}
			}
		}
	}
}
