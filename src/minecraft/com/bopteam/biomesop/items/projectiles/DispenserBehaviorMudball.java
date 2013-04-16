package com.bopteam.biomesop.items.projectiles;

import net.minecraft.dispenser.BehaviorProjectileDispense;
import net.minecraft.dispenser.IPosition;
import net.minecraft.entity.IProjectile;
import net.minecraft.world.World;

public class DispenserBehaviorMudball extends BehaviorProjectileDispense
{
	protected IProjectile getProjectileEntity(World par1World, IPosition par2IPosition)
    {
        return new EntityMudball(par1World, par2IPosition.getX(), par2IPosition.getY(), par2IPosition.getZ());
    }
}
