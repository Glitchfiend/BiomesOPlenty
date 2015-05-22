/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.util.config;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map.Entry;

import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;

import org.apache.commons.io.FileUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import biomesoplenty.common.util.block.BlockStateUtils;
import biomesoplenty.common.util.config.JsonBlockState;
import biomesoplenty.core.BiomesOPlenty;

public class ConfigHelper
{
    
    public static Gson serializer = new GsonBuilder().setPrettyPrinting().create();
    public static JsonParser parser = new JsonParser();
    public JsonBlockState blockStateParser = new JsonBlockState();
    public JsonObject root;
    public ArrayList<String> messages;
    
    public ConfigHelper()
    {
        
    }
    
    public ConfigHelper(String jsonString)
    {
        this.parse(jsonString);
    }
    
    public ConfigHelper(File configFile)
    {
        String jsonString = null;
        if (configFile.exists())
        {
            try
            {
                jsonString = FileUtils.readFileToString(configFile);
            } catch (IOException e) {
                BiomesOPlenty.logger.error("Error reading config file "+e.getMessage());
            }
        }
        this.parse(jsonString);
    }
    
    public void parse(String jsonString)
    {
        this.root = null;
        JsonElement rootElement = null;
        if (jsonString != null)
        {
            try
            {
                rootElement = parser.parse(jsonString);
                if (rootElement != null)
                {
                    if (rootElement.isJsonObject())
                    {
                        this.root = rootElement.getAsJsonObject();
                    } else {
                        BiomesOPlenty.logger.error("Error parsing config: not a JSON object");
                    }
                }
            } catch (Exception e) {
                BiomesOPlenty.logger.error("Error parsing config: "+e.getMessage());
            }
        }
    }
    
    
    public void addMessage(String message)
    {
        this.messages.add(message);
    }
    

    
    public Boolean getBool(String name, Boolean defaultVal)
    {
        if (this.root == null || !this.root.has(name)) {return defaultVal;}
        try
        {
            return this.root.get(name).getAsBoolean();
        } catch (Exception e) {
            this.addMessage("Error fetching boolean " + name + ": " + e.getMessage());
            return defaultVal;
        }
    }
    
    public String getString(String name, String defaultVal)
    {
        if (this.root == null || !this.root.has(name)) {return defaultVal;}
        try
        {
            return this.root.get(name).getAsString();
        } catch (Exception e) {
            this.addMessage("Error fetching string " + name + ": " + e.getMessage());
            return defaultVal;
        }
    }
    
    public Integer getInt(String name, Integer defaultVal)
    {
        if (this.root == null || !this.root.has(name)) {return defaultVal;}
        try
        {
            return this.root.get(name).getAsInt();
        } catch (Exception e) {
            this.addMessage("Error fetching integer " + name + ": " + e.getMessage());
            return defaultVal;
        }
    }
    
    public Float getFloat(String name, Float defaultVal)
    {
        if (this.root == null || !this.root.has(name)) {return defaultVal;}
        try
        {
            return this.root.get(name).getAsFloat();
        } catch (Exception e) {
            this.addMessage("Error fetching float " + name + ": " + e.getMessage());
            return defaultVal;
        }
    }
    
    public IBlockState getBlockState(String name, IBlockState defaultVal)
    {
        if (this.root == null || !this.root.has(name)) {return defaultVal;}
        try {
            
            JsonObject ele = this.root.get(name).getAsJsonObject();
            
            // attempt to load the specified block
            if (!ele.has("block"))
            {
                this.addMessage("Block name missing");
                return defaultVal;
            }
            JsonElement blockName = ele.get("block");
            if (!blockName.isJsonPrimitive())
            {
                this.addMessage("Invalid block name - must be a string");
                return defaultVal;
            }
            Block block = Block.getBlockFromName(blockName.getAsString());
            if (block == null)
            {
                this.addMessage("Unrecognised block name " + blockName.getAsString());
                return defaultVal;
            }

            IBlockState state = block.getDefaultState();
            
            // attempt to add properties
            if (ele.has("properties")) {
                
                JsonElement properties = ele.get("properties");
                if (!properties.isJsonObject())
                {
                    this.addMessage("Invalid properties list - must be a JSON object");
                    return state;
                }
                
                for (Entry<String, JsonElement> entry : properties.getAsJsonObject().entrySet())
                {
                    IProperty property = BlockStateUtils.getPropertyByName(state, entry.getKey());
                    if (property != null)
                    {
                        Comparable propertyValue = BlockStateUtils.getPropertyValueByName(state, property, entry.getValue().getAsString());
                        if (propertyValue != null)
                        {
                            state = state.withProperty(property, propertyValue);
                        }
                        else
                        {
                            this.addMessage("Invalid value " + entry.getValue().getAsString() + " for property " + entry.getKey());
                        }
                    }
                    else
                    {
                        this.addMessage("Invalid property name: " + entry.getKey());
                    }
                }
            }
            
            return state;
            
        } catch (Exception e) {
            this.addMessage("Error fetching block state " + name + ": " + e.getMessage());
            return defaultVal;
        }
    }
    
    
    public static boolean writeFile(File outputFile, Object obj)
    {
        try {
            FileUtils.write(outputFile, serializer.toJson(obj));
            return true;
        } catch (Exception e) {
            BiomesOPlenty.logger.error("Error writing config file " + outputFile.getAbsolutePath() + ": " + e.getMessage());
            return false;
        }
    }
    
}