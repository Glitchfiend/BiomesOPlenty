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
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.content.BOPCBlocks;

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
	public int getFlammability(IBlockAccess world, int x, int y, int z, ForgeDirection face)
	{
		Block block = BOPCBlocks.planks;

		if (block == BOPCBlocks.planks && world.getBlockMetadata(x, y, z) == 12)
		{
			return 0;
		}
		else
		{
			return Blocks.fire.getFlammability(this);
		}
	}

	@Override
	public int getFireSpreadSpeed(IBlockAccess world, int x, int y, int z, ForgeDirection face)
	{
		Block block = BOPCBlocks.planks;

		if (block == BOPCBlocks.planks && world.getBlockMetadata(x, y, z) == 12)
		{
			return 0;
		}
		else
		{
			return Blocks.fire.getEncouragement(this);
		}
	}

	@Override
	public boolean isFlammable(IBlockAccess world, int x, int y, int z, ForgeDirection face)
	{
		Block block = BOPCBlocks.planks;

		if (block == BOPCBlocks.planks && world.getBlockMetadata(x, y, z) == 12)
		{
			return false;
		}
		else
		{
			return getFlammability(world, x, y, z, face) > 0;
		}
	}

	@Override
	public int damageDropped(int meta)
	{
		return meta;
	}
}
