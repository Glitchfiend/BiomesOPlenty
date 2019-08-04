package biomesoplenty.common.world.gen.feature;

import biomesoplenty.api.block.BOPBlocks;
import com.mojang.datafixers.Dynamic;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
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

      for(int j = 0; j < 16; ++j)
      {
         BlockPos blockpos = p_212245_4_.add(p_212245_3_.nextInt(8) - p_212245_3_.nextInt(8), p_212245_3_.nextInt(4) - p_212245_3_.nextInt(4), p_212245_3_.nextInt(8) - p_212245_3_.nextInt(8));
         if (p_212245_1_.getBlockState(blockpos).isAir(p_212245_1_, blockpos) && p_212245_1_.getBlockState(blockpos.down()).getBlock() == Blocks.NETHERRACK)
         {
            p_212245_1_.setBlockState(blockpos.down(), Blocks.SOUL_SAND.getDefaultState(), 2);
        	p_212245_1_.setBlockState(blockpos, Blocks.NETHER_WART.getDefaultState().with(NetherWartBlock.AGE, p_212245_3_.nextInt(4)), 2);
            
            ++i;
         }
      }

      return i > 0;
   }
}