package biomesoplenty.common.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.StructureManager;

import java.util.Random;

public class ScatteredRocksFeature extends Feature<NoFeatureConfig>
{
   public ScatteredRocksFeature(Codec<NoFeatureConfig> deserializer)
   {
      super(deserializer);
   }

   @Override
   public boolean place(ISeedReader world, ChunkGenerator chunkGenerator, Random rand, BlockPos pos, NoFeatureConfig config)
   {
      int i = 0;

      for(int j = 0; j < 32; ++j)
      {
         BlockPos blockpos = pos.offset(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
         if (world.getBlockState(blockpos).canBeReplacedByLeaves(world, blockpos) && world.getBlockState(blockpos.below()).getBlock() == Blocks.GRASS_BLOCK)
         {

            if (rand.nextInt(3) == 0)
            {
               world.setBlock(blockpos, Blocks.MOSSY_COBBLESTONE.defaultBlockState(), 2);
            }
            else
            {
               world.setBlock(blockpos, Blocks.COBBLESTONE.defaultBlockState(), 2);
            }
            
            ++i;
         }
      }

      return i > 0;
   }
}