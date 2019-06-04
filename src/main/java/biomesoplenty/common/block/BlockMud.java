/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.block;

import biomesoplenty.api.item.BOPItems;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import java.util.Random;

public class BlockMud extends Block
{
	protected static final VoxelShape SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 14.0D, 16.0D);

    public BlockMud(Block.Properties properties)
    {
        super(properties);
    }

    @Override
    public VoxelShape getCollisionShape(IBlockState state, IBlockReader worldIn, BlockPos pos)
    {
        return SHAPE;
    }

    @Override
    public IItemProvider getItemDropped(IBlockState state, World worldIn, BlockPos pos, int fortune)
    {
        return BOPItems.mudball;
    }
    
    @Override
    public int quantityDropped(IBlockState state, Random random)
    {
        return 4;
    }
    
    @Override
    public void onEntityCollision(IBlockState state, World worldIn, BlockPos pos, Entity entityIn)
    {
        entityIn.motionX *= 0.4D;
        entityIn.motionZ *= 0.4D;
    }
    
    @Override
    public boolean canSustainPlant(IBlockState state, IBlockReader world, BlockPos pos, EnumFacing facing, net.minecraftforge.common.IPlantable plantable) {
        net.minecraftforge.common.EnumPlantType type = plantable.getPlantType(world, pos.offset(facing));

        switch (type) {
            case Desert: return false;
            case Nether: return false;
            case Crop: return false;
            case Cave: return false;
            case Plains: return false;
            case Water: return false;
            case Beach: return true;
        }
        return false;
    }
}
