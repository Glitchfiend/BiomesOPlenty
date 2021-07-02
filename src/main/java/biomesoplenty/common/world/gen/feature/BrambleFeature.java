/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.world.gen.feature;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.block.BrambleBlock;
import biomesoplenty.common.util.biome.GeneratorUtil;
import biomesoplenty.common.util.block.IBlockPosQuery;
import com.mojang.serialization.Codec;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.material.Material;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.gen.feature.structure.StructureManager;

import java.util.Random;

public class BrambleFeature extends Feature<NoneFeatureConfiguration>
{
	public BrambleFeature(Codec<NoneFeatureConfiguration> deserializer)
	{
		super(deserializer);
	}

	protected IBlockPosQuery placeOn = (world, pos) ->
    {
        BlockState state = world.getBlockState(pos);
        return state.canSustainPlant(world, pos, Direction.UP, (SaplingBlock)Blocks.OAK_SAPLING) || state.getBlock() == Blocks.NETHERRACK;
    };
    
    protected IBlockPosQuery replace = (world, pos) -> world.getBlockState(pos).getMaterial() == Material.AIR;

	@Override
	public boolean place(WorldGenLevel world, ChunkGenerator chunkGenerator, Random rand, BlockPos startPos, NoneFeatureConfiguration config)
    {
        for (int i = 0; i < 128; ++i)
        {
            BlockPos genPos = startPos.offset(rand.nextInt(4) - rand.nextInt(4), rand.nextInt(3) - rand.nextInt(3), rand.nextInt(4) - rand.nextInt(4));
                
            if (this.placeOn.matches(world, genPos.below()) && this.replace.matches(world, genPos))
            {
                int targetLength = GeneratorUtil.nextIntBetween(rand, 15, 30);
                int height = 0;
                int direction = rand.nextInt(4) + 2;
                
                for (int length = 0; length <= targetLength && replace.matches(world, genPos); length++)
                {  
                	//if (BrambleBlock.canPlaceBlockAt(world, genPos))
                	//{
                		world.setBlock(genPos, ((BrambleBlock)BOPBlocks.bramble).makeConnections(world, genPos), 2);

                		for (Direction face : Direction.values())
                		{
                			if (world.getBlockState(genPos.relative(face)).getBlock() == BOPBlocks.bramble)
                			{
                				world.setBlock(genPos.relative(face), ((BrambleBlock)BOPBlocks.bramble).makeConnections(world, genPos.relative(face)), 2);
                			}
                		}
	                    
	                    if (rand.nextInt(2) == 0)
	                	{
	            	    	direction = rand.nextInt(4) + 2;
	                	}
	                    
	                    if (rand.nextInt(2) == 0)
	                    {
	                    	int leafDirection = rand.nextInt(6);
	                    	BlockPos leafPos = genPos.relative(Direction.values()[leafDirection]);
	                    	if (world.isEmptyBlock(leafPos))
	                    	{
	                    		world.setBlock(leafPos, Blocks.OAK_LEAVES.defaultBlockState().setValue(LeavesBlock.PERSISTENT, true), 19);
	                    		for (Direction face : Direction.values())
	                    		{
	                    			if (world.getBlockState(leafPos.relative(face)).getBlock() == BOPBlocks.bramble)
	                    			{
	                    				world.setBlock(leafPos.relative(face), ((BrambleBlock)BOPBlocks.bramble).makeConnections(world, leafPos.relative(face)), 2);
	                    			}
	                    		}
	                    	}
	                    }
	                	
            	    	switch (rand.nextInt(6))
            	    	{
            	    		case 0: case 1:
            	    			if (height <= 8)
            	    			{
	            	    			genPos = genPos.above();
	            	    			height++;
            	    			}
            	    			break;
            	    		case 2:
            	    			if (height >= 0)
            	    			{
	            	    			genPos = genPos.below();
	            	    			height--;
            	    			}
            	    			break;
            	    		default:
            	    			genPos = genPos.relative(Direction.values()[direction]);
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
