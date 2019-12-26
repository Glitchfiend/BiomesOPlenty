package biomesoplenty.common.item;

import biomesoplenty.common.entity.item.BoatEntityBOP;
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
    private final BoatEntityBOP.Type type;

    public DispenserBoatBehaviorBOP(BoatEntityBOP.Type type) {
        this.type = type;
    }

    public ItemStack dispenseStack(IBlockSource iBlockSource, ItemStack stack) {
        Direction direction = iBlockSource.getBlockState().get(DispenserBlock.FACING);
        World world = iBlockSource.getWorld();
        double x = iBlockSource.getX() + (double) ((float) direction.getXOffset() * 1.125f);
        double y = iBlockSource.getY() + (double) ((float) direction.getYOffset() * 1.125f);
        double z = iBlockSource.getZ() + (double) ((float) direction.getZOffset() * 1.125f);
        BlockPos pos = iBlockSource.getBlockPos().offset(direction);
        double adjustY;
        if (world.getFluidState(pos).isTagged(FluidTags.WATER)) {
            adjustY = 1d;
        } else {
            if (!world.getBlockState(pos).isAir() || !world.getFluidState(pos.down()).isTagged(FluidTags.WATER)) {
                return this.defaultDispenseItemBehavior.dispense(iBlockSource, stack);
            }
            adjustY = 0d;
        }
        BoatEntityBOP boat = new BoatEntityBOP(world, x, y + adjustY, z);
        boat.setBoatType(this.type);
        boat.rotationYaw = direction.getHorizontalAngle();
        world.addEntity(boat);
        stack.shrink(1);
        return stack;
    }

    protected void playDispenseSound(IBlockSource iBlockSource) {
        iBlockSource.getWorld().playEvent(1000, iBlockSource.getBlockPos(), 0);
    }
}

