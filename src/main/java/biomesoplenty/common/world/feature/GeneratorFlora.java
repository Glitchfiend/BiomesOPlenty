/*******************************************************************************
 * Copyright 2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.world.feature;

import java.util.Random;

import com.google.common.base.Predicates;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import biomesoplenty.api.biome.generation.GeneratorCustomizable;
import biomesoplenty.api.biome.generation.GeneratorWeightedEntry;
import biomesoplenty.common.block.BlockDecoration;
import biomesoplenty.common.util.biome.GeneratorUtils;

public class GeneratorFlora extends GeneratorCustomizable
{
    protected int amountPerChunk;
    protected IBlockState state;
    
    public GeneratorFlora() {}
    
    public GeneratorFlora(int amountPerChunk, IBlockState state)
    {
        this.amountPerChunk = amountPerChunk;
        this.state = state;
    }

    @Override
    public void scatter(World world, Random random, BlockPos pos)
    {
        for (int i = 0; i < amountPerChunk; i++)
        {
            int x = random.nextInt(16) + 8;
            int z = random.nextInt(16) + 8;
            BlockPos genPos = pos.add(x, 0, z);
            int y = GeneratorUtils.safeNextInt(random, world.getHeight(genPos).getY() + 32);
            genPos = genPos.add(0, y, 0);

            generate(world, random, genPos);
        }
    }

    @Override
    public boolean generate(World world, Random random, BlockPos pos)
    {
        Block block = this.state.getBlock();
        
        for (int i = 0; i < 64; ++i)
        {
            BlockPos genPos = pos.add(random.nextInt(8) - random.nextInt(8), random.nextInt(4) - random.nextInt(4), random.nextInt(8) - random.nextInt(8));

            boolean canStay = block instanceof BlockDecoration ? ((BlockDecoration)block).canBlockStay(world, genPos, this.state) : block.canPlaceBlockAt(world, genPos);
            
            if (world.isAirBlock(genPos) && (!world.provider.getHasNoSky() || genPos.getY() < 255) && canStay)
            {
                world.setBlockState(genPos, this.state, 2);
            }
        }

        return true;
    }

    @Override
    public void writeToJson(JsonObject json, JsonSerializationContext context)
    {
        json.addProperty("amount_per_chunk", this.amountPerChunk);
        json.add("state", context.serialize(this.state));
    }

    @Override
    public void readFromJson(JsonObject json, JsonDeserializationContext context)
    {
        this.amountPerChunk = json.get("amount_per_chunk").getAsInt();
        this.state = GeneratorUtils.deserializeStateNonNull(json, "state", context);
    }
}
