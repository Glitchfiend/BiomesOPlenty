package thaumcraft.api;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;


/**
 * @author Azanor
 * Items and tools with this interface can receive the Repair enchantment. 
 * Armor, weapons and tools are handled automatically. 
 * Usually 1 vis equals 1 point of durability every 2 seconds (1 second for repair II)
 * Repair enchant values over II also grants a reduction in speed, but at a much 
 * reduced rate (about 2 ticks per rating over II)
 * Some sample code:<p>
 * <i>
 *	public void doRepair(ItemStack is, Entity e) {<br>
 *	 if (AuraManager.decreaseClosestAura(e.worldObj,e.posX, e.posY, e.posZ, 1)) {<br>
 *		 is.damageItem(-1,(EntityLiving) e);<br>
 *		}<br>
 *	}<br><br>
 *</i>
 */
public interface IVisRepairable {
	
	void doRepair(ItemStack stack, Entity e);

}
