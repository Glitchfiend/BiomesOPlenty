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
import biomesoplenty.common.helpers.TeleporterPromised;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockPromisedPortal extends Block
{
	public BlockPromisedPortal()
	{
		//TODO:	Material.portal
		super(Material.portal);
		
		//TODO:	this.setUnbreakable()
		this.setBlockUnbreakable();
		//TODO: this.setResistance
		this.setResistance(6000000.0F);
		
		//TODO: this.setLightValue
		this.setLightLevel(1.0F);
	}

	@Override
	//TODO:		registerIcons()
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		//TODO: blockIcon
		this.blockIcon = iconRegister.registerIcon("biomesoplenty:portal");
	}

	@Override
	//TODO:	   getRenderBlockPass()
	public int getRenderBlockPass()
	{
		return 1;
	}

	@Override
	@SideOnly(Side.CLIENT)
	//TODO			shouldSideBeRendered
    public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side)
	{
		//TODO:		 shouldSideBeRendered()
		return super.shouldSideBeRendered(world, x, y, z, 1 - side);
	}

	@Override
	//TODO: getCollisionBoundingBoxFromPool
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
	{
		return null;
	}

	@Override
	//TODO:		   isOpaqueCube()
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	//TODO:		onEntityCollidedWithBlock()
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{
		if (entity.ridingEntity == null && entity.riddenByEntity == null)
		{
			if (entity instanceof EntityPlayerMP)
			{
			    EntityPlayerMP thePlayer = (EntityPlayerMP) entity;
				if (entity.dimension != BOPConfigurationIDs.promisedLandDimID)
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
