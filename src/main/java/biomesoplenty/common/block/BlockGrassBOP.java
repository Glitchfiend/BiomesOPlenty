/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.block;

import java.util.Random;

import biomesoplenty.api.block.BOPBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReaderBase;
import net.minecraft.world.World;

public class BlockGrassBOP extends BlockGrass
{
    public BlockGrassBOP(Block.Properties properties)
    {
        super(properties);
    }
    
    private static boolean func_196383_a(IWorldReaderBase p_196383_0_, BlockPos p_196383_1_)
    {
        BlockPos blockpos = p_196383_1_.up();
        return p_196383_0_.getLight(blockpos) >= 4 || p_196383_0_.getBlockState(blockpos).getOpacity(p_196383_0_, blockpos) < p_196383_0_.getMaxLightLevel();
    }
    
    private static boolean func_196384_b(IWorldReaderBase p_196384_0_, BlockPos p_196384_1_)
    {
        BlockPos blockpos = p_196384_1_.up();
        return p_196384_0_.getLight(blockpos) >= 4 && p_196384_0_.getBlockState(blockpos).getOpacity(p_196384_0_, blockpos) < p_196384_0_.getMaxLightLevel() && !p_196384_0_.getFluidState(blockpos).isTagged(FluidTags.WATER);
    }
    
    @Override
    public void tick(IBlockState state, World worldIn, BlockPos pos, Random random)
    {
        if (!worldIn.isRemote)
        {
           if (!worldIn.isAreaLoaded(pos, 3))
           {
        	   return; // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading
           }
           if (!func_196383_a(worldIn, pos))
           {
        	  if (this == BOPBlocks.loamy_grass_block)
        	  	 {
        		  worldIn.setBlockState(pos, BOPBlocks.loamy_dirt.getDefaultState());
        	  	 }
        	  if (this == BOPBlocks.silty_grass_block)
     	  	 	 {
        		 worldIn.setBlockState(pos, BOPBlocks.silty_dirt.getDefaultState());
     	  	 	 }
        	  if (this == BOPBlocks.sandy_grass_block)
     	  	 	 {
        		 worldIn.setBlockState(pos, BOPBlocks.sandy_dirt.getDefaultState());
     	  	 	 }
           }
           else
           {
              if (worldIn.getLight(pos.up()) >= 9)
              {
                 for(int i = 0; i < 4; ++i)
                 {
                    BlockPos blockpos = pos.add(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
                    if (!worldIn.isBlockPresent(blockpos))
                    {
                       return;
                    }

                    if ((worldIn.getBlockState(blockpos).getBlock() == Blocks.DIRT) && func_196384_b(worldIn, blockpos))
                    {
                       worldIn.setBlockState(blockpos, Blocks.GRASS_BLOCK.getDefaultState());
                    }
                    if ((worldIn.getBlockState(blockpos).getBlock() == BOPBlocks.loamy_dirt) && func_196384_b(worldIn, blockpos))
                    {
                       worldIn.setBlockState(blockpos, BOPBlocks.loamy_grass_block.getDefaultState());
                    }
                    if ((worldIn.getBlockState(blockpos).getBlock() == BOPBlocks.silty_dirt) && func_196384_b(worldIn, blockpos))
                    {
                       worldIn.setBlockState(blockpos, BOPBlocks.silty_grass_block.getDefaultState());
                    }
                    if ((worldIn.getBlockState(blockpos).getBlock() == BOPBlocks.sandy_dirt) && func_196384_b(worldIn, blockpos))
                    {
                       worldIn.setBlockState(blockpos, BOPBlocks.sandy_grass_block.getDefaultState());
                    }
                }
             }
          }
       }
    }
    
    @Override
    public boolean canSustainPlant(IBlockState state, IBlockReader world, BlockPos pos, EnumFacing facing, net.minecraftforge.common.IPlantable plantable) {
        net.minecraftforge.common.EnumPlantType type = plantable.getPlantType(world, pos.offset(facing));

        switch (type) {
            case Desert: return false;
            case Nether: return false;
            case Crop: return false;
            case Cave: return true;
            case Plains: return true;
            case Water: return false;
            case Beach:
                boolean isBeach = true;
                boolean hasWater = (world.getBlockState(pos.east()).getMaterial() == Material.WATER ||
                        world.getBlockState(pos.west()).getMaterial() == Material.WATER ||
                        world.getBlockState(pos.north()).getMaterial() == Material.WATER ||
                        world.getBlockState(pos.south()).getMaterial() == Material.WATER);
                return isBeach && hasWater;
        }
        return false;
    }
}
