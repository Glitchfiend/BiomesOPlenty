package biomesoplenty.common.world;

import biomesoplenty.api.biome.BOPBiomes;
import com.google.common.collect.Sets;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import net.minecraft.block.BlockState;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.biome.provider.EndBiomeProviderSettings;
import net.minecraft.world.gen.SimplexNoiseGenerator;
import net.minecraft.world.gen.feature.structure.Structure;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class BOPEndBiomeProvider extends BiomeProvider {
	private final SimplexNoiseGenerator generator;
	private final SharedSeedRandom random;
	private final Biome[] field_205009_d = new Biome[]{Biomes.THE_END, Biomes.END_HIGHLANDS, Biomes.END_MIDLANDS, Biomes.SMALL_END_ISLANDS, Biomes.END_BARRENS, BOPBiomes.dead_reef.get(), BOPBiomes.ethereal_forest.get()};

	public BOPEndBiomeProvider(EndBiomeProviderSettings p_i48970_1_) {
		this.random = new SharedSeedRandom(p_i48970_1_.getSeed());
		this.random.skip(17292);
		this.generator = new SimplexNoiseGenerator(this.random);
	}

	@Override
	public Biome getBiome(int p_201545_1_, int p_201545_2_)
	{
		int lvt_3_1_ = p_201545_1_ >> 4;
		int lvt_4_1_ = p_201545_2_ >> 4;
		if ((long)lvt_3_1_ * (long)lvt_3_1_ + (long)lvt_4_1_ * (long)lvt_4_1_ <= 4096L)
		{
			return Biomes.THE_END;
		}
		else
		{
			float lvt_5_1_ = this.func_222365_c(lvt_3_1_ * 2 + 1, lvt_4_1_ * 2 + 1);
			if (lvt_5_1_ > 40.0F)
			{
				//return Biomes.END_HIGHLANDS;
				return BOPBiomes.ethereal_forest.get();
			}
			else if (lvt_5_1_ >= 0.0F)
			{
				return Biomes.END_MIDLANDS;
			}
			else
			{
				return lvt_5_1_ < -20.0F ? Biomes.SMALL_END_ISLANDS : Biomes.END_BARRENS;
			}
		}
	}

	@Override
	public Biome[] getBiomes(int p_201537_1_, int p_201537_2_, int p_201537_3_, int p_201537_4_, boolean p_201537_5_) {
		Biome[] lvt_6_1_ = new Biome[p_201537_3_ * p_201537_4_];
		Long2ObjectMap<Biome> lvt_7_1_ = new Long2ObjectOpenHashMap();

		for(int lvt_8_1_ = 0; lvt_8_1_ < p_201537_3_; ++lvt_8_1_) {
			for(int lvt_9_1_ = 0; lvt_9_1_ < p_201537_4_; ++lvt_9_1_) {
				int lvt_10_1_ = lvt_8_1_ + p_201537_1_;
				int lvt_11_1_ = lvt_9_1_ + p_201537_2_;
				long lvt_12_1_ = ChunkPos.asLong(lvt_10_1_, lvt_11_1_);
				Biome lvt_14_1_ = lvt_7_1_.get(lvt_12_1_);
				if (lvt_14_1_ == null) {
					lvt_14_1_ = this.getBiome(lvt_10_1_, lvt_11_1_);
					lvt_7_1_.put(lvt_12_1_, lvt_14_1_);
				}

				lvt_6_1_[lvt_8_1_ + lvt_9_1_ * p_201537_3_] = lvt_14_1_;
			}
		}

		return lvt_6_1_;
	}

	@Override
	public Set<Biome> getBiomesInSquare(int centerX, int centerZ, int sideLength) {
		int i = centerX - sideLength >> 2;
		int j = centerZ - sideLength >> 2;
		int k = centerX + sideLength >> 2;
		int l = centerZ + sideLength >> 2;
		int i1 = k - i + 1;
		int j1 = l - j + 1;
		return Sets.newHashSet(this.getBiomeBlock(i, j, i1, j1));
	}

	@Nullable
	@Override
	public BlockPos findBiomePosition(int x, int z, int range, List<Biome> biomes, Random random) {
		int i = x - range >> 2;
		int j = z - range >> 2;
		int k = x + range >> 2;
		int l = z + range >> 2;
		int i1 = k - i + 1;
		int j1 = l - j + 1;
		Biome[] abiome = this.getBiomeBlock(i, j, i1, j1);
		BlockPos blockpos = null;
		int k1 = 0;

		for(int l1 = 0; l1 < i1 * j1; ++l1) {
			int i2 = i + l1 % i1 << 2;
			int j2 = j + l1 / i1 << 2;
			if (biomes.contains(abiome[l1])) {
				if (blockpos == null || random.nextInt(k1 + 1) == 0) {
					blockpos = new BlockPos(i2, 0, j2);
				}

				++k1;
			}
		}

		return blockpos;
	}

	@Override
	public float func_222365_c(int p_222365_1_, int p_222365_2_) {
		int lvt_3_1_ = p_222365_1_ / 2;
		int lvt_4_1_ = p_222365_2_ / 2;
		int lvt_5_1_ = p_222365_1_ % 2;
		int lvt_6_1_ = p_222365_2_ % 2;
		float lvt_7_1_ = 100.0F - MathHelper.sqrt((float)(p_222365_1_ * p_222365_1_ + p_222365_2_ * p_222365_2_)) * 8.0F;
		lvt_7_1_ = MathHelper.clamp(lvt_7_1_, -100.0F, 80.0F);

		for(int lvt_8_1_ = -12; lvt_8_1_ <= 12; ++lvt_8_1_) {
			for(int lvt_9_1_ = -12; lvt_9_1_ <= 12; ++lvt_9_1_) {
				long lvt_10_1_ = (long)(lvt_3_1_ + lvt_8_1_);
				long lvt_12_1_ = (long)(lvt_4_1_ + lvt_9_1_);
				if (lvt_10_1_ * lvt_10_1_ + lvt_12_1_ * lvt_12_1_ > 4096L && this.generator.getValue((double)lvt_10_1_, (double)lvt_12_1_) < -0.8999999761581421D) {
					float lvt_14_1_ = (MathHelper.abs((float)lvt_10_1_) * 3439.0F + MathHelper.abs((float)lvt_12_1_) * 147.0F) % 13.0F + 9.0F;
					float lvt_15_1_ = (float)(lvt_5_1_ - lvt_8_1_ * 2);
					float lvt_16_1_ = (float)(lvt_6_1_ - lvt_9_1_ * 2);
					float lvt_17_1_ = 100.0F - MathHelper.sqrt(lvt_15_1_ * lvt_15_1_ + lvt_16_1_ * lvt_16_1_) * lvt_14_1_;
					lvt_17_1_ = MathHelper.clamp(lvt_17_1_, -100.0F, 80.0F);
					lvt_7_1_ = Math.max(lvt_7_1_, lvt_17_1_);
				}
			}
		}

		return lvt_7_1_;
	}

	@Override
	public boolean hasStructure(Structure<?> structureIn) {
		return this.hasStructureCache.computeIfAbsent(structureIn, (p_205008_1_) -> {
			for(Biome biome : this.field_205009_d) {
				if (biome.hasStructure(p_205008_1_)) {
					return true;
				}
			}

			return false;
		});
	}

	@Override
	public Set<BlockState> getSurfaceBlocks() {
		if (this.topBlocksCache.isEmpty()) {
			for(Biome biome : this.field_205009_d) {
				this.topBlocksCache.add(biome.getSurfaceBuilderConfig().getTop());
			}
		}

		return this.topBlocksCache;
	}
}