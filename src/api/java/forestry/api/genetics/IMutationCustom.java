/*******************************************************************************
 * Copyright 2011-2014 SirSengir
 *
 * This work (the API) is licensed under the "MIT" License, see LICENSE.txt for details.
 ******************************************************************************/
package forestry.api.genetics;

import net.minecraft.block.Block;

import net.minecraftforge.common.BiomeDictionary;

import forestry.api.core.EnumHumidity;
import forestry.api.core.EnumTemperature;

/** Set custom mutation requirements */
public interface IMutationCustom extends IMutation {

	/** Prevent this mutation from being shown in the analyzers */
	IMutationCustom setIsSecret();

	/** Require a specific temperature for this mutation to occur */
	IMutationCustom restrictTemperature(EnumTemperature temperature);
	IMutationCustom restrictTemperature(EnumTemperature minTemperature, EnumTemperature maxTemperature);

	/** Require a specific humidity for this mutation to occur */
	IMutationCustom restrictHumidity(EnumHumidity humidity);
	IMutationCustom restrictHumidity(EnumHumidity minHumidity, EnumHumidity maxHumidity);

	/**
	 * Restrict this mutation to certain types of biomes.
	 * @param types The types of biomes this mutation can occur.
	 */
	IMutationCustom restrictBiomeType(BiomeDictionary.Type... types);

	/** Restrict the days of the year that this mutation can occur */
	IMutationCustom restrictDateRange(int startMonth, int startDay, int endMonth, int endDay);

	/** Restrict the time of day that this mutation can occur */
	IMutationCustom requireDay();
	IMutationCustom requireNight();

	/** Require a specific resource to be under the location of the mutation */
	IMutationCustom requireResource(Block block, int meta);

	/** Require some other custom mutation condition */
	IMutationCustom addMutationCondition(IMutationCondition mutationCondition);
}
