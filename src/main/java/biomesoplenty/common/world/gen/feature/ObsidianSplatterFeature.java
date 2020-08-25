package biomesoplenty.common.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.StructureManager;

import java.util.Random;

public class ObsidianSplatterFeature extends Feature<NoFeatureConfig>
{
   public ObsidianSplatterFeature(Codec<NoFeatureConfig> deserializer)
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

                  if (blockstate.getBlock() == Blocks.BLACKSTONE)
                  {
                     if (rand.nextInt(2) == 0)
                     {
                        worldIn.setBlock(blockpos, Blocks.OBSIDIAN.defaultBlockState(), 2);
                     }
                     if (rand.nextInt(15) == 0)
                     {
                        worldIn.setBlock(blockpos, Blocks.CRYING_OBSIDIAN.defaultBlockState(), 2);
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