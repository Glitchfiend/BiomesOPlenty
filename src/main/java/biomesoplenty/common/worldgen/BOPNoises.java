/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.worldgen;

import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

public class BOPNoises
{
    public static final ResourceKey<NormalNoise.NoiseParameters> UNIQUENESS = createKey("uniqueness");
    public static final ResourceKey<NormalNoise.NoiseParameters> RARENESS = createKey("rareness");
    public static final ResourceKey<NormalNoise.NoiseParameters> UNIQUENESS_LARGE = createKey("uniqueness_large");
    public static final ResourceKey<NormalNoise.NoiseParameters> RARENESS_LARGE = createKey("rareness_large");

    private static ResourceKey<NormalNoise.NoiseParameters> createKey(String name)
    {
        return ResourceKey.create(Registry.NOISE_REGISTRY, new ResourceLocation(BiomesOPlenty.MOD_ID, name));
    }
}
