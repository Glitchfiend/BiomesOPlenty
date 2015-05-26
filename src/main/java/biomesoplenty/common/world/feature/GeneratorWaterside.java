/*******************************************************************************
 * Copyright 2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.world.feature;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import biomesoplenty.api.biome.generation.GeneratorCustomizable;
import biomesoplenty.common.util.config.ConfigHelper.WrappedJsonObject;

public class GeneratorWaterside extends GeneratorCustomizable
{
    private int amountPerChunk;
    private int maxRadius;
    private IBlockState state;
    private List<IBlockState> replacedStates;
    
    public GeneratorWaterside()
    {
        // default
        this(4, 7, Blocks.gravel.getDefaultState());
    }
    
    public GeneratorWaterside(int amountPerChunk, int maxRadius, IBlockState state, IBlockState... replacedStates)
    {
        this.amountPerChunk = amountPerChunk;
        this.maxRadius = maxRadius;
        this.state = state;
        this.replacedStates = Arrays.asList(replacedStates);
    }
    
    public GeneratorWaterside(int amountPerChunk, int maxRadius, IBlockState state)
    {
        this(amountPerChunk, maxRadius, state, Blocks.grass.getDefaultState(), Blocks.dirt.getDefaultState());
    }
    
    @Override
    public void scatter(World world, Random random, BlockPos pos)
    {
        for (int i = 0; i < this.amountPerChunk; i++)
        {
            int x = random.nextInt(16) + 8;
            int z = random.nextInt(16) + 8;
            
            generate(world, random, world.getTopSolidOrLiquidBlock(pos.add(x, 0, z)));
        }
    }

    @Override
    public boolean generate(World world, Random random, BlockPos pos)
    {
        //Check we are generating around water
        if (world.getBlockState(pos).getBlock().getMaterial() != Material.water)
        {
            return false;
        }
        else
        {
            //The minimum radius must be 2, so two is subtracted from the random number calculation
            //and is added back on at the end
            int radius = random.nextInt(this.maxRadius - 2) + 2;
            byte heightRadius = 2;

            for (int x = pos.getX() - radius; x <= pos.getX() + radius; ++x)
            {
                for (int z = pos.getZ() - radius; z <= pos.getZ() + radius; ++z)
                {
                    int xDiff = x - pos.getX();
                    int zDiff = z - pos.getZ();

                    //Only replace blocks if the point is within an actual circle of the given radius
                    //The for loops merely create a square with a length and width 2 * radius
                    //x^2 + y^2 = r^2 is the equation of a circle
                    if (xDiff * xDiff + zDiff * zDiff <= radius * radius)
                    {
                        for (int y = pos.getY() - heightRadius; y <= pos.getY() + heightRadius; y++)
                        {
                            BlockPos posToReplace = new BlockPos(x, y, z);
                            IBlockState stateToReplace = world.getBlockState(posToReplace);

                            //If the list contains the state of the current block, replace it
                            if (replacedStates.contains(stateToReplace))
                            {
                                world.setBlockState(posToReplace, this.state, 2);
                            }
                        }
                    }
                }
            }

            return true;
        }
    }
    
    @Override
    public void configure(WrappedJsonObject conf)
    {
        this.amountPerChunk = conf.getInt("amountPerChunk", this.amountPerChunk);
        this.maxRadius = conf.getInt("maxRadius", this.maxRadius);
        this.state = conf.getBlockState("state", this.state);        
        this.replacedStates = conf.getBlockStateArray("replacedStates", new ArrayList(this.replacedStates) );
    }

}
