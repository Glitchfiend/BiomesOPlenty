package forestry.api.fuels;

import net.minecraft.item.ItemStack;

public class EngineBronzeFuel {
	/**
	 * Item that is valid fuel for a biogas engine.
	 */
	public final ItemStack liquid;
	/**
	 * Power produced by this fuel per work cycle of the engine.
	 */
	public final int powerPerCycle;
	/**
	 * How many work cycles a single "stack" of this type lasts.
	 */
	public final int burnDuration;
	/**
	 * By how much the normal heat dissipation rate of 1 is multiplied when using this fuel type.
	 */
	public final int dissipationMultiplier;

	public EngineBronzeFuel(ItemStack liquid, int powerPerCycle, int burnDuration, int dissipationMultiplier) {
		this.liquid = liquid;
		this.powerPerCycle = powerPerCycle;
		this.burnDuration = burnDuration;
		this.dissipationMultiplier = dissipationMultiplier;
	}
}
