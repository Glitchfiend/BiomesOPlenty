/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class BlockDriedSand extends Block
{
    public BlockDriedSand(Block.Properties properties)
    {
        super(properties);
    }
    
    @Override
    public boolean canSustainPlant(IBlockState state, IBlockReader world, BlockPos pos, EnumFacing facing, net.minecraftforge.common.IPlantable plantable) {
        net.minecraftforge.common.EnumPlantType type = plantable.getPlantType(world, pos.offset(facing));

        switch (type) {
            case Desert: return true;
            case Nether: return true;
            case Crop: return false;
            case Cave: return true;
            case Plains: return true;
            case Water: return false;
            case Beach: return false;
        }
        return false;
    }
}
