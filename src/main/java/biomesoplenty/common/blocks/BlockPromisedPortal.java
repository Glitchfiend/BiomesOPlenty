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
	public BlockPromisedPortal()
	{
		//TODO:	Material.portal
		super(Material.field_151567_E);
		
		//TODO:	this.setUnbreakable()
		this.func_149722_s();
		//TODO: this.setResistance
		this.func_149752_b(6000000.0F);
		
		//TODO: this.setLightValue
		this.func_149715_a(1.0F);
	}

	@Override
	//TODO:		registerIcons()
	public void func_149651_a(IIconRegister iconRegister)
	{
		//TODO: blockIcon
		this.field_149761_L = iconRegister.registerIcon("biomesoplenty:portal");
	}

	@Override
	//TODO:	   getRenderBlockPass()
	public int func_149701_w()
	{
		return 1;
	}

	@Override
	@SideOnly(Side.CLIENT)
	//TODO			shouldSideBeRendered
    public boolean func_149646_a(IBlockAccess world, int x, int y, int z, int side)
	{
		//TODO:		 shouldSideBeRendered()
		return super.func_149646_a(world, x, y, z, 1 - side);
	}

	@Override
	//TODO: getCollisionBoundingBoxFromPool
	public AxisAlignedBB func_149668_a(World world, int x, int y, int z)
	{
		return null;
	}

	@Override
	//TODO:		   isOpaqueCube()
	public boolean func_149662_c()
	{
		return false;
	}

	@Override
	//TODO:		onEntityCollidedWithBlock()
	public void func_149670_a(World world, int x, int y, int z, Entity entity)
	{
		if (entity.ridingEntity == null && entity.riddenByEntity == null)
		{
			if (entity instanceof EntityPlayerMP)
			{
				/*TODO: FEATURE EntityPlayerMP thePlayer = (EntityPlayerMP) par5Entity;
				if (par5Entity.dimension != BOPConfigurationIDs.promisedLandDimID)
				{
					thePlayer.mcServer.getConfigurationManager().transferPlayerToDimension(thePlayer, BOPConfigurationIDs.promisedLandDimID, new TeleporterPromised(thePlayer.mcServer.worldServerForDimension(BOPConfigurationIDs.promisedLandDimID)));
				}
				else
				{
					thePlayer.mcServer.getConfigurationManager().transferPlayerToDimension(thePlayer, 0, new TeleporterPromised(thePlayer.mcServer.worldServerForDimension(0)));
				}*/
			}
		}
	}
}
