/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.item;

import biomesoplenty.common.block.BlockBOPPlant;
import biomesoplenty.common.enums.BOPPlants;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class ItemBOPPlant extends ItemBOPBlock {

    public ItemBOPPlant(Block block) {
        super(block);
    }
    
    // The code for right clicking needs to be overridden to handle the unique way reeds are placed - on top of the water
    // (usually when you point the cursor at water the picked block is whatever is underneath the water - when placing reeds the water itself has to be picked)
    @Override
    public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn)
    {
        if (this.block instanceof BlockBOPPlant)
        {
            BlockBOPPlant block = (BlockBOPPlant)this.block;
            IBlockState state = block.getStateFromMeta( itemStackIn.getMetadata() );
            BOPPlants plant = ((BOPPlants) state.getValue(block.variantProperty));
            if (plant == BOPPlants.REED)
            {
                
                MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(worldIn, playerIn, true);
                
                if (movingobjectposition == null)
                {
                    return itemStackIn;
                }
                else
                {
                    if (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
                    {
                        BlockPos blockpos = movingobjectposition.getBlockPos();

                        if (!worldIn.isBlockModifiable(playerIn, blockpos))
                        {
                            return itemStackIn;
                        }

                        if (!playerIn.canPlayerEdit(blockpos.offset(movingobjectposition.sideHit), movingobjectposition.sideHit, itemStackIn))
                        {
                            return itemStackIn;
                        }

                        BlockPos blockpos1 = blockpos.up();
                        IBlockState iblockstate = worldIn.getBlockState(blockpos);

                        if (iblockstate.getBlock().getMaterial() == Material.water && ((Integer)iblockstate.getValue(BlockLiquid.LEVEL)).intValue() == 0 && worldIn.isAirBlock(blockpos1))
                        {
                            // special case for handling block placement with reeds
                            net.minecraftforge.common.util.BlockSnapshot blocksnapshot = net.minecraftforge.common.util.BlockSnapshot.getBlockSnapshot(worldIn, blockpos1);
                                                
                            worldIn.setBlockState(blockpos1, BlockBOPPlant.paging.getVariantState(BOPPlants.REED));
                            if (net.minecraftforge.event.ForgeEventFactory.onPlayerBlockPlace(playerIn, blocksnapshot, net.minecraft.util.EnumFacing.UP).isCanceled())
                            {
                                blocksnapshot.restore(true, false);
                                return itemStackIn;
                            }

                            if (!playerIn.capabilities.isCreativeMode)
                            {
                                --itemStackIn.stackSize;
                            }

                            playerIn.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);
                        }
                    }

                    return itemStackIn;
                }
                
                
            }
            
        }
        // in all other cases take the default action
        return super.onItemRightClick(itemStackIn, worldIn, playerIn);
        
    }
    
    
}