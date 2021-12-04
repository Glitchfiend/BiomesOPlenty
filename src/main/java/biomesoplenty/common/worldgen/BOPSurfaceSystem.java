/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.worldgen;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.BlockColumn;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.carver.CarvingContext;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

import java.util.Optional;
import java.util.function.Function;

public class BOPSurfaceSystem extends SurfaceSystem
{
    public BOPSurfaceSystem(Registry<NormalNoise.NoiseParameters> p_198285_, BlockState p_198286_, int p_198287_, long p_198288_, WorldgenRandom.Algorithm p_198289_)
    {
        super(p_198285_, p_198286_, p_198287_, p_198288_, p_198289_);
    }

    public void buildSurface(BiomeManager p_189945_, Registry<Biome> p_189946_, boolean p_189947_, WorldGenerationContext p_189948_, final ChunkAccess p_189949_, BOPNoiseChunk p_189950_, SurfaceRules.RuleSource p_189951_)
    {
        final BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
        final ChunkPos chunkpos = p_189949_.getPos();
        int i = chunkpos.getMinBlockX();
        int j = chunkpos.getMinBlockZ();
        BlockColumn blockcolumn = new BlockColumn() {
            public BlockState getBlock(int p_190006_) {
                return p_189949_.getBlockState(blockpos$mutableblockpos.setY(p_190006_));
            }

            public void setBlock(int p_190008_, BlockState p_190009_) {
                LevelHeightAccessor levelheightaccessor = p_189949_.getHeightAccessorForGeneration();
                if (p_190008_ >= levelheightaccessor.getMinBuildHeight() && p_190008_ < levelheightaccessor.getMaxBuildHeight()) {
                    p_189949_.setBlockState(blockpos$mutableblockpos.setY(p_190008_), p_190009_, false);
                    if (!p_190009_.getFluidState().isEmpty()) {
                        p_189949_.markPosForPostprocessing(blockpos$mutableblockpos);
                    }
                }

            }

            public String toString() {
                return "ChunkBlockColumn " + chunkpos;
            }
        };
        SurfaceRules.Context surfacerules$context = new BOPSurfaceRules.Context(this, p_189949_, p_189950_, p_189945_::getBiome, p_189946_, p_189948_);
        SurfaceRules.SurfaceRule surfacerules$surfacerule = p_189951_.apply(surfacerules$context);
        BlockPos.MutableBlockPos blockpos$mutableblockpos1 = new BlockPos.MutableBlockPos();

        for(int k = 0; k < 16; ++k) {
            for(int l = 0; l < 16; ++l) {
                int i1 = i + k;
                int j1 = j + l;
                int k1 = p_189949_.getHeight(Heightmap.Types.WORLD_SURFACE_WG, k, l) + 1;
                blockpos$mutableblockpos.setX(i1).setZ(j1);
                Biome biome = p_189945_.getBiome(blockpos$mutableblockpos1.set(i1, p_189947_ ? 0 : k1, j1));
                ResourceKey<Biome> resourcekey = p_189946_.getResourceKey(biome).orElseThrow(() -> {
                    return new IllegalStateException("Unregistered biome: " + biome);
                });
                if (resourcekey == Biomes.ERODED_BADLANDS) {
                    this.erodedBadlandsExtension(blockcolumn, i1, j1, k1, p_189949_);
                }

                int l1 = p_189949_.getHeight(Heightmap.Types.WORLD_SURFACE_WG, k, l) + 1;
                surfacerules$context.updateXZ(i1, j1);
                int i2 = 0;
                int j2 = Integer.MIN_VALUE;
                int k2 = Integer.MAX_VALUE;
                int l2 = p_189949_.getMinBuildHeight();

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
                        surfacerules$context.updateY(i2, k3, j2, i1, i3, j1);
                        if (blockstate == this.defaultBlock) {
                            BlockState blockstate2 = surfacerules$surfacerule.tryApply(i1, i3, j1);
                            if (blockstate2 != null) {
                                blockcolumn.setBlock(i3, blockstate2);
                            }
                        }
                    }
                }

                if (resourcekey == Biomes.FROZEN_OCEAN || resourcekey == Biomes.DEEP_FROZEN_OCEAN) {
                    this.frozenOceanExtension(surfacerules$context.getMinSurfaceLevel(), biome, blockcolumn, blockpos$mutableblockpos1, i1, j1, k1);
                }
            }
        }

    }

    public Optional<BlockState> topMaterial(SurfaceRules.RuleSource p_189972_, BOPCarvingContext p_189973_, Function<BlockPos, Biome> p_189974_, ChunkAccess p_189975_, BOPNoiseChunk p_189976_, BlockPos p_189977_, boolean p_189978_)
    {
        SurfaceRules.Context surfacerules$context = new BOPSurfaceRules.Context(this, p_189975_, p_189976_, p_189974_, p_189973_.registryAccess().registryOrThrow(Registry.BIOME_REGISTRY), p_189973_);
        SurfaceRules.SurfaceRule surfacerules$surfacerule = p_189972_.apply(surfacerules$context);
        int i = p_189977_.getX();
        int j = p_189977_.getY();
        int k = p_189977_.getZ();
        surfacerules$context.updateXZ(i, k);
        surfacerules$context.updateY(1, 1, p_189978_ ? j + 1 : Integer.MIN_VALUE, i, j, k);
        BlockState blockstate = surfacerules$surfacerule.tryApply(i, j, k);
        return Optional.ofNullable(blockstate);
    }
}
