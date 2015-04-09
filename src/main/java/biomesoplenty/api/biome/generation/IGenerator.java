/*******************************************************************************
 * Copyright 2014-2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.api.biome.generation;

import java.util.Random;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;

public interface IGeneratorBase
{
    public void setStage(GeneratorStage stage);
    
    /**The identifier for this generator should be consistent across all instances of the same type*/
    public String getIdentifier();
    public GeneratorStage getStage();
    
    public void writeToJson(JsonObject json, JsonSerializationContext context);
    public void readFromJson(JsonObject json, JsonDeserializationContext context);
}
