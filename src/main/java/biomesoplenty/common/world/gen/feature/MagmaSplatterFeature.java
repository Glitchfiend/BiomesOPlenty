package biomesoplenty.common.world.gen.feature;

import biomesoplenty.api.block.BOPBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.Random;

public class MagmaSplatterFeature extends Feature<NoneFeatureConfiguration>
{
   public MagmaSplatterFeature(Codec<NoneFeatureConfiguration> deserializer)
   {
      super(deserializer);
   }

   @Override
   public boolean place(WorldGenLevel worldIn, ChunkGenerator chunkGenerator, Random rand, BlockPos pos, NoneFeatureConfiguration config)
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

                  if (rand.nextInt(6) != 0)
                  {
                     if (blockstate.getBlock() == Blocks.GRASS_BLOCK && (blockstate1.canBeReplacedByLeaves(worldIn, blockpos.above()) || blockstate1.getBlock() instanceof BushBlock))
                     {
                        worldIn.setBlock(blockpos, Blocks.MAGMA_BLOCK.defaultBlockState(), 2);
                        worldIn.setBlock(blockpos.above(), Blocks.FIRE.defaultBlockState(), 2);
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