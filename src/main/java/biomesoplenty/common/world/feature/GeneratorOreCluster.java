/*******************************************************************************
 * Copyright 2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.world.feature;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenMinable;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;

public class GeneratorOreCluster extends GeneratorOreBase
{
    private WorldGenMinable generator;
    
    public GeneratorOreCluster() {}
    
    public GeneratorOreCluster(IBlockState state, int amountPerChunk, int clusterSize, int minHeight, int maxHeight)
    {
        super(amountPerChunk, minHeight, maxHeight);
        
        this.generator = new WorldGenMinable(state, clusterSize);
    }
    
    @Override
    public void generate(World world, Random random, BlockPos pos)
    {
        this.generator.generate(world, random, pos);
    }

    @Override
    public void writeToJson(JsonObject json, JsonSerializationContext context)
    {
        super.writeToJson(json, context);
        
        json.add("state", context.serialize(this.generator.oreBlock));
        json.addProperty("cluster_size", this.generator.numberOfBlocks);
    }

    @Override
    public void readFromJson(JsonObject json, JsonDeserializationContext context)
    {
        super.readFromJson(json, context);
        
        IBlockState state = context.deserialize(json.get("state"), IBlockState.class);
        int clusterSize = json.get("cluster_size").getAsInt();
        
        this.generator = new WorldGenMinable(state, clusterSize); 
    }
}
