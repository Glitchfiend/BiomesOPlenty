/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.item;

import java.util.List;

import biomesoplenty.common.entity.item.EntityBoatBOP;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceFluidMode;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ItemBoatBOP extends Item
{
	   private final EntityBoatBOP.Type type;

	   public ItemBoatBOP(EntityBoatBOP.Type typeIn, Item.Properties properties)
	   {
	      super(properties);
	      this.type = typeIn;
	   }

	   /**
	    * Called to trigger the item's "innate" right click behavior. To handle when this item is used on a Block, see
	    * {@link #onItemUse}.
	    */
	   @Override
	   public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
	      ItemStack itemstack = playerIn.getHeldItem(handIn);
	      float f = 1.0F;
	      float f1 = playerIn.prevRotationPitch + (playerIn.rotationPitch - playerIn.prevRotationPitch) * 1.0F;
	      float f2 = playerIn.prevRotationYaw + (playerIn.rotationYaw - playerIn.prevRotationYaw) * 1.0F;
	      double d0 = playerIn.prevPosX + (playerIn.posX - playerIn.prevPosX) * 1.0D;
	      double d1 = playerIn.prevPosY + (playerIn.posY - playerIn.prevPosY) * 1.0D + (double)playerIn.getEyeHeight();
	      double d2 = playerIn.prevPosZ + (playerIn.posZ - playerIn.prevPosZ) * 1.0D;
	      Vec3d vec3d = new Vec3d(d0, d1, d2);
	      float f3 = MathHelper.cos(-f2 * ((float)Math.PI / 180F) - (float)Math.PI);
	      float f4 = MathHelper.sin(-f2 * ((float)Math.PI / 180F) - (float)Math.PI);
	      float f5 = -MathHelper.cos(-f1 * ((float)Math.PI / 180F));
	      float f6 = MathHelper.sin(-f1 * ((float)Math.PI / 180F));
	      float f7 = f4 * f5;
	      float f8 = f3 * f5;
	      double d3 = 5.0D;
	      Vec3d vec3d1 = vec3d.add((double)f7 * 5.0D, (double)f6 * 5.0D, (double)f8 * 5.0D);
	      RayTraceResult raytraceresult = worldIn.rayTraceBlocks(vec3d, vec3d1, RayTraceFluidMode.ALWAYS);
	      if (raytraceresult == null) {
	         return new ActionResult<>(EnumActionResult.PASS, itemstack);
	      } else {
	         Vec3d vec3d2 = playerIn.getLook(1.0F);
	         boolean flag = false;
	         List<Entity> list = worldIn.getEntitiesWithinAABBExcludingEntity(playerIn, playerIn.getBoundingBox().expand(vec3d2.x * 5.0D, vec3d2.y * 5.0D, vec3d2.z * 5.0D).grow(1.0D));

	         for(int i = 0; i < list.size(); ++i) {
	            Entity entity = list.get(i);
	            if (entity.canBeCollidedWith()) {
	               AxisAlignedBB axisalignedbb = entity.getBoundingBox().grow((double)entity.getCollisionBorderSize());
	               if (axisalignedbb.contains(vec3d)) {
	                  flag = true;
	               }
	            }
	         }

	         if (flag) {
	            return new ActionResult<>(EnumActionResult.PASS, itemstack);
	         } else if (raytraceresult.type == RayTraceResult.Type.BLOCK) {
	            BlockPos blockpos = raytraceresult.getBlockPos();
	            Block block = worldIn.getBlockState(blockpos).getBlock();
	            EntityBoatBOP entityboat = new EntityBoatBOP(worldIn, raytraceresult.hitVec.x, raytraceresult.hitVec.y, raytraceresult.hitVec.z);
	            entityboat.setBoatType(this.type);
	            entityboat.rotationYaw = playerIn.rotationYaw;
	            if (!worldIn.isCollisionBoxesEmpty(entityboat, entityboat.getBoundingBox().grow(-0.1D))) {
	               return new ActionResult<>(EnumActionResult.FAIL, itemstack);
	            } else {
	               if (!worldIn.isRemote) {
	                  worldIn.spawnEntity(entityboat);
	               }

	               if (!playerIn.abilities.isCreativeMode) {
	                  itemstack.shrink(1);
	               }

	               playerIn.addStat(StatList.ITEM_USED.get(this));
	               return new ActionResult<>(EnumActionResult.SUCCESS, itemstack);
	            }
	         } else {
	            return new ActionResult<>(EnumActionResult.PASS, itemstack);
	         }
	      }
	   }
	}