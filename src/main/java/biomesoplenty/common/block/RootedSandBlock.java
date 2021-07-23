package biomesoplenty.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.common.PlantType;

import java.util.Random;

public class RootedSandBlock extends Block implements BonemealableBlock
{
    public RootedSandBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public boolean canSustainPlant(BlockState state, BlockGetter world, BlockPos pos, Direction facing, net.minecraftforge.common.IPlantable plantable)
    {
        PlantType type = plantable.getPlantType(world, pos.relative(facing));

        if (type == PlantType.DESERT)      return true;
        else if (type == PlantType.CAVE)   return true;
        else if (type == PlantType.BEACH)
        {
            for(Direction direction : Direction.Plane.HORIZONTAL) {
                BlockState blockstate1 = world.getBlockState(pos.relative(direction));
                FluidState fluidstate = world.getFluidState(pos.relative(direction));
                if (fluidstate.is(FluidTags.WATER) || blockstate1.is(Blocks.FROSTED_ICE)) {
                    return true;
                }
            }
        }

        return super.canSustainPlant(state, world, pos, facing, plantable);
    }

    @Override
    public boolean isValidBonemealTarget(BlockGetter p_154366_, BlockPos p_154367_, BlockState p_154368_, boolean p_154369_) {
        return p_154366_.getBlockState(p_154367_.below()).isAir();
    }

    @Override
    public boolean isBonemealSuccess(Level p_154371_, Random p_154372_, BlockPos p_154373_, BlockState p_154374_) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel p_154361_, Random p_154362_, BlockPos p_154363_, BlockState p_154364_) {
        p_154361_.setBlockAndUpdate(p_154363_.below(), Blocks.HANGING_ROOTS.defaultBlockState());
    }
}
