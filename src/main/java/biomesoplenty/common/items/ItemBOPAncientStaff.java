package biomesoplenty.common.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.common.configuration.BOPConfigurationIDs;
import biomesoplenty.common.world.features.WorldGenPromisedLandPortal;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBOPAncientStaff extends Item
{
	private static String[] parts = {"ancientstaff", "staffhandle", "staffpole", "stafftopper", "ancientstaffbroken"};
	@SideOnly(Side.CLIENT)
	private IIcon[] textures;

	public ItemBOPAncientStaff()
	{
		this.maxStackSize = 1;
		
		this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}
	
	@Override
    public boolean isFull3D()
    {
		return true;
    }

	@Override
	public boolean hasEffect(ItemStack itemStack, int pass)
	{
		if (itemStack.getItemDamage() == 0)
			return true;
		else
			return false;
	}

	@Override
	public void registerIcons(IIconRegister iconRegister)
	{
		textures = new IIcon[parts.length];

		for (int i = 0; i < parts.length; ++i) 
		{
			textures[i] = iconRegister.registerIcon("biomesoplenty:" + parts[i]);
		}
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack)
	{
		int meta = itemStack.getItemDamage();
		if (meta < 0 || meta >= parts.length) {
			meta = 0;
		}

		return super.getUnlocalizedName() + "." + parts[meta];
	}

	@Override
	public IIcon getIconFromDamage(int meta)
	{
		if (meta < 0 || meta >= textures.length) 
		{
			meta = 0;
		}

		return textures[meta];
	}

	@Override
    //TODO: public void getSubItems(Item item, CreativeTabs creativeTabs, List list)
    public void getSubItems(Item item, CreativeTabs creativeTabs, List list)
	{
		for (int meta = 0; meta < parts.length; ++meta) 
		{
			list.add(new ItemStack(item, 1, meta));
		}
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World world, EntityPlayer player)
	{
		if (player.dimension == 0 || player.dimension == BOPConfigurationIDs.promisedLandDimID)
		{
			int i = MathHelper.floor_double(player.posX - 8);
			int j = 145;
			int k = MathHelper.floor_double(player.posZ - 8);

			boolean isAir = true;

			for (int iy = -1; iy < 4; iy++)
			{
				for (int ix = -2; ix < 3; ix++) {
					for (int iz = -2; iz < 3; iz++)
						// is air block
						if (!world.getBlock(i + ix, j + iy, k + iz).isAir(world, i + ix, j + iy, k + iz))
						{
							isAir = false;
						}
				}
			}

			if (par1ItemStack.getItemDamage() == 0)
			{
				if (isAir)
				{

					if (!player.worldObj.isRemote)
					{
						if (player.dimension == 0)
						{
							//send player chat
							player.addChatMessage(new ChatComponentTranslation("phrase.bop.promisedPortalOverworld"));
						}
						else
						{
							//send player chat
							player.addChatMessage(new ChatComponentTranslation("phrase.bop.promisedPortalOther"));
						}
					}



					boolean flag;
					new WorldGenPromisedLandPortal().generate(world, world.rand, (int)player.posX, 130, (int)player.posZ);

					par1ItemStack.setItemDamage(4);
				}
			}
		}

		return par1ItemStack;
	}
}
