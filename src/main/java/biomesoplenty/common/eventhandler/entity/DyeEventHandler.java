package biomesoplenty.common.eventhandler.entity;

import net.minecraft.block.BlockColored;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import biomesoplenty.api.content.BOPCItems;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class DyeEventHandler 
{
	@SubscribeEvent
	public void entityInteract(EntityInteractEvent event)
	{
		ItemStack itemstack = event.entityPlayer.getCurrentEquippedItem();
		Entity entity = event.target;
		EntityPlayer player = event.entityPlayer;

		if (itemstack != null)
		{
			int itemDamage = itemstack.getItemDamage();

			if (itemstack.getItem() == BOPCItems.misc && (itemDamage == 5 || itemDamage == 6 || itemDamage == 7 || itemDamage == 8 || itemDamage == 9))    
			{
				int dyeMeta = convertToDyeMeta(itemDamage);
				int i = BlockColored.func_150032_b(dyeMeta);

				if (entity instanceof EntityWolf)
				{
					EntityWolf entitywolf = (EntityWolf)entity;

					if (i != entitywolf.getCollarColor())
					{
						entitywolf.setCollarColor(i)
						;
						if (!event.entityPlayer.capabilities.isCreativeMode)
						{
							--itemstack.stackSize;
						}

						event.setResult(Result.ALLOW);
					}
				}
				else if (entity instanceof EntitySheep)
				{
					EntitySheep entitysheep = (EntitySheep)entity;

					if (!entitysheep.getSheared() && entitysheep.getFleeceColor() != i)
					{
						entitysheep.setFleeceColor(i);

						if (!event.entityPlayer.capabilities.isCreativeMode)
						{
							--itemstack.stackSize;
						}
					}

					event.setResult(Result.ALLOW);
				}
			}
		}
	}

	private int convertToDyeMeta(int meta)
	{
		switch (meta)
		{
		case 5:
			return 4;

		case 6:
			return 3;

		case 7:
			return 2;

		case 8:
			return 15;

		case 9:
			return 0;

		default:
			return 0;
		}
	}
}
