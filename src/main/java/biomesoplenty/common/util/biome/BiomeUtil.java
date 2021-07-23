/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.util.biome;

import biomesoplenty.common.biome.BiomeMetadata;
import biomesoplenty.init.ModBiomes;
import net.minecraft.client.Minecraft;
import net.minecraft.core.MappedRegistry;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;

import javax.annotation.Nullable;

public class BiomeUtil
{
    public static Registry<Biome> DYNAMIC_REGISTRY = null;
    public static ResourceKey<Biome> createKey(Biome biome)
    {
        return biome == null ? null : ResourceKey.create(Registry.BIOME_REGISTRY, registry().getKey(biome));
    }

    private static Registry<Biome> registry() {
        return DYNAMIC_REGISTRY == null ? BuiltinRegistries.BIOME : DYNAMIC_REGISTRY;
    }

    public static ResourceKey<Biome> createKey(int id)
    {
        return createKey(getBiome(id));
    }

    public static Biome getBiome(ResourceKey<Biome> key)
    {
        Biome biome = registry().get(key);
        if (biome == null) throw new RuntimeException("Attempted to get unregistered biome " + key);
        return biome;
    }

    public static Biome getBiome(int id)
    {
        if (id == -1) throw new RuntimeException("Attempted to get biome with id -1");
        return registry().byId(id);
    }

    public static int getBiomeId(Biome biome)
    {
        if (biome == null) throw new RuntimeException("Attempted to get id of null biome");
        int id = registry().getId(biome);
        if (id == -1) throw new RuntimeException("Biome id is -1 for biome " + registry().getKey(biome));
        return id;
    }

    public static int getBiomeId(ResourceKey<Biome> key)
    {
        return getBiomeId(getBiome(key));
    }

    public static boolean hasMetadata(ResourceKey<Biome> key)
    {
        return ModBiomes.biomeMetadata.containsKey(key);
    }

    public static boolean hasMetadata(Biome biome)
    {
        return hasMetadata(createKey(biome));
    }

    public static BiomeMetadata getMetadata(ResourceKey<Biome> key)
    {
        return ModBiomes.biomeMetadata.get(key);
    }

    public static BiomeMetadata getMetadata(Biome biome)
    {
        return getMetadata(createKey(biome));
    }

    public static boolean exists(ResourceKey<Biome> key)
    {
        return registry().containsKey(key.location());
    }

    public static boolean exists(int id)
    {
        return getBiome(id) != null;
    }

    @Nullable
    public static ResourceKey<Biome> getClientKey(Biome biome)
    {
        return Minecraft.getInstance().level.registryAccess().registryOrThrow(Registry.BIOME_REGISTRY).getResourceKey(biome).orElse(null);
    }
}
