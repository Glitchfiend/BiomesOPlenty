/*******************************************************************************
 * Copyright 2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.api.biome.generation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.util.BlockPos;
import net.minecraft.util.WeightedRandom;
import net.minecraft.world.World;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.reflect.TypeToken;

public class GeneratorWeighted extends GeneratorCustomizable
{
    private List<GeneratorWeightedEntry> weightedEntries = new ArrayList<GeneratorWeightedEntry>();
    
    public GeneratorWeighted() {}

    public void add(int weight, IGenerator entry)
    {
        entry.setStage(GeneratorStage.PARENT);
        this.weightedEntries.add(new GeneratorWeightedEntry(weight, entry));
    }
    
    @Override
    public void scatter(World world, Random random, BlockPos pos)
    {
        GeneratorWeightedEntry generator = (GeneratorWeightedEntry)WeightedRandom.getRandomItem(random, this.weightedEntries);
        
        generator.scatter(world, random, pos);
    }

    @Override
    public boolean generate(World world, Random random, BlockPos pos)
    {
        GeneratorWeightedEntry generator = (GeneratorWeightedEntry)WeightedRandom.getRandomItem(random, this.weightedEntries);
        
        return generator.generate(world, random, pos);
    }

    @Override
    public void writeToJson(JsonObject json, JsonSerializationContext context)
    {
        json.add("entries", context.serialize(this.weightedEntries));
    }

    @Override
    public void readFromJson(JsonObject json, JsonDeserializationContext context)
    {
        this.weightedEntries = context.deserialize(json.get("entries"), new TypeToken<List<GeneratorWeightedEntry>>() {}.getType());
    }
}
