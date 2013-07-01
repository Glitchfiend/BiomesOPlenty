package biomesoplenty.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import biomesoplenty.tileentity.TileEntityGrave;

public class BlockGrave extends Block
{
	public BlockGrave(int id)
	{
		super(id, Material.rock);

		setHardness(5f);
		setCreativeTab(CreativeTabs.tabBlock);
	}

	@Override
	public boolean hasTileEntity(int metadata)
	{
		return true;
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public TileEntity createTileEntity(World world, int metadata)
	{
		return new TileEntityGrave();
	}

	@Override
	public int getRenderType()
	{
		return -1;
	}
}
