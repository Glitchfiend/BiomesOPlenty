/*******************************************************************************
 * Copyright 2014-2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.api.biome;

public enum BiomeProperty
{
	GRASS_PER_CHUNK(1),
	FLOWERS_PER_CHUNK(2);

	private Object defaultValue;
	
	private BiomeProperty() {}
	
	private BiomeProperty(Object defaultValue)
	{
		this.defaultValue = defaultValue;
	}
	
	public String getName() 
	{
		return this.name().toLowerCase();
	}

	public Object getDefaultValue() 
	{
		return this.defaultValue;
	}
}
