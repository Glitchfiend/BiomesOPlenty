/*
 *******************************************************************************
 * Copyright (c) 2011-2014 SirSengir.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 *
 * Various Contributors including, but not limited to:
 * SirSengir (original work), CovertJaguar, Player, Binnie, MysteriousAges
 *******************************************************************************
 */
package forestry.api.core;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import net.minecraft.client.renderer.texture.IIconRegister;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 *
 * @author CovertJaguar <http://www.railcraft.info/>
 */
public class ErrorStateRegistry {

	private static final BiMap<Short, IErrorState> states = HashBiMap.create();
	private static final Map<String, IErrorState> stateNames = new HashMap<String, IErrorState>();
	private static final Set<IErrorState> stateView = Collections.unmodifiableSet(states.inverse().keySet());

	public static void registerErrorState(IErrorState state) {
		if (states.containsKey(state.getID()))
			throw new RuntimeException("Forestry Error State does not possess a unique id.");
		states.put(state.getID(), state);
		addStateName(state, state.getUniqueName());
	}

	public static void addAlias(IErrorState state, String name) {
		if (!states.values().contains(state))
			throw new RuntimeException("Forestry Error State did not exist while trying to register alias.");
		addStateName(state, name);
	}

	private static void addStateName(IErrorState state, String name) {
		if (!name.contains(":"))
			throw new RuntimeException("Forestry Error State name must be in the format <modid>:<name>.");
		if (stateNames.containsKey(name))
			throw new RuntimeException("Forestry Error State does not possess a unique name.");
		stateNames.put(name, state);
	}

	public static IErrorState getErrorState(short id) {
		return states.get(id);
	}

	public static IErrorState getErrorState(String name) {
		return stateNames.get(name);
	}

	public static Set<IErrorState> getErrorStates() {
		return stateView;
	}

	@SideOnly(Side.CLIENT)
	public static void initIcons(IIconRegister register) {
		for (IErrorState code : states.values()) {
			code.registerIcons(register);
		}
	}
}
