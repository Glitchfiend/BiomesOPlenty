/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.worldgen.feature.misc;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
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

import java.util.function.Consumer;

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

                  if (blockstate.getBlock() == Blocks.GRASS_BLOCK && this.isAir(worldIn, blockpos.above()))
                  {
                     worldIn.setBlock(blockpos, Blocks.DRIPSTONE_BLOCK.defaultBlockState(), 2);
                     if (rand.nextInt(5) == 0)
                     {
                        int height = 1 + rand.nextInt(8);
                        growPointedDripstone(worldIn, blockpos.above(), Direction.UP, height, false);
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

   protected static void growPointedDripstone(LevelAccessor p_190848_, BlockPos p_190849_, Direction p_190850_, int p_190851_, boolean p_190852_)
   {
      BlockPos.MutableBlockPos blockpos$mutableblockpos = p_190849_.mutable();
      buildBaseToTipColumn(p_190850_, p_190851_, p_190852_, (p_309326_) -> {
         if (p_309326_.is(Blocks.POINTED_DRIPSTONE))
         {
            p_309326_ = p_309326_.setValue(PointedDripstoneBlock.WATERLOGGED, Boolean.valueOf(p_190848_.isWaterAt(blockpos$mutableblockpos)));
         }

         p_190848_.setBlock(blockpos$mutableblockpos, p_309326_, 2);
         blockpos$mutableblockpos.move(p_190850_);
      });
   }

   protected static void buildBaseToTipColumn(Direction p_159652_, int p_159653_, boolean p_159654_, Consumer<BlockState> p_159655_)
   {
      if (p_159653_ >= 3)
      {
         p_159655_.accept(createPointedDripstone(p_159652_, DripstoneThickness.BASE));

         for(int i = 0; i < p_159653_ - 3; ++i)
         {
            p_159655_.accept(createPointedDripstone(p_159652_, DripstoneThickness.MIDDLE));
         }
      }

      if (p_159653_ >= 2)
      {
         p_159655_.accept(createPointedDripstone(p_159652_, DripstoneThickness.FRUSTUM));
      }

      if (p_159653_ >= 1)
      {
         p_159655_.accept(createPointedDripstone(p_159652_, p_159654_ ? DripstoneThickness.TIP_MERGE : DripstoneThickness.TIP));
      }
   }

   private static BlockState createPointedDripstone(Direction p_159657_, DripstoneThickness p_159658_)
   {
      return Blocks.POINTED_DRIPSTONE.defaultBlockState().setValue(PointedDripstoneBlock.TIP_DIRECTION, p_159657_).setValue(PointedDripstoneBlock.THICKNESS, p_159658_);
   }

   public static boolean isAir(LevelSimulatedReader level, BlockPos pos)
   {
      return level.isStateAtPosition(pos, BlockBehaviour.BlockStateBase::isAir);
   }
}