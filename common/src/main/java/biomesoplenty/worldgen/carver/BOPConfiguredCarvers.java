/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.worldgen.carver;

import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.ConstantFloat;
import net.minecraft.util.valueproviders.TrapezoidFloat;
import net.minecraft.util.valueproviders.VeryBiasedToBottomInt;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.carver.CaveWorldCarver;
import net.minecraft.world.level.levelgen.carver.WorldCarver;
import net.minecraft.world.level.levelgen.heightproviders.BiasedToBottomHeight;

public class BOPConfiguredCarvers
{
    public static final ResourceKey<WorldCarver> ORIGIN_CAVE = createKey("origin_cave");

    public static void bootstrap(BootstrapContext<WorldCarver> context)
    {
        context.register(ORIGIN_CAVE, new CaveWorldCarver(
                0.14285715F,
                BiasedToBottomHeight.of(VerticalAnchor.absolute(0), VerticalAnchor.absolute(127), 8),
                VeryBiasedToBottomInt.of(0, 14),
                TrapezoidFloat.of(0.0F, 3.0F, 1.0F),
                true,
                ConstantFloat.of(0.5F),
                ConstantFloat.of(1.0F),
                ConstantFloat.of(1.0F),
                ConstantFloat.of(1.0F),
                ConstantFloat.of(-0.7F)
        ));
    }

    private static ResourceKey<WorldCarver> createKey(String name)
    {
        return ResourceKey.create(Registries.CARVER, Identifier.fromNamespaceAndPath(BiomesOPlenty.MOD_ID, name));
    }
}
