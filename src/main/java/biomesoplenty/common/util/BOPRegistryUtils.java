/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.util;

import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceLocation;

public class BOPRegistryUtils
{
    public static <V extends T, T> Holder<V> registerExact(Registry<T> registry, String key, V value)
    {
        return BuiltinRegistries.register((Registry<V>)registry, new ResourceLocation(BiomesOPlenty.MOD_ID, key), value);
    }
}
