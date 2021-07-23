/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.world.gen.placement;

import biomesoplenty.common.world.gen.feature.DenseFeatureSpreadConfig;
import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.configurations.DecoratorConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneDecoratorConfiguration;
import net.minecraft.world.level.levelgen.placement.FeatureDecorator;
import net.minecraftforge.registries.ForgeRegistries;

public class BOPPlacements
{
    public static final FeatureDecorator<DenseFeatureSpreadConfig> COUNT = register("count", new BOPCountPlacement(DenseFeatureSpreadConfig.CODEC.stable()));
    public static final FeatureDecorator<NoneDecoratorConfiguration> ALPHA_TREE = register("alpha_tree", new AlphaTreePlacement(NoneDecoratorConfiguration.CODEC.stable()));

    private static <T extends DecoratorConfiguration, G extends FeatureDecorator<T>> G register(String key, G placement)
    {
        placement.setRegistryName(new ResourceLocation(BiomesOPlenty.MOD_ID, key));
        ForgeRegistries.DECORATORS.register(placement);
        return placement;
    }
}
