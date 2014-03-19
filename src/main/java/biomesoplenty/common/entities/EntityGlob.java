package biomesoplenty.common.entities;

import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.BOPItemHelper;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;

public class EntityGlob extends EntityLiving implements IMob
{
	/** Chances for Globs to spawn in swamps for every moon phase. */
	private static final float[] spawnChances = new float[] {1.0F, 0.75F, 0.5F, 0.25F, 0.0F, 0.25F, 0.5F, 0.75F};
	public float squishAmount;
	public float squishFactor;
	public float prevSquishFactor;

	/** the time between each jump of the Glob */
	private int globJumpDelay = 0;

	public EntityGlob(World par1World)
	{
		super(par1World);
		int i = 1 << rand.nextInt(3);
		yOffset = 0.0F;
		globJumpDelay = rand.nextInt(20) + 10;
		this.setGlobSize(i);
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		dataWatcher.addObject(16, new Byte((byte)1));
	}

	protected void setGlobSize(int par1)
	{
        this.dataWatcher.updateObject(16, new Byte((byte)par1));
        this.setSize(0.6F * (float)par1, 0.6F * (float)par1);
        this.setPosition(this.posX, this.posY, this.posZ);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue((double)(par1 * par1));
        this.setHealth(this.getMaxHealth());
        this.experienceValue = par1;
	}

	 public int getGlobSize()
	 {
		 return dataWatcher.getWatchableObjectByte(16);
	 }

	 @Override
	 public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
	 {
		 super.writeEntityToNBT(par1NBTTagCompound);
		 par1NBTTagCompound.setInteger("Size", this.getGlobSize() - 1);
	 }

	 @Override
	 public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
	 {
		 super.readEntityFromNBT(par1NBTTagCompound);
		 this.setGlobSize(par1NBTTagCompound.getInteger("Size") + 1);
	 }

	 protected String getJumpSound()
	 {
		 return "mob.slime." + (this.getGlobSize() > 1 ? "big" : "small");
	 }

