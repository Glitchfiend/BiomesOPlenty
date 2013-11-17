package biomesoplenty.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingData;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.Items;
import biomesoplenty.configuration.configfile.BOPConfigurationIDs;
import biomesoplenty.entities.EntityPixie;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemJarFilled extends Item
{
	private static String[] jars = {"jarhoney", "jarpoison", "jarpixie"};
	@SideOnly(Side.CLIENT)
	private Icon[] textures;

	public ItemJarFilled(int id)
	{
		super(id);
		setMaxDamage(0);
		setMaxStackSize(1);
		setHasSubtypes(true);
		setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	public void registerIcons(IconRegister iconRegister)
	{
		textures = new Icon[jars.length];

		for (int i = 0; i < jars.length; ++i) {
			textures[i] = iconRegister.registerIcon("biomesoplenty:"+jars[i]);
		}
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack)
	{
		int meta = itemStack.getItemDamage();
		if (meta < 0 || meta >= jars.length) {
			meta = 0;
		}

		return super.getUnlocalizedName() + "." + jars[meta];
	}

	@Override
	public Icon getIconFromDamage(int meta)
	{
		if (meta < 0 || meta >= textures.length) {
			meta = 0;
		}

		return textures[meta];
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void getSubItems(int itemId, CreativeTabs creativeTab, List subTypes)
	{
		for(int meta = 0; meta < jars.length; ++meta) {
			subTypes.add(new ItemStack(itemId, 1, meta));
		}
	}
	
	/**
	 * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
	 */
	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		if (par1ItemStack.getItemDamage() == 2)
		{
			if (par3EntityPlayer.ridingEntity != null)
				return par1ItemStack;
			else
			{
				if (par3EntityPlayer.dimension == 0 || par3EntityPlayer.dimension == BOPConfigurationIDs.promisedLandDimID)
				{
					--par1ItemStack.stackSize;
					
	                if (par1ItemStack.stackSize <= 0)
	                {
	                    return new ItemStack(Items.jarEmpty.get(), 1, 0);
	                }
	
	                if (!par3EntityPlayer.inventory.addItemStackToInventory(new ItemStack(Items.jarEmpty.get(), 1, 0)))
	                {
	                    par3EntityPlayer.dropPlayerItem(new ItemStack(Items.jarEmpty.get(), 1, 0));
	                }

	    			EntityPixie pixie = new EntityPixie(par2World);
		            pixie.setLocationAndAngles((double)par3EntityPlayer.posX, (double)par3EntityPlayer.posY + 1.0F, (double)par3EntityPlayer.posZ, 0.0F, 0.0F);
		            pixie.onSpawnWithEgg((EntityLivingData)null);
		            par2World.spawnEntityInWorld(pixie);
		            pixie.playLivingSound();
				}
				else
				{
					if (!par3EntityPlayer.worldObj.isRemote)
					{
						par3EntityPlayer.addChatMessage("\u00a75Pixies cannot survive in this environment!");
					}
				}
			}
		}
		
		return par1ItemStack;
	}
}
