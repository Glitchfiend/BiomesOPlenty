package biomesoplenty.common.item;

import biomesoplenty.common.entity.item.BoatEntityBOP;
import biomesoplenty.common.entity.item.BoatEntityBOP.BoatModel;
import net.minecraft.block.DispenserBlock;
import net.minecraft.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DispenserBoatBehaviorBOP extends DefaultDispenseItemBehavior {
    private final DefaultDispenseItemBehavior defaultDispenseItemBehavior = new DefaultDispenseItemBehavior();
    private final BoatModel model;

    public DispenserBoatBehaviorBOP(BoatModel model) {
        this.model = model;
    }

    @Override
    public ItemStack execute(IBlockSource source, ItemStack stack) {
        Direction direction = source.getBlockState().getValue(DispenserBlock.FACING);
        World world = source.getLevel();
        double d0 = source.x() + (double) ((float) direction.getStepX() * 1.125f);
        double d1 = source.y() + (double) ((float) direction.getStepY() * 1.125f);
        double d2 = source.z() + (double) ((float) direction.getStepZ() * 1.125f);
        BlockPos blockpos = source.getPos().relative(direction);
        double d3;
        if (world.getFluidState(blockpos).is(FluidTags.WATER)) {
            d3 = 1d;
        } else {
            if (!world.getBlockState(blockpos).isAir() || !world.getFluidState(blockpos.below()).is(FluidTags.WATER)) {
                return this.defaultDispenseItemBehavior.dispense(source, stack);
            }
            d3 = 0d;
        }
        BoatEntityBOP boat = new BoatEntityBOP(world, d0, d1 + d3, d2).withModel(this.model);
        boat.yRot = direction.toYRot();
        world.addFreshEntity(boat);
        stack.shrink(1);
        return stack;
    }
}
