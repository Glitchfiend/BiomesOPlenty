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
	//TODO:     setBlockBoundsBasedOnState()
	public void func_149719_a(IBlockAccess world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z);

		switch (meta)
		{
			case 0:
						//TODO: setBlockBounds
		this.func_149676_a(0.0F, 0.0F, 0.31F, 1.0F, 1.6875F, 0.69F);
				break;

			case 1:
						//TODO: setBlockBounds
		this.func_149676_a(0.0F, -1.0F, 0.31F, 1.0F, 0.6875F, 0.69F);
				break;
				
			case 2:
						//TODO: setBlockBounds
		this.func_149676_a(0.31F, 0.0F, 0.0F, 0.69F, 1.6875F, 1.0F);
				break;

			case 3:
						//TODO: setBlockBounds
		this.func_149676_a(0.31F, -1.0F, 0.0F, 0.69F, 0.6875F, 1.0F);
				break;
		}
	}
	
	@Override
    //TODO:		onNeighborBlockChange()
	public void func_149695_a(World world, int x, int y, int z, Block neighborBlock)
	{
		if (neighborBlock == this)
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
	//TODO:		   renderAsNormalBlock()
    public boolean func_149686_d()
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
	//TODO		getRenderType()
	public int func_149645_b()
	{
		return RenderUtils.graveModel;
	}
	
	@Override
	//TODO			shouldSideBeRendered
    public boolean func_149646_a(IBlockAccess world, int x, int y, int z, int side)
	{
		return true;
	}
}
