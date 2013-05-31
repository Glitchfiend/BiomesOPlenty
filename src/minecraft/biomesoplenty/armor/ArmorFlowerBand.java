package biomesoplenty.armor;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraftforge.common.IArmorTextureProvider;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ArmorFlowerBand extends ItemArmor implements IArmorTextureProvider
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
		return (new StringBuilder()).append(flowerBandTypes[itemStack.getItemDamage()]).toString();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister)
	{
		textures = new Icon[flowerBandTypes.length];

		for (int i = 0; i < flowerBandTypes.length; ++i) {
			textures[i] = iconRegister.registerIcon("BiomesOPlenty:" + flowerBandTypes[i]);
		}
	}

	@Override
	public Icon getIconFromDamage(int meta)
	{
		return textures[meta];
	}

	@Override
	public String getArmorTextureFile(ItemStack par1)
	{
		if (par1.getItemDamage() == 0)
			return "/mods/BiomesOPlenty/textures/armor/dullflowerband.png";

		if (par1.getItemDamage() == 1)
			return "/mods/BiomesOPlenty/textures/armor/plainflowerband.png";

		if (par1.getItemDamage() == 2)
			return "/mods/BiomesOPlenty/textures/armor/lushflowerband.png";

		if (par1.getItemDamage() == 3)
			return "/mods/BiomesOPlenty/textures/armor/exoticflowerband.png";

		return null;
	}
}
