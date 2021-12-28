/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.worldgen;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.BlockColumn;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

public class BOPSurfaceSystem extends SurfaceSystem
{
    protected AlphaOctavePerlinNoise sandNoise;
    protected AlphaOctavePerlinNoise gravelNoise;

    public BOPSurfaceSystem(Registry<NormalNoise.NoiseParameters> noises, BlockState defaultBlock, int seaLevel, long seed, WorldgenRandom.Algorithm randomSource)
    {
        super(noises, defaultBlock, seaLevel, seed, randomSource);
        this.sandNoise = new AlphaOctavePerlinNoise(this.randomFactory.fromHashOf(new ResourceLocation(BiomesOPlenty.MOD_ID, "origin_sand_noise")), 4);
        this.gravelNoise = new AlphaOctavePerlinNoise(this.randomFactory.fromHashOf(new ResourceLocation(BiomesOPlenty.MOD_ID, "origin_gravel_noise")), 4);
    }

    @Override
    public void buildSurface(BiomeManager biomeManager, Registry<Biome> biomeRegistry, boolean useLegacyRandomSource, WorldGenerationContext p_189948_, final ChunkAccess chunkAccess, NoiseChunk noiseChunk, SurfaceRules.RuleSource ruleSource)
    {
        final BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
        final ChunkPos chunkpos = chunkAccess.getPos();
        int minX = chunkpos.getMinBlockX();
        int minZ = chunkpos.getMinBlockZ();
        RandomSource chunkRandom = this.randomFactory.at(minX, 0, minZ);
        BlockColumn blockcolumn = new BlockColumn() {
            public BlockState getBlock(int p_190006_) {
                return chunkAccess.getBlockState(blockpos$mutableblockpos.setY(p_190006_));
            }

            public void setBlock(int p_190008_, BlockState p_190009_) {
                LevelHeightAccessor levelheightaccessor = chunkAccess.getHeightAccessorForGeneration();
                if (p_190008_ >= levelheightaccessor.getMinBuildHeight() && p_190008_ < levelheightaccessor.getMaxBuildHeight()) {
                    chunkAccess.setBlockState(blockpos$mutableblockpos.setY(p_190008_), p_190009_, false);
                    if (!p_190009_.getFluidState().isEmpty()) {
                        chunkAccess.markPosForPostprocessing(blockpos$mutableblockpos);
                    }
                }

            }

            public String toString() {
                return "ChunkBlockColumn " + chunkpos;
            }
        };
        SurfaceRules.Context ruleContext = new SurfaceRules.Context(this, chunkAccess, noiseChunk, biomeManager::getBiome, biomeRegistry, p_189948_);
        SurfaceRules.SurfaceRule surfacerules$surfacerule = ruleSource.apply(ruleContext);
        BlockPos.MutableBlockPos blockpos$mutableblockpos1 = new BlockPos.MutableBlockPos();

        for(int localX = 0; localX < 16; ++localX)
        {
            for(int localZ = 0; localZ < 16; ++localZ)
            {
                int x = minX + localX;
                int z = minZ + localZ;
                int height = chunkAccess.getHeight(Heightmap.Types.WORLD_SURFACE_WG, localX, localZ) + 1;
                blockpos$mutableblockpos.setX(x).setZ(z);
                Biome biome = biomeManager.getBiome(blockpos$mutableblockpos1.set(x, useLegacyRandomSource ? 0 : height, z));
                ResourceKey<Biome> biomeKey = biomeRegistry.getResourceKey(biome).orElseThrow(() -> {
                    return new IllegalStateException("Unregistered biome: " + biome);
                });

                // Evade the surface rules system for these biomes because it is garbage
                if (biomeKey == Biomes.ERODED_BADLANDS) this.erodedBadlandsExtension(blockcolumn, x, z, height, chunkAccess);
                //else if (biomeKey == BOPBiomes.ORIGIN_VALLEY) this.originValleyExtension(chunkAccess, chunkRandom, biome, x, z, height);
                //else if (biomeKey == BOPBiomes.TROPICS) this.tropicsExtension(chunkAccess, chunkRandom, biome, x, z, height);

                int l1 = chunkAccess.getHeight(Heightmap.Types.WORLD_SURFACE_WG, localX, localZ) + 1;
                ruleContext.updateXZ(x, z);
                int i2 = 0;
                int j2 = Integer.MIN_VALUE;
                int k2 = Integer.MAX_VALUE;
                int l2 = chunkAccess.getMinBuildHeight();

                for(int i3 = l1; i3 >= l2; --i3) {
                    BlockState blockstate = blockcolumn.getBlock(i3);
                    if (blockstate.isAir()) {
                        i2 = 0;
                        j2 = Integer.MIN_VALUE;
                    } else if (!blockstate.getFluidState().isEmpty()) {
                        if (j2 == Integer.MIN_VALUE) {
                            j2 = i3 + 1;
                        }
                    } else {
                        if (k2 >= i3) {
                            k2 = DimensionType.WAY_BELOW_MIN_Y;

                            for(int j3 = i3 - 1; j3 >= l2 - 1; --j3) {
                                BlockState blockstate1 = blockcolumn.getBlock(j3);
                                if (!this.isStone(blockstate1)) {
                                    k2 = j3 + 1;
                                    break;
                                }
                            }
                        }

                        ++i2;
                        int k3 = i3 - k2 + 1;
                        ruleContext.updateY(i2, k3, j2, x, i3, z);
                        if (blockstate == this.defaultBlock) {
                            BlockState blockstate2 = surfacerules$surfacerule.tryApply(x, i3, z);
                            if (blockstate2 != null) {
                                blockcolumn.setBlock(i3, blockstate2);
                            }
                        }
                    }
                }

                if (biomeKey == Biomes.FROZEN_OCEAN || biomeKey == Biomes.DEEP_FROZEN_OCEAN) {
                    this.frozenOceanExtension(ruleContext.getMinSurfaceLevel(), biome, blockcolumn, blockpos$mutableblockpos1, x, z, height);
                }
            }
        }
    }

    public void originValleyExtension(ChunkAccess chunkAccess, RandomSource random, Biome biome, int x, int z, int startHeight)
    {
        double noise = this.surfaceNoise.getValue((double)x * 0.0625D, (double)z * 0.0625D, (double)startHeight * 0.0625D) * 15.0D;

        final BlockState top = BOPBlocks.ORIGIN_GRASS_BLOCK.defaultBlockState();
        final BlockState middle = Blocks.DIRT.defaultBlockState();

        BlockState topState = top;
        BlockState middleState = middle;
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();

        int placedDepth = -1;
        int grassDepth = (int)(noise / 3.0D + 3.0D + random.nextDouble() * 0.25D);
        int localX = x & 15;
        int localZ = z & 15;

        boolean gravelGen = gravelNoise.sample(x, 109.0134, z, 0.03125, 1, 0.03125) + random.nextDouble() * 0.2 > 3.0;
        boolean sandGen = sandNoise.sample(x, z, 0, 0.03125, 0.03125, 1) + random.nextDouble() * 0.2 > 0.0;

        for (int y = startHeight; y >= 0; --y)
        {
            mutable.set(localX, y, localZ);
            BlockState blockstate2 = chunkAccess.getBlockState(mutable);
            if (blockstate2.isAir())
            {
                placedDepth = -1;
            }
            else if (blockstate2.is(defaultBlock.getBlock()))
            {
                if (placedDepth == -1)
                {
                    if (grassDepth <= 0)
                    {
                        topState = Blocks.AIR.defaultBlockState();
                        middleState = defaultBlock;
                    }
                    else if (y >= this.seaLevel - 4 && y <= this.seaLevel + 1)
                    {
                        topState = top;
                        middleState = middle;

                        if (gravelGen)
                        {
                            topState = Blocks.AIR.defaultBlockState();
                            middleState = Blocks.GRAVEL.defaultBlockState();
                        }
                        if (sandGen)
                        {
                            topState = Blocks.SAND.defaultBlockState();
                            middleState = Blocks.SAND.defaultBlockState();
                        }
                    }

                    if (y < this.seaLevel && (topState == null || topState.isAir()))
                    {
                        if (!biome.warmEnoughToRain(mutable.set(x, y, z)))
                        {
                            topState = Blocks.ICE.defaultBlockState();
                        }
                        else
                        {
                            topState = Blocks.WATER.defaultBlockState();
                        }

                        mutable.set(localX, y, localZ);
                    }

                    placedDepth = grassDepth;
                    if (y >= this.seaLevel - 1)
                    {
                        chunkAccess.setBlockState(mutable, topState, false);
                    }
                    else
                    {
                        chunkAccess.setBlockState(mutable, middleState, false);
                    }
                }
                else if (placedDepth > 0)
                {
                    --placedDepth;
                    chunkAccess.setBlockState(mutable, middleState, false);
                }
            }
        }
    }

    public void tropicsExtension(ChunkAccess chunkAccess, RandomSource random, Biome biome, int x, int z, int startHeight)
    {
        double noise = this.surfaceNoise.getValue((double)x * 0.0625D, (double)z * 0.0625D, (double)startHeight * 0.0625D) * 15.0D;

        final BlockState top = Blocks.GRASS_BLOCK.defaultBlockState();
        final BlockState middle = Blocks.DIRT.defaultBlockState();
        final BlockState bottom = Blocks.GRAVEL.defaultBlockState();

        BlockState topState = top;
        BlockState middleState = middle;

        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
        int placedDepth = -1;
        int grassDepth = (int)(noise / 3.0D + 3.0D + random.nextDouble() * 0.25D);
        int localX = x & 15;
        int localZ = z & 15;

        for(int y = startHeight; y >= 0; --y)
        {
            mutable.set(localX, y, localZ);
            BlockState blockstate2 = chunkAccess.getBlockState(mutable);
            if (blockstate2.isAir())
            {
                placedDepth = -1;
            }
            else if (blockstate2.is(defaultBlock.getBlock()))
            {
                if (placedDepth == -1)
                {
                    if (grassDepth <= 0)
                    {
                        topState = Blocks.AIR.defaultBlockState();
                        middleState = defaultBlock;
                    }
                    else if (y >= this.seaLevel - 4 && y <= this.seaLevel + 1)
                    {
                        topState = BOPBlocks.WHITE_SAND.defaultBlockState();
                        middleState = BOPBlocks.WHITE_SAND.defaultBlockState();
                    }

                    if (y < this.seaLevel && (topState == null || topState.isAir()))
                    {
                        if (!biome.warmEnoughToRain(mutable.set(x, y, z)))
                        {
                            topState = Blocks.ICE.defaultBlockState();
                        }
                        else
                        {
                            topState = Blocks.WATER.defaultBlockState();
                        }

                        mutable.set(localX, y, localZ);
                    }

                    placedDepth = grassDepth;
                    if (y >= this.seaLevel - 1)
                    {
                        chunkAccess.setBlockState(mutable, topState, false);
                    }
                    else if (y < this.seaLevel - 7 - grassDepth)
                    {
                        topState = Blocks.AIR.defaultBlockState();
                        middleState = defaultBlock;
                        chunkAccess.setBlockState(mutable, bottom, false);
                    }
                    else
                    {
                        chunkAccess.setBlockState(mutable, middleState, false);
                    }
                }
                else if (placedDepth > 0)
                {
                    --placedDepth;
                    chunkAccess.setBlockState(mutable, middleState, false);
                    if (placedDepth == 0 && middleState.is(BOPBlocks.WHITE_SAND) && grassDepth > 1)
                    {
                        placedDepth = random.nextInt(4) + Math.max(0, y - 63);
                        middleState = BOPBlocks.WHITE_SANDSTONE.defaultBlockState();
                    }
                }
            }
        }
    }

}
