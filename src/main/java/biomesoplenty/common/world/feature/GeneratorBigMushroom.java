/*******************************************************************************
 * Copyright 2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.world.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHugeMushroom;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import biomesoplenty.api.biome.generation.GeneratorCustomizable;
import biomesoplenty.common.util.block.BlockQueryUtils.BlockQueryAny;
import biomesoplenty.common.util.block.BlockQueryUtils.BlockQueryMaterial;
import biomesoplenty.common.util.block.BlockQueryUtils.IBlockQuery;
import biomesoplenty.common.util.config.BOPConfig.IConfigObj;

public class GeneratorBigMushroom extends GeneratorCustomizable
{       
    
    public static enum BigMushroomType
    {
        BROWN, RED;
        public IBlockState getDefaultState()
        {
            switch (this)
            {
                case BROWN:
                    return Blocks.brown_mushroom_block.getDefaultState();
                case RED: default:
                    return Blocks.red_mushroom_block.getDefaultState();
            }
        }
        public int getCapHeight(int height)
        {
            switch (this)
            {
                case BROWN:
                    return 1;
                case RED: default:
                    return 3;
            }
        }
        public int getCapDiameter(int y, int height)
        {
            switch (this)
            {
                case BROWN:
                    // only one layer - 7 blocks across
                    return 7;
                case RED: default:
                    // top layer 3 blocks across, other layers 5 blocks across
                    return (y < (height - 1)) ? 5 : 3;
            }           
        }
        public boolean cutCorners(int y, int height)
        {
            switch (this)
            {
                case BROWN:
                    return true;
                case RED: default:
                    // cur corners of every layer except the top layer on red mushroom
                    return (y < (height - 1));
            }
        }
    }
    
    private static IBlockQuery isLeavesOrAir = new BlockQueryAny(new BlockQueryMaterial(Material.leaves), new BlockQueryMaterial(Material.air));

    protected int amountPerChunk;
    protected BigMushroomType mushroomType;
    protected IBlockState mushroomState;
    
    public GeneratorBigMushroom()
    {
        // default
        this(4, BigMushroomType.BROWN);
    }
    
    public GeneratorBigMushroom(int amountPerChunk, BigMushroomType mushroomType)
    {
        this.amountPerChunk = amountPerChunk;
        this.setMushroomType(mushroomType);
    }  
    
    public void setMushroomType(BigMushroomType type)
    {
        this.mushroomType = type;
        this.mushroomState = type.getDefaultState();
    }
    
    
    @Override
    public void scatter(World world, Random random, BlockPos pos)
    {
        for (int i = 0; i < this.amountPerChunk; i++)
        {
            int x = random.nextInt(16) + 8;
            int z = random.nextInt(16) + 8;
            BlockPos genPos = world.getHeight(pos.add(x, 0, z));
            
            generate(world, random, genPos);
        }
    }
    
    protected void replaceWithMushroom(World world, BlockPos pos, BlockHugeMushroom.EnumType side)
    {
        if (world.getBlockState(pos).getBlock().canBeReplacedByLeaves(world, pos))
        {
            world.setBlockState(pos, this.mushroomState.withProperty(BlockHugeMushroom.VARIANT, side), 2);
        }
    }
    
    public boolean isEnoughSpace(World world, BlockPos pos, int height)
    {                
        if (pos.getY() < 1 || pos.getY() + height > 255)
        {
            return false;
        }
        for (int y = pos.getY(); y <= pos.getY() + height; ++y)
        {
            int radius = (y <= pos.getY() + 3) ? 0 : 3;

            for (int x = pos.getX() - radius; x <= pos.getX() + radius; ++x)
            {
                for (int z = pos.getZ() - radius; z <= pos.getZ() + radius; ++z)
                {
                    if (!isLeavesOrAir.matches(world.getBlockState(new BlockPos(x, y, z))))
                    {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    
    @Override
    public boolean generate(World world, Random rand, BlockPos pos)
    {

        int height = rand.nextInt(3) + 5;
        
        // check that there's room
        if (!this.isEnoughSpace(world, pos, height)) {return false;}
        
        Block blockBelow = world.getBlockState(pos.down()).getBlock();
        if (blockBelow != Blocks.dirt && blockBelow != Blocks.grass && blockBelow != Blocks.mycelium)
        {
            return false;
        }
        
        // create the mushroom cap
        for (int y = height - this.mushroomType.getCapHeight(height); y < height; ++y)
        {
            
            // not strictly the radius, but the number of blocks either side of the center
            int radius = ((this.mushroomType.getCapDiameter(y, height) - 1) / 2);

            for (int x = -radius; x <= radius; ++x)
            {
                for (int z = -radius; z <= radius; ++z)
                {
                    // work out which sides are exposed
                    BlockHugeMushroom.EnumType side;
                    if (x == -radius)
                    {
                        if (z == radius) {
                            side = BlockHugeMushroom.EnumType.SOUTH_WEST;
                        } else if (z == -radius) {
                            side = BlockHugeMushroom.EnumType.NORTH_WEST;
                        } else {
                            side = BlockHugeMushroom.EnumType.WEST;
                        }
                    }
                    else if (x == radius)
                    {
                        if (z == radius) {
                            side = BlockHugeMushroom.EnumType.SOUTH_EAST;
                        } else if (z == -radius) {
                            side = BlockHugeMushroom.EnumType.NORTH_EAST;
                        } else {
                            side = BlockHugeMushroom.EnumType.EAST;
                        }
                    }
                    else
                    {
                        if (z == radius) {
                            side = BlockHugeMushroom.EnumType.SOUTH;
                        } else if (z == -radius) {
                            side = BlockHugeMushroom.EnumType.NORTH;
                        } else {
                            side = BlockHugeMushroom.EnumType.CENTER;
                        }
                    }
                    

                    if (this.mushroomType.cutCorners(y, height))
                    {
                        // skip the corner blocks
                        if ((x == -radius || x == radius) && (z == -radius || z == radius))
                        {
                            continue;
                        }

                        // fix the sides which now become exposed after cutting the corners
                        if (x == -(radius - 1) && z == -radius)
                        {
                            side = BlockHugeMushroom.EnumType.NORTH_WEST;
                        }
                        if (x == -radius && z == -(radius - 1))
                        {
                            side = BlockHugeMushroom.EnumType.NORTH_WEST;
                        }
                        if (x == (radius - 1) && z == -radius)
                        {
                            side = BlockHugeMushroom.EnumType.NORTH_EAST;
                        }
                        if (x == radius && z == -(radius - 1))
                        {
                            side = BlockHugeMushroom.EnumType.NORTH_EAST;
                        }
                        if (x == -(radius - 1) && z == radius)
                        {
                            side = BlockHugeMushroom.EnumType.SOUTH_WEST;
                        }
                        if (x == -radius && z == (radius - 1))
                        {
                            side = BlockHugeMushroom.EnumType.SOUTH_WEST;
                        }
                        if (x == (radius - 1) && z == radius)
                        {
                            side = BlockHugeMushroom.EnumType.SOUTH_EAST;
                        }
                        if (x == radius && z == (radius - 1))
                        {
                            side = BlockHugeMushroom.EnumType.SOUTH_EAST;
                        }
                    }

                    // skip blocks which are totally inside the cap (unless the mushroom is really small)
                    if (side == BlockHugeMushroom.EnumType.CENTER && y < (height - 1))
                    {
                        if (height <= 2)
                        {
                            side = BlockHugeMushroom.EnumType.ALL_INSIDE;
                        } else {
                            continue;
                        }
                    }

                    // finally add the mushroom block
                    this.replaceWithMushroom(world, pos.add(x, y, z), side);
                }
            }
        }

        // create the mushroom stem
        for (int y = 0; y < height - 1; ++y)
        {
            this.replaceWithMushroom(world, pos.up(y), BlockHugeMushroom.EnumType.STEM);
        }

        return true;

    }
    
    @Override
    public void configure(IConfigObj conf)
    {        
        this.amountPerChunk = conf.getInt("amountPerChunk", this.amountPerChunk);
        this.setMushroomType(conf.getEnum("type", this.mushroomType, BigMushroomType.class));
    }
    
}