/*******************************************************************************
 * Copyright 2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.world.feature;

import java.util.Random;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;

import biomesoplenty.api.biome.generation.GeneratorCustomizable;
import biomesoplenty.common.block.BlockDecoration;
import biomesoplenty.common.util.biome.GeneratorUtils;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class GeneratorDoubleFlora extends GeneratorCustomizable
{
    private int amountPerChunk;
    private IBlockState bottomState;
    private IBlockState topState;
    private int generationAttempts;
    
    public GeneratorDoubleFlora() {}
    
    public GeneratorDoubleFlora(int amountPerChunk, IBlockState bottomState, IBlockState topState, int generationAttempts)
    {
        this.amountPerChunk = amountPerChunk;
        this.bottomState = bottomState;
        this.topState = topState;
        this.generationAttempts = generationAttempts;
    }
    
    public GeneratorDoubleFlora(int amountPerChunk, IBlockState bottomState, IBlockState topState)
    {
        this(amountPerChunk, bottomState, topState, 64);
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
        Block bottomBlock = this.bottomState.getBlock();
        
        for (int i = 0; i < this.generationAttempts; ++i)
        {
            BlockPos genPos = pos.add(random.nextInt(8) - random.nextInt(8), random.nextInt(4) - random.nextInt(4), random.nextInt(8) - random.nextInt(8));

            boolean canStay = bottomBlock instanceof BlockDecoration ? ((BlockDecoration)bottomBlock).canBlockStay(world, genPos, this.bottomState) : bottomBlock.canPlaceBlockAt(world, genPos);
            
            if (world.isAirBlock(genPos) && world.isAirBlock(genPos.up()) && (!world.provider.getHasNoSky() || genPos.getY() < 255) && canStay)
            {
                world.setBlockState(genPos, this.bottomState, 2);
                world.setBlockState(genPos.up(), this.topState, 2);
            }
        }

        return true;
    }

    @Override
    public void writeToJson(JsonObject json, JsonSerializationContext context)
    {
        json.addProperty("amount_per_chunk", this.amountPerChunk);
        json.add("bottom_state", context.serialize(this.bottomState));
        json.add("top_state", context.serialize(this.topState));
        json.addProperty("generation_attempts", this.generationAttempts);
    }

    @Override
    public void readFromJson(JsonObject json, JsonDeserializationContext context)
    {
        this.amountPerChunk = json.get("amount_per_chunk").getAsInt();
        this.bottomState = GeneratorUtils.deserializeStateNonNull(json, "bottom_state", context);
        this.topState = GeneratorUtils.deserializeStateNonNull(json, "top_state", context);
        this.generationAttempts = json.get("generation_attempts").getAsInt();
    }
}
