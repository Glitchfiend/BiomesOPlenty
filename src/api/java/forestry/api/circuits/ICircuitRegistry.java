/*******************************************************************************
 * Copyright 2011-2014 SirSengir
 * 
 * This work (the API) is licensed under the "MIT" License, see LICENSE.txt for details.
 ******************************************************************************/
package forestry.api.circuits;

import java.util.Map;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface ICircuitRegistry {

	/* CIRCUITS */
	Map<String, ICircuit> getRegisteredCircuits();

	void registerCircuit(ICircuit circuit);

	ICircuit getCircuit(String uid);

	ICircuitLibrary getCircuitLibrary(World world, String playername);

	/* LAYOUTS */
	Map<String, ICircuitLayout> getRegisteredLayouts();

	void registerLayout(ICircuitLayout layout);

	ICircuitLayout getLayout(String uid);

	ICircuitLayout getDefaultLayout();

	ICircuitBoard getCircuitboard(ItemStack itemstack);

	boolean isChipset(ItemStack itemstack);

}
