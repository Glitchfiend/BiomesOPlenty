package biomesoplenty.common.world.gen.feature;

import biomesoplenty.api.block.BOPBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.Random;

public class MossSplatterFeature extends Feature<NoneFeatureConfiguration>
{
   public MossSplatterFeature(Codec<NoneFeatureConfiguration> deserializer)
   {
      super(deserializer);
   }

   @Override
   public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> featurePlaceContext)
   {
      WorldGenLevel worldIn = featurePlaceContext.level();
      ChunkGenerator chunkGenerator = featurePlaceContext.chunkGenerator();
      Random rand = featurePlaceContext.random();
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

                  if (blockstate.is(BlockTags.MOSS_REPLACEABLE) && (this.isAir(worldIn, blockpos.above()) || blockstate1.getBlock() instanceof BushBlock))
                  {
                     worldIn.setBlock(blockpos, Blocks.MOSS_BLOCK.defaultBlockState(), 2);
                     switch (rand.nextInt(3))
                     {
                        case 2:
                           worldIn.setBlock(blockpos.above(), Blocks.GRASS.defaultBlockState(), 2);
                           break;

                        default:
                           worldIn.setBlock(blockpos.above(), Blocks.MOSS_CARPET.defaultBlockState(), 2);
                           break;
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