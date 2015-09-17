package biomesoplenty.common.entities;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityRosester extends EntityChicken
{
	public boolean field_70885_d = false;
	public float field_70886_e = 0.0F;
	public float destPos = 0.0F;
	public float field_70884_g;
	public float field_70888_h;
	public float field_70889_i = 1.0F;

	public int timeUntilNextEgg;

	public EntityRosester(World par1World)
	{
		super(par1World);
		this.setSize(0.3F, 0.7F);
		timeUntilNextEgg = rand.nextInt(6000) + 6000;
		float var2 = 0.25F;
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAIPanic(this, 0.38F));
		tasks.addTask(2, new EntityAIMate(this, var2));
		tasks.addTask(3, new EntityAITempt(this, 0.25F, Items.wheat_seeds, false));
		tasks.addTask(4, new EntityAIFollowParent(this, 0.28F));
		tasks.addTask(5, new EntityAIWander(this, var2));
		tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		tasks.addTask(7, new EntityAILookIdle(this));
	}

	@Override
	public boolean isAIEnabled()
	{
		return true;
	}

    @Override
	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();

        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(4.0D);
    }

	@Override
	public void onLivingUpdate()
	{
		super.onLivingUpdate();
		field_70888_h = field_70886_e;
		field_70884_g = destPos;
		destPos = (float)(destPos + (onGround ? -1 : 4) * 0.3D);

		if (destPos < 0.0F)
		{
			destPos = 0.0F;
		}

		if (destPos > 1.0F)
		{
			destPos = 1.0F;
		}

		if (!onGround && field_70889_i < 1.0F)
		{
			field_70889_i = 1.0F;
		}

		field_70889_i = (float)(field_70889_i * 0.9D);

		if (!onGround && motionY < 0.0D)
		{
			motionY *= 0.6D;
		}

		field_70886_e += field_70889_i * 2.0F;

		if (!this.isChild() && !worldObj.isRemote && --timeUntilNextEgg <= 0)
		{
			this.playSound("mob.chicken.plop", 1.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
			this.entityDropItem(new ItemStack(Items.dye, 1, 1), 0.0F);
			timeUntilNextEgg = rand.nextInt(6000) + 6000;
		}
		
        int i = MathHelper.floor_double(this.posX);
        int j = MathHelper.floor_double(this.posZ);
		
        for (i = 0; i < 4; ++i)
        {
            j = MathHelper.floor_double(this.posX + (double)((float)(i % 2 * 2 - 1) * 0.25F));
            int k = MathHelper.floor_double(this.posY);
            int l = MathHelper.floor_double(this.posZ + (double)((float)(i / 2 % 2 * 2 - 1) * 0.25F));

            if (!this.worldObj.isRemote && this.worldObj.isAirBlock(j, k, l) && this.worldObj.getBiomeGenForCoords(j, l).getFloatTemperature(j, k, l) > 0.3F && Blocks.red_flower.canPlaceBlockAt(this.worldObj, j, k, l) && this.worldObj.rand.nextInt(300) == 0)
            {
                this.worldObj.setBlock(j, k, l, Blocks.red_flower, 0, 2);
            }
        }
	}

	@Override
	protected void fall(float par1) {}

	@Override
	protected String getLivingSound()
	{
		return "mob.chicken.say";
	}

	@Override
	protected String getHurtSound()
	{
		return "mob.chicken.hurt";
	}

	@Override
	protected String getDeathSound()
	{
		return "mob.chicken.hurt";
	}

	@Override
	protected void func_145780_a(int par1, int par2, int par3, Block block)
	{
		this.playSound("mob.chicken.step", 0.15F, 1.0F);
	}

	@Override
	protected Item getDropItem()
	{
		return Items.feather;
	}

	@Override
	protected void dropFewItems(boolean par1, int par2)
	{
		int var3 = rand.nextInt(3) + rand.nextInt(1 + par2);

		for (int var4 = 0; var4 < var3; ++var4)
		{
			this.entityDropItem(new ItemStack(Items.dye, 1, 1), 0.0F);
		}

		if (this.isBurning())
		{
			this.dropItem(Items.cooked_chicken, 1);
		}
		else
		{
			this.dropItem(Items.chicken, 1);
		}
	}

	@Override
	public boolean isBreedingItem(ItemStack par1ItemStack)
	{
		return par1ItemStack != null && par1ItemStack.getItem() instanceof ItemSeeds;
	}

	@Override
	public EntityChicken createChild(EntityAgeable entityAgeable)
	{
		return new EntityRosester(worldObj);
	}
}
