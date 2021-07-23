/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.block;

import net.minecraft.world.level.block.Block;

public class DriedSaltBlock extends Block
{
    public DriedSaltBlock(Block.Properties properties)
    {
        super(properties);
    }

// TODO:
//    @Override
//    public boolean canSustainPlant(BlockState state, BlockGetter world, BlockPos pos, Direction facing, net.minecraftforge.common.IPlantable plantable) {
//        PlantType type = plantable.getPlantType(world, pos.relative(facing));
//
//        if (type == PlantType.DESERT) return true;
//        else if (type == PlantType.NETHER) return true;
//        else if (type == PlantType.CAVE) return true;
//        else if (type == PlantType.PLAINS) return true;
//        return false;
//    }
}
