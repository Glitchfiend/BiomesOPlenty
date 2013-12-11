package biomesoplenty.handlers;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import biomesoplenty.api.Blocks;

public class MovementHandler 
{
	@ForgeSubscribe
	public void onEntityLivingUpdate(LivingUpdateEvent event) 
	{
		EntityLivingBase entity = event.entityLiving;

		World world = entity.worldObj;

		int x = MathHelper.floor_double(entity.posX);
		int y = MathHelper.floor_double(entity.boundingBox.minY);
		int z = MathHelper.floor_double(entity.posZ);

		int blockID = world.getBlockId(x, y, z);

		if (blockID == Blocks.puddle.get().blockID)
		{
			if ((entity.motionX > 0 || entity.motionX < 0) || (entity.motionZ > 0 || entity.motionZ < 0))
			{
				if (event.entityLiving.isCollidedVertically)
				{
					if (world.rand.nextInt(2) == 0)
					{
						if (world.rand.nextInt(2) == 0)
						{
							float f = MathHelper.sqrt_double(entity.motionX * entity.motionX * 0.20000000298023224D + entity.motionY * entity.motionY + entity.motionZ * entity.motionZ * 0.20000000298023224D) * 0.2F;

							if (f > 1.0F)
							{
								f = 1.0F;
							}
							
							entity.playSound("liquid.splash", f, 1.0F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.4F);
						}
						world.spawnParticle("splash", entity.posX + ((double)world.rand.nextFloat() - 0.5D) * (double)entity.width, entity.boundingBox.minY + 0.1D, entity.posZ + ((double)world.rand.nextFloat() - 0.5D) * (double)entity.width, -entity.motionX, 0.6D, -entity.motionZ);
					}
				}
			}
		}
	}
}
