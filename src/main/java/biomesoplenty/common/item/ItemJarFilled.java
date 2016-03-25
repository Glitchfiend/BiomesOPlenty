/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.item;

import java.util.List;

import biomesoplenty.common.entities.EntityPixie;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemJarFilled extends Item
{
    
    public enum JarContents implements IStringSerializable
    {
        HONEY, POISON, PIXIE;
        
        @Override
        public String getName()
        {
            return this.name().toLowerCase();
        }
        @Override
        public String toString()
        {
            return this.getName();
        }
    }

    public ItemJarFilled()
    {
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.setMaxStackSize(1);
    }
    
    // add all the contents types as separate items in the creative tab
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item itemIn, CreativeTabs tab, List subItems)
    {
        for (JarContents contents : JarContents.values())
        {
            subItems.add(new ItemStack(itemIn, 1, contents.ordinal()));
        }
    }

    // default behavior in Item is to return 0, but the meta value is important here because it determines the jar contents
    @Override
    public int getMetadata(int metadata)
    {
        return metadata;
    }
    
    public JarContents getContentsType(ItemStack stack)
    {
        int meta = stack.getMetadata();
        try {
            return JarContents.values()[meta];
        } catch (Exception e) {
            // if metadata is out of bounds return honey as a default (should never happen)
            return JarContents.HONEY;
        }
    }
    
    // get the correct name for this item by looking up the meta value in the JarContents enum
    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        return super.getUnlocalizedName() + "_" + this.getContentsType(stack).getName();
    }
    
    
    
    protected Vec3d getAirPositionInFrontOfPlayer(World world, EntityPlayer player, double targetDistance)
    {
        float cosYaw = MathHelper.cos(-player.rotationYaw * 0.017453292F - (float)Math.PI);
        float sinYaw = MathHelper.sin(-player.rotationYaw * 0.017453292F - (float)Math.PI);
        float cosPitch = -MathHelper.cos(-player.rotationPitch * 0.017453292F);
        float facingX = sinYaw * cosPitch;
        float facingY = MathHelper.sin(-player.rotationPitch * 0.017453292F);
        float facingZ = cosYaw * cosPitch;

        Vec3d playerEyePosition = new Vec3d(player.posX, player.posY + (double)player.getEyeHeight(), player.posZ);
        Vec3d targetPosition = playerEyePosition.addVector((double)facingX * targetDistance, (double)facingY * targetDistance, (double)facingZ * targetDistance);
        
        // see if there's anything in the way
        RayTraceResult hit = world.rayTraceBlocks(playerEyePosition, targetPosition, true, false, false);
        if (hit == null)
        {
            return targetPosition;
        }
        else
        {
            // there's something in the way - return the point just before the collision point
            double distance = playerEyePosition.distanceTo(hit.hitVec) * 0.9;
            return playerEyePosition.addVector((double)facingX * distance, (double)facingY * distance, (double)facingZ * distance);            
        }
    }
    
    // TODO: should you get back an empty jar?
    public boolean releasePixie(ItemStack stack, World world, EntityPlayer player, Vec3d releasePoint)
    {
        if (world.provider.isSurfaceWorld())
        {
            EntityPixie pixie = new EntityPixie(world);                    
            pixie.setLocationAndAngles(releasePoint.xCoord, releasePoint.yCoord, releasePoint.zCoord, MathHelper.wrapDegrees(world.rand.nextFloat() * 360.0F), 0.0F);
            world.spawnEntityInWorld(pixie);
            pixie.playLivingSound();
            if (stack.hasDisplayName()) {pixie.setCustomNameTag(stack.getDisplayName());}
            if (!player.capabilities.isCreativeMode) {--stack.stackSize;}
            return true;
        } else {
            player.addChatComponentMessage(new TextComponentString("\u00a75Pixies cannot survive in this environment!"));
            return false;
        }
    }
    
    
    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World world, EntityPlayer player, EnumHand hand)
    {
        if (world.isRemote) {return new ActionResult<ItemStack>(EnumActionResult.FAIL, stack);}
        switch (this.getContentsType(stack))
        {
            // right click in air releases pixie
            case PIXIE:
                if (this.getContentsType(stack) == JarContents.PIXIE)
                {
                    // release pixie into the air in front of the player (target distance 0.8, but will be closer if there's blocks in the way)
                    Vec3d releasePoint = this.getAirPositionInFrontOfPlayer(world, player, 0.8D);
                    this.releasePixie(stack, world, player, releasePoint);
                }
                return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
                
            case HONEY: case POISON: default:
                return new ActionResult<ItemStack>(EnumActionResult.FAIL, stack);
        }
    }
    
    
    @Override
    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (world.isRemote) {return EnumActionResult.FAIL;}
        switch (this.getContentsType(stack))
        {
            // right click on block also releases pixie
            case PIXIE:
                double distX = hitX - player.posX;
                double distY = hitY - (player.posY + (double)player.getEyeHeight());
                double distZ = hitZ - player.posZ;                
                double a = 0.9D;
                Vec3d releasePoint = new Vec3d(player.posX + a * distX, player.posY + (double)player.getEyeHeight() + a * distY, player.posZ + a * distZ);
                return this.releasePixie(stack, world, player, releasePoint) ? EnumActionResult.SUCCESS : EnumActionResult.FAIL;
                
            // TODO: are you supposed to be able to pour out honey? How much should you get?  Why don't we just use buckets?
            case HONEY: case POISON: default:
                return EnumActionResult.SUCCESS;
        }
    }
    
    
    
    
    
}
   