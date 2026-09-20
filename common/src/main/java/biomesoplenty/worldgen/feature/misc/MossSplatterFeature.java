/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.worldgen.feature.misc;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.VegetationBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import com.mojang.serialization.MapCodec;

public class MossSplatterFeature implements Feature
{
   public static final MapCodec<MossSplatterFeature> CODEC = MapCodec.unit(MossSplatterFeature::new);

    @Override
    public MapCodec<MossSplatterFeature> codec()
    {
        return CODEC;
    }


   @Override
   public boolean place(WorldGenLevel worldIn, ChunkGenerator chunkGenerator, RandomSource rand, BlockPos pos)
   {
      int i = 0;
      int j = rand.nextInt(8 - 2) + 2;

      for (int k = pos.getX() - j; k <= pos.getX() + j; ++k)
      {
         for (int l = pos.getZ() - j; l <= pos.getZ() + j; ++l)
         {
            int i1 = k - pos.getX();
            int j1 = l - pos.getZ();
            if (i1 * i1 + j1 * j1 <= j * j)
            {
               for (int k1 = pos.getY() - 2; k1 <= pos.getY() + 2; ++k1)
               {
                  BlockPos blockpos = new BlockPos(k, k1, l);
                  BlockState blockstate = worldIn.getBlockState(blockpos);
                  BlockState blockstate1 = worldIn.getBlockState(blockpos.above());

                  if (blockstate.is(BlockTags.MOSS_REPLACEABLE) && (this.isAir(worldIn, blockpos.above()) || blockstate1.getBlock() instanceof VegetationBlock))
                  {
                     worldIn.setBlock(blockpos, Blocks.MOSS_BLOCK.defaultBlockState(), 2);
                     switch (rand.nextInt(4))
                     {
                        case 2:
                           worldIn.setBlock(blockpos.above(), Blocks.SHORT_GRASS.defaultBlockState(), 2);
                           break;

                        case 1:
                           worldIn.setBlock(blockpos.above(), Blocks.MOSS_CARPET.defaultBlockState(), 2);
                           break;

                        case 0:
                        default:
                           worldIn.setBlock(blockpos.above(), Blocks.AIR.defaultBlockState(), 2);
                           break;
                     }

                     if (worldIn.getBlockState(blockpos.above(2)).getBlock() instanceof DoublePlantBlock)
                     {
                        worldIn.setBlock(blockpos.above(2), Blocks.AIR.defaultBlockState(), 2);
                     }

                     ++i;
                     break;
                  }
               }
            }
         }
      }

      return i > 0;
   }

   public static boolean isAir(LevelSimulatedReader level, BlockPos pos)
   {
      return level.isStateAtPosition(pos, BlockBehaviour.BlockStateBase::isAir);
   }
}