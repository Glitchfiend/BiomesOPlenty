/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.worldgen.feature;

import biomesoplenty.common.worldgen.feature.configurations.TaigaTreeConfiguration;
import biomesoplenty.common.worldgen.feature.tree.BOPTreeFeature;
import biomesoplenty.common.worldgen.feature.tree.TaigaTreeFeature;
import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraftforge.registries.ForgeRegistries;

public class BOPBaseFeatures
{
    // Trees
    public static final BOPTreeFeature<TaigaTreeConfiguration> TAIGA_TREE = register("taiga_tree", new TaigaTreeFeature(TaigaTreeConfiguration.CODEC));

    private static <C extends FeatureConfiguration, F extends Feature<C>> F register(String key, F value)
    {
        value.setRegistryName(new ResourceLocation(BiomesOPlenty.MOD_ID, key));
        ForgeRegistries.FEATURES.register(value);
        return value;
    }
}
