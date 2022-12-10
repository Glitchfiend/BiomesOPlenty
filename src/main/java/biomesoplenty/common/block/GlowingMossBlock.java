/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.block;

import biomesoplenty.common.worldgen.feature.BOPCaveFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.MossBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class GlowingMossBlock extends MossBlock implements BonemealableBlock
{
    public GlowingMossBlock(BlockBehaviour.Properties p_153790_) {
        super(p_153790_);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader p_153797_, BlockPos p_153798_, BlockState p_153799_, boolean p_153800_)
    {
        return p_153797_.getBlockState(p_153798_.above()).isAir();
    }

    @Override
    public boolean isBonemealSuccess(Level p_153802_, RandomSource p_153803_, BlockPos p_153804_, BlockState p_153805_)
    {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel p_153792_, RandomSource p_153793_, BlockPos p_153794_, BlockState p_153795_)
    {
        Registry<ConfiguredFeature<?, ?>> configuredFeatureRegistry = p_153792_.registryAccess().registryOrThrow(Registries.CONFIGURED_FEATURE);
        configuredFeatureRegistry.get(BOPCaveFeatures.GLOWING_MOSS_PATCH_BONEMEAL).place(p_153792_, p_153792_.getChunkSource().getGenerator(), p_153793_, p_153794_.above());
    }
}