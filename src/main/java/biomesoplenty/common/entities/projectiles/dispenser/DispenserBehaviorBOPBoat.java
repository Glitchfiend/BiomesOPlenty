package biomesoplenty.common.entities.projectiles.dispenser;

import biomesoplenty.common.entities.item.EntityBOPBoat;
import net.minecraft.block.BlockDispenser;
import net.minecraft.block.material.Material;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DispenserBehaviorBOPBoat extends BehaviorDefaultDispenseItem
{
    private final BehaviorDefaultDispenseItem dispenseBehavior = new BehaviorDefaultDispenseItem();
    private final EntityBOPBoat.Type boatType;

    public DispenserBehaviorBOPBoat(EntityBOPBoat.Type boatTypeIn)
    {
        this.boatType = boatTypeIn;
    }

    public ItemStack dispenseStack(IBlockSource source, ItemStack stack)
    {
        EnumFacing enumfacing = (EnumFacing)source.getBlockState().getValue(BlockDispenser.FACING);
        World world = source.getWorld();
        double d0 = source.getX() + (double)((float)enumfacing.getFrontOffsetX() * 1.125F);
        double d1 = source.getY() + (double)((float)enumfacing.getFrontOffsetY() * 1.125F);
        double d2 = source.getZ() + (double)((float)enumfacing.getFrontOffsetZ() * 1.125F);
        BlockPos blockpos = source.getBlockPos().offset(enumfacing);
        Material material = world.getBlockState(blockpos).getMaterial();
        double d3;

        if (Material.WATER.equals(material))
        {
            d3 = 1.0D;
        }
        else
        {
            if (!Material.AIR.equals(material) || !Material.WATER.equals(world.getBlockState(blockpos.down()).getMaterial()))
            {
                return this.dispenseBehavior.dispense(source, stack);
            }

            d3 = 0.0D;
        }

        EntityBOPBoat entitybopboat = new EntityBOPBoat(world, d0, d1 + d3, d2);
        entitybopboat.setBoatType(this.boatType);
        entitybopboat.rotationYaw = enumfacing.getHorizontalAngle();
        world.spawnEntity(entitybopboat);
        stack.shrink(1);
        return stack;
    }

    protected void playDispenseSound(IBlockSource source)
    {
        source.getWorld().playEvent(1000, source.getBlockPos(), 0);
    }
}