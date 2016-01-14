/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.block;

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;

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
    public boolean isDouble() {
        return true;
    }
    
    
}