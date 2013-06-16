package biomesoplenty.items;

import java.util.List;

import net.minecraft.block.BlockCloth;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import biomesoplenty.BiomesOPlenty;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBOP extends Item
{
	private static String[] items = {"mudbrick", "ash", "amethyst", "poison", "crystalshard", "bluedye", "browndye", "greendye", "whitedye", "blackdye", "ruby", "peridot", "topaz", "tanzanite", "apatite", "sapphire", "ghastlysoul"};
	@SideOnly(Side.CLIENT)
	private Icon[] textures;

	public ItemBOP(int id)
	{
		super(id);
		setMaxDamage(0);
		setHasSubtypes(true);
		setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}
	
	@Override
	public boolean itemInteractionForEntity(ItemStack par1ItemStack, EntityLiving par2EntityLiving)
	{
		int itemDamage = par1ItemStack.getItemDamage(); 

		if (itemDamage == 5 || itemDamage == 6 || itemDamage == 7 || itemDamage == 8 || itemDamage == 9)    
		{
			int dyeMeta = convertToDyeMeta(itemDamage);      
			int i = BlockCloth.getBlockFromDye(dyeMeta);

			if (par2EntityLiving instanceof EntityWolf)
			{
				EntityWolf entitywolf = (EntityWolf)par2EntityLiving;

				if (i != entitywolf.getCollarColor())
				{
					entitywolf.setCollarColor(i)
					;
					if (!Minecraft.getMinecraft().thePlayer.capabilities.isCreativeMode)
					{
						--par1ItemStack.stackSize;
					}

					return true;
				}
			}
			else if (par2EntityLiving instanceof EntitySheep)
			{
				EntitySheep entitysheep = (EntitySheep)par2EntityLiving;

				if (!entitysheep.getSheared() && entitysheep.getFleeceColor() != i)
				{
					entitysheep.setFleeceColor(i);
					
					if (!Minecraft.getMinecraft().thePlayer.capabilities.isCreativeMode)
					{
						--par1ItemStack.stackSize;
					}
				}

				return true;
			}
			else
			{
				return false;
			}
		}

		return false;
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

	@Override
	public void registerIcons(IconRegister iconRegister)
	{
		textures = new Icon[items.length];

		for (int i = 0; i < items.length; ++i) {
			textures[i] = iconRegister.registerIcon("BiomesOPlenty:"+items[i]);
		}
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack)
	{
		int meta = itemStack.getItemDamage();
		if (meta < 0 || meta >= items.length) {
			meta = 0;
		}

		return super.getUnlocalizedName() + "." + items[meta];
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
		for(int meta = 0; meta < items.length; ++meta) {
			subTypes.add(new ItemStack(itemId, 1, meta));
		}
	}
}
