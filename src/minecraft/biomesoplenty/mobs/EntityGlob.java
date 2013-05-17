package biomesoplenty.mobs;

import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.Items;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;

public class EntityGlob extends EntityLiving implements IMob
{
    /** Chances for Globs to spawn in swamps for every moon phase. */
    private static final float[] spawnChances = new float[] {1.0F, 0.75F, 0.5F, 0.25F, 0.0F, 0.25F, 0.5F, 0.75F};
    public float field_70813_a;
    public float field_70811_b;
    public float field_70812_c;

    /** the time between each jump of the Glob */
    private int GlobJumpDelay = 0;

    public EntityGlob(World par1World)
    {
        super(par1World);
        this.texture = "/mods/BiomesOPlenty/textures/mobs/glob.png";
        int i = 1 << this.rand.nextInt(3);
        this.yOffset = 0.0F;
        this.GlobJumpDelay = this.rand.nextInt(20) + 10;
        this.setGlobSize(i);
    }

    protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(16, new Byte((byte)1));
    }

    protected void setGlobSize(int par1)
    {
        this.dataWatcher.updateObject(16, new Byte((byte)par1));
        this.setSize(0.6F * (float)par1, 0.6F * (float)par1);
        this.setPosition(this.posX, this.posY, this.posZ);
        this.setEntityHealth(this.getMaxHealth());
        this.experienceValue = par1;
    }

    public int getMaxHealth()
    {
        int i = this.getGlobSize();
        return i * i;
    }

    /**
     * Returns the size of the Glob.
     */
    public int getGlobSize()
    {
        return this.dataWatcher.getWatchableObjectByte(16);
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeEntityToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setInteger("Size", this.getGlobSize() - 1);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readEntityFromNBT(par1NBTTagCompound);
        this.setGlobSize(par1NBTTagCompound.getInteger("Size") + 1);
    }

    /**
     * Returns the name of the sound played when the Glob jumps.
     */
    protected String getJumpSound()
    {
        return "mob.slime." + (this.getGlobSize() > 1 ? "big" : "small");
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        if (!this.worldObj.isRemote && this.worldObj.difficultySetting == 0 && this.getGlobSize() > 0)
        {
            this.isDead = true;
        }

        this.field_70811_b += (this.field_70813_a - this.field_70811_b) * 0.5F;
        this.field_70812_c = this.field_70811_b;
        boolean flag = this.onGround;
        super.onUpdate();
        int i;

        if (this.onGround && !flag)
        {
            i = this.getGlobSize();

            for (int j = 0; j < i * 8; ++j)
            {
                float f = this.rand.nextFloat() * (float)Math.PI * 2.0F;
                float f1 = this.rand.nextFloat() * 0.5F + 0.5F;
                float f2 = MathHelper.sin(f) * (float)i * 0.5F * f1;
                float f3 = MathHelper.cos(f) * (float)i * 0.5F * f1;
                BiomesOPlenty.proxy.spawnParticle("mud", this.posX + (double)f2, this.boundingBox.minY, this.posZ + (double)f3);
            }

            if (this.makesSoundOnLand())
            {
                this.playSound(this.getJumpSound(), this.getSoundVolume(), ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F) / 0.8F);
            }

            this.field_70813_a = -0.5F;
        }
        else if (!this.onGround && flag)
        {
            this.field_70813_a = 1.0F;
        }

        this.func_70808_l();

        if (this.worldObj.isRemote)
        {
            i = this.getGlobSize();
            this.setSize(0.6F * (float)i, 0.6F * (float)i);
        }
    }

    protected void updateEntityActionState()
    {
        this.despawnEntity();
        EntityPlayer entityplayer = this.worldObj.getClosestVulnerablePlayerToEntity(this, 16.0D);

        if (entityplayer != null)
        {
            this.faceEntity(entityplayer, 10.0F, 20.0F);
        }

        if (this.onGround && this.GlobJumpDelay-- <= 0)
        {
            this.GlobJumpDelay = this.getJumpDelay();

            if (entityplayer != null)
            {
                this.GlobJumpDelay /= 3;
            }

            this.isJumping = true;

            if (this.makesSoundOnJump())
            {
                this.playSound(this.getJumpSound(), this.getSoundVolume(), ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F) * 0.8F);
            }

            this.moveStrafing = 1.0F - this.rand.nextFloat() * 2.0F;
            this.moveForward = (float)(1 * this.getGlobSize());
        }
        else
        {
            this.isJumping = false;

            if (this.onGround)
            {
                this.moveStrafing = this.moveForward = 0.0F;
            }
        }
    }

    protected void func_70808_l()
    {
        this.field_70813_a *= 0.6F;
    }

    /**
     * Gets the amount of time the Glob needs to wait between jumps.
     */
    protected int getJumpDelay()
    {
        return this.rand.nextInt(20) + 10;
    }

    protected EntityGlob createInstance()
    {
        return new EntityGlob(this.worldObj);
    }

    /**
     * Will get destroyed next tick.
     */
    public void setDead()
    {
        int i = this.getGlobSize();

        if (!this.worldObj.isRemote && i > 1 && this.getHealth() <= 0)
        {
            int j = 2 + this.rand.nextInt(3);

            for (int k = 0; k < j; ++k)
            {
                float f = ((float)(k % 2) - 0.5F) * (float)i / 4.0F;
                float f1 = ((float)(k / 2) - 0.5F) * (float)i / 4.0F;
                EntityGlob entityGlob = this.createInstance();
                entityGlob.setGlobSize(i / 2);
                entityGlob.setLocationAndAngles(this.posX + (double)f, this.posY + 0.5D, this.posZ + (double)f1, this.rand.nextFloat() * 360.0F, 0.0F);
                this.worldObj.spawnEntityInWorld(entityGlob);
            }
        }

        super.setDead();
    }

    /**
     * Called by a player entity when they collide with an entity
     */
    public void onCollideWithPlayer(EntityPlayer par1EntityPlayer)
    {
        if (this.canDamagePlayer())
        {
            int i = this.getGlobSize();

            if (this.canEntityBeSeen(par1EntityPlayer) && this.getDistanceSqToEntity(par1EntityPlayer) < 0.6D * (double)i * 0.6D * (double)i && par1EntityPlayer.attackEntityFrom(DamageSource.causeMobDamage(this), this.getAttackStrength()))
            {
                this.playSound("mob.attack", 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
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
    protected String getHurtSound()
    {
        return "mob.slime." + (this.getGlobSize() > 1 ? "big" : "small");
    }

    /**
     * Returns the sound this mob makes on death.
     */
    protected String getDeathSound()
    {
        return "mob.slime." + (this.getGlobSize() > 1 ? "big" : "small");
    }

    /**
     * Returns the item ID for the item the mob drops on death.
     */
    protected int getDropItemId()
    {
        return this.getGlobSize() == 1 ? Item.slimeBall.itemID : Items.mudball.get().itemID;
    }

    /**
     * Checks if the entity's current position is a valid location to spawn this entity.
     */
    public boolean getCanSpawnHere()
    {
        Chunk chunk = this.worldObj.getChunkFromBlockCoords(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posZ));

        if (this.worldObj.getWorldInfo().getTerrainType().handleSlimeSpawnReduction(rand, worldObj))
        {
            return false;
        }
        else
        {
            if (this.getGlobSize() == 1 || this.worldObj.difficultySetting > 0)
            {
                BiomeGenBase biomegenbase = this.worldObj.getBiomeGenForCoords(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posZ));

                if (biomegenbase == BiomeGenBase.swampland && this.posY > 50.0D && this.posY < 70.0D && this.rand.nextFloat() < 0.5F && this.rand.nextFloat() < spawnChances[this.worldObj.getMoonPhase()] && this.worldObj.getBlockLightValue(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ)) <= this.rand.nextInt(8))
                {
                    return super.getCanSpawnHere();
                }

                if (this.rand.nextInt(10) == 0 && chunk.getRandomWithSeed(987234911L).nextInt(10) == 0 && this.posY < 40.0D)
                {
                    return super.getCanSpawnHere();
                }
            }

            return false;
        }
    }

    /**
     * Returns the volume for the sounds this mob makes.
     */
    protected float getSoundVolume()
    {
        return 0.4F * (float)this.getGlobSize();
    }

    /**
     * The speed it takes to move the entityliving's rotationPitch through the faceEntity method. This is only currently
     * use in wolves.
     */
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
