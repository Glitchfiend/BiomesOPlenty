package biomesoplenty.common.blocks;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.client.render.RenderUtils;

public class BlockGrave extends Block
{	
	public BlockGrave()
	{
		super(Material.rock);

		this.setHardness(5f);
		
		this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}
	
	@Override
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		this.blockIcon = iconRegister.registerIcon("biomesoplenty:grave");
	}
    
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z);

		switch (meta)
		{
			case 0:
		this.setBlockBounds(0.0F, 0.0F, 0.31F, 1.0F, 1.6875F, 0.69F);
				break;

			case 1:
		this.setBlockBounds(0.0F, -1.0F, 0.31F, 1.0F, 0.6875F, 0.69F);
				break;
				
			case 2:
		this.setBlockBounds(0.31F, 0.0F, 0.0F, 0.69F, 1.6875F, 1.0F);
				break;

			case 3:
		this.setBlockBounds(0.31F, -1.0F, 0.0F, 0.69F, 0.6875F, 1.0F);
				break;
		}
	}
	
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block neighborBlock)
	{
		if (neighborBlock == this)
		{
			if (world.getBlockMetadata(x, y, z) == 0 || world.getBlockMetadata(x, y, z) == 2)
			{
				if (world.getBlock(x, y + 1, z) != this)
				{
		            world.func_147480_a(x, y, z, true);
				}
			}
			else
			{
				if (world.getBlock(x, y - 1, z) != this)
				{
		            world.func_147480_a(x, y, z, true);
				}
			}
		}
	}
	
	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int meta, int fortune)
	{
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();

		if (meta == 0) ret.add(new ItemStack(this, 1));
		else if (meta == 2) ret.add(new ItemStack(this, 1));

		return ret;
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
	public int getRenderType()
	{
		return RenderUtils.graveModel;
	}
	
	@Override
    public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side)
	{
		return true;
	}
}
