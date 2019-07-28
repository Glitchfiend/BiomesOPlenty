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
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.Direction;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.PlantType;

import java.util.Random;

public class MudBlock extends Block
{
	protected static final VoxelShape SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 14.0D, 16.0D);

    public MudBlock(Block.Properties properties)
    {
        super(properties);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext selectionContext)
    {
        return SHAPE;
    }

    @Override
    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn)
    {
        entityIn.setMotion(entityIn.getMotion().mul(0.5D, 1.0D, 0.5D));
    }
    
    @Override
    public boolean canSustainPlant(BlockState state, IBlockReader world, BlockPos pos, Direction facing, net.minecraftforge.common.IPlantable plantable)
    {
        PlantType type = plantable.getPlantType(world, pos.offset(facing));

        switch (type) {
            case Desert: return false;
            case Nether: return false;
            case Crop: return false;
            case Cave: return false;
            case Plains: return false;
            case Water: return false;
            case Beach:
                boolean hasWater = (world.getBlockState(pos.east()).getMaterial() == Material.WATER ||
                        world.getBlockState(pos.west()).getMaterial() == Material.WATER ||
                        world.getBlockState(pos.north()).getMaterial() == Material.WATER ||
                        world.getBlockState(pos.south()).getMaterial() == Material.WATER);
                return hasWater;
        }
        return false;
    }
}
