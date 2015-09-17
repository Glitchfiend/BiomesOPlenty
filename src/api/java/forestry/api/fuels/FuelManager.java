/*******************************************************************************
 * Copyright 2011-2014 SirSengir
 * 
 * This work (the API) is licensed under the "MIT" License, see LICENSE.txt for details.
 ******************************************************************************/
package forestry.api.fuels;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;

import java.util.HashMap;


public class FuelManager {
	/**
	 * Add new fuels for the fermenter here (i.e. fertilizer).
	 */
	public static HashMap<ItemStack, FermenterFuel> fermenterFuel;
	/**
	 * Add new resources for the moistener here (i.e. wheat)
	 */
	public static HashMap<ItemStack, MoistenerFuel> moistenerResource;
	/**
	 * Add new substrates for the rainmaker here
	 */
	public static HashMap<ItemStack, RainSubstrate> rainSubstrate;
	/**
	 * Add new fuels for EngineBronze (= biogas engine) here
	 */
	public static HashMap<Fluid, EngineBronzeFuel> bronzeEngineFuel;
	/**
	 * Add new fuels for EngineCopper (= peat-fired engine) here
	 */
	public static HashMap<ItemStack, EngineCopperFuel> copperEngineFuel;
	/**
	 * Add new fuels for Generator here
	 */
	public static HashMap<Fluid, GeneratorFuel> generatorFuel;

}
