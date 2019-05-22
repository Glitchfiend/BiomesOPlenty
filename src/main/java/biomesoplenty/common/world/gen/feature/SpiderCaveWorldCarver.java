/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.world.gen.feature;

import java.util.BitSet;
import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.block.BlockBramble;
import biomesoplenty.common.util.biome.GeneratorUtil;
import biomesoplenty.common.util.block.IBlockPosQuery;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.IChunkGenSettings;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.ProbabilityConfig;

public class SpiderCaveWorldCarver extends WorldCarver<ProbabilityConfig> {
	   public boolean func_212246_a(IBlockReader p_212246_1_, Random p_212246_2_, int p_212246_3_, int p_212246_4_, ProbabilityConfig p_212246_5_) {
	      return p_212246_2_.nextFloat() <= p_212246_5_.probability;
	   }

	   public boolean carve(IWorld region, Random random, int chunkX, int chunkZ, int originalX, int originalZ, BitSet mask, ProbabilityConfig config) {
	      int i = (this.func_202520_b() * 2 - 1) * 16;
	      int j = random.nextInt(random.nextInt(random.nextInt(15) + 1) + 1);

	      for(int k = 0; k < j; ++k) {
	         double d0 = (double)(chunkX * 16 + random.nextInt(16));
	         double d1 = (double)random.nextInt(random.nextInt(120) + 8);
	         double d2 = (double)(chunkZ * 16 + random.nextInt(16));
	         int l = 1;
	         if (random.nextInt(4) == 0) {
	            double d3 = 0.5D;
	            float f1 = 1.0F + random.nextFloat() * 6.0F;
	            this.addRoom(region, random.nextLong(), originalX, originalZ, d0, d1, d2, f1, 0.5D, mask);
	            l += random.nextInt(4);
	         }

	         for(int k1 = 0; k1 < l; ++k1) {
	            float f = random.nextFloat() * ((float)Math.PI * 2F);
	            float f3 = (random.nextFloat() - 0.5F) / 4.0F;
	            double d4 = 1.0D;
	            float f2 = random.nextFloat() * 2.0F + random.nextFloat();
	            if (random.nextInt(10) == 0) {
	               f2 *= random.nextFloat() * random.nextFloat() * 3.0F + 1.0F;
	            }

	            int i1 = i - random.nextInt(i / 4);
	            int j1 = 0;
	            this.addTunnel(region, random.nextLong(), originalX, originalZ, d0, d1, d2, f2, f, f3, 0, i1, 1.0D, mask);
	         }
	      }

	      return true;
	   }

	   protected void addRoom(IWorld worldIn, long seed, int mainChunkX, int mainChunkZ, double xRange, double yRange, double zRange, float roomRadius, double roomHeight, BitSet mask) {
	      double d0 = 1.5D + (double)(MathHelper.sin(((float)Math.PI / 2F)) * roomRadius);
	      double d1 = d0 * roomHeight;
	      this.carveAtTarget(worldIn, seed, mainChunkX, mainChunkZ, xRange + 1.0D, yRange, zRange, d0, d1, mask);
	   }

	   protected void addTunnel(IWorld worldIn, long seed, int mainChunkX, int mainChunkZ, double rangeX, double rangeY, double rangeZ, float p_202533_12_, float area, float p_202533_14_, int depth, int maxDepth, double p_202533_17_, BitSet mask) {
	      Random random = new Random(seed);
	      int i = random.nextInt(maxDepth / 2) + maxDepth / 4;
	      boolean flag = random.nextInt(6) == 0;
	      float f = 0.0F;
	      float f1 = 0.0F;

	      for(int j = depth; j < maxDepth; ++j) {
	         double d0 = 1.5D + (double)(MathHelper.sin((float)Math.PI * (float)j / (float)maxDepth) * p_202533_12_);
	         double d1 = d0 * p_202533_17_;
	         float f2 = MathHelper.cos(p_202533_14_);
	         rangeX += (double)(MathHelper.cos(area) * f2);
	         rangeY += (double)MathHelper.sin(p_202533_14_);
	         rangeZ += (double)(MathHelper.sin(area) * f2);
	         p_202533_14_ = p_202533_14_ * (flag ? 0.92F : 0.7F);
	         p_202533_14_ = p_202533_14_ + f1 * 0.1F;
	         area += f * 0.1F;
	         f1 = f1 * 0.9F;
	         f = f * 0.75F;
	         f1 = f1 + (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 2.0F;
	         f = f + (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 4.0F;
	         if (j == i && p_202533_12_ > 1.0F) {
	            this.addTunnel(worldIn, random.nextLong(), mainChunkX, mainChunkZ, rangeX, rangeY, rangeZ, random.nextFloat() * 0.5F + 0.5F, area - ((float)Math.PI / 2F), p_202533_14_ / 3.0F, j, maxDepth, 1.0D, mask);
	            this.addTunnel(worldIn, random.nextLong(), mainChunkX, mainChunkZ, rangeX, rangeY, rangeZ, random.nextFloat() * 0.5F + 0.5F, area + ((float)Math.PI / 2F), p_202533_14_ / 3.0F, j, maxDepth, 1.0D, mask);
	            return;
	         }

	         if (random.nextInt(4) != 0) {
	            if (!this.isWithinGenerationDepth(mainChunkX, mainChunkZ, rangeX, rangeZ, j, maxDepth, p_202533_12_)) {
	               return;
	            }

	            this.carveAtTarget(worldIn, seed, mainChunkX, mainChunkZ, rangeX, rangeY, rangeZ, d0, d1, mask);
	         }
	      }

	   }

	   protected boolean carveAtTarget(IWorld worldIn, long seed, int mainChunkX, int mainChunkZ, double xRange, double yRange, double zRange, double placementXZBound, double placementYBound, BitSet mask) {
	      double d0 = (double)(mainChunkX * 16 + 8);
	      double d1 = (double)(mainChunkZ * 16 + 8);
	      if (!(xRange < d0 - 16.0D - placementXZBound * 2.0D) && !(zRange < d1 - 16.0D - placementXZBound * 2.0D) && !(xRange > d0 + 16.0D + placementXZBound * 2.0D) && !(zRange > d1 + 16.0D + placementXZBound * 2.0D)) {
	         int i = Math.max(MathHelper.floor(xRange - placementXZBound) - mainChunkX * 16 - 1, 0);
	         int j = Math.min(MathHelper.floor(xRange + placementXZBound) - mainChunkX * 16 + 1, 16);
	         int k = Math.max(MathHelper.floor(yRange - placementYBound) - 1, 1);
	         int l = Math.min(MathHelper.floor(yRange + placementYBound) + 1, 248);
	         int i1 = Math.max(MathHelper.floor(zRange - placementXZBound) - mainChunkZ * 16 - 1, 0);
	         int j1 = Math.min(MathHelper.floor(zRange + placementXZBound) - mainChunkZ * 16 + 1, 16);
	         if (this.doesAreaHaveFluids(worldIn, mainChunkX, mainChunkZ, i, j, k, l, i1, j1)) {
	            return false;
	         } else {
	            boolean flag = false;
	            BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
	            BlockPos.MutableBlockPos blockpos$mutableblockpos1 = new BlockPos.MutableBlockPos();
	            BlockPos.MutableBlockPos blockpos$mutableblockpos2 = new BlockPos.MutableBlockPos();

	            for(int k1 = i; k1 < j; ++k1) {
	               int l1 = k1 + mainChunkX * 16;
	               double d2 = ((double)l1 + 0.5D - xRange) / placementXZBound;

	               for(int i2 = i1; i2 < j1; ++i2) {
	                  int j2 = i2 + mainChunkZ * 16;
	                  double d3 = ((double)j2 + 0.5D - zRange) / placementXZBound;
	                  if (!(d2 * d2 + d3 * d3 >= 1.0D)) {
	                     boolean flag1 = false;

	                     for(int k2 = l; k2 > k; --k2) {
	                        double d4 = ((double)k2 - 0.5D - yRange) / placementYBound;
	                        if (!(d4 <= -0.7D) && !(d2 * d2 + d4 * d4 + d3 * d3 >= 1.0D)) {
	                           int l2 = k1 | i2 << 4 | k2 << 8;
	                           if (!mask.get(l2)) {
	                              mask.set(l2);
	                              blockpos$mutableblockpos.setPos(l1, k2, j2);
	                              IBlockState iblockstate = worldIn.getBlockState(blockpos$mutableblockpos);
	                              IBlockState iblockstate1 = worldIn.getBlockState(blockpos$mutableblockpos1.setPos(blockpos$mutableblockpos).move(EnumFacing.UP));
	                              if (iblockstate.getBlock() == Blocks.GRASS_BLOCK || iblockstate.getBlock() == Blocks.MYCELIUM) {
	                                 flag1 = true;
	                              }

	                              if (this.isTargetSafeFromFalling(iblockstate, iblockstate1)) {
	                                 if (k2 < 11) {
	                                    worldIn.setBlockState(blockpos$mutableblockpos, LAVA_FLUID.getBlockState(), 2);
	                                 }
	                                 else
	                                 {
	                                	worldIn.setBlockState(blockpos$mutableblockpos, Blocks.COBWEB.getDefaultState(), 2);
	                                    if (flag1) {
	                                       blockpos$mutableblockpos2.setPos(blockpos$mutableblockpos).move(EnumFacing.DOWN);
	                                       if (worldIn.getBlockState(blockpos$mutableblockpos2).getBlock() == Blocks.DIRT) {
	                                          IBlockState iblockstate2 = worldIn.getBiome(blockpos$mutableblockpos).getSurfaceBuilderConfig().getTop();
	                                          worldIn.setBlockState(blockpos$mutableblockpos2, iblockstate2, 2);
	                                       }
	                                    }
	                                 }

	                                 flag = true;
	                              }
	                           }
	                        }
	                     }
	                  }
	               }
	            }

	            return flag;
	         }
	      } else {
	         return false;
	      }
	   }
	}