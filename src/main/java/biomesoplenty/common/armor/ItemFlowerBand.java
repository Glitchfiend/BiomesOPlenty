package biomesoplenty.common.armor;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemFlowerBand extends ItemArmor
{
	private static final String[] flowerBandTypes = new String[] {"dullflowerband", "plainflowerband", "lushflowerband", "exoticflowerband"};
	@SideOnly(Side.CLIENT)
	private IIcon[] textures;

	public ItemFlowerBand(ArmorMaterial par2EnumArmorMaterial, int renderIndex, int armorType) 
	{
		super(par2EnumArmorMaterial, renderIndex, armorType);
		
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		this.maxStackSize = 8;
	}

	@Override
    //TODO: public void getSubItems(Item item, CreativeTabs creativeTabs, List list)
    public void func_150895_a(Item item, CreativeTabs creativeTabs, List list)
	{
		for (int i = 0; i < flowerBandTypes.length; ++i)
		{
			list.add(new ItemStack(item, 1, i));
		}
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack)
	{
		return super.getUnlocalizedName() + "." + (new StringBuilder()).append(flowerBandTypes[itemStack.getItemDamage()]).toString();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister)
	{
		textures = new IIcon[flowerBandTypes.length];

		for (int i = 0; i < flowerBandTypes.length; ++i) 
		{
			textures[i] = iconRegister.registerIcon("biomesoplenty:" + flowerBandTypes[i]);
		}
	}

	@Override
	public IIcon getIconFromDamage(int meta)
	{
		return textures[meta];
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) 
	{
		if (stack.getItemDamage() == 0)
			return "biomesoplenty:textures/armor/dullflowerband.png";

		if (stack.getItemDamage() == 1)
			return "biomesoplenty:textures/armor/plainflowerband.png";

		if (stack.getItemDamage() == 2)
			return "biomesoplenty:textures/armor/lushflowerband.png";

		if (stack.getItemDamage() == 3)
			return "biomesoplenty:textures/armor/exoticflowerband.png";

		return null;
	}
}
