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
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.registries.ForgeRegistries;

public class BiomeUtil
{
    public static RegistryKey<Biome> createKey(Biome biome)
    {
        return biome == null ? null : RegistryKey.create(Registry.BIOME_REGISTRY, biome.delegate.name());
    }

    public static RegistryKey<Biome> createKey(int id)
    {
        return createKey(getBiome(id));
    }

    public static Biome getBiome(RegistryKey<Biome> key)
    {
        return WorldGenRegistries.BIOME.get(key);
    }

    public static Biome getBiome(int id)
    {
        return WorldGenRegistries.BIOME.byId(id);
    }

    public static int getBiomeId(Biome biome)
    {
        return WorldGenRegistries.BIOME.getId(biome);
    }

    public static int getBiomeId(RegistryKey<Biome> key)
    {
        return getBiomeId(getBiome(key));
    }

    public static boolean hasMetadata(RegistryKey<Biome> key)
    {
        return ModBiomes.biomeMetadata.containsKey(key);
    }

    public static boolean hasMetadata(Biome biome)
    {
        return hasMetadata(createKey(biome));
    }

    public static BiomeMetadata getMetadata(RegistryKey<Biome> key)
    {
        return ModBiomes.biomeMetadata.get(key);
    }

    public static BiomeMetadata getMetadata(Biome biome)
    {
        return getMetadata(createKey(biome));
    }

    public static boolean exists(RegistryKey<Biome> key)
    {
        return WorldGenRegistries.BIOME.containsKey(key.location());
    }

    public static boolean exists(int id)
    {
        return getBiome(id) != null;
    }
}
