package biomesoplenty.common.entities.projectiles.dispenser;

import biomesoplenty.common.entities.projectiles.EntityDart;
import net.minecraft.dispenser.BehaviorProjectileDispense;
import net.minecraft.dispenser.IPosition;
import net.minecraft.entity.IProjectile;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class DispenserBehaviorDart extends BehaviorProjectileDispense
{
	@Override
    protected IProjectile getProjectileEntity(World world, IPosition position, ItemStack stack)
	{
		return new EntityDart(world, position.getX(), position.getY(), position.getZ());
	}
}