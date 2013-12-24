package biomesoplenty.common.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.BOPItemHelper;
import biomesoplenty.common.entities.projectiles.EntityDart;
import biomesoplenty.common.entities.projectiles.EntityDart.DartType;

public class ItemDartBlower extends Item
{
	public ItemDartBlower()
	{
		super();
		
		this.maxStackSize = 1;
		this.setMaxDamage(63);
		
		this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	public void registerIcons(IIconRegister iconRegister)
	{
		itemIcon = iconRegister.registerIcon("biomesoplenty:dartblower");
	}
	
	@Override
    public boolean isFull3D()
    {
		return true;
    }

	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer par3EntityPlayer)
	{
		boolean flag = par3EntityPlayer.capabilities.isCreativeMode;

		//TODO:								   hasItem()
		if (flag || par3EntityPlayer.inventory.func_146028_b(BOPItemHelper.get("dart")))
		{
			EntityDart entityDart = new EntityDart(world, par3EntityPlayer, 1.0F);

			itemStack.damageItem(1, par3EntityPlayer);
			world.playSoundAtEntity(par3EntityPlayer, "random.bow", 1.0F, 2.0F / (1.0F * 0.4F + 1.2F) + 1.0F * 0.5F);

			int slot = -1;
			if (par3EntityPlayer.inventory.hasItemStack(new ItemStack(BOPItemHelper.get("dart"), 1, 1)))
			{
				entityDart.setDartType(DartType.POISON);

				for (int k = 0; k < par3EntityPlayer.inventory.mainInventory.length; ++k)
				{
					if (par3EntityPlayer.inventory.mainInventory[k] != null && par3EntityPlayer.inventory.mainInventory[k].getItem() == BOPItemHelper.get("dart") && par3EntityPlayer.inventory.mainInventory[k].getItemDamage() == 1)
					{
						slot = k;
						break;
					}
				}
			}
			else if (par3EntityPlayer.inventory.hasItemStack(new ItemStack(BOPItemHelper.get("dart"), 1, 0)))
			{
				entityDart.setDartType(DartType.NORMAL);

				for (int k = 0; k < par3EntityPlayer.inventory.mainInventory.length; ++k)
				{
					if (par3EntityPlayer.inventory.mainInventory[k] != null && par3EntityPlayer.inventory.mainInventory[k].getItem() == BOPItemHelper.get("dart") && par3EntityPlayer.inventory.mainInventory[k].getItemDamage() == 0)
					{
						slot = k;
						break;
					}
				}
			}

			if (!world.isRemote) 
			{
				world.spawnEntityInWorld(entityDart);
			}

			if (!flag && slot >= 0) {
				par3EntityPlayer.inventory.decrStackSize(slot, 1);
			}
		}

		return itemStack;
	}
}
