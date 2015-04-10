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

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import biomesoplenty.common.block.BlockDecoration;
import biomesoplenty.common.util.biome.GeneratorUtils;

public class GeneratorGrass extends GeneratorFlora
{
    private int generationAttempts;
    
    public GeneratorGrass() {}
    
    public GeneratorGrass(int amountPerChunk, IBlockState state, int generationAttempts)
    {
        super(amountPerChunk, state);
        
        this.generationAttempts = generationAttempts;
    }
    
    public GeneratorGrass(int amountPerChunk, IBlockState state)
    {
        this(amountPerChunk, state, 128);
    }
    
    @Override
    public void scatter(World world, Random random, BlockPos pos)
    {
        for (int i = 0; i < this.amountPerChunk; i++)
        {
            int x = random.nextInt(16) + 8;
            int z = random.nextInt(16) + 8;
            BlockPos genPos = pos.add(x, 0, z);
            int y = GeneratorUtils.safeNextInt(random, world.getHeight(genPos).getY() * 2);
            genPos = genPos.add(0, y, 0);

            generate(world, random, genPos);
        }
    }

    @Override
    public boolean generate(World world, Random random, BlockPos pos)
    {
        Block block;
        Block stateBlock = this.state.getBlock();

        do
        {
            block = world.getBlockState(pos).getBlock();
            if (!block.isAir(world, pos) && !block.isLeaves(world, pos)) break;
            pos = pos.down();
        } 
        while (pos.getY() > 0);

        for (int i = 0; i < this.generationAttempts; i++)
        {
            BlockPos genPos = pos.add(random.nextInt(8) - random.nextInt(8), random.nextInt(4) - random.nextInt(4), random.nextInt(8) - random.nextInt(8));
            boolean canStay = stateBlock instanceof BlockDecoration ? ((BlockDecoration)stateBlock).canBlockStay(world, genPos, this.state) : stateBlock.canPlaceBlockAt(world, genPos);
            
            if (world.isAirBlock(genPos) && canStay)
            {
                world.setBlockState(genPos, this.state, 2);
            }
        }

        return true;
    }
    
    @Override
    public void writeToJson(JsonObject json, JsonSerializationContext context)
    {
        super.writeToJson(json, context);
        
        json.addProperty("generation_attempts", this.generationAttempts);
    }

    @Override
    public void readFromJson(JsonObject json, JsonDeserializationContext context)
    {
        super.readFromJson(json, context);
        
        this.generationAttempts = json.get("generation_attempts").getAsInt();
    }
}
