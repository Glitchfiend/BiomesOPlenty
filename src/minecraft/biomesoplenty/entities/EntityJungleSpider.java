package biomesoplenty.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class EntityJungleSpider extends EntitySpider
{
	public EntityJungleSpider(World par1World)
	{
		super(par1World);
		this.setSize(0.4F, 0.3F);
	}

    @Override
	protected void func_110147_ax()
    {
        super.func_110147_ax();
        //Max health
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(8.0D);
        //Movement speed
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(1.1D);
    }

	@Override
	public boolean attackEntityAsMob(Entity par1Entity)
	{
		if (super.attackEntityAsMob(par1Entity))
		{
			if (par1Entity instanceof EntityLivingBase)
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
					((EntityLivingBase)par1Entity).addPotionEffect(new PotionEffect(Potion.blindness.id, var2 * 20, 0));
				}
			}

			return true;
		} else
			return false;
	}
}
