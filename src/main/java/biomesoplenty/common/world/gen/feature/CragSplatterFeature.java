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

public class CragSplatterFeature extends Feature<NoFeatureConfig>
{
   public CragSplatterFeature(Codec<NoFeatureConfig> deserializer)
   {
      super(deserializer);
   }

   @Override
   public boolean place(ISeedReader worldIn, ChunkGenerator chunkGenerator, Random rand, BlockPos pos, NoFeatureConfig config)
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

                  if ((blockstate.getBlock() == Blocks.STONE || blockstate.getBlock() == Blocks.GRAVEL || blockstate.getBlock() == Blocks.ANDESITE || blockstate.getBlock() == Blocks.DIORITE || blockstate.getBlock() == Blocks.GRANITE || blockstate.getBlock() == Blocks.DIRT))
                  {
                     switch (rand.nextInt(14))
                     {
                        default:
                        case 0:
                        case 1:
                        case 2:
                           worldIn.setBlock(blockpos, Blocks.COBBLESTONE.defaultBlockState(), 2);
                           break;

                        case 3:
                        case 4:
                        case 5:
                           worldIn.setBlock(blockpos, Blocks.GRAVEL.defaultBlockState(), 2);
                           break;

                        case 6:
                        case 7:
                           worldIn.setBlock(blockpos, Blocks.MOSSY_COBBLESTONE.defaultBlockState(), 2);
                           break;

                        case 8:
                        case 9:
                           worldIn.setBlock(blockpos, Blocks.ANDESITE.defaultBlockState(), 2);
                           break;

                        case 10:
                           if (blockstate1.isAir(worldIn, blockpos.above()))
                              {
                                 worldIn.setBlock(blockpos, Blocks.GRASS_BLOCK.defaultBlockState(), 2);
                                 worldIn.setBlock(blockpos.above(), Blocks.GRASS.defaultBlockState(), 2);

                              }
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