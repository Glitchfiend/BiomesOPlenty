package biomesoplenty.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class EntityJungleSpider extends EntitySpider
{
	public EntityJungleSpider(World par1World)
	{
		super(par1World);
		//texture = "/mods/BiomesOPlenty/textures/mobs/junglespider.png";
		this.setSize(0.4F, 0.3F);
		//moveSpeed = 1.1F;
	}

	/*@Override
	public int getMaxHealth()
	{
		return 8;
	}*/

	/**
	 * How large the spider should be scaled.
	 */
	/*@Override
	public float spiderScaleAmount()
	{
		return 0.4F;
	}*/

	@Override
	public float getShadowSize()
	{
		return 0.0F;
	}

	@Override
	public boolean attackEntityAsMob(Entity par1Entity)
	{
		if (super.attackEntityAsMob(par1Entity))
		{
			if (par1Entity instanceof EntityLiving)
			{
				byte var2 = 0;

				if (worldObj.difficultySetting > 1)
				{
					if (worldObj.difficultySetting == 2)
					{
						var2 = 7;
					}
					else if (worldObj.difficultySetting == 3)
					{
						var2 = 15;
					}
				}

				if (var2 > 0)
				{
					((EntityLiving)par1Entity).addPotionEffect(new PotionEffect(Potion.blindness.id, var2 * 20, 0));
				}
			}

			return true;
		} else
			return false;
	}

	/**
	 * Initialize this creature.
	 */
	 /*@Override
	 public void initCreature() {}*/
}
