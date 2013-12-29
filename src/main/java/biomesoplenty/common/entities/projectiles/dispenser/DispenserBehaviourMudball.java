package biomesoplenty.common.entities.projectiles.dispenser;

import net.minecraft.dispenser.BehaviorProjectileDispense;
import net.minecraft.dispenser.IPosition;
import net.minecraft.entity.IProjectile;
import net.minecraft.world.World;
import biomesoplenty.common.entities.projectiles.EntityMudball;

public class DispenserBehaviourMudball extends BehaviorProjectileDispense
{
	@Override
	protected IProjectile getProjectileEntity(World world, IPosition iPosition)
	{
		return new EntityMudball(world, iPosition.getX(), iPosition.getY(), iPosition.getZ());
	}
}
