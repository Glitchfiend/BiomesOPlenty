/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.item;

import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import biomesoplenty.api.item.IColoredItem;
import biomesoplenty.common.init.ModEntities;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.MobSpawnerBaseLogic;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBOPSpawnEgg extends Item implements IColoredItem
{
    
    public ItemBOPSpawnEgg()
    {
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item itemIn, CreativeTabs tab, List subItems)
    {
        for (Entry<Integer, EntityList.EntityEggInfo> entry : ModEntities.entityEggs.entrySet())
        {
            subItems.add(new ItemStack(itemIn, 1, entry.getKey()));
        }
    }
    
            
    public static Entity spawnBOPCreature(World worldIn, int entityID, double x, double y, double z)
    {
        Entity entity = ModEntities.createEntityByID(entityID, worldIn);
        
        if (entity instanceof EntityLivingBase)
        {
            EntityLiving entityliving = (EntityLiving)entity;
            entity.setLocationAndAngles(x, y, z, MathHelper.wrapDegrees(worldIn.rand.nextFloat() * 360.0F), 0.0F);
            entityliving.rotationYawHead = entityliving.rotationYaw;
            entityliving.renderYawOffset = entityliving.rotationYaw;
            entityliving.onInitialSpawn(worldIn.getDifficultyForLocation(new BlockPos(entityliving)), (IEntityLivingData)null);
            worldIn.spawnEntityInWorld(entity);
            entityliving.playLivingSound();
        }

        return entity;
    }
    
    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        int bopEntityId = stack.getMetadata();
        String entityName = ModEntities.idToBOPEntityName.get(bopEntityId);
        return super.getUnlocalizedName(stack)+"_"+entityName;
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public IItemColor getItemColor()
    {
        return new IItemColor()
        {
            @Override
            public int getColorFromItemstack(ItemStack stack, int tintIndex) 
            {
                EntityList.EntityEggInfo entityegginfo = ModEntities.entityEggs.get(Integer.valueOf(stack.getMetadata()));
                return entityegginfo != null ? (tintIndex == 0 ? entityegginfo.primaryColor : entityegginfo.secondaryColor) : 16777215;
            }
        };
    }

    @Override
    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (worldIn.isRemote)
        {
            return EnumActionResult.SUCCESS;
        }
        else if (!playerIn.canPlayerEdit(pos.offset(facing), facing, stack))
        {
            return EnumActionResult.FAIL;
        }
        else
        {
            IBlockState iblockstate = worldIn.getBlockState(pos);

            if (iblockstate.getBlock() == Blocks.mob_spawner)
            {
                TileEntity tileentity = worldIn.getTileEntity(pos);

                if (tileentity instanceof TileEntityMobSpawner)
                {
                    MobSpawnerBaseLogic mobspawnerbaselogic = ((TileEntityMobSpawner)tileentity).getSpawnerBaseLogic();
                    mobspawnerbaselogic.setEntityName(EntityList.classToStringMapping.get(EntityList.getClassFromID(stack.getMetadata())));
                    tileentity.markDirty();
                    worldIn.notifyBlockUpdate(pos, iblockstate, iblockstate, 3);

                    if (!playerIn.capabilities.isCreativeMode)
                    {
                        --stack.stackSize;
                    }

                    return EnumActionResult.SUCCESS;
                }
            }

            pos = pos.offset(facing);
            double d0 = 0.0D;

            if (facing == EnumFacing.UP && iblockstate instanceof BlockFence)
            {
                d0 = 0.5D;
            }

            Entity entity = spawnBOPCreature(worldIn, stack.getMetadata(), (double)pos.getX() + 0.5D, (double)pos.getY() + d0, (double)pos.getZ() + 0.5D);

            if (entity != null)
            {
                if (entity instanceof EntityLivingBase && stack.hasDisplayName())
                {
                    entity.setCustomNameTag(stack.getDisplayName());
                }

                if (!playerIn.capabilities.isCreativeMode)
                {
                    --stack.stackSize;
                }
            }

            return EnumActionResult.SUCCESS;
        }
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World world, EntityPlayer player, EnumHand hand)
    {
        if (world.isRemote)
        {
            return new ActionResult<ItemStack>(EnumActionResult.FAIL, stack);
        }
        else
        {
            RayTraceResult movingobjectposition = this.getMovingObjectPositionFromPlayer(world, player, true);

            if (movingobjectposition == null)
            {
                return new ActionResult<ItemStack>(EnumActionResult.FAIL, stack);
            }
            else
            {
                if (movingobjectposition.typeOfHit == RayTraceResult.Type.BLOCK)
                {
                    BlockPos blockpos = movingobjectposition.getBlockPos();

                    if (!world.isBlockModifiable(player, blockpos))
                    {
                        return new ActionResult<ItemStack>(EnumActionResult.FAIL, stack);
                    }

                    if (!player.canPlayerEdit(blockpos, movingobjectposition.sideHit, stack))
                    {
                        return new ActionResult<ItemStack>(EnumActionResult.FAIL, stack);
                    }

                    if (world.getBlockState(blockpos).getBlock() instanceof BlockLiquid)
                    {
                        Entity entity = spawnBOPCreature(world, stack.getMetadata(), (double)blockpos.getX() + 0.5D, (double)blockpos.getY() + 0.5D, (double)blockpos.getZ() + 0.5D);

                        if (entity != null)
                        {
                            if (entity instanceof EntityLivingBase && stack.hasDisplayName())
                            {
                                ((EntityLiving)entity).setCustomNameTag(stack.getDisplayName());
                            }

                            if (!player.capabilities.isCreativeMode)
                            {
                                --stack.stackSize;
                            }

                            //TODO: 1.9 playerIn.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);
                        }
                    }
                }

                return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
            }
        }
    }
    
    
}