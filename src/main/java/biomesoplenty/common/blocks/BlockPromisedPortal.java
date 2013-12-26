package biomesoplenty.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import biomesoplenty.common.configuration.BOPConfigurationIDs;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockPromisedPortal extends Block
{
	public BlockPromisedPortal(int par1)
	{
		super(par1, Material.portal);
	}

	@Override
	//TODO:		registerIcons()
	public void func_149651_a(IIconRegister iconRegister)
	{
				//TODO: blockIcon
		this.field_149761_L = par1IconRegister.registerIcon("biomesoplenty:portal");
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
	@SideOnly(Side.CLIENT)

	/**
	 * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
	 * coordinates.  Args: blockAccess, x, y, z, side
	 */
	//TODO			shouldSideBeRendered
    public boolean func_149646_a(IBlockAccess world, int x, int y, int z, int side)
	{
		return super.shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, 1 - par5);
	}

	/**
	 * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
	 * cleared to be reused)
	 */
	@Override
	//TODO: getCollisionBoundingBoxFromPool
	public AxisAlignedBB func_149668_a(World world, int x, int y, int z)
	{
		return null;
	}

	/**
	 * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
	 * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
	 */
	@Override
	//TODO:		   isOpaqueCube()
	public boolean func_149662_c()
	{
		return false;
	}

	@Override
	public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity)
	{
		if (par5Entity.ridingEntity == null && par5Entity.riddenByEntity == null)
		{
			if (par5Entity instanceof EntityPlayerMP)
			{
				EntityPlayerMP thePlayer = (EntityPlayerMP) par5Entity;
				if (par5Entity.dimension != BOPConfigurationIDs.promisedLandDimID)
				{
					thePlayer.mcServer.getConfigurationManager().transferPlayerToDimension(thePlayer, BOPConfigurationIDs.promisedLandDimID, new TeleporterPromised(thePlayer.mcServer.worldServerForDimension(BOPConfigurationIDs.promisedLandDimID)));
				}
				else
				{
					thePlayer.mcServer.getConfigurationManager().transferPlayerToDimension(thePlayer, 0, new TeleporterPromised(thePlayer.mcServer.worldServerForDimension(0)));
				}
			}
		}
	}
}
