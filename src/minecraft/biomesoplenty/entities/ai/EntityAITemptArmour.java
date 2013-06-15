package biomesoplenty.entities.ai;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class EntityAITemptArmour extends EntityAIBase
{
	/** The entity using this AI that is tempted by the player. */
	private EntityCreature temptedEntity;
	private float field_75282_b;
	private double field_75283_c;
	private double field_75280_d;
	private double field_75281_e;
	private double field_75278_f;
	private double field_75279_g;

	/** The player that is tempting the entity that is using this AI. */
	private EntityPlayer temptingPlayer;

	/**
	 * A counter that is decremented each time the shouldExecute method is called. The shouldExecute method will always
	 * return false if delayTemptCounter is greater than 0.
	 */
	private boolean field_75287_j;

	/**
	 * This field saves the ID of the items that can be used to breed entities with this behaviour.
	 */
	private int armourID;
	private int meta;

	/**
	 * Whether the entity using this AI will be scared by the tempter's sudden movement.
	 */
	private boolean scaredByPlayerMovement;
	private boolean field_75286_m;

	public EntityAITemptArmour(EntityCreature par1EntityCreature, float par2, int par3, int par4, boolean par5)
	{
		temptedEntity = par1EntityCreature;
		field_75282_b = par2;
		armourID = par3;
		meta = par4;
		scaredByPlayerMovement = par5;
		this.setMutexBits(3);
	}

	/**
	 * Returns whether the EntityAIBase should begin execution.
	 */
	@Override
	public boolean shouldExecute()
	{
		temptingPlayer = temptedEntity.worldObj.getClosestPlayerToEntity(temptedEntity, 10.0D);

		if (temptingPlayer == null)
			return false;
		else
		{
			ItemStack itemstack = temptingPlayer.inventory.armorInventory[3];
			return itemstack == null ? false : (itemstack.itemID == armourID) && (itemstack.getItemDamage() >= meta);
		}
	}

	/**
	 * Returns whether an in-progress EntityAIBase should continue executing
	 */
	@Override
	public boolean continueExecuting()
	{
		if (scaredByPlayerMovement)
		{
			if (temptedEntity.getDistanceSqToEntity(temptingPlayer) < 36.0D)
			{
				if (temptingPlayer.getDistanceSq(field_75283_c, field_75280_d, field_75281_e) > 0.010000000000000002D)
					return false;

				if (Math.abs(temptingPlayer.rotationPitch - field_75278_f) > 5.0D || Math.abs(temptingPlayer.rotationYaw - field_75279_g) > 5.0D)
					return false;
			}
			else
			{
				field_75283_c = temptingPlayer.posX;
				field_75280_d = temptingPlayer.posY;
				field_75281_e = temptingPlayer.posZ;
			}

			field_75278_f = temptingPlayer.rotationPitch;
			field_75279_g = temptingPlayer.rotationYaw;
		}

		return this.shouldExecute();
	}

	/**
	 * Execute a one shot task or start executing a continuous task
	 */
	@Override
	public void startExecuting()
	{
		field_75283_c = temptingPlayer.posX;
		field_75280_d = temptingPlayer.posY;
		field_75281_e = temptingPlayer.posZ;
		field_75287_j = true;
		field_75286_m = temptedEntity.getNavigator().getAvoidsWater();
		temptedEntity.getNavigator().setAvoidsWater(false);
	}

	/**
	 * Resets the task
	 */
	@Override
	public void resetTask()
	{
		temptingPlayer = null;
		temptedEntity.getNavigator().clearPathEntity();
		field_75287_j = false;
		temptedEntity.getNavigator().setAvoidsWater(field_75286_m);
	}

	/**
	 * Updates the task
	 */
	@Override
	public void updateTask()
	{
		temptedEntity.getLookHelper().setLookPositionWithEntity(temptingPlayer, 30.0F, temptedEntity.getVerticalFaceSpeed());

		if (temptedEntity.getDistanceSqToEntity(temptingPlayer) < 6.25D)
		{
			temptedEntity.getNavigator().clearPathEntity();
		}
		else
		{
			temptedEntity.getNavigator().tryMoveToEntityLiving(temptingPlayer, field_75282_b);
		}
	}

	public boolean func_75277_f()
	{
		return field_75287_j;
	}
}