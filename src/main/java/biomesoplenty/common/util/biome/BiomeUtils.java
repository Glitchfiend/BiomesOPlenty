/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.util.biome;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.base.Optional;
import com.google.common.collect.Sets;
import net.minecraft.world.gen.ChunkGeneratorSettings;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import org.apache.commons.lang3.tuple.Pair;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

import biomesoplenty.common.world.BOPWorldSettings;
import biomesoplenty.common.world.WorldTypeBOP;
import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeProvider;

public class BiomeUtils
{
    public static int getBiomeSize(World world)
    {
        String generatorSettingsJson = world.getWorldInfo().getGeneratorOptions();
        if (world.getWorldType() instanceof WorldTypeBOP)
        {
            return (new BOPWorldSettings(generatorSettingsJson)).biomeSize.getValue();
        }
        else
        {
            return ChunkGeneratorSettings.Factory.jsonToFactory(generatorSettingsJson).build().biomeSize;
        }
    }
    
    /**Use getRegistryName() instead**/
    @Deprecated
    public static ResourceLocation getLocForBiome(Biome biome)
    {
        return Biome.REGISTRY.getNameForObject(biome);
    }
    
    public static Biome getBiomeForLoc(ResourceLocation location)
    {
        return Biome.REGISTRY.getObject(location);
    }

    private static final Function<ResourceLocation, Pair<ResourceLocation, Biome>> MAPPING_FOR_LOCATION = new Function<ResourceLocation, Pair<ResourceLocation, Biome>>()
    {
        @Nullable
        @Override
        public Pair<ResourceLocation, Biome> apply(@Nullable ResourceLocation input)
        {
            return Pair.of(input, Biome.REGISTRY.getObject(input));
        }
    };

    public static List<Biome> getRegisteredBiomes()
    {
        return Lists.newArrayList(Biome.REGISTRY.iterator());
    }

    public static BlockPos spiralOutwardsLookingForBiome(World world, Biome biomeToFind, double startX, double startZ)
    {
        int sampleSpacing = 4 << BiomeUtils.getBiomeSize(world);
        int maxDist = sampleSpacing * 100;
        return spiralOutwardsLookingForBiome(world, biomeToFind, startX, startZ, maxDist, sampleSpacing);
    }

    public static List<Biome> filterPresentBiomes(Optional<Biome>... biomes) { return Lists.newArrayList(Optional.presentInstances(Sets.newHashSet(biomes)));}

    // sample points in an archimedean spiral starting from startX,startY each one sampleSpace apart
    // stop when the specified biome is found (and return the position it was found at) or when we reach maxDistance (and return null)
    public static BlockPos spiralOutwardsLookingForBiome(World world, Biome biomeToFind, double startX, double startZ, int maxDist, int sampleSpace)
    {

        if (maxDist <= 0 || sampleSpace <= 0) {throw new IllegalArgumentException("maxDist and sampleSpace must be positive");}
        BiomeProvider chunkManager = world.getBiomeProvider();
        double a = sampleSpace / Math.sqrt(Math.PI);
        double b = 2 * Math.sqrt(Math.PI);
        double x = 0;
        double z = 0;
        double dist = 0;
        int n = 0;
        String biomeName = FMLCommonHandler.instance().getSide() == Side.CLIENT ? biomeToFind.getBiomeName() : "biome";
        for (n = 0; dist < maxDist; ++n)
        {
            double rootN = Math.sqrt(n);
            dist = a * rootN;
            x = startX + (dist * Math.sin(b * rootN));
            z = startZ + (dist * Math.cos(b * rootN));
            // chunkManager.genBiomes is the first layer returned from initializeAllBiomeGenerators()
            // chunkManager.biomeIndexLayer is the second layer returned from initializeAllBiomeGenerators(), it's zoomed twice from genBiomes (>> 2) this one is actual size
            // chunkManager.getBiomeGenAt uses biomeIndexLayer to get the biome
            Biome[] biomesAtSample = chunkManager.getBiomes(null, (int)x, (int)z, 1, 1, false);
            if (biomesAtSample[0] == biomeToFind)
            {

                BiomesOPlenty.logger.info("Found "+ biomeName +" after "+n+" samples, spaced "+sampleSpace+" blocks apart at ("+((int)x)+","+((int)z)+") distance "+((int)dist));
                return new BlockPos((int)x, 0, (int)z);
            }
        }
        BiomesOPlenty.logger.info("Failed to find "+biomeName+" gave up after "+n+" samples, spaced "+sampleSpace+" blocks apart distance "+((int)dist));
        return null;
    }

}
