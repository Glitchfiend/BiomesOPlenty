/*******************************************************************************
 * Copyright 2011-2014 SirSengir
 *
 * This work (the API) is licensed under the "MIT" License, see LICENSE.txt for details.
 ******************************************************************************/
package forestry.api.apiculture.hives;

import java.util.List;

import forestry.api.apiculture.IHiveDrop;

public interface IHiveRegistry {

	/* Forestry Hive Names */
	public static final String forest = "Forestry:forest";
	public static final String meadows = "Forestry:meadows";
	public static final String desert = "Forestry:desert";
	public static final String jungle = "Forestry:jungle";
	public static final String end = "Forestry:end";
	public static final String snow = "Forestry:snow";
	public static final String swamp = "Forestry:swamp";

	/**
	 * Adds a new hive to be generated in the world.
	 */
	void registerHive(String hiveName, IHiveDescription hiveDescription);

	/**
	 * Add drops to a registered hive.
	 */
	void addDrops(String hiveName, IHiveDrop... drops);
	void addDrops(String hiveName, List<IHiveDrop> drop);
}
