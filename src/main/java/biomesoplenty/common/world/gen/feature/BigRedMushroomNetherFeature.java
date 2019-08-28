package biomesoplenty.common.world.gen.feature;

import com.mojang.datafixers.Dynamic;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HugeMushroomBlock;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.BigMushroomFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;
import java.util.function.Function;

public class BigRedMushroomNetherFeature extends Feature<BigMushroomFeatureConfig> {
   public BigRedMushroomNetherFeature(Function<Dynamic<?>, ? extends BigMushroomFeatureConfig> p_i49863_1_) {
      super(p_i49863_1_);
   }

   public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, BigMushroomFeatureConfig config) {
      int i = rand.nextInt(3) + 4;
      if (rand.nextInt(12) == 0) {
         i *= 2;
      }

      int j = pos.getY();
      if (j >= 1 && j + i + 1 < 256) {
         Block block = worldIn.getBlockState(pos.down()).getBlock();
         if (!Block.isDirt(block) && block != Blocks.NETHERRACK && block != Blocks.SOUL_SAND) {
            return false;
         } else {
            BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

            for(int k = 0; k <= i; ++k) {
               int l = 0;
               if (k < i && k >= i - 3) {
                  l = 2;
               } else if (k == i) {
                  l = 1;
               }

               for(int i1 = -l; i1 <= l; ++i1) {
                  for(int j1 = -l; j1 <= l; ++j1) {
                     BlockState blockstate = worldIn.getBlockState(blockpos$mutableblockpos.setPos(pos).move(i1, k, j1));
                     if (!blockstate.isAir(worldIn, blockpos$mutableblockpos) && !blockstate.isIn(BlockTags.LEAVES)) {
                        return false;
                     }
                  }
               }
            }

            BlockState blockstate1 = Blocks.RED_MUSHROOM_BLOCK.getDefaultState().with(HugeMushroomBlock.DOWN, Boolean.valueOf(false));

            for(int l1 = i - 3; l1 <= i; ++l1) {
               int i2 = l1 < i ? 2 : 1;
               int k2 = 0;

               for(int l2 = -i2; l2 <= i2; ++l2) {
                  for(int k1 = -i2; k1 <= i2; ++k1) {
                     boolean flag = l2 == -i2;
                     boolean flag1 = l2 == i2;
                     boolean flag2 = k1 == -i2;
                     boolean flag3 = k1 == i2;
                     boolean flag4 = flag || flag1;
                     boolean flag5 = flag2 || flag3;
                     if (l1 >= i || flag4 != flag5) {
                        blockpos$mutableblockpos.setPos(pos).move(l2, l1, k1);
                        if (worldIn.getBlockState(blockpos$mutableblockpos).canBeReplacedByLeaves(worldIn, blockpos$mutableblockpos)) {
                           this.setBlockState(worldIn, blockpos$mutableblockpos, blockstate1.with(HugeMushroomBlock.UP, Boolean.valueOf(l1 >= i - 1)).with(HugeMushroomBlock.WEST, Boolean.valueOf(l2 < 0)).with(HugeMushroomBlock.EAST, Boolean.valueOf(l2 > 0)).with(HugeMushroomBlock.NORTH, Boolean.valueOf(k1 < 0)).with(HugeMushroomBlock.SOUTH, Boolean.valueOf(k1 > 0)));
                        }
                     }
                  }
               }
            }

            BlockState blockstate2 = Blocks.MUSHROOM_STEM.getDefaultState().with(HugeMushroomBlock.UP, Boolean.valueOf(false)).with(HugeMushroomBlock.DOWN, Boolean.valueOf(false));

            for(int j2 = 0; j2 < i; ++j2) {
               blockpos$mutableblockpos.setPos(pos).move(Direction.UP, j2);
               if (worldIn.getBlockState(blockpos$mutableblockpos).canBeReplacedByLeaves(worldIn, blockpos$mutableblockpos)) {
                  if (config.planted) {
                     worldIn.setBlockState(blockpos$mutableblockpos, blockstate2, 3);
                  } else {
                     this.setBlockState(worldIn, blockpos$mutableblockpos, blockstate2);
                  }
               }
            }

            return true;
         }
      } else {
         return false;
      }
   }
}