package biomesoplenty.common.world.gen.placement;

import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import biomesoplenty.common.world.AlphaOctavePerlinNoise;
import com.mojang.serialization.Codec;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.WorldDecoratingHelper;
import net.minecraft.world.gen.placement.NoPlacementConfig;
import net.minecraft.world.gen.placement.Placement;

public class AlphaTreePlacement extends Placement<NoPlacementConfig>
{
	private AlphaOctavePerlinNoise treeNoise;
	private long seed;

	public AlphaTreePlacement(Codec<NoPlacementConfig> codec)
	{
		super(codec);
	}

	@Override
	public Stream<BlockPos> getPositions(WorldDecoratingHelper decoratorContext, Random random, NoPlacementConfig config, BlockPos pos)
	{
		// If the seed has changed, then re-initialize the noise.
		long seed = decoratorContext.level.getSeed();
		if (this.treeNoise == null || seed != this.seed) {
			this.treeNoise = new AlphaOctavePerlinNoise(new Random(seed), 8);
			this.seed = seed;
		}

		// Sample the tree gen noise for the base amount of trees in this chunk
		int treeCount = (int)((this.treeNoise.sample((double) pos.getX() * 0.5D, (double) pos.getZ() * 0.5D) / 8.0D + random.nextDouble() * 4.0D + 4.0D) / 3.0D);

		// Add extra trees randomly
		if (random.nextInt(10) == 0) {
			treeCount++;
		}

		// Map to position
		return IntStream.range(0, treeCount).mapToObj((idx) -> pos);
	}
}
