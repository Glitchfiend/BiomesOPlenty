/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.item;

import java.util.Random;

import biomesoplenty.api.block.BOPFlowerEnums.AllFlowers;
import biomesoplenty.api.particle.BOPParticleTypes;
import biomesoplenty.common.block.BlockBOPFlower;
import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class ItemBOPFlower extends ItemBOPBlock {

    public ItemBOPFlower(Block block) {
        super(block);
    }
    
    public AllFlowers getFlower(ItemStack stack)
    {
        if (! (this.block instanceof BlockBOPFlower)) {return null;}
        return BlockBOPFlower.paging.getVariant((BlockBOPFlower)this.block, stack.getMetadata());
    }
    
    @Override
    public int getMaxItemUseDuration(ItemStack stack)
    {
        return 20;
    }
    
    @Override
    public EnumAction getItemUseAction(ItemStack stack)
    {
        return (this.getFlower(stack) == AllFlowers.DANDELION) ? EnumAction.BLOCK : super.getItemUseAction(stack);
    }
    
    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
        if (this.getFlower(stack) == AllFlowers.DANDELION)
        {
            player.setItemInUse(stack, this.getMaxItemUseDuration(stack));
        }
        return stack;
    }

    @Override
    public void onUsingTick(ItemStack stack, EntityPlayer player, int count)
    {
        if (this.getFlower(stack) == AllFlowers.DANDELION)
        {
            Vec3 vec = player.getLook(0.5F);
            Random rnd = player.getRNG();
        
            if (player.worldObj.isRemote)
            {
                for (int p = 0; p < 4; ++p)
                {
                    float pos = (rnd.nextFloat() - 0.5F) / 8;
                    BiomesOPlenty.proxy.spawnParticle(BOPParticleTypes.DANDELION, player.posX + vec.xCoord + pos, player.posY + vec.yCoord + player.getEyeHeight() + pos, player.posZ + vec.zCoord + pos);
                }
            }
        
            if (count < 10 && !player.capabilities.isCreativeMode) {
                player.stopUsingItem();
                --stack.stackSize;
            }
        }
    }


}