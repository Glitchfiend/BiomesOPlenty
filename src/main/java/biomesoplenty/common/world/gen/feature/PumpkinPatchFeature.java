package biomesoplenty.common.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.Blocks;
import net.minecraft.block.CarvedPumpkinBlock;
import net.minecraft.block.LeavesBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.StructureManager;

import java.util.Random;

public class PumpkinPatchFeature extends Feature<NoFeatureConfig>
{
	public PumpkinPatchFeature(Codec<NoFeatureConfig> deserializer)
	{
		super(deserializer);
	}

	@Override
	public boolean place(ISeedReader world, ChunkGenerator chunkGenerator, Random rand, BlockPos pos, NoFeatureConfig config)
	{
		int i = 0;

		for(int j = 0; j < 64; ++j)
		{
			BlockPos blockpos = pos.offset(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
			if (world.getBlockState(blockpos).canBeReplacedByLeaves(world, blockpos) && world.getBlockState(blockpos.below()).getBlock() == Blocks.GRASS_BLOCK)
			{

				if (rand.nextInt(3) == 0)
				{
					int num = rand.nextInt(50);

					if (num > 10)
					{
						world.setBlock(blockpos, Blocks.PUMPKIN.defaultBlockState(), 2);
					}
					else if (num > 1)
					{
						world.setBlock(blockpos, Blocks.CARVED_PUMPKIN.defaultBlockState().setValue(CarvedPumpkinBlock.FACING, Direction.from3DDataValue(2 + rand.nextInt(4))), 2);
					}
					else
					{
						world.setBlock(blockpos, Blocks.JACK_O_LANTERN.defaultBlockState().setValue(CarvedPumpkinBlock.FACING, Direction.from3DDataValue(2 + rand.nextInt(4))), 2);
					}
				}
				else
				{
					world.setBlock(blockpos, Blocks.OAK_LEAVES.defaultBlockState().setValue(LeavesBlock.PERSISTENT, true), 2);
				}

				++i;
			}
		}

		return i > 0;
	}
}