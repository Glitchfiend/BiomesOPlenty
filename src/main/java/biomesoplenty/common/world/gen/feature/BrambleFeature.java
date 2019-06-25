/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.world.gen.feature;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.block.BlockBramble;
import biomesoplenty.common.util.biome.GeneratorUtil;
import biomesoplenty.common.util.block.IBlockPosQuery;
import com.mojang.datafixers.Dynamic;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.material.Material;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;
import java.util.function.Function;

public class BrambleFeature extends Feature<NoFeatureConfig>
{
	public BrambleFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> deserializer)
	{
		super(deserializer);
	}

    protected IBlockPosQuery placeOn = (world, pos) ->
    {
        BlockState state = world.getBlockState(pos);
        return state.canSustainPlant(world, pos, Direction.UP, (SaplingBlock)Blocks.OAK_SAPLING) || state.getBlock() == Blocks.SOUL_SAND;
    };
    
    protected IBlockPosQuery replace = (world, pos) -> world.getBlockState(pos).getMaterial() == Material.AIR;

	@Override
	public boolean func_212245_a(IWorld world, ChunkGenerator<? extends GenerationSettings> p_212245_2, Random rand, BlockPos startPos, NoFeatureConfig p_212245_5_)
    {
        for (int i = 0; i < 128; ++i)
        {
            BlockPos genPos = startPos.add(rand.nextInt(4) - rand.nextInt(4), rand.nextInt(3) - rand.nextInt(3), rand.nextInt(4) - rand.nextInt(4));
                
            if (this.placeOn.matches(world, genPos.down()) && this.replace.matches(world, genPos))
            {
                int targetLength = GeneratorUtil.nextIntBetween(rand, 15, 30);
                int height = 0;
                int direction = rand.nextInt(4) + 2;
                
                for (int length = 0; length <= targetLength && replace.matches(world, genPos); length++)
                {  
                	//if (BlockBramble.canPlaceBlockAt(world, genPos))
                	//{
                		world.setBlockState(genPos, ((BlockBramble)BOPBlocks.bramble).makeConnections(world, genPos), 2);

                		for (Direction face : Direction.values())
                		{
                			if (world.getBlockState(genPos.offset(face)).getBlock() == BOPBlocks.bramble)
                			{
                				world.setBlockState(genPos.offset(face), ((BlockBramble)BOPBlocks.bramble).makeConnections(world, genPos.offset(face)), 2);
                			}
                		}
	                    
	                    if (rand.nextInt(2) == 0)
	                	{
	            	    	direction = rand.nextInt(4) + 2;
	                	}
	                    
	                    if (rand.nextInt(2) == 0)
	                    {
	                    	int leafDirection = rand.nextInt(6);
	                    	BlockPos leafPos = genPos.offset(Direction.values()[leafDirection]);
	                    	if (world.isAirBlock(leafPos))
	                    	{
	                    		world.setBlockState(leafPos, Blocks.OAK_LEAVES.getDefaultState().with(LeavesBlock.PERSISTENT, true), 19);
	                    		for (Direction face : Direction.values())
	                    		{
	                    			if (world.getBlockState(leafPos.offset(face)).getBlock() == BOPBlocks.bramble)
	                    			{
	                    				world.setBlockState(leafPos.offset(face), ((BlockBramble)BOPBlocks.bramble).makeConnections(world, leafPos.offset(face)), 2);
	                    			}
	                    		}
	                    	}
	                    }
	                	
            	    	switch (rand.nextInt(6))
            	    	{
            	    		case 0: case 1:
            	    			if (height <= 8)
            	    			{
	            	    			genPos = genPos.up();
	            	    			height++;
            	    			}
            	    			break;
            	    		case 2:
            	    			if (height >= 0)
            	    			{
	            	    			genPos = genPos.down();
	            	    			height--;
            	    			}
            	    			break;
            	    		default:
            	    			genPos = genPos.offset(Direction.values()[direction]);
            	    			break;
            	    	}
                	//}
                	//else
                	//{
                		//return false;
                	//}
                }
            }
        }
        return true;
    }
}
