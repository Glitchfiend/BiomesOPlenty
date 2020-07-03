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
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.PlantType;

public class MudBlock extends Block
{
    public MudBlock(Block.Properties properties)
    {
        super(properties);
    }

    @Override
    public void stepOn(World worldIn, BlockPos pos, Entity entityIn)
    {
        entityIn.setDeltaMovement(entityIn.getDeltaMovement().multiply(0.5D, 1.0D, 0.5D));
    }

    @Override
    public boolean canSustainPlant(BlockState state, IBlockReader world, BlockPos pos, Direction facing, net.minecraftforge.common.IPlantable plantable)
    {
        PlantType type = plantable.getPlantType(world, pos.relative(facing));

        switch (type) {
            case PlantType.DESERT: return false;
            case PlantType.NETHER: return false;
            case PlantType.CROP: return false;
            case PlantType.CAVE: return false;
            case PlantType.PLAINS: return false;
            case PlantType.WATER: return false;
            case PlantType.BEACH:
                boolean hasWater = (world.getBlockState(pos.east()).getMaterial() == Material.WATER ||
                        world.getBlockState(pos.west()).getMaterial() == Material.WATER ||
                        world.getBlockState(pos.north()).getMaterial() == Material.WATER ||
                        world.getBlockState(pos.south()).getMaterial() == Material.WATER);
                return hasWater;
        }
        return false;
    }
}
