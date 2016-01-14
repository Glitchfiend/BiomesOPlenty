package thaumcraft.api.golems;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Contains functions to allow addon devs to access golem internals
 */
public interface IGolemAPI {
	
	public EntityLivingBase getGolemEntity();
	
	public IGolemProperties getProperties();
	
	public void setProperties(IGolemProperties prop);
	
	public World getGolemWorld();
	
	/**
	 * Causes the golem to hold the itemstack supplied.
	 * @param stack
	 * @return anything left over that the golem could not hold. If the golem picked up the entire stack this will be a null.
	 */
	public ItemStack holdItem(ItemStack stack);
	
	/**
	 * Causes the golem to remove an itemstack it is holding. It does not actually drop the item in the 
	 * world or place it anywhere - that is up to whatever is calling this method.
	 * @param stack the itemstack that the golem will drop. If null is supplied the golem will drop whatever it is holding
	 * @return the stack it 'dropped'
	 */
	public ItemStack dropItem(ItemStack stack);
	
	
	/**
	 * Checks if the golem has carrying capacity for the given stack
	 * @param stack the stack the golem has room for - can be null
	 * @param partial does the golem only need to have room for part of the stack?
	 * @return 
	 */
	public boolean canCarry(ItemStack stack, boolean partial);

	public boolean isCarrying(ItemStack stack);
	
	public ItemStack[] getCarrying();
	
	/**
	 * Gives the golem xp towards increasing its rank rating. Default is usually 1 for completing a task. 
	 * @param xp
	 */
	public void addRankXp(int xp);

	
	
	/**
	 * Plays arm swinging animated for attacks and such
	 */
	public void swingArm();
	
	
	
}
