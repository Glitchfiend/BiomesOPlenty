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
import com.google.gson.JsonElement;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;

public interface IConfigObj
{
    JsonElement serializeDefaults();
    void addMessage(String message);
    void addMessage(String extraPrefix, String message);
    List<String> flushMessages();
    boolean isEmpty();
    boolean has(String name);
    Set<String> getKeys();
    
    IConfigObj getObject(String name);
    ArrayList<IConfigObj> getObjectArray(String name);
    IConfigObj getObject(String name, boolean warnIfMissing);
    ArrayList<IConfigObj> getObjectArray(String name, boolean warnIfMissing);
    
    // Use the methods below when you want to obtain a value from a config file, if it is present, but you have a default value to use if it isn't
    // No warning messages will be logged using these methods if the value is missing
    Boolean getBool(String name, Boolean defaultVal);
    String getString(String name, String defaultVal);
    Integer getInt(String name, Integer defaultVal);
    Float getFloat(String name, Float defaultVal);
    IBlockState getBlockState(String name, IBlockState defaultVal);
    IBlockPosQuery getBlockPosQuery(String name, IBlockPosQuery defaultVal);
    ResourceLocation getResourceLocation(String name, ResourceLocation defaultVal);
    <E extends Enum> E getEnum(String name, E defaultVal, Class<E> clazz);
    
    // Use the methods below when you want to obtain a value from a config file which SHOULD be present
    // If the value is missing, a warning message is logged, and null is returned
    Boolean getBool(String name);
    String getString(String name);
    Integer getInt(String name);
    Float getFloat(String name);
    IBlockState getBlockState(String name);
    IBlockPosQuery getBlockPosQuery(String name);
    ResourceLocation getResourceLocation(String name);
    <E extends Enum> E getEnum(String name, Class<E> clazz);

    // Use the methods below when you want to obtain an array of values from a config file, if it is present, but you have a default value to use if it isn't
    // No warning messages will be logged using these methods if the value is missing
    ArrayList<Boolean> getBoolArray(String name, ArrayList<Boolean> defaultVal);
    ArrayList<String> getStringArray(String name, ArrayList<String> defaultVal);
    ArrayList<Integer> getIntArray(String name, ArrayList<Integer> defaultVal);
    ArrayList<Float> getFloatArray(String name, ArrayList<Float> defaultVal);
    ArrayList<IBlockState> getBlockStateArray(String name, ArrayList<IBlockState> defaultVal);
    ArrayList<IBlockPosQuery> getBlockPosQueryArray(String name, ArrayList<IBlockPosQuery> defaultVal);
    ArrayList<ResourceLocation> getResourceLocationArray(String name, ArrayList<ResourceLocation> defaultVal);
    <E extends Enum> ArrayList<E> getEnumArray(String name, ArrayList<E> defaultVal, Class<E> clazz);

    // Use the methods below when you want to obtain an array of values from a config file which SHOULD be present
    // If the value is missing, a warning message is logged, and null is returned
    ArrayList<Boolean> getBoolArray(String name);
    ArrayList<String> getStringArray(String name);
    ArrayList<Integer> getIntArray(String name);
    ArrayList<Float> getFloatArray(String name);
    ArrayList<IBlockState> getBlockStateArray(String name);
    ArrayList<IBlockPosQuery> getBlockPosQueryArray(String name);
    ArrayList<ResourceLocation> getResourceLocationArray(String name);
    <E extends Enum> ArrayList<E> getEnumArray(String name, Class<E> clazz);

    
}