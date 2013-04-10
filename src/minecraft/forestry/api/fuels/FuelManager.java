package forestry.api.fuels;

import java.util.HashMap;

import net.minecraft.item.ItemStack;

public class FuelManager {
	/**
	 * Add new fuels for the fermenter here (i.e. fertilizer)
	 */
	public static HashMap<ItemStack, FermenterFuel> fermenterFuel = new ItemStackMap<FermenterFuel>();
	/**
	 * Add new resources for the moistener here (i.e. wheat)
	 */
	public static HashMap<ItemStack, MoistenerFuel> moistenerResource = new ItemStackMap<MoistenerFuel>();
	/**
	 * Add new substrates for the rainmaker here
	 */
	public static HashMap<ItemStack, RainSubstrate> rainSubstrate = new ItemStackMap<RainSubstrate>();
	/**
	 * Add new fuels for EngineBronze (= biogas engine) here
	 */
	public static HashMap<ItemStack, EngineBronzeFuel> bronzeEngineFuel = new ItemStackMap<EngineBronzeFuel>();
	/**
	 * Add new fuels for EngineCopper (= peat-fired engine) here
	 */
	public static HashMap<ItemStack, EngineCopperFuel> copperEngineFuel = new ItemStackMap<EngineCopperFuel>();

	// Generator fuel list in GeneratorFuel.class
}
