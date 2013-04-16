package biomesoplenty.items;

import biomesoplenty.configuration.BOPItems;
import biomesoplenty.items.projectiles.EntityMudball;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemBOP extends Item
{
	public int boptextureid = 0;

	public ItemBOP(int id, int texture)
	{
		super(id);
		boptextureid = texture;
	}

	public void registerIcons(IconRegister iconRegister)
	{
		if(boptextureid==0){ itemIcon = iconRegister.registerIcon("BiomesOPlenty:mudball"); }
		else if(boptextureid==1){ itemIcon = iconRegister.registerIcon("BiomesOPlenty:mudbrick"); }
		else if(boptextureid==2){ itemIcon = iconRegister.registerIcon("BiomesOPlenty:moss"); }
		else if(boptextureid==3){ itemIcon = iconRegister.registerIcon("BiomesOPlenty:ash"); }
		else if(boptextureid==4){ itemIcon = iconRegister.registerIcon("BiomesOPlenty:amethyst"); }
		else if(boptextureid==5){ itemIcon = iconRegister.registerIcon("BiomesOPlenty:staffhandle"); }
		else if(boptextureid==6){ itemIcon = iconRegister.registerIcon("BiomesOPlenty:staffpole"); }
		else if(boptextureid==7){ itemIcon = iconRegister.registerIcon("BiomesOPlenty:stafftopper"); }
		else { itemIcon = iconRegister.registerIcon("BiomesOPlenty:mudball"); }
	}

	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		if (par1ItemStack.isItemEqual(new ItemStack(BOPItems.mudBall)))
		{
			if (!par3EntityPlayer.capabilities.isCreativeMode)
			{
				--par1ItemStack.stackSize;
			}

			par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

			if (!par2World.isRemote)
			{
				par2World.spawnEntityInWorld(new EntityMudball(par2World, par3EntityPlayer));
			}
		}
		return par1ItemStack;
	} 
}
