package biomesoplenty.common.world.gen.feature;

import biomesoplenty.api.block.BOPBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.AmethystClusterBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.Random;

public class SmallCrystalFeature extends Feature<NoneFeatureConfiguration>
{
   public SmallCrystalFeature(Codec<NoneFeatureConfiguration> deserializer)
   {
      super(deserializer);
   }

   @Override
   public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> featurePlaceContext)
   {
      WorldGenLevel world = featurePlaceContext.level();
      ChunkGenerator chunkGenerator = featurePlaceContext.chunkGenerator();
      Random rand = featurePlaceContext.random();
      BlockPos pos = featurePlaceContext.origin();
      NoneFeatureConfiguration config = featurePlaceContext.config();
      int i = 0;

      for(int j = 0; j < 128; ++j)
      {
         Direction direction = Direction.getRandom(rand);

         BlockState cluster_state;
         switch (rand.nextInt(5))
         {
            case 3:
            default:
               cluster_state = BOPBlocks.ROSE_QUARTZ_CLUSTER.defaultBlockState();
               break;

            case 2:
               cluster_state = BOPBlocks.LARGE_ROSE_QUARTZ_BUD.defaultBlockState();
               break;

            case 1:
               cluster_state = BOPBlocks.MEDIUM_ROSE_QUARTZ_BUD.defaultBlockState();
               break;

            case 0:
               cluster_state = BOPBlocks.SMALL_ROSE_QUARTZ_BUD.defaultBlockState();
               break;
         }

         BlockState state = cluster_state.setValue(AmethystClusterBlock.FACING, direction);
         BlockPos blockpos = pos.offset(rand.nextInt(4) - rand.nextInt(4), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(4) - rand.nextInt(4));

         if (world.isEmptyBlock(blockpos) && state.canSurvive(world, blockpos))
         {
            world.setBlock(blockpos, state, 2);

            ++i;
         }
      }

      return i > 10;
   }
}