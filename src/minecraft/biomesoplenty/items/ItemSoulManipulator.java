package biomesoplenty.items;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.Blocks;
import biomesoplenty.configuration.BOPConfiguration;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemSoulManipulator extends Item
{
	private static String[] manipulatorTypes = {"soulmanipulator_empty", "soulmanipulator_ghastlysoul", "soulmanipulator_villager"};
	@SideOnly(Side.CLIENT)
	private Icon[] textures;

	public ItemSoulManipulator(int par1)
	{
		super(par1);
		maxStackSize = 1;
		setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}
	
	@Override
    public boolean isFull3D()
    {
		return true;
    }

	@Override
	public void registerIcons(IconRegister iconRegister)
	{
		textures = new Icon[manipulatorTypes.length];

		for (int i = 0; i < manipulatorTypes.length; ++i) {
			textures[i] = iconRegister.registerIcon("BiomesOPlenty:"+manipulatorTypes[i]);
		}
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack)
	{
		int meta = itemStack.getItemDamage();
		if (meta < 0 || meta >= manipulatorTypes.length) {
			meta = 0;
		}

		return super.getUnlocalizedName() + "." + manipulatorTypes[meta];
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
		for(int meta = 0; meta < manipulatorTypes.length; ++meta) {
			subTypes.add(new ItemStack(itemId, 1, meta));
		}
	}
}