package biomesoplenty.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import biomesoplenty.BiomesOPlenty;

public class BlockBOPGlass extends Block
{
	private static final String[] glassTypes = new String[] {"celestiallens", "sacrificialFocus"};
	private Icon[] textures;

	public BlockBOPGlass(int blockID)
	{
		super(blockID, Material.glass);
		this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);

		this.blockHardness = 0.37F;
	}

	@Override
	public void registerIcons(IconRegister iconRegister)
	{
		textures = new Icon[glassTypes.length];

		for (int i = 0; i < glassTypes.length; ++i) {
			textures[i] = iconRegister.registerIcon("BiomesOPlenty:"+glassTypes[i]);
		}
	}

	@Override
	public Icon getIcon(int side, int meta)
	{
		if (meta < 0 || meta >= textures.length) {
			meta = 0;
		}

		return textures[meta];
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void getSubBlocks(int blockID, CreativeTabs creativeTabs, List list) {
		for (int i = 0; i < glassTypes.length; ++i) {
			list.add(new ItemStack(blockID, 1, i));
		}
	}

	@Override
	public int damageDropped(int meta)
	{
		return meta;
	}
}
