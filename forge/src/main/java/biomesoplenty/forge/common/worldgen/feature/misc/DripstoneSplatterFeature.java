/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.forge.common.worldgen.feature.misc;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.PointedDripstoneBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DripstoneThickness;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class DripstoneSplatterFeature extends Feature<NoneFeatureConfiguration>
{
   public DripstoneSplatterFeature(Codec<NoneFeatureConfiguration> deserializer)
   {
      super(deserializer);
   }

   @Override
   public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> featurePlaceContext)
   {
      WorldGenLevel worldIn = featurePlaceContext.level();
      ChunkGenerator chunkGenerator = featurePlaceContext.chunkGenerator();
      RandomSource rand = featurePlaceContext.random();
      BlockPos pos = featurePlaceContext.origin();
      NoneFeatureConfiguration config = featurePlaceContext.config();
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

                  if (blockstate.getBlock() == Blocks.GRASS_BLOCK && this.isAir(worldIn, blockpos.above()))
                  {
                     worldIn.setBlock(blockpos, Blocks.DRIPSTONE_BLOCK.defaultBlockState(), 2);
                     if (rand.nextInt(5) == 0)
                     {
                        int height = 1 + rand.nextInt(8);
                        for (int ii = height; ii > 0; ii--)
                        {
                           BlockPos dripPos = blockpos.above(ii);
                           worldIn.setBlock(dripPos, Blocks.POINTED_DRIPSTONE.defaultBlockState().setValue(PointedDripstoneBlock.THICKNESS,calculateDripstoneThickness(worldIn, dripPos, Direction.UP, false)), 2);
                        }
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

   private static DripstoneThickness calculateDripstoneThickness(LevelReader p_154093_, BlockPos p_154094_, Direction p_154095_, boolean p_154096_) {
      Direction direction = p_154095_.getOpposite();
      BlockState blockstate = p_154093_.getBlockState(p_154094_.relative(p_154095_));
      if (isPointedDripstoneWithDirection(blockstate, direction)) {
         return !p_154096_ && blockstate.getValue(PointedDripstoneBlock.THICKNESS) != DripstoneThickness.TIP_MERGE ? DripstoneThickness.TIP : DripstoneThickness.TIP_MERGE;
      } else if (!isPointedDripstoneWithDirection(blockstate, p_154095_)) {
         return DripstoneThickness.TIP;
      } else {
         DripstoneThickness dripstonethickness = blockstate.getValue(PointedDripstoneBlock.THICKNESS);
         if (dripstonethickness != DripstoneThickness.TIP && dripstonethickness != DripstoneThickness.TIP_MERGE) {
            BlockState blockstate1 = p_154093_.getBlockState(p_154094_.relative(direction));
            return !isPointedDripstoneWithDirection(blockstate1, p_154095_) ? DripstoneThickness.BASE : DripstoneThickness.MIDDLE;
         } else {
            return DripstoneThickness.FRUSTUM;
         }
      }
   }

   private static boolean isPointedDripstoneWithDirection(BlockState p_154208_, Direction p_154209_) {
      return p_154208_.is(Blocks.POINTED_DRIPSTONE) && p_154208_.getValue(PointedDripstoneBlock.TIP_DIRECTION) == p_154209_;
   }

   public static boolean isAir(LevelSimulatedReader level, BlockPos pos)
   {
      return level.isStateAtPosition(pos, BlockBehaviour.BlockStateBase::isAir);
   }
}