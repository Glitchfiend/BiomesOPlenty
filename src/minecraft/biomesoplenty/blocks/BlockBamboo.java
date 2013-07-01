package biomesoplenty.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.world.World;
import biomesoplenty.BiomesOPlenty;

public class BlockBamboo extends Block
{
	public BlockBamboo(int par1)
	{
		super(par1, Material.plants);
		float var3 = 0.15F;
		setBurnProperties(blockID, 5, 5);
		this.setBlockBounds(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, 1.0F, 0.5F + var3);
		this.setTickRandomly(true);
		this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister)
	{
		blockIcon = par1IconRegister.registerIcon("BiomesOPlenty:bamboo");
	}

	@Override
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
	{
		if (par1World.isAirBlock(par2, par3 + 1, par4))
		{
			int var6;

			for (var6 = 1; par1World.getBlockId(par2, par3 - var6, par4) == blockID; ++var6)
			{
				;
			}

			if (var6 < 3)
			{
				int var7 = par1World.getBlockMetadata(par2, par3, par4);

				if (var7 == 15)
				{
					par1World.setBlock(par2, par3 + 1, par4, blockID);
					par1World.setBlockMetadataWithNotify(par2, par3, par4, 0, 2);
				}
				else
				{
					par1World.setBlockMetadataWithNotify(par2, par3, par4, var7 + 1, 2);
				}
			}
		}
	}

	@Override
	public boolean canBeReplacedByLeaves(World world, int x, int y, int z)
	{
		return false;
	}

	/**
	 * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
	 */
	@Override
	public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
	{
		int var5 = par1World.getBlockId(par2, par3 - 1, par4);
		if (var5 == blockID)
			return true;
		else if (var5 == Block.grass.blockID)
			return true;
		else
			return false;
	}

	@Override
	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5)
	{
		this.checkBlockCoordValid(par1World, par2, par3, par4);
	}

	protected final void checkBlockCoordValid(World par1World, int par2, int par3, int par4)
	{
		if (!this.canBlockStay(par1World, par2, par3, par4))
		{
			this.dropBlockAsItem(par1World, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4), 0);
			par1World.setBlock(par2, par3, par4, 0);
		}
	}

	@Override
	public boolean canBlockStay(World par1World, int par2, int par3, int par4)
	{
		return this.canPlaceBlockAt(par1World, par2, par3, par4);
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	public int getRenderType()
	{
		return 1;
	}

	@Override
	public boolean canSustainLeaves(World world, int x, int y, int z)
	{
		return true;
	}

}