	 /**
	  * Called to update the entity's position/logic.
	  */
	 @Override
	 public void onUpdate()
	 {
		 if (!worldObj.isRemote && worldObj.difficultySetting == EnumDifficulty.PEACEFUL && this.getGlobSize() > 0)
		 {
			 isDead = true;
		 }

		 squishFactor += (squishAmount - squishFactor) * 0.5F;
		 prevSquishFactor = squishFactor;
		 boolean flag = onGround;
		 super.onUpdate();
		 int i;

		 if (onGround && !flag)
		 {
			 i = this.getGlobSize();

			 for (int j = 0; j < i * 8; ++j)
			 {
				 float f = rand.nextFloat() * (float)Math.PI * 2.0F;
				 float f1 = rand.nextFloat() * 0.5F + 0.5F;
				 float f2 = MathHelper.sin(f) * i * 0.5F * f1;
				 float f3 = MathHelper.cos(f) * i * 0.5F * f1;
				 BiomesOPlenty.proxy.spawnParticle("mud", posX + f2, boundingBox.minY, posZ + f3);
			 }

			 if (this.makesSoundOnLand())
			 {
				 this.playSound(this.getJumpSound(), this.getSoundVolume(), ((rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F) / 0.8F);
			 }

			 squishAmount = -0.5F;
		 }
		 else if (!onGround && flag)
		 {
			 squishAmount = 1.0F;
		 }

		 this.alterSquishAmount();

		 if (worldObj.isRemote)
		 {
			 i = this.getGlobSize();
			 this.setSize(0.6F * i, 0.6F * i);
		 }
	 }

	 @Override
	 protected void updateEntityActionState()
	 {
		 this.despawnEntity();
		 EntityPlayer entityplayer = worldObj.getClosestVulnerablePlayerToEntity(this, 16.0D);

		 if (entityplayer != null)
		 {
			 this.faceEntity(entityplayer, 10.0F, 20.0F);
		 }

		 if (onGround && globJumpDelay-- <= 0)
		 {
			 globJumpDelay = this.getJumpDelay();

			 if (entityplayer != null)
			 {
				 globJumpDelay /= 3;
			 }

			 isJumping = true;

			 if (this.makesSoundOnJump())
			 {
				 this.playSound(this.getJumpSound(), this.getSoundVolume(), ((rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F) * 0.8F);
			 }

			 moveStrafing = 1.0F - rand.nextFloat() * 2.0F;
			 moveForward = 1 * this.getGlobSize();
		 }
		 else
		 {
			 isJumping = false;

			 if (onGround)
			 {
				 moveStrafing = moveForward = 0.0F;
			 }
		 }
	 }

	 protected void alterSquishAmount()
	 {
		 squishAmount *= 0.6F;
	 }

	 /**
	  * Gets the amount of time the Glob needs to wait between jumps.
	  */
	 protected int getJumpDelay()
	 {
		 return rand.nextInt(20) + 10;
	 }

	 protected EntityGlob createInstance()
	 {
		 return new EntityGlob(worldObj);
	 }

	 /**
	  * Will get destroyed next tick.
	  */
	 @Override
	 public void setDead()
	 {
		 int i = this.getGlobSize();

		 //getHealth() == getHealth
		 if (!worldObj.isRemote && i > 1 && this.getHealth() <= 0)
		 {
			 int j = 2 + rand.nextInt(3);

			 for (int k = 0; k < j; ++k)
			 {
				 float f = (k % 2 - 0.5F) * i / 4.0F;
				 float f1 = (k / 2 - 0.5F) * i / 4.0F;
				 EntityGlob entityGlob = this.createInstance();
				 entityGlob.setGlobSize(i / 2);
				 entityGlob.setLocationAndAngles(posX + f, posY + 0.5D, posZ + f1, rand.nextFloat() * 360.0F, 0.0F);
				 worldObj.spawnEntityInWorld(entityGlob);
			 }
		 }

		 super.setDead();
	 }

	 /**
	  * Called by a player entity when they collide with an entity
	  */
	 @Override
	 public void onCollideWithPlayer(EntityPlayer par1EntityPlayer)
	 {
		 if (this.canDamagePlayer())
		 {
			 int i = this.getGlobSize();

			 if (this.canEntityBeSeen(par1EntityPlayer) && this.getDistanceSqToEntity(par1EntityPlayer) < 0.6D * i * 0.6D * i && par1EntityPlayer.attackEntityFrom(DamageSource.causeMobDamage(this), this.getAttackStrength()))
			 {
				 this.playSound("mob.attack", 1.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
			 }
		 }
	 }

	 /**
	  * Indicates weather the Glob is able to damage the player (based upon the Glob's size)
	  */
	 protected boolean canDamagePlayer()
	 {
		 return this.getGlobSize() > 1;
	 }

	 /**
	  * Gets the amount of damage dealt to the player when "attacked" by the Glob.
	  */
	 protected int getAttackStrength()
	 {
		 return this.getGlobSize();
	 }

	 /**
	  * Returns the sound this mob makes when it is hurt.
	  */
	 @Override
	 protected String getHurtSound()
	 {
		 return "mob.slime." + (this.getGlobSize() > 1 ? "big" : "small");
	 }

	 /**
	  * Returns the sound this mob makes on death.
	  */
	 @Override
	 protected String getDeathSound()
	 {
		 return "mob.slime." + (this.getGlobSize() > 1 ? "big" : "small");
	 }
	 
	@Override
	protected void dropFewItems(boolean par1, int par2)
	{
		int var3 = rand.nextInt(3) + rand.nextInt(1 + par2);
		
		if (rand.nextInt(1000) == 0)
		{
			this.entityDropItem(new ItemStack(BOPItemHelper.get("bopDiscMud")), 0.0F);
		}

		for (int var4 = 0; var4 < var3; ++var4)
		{
			this.entityDropItem(new ItemStack(BOPItemHelper.get("mudball"), 1, 1), 0.0F);
		}
		

		//TODO: dropItem()
		this.dropItem(Items.slime_ball, 1);
	}

	 /**
	  * Checks if the entity's current position is a valid location to spawn this entity.
	  */
	 @Override
	 public boolean getCanSpawnHere()
	 {
		 Chunk chunk = worldObj.getChunkFromBlockCoords(MathHelper.floor_double(posX), MathHelper.floor_double(posZ));

		 if (worldObj.getWorldInfo().getTerrainType().handleSlimeSpawnReduction(rand, worldObj))
			 return false;
		 else
		 {
			 if (this.getGlobSize() == 1 || worldObj.difficultySetting != EnumDifficulty.PEACEFUL)
			 {
				 BiomeGenBase biomegenbase = worldObj.getBiomeGenForCoords(MathHelper.floor_double(posX), MathHelper.floor_double(posZ));

				 if (biomegenbase == BiomeGenBase.swampland && posY > 50.0D && posY < 70.0D && rand.nextFloat() < 0.5F && rand.nextFloat() < this.worldObj.getCurrentMoonPhaseFactor() && worldObj.getBlockLightValue(MathHelper.floor_double(posX), MathHelper.floor_double(posY), MathHelper.floor_double(posZ)) <= rand.nextInt(8))
					 return super.getCanSpawnHere();

				 if (rand.nextInt(10) == 0 && chunk.getRandomWithSeed(987234911L).nextInt(10) == 0 && posY < 40.0D)
					 return super.getCanSpawnHere();
			 }

			 return false;
		 }
	 }

	 /**
	  * Returns the volume for the sounds this mob makes.
	  */
	 @Override
	 protected float getSoundVolume()
	 {
		 return 0.4F * this.getGlobSize();
	 }

	 /**
	  * The speed it takes to move the entityliving's rotationPitch through the faceEntity method. This is only currently
	  * use in wolves.
	  */
	 @Override
	 public int getVerticalFaceSpeed()
	 {
		 return 0;
	 }

	 /**
	  * Returns true if the Glob makes a sound when it jumps (based upon the Glob's size)
	  */
	 protected boolean makesSoundOnJump()
	 {
		 return this.getGlobSize() > 0;
	 }

	 /**
	  * Returns true if the Glob makes a sound when it lands after a jump (based upon the Glob's size)
	  */
	 protected boolean makesSoundOnLand()
	 {
		 return this.getGlobSize() > 2;
	 }
}


