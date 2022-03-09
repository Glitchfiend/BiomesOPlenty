/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.worldgen.carver;

import biomesoplenty.common.util.BOPRegistryUtils;
import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.core.Holder;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantFloat;
import net.minecraft.util.valueproviders.UniformFloat;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.carver.*;
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration;
import net.minecraft.world.level.levelgen.heightproviders.BiasedToBottomHeight;
import net.minecraft.world.level.levelgen.heightproviders.UniformHeight;
import terrablender.util.RegistryUtils;

public class BOPConfiguredCarvers
{
    public static final Holder<ConfiguredWorldCarver<CaveCarverConfiguration>> ORIGIN_CAVE = register("origin_cave", BOPWorldCarvers.ORIGIN_CAVE.configured(new CaveCarverConfiguration(0.14285715F, BiasedToBottomHeight.of(VerticalAnchor.absolute(0), VerticalAnchor.absolute(127), 8), ConstantFloat.of(0.5F), VerticalAnchor.aboveBottom(10), CarverDebugSettings.of(false, Blocks.CRIMSON_BUTTON.defaultBlockState()), ConstantFloat.of(1.0F), ConstantFloat.of(1.0F), ConstantFloat.of(-0.7F))));

    private static <WC extends CarverConfiguration> Holder<ConfiguredWorldCarver<WC>> register(String key, ConfiguredWorldCarver<WC> carver)
    {
        return BOPRegistryUtils.registerExact(BuiltinRegistries.CONFIGURED_CARVER, key, carver);
    }
}
