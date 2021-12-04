/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.worldgen;

import net.minecraft.core.SectionPos;
import net.minecraft.util.Mth;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.levelgen.Aquifer;
import net.minecraft.world.level.levelgen.PositionalRandomFactory;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

public class BOPNoiseBasedAquifer extends Aquifer.NoiseBasedAquifer
{
    private final BOPNoiseChunk bopNoiseChunk;

    public BOPNoiseBasedAquifer(BOPNoiseChunk p_198204_, ChunkPos p_198205_, NormalNoise p_198206_, NormalNoise p_198207_, NormalNoise p_198208_, NormalNoise p_198209_, PositionalRandomFactory p_198210_, int p_198211_, int p_198212_, Aquifer.FluidPicker p_198213_) {
        super(null, p_198205_, p_198206_, p_198207_, p_198208_, p_198209_, p_198210_, p_198211_, p_198212_, p_198213_);
        this.bopNoiseChunk = p_198204_;
    }

    @Override
    public Aquifer.FluidStatus computeFluid(int p_188448_, int p_188449_, int p_188450_)
    {
        Aquifer.FluidStatus aquifer$fluidstatus = this.globalFluidPicker.computeFluid(p_188448_, p_188449_, p_188450_);
        int i = Integer.MAX_VALUE;
        int j = p_188449_ + 12;
        int k = p_188449_ - 12;
        boolean flag = false;

        for(int[] aint : SURFACE_SAMPLING_OFFSETS_IN_CHUNKS) {
            int l = p_188448_ + SectionPos.sectionToBlockCoord(aint[0]);
            int i1 = p_188450_ + SectionPos.sectionToBlockCoord(aint[1]);
            int j1 = this.bopNoiseChunk.preliminarySurfaceLevel(l, i1);
            int k1 = j1 + 8;
            boolean flag1 = aint[0] == 0 && aint[1] == 0;
            if (flag1 && k > k1) {
                return aquifer$fluidstatus;
            }

            boolean flag2 = j > k1;
            if (flag2 || flag1) {
                Aquifer.FluidStatus aquifer$fluidstatus1 = this.globalFluidPicker.computeFluid(l, k1, i1);
                if (!aquifer$fluidstatus1.at(k1).isAir()) {
                    if (flag1) {
                        flag = true;
                    }

                    if (flag2) {
                        return aquifer$fluidstatus1;
                    }
                }
            }

            i = Math.min(i, j1);
        }

        int j4 = i + 8 - p_188449_;
        int k4 = 64;
        double d1 = flag ? Mth.clampedMap((double)j4, 0.0D, 64.0D, 1.0D, 0.0D) : 0.0D;
        double d2 = 0.67D;
        double d3 = Mth.clamp(this.fluidLevelFloodednessNoise.getValue((double)p_188448_, (double)p_188449_ * 0.67D, (double)p_188450_), -1.0D, 1.0D);
        double d4 = Mth.map(d1, 1.0D, 0.0D, -0.3D, 0.8D);
        if (d3 > d4) {
            return aquifer$fluidstatus;
        } else {
            double d5 = Mth.map(d1, 1.0D, 0.0D, -0.8D, 0.4D);
            if (d3 <= d5) {
                return new Aquifer.FluidStatus(DimensionType.WAY_BELOW_MIN_Y, aquifer$fluidstatus.fluidType);
            } else {
                int l1 = 16;
                int i2 = 40;
                int j2 = Math.floorDiv(p_188448_, 16);
                int k2 = Math.floorDiv(p_188449_, 40);
                int l2 = Math.floorDiv(p_188450_, 16);
                int i3 = k2 * 40 + 20;
                int j3 = 10;
                double d0 = this.fluidLevelSpreadNoise.getValue((double)j2, (double)k2 / 1.4D, (double)l2) * 10.0D;
                int k3 = Mth.quantize(d0, 3);
                int l3 = i3 + k3;
                int i4 = Math.min(i, l3);
                BlockState blockstate = this.getFluidType(p_188448_, p_188449_, p_188450_, aquifer$fluidstatus, l3);
                return new Aquifer.FluidStatus(i4, blockstate);
            }
        }
    }

    static Aquifer create(BOPNoiseChunk noiseChunk, ChunkPos chunkPos, NormalNoise p_198195_, NormalNoise p_198196_, NormalNoise p_198197_, NormalNoise p_198198_, PositionalRandomFactory p_198199_, int p_198200_, int p_198201_, Aquifer.FluidPicker fluidPicker)
    {
        return new BOPNoiseBasedAquifer(noiseChunk, chunkPos, p_198195_, p_198196_, p_198197_, p_198198_, p_198199_, p_198200_, p_198201_, fluidPicker);
    }
}
