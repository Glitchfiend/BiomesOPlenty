package biomesoplenty.armor;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ArmorFlowerBand extends ItemArmor
{
	private static final String[] flowerBandTypes = new String[] {"dullflowerband", "plainflowerband", "lushflowerband", "exoticflowerband"};
	@SideOnly(Side.CLIENT)
	private Icon[] textures;

	public ArmorFlowerBand(int par1, EnumArmorMaterial par2EnumArmorMaterial, int par3, int par4) {
		super(par1, par2EnumArmorMaterial, par3, par4);
		setHasSubtypes(true);
		setMaxDamage(0);
		maxStackSize = 8;
	}

	@Override
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
	{
		for (int i = 0; i < flowerBandTypes.length; ++i)
		{
			par3List.add(new ItemStack(par1, 1, i));
		}
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack)
	{
		return super.getUnlocalizedName() + "." + (new StringBuilder()).append(flowerBandTypes[itemStack.getItemDamage()]).toString();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister)
	{
		textures = new Icon[flowerBandTypes.length];

		for (int i = 0; i < flowerBandTypes.length; ++i) {
			textures[i] = iconRegister.registerIcon("biomesoplenty:" + flowerBandTypes[i]);
		}
	}

	@Override
	public Icon getIconFromDamage(int meta)
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
