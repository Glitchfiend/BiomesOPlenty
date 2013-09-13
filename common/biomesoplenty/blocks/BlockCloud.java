package biomesoplenty.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import biomesoplenty.BiomesOPlenty;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockCloud extends Block
{
	public BlockCloud(int par1)
	{
		super(par1, Material.sponge);
		this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister)
	{
		blockIcon = par1IconRegister.registerIcon("biomesoplenty:cloud");
	}


	/**
	 * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
	 * cleared to be reused)
	 */
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
	{
		float var5 = 0.25F;
		return AxisAlignedBB.getAABBPool().getAABB(par2, par3, par4, par2 + 1, par3 + 1 - var5, par4 + 1);
	}

	/**
	 * Triggered whenever an entity collides with this block (enters into the block). Args: world, x, y, z, entity
	 */
	@Override
	public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity)
	{
		par5Entity.fallDistance = 0.0F;
		par5Entity.motionX *= 0.8D;
		par5Entity.motionZ *= 0.8D;
	}

	@Override
	@SideOnly(Side.CLIENT)

	/**
	 * Returns which pass should this block be rendered on. 0 for solids and 1 for alpha
	 */
	public int getRenderBlockPass()
	{
		return 1;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)

	/**
	 * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
	 * coordinates.  Args: blockAccess, x, y, z, side
	 */
	public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
	{
		return true;//super.shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, 1 - par5);
	}
}