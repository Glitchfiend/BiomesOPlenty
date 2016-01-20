/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.block;

import java.util.ArrayList;
import java.util.List;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.enums.BOPWoods;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockBOPDoubleOtherSlab extends BlockBOPHalfOtherSlab
{
    
    @Override
    public IProperty[] getNonRenderingProperties() { return new IProperty[] { HALF }; }
    @Override
    public String getStateName(IBlockState state)
    {
        return "double_" + ((SlabType) state.getValue(VARIANT)).getName() + "_slab";
    }  

    public BlockBOPDoubleOtherSlab()
    {
        super();
    }
    
    @Override
    public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
        List<ItemStack> ret = new ArrayList<ItemStack>();
        SlabType type = state.getValue(BlockBOPHalfOtherSlab.VARIANT);
        IBlockState halfState = BOPBlocks.other_slab.getDefaultState().withProperty(BlockBOPHalfOtherSlab.VARIANT, type);
        
        //Drop two of the corresponding half slab for this block
        ret.add(new ItemStack(halfState.getBlock(), 2, halfState.getBlock().getMetaFromState(halfState)));
        
        return ret;
    }
    
    @Override
    public boolean isDouble() {
        return true;
    }
    
    
}