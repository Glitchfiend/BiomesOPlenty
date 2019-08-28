package biomesoplenty.common.world.gen.feature;

import biomesoplenty.api.block.BOPBlocks;
import com.mojang.datafixers.Dynamic;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;
import java.util.function.Function;

public class AshSplatterFeature extends Feature<NoFeatureConfig> {
   public AshSplatterFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> deserializer) {
      super(deserializer);
   }

   @Override
   public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> p_212245_2_, Random rand, BlockPos pos, NoFeatureConfig config)
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
                  BlockState blockstate1 = worldIn.getBlockState(blockpos.up());

                  if (blockstate.getBlock() == Blocks.NETHERRACK && blockstate1.isAir(worldIn, blockpos.up()))
                  {
                     worldIn.setBlockState(blockpos, BOPBlocks.ash_block.getDefaultState(), 2);
                     ++i;
                     break;
                  }
               }
            }
         }
      }

      return i > 0;
   }
}