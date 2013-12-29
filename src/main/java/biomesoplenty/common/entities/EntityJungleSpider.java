package biomesoplenty.common.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityJungleSpider extends EntitySpider
{
	public EntityJungleSpider(World par1World)
	{
		super(par1World);
		this.setSize(0.4F, 0.3F);
	}

    @Override
	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        //Max health
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(6.0D);
        //Movement speed
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.95D);
    }

	@Override
	public boolean attackEntityAsMob(Entity par1Entity)
	{
		if (super.attackEntityAsMob(par1Entity))
		{
			if (par1Entity instanceof EntityLivingBase)
			{
                byte b0 = 0;

                if (this.worldObj.difficultySetting == EnumDifficulty.NORMAL)
                {
                    b0 = 7;
                }
                else if (this.worldObj.difficultySetting == EnumDifficulty.HARD)
                {
                    b0 = 15;
                }

                if (b0 > 0)
                {
                    ((EntityLivingBase)par1Entity).addPotionEffect(new PotionEffect(Potion.blindness.id, b0 * 20, 0));
                }
			}

			return true;
		} 
		else
		{
			return false;
		}
	}
}
