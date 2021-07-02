package biomesoplenty.common.world.gen.feature;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.block.NetherCrystalBlock;
import com.mojang.serialization.Codec;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.gen.feature.structure.StructureManager;

import java.util.Random;

public class SmallCrystalFeature extends Feature<NoneFeatureConfiguration>
{
   public SmallCrystalFeature(Codec<NoneFeatureConfiguration> deserializer)
   {
      super(deserializer);
   }

   @Override
   public boolean place(WorldGenLevel world, ChunkGenerator chunkGenerator, Random rand, BlockPos pos, NoneFeatureConfiguration config)
   {
      int i = 0;

      for(int j = 0; j < 128; ++j)
      {
         Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(rand);
         AttachFace face;
         switch (rand.nextInt(3))
         {
            default:
            case 0:
               face = AttachFace.FLOOR;
               break;

            case 1:
               face = AttachFace.CEILING;
               break;

            case 2:
               face = AttachFace.WALL;
               break;
         }

         BlockState state = BOPBlocks.nether_crystal.defaultBlockState().setValue(NetherCrystalBlock.FACING, direction).setValue(NetherCrystalBlock.FACE, face);
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