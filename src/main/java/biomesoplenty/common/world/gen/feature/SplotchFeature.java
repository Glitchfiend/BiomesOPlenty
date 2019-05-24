package biomesoplenty.common.world.gen.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.IChunkGenSettings;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.Feature;

public class SplotchFeature extends Feature<SplotchConfig>
{
   public boolean place(IWorld p_212245_1_, IChunkGenerator<? extends IChunkGenSettings> p_212245_2_, Random p_212245_3_, BlockPos p_212245_4_, SplotchConfig p_212245_5_)
   {
     int i = 0;
     int j = p_212245_3_.nextInt(p_212245_5_.radius - 2) + 2;

     for(int k = p_212245_4_.getX() - j; k <= p_212245_4_.getX() + j; ++k) {
        for(int l = p_212245_4_.getZ() - j; l <= p_212245_4_.getZ() + j; ++l) {
           int i1 = k - p_212245_4_.getX();
           int j1 = l - p_212245_4_.getZ();
           if (i1 * i1 + j1 * j1 <= j * j) {
              for(int k1 = p_212245_4_.getY() - p_212245_5_.ySize; k1 <= p_212245_4_.getY() + p_212245_5_.ySize; ++k1) {
                 BlockPos blockpos = new BlockPos(k, k1, l);
                 BlockPos blockpos1 = blockpos.north();
                 BlockPos blockpos2 = blockpos.south();
                 BlockPos blockpos3 = blockpos.east();
                 BlockPos blockpos4 = blockpos.west();
                 BlockPos blockpos5 = blockpos.north().east();
                 BlockPos blockpos6 = blockpos.south().east();
                 BlockPos blockpos7 = blockpos.north().west();
                 BlockPos blockpos8 = blockpos.south().west();
                 BlockPos blockpos9 = blockpos.down();
                 Block block = p_212245_1_.getBlockState(blockpos).getBlock();
                 Block block1 = p_212245_1_.getBlockState(blockpos1).getBlock();
                 Block block2 = p_212245_1_.getBlockState(blockpos2).getBlock();
                 Block block3 = p_212245_1_.getBlockState(blockpos3).getBlock();
                 Block block4 = p_212245_1_.getBlockState(blockpos4).getBlock();
                 Block block5 = p_212245_1_.getBlockState(blockpos5).getBlock();
                 Block block6 = p_212245_1_.getBlockState(blockpos6).getBlock();
                 Block block7 = p_212245_1_.getBlockState(blockpos7).getBlock();
                 Block block8 = p_212245_1_.getBlockState(blockpos8).getBlock();
                 Block block9 = p_212245_1_.getBlockState(blockpos9).getBlock();
                 
                 if (p_212245_5_.targets.contains(block) && p_212245_5_.targets.contains(block1) && p_212245_5_.targets.contains(block2) && p_212245_5_.targets.contains(block3) && p_212245_5_.targets.contains(block4) && p_212245_5_.targets.contains(block5) && p_212245_5_.targets.contains(block6) && p_212245_5_.targets.contains(block7) && p_212245_5_.targets.contains(block8) && p_212245_5_.targets.contains(block9))
                 {
                    p_212245_1_.setBlockState(blockpos, p_212245_5_.block.getDefaultState(), 2);
                    ++i;
                 }
              }
           }
        }
     }

     return i > 0;
   }
}