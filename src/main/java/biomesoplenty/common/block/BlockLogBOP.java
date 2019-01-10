/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class BlockLogBOP extends BlockLog
{
	private final MapColor field_196504_b;
	   
    public BlockLogBOP(MapColor p_i48367_1_, Block.Builder p_i48367_2_)
    {
        super(p_i48367_1_, p_i48367_2_);
        this.field_196504_b = p_i48367_1_;
    }
    
    @Override
    public MapColor getMapColor(IBlockState state, IBlockReader worldIn, BlockPos pos) {
        return state.get(AXIS) == EnumFacing.Axis.Y ? this.field_196504_b : this.materialColor;
     }
}
