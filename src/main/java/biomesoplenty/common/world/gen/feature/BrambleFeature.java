/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.world.gen.feature;

import java.util.Random;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.block.BlockBramble;
import biomesoplenty.common.util.biome.GeneratorUtil;
import biomesoplenty.common.util.block.IBlockPosQuery;
import net.minecraft.block.BlockChorusPlant;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.IChunkGenSettings;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class BrambleFeature extends Feature<NoFeatureConfig>
{
    protected IBlockPosQuery placeOn = (world, pos) ->
    {
        IBlockState state = world.getBlockState(pos);
        return state.canSustainPlant(world, pos, EnumFacing.UP, (BlockSapling)Blocks.OAK_SAPLING) || state.getBlock() == Blocks.SOUL_SAND;
    };
    
    protected IBlockPosQuery replace = (world, pos) -> world.getBlockState(pos).getMaterial() == Material.AIR;

	@Override
	public boolean place(IWorld world, IChunkGenerator<? extends IChunkGenSettings> p_212245_2, Random rand, BlockPos startPos, NoFeatureConfig p_212245_5_)
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
	                    
	                    if (rand.nextInt(2) == 0)
	                	{
	            	    	direction = rand.nextInt(4) + 2;
	                	}
	                    
	                    if (rand.nextInt(2) == 0)
	                    {
	                    	int leafDirection = rand.nextInt(6);
	                    	if (world.isAirBlock(genPos.offset(EnumFacing.values()[leafDirection])))
	                    	{
	                    		world.setBlockState(genPos.offset(EnumFacing.values()[leafDirection]), Blocks.OAK_LEAVES.getDefaultState().with(BlockLeaves.PERSISTENT, true), 19);
	                    	}
	                    }
	                	
            	    	switch (rand.nextInt(6))
            	    	{
            	    		case 0: case 1:
            	    			if (height <= 6)
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
            	    			genPos = genPos.offset(EnumFacing.values()[direction]);
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
