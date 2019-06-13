package biomesoplenty.common.world;

import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.annotation.Nullable;

import com.google.common.collect.Sets;

import biomesoplenty.api.biome.BOPBiomes;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import net.minecraft.block.BlockState;
import net.minecraft.init.Biomes;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.biome.provider.EndBiomeProviderSettings;
import net.minecraft.world.gen.NoiseGeneratorSimplex;
import net.minecraft.world.gen.feature.structure.Structure;

public class BOPEndBiomeProvider extends BiomeProvider {
	   private final NoiseGeneratorSimplex field_201546_a;
	   private final SharedSeedRandom random;
	   private final Biome[] field_205009_d = new Biome[]{Biomes.THE_END, Biomes.END_HIGHLANDS, Biomes.END_MIDLANDS, Biomes.SMALL_END_ISLANDS, Biomes.END_BARRENS, BOPBiomes.end_plains.get()};

	   public BOPEndBiomeProvider(EndBiomeProviderSettings p_i48970_1_) {
	      this.random = new SharedSeedRandom(p_i48970_1_.getSeed());
	      this.random.skip(17292);
	      this.field_201546_a = new NoiseGeneratorSimplex(this.random);
	   }

	   @Nullable
	   @Override
	   public Biome getBiome(BlockPos pos, @Nullable Biome defaultBiome) {
	      return this.func_201545_a(pos.getX() >> 4, pos.getZ() >> 4);
	   }

	   private Biome func_201545_a(int p_201545_1_, int p_201545_2_) {
	      if ((long)p_201545_1_ * (long)p_201545_1_ + (long)p_201545_2_ * (long)p_201545_2_ <= 4096L)
	      {
	         return Biomes.THE_END;
	      }
	      else
	      {
	         float f = this.getHeightValue(p_201545_1_, p_201545_2_, 1, 1);
	         double d0 = Biome.INFO_NOISE.getValue((double)p_201545_1_ * 0.005D, (double)p_201545_2_ * 0.005D);
	         
	         if (f > 40.0F)
	         {
	        	if (d0 > 0.01D)
	        	{
	        		return Biomes.END_HIGHLANDS;
	        	}
	        	else
	        	{
	        		return BOPBiomes.end_plains.get();
	        	}
	         }
	         else if (f >= 0.0F)
	         {
	            return Biomes.END_MIDLANDS;
	         }
	         else
	         {
	            return f < -20.0F ? Biomes.SMALL_END_ISLANDS : Biomes.END_BARRENS;
	         }
	      }
	   }

	   @Override
	   public Biome[] getBiomes(int startX, int startZ, int xSize, int zSize) {
	      return this.getBiomeBlock(startX, startZ, xSize, zSize);
	   }

	   @Override
	   public Biome[] getBiomes(int x, int z, int width, int length, boolean cacheFlag) {
	      Biome[] abiome = new Biome[width * length];
	      Long2ObjectMap<Biome> long2objectmap = new Long2ObjectOpenHashMap<>();

	      for(int i = 0; i < width; ++i) {
	         for(int j = 0; j < length; ++j) {
	            int k = i + x >> 4;
	            int l = j + z >> 4;
	            long i1 = ChunkPos.asLong(k, l);
	            Biome biome = long2objectmap.get(i1);
	            if (biome == null) {
	               biome = this.func_201545_a(k, l);
	               long2objectmap.put(i1, biome);
	            }

	            abiome[i + j * width] = biome;
	         }
	      }

	      return abiome;
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
	   public float getHeightValue(int p_201536_1_, int p_201536_2_, int p_201536_3_, int p_201536_4_) {
	      float f = (float)(p_201536_1_ * 2 + p_201536_3_);
	      float f1 = (float)(p_201536_2_ * 2 + p_201536_4_);
	      float f2 = 100.0F - MathHelper.sqrt(f * f + f1 * f1) * 8.0F;
	      f2 = MathHelper.clamp(f2, -100.0F, 80.0F);

	      for(int i = -12; i <= 12; ++i) {
	         for(int j = -12; j <= 12; ++j) {
	            long k = (long)(p_201536_1_ + i);
	            long l = (long)(p_201536_2_ + j);
	            if (k * k + l * l > 4096L && this.field_201546_a.getValue((double)k, (double)l) < (double)-0.9F) {
	               float f3 = (MathHelper.abs((float)k) * 3439.0F + MathHelper.abs((float)l) * 147.0F) % 13.0F + 9.0F;
	               f = (float)(p_201536_3_ - i * 2);
	               f1 = (float)(p_201536_4_ - j * 2);
	               float f4 = 100.0F - MathHelper.sqrt(f * f + f1 * f1) * f3;
	               f4 = MathHelper.clamp(f4, -100.0F, 80.0F);
	               f2 = Math.max(f2, f4);
	            }
	         }
	      }

	      return f2;
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

<<<<<<< HEAD
	   public Set<BlockState> getSurfaceBlocks() {
=======
	   @Override
	   public Set<IBlockState> getSurfaceBlocks() {
>>>>>>> 23ad4b22dd4f1d9f81ff5efa7bc6b9519d4d41af
	      if (this.topBlocksCache.isEmpty()) {
	         for(Biome biome : this.field_205009_d) {
	            this.topBlocksCache.add(biome.getSurfaceBuilderConfig().getTop());
	         }
	      }

	      return this.topBlocksCache;
	   }
	}