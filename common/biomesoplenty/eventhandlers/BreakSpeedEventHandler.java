package biomesoplenty.eventhandlers;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumMovingObjectType;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.PlayerEvent;
import biomesoplenty.api.Blocks;

public class BreakSpeedEventHandler 
{
	@ForgeSubscribe
	public void breakSpeed(PlayerEvent.BreakSpeed event) 
	{
		Item item = null;
		World world = event.entityLiving.worldObj;

		if (event.entityPlayer.getCurrentEquippedItem() != null)
		{
			item = Item.itemsList[event.entityPlayer.getCurrentEquippedItem().itemID];
		}

		MovingObjectPosition movingobjectposition = getMovingObjectPositionFromPlayer(event.entityPlayer.worldObj, event.entityPlayer, true);

		if (movingobjectposition != null)
		{
			if (movingobjectposition.typeOfHit == EnumMovingObjectType.TILE)
			{
				int i = movingobjectposition.blockX;
				int j = movingobjectposition.blockY;
				int k = movingobjectposition.blockZ;

				if (item != null)
				{
					if (item.itemID == Item.shears.itemID)
					{
						Block block = Block.blocksList[world.getBlockId(i, j, k)];

						if (Blocks.shearBlockIds.get(block.blockID) != null)
						{
							event.newSpeed = Float.parseFloat(Blocks.shearBlockIds.get(block.blockID).toString());
						}
						else if (block.blockID == Block.web.blockID | block.blockID == Block.leaves.blockID)
						{
							event.newSpeed = 15.0F;
						}
						else if (block.blockID == Block.cloth.blockID)
						{
							event.newSpeed = 5.0F;
						}
						else
						{
							event.newSpeed = item.getStrVsBlock(new ItemStack(item, 1), block);
						}
					}
				}
			}
		}
	}

    private static MovingObjectPosition getMovingObjectPositionFromPlayer(World par1World, EntityPlayer par2EntityPlayer, boolean par3)
    {
        float f = 1.0F;
        float f1 = par2EntityPlayer.prevRotationPitch + (par2EntityPlayer.rotationPitch - par2EntityPlayer.prevRotationPitch) * f;
        float f2 = par2EntityPlayer.prevRotationYaw + (par2EntityPlayer.rotationYaw - par2EntityPlayer.prevRotationYaw) * f;
        double d0 = par2EntityPlayer.prevPosX + (par2EntityPlayer.posX - par2EntityPlayer.prevPosX) * (double)f;
        double d1 = par2EntityPlayer.prevPosY + (par2EntityPlayer.posY - par2EntityPlayer.prevPosY) * (double)f + 1.62D - (double)par2EntityPlayer.yOffset;
        double d2 = par2EntityPlayer.prevPosZ + (par2EntityPlayer.posZ - par2EntityPlayer.prevPosZ) * (double)f;
        Vec3 vec3 = par1World.getWorldVec3Pool().getVecFromPool(d0, d1, d2);
        float f3 = MathHelper.cos(-f2 * 0.017453292F - (float)Math.PI);
        float f4 = MathHelper.sin(-f2 * 0.017453292F - (float)Math.PI);
        float f5 = -MathHelper.cos(-f1 * 0.017453292F);
        float f6 = MathHelper.sin(-f1 * 0.017453292F);
        float f7 = f4 * f5;
        float f8 = f3 * f5;
        double d3 = 5.0D;
        if (par2EntityPlayer instanceof EntityPlayerMP)
        {
            d3 = ((EntityPlayerMP)par2EntityPlayer).theItemInWorldManager.getBlockReachDistance();
        }
        Vec3 vec31 = vec3.addVector((double)f7 * d3, (double)f6 * d3, (double)f8 * d3);
        return par1World.rayTraceBlocks_do_do(vec3, vec31, par3, !par3);
    }
}
