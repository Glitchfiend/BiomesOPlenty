/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.util.biome;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class BiomeUtil
{
    public static ResourceKey<Biome> biomeOrFallback(Registry<Biome> biomeRegistry, ResourceKey<Biome>... biomes)
    {
        for (ResourceKey<Biome> key : biomes)
        {
            if (isKeyRegistered(biomeRegistry, key))
                return key;
        }

        throw new RuntimeException("Failed to find fallback for biome!");
    }

    public static boolean isKeyRegistered(Registry<Biome> registry, ResourceKey<Biome> key)
    {
        return key != null && registry.get(key) != null;
    }
}
