/*******************************************************************************
 * Copyright 2014-2020, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.world.gen.placement;

import biomesoplenty.common.world.gen.feature.DenseFeatureSpreadConfig;
import biomesoplenty.core.BiomesOPlenty;
import com.mojang.serialization.Codec;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.FeatureSpreadConfig;
import net.minecraft.world.gen.placement.*;

public class BOPPlacements
{
    public static final Placement<DenseFeatureSpreadConfig> COUNT = register("count", new BOPCountPlacement(DenseFeatureSpreadConfig.CODEC.stable()));
    public static final Placement<NoPlacementConfig> ALPHA_TREE = register("alpha_tree", new AlphaTreePlacement(NoPlacementConfig.CODEC.stable()));

    private static <T extends IPlacementConfig, G extends Placement<T>> G register(String key, G placement)
    {
        return Registry.register(Registry.DECORATOR, new ResourceLocation(BiomesOPlenty.MOD_ID, key), placement);
    }
}
