package biomesoplenty.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import biomesoplenty.BiomesOPlenty;

public class BlockBOPPlank extends Block
{
	private static final String[] woodTypes = new String[] {"plank_acacia", "plank_cherry", "plank_dark", "plank_fir", "plank_holy", "plank_magic", "plank_mangrove", "plank_palm", "plank_redwood", "plank_willow", "bamboothatching", "plank_pine", "plank_hell_bark", "plank_jacaranda"};
	private Icon[] textures;

	public BlockBOPPlank(int blockID)
	{
		super(blockID, Material.wood);
		setBurnProperties(this.blockID, 5, 20);
		setHardness(2.0F);
		this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	public void registerIcons(IconRegister iconRegister)
	{
		textures = new Icon[woodTypes.length];

		for (int i = 0; i < woodTypes.length; ++i) {
			textures[i] = iconRegister.registerIcon("biomesoplenty:"+woodTypes[i]);
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
		for (int i = 0; i < woodTypes.length; ++i) {
			list.add(new ItemStack(blockID, 1, i));
		}
	}

	@Override
	public int damageDropped(int meta)
	{
		return meta;
	}
}
