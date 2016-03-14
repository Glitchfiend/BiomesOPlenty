/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.item;

import java.util.List;

import com.google.common.collect.ImmutableSet;

import biomesoplenty.api.block.IBOPBlock;
import biomesoplenty.common.util.block.BlockStateUtils;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBOPBlock extends ItemBlock
{
    
    public IBOPBlock bopBlock;
    
    public ItemBOPBlock(Block block)
    {
        super(block);
        if (block instanceof IBOPBlock)
        {
            this.bopBlock = (IBOPBlock)block;
        }
        else
        {
            throw new IllegalArgumentException("ItemBOPBlock must be created with a block implementing IBOPBlock");
        }
        this.setHasSubtypes(true);
    }
    
    // define the items which will appear in the creative tab (called by ItemBlock class)
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item itemIn, CreativeTabs tab, List subItems)
    {        
        ImmutableSet<IBlockState> presets = BlockStateUtils.getBlockPresets(this.block);
        if (presets.isEmpty())
        {
            subItems.add(new ItemStack(this.block, 1, 0));
        }
        else
        {
            for (IBlockState state : presets)
            {
                subItems.add(new ItemStack(this.block, 1, this.block.getMetaFromState(state)));
            }
        }
    }

    @Override
    public int getMetadata(int metadata)
    {
        return metadata;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        ImmutableSet<IBlockState> presets = BlockStateUtils.getBlockPresets(this.block);
        if (presets.isEmpty())
        {
            return super.getUnlocalizedName();
        }
        else
        {
            int meta = stack.getMetadata();
            IBlockState oldState = block.getStateFromMeta(meta);
            IBlockState newState = BlockStateUtils.getPresetState(oldState);

            return super.getUnlocalizedName() + "." + bopBlock.getStateName(newState);
        }
    }
}
