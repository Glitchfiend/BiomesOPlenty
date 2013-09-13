package biomesoplenty.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import biomesoplenty.api.Items;
import biomesoplenty.api.Potions;

public class EntityPhantom extends EntityMob
{
	public EntityPhantom(World world) 
	{
		super(world);
	}

    @Override
	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();

        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(20.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.45D);
    }
    
    @Override
    public void onLivingUpdate()
    {
        super.onLivingUpdate();

        if (!this.onGround && this.motionY < 0.0D)
        {
            this.motionY *= 0.6D;
        }
        
		this.fallDistance = 0.0F;
    }

    @Override
    protected void fall(float par1) {}
    
    @Override
    public void onEntityUpdate()
    {
    	super.onEntityUpdate();

    	for (int i = 0; i < 9; i++)
    	{
    		this.worldObj.spawnParticle("mobSpell", this.posX + (this.rand.nextDouble()) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height - (double)this.yOffset, this.posZ + (this.rand.nextDouble()) * (double)this.width, 0, 0, 0);
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
					((EntityLivingBase)par1Entity).addPotionEffect(new PotionEffect(Potions.possession.get().id, 30, 0));
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
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
	{
		for (int i = 0; i < 9; i++)
		{
			this.worldObj.spawnParticle("mobSpell", this.posX + (this.rand.nextDouble()) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height - (double)this.yOffset, this.posZ + (this.rand.nextDouble()) * (double)this.width, 66, 0, 0);
		}

		return super.attackEntityFrom(par1DamageSource, par2);
	}

	@Override
    protected void dropFewItems(boolean par1, int par2)
    {
        super.dropFewItems(par1, par2);

        if (par1 && (this.rand.nextInt(3) == 0 || this.rand.nextInt(1 + par2) > 0))
        {
            this.entityDropItem(new ItemStack(Items.miscItems.get().itemID, 1, 16), 1);
        }
    }
	
	@Override
	public boolean getCanSpawnHere()
	{
		return this.worldObj.difficultySetting > 0 && this.worldObj.checkNoEntityCollision(this.boundingBox) && this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).isEmpty() && !this.worldObj.isAnyLiquid(this.boundingBox);
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
