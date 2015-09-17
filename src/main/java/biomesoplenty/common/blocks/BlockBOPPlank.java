package biomesoplenty.common.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import biomesoplenty.BiomesOPlenty;

public class BlockBOPPlank extends Block
{
	private static final String[] woodTypes = new String[] {"plank_sacredoak", "plank_cherry", "plank_dark", "plank_fir", "plank_ethereal", "plank_magic", "plank_mangrove", "plank_palm", "plank_redwood", "plank_willow", "bamboothatching", "plank_pine", "plank_hell_bark", "plank_jacaranda", "plank_mahogany"};
	private IIcon[] textures;

	public BlockBOPPlank()
	{
		super(Material.wood);
		
		this.setHardness(2.0F);
		this.setHarvestLevel("axe", 0);
		
		this.setStepSound(Block.soundTypeWood);
		
		this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		textures = new IIcon[woodTypes.length];

		for (int i = 0; i < woodTypes.length; ++i) {
			textures[i] = iconRegister.registerIcon("biomesoplenty:"+woodTypes[i]);
		}
	}

	@Override
	public IIcon getIcon(int side, int meta)
	{
		if (meta < 0 || meta >= textures.length) {
			meta = 0;
		}

		return textures[meta];
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void getSubBlocks(Item block, CreativeTabs creativeTabs, List list) 
	{
		for (int i = 0; i < woodTypes.length; ++i) 
		{
			list.add(new ItemStack(block, 1, i));
		}
	}

	@Override
	public int damageDropped(int meta)
	{
		return meta;
	}
}
