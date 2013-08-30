package biomesoplenty.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import biomesoplenty.api.Potions;

public class EntityPhantom extends EntityMob
{
	public EntityPhantom(World world) 
	{
		super(world);
	}

    @Override
	protected void func_110147_ax()
    {
        super.func_110147_ax();
        //Max health
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(20.0D);
        //Movement speed
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.45D);
    }
    
    @Override
    public void onEntityUpdate()
    {
    	super.onEntityUpdate();

    	for (int i = 0; i < 3; i++)
    	{
    		this.worldObj.spawnParticle("mobSpell", this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height - (double)this.yOffset, this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width, 0, 0, 0);
    	}
    }
    
	@Override
	public boolean attackEntityAsMob(Entity par1Entity)
	{
		if (super.attackEntityAsMob(par1Entity))
		{
			if (par1Entity instanceof EntityLivingBase)
			{
				if (worldObj.difficultySetting > 1)
				{
					((EntityLivingBase)par1Entity).addPotionEffect(new PotionEffect(Potions.possession.get().id, 40, 0));
				}
			}

			return true;
		} 
		else
		{
			return false;
		}
	}
	
	@Override
    protected String getLivingSound()
    {
        return "biomesoplenty:mob.phantom.say";
    }
	
	@Override
    protected String getHurtSound()
    {
        return "biomesoplenty:mob.phantom.hurt";
    }

	@Override
    protected String getDeathSound()
    {
        return "biomesoplenty:mob.phantom.death";
    }
}
