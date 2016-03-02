/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.util.biome;

import java.util.Collection;
import java.util.Random;

import org.apache.commons.lang3.tuple.Pair;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import biomesoplenty.api.block.BlockQueries;
import biomesoplenty.common.util.block.BlockQuery.IBlockPosQuery;
import net.minecraft.block.BlockLog;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GeneratorUtils
{
    public static Pair<Integer, Integer> validateMinMaxHeight(int minHeight, int maxHeight)
    {
        if (maxHeight < minHeight)
        {
            //Swap min and max height so that max is higher than min
            int prevMinHeight = minHeight;
            minHeight = maxHeight;
            maxHeight = prevMinHeight;
        }
        
        return Pair.of(minHeight, maxHeight);
    }
    
    public static int safeNextInt(Random random, int i)
    {
        if (i <= 1) return 0;

        return random.nextInt(i);
    }
    
    // get a random number between 2 values (inclusive of end points)
    public static int nextIntBetween(Random rand, int a, int b)
    {
        if (a == b) {return a;}
        int min = a < b ? a : b;
        int max = a > b ? a : b;
        return min + rand.nextInt(1 + max - min);
    }
    
    public static IBlockState deserializeStateNonNull(JsonObject json, String memberName, JsonDeserializationContext context)
    {
        IBlockState state = context.deserialize(json.get(memberName), IBlockState.class);
        
        if (state == null)
        {
            throw new JsonSyntaxException("Property " + memberName + " doesn't exist");
        }
        
        return state;
    }
        
    public static BlockPos getFirstBlockMatching(World world, BlockPos startPos, IBlockPosQuery query)
    {
        for (BlockPos pos = startPos; pos.getY() > 0; pos = pos.down())
        {
            if (query.matches(world, pos))
            {
                return pos;
            }
        }
        return null;
    }
    
    public static enum ScatterYMethod
    {
        ANYWHERE, AT_SURFACE, AT_GROUND, BELOW_SURFACE, BELOW_GROUND, ABOVE_SURFACE, ABOVE_GROUND;
        public BlockPos getBlockPos(World world, Random random, int x, int z)
        {
            int tempY;
            BlockPos pos;
            switch(this)
            {
                case AT_SURFACE:
                    // always at the 'surface level' - to be precise, the air block directly above land or sea
                    pos = getFirstBlockMatching(world, new BlockPos(x, 255, z), BlockQueries.surfaceBlocks);
                    return (pos == null ? new BlockPos(x, 1, z) : pos.up());                    
                case AT_GROUND:
                    // always at the 'ground level' - the air or water block directly above the land, or the bottom of the sea bed
                    pos = getFirstBlockMatching(world, new BlockPos(x, 255, z), BlockQueries.groundBlocks);
                    return (pos == null ? new BlockPos(x, 1, z) : pos.up());                     
                case BELOW_SURFACE:
                    // random point below surface (but possibly in the sea)
                    tempY = world.getHeight(new BlockPos(x, 0, z)).getY();
                    return new BlockPos(x, nextIntBetween(random, 1, tempY - 1), z);
                case BELOW_GROUND:
                    // random point below ground (and below sea)
                    tempY = world.getTopSolidOrLiquidBlock(new BlockPos(x, 0, z)).getY();
                    return new BlockPos(x, nextIntBetween(random, 1, tempY - 1), z);
                case ABOVE_SURFACE:
                    // random point above surface (amd above the sea)
                    tempY = world.getHeight(new BlockPos(x, 0, z)).getY();
                    return new BlockPos(x, GeneratorUtils.nextIntBetween(random, tempY, 255), z);
                case ABOVE_GROUND:
                    // random point above ground (but possibly in the sea)
                    tempY = world.getTopSolidOrLiquidBlock(new BlockPos(x, 0, z)).getY();
                    return new BlockPos(x, GeneratorUtils.nextIntBetween(random, tempY, 255), z);
                case ANYWHERE: default:
                    // random y coord
                    return new BlockPos(x, nextIntBetween(random, 1, 255), z);
            }
        }
    }
    
    
    // Given a blockstate for some block which has an axis orientation (such as logs), try to determine the axis property
    public static IProperty getAxisProperty(IBlockState log)
    {
        for (Object property : log.getProperties().keySet())
        {
            Collection allowedValues = ((IProperty)property).getAllowedValues();
            if (allowedValues.contains(BlockLog.EnumAxis.X) && allowedValues.contains(BlockLog.EnumAxis.Y) && allowedValues.contains(BlockLog.EnumAxis.Z))
            {
                return(IProperty)property;
            }
        }
        return null;
    }
    
    
}
