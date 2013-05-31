package biomesoplenty.items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.Items;
import biomesoplenty.items.projectiles.EntityDart;
import biomesoplenty.items.projectiles.EntityDart.DartType;

public class ItemDartBlower extends Item
{
	public ItemDartBlower(int par1)
	{
		super(par1);
		maxStackSize = 1;
		this.setMaxDamage(63);
		setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
		setUnlocalizedName("dartblower");
	}

	@Override
	public void registerIcons(IconRegister iconRegister)
	{
		itemIcon = iconRegister.registerIcon("BiomesOPlenty:dartblower");
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		boolean flag = par3EntityPlayer.capabilities.isCreativeMode;

		if (flag || par3EntityPlayer.inventory.hasItem(Items.dart.get().itemID))
		{
			//EntityArrow entitydart = new EntityArrow(par2World, par3EntityPlayer, 2.0F);
			EntityDart entityDart = new EntityDart(par2World, par3EntityPlayer, 1.25F);

			itemStack.damageItem(1, par3EntityPlayer);
			par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 1.0F, 2.0F / (1.0F * 0.4F + 1.2F) + 1.0F * 0.5F);

			int slot = -1;
			if (par3EntityPlayer.inventory.hasItemStack(new ItemStack(Items.dart.get().itemID, 1, 1)))
			{
				entityDart.setDartType(DartType.POISON);

				for (int k = 0; k < par3EntityPlayer.inventory.mainInventory.length; ++k)
					if (par3EntityPlayer.inventory.mainInventory[k] != null && par3EntityPlayer.inventory.mainInventory[k].itemID == Items.dart.get().itemID && par3EntityPlayer.inventory.mainInventory[k].getItemDamage() == 1)
					{
						slot = k;
						break;
					}
			}
			else if (par3EntityPlayer.inventory.hasItemStack(new ItemStack(Items.dart.get().itemID, 1, 0)))
			{
				entityDart.setDartType(DartType.NORMAL);

				for (int k = 0; k < par3EntityPlayer.inventory.mainInventory.length; ++k)
					if (par3EntityPlayer.inventory.mainInventory[k] != null && par3EntityPlayer.inventory.mainInventory[k].itemID == Items.dart.get().itemID && par3EntityPlayer.inventory.mainInventory[k].getItemDamage() == 0)
					{
						slot = k;
						break;
					}
			}

			if (!par2World.isRemote) {
				par2World.spawnEntityInWorld(entityDart);
			}

			if (!flag && slot >= 0) {
				par3EntityPlayer.inventory.decrStackSize(slot, 1);
			}
		}

		return itemStack;
	}
}
