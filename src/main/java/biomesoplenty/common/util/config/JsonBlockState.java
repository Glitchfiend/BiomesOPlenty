/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.util.config;

import java.lang.reflect.Type;
import java.util.Map.Entry;

import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraftforge.fml.common.registry.GameRegistry;
import biomesoplenty.common.util.block.BlockStateUtils;

import com.google.common.collect.ImmutableSet;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSyntaxException;

public class JsonBlockState implements JsonDeserializer<IBlockState>, JsonSerializer<IBlockState>
{
    @Override
    public JsonElement serialize(IBlockState blockState, Type typeOfSrc, JsonSerializationContext context)
    {
        JsonObject jsonBlockState = new JsonObject();
        JsonObject jsonStateProperties = new JsonObject();

        jsonBlockState.addProperty("block", GameRegistry.findUniqueIdentifierFor(blockState.getBlock()).toString());

        for (Entry<IProperty, Comparable> entry : (ImmutableSet<Entry<IProperty, Comparable>>) blockState.getProperties().entrySet())
        {
            IProperty property = entry.getKey();
            Comparable value = entry.getValue();

            jsonStateProperties.addProperty(property.getName(), value.toString());
        }

        jsonBlockState.add("properties", jsonStateProperties);

        return jsonBlockState;
    }

    @Override
    public IBlockState deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
    {
        JsonObject jsonBlockState = json.getAsJsonObject();

        if (jsonBlockState.has("block"))
        {
            Block block = Block.getBlockFromName(jsonBlockState.get("block").getAsString());

            if (block != null)
            {
                IBlockState blockState = block.getDefaultState();

                if (jsonBlockState.has("properties"))
                {
                    JsonObject jsonProperties = jsonBlockState.getAsJsonObject("properties");

                    for (Entry<String, JsonElement> entry : jsonProperties.entrySet())
                    {
                        IProperty property = BlockStateUtils.getPropertyByName(blockState, entry.getKey());

                        if (property != null)
                        {
                            Comparable propertyValue = BlockStateUtils.getPropertyValueByName(blockState, property, entry.getValue().getAsString());

                            if (propertyValue != null)
                            {
                                blockState = blockState.withProperty(property, propertyValue);
                            }
                            else
                            {
                                throw new JsonParseException("Invalid value " + entry.getValue().getAsString() + " for property " + entry.getKey());
                            }
                        }
                        else
                        {
                            throw new JsonParseException("Invalid property name: " + entry.getKey());
                        }
                    }
                }

                return blockState;
            }
        }

        throw new JsonParseException("Invalid block state: " + json.toString());
    }
}
