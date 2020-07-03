package biomesoplenty.common.world.gen.feature;

import com.mojang.datafixers.Dynamic;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;
import java.util.function.Function;

public class ScatteredRocksFeature extends Feature<NoFeatureConfig>
{
   public ScatteredRocksFeature(Codec<NoFeatureConfig> deserializer)
   {
      super(deserializer);
   }

   @Override
   public boolean place(IWorld p_212245_1_, ChunkGenerator<? extends GenerationSettings> p_212245_2_, Random p_212245_3_, BlockPos p_212245_4_, NoFeatureConfig p_212245_5_)
   {
      int i = 0;

      for(int j = 0; j < 32; ++j)
      {
         BlockPos blockpos = p_212245_4_.offset(p_212245_3_.nextInt(8) - p_212245_3_.nextInt(8), p_212245_3_.nextInt(4) - p_212245_3_.nextInt(4), p_212245_3_.nextInt(8) - p_212245_3_.nextInt(8));
         if (p_212245_1_.getBlockState(blockpos).canBeReplacedByLeaves(p_212245_1_, blockpos) && p_212245_1_.getBlockState(blockpos.below()).getBlock() == Blocks.GRASS_BLOCK)
         {

            if (p_212245_3_.nextInt(3) == 0)
            {
               p_212245_1_.setBlock(blockpos, Blocks.MOSSY_COBBLESTONE.defaultBlockState(), 2);
            }
            else
            {
               p_212245_1_.setBlock(blockpos, Blocks.COBBLESTONE.defaultBlockState(), 2);
            }
            
            ++i;
         }
      }

      return i > 0;
   }
}