/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.item;

import java.util.Random;

import biomesoplenty.api.particle.BOPParticleTypes;
import biomesoplenty.common.block.BlockBOPFlower1;
import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class ItemBOPFlower extends ItemBOPBlock {

    public ItemBOPFlower(Block block) {
        super(block);
    }
    
    public boolean isDandelion(ItemStack stack)
    {
        if (!(this.block instanceof BlockBOPFlower1)) {return false;}
        IBlockState state = ((BlockBOPFlower1)this.block).getStateFromMeta(stack.getMetadata());
        return state.getValue(BlockBOPFlower1.VARIANT) == BlockBOPFlower1.FlowerType.DANDELION;     
    }
    
    @Override
    public int getMaxItemUseDuration(ItemStack stack)
    {
        return 20;
    }
    
    @Override
    public EnumAction getItemUseAction(ItemStack stack)
    {
        return this.isDandelion(stack) ? EnumAction.BLOCK : super.getItemUseAction(stack);
    }
    
    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
        if (this.isDandelion(stack))
        {
            player.setItemInUse(stack, this.getMaxItemUseDuration(stack));
        }
        return stack;
    }

    @Override
    public void onUsingTick(ItemStack stack, EntityPlayer player, int count)
    {
        if (this.isDandelion(stack))
        {
            Vec3 vec = player.getLook(0.5F);
            Random rnd = player.getRNG();
        
            for (int p = 0; p < 4; ++p)
            {
                float pos = (rnd.nextFloat() - 0.5F) / 8;
                BiomesOPlenty.proxy.spawnParticle(BOPParticleTypes.DANDELION, player.posX + vec.xCoord + pos, player.posY + vec.yCoord + player.getEyeHeight() + pos, player.posZ + vec.zCoord + pos);
            }
        
            if (count < 10 && !player.capabilities.isCreativeMode) {
                player.stopUsingItem();
                --stack.stackSize;
            }
        }
    }


}