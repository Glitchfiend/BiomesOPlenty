/*******************************************************************************
 * Copyright 2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.world.feature;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.util.BlockPos;
import net.minecraft.util.WeightedRandom;
import net.minecraft.world.World;
import biomesoplenty.api.biome.generation.CustomizableGenerator;
import biomesoplenty.api.biome.generation.CustomizableWeightedGenerator;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.reflect.TypeToken;

public class GeneratorWeighted extends CustomizableGenerator
{
    private int amountPerChunk;
    private List<CustomizableWeightedGenerator> weightedEntries = new ArrayList<CustomizableWeightedGenerator>();
    
    public GeneratorWeighted() {}
    
    public GeneratorWeighted(int amountPerChunk)
    {
        this.amountPerChunk = amountPerChunk;
    }
    
    public void add(CustomizableWeightedGenerator entry)
    {
        this.weightedEntries.add(entry);
    }
    
    @Override
    public void scatter(World world, Random random, BlockPos pos)
    {
        CustomizableWeightedGenerator generator = (CustomizableWeightedGenerator)WeightedRandom.getRandomItem(random, this.weightedEntries);
        
        generator.scatter(world, random, pos, this.amountPerChunk);
    }

    @Override
    public boolean generate(World world, Random random, BlockPos pos)
    {
        CustomizableWeightedGenerator generator = (CustomizableWeightedGenerator)WeightedRandom.getRandomItem(random, this.weightedEntries);
        
        return generator.generate(world, random, pos, this.amountPerChunk);
    }

    @Override
    public void writeToJson(JsonObject json, JsonSerializationContext context)
    {
        json.addProperty("amount_per_chunk", this.amountPerChunk);
        json.add("entries", context.serialize(this.weightedEntries));
    }

    @Override
    public void readFromJson(JsonObject json, JsonDeserializationContext context)
    {
        this.amountPerChunk = json.get("amount_per_chunk").getAsInt();
        this.weightedEntries = context.deserialize(json.get("entries"), new TypeToken<List<CustomizableWeightedGenerator>>() {}.getType());
    }
}
