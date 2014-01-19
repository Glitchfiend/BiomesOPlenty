package biomesoplenty.common.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import biomesoplenty.BiomesOPlenty;

public class BlockBOPPlank extends Block
{
	private static final String[] woodTypes = new String[] {"plank_sacredoak", "plank_cherry", "plank_dark", "plank_fir", "plank_holy", "plank_magic", "plank_mangrove", "plank_palm", "plank_redwood", "plank_willow", "bamboothatching", "plank_pine", "plank_hell_bark", "plank_jacaranda"};
	private IIcon[] textures;

	public BlockBOPPlank()
	{
		//TODO: Material.wood
		super(Material.field_151575_d);
		
		//TODO: this.setHardness
		this.func_149711_c(2.0F);
		this.setHarvestLevel("axe", 0);
		
		//TODO setStepSound(Block.soundWoodFootstep)
		this.func_149672_a(Block.field_149766_f);
		
		//TODO: this.setCreativeTab()
		this.func_149647_a(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	//TODO:		registerIcons()
	public void func_149651_a(IIconRegister iconRegister)
	{
		textures = new IIcon[woodTypes.length];

		for (int i = 0; i < woodTypes.length; ++i) {
			textures[i] = iconRegister.registerIcon("biomesoplenty:"+woodTypes[i]);
		}
	}

	@Override
	//TODO:		 getIcon()
	public IIcon func_149691_a(int side, int meta)
	{
		if (meta < 0 || meta >= textures.length) {
			meta = 0;
		}

		return textures[meta];
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	//TODO:		getSubBlocks()
	public void func_149666_a(Item block, CreativeTabs creativeTabs, List list) 
	{
		for (int i = 0; i < woodTypes.length; ++i) 
		{
			list.add(new ItemStack(block, 1, i));
		}
	}

	@Override
	//TODO     damageDropped()
	public int func_149692_a(int meta)
	{
		return meta;
	}
}
