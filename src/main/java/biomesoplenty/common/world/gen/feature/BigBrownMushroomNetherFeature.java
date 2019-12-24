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

public class BigBrownMushroomNetherFeature extends Feature<BigMushroomFeatureConfig> {
   public BigBrownMushroomNetherFeature(Function<Dynamic<?>, ? extends BigMushroomFeatureConfig> p_i49864_1_) {
      super(p_i49864_1_);
   }

   public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, BigMushroomFeatureConfig config) {
      int i = rand.nextInt(3) + 4;
      if (rand.nextInt(12) == 0) {
         i *= 2;
      }

      int j = pos.getY();
      if (j >= 1 && j + i + 1 < worldIn.getWorld().getDimension().getHeight()) {
         Block block = worldIn.getBlockState(pos.down()).getBlock();
         if (!Block.isDirt(block) && block != Blocks.NETHERRACK && block != Blocks.SOUL_SAND) {
            return false;
         } else {
            BlockPos.Mutable blockpos$mutableblockpos = new BlockPos.Mutable();

            for(int k = 0; k <= 1 + i; ++k) {
               int l = k <= 3 ? 0 : 3;

               for(int i1 = -l; i1 <= l; ++i1) {
                  for(int j1 = -l; j1 <= l; ++j1) {
                     BlockState blockstate = worldIn.getBlockState(blockpos$mutableblockpos.setPos(pos).move(i1, k, j1));
                     if (!blockstate.isAir(worldIn, blockpos$mutableblockpos) && !blockstate.isIn(BlockTags.LEAVES)) {
                        return false;
                     }
                  }
               }
            }

            BlockState blockstate1 = Blocks.BROWN_MUSHROOM_BLOCK.getDefaultState().with(HugeMushroomBlock.UP, Boolean.valueOf(true)).with(HugeMushroomBlock.DOWN, Boolean.valueOf(false));
            int k1 = 3;

            for(int l1 = -3; l1 <= 3; ++l1) {
               for(int i2 = -3; i2 <= 3; ++i2) {
                  boolean flag9 = l1 == -3;
                  boolean flag = l1 == 3;
                  boolean flag1 = i2 == -3;
                  boolean flag2 = i2 == 3;
                  boolean flag3 = flag9 || flag;
                  boolean flag4 = flag1 || flag2;
                  if (!flag3 || !flag4) {
                     blockpos$mutableblockpos.setPos(pos).move(l1, i, i2);
                     if (worldIn.getBlockState(blockpos$mutableblockpos).canBeReplacedByLeaves(worldIn, blockpos$mutableblockpos)) {
                        boolean flag5 = flag9 || flag4 && l1 == -2;
                        boolean flag6 = flag || flag4 && l1 == 2;
                        boolean flag7 = flag1 || flag3 && i2 == -2;
                        boolean flag8 = flag2 || flag3 && i2 == 2;
                        this.setBlockState(worldIn, blockpos$mutableblockpos, blockstate1.with(HugeMushroomBlock.WEST, Boolean.valueOf(flag5)).with(HugeMushroomBlock.EAST, Boolean.valueOf(flag6)).with(HugeMushroomBlock.NORTH, Boolean.valueOf(flag7)).with(HugeMushroomBlock.SOUTH, Boolean.valueOf(flag8)));
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