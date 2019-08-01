package biomesoplenty.common.world.gen.feature;

import biomesoplenty.api.block.BOPBlocks;
import com.mojang.datafixers.Dynamic;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;
import java.util.function.Function;

public class DeadCoralSplatterFeature extends Feature<NoFeatureConfig>
{
   public DeadCoralSplatterFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> deserializer)
   {
      super(deserializer);
   }

   @Override
   public boolean place(IWorld p_212245_1_, ChunkGenerator<? extends GenerationSettings> p_212245_2_, Random p_212245_3_, BlockPos p_212245_4_, NoFeatureConfig p_212245_5_)
   {
      int i = 0;

      for(int j = 0; j < 64; ++j)
      {
         BlockPos blockpos = p_212245_4_.add(p_212245_3_.nextInt(8) - p_212245_3_.nextInt(8), p_212245_3_.nextInt(4) - p_212245_3_.nextInt(4), p_212245_3_.nextInt(8) - p_212245_3_.nextInt(8));
         if (p_212245_1_.getBlockState(blockpos).isAir(p_212245_1_, p_212245_4_) && p_212245_1_.getBlockState(blockpos.down()).getBlock() == Blocks.END_STONE)
         {
            BlockState state;
            int rand = p_212245_3_.nextInt(5);

            switch (rand)
            {
               case 0:
               default:
                  state = Blocks.DEAD_BRAIN_CORAL_BLOCK.getDefaultState();
                  break;

               case 1:
                  state = Blocks.DEAD_BUBBLE_CORAL_BLOCK.getDefaultState();
                  break;

               case 2:
                  state = Blocks.DEAD_FIRE_CORAL_BLOCK.getDefaultState();
                  break;

               case 3:
                  state = Blocks.DEAD_HORN_CORAL_BLOCK.getDefaultState();
                  break;

               case 4:
                  state = Blocks.DEAD_TUBE_CORAL_BLOCK.getDefaultState();
                  break;
            }

        	p_212245_1_.setBlockState(blockpos.down(), state, 2);
            
            ++i;
         }
      }

      return i > 0;
   }
}