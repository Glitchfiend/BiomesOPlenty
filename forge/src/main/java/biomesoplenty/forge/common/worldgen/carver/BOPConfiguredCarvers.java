/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.forge.common.worldgen.carver;

import biomesoplenty.forge.core.BiomesOPlentyForge;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantFloat;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.carver.CarverDebugSettings;
import net.minecraft.world.level.levelgen.carver.CaveCarverConfiguration;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.heightproviders.BiasedToBottomHeight;

public class BOPConfiguredCarvers
{
    public static final ResourceKey<ConfiguredWorldCarver<?>> ORIGIN_CAVE = createKey("origin_cave");

    public static void bootstrap(BootstapContext<ConfiguredWorldCarver<?>> context)
    {
        HolderGetter<Block> blockGetter = context.lookup(Registries.BLOCK);
        context.register(ORIGIN_CAVE, BOPWorldCarvers.ORIGIN_CAVE.get().configured(new CaveCarverConfiguration(0.14285715F, BiasedToBottomHeight.of(VerticalAnchor.absolute(0), VerticalAnchor.absolute(127), 8), ConstantFloat.of(0.5F), VerticalAnchor.aboveBottom(10), CarverDebugSettings.of(false, Blocks.CRIMSON_BUTTON.defaultBlockState()), blockGetter.getOrThrow(BlockTags.OVERWORLD_CARVER_REPLACEABLES), ConstantFloat.of(1.0F), ConstantFloat.of(1.0F), ConstantFloat.of(-0.7F))));
    }

    private static ResourceKey<ConfiguredWorldCarver<?>> createKey(String name)
    {
        return ResourceKey.create(Registries.CONFIGURED_CARVER, new ResourceLocation(BiomesOPlentyForge.MOD_ID, name));
    }
}
