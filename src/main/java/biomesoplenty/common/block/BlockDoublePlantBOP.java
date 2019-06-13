/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DoublePlantBlock;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;

public class BlockDoublePlantBOP extends DoublePlantBlock implements IPlantable
{
    public static final EnumProperty<DoubleBlockHalf> field_208063_b = DoublePlantBlock.HALF;
    private final Block field_196392_b;

    public BlockDoublePlantBOP(Block p_i48335_1_, Block.Properties properties)
    {
        super(properties);
        this.field_196392_b = p_i48335_1_;
    }

    @Override
    public boolean isReplaceable(BlockState state, BlockItemUseContext useContext)
    {
        boolean flag = super.isReplaceable(state, useContext);
        return flag && useContext.getItem().getItem() == this.asItem() ? false : flag;
    }

    @Override
    public PlantType getPlantType(IBlockReader world, BlockPos pos)
    {
        Block block = world.getBlockState(pos).getBlock();

        return PlantType.Plains;
    }
}
