package biomesoplenty.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SandBlock;
import net.minecraft.fluid.IFluidState;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.PlantType;

public class WhiteSandBlock extends SandBlock
{
    public WhiteSandBlock(int p_i48338_1_, Block.Properties properties)
    {
        super(p_i48338_1_, properties);
    }

    @Override
    public boolean canSustainPlant(BlockState state, IBlockReader world, BlockPos pos, Direction facing, net.minecraftforge.common.IPlantable plantable)
    {
        PlantType type = plantable.getPlantType(world, pos.relative(facing));

        switch (type) {
            case Desert: return true;
            case Nether: return false;
            case Crop: return false;
            case Cave: return true;
            case Plains: return false;
            case Water: return false;
            case Beach:
                for(Direction direction : Direction.Plane.HORIZONTAL) {
                    IFluidState fluidState = world.getFluidState(pos.relative(direction));
                    if (fluidState.is(FluidTags.WATER)) {
                        return true;
                    }
                }
        }
        return false;
    }
}
