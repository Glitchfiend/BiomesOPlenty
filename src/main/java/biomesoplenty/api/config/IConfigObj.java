/*******************************************************************************
 * Copyright 2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.api.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import biomesoplenty.api.block.IBlockPosQuery;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;

public interface IConfigObj
{
    public void addMessage(String message);
    public void addMessage(String extraPrefix, String message);
    public List<String> flushMessages();
    public boolean isEmpty();
    public boolean has(String name);
    public Set<String> getKeys();
    
    public IConfigObj getObject(String name);
    public ArrayList<IConfigObj> getObjectArray(String name);
    public IConfigObj getObject(String name, boolean warnIfMissing);
    public ArrayList<IConfigObj> getObjectArray(String name, boolean warnIfMissing);
    
    // Use the methods below when you want to obtain a value from a config file, if it is present, but you have a default value to use if it isn't
    // No warning messages will be logged using these methods if the value is missing
    public Boolean getBool(String name, Boolean defaultVal);
    public String getString(String name, String defaultVal);
    public Integer getInt(String name, Integer defaultVal);
    public Float getFloat(String name, Float defaultVal);
    public IBlockState getBlockState(String name, IBlockState defaultVal);
    public IBlockPosQuery getBlockPosQuery(String name, IBlockPosQuery defaultVal);
    public ResourceLocation getResourceLocation(String name, ResourceLocation defaultVal);
    public <E extends Enum> E getEnum(String name, E defaultVal, Class<E> clazz);
    
    // Use the methods below when you want to obtain a value from a config file which SHOULD be present
    // If the value is missing, a warning message is logged, and null is returned
    public Boolean getBool(String name);
    public String getString(String name);
    public Integer getInt(String name);
    public Float getFloat(String name);
    public IBlockState getBlockState(String name);
    public IBlockPosQuery getBlockPosQuery(String name);
    public ResourceLocation getResourceLocation(String name);
    public <E extends Enum> E getEnum(String name, Class<E> clazz);

    // Use the methods below when you want to obtain an array of values from a config file, if it is present, but you have a default value to use if it isn't
    // No warning messages will be logged using these methods if the value is missing
    public ArrayList<Boolean> getBoolArray(String name, ArrayList<Boolean> defaultVal);
    public ArrayList<String> getStringArray(String name, ArrayList<String> defaultVal);
    public ArrayList<Integer> getIntArray(String name, ArrayList<Integer> defaultVal);
    public ArrayList<Float> getFloatArray(String name, ArrayList<Float> defaultVal);
    public ArrayList<IBlockState> getBlockStateArray(String name, ArrayList<IBlockState> defaultVal);
    public ArrayList<IBlockPosQuery> getBlockPosQueryArray(String name, ArrayList<IBlockPosQuery> defaultVal);
    public ArrayList<ResourceLocation> getResourceLocationArray(String name, ArrayList<ResourceLocation> defaultVal);
    public <E extends Enum> ArrayList<E> getEnumArray(String name, ArrayList<E> defaultVal, Class<E> clazz);

    // Use the methods below when you want to obtain an array of values from a config file which SHOULD be present
    // If the value is missing, a warning message is logged, and null is returned
    public ArrayList<Boolean> getBoolArray(String name);
    public ArrayList<String> getStringArray(String name);
    public ArrayList<Integer> getIntArray(String name);
    public ArrayList<Float> getFloatArray(String name);
    public ArrayList<IBlockState> getBlockStateArray(String name);
    public ArrayList<IBlockPosQuery> getBlockPosQueryArray(String name);
    public ArrayList<ResourceLocation> getResourceLocationArray(String name);
    public <E extends Enum> ArrayList<E> getEnumArray(String name, Class<E> clazz);

    
}