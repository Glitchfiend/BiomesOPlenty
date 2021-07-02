/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.util.biome;

import biomesoplenty.common.biome.BiomeMetadata;
import biomesoplenty.core.BiomesOPlenty;
import biomesoplenty.init.ModBiomes;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.core.Registry;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistry;

import javax.annotation.Nullable;

public class BiomeUtil
{
    public static ResourceKey<Biome> createKey(Biome biome)
    {
        return biome == null ? null : ResourceKey.create(Registry.BIOME_REGISTRY, biome.delegate.name());
    }

    public static ResourceKey<Biome> createKey(int id)
    {
        return createKey(getBiome(id));
    }

    public static Biome getBiome(ResourceKey<Biome> key)
    {
        Biome biome = ForgeRegistries.BIOMES.getValue(key.location());
        if (biome == null) throw new RuntimeException("Attempted to get unregistered biome " + key);
        return biome;
    }

    public static Biome getBiome(int id)
    {
        if (id == -1) throw new RuntimeException("Attempted to get biome with id -1");
        return getBiome(((ForgeRegistry<Biome>)ForgeRegistries.BIOMES).getKey(id));
    }

    public static int getBiomeId(Biome biome)
    {
        if (biome == null) throw new RuntimeException("Attempted to get id of null biome");
        int id = ((ForgeRegistry<Biome>)ForgeRegistries.BIOMES).getID(biome);
        if (id == -1) throw new RuntimeException("Biome id is -1 for biome " + biome.delegate.name());
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
        return ForgeRegistries.BIOMES.containsKey(key.location());
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
