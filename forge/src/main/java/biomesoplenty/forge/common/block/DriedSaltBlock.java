/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.forge.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.PlantType;

public class DriedSaltBlock extends Block
{
    public DriedSaltBlock(Block.Properties properties)
    {
        super(properties);
    }

    @Override
    public boolean canSustainPlant(BlockState state, BlockGetter world, BlockPos pos, Direction facing, net.minecraftforge.common.IPlantable plantable) {
        PlantType type = plantable.getPlantType(world, pos.relative(facing));

        if (type == PlantType.DESERT) return true;
        else if (type == PlantType.NETHER) return true;
        else if (type == PlantType.CAVE) return true;
        else if (type == PlantType.PLAINS) return true;
        return false;
    }
}
