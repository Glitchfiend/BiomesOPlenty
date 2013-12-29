package biomesoplenty.common.entities.projectiles.dispenser;

import net.minecraft.dispenser.BehaviorProjectileDispense;
import net.minecraft.dispenser.iPosition;
import net.minecraft.entity.IProjectile;
import net.minecraft.world.World;
import biomesoplenty.common.entities.projectiles.EntityDart;

public class DispenserBehaviourDart extends BehaviorProjectileDispense
{
	@Override
	protected IProjectile getProjectileEntity(World world, iPosition iPosition)
	{
		return new EntityDart(world, iPosition.getX(), iPosition.getY(), iPosition.getZ());
	}
}
