package biomesoplenty.common.world.gen.feature;

import com.mojang.datafixers.Dynamic;
import net.minecraft.block.Blocks;
import net.minecraft.block.NetherWartBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;
import java.util.function.Function;

public class NetherWartFeature extends Feature<NoFeatureConfig>
{
   public NetherWartFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> deserializer)
   {
      super(deserializer);
   }

   @Override
   public boolean place(IWorld p_212245_1_, ChunkGenerator<? extends GenerationSettings> p_212245_2_, Random p_212245_3_, BlockPos p_212245_4_, NoFeatureConfig p_212245_5_)
   {
      int i = 0;

      for(int j = 0; j < 64; ++j)
      {
         BlockPos blockpos = p_212245_4_.offset(p_212245_3_.nextInt(4) - p_212245_3_.nextInt(4), p_212245_3_.nextInt(4) - p_212245_3_.nextInt(4), p_212245_3_.nextInt(4) - p_212245_3_.nextInt(4));
         if (p_212245_1_.getBlockState(blockpos).isAir(p_212245_1_, blockpos) && p_212245_1_.getBlockState(blockpos.below()).getBlock() == Blocks.NETHERRACK && p_212245_1_.getBlockState(blockpos.below().east()).getBlock() == Blocks.NETHERRACK && p_212245_1_.getBlockState(blockpos.below().west()).getBlock() == Blocks.NETHERRACK && p_212245_1_.getBlockState(blockpos.below().north()).getBlock() == Blocks.NETHERRACK && p_212245_1_.getBlockState(blockpos.below().south()).getBlock() == Blocks.NETHERRACK)
         {
            p_212245_1_.setBlock(blockpos.below(), Blocks.SOUL_SAND.defaultBlockState(), 2);
        	p_212245_1_.setBlock(blockpos, Blocks.NETHER_WART.defaultBlockState().setValue(NetherWartBlock.AGE, p_212245_3_.nextInt(4)), 2);
            
            ++i;
         }
      }

      return i > 0;
   }
}