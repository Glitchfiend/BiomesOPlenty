/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.block;

import biomesoplenty.worldgen.feature.BOPCaveFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.features.CaveFeatures;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BonemealableFeaturePlacerBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.block.BonemealSource;

public class GlowingMossBlock extends BonemealableFeaturePlacerBlock implements BonemealableBlock
{
    public GlowingMossBlock(BlockBehaviour.Properties p_153790_) {
        super(BOPCaveFeatures.GLOWING_MOSS_PATCH_BONEMEAL, p_153790_);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader p_153797_, BlockPos p_153798_, BlockState p_153799_, BonemealSource source)
    {
        return p_153797_.getBlockState(p_153798_.above()).isAir() || p_153797_.getBlockState(p_153798_.below()).isAir();
    }

    @Override
    public boolean isBonemealSuccess(Level p_153802_, RandomSource p_153803_, BlockPos p_153804_, BlockState p_153805_, BonemealSource source)
    {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel p_153792_, RandomSource p_153793_, BlockPos p_153794_, BlockState p_153795_, BonemealSource source)
    {
        Registry<Feature> configuredFeatureRegistry = p_153792_.registryAccess().lookupOrThrow(Registries.FEATURE);
        configuredFeatureRegistry.get(BOPCaveFeatures.GLOWING_MOSS_PATCH_BONEMEAL).orElseThrow().value().place(p_153792_, p_153792_.getChunkSource().getGenerator(), p_153793_, p_153794_.above());
        configuredFeatureRegistry.get(BOPCaveFeatures.GLOWING_MOSS_PATCH_BONEMEAL_BOTTOM).orElseThrow().value().place(p_153792_, p_153792_.getChunkSource().getGenerator(), p_153793_, p_153794_.below());
    }
}