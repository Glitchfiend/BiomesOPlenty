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
							itemstack.damageItem(4, player);

							if (world.rand.nextInt(30) == 0)
							{
								int firstBlockId = world.getBlockId(event.x, event.y, event.z);
								
								for (int i = 0; world.getBlockId(event.x, event.y + i, event.z) == firstBlockId; i++)
								{
									if (!world.isRemote)
									{
										world.destroyBlock(event.x, event.y + i, event.z, true);
										System.out.println(i);
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
