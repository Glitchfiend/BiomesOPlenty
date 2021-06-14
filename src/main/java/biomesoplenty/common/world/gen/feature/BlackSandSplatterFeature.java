package biomesoplenty.common.world.gen.feature;

import biomesoplenty.api.block.BOPBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

public class BlackSandSplatterFeature extends Feature<NoFeatureConfig>
{
   public BlackSandSplatterFeature(Codec<NoFeatureConfig> deserializer)
   {
      super(deserializer);
   }

   @Override
   public boolean place(ISeedReader worldIn, ChunkGenerator chunkGenerator, Random rand, BlockPos pos, NoFeatureConfig config)
   {
      int i = 0;
      int j = rand.nextInt(2) + 1;

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

                  if (blockstate.getBlock() == Blocks.GRASS_BLOCK && blockstate1.isAir(worldIn, blockpos.above()))
                  {
                     worldIn.setBlock(blockpos, BOPBlocks.black_sand.defaultBlockState(), 2);
                     if (rand.nextInt(4) == 0)
                     {
                        worldIn.setBlock(blockpos.above(), Blocks.DEAD_BUSH.defaultBlockState(), 2);
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
}