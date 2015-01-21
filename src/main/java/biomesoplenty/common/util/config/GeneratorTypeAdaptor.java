/*******************************************************************************
 * Copyright 2014-2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.util.config;

import java.lang.reflect.Type;

import biomesoplenty.api.biome.IGenerator;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class GeneratorTypeAdaptor implements JsonSerializer<IGenerator<?>>, JsonDeserializer<IGenerator<?>>
{
    @Override
    public JsonElement serialize(IGenerator<?> src, Type typeOfSrc, JsonSerializationContext context)
    {
        JsonObject jsonObject = src.serialize((IGenerator) src).getAsJsonObject();

        jsonObject.addProperty("class", src.getClass().getCanonicalName());

        return jsonObject;
    }

    @Override
    public IGenerator<?> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
    {
        JsonObject jsonObject = json.getAsJsonObject();

        if (jsonObject.has("class"))
        {
            try
            {
                Class generatorClass = Class.forName(jsonObject.get("class").getAsString());

                if (IGenerator.class.isAssignableFrom(generatorClass))
                {
                    IGenerator<?> generator = (IGenerator<?>) generatorClass.newInstance();

                    return generator.deserialize(json);
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        return null;
    }
}
