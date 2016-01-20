/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.item;

import java.util.Random;

import biomesoplenty.api.particle.BOPParticleTypes;
import biomesoplenty.common.block.BlockBOPFlower;
import biomesoplenty.common.enums.BOPFlowers;
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
    
    public BOPFlowers getFlower(ItemStack stack)
    {
        if (! (this.block instanceof BlockBOPFlower)) {return null;}
        return BlockBOPFlower.paging.getVariant((BlockBOPFlower)this.block, stack.getMetadata());
    }

}