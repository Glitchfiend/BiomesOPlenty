package biomesoplenty.common.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.Blocks;
import net.minecraft.block.NetherWartBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.StructureManager;

import java.util.Random;

public class NetherWartFeature extends Feature<NoFeatureConfig>
{
   public NetherWartFeature(Codec<NoFeatureConfig> deserializer)
   {
      super(deserializer);
   }

   @Override
   public boolean place(ISeedReader world, ChunkGenerator chunkGenerator, Random rand, BlockPos pos, NoFeatureConfig config)
   {
      int i = 0;

      for(int j = 0; j < 64; ++j)
      {
         BlockPos blockpos = pos.offset(rand.nextInt(4) - rand.nextInt(4), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(4) - rand.nextInt(4));
         if (world.getBlockState(blockpos).isAir(world, blockpos) && world.getBlockState(blockpos.below()).getBlock() == Blocks.NETHERRACK && world.getBlockState(blockpos.below().east()).getBlock() == Blocks.NETHERRACK && world.getBlockState(blockpos.below().west()).getBlock() == Blocks.NETHERRACK && world.getBlockState(blockpos.below().north()).getBlock() == Blocks.NETHERRACK && world.getBlockState(blockpos.below().south()).getBlock() == Blocks.NETHERRACK)
         {
            world.setBlock(blockpos.below(), Blocks.SOUL_SAND.defaultBlockState(), 2);
        	world.setBlock(blockpos, Blocks.NETHER_WART.defaultBlockState().setValue(NetherWartBlock.AGE, rand.nextInt(4)), 2);
            
            ++i;
         }
      }

      return i > 0;
   }
}