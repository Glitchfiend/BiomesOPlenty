package biomesoplenty.common.entities.projectiles.dispenser;

import net.minecraft.dispenser.BehaviorProjectileDispense;
import net.minecraft.dispenser.iPosition;
import net.minecraft.entity.IProjectile;
import net.minecraft.world.World;
import biomesoplenty.common.entities.projectiles.EntityMudball;

public class DispenserBehaviourMudball extends BehaviorProjectileDispense
{
	@Override
	protected IProjectile getProjectileEntity(World world, iPosition par2IPosition)
	{
		return new EntityMudball(world, par2IPosition.getX(), par2IPosition.getY(), par2IPosition.getZ());
	}
}
