package biomesoplenty.common.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SandBlock;

public class SandBlockBOP extends SandBlock
{
    public SandBlockBOP(int dustColor, Block.Properties properties)
    {
        super(dustColor, properties);
    }

// TODO:
//    @Override
//    public boolean canSustainPlant(BlockState state, BlockGetter world, BlockPos pos, Direction facing, net.minecraftforge.common.IPlantable plantable)
//    {
//        PlantType type = plantable.getPlantType(world, pos.relative(facing));
//
//        if (type == PlantType.DESERT)      return true;
//        else if (type == PlantType.CAVE)   return true;
//        else if (type == PlantType.BEACH)
//        {
//            for(Direction direction : Direction.Plane.HORIZONTAL) {
//                BlockState blockstate1 = world.getBlockState(pos.relative(direction));
//                FluidState fluidstate = world.getFluidState(pos.relative(direction));
//                if (fluidstate.is(FluidTags.WATER) || blockstate1.is(Blocks.FROSTED_ICE)) {
//                    return true;
//                }
//            }
//        }
//
//        return super.canSustainPlant(state, world, pos, facing, plantable);
//    }
}
