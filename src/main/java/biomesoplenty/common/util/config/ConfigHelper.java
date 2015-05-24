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
import com.google.gson.JsonArray;
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
    public WrappedJsonObject root;
    public ArrayList<String> messages = new ArrayList<String>();
    
    
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
                        this.root = new WrappedJsonObject(this, rootElement.getAsJsonObject());
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
    
    
    public WrappedJsonObject getObject(String name)
    {
        return this.root == null ? null : this.root.getObject(name);
    }
    public ArrayList<WrappedJsonObject> getObjectArray(String name)
    {
        return this.root == null ? new ArrayList<WrappedJsonObject>() : this.root.getObjectArray(name);
    }
    public ArrayList<Boolean> getBoolArray(String name)
    {
        return this.root == null ? new ArrayList<Boolean>() : this.root.getBoolArray(name);
    }
    public Boolean getBool(String name, Boolean defaultVal)
    {
        return this.root == null ? defaultVal : this.root.getBool(name, defaultVal);
    }
    public ArrayList<String> getStringArray(String name)
    {
        return this.root == null ? new ArrayList<String>() : this.root.getStringArray(name);
    }
    public String getString(String name, String defaultVal)
    {
        return this.root == null ? defaultVal : this.root.getString(name, defaultVal);
    }
    public ArrayList<Integer> getIntArray(String name)
    {
        return this.root == null ? new ArrayList<Integer>() : this.root.getIntArray(name);
    }
    public Integer getInt(String name, Integer defaultVal)
    {
        return this.root == null ? defaultVal : this.root.getInt(name, defaultVal);
    }
    public ArrayList<Float> getFloatArray(String name)
    {
        return this.root == null ? new ArrayList<Float>() : this.root.getFloatArray(name);
    }
    public Float getFloat(String name, Float defaultVal)
    {
        return this.root == null ? defaultVal : this.root.getFloat(name, defaultVal);
    }
    public ArrayList<IBlockState> getBlockStateArray(String name)
    {
        return this.root == null ? new ArrayList<IBlockState>() : this.root.getBlockStateArray(name);
    }
    public IBlockState getBlockState(String name, IBlockState defaultVal)
    {
        return this.root == null ? defaultVal : this.root.getBlockState(name, defaultVal);
    }
    
    
    
    
        
    private static enum Types {BOOLEAN, STRING, INTEGER, FLOAT, BLOCKSTATE}
    
    private class WrappedJsonObject
    {
        
        
        protected JsonObject obj;
        protected ConfigHelper conf;
        WrappedJsonObject(ConfigHelper conf, JsonObject obj)
        {
            this.obj = obj;
            this.conf = conf;
        }
        
        public WrappedJsonObject getObject(String name)
        {
            if (this.obj == null || !this.obj.has(name)) {return null;}
            try
            {
                JsonObject obj = this.obj.getAsJsonObject(name);
                if (obj == null) {return null;}
                return new WrappedJsonObject(this.conf, obj);
            } catch (Exception e) {
                this.conf.addMessage("Error fetching object " + name + ": " + e.getMessage());
                return null;               
            }
        }
        
        public ArrayList<WrappedJsonObject> getObjectArray(String name)
        {
            ArrayList<WrappedJsonObject> list = new ArrayList<WrappedJsonObject>();
            if (this.obj != null && this.obj.has(name)) {
                try
                {
                    JsonArray arr = this.obj.getAsJsonArray(name);
                    for (int i = 0; i < arr.size(); i++)
                    {
                        try
                        {
                            JsonObject obj = arr.get(i).getAsJsonObject();
                            if (obj != null) {list.add( new WrappedJsonObject(this.conf, obj) );}
                        } catch (Exception e) {
                            this.conf.addMessage("Error fetching object from array " + name + " at index " + i + ": " + e.getMessage());
                        }
                    }
                    
                } catch (Exception e) {
                    this.conf.addMessage("Error fetching object array " + name + ": " + e.getMessage());
                    return null;               
                }
            }
            return list;
        }
        
        public ArrayList<Boolean> getBoolArray(String name) {return this.<Boolean>getArray(name, Types.BOOLEAN);}
        public Boolean getBool(String name, Boolean defaultVal) {return this.<Boolean>get(name, defaultVal, Types.BOOLEAN);}
        public ArrayList<String> getStringArray(String name) {return this.<String>getArray(name, Types.STRING);}
        public String getString(String name, String defaultVal) {return this.<String>get(name, defaultVal, Types.STRING);}
        public ArrayList<Integer> getIntArray(String name) {return this.<Integer>getArray(name, Types.INTEGER);}
        public Integer getInt(String name, Integer defaultVal) {return this.<Integer>get(name, defaultVal, Types.INTEGER);}
        public ArrayList<Float> getFloatArray(String name) {return this.<Float>getArray(name, Types.FLOAT);}
        public Float getFloat(String name, Float defaultVal) {return this.<Float>get(name, defaultVal, Types.FLOAT);}
        public ArrayList<IBlockState> getBlockStateArray(String name) {return this.<IBlockState>getArray(name, Types.BLOCKSTATE);}
        public IBlockState getBlockState(String name, IBlockState defaultVal) {return this.<IBlockState>get(name, defaultVal, Types.BLOCKSTATE);}
        
 
        
        
        private <T> T get(String name, T defaultVal, Types type)
        {
            if (this.obj == null || !this.obj.has(name)) {return defaultVal;}
            T out = this.<T>as(this.obj.get(name), type);
            return out == null ? defaultVal : out;    
        }
        
        private <T> ArrayList<T> getArray(String name, Types type)
        {
            ArrayList<T> list = new ArrayList<T>();
            if (this.obj != null && this.obj.has(name)) {
                try
                {
                    JsonArray arr = this.obj.getAsJsonArray(name);
                    for (int i = 0; i < arr.size(); i++)
                    {
                        T ele = this.<T>as(arr.get(i), type);
                        if (ele != null) {list.add(ele);}
                    }
                } catch (Exception e) {
                    this.conf.addMessage("Error fetching " + type.toString().toLowerCase() + " array: " + e.getMessage());
                }
            }
            return list;
        }
        
        private <T> T as(JsonElement ele, Types type)
        {
            switch (type) {
                case BOOLEAN:
                    return (T)this.asBool(ele);
                case STRING:
                    return (T)this.asString(ele);
                case INTEGER:
                    return (T)this.asInt(ele);
                case FLOAT:
                    return (T)this.asFloat(ele);
                case BLOCKSTATE:
                    return (T)this.asBlockState(ele);
                default:
                    return null;
            }
        }
                
        public Boolean asBool(JsonElement ele)
        {
            try
            {
                return ele.getAsBoolean();
            } catch (Exception e) {
                this.conf.addMessage("Error fetching boolean: " + e.getMessage());
                return null;
            }
        }
        
        public String asString(JsonElement ele)
        {
            try
            {
                return ele.getAsString();
            } catch (Exception e) {
                this.conf.addMessage("Error fetching string: " + e.getMessage());
                return null;
            }
        }
        
        public Integer asInt(JsonElement ele)
        {
            try
            {
                return ele.getAsInt();
            } catch (Exception e) {
                this.conf.addMessage("Error fetching integer: " + e.getMessage());
                return null;
            }
        }
        
        public Float asFloat(JsonElement ele)
        {
            try
            {
                return ele.getAsFloat();
            } catch (Exception e) {
                this.conf.addMessage("Error fetching float: " + e.getMessage());
                return null;
            }
        }
        
        public IBlockState asBlockState(JsonElement arse)
        {
            try {
                
                JsonObject ele = arse.getAsJsonObject();
                
                // attempt to load the specified block
                if (!ele.has("block"))
                {
                    this.conf.addMessage("Block name missing");
                    return null;
                }
                JsonElement blockName = ele.get("block");
                if (!blockName.isJsonPrimitive())
                {
                    this.conf.addMessage("Invalid block name - must be a string");
                    return null;
                }
                Block block = Block.getBlockFromName(blockName.getAsString());
                if (block == null)
                {
                    this.conf.addMessage("Unrecognised block name " + blockName.getAsString());
                    return null;
                }

                IBlockState state = block.getDefaultState();
                
                // attempt to add properties
                if (ele.has("properties")) {
                    
                    JsonElement properties = ele.get("properties");
                    if (!properties.isJsonObject())
                    {
                        this.conf.addMessage("Invalid properties list - must be a JSON object");
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
                                this.conf.addMessage("Invalid value " + entry.getValue().getAsString() + " for property " + entry.getKey());
                            }
                        }
                        else
                        {
                            this.conf.addMessage("Invalid property name: " + entry.getKey());
                        }
                    }
                }
                
                return state;
                
            } catch (Exception e) {
                this.conf.addMessage("Error fetching blockstate: " + e.getMessage());
                return null;
            }
        }
    } 
    
}