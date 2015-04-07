/*******************************************************************************
 * Copyright 2014-2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.util.config;

import java.lang.reflect.Type;

import biomesoplenty.api.biome.generation.GeneratorRegistry;
import biomesoplenty.api.biome.generation.GeneratorStage;
import biomesoplenty.api.biome.generation.IGenerator;

import com.google.common.reflect.TypeToken;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSyntaxException;

public class GeneratorTypeAdaptor implements JsonSerializer<IGenerator>, JsonDeserializer<IGenerator>
{
    @Override
    public JsonElement serialize(IGenerator src, Type typeOfSrc, JsonSerializationContext context)
    {
        JsonObject jsonObject = new JsonObject();
        src.writeToJson(jsonObject, context);
        
        jsonObject.addProperty("generator", src.getIdentifier());
        jsonObject.add("stage", context.serialize(src.getStage()));

        return jsonObject;
    }

    @Override
    public IGenerator deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
    {
        JsonObject jsonObject = json.getAsJsonObject();

        if (jsonObject.has("generator"))
        {
            String generatorIdentifier = jsonObject.get("generator").getAsString();
            Class<? extends IGenerator> generatorClass = GeneratorRegistry.getGeneratorClass(generatorIdentifier);

            if (generatorClass == null)
            {
                throw new JsonSyntaxException("Generator " + generatorIdentifier + " doesn't exist");
            }
            else
            {
                IGenerator generator;
                try
                {
                    generator = (IGenerator)generatorClass.newInstance();

                    Type generatorStageType = new TypeToken<GeneratorStage>() {}.getType();
                    String generatorStageName = jsonObject.get("stage").getAsString();
                    GeneratorStage generatorStage = (GeneratorStage)context.deserialize(jsonObject.get("stage"), generatorStageType);
                    
                    if (generatorStage == null)
                    {
                        throw new JsonSyntaxException("Generator stage " + generatorStageName + " is invalid");
                    }
                    else
                    {
                        generator.setStage((GeneratorStage)context.deserialize(jsonObject.get("stage"), generatorStageType));
                        generator.readFromJson(jsonObject, context);
                        
                        return generator;   
                    }
                } 
                catch (InstantiationException e)
                {
                    throw new RuntimeException("Generators must have a no-args constructor", e);
                } 
                catch (IllegalAccessException e)
                {
                    e.printStackTrace();
                }
            }
        }
        else
        {
            throw new JsonSyntaxException("Entry missing generator property");
        }

        return null;
    }
}
