package biomesoplenty.common.blocks;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import biomesoplenty.BiomesOPlenty;

public class BlockGrave extends Block
{	
	public BlockGrave()
	{
		//TODO: Material.rock
		super(Material.field_151576_e);

				//TODO: this.setHardness
		this.func_149711_c(5f);
		
		//TODO: this.setCreativeTab()
		this.func_149647_a(BiomesOPlenty.tabBiomesOPlenty);
	}
	
	@Override
	//TODO:		registerIcons()
	public void func_149651_a(IIconRegister iconRegister)
	{
				//TODO: blockIcon
		this.field_149761_L = iconRegister.registerIcon("biomesoplenty:grave");
	}
    
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, int par2, int par3, int par4)
	{
		int meta = world.getBlockMetadata(par2, par3, par4);

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
    public void onNeighborBlockChange(World world, int x, int y, int z, int neighbourID) 
	{
		if (neighbourID == Blocks.grave.get().blockID)
		{
			if (world.getBlockMetadata(x, y, z) == 0 || world.getBlockMetadata(x, y, z) == 2)
			{
				if (world.getBlockId(x, y + 1, z) != Blocks.grave.get().blockID)
				{
		            world.destroyBlock(x, y, z, true);
				}
			}
			else
			{
				if (world.getBlockId(x, y - 1, z) != Blocks.grave.get().blockID)
				{
		            world.destroyBlock(x, y, z, true);
				}
			}
		}
	}
	
	@Override
	public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int meta, int fortune)
	{
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();

		if (meta == 0) ret.add(new ItemStack(Blocks.grave.get(), 1));
		else if (meta == 2) ret.add(new ItemStack(Blocks.grave.get(), 1));

		return ret;
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	//TODO:		   isOpaqueCube()
	public boolean func_149662_c()
	{
		return false;
	}

	@Override
	public int getRenderType()
	{
		return RenderUtils.graveModel;
	}
	
	@Override
	public boolean shouldSideBeRendered(IBlockAccess iblockaccess, int i, int j, int k, int l)
	{
		return true;
	}
}
