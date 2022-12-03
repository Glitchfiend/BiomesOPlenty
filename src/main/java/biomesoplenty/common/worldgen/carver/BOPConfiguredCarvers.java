/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.worldgen.carver;

import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.core.Registry;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantFloat;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.carver.CarverConfiguration;
import net.minecraft.world.level.levelgen.carver.CarverDebugSettings;
import net.minecraft.world.level.levelgen.carver.CaveCarverConfiguration;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.heightproviders.BiasedToBottomHeight;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class BOPConfiguredCarvers
{
    public static final RegistryObject<ConfiguredWorldCarver<CaveCarverConfiguration>> ORIGIN_CAVE = register("origin_cave", () -> BOPWorldCarvers.ORIGIN_CAVE.get().configured(new CaveCarverConfiguration(0.14285715F, BiasedToBottomHeight.of(VerticalAnchor.absolute(0), VerticalAnchor.absolute(127), 8), ConstantFloat.of(0.5F), VerticalAnchor.aboveBottom(10), CarverDebugSettings.of(false, Blocks.CRIMSON_BUTTON.defaultBlockState()), Registry.BLOCK.getOrCreateTag(BlockTags.OVERWORLD_CARVER_REPLACEABLES), ConstantFloat.of(1.0F), ConstantFloat.of(1.0F), ConstantFloat.of(-0.7F))));

    private static <WC extends CarverConfiguration> RegistryObject<ConfiguredWorldCarver<WC>> register(String key, Supplier<ConfiguredWorldCarver<WC>> carverSupplier)
    {
        return BiomesOPlenty.CONFIGURED_CARVER_REGISTER.register(key, carverSupplier);
    }

    public static void setup() {}
}
