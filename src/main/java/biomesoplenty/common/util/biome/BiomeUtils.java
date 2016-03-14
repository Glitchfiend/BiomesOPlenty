/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.util.biome;

import com.google.common.base.CaseFormat;

import biomesoplenty.common.world.BOPWorldSettings;
import biomesoplenty.common.world.WorldTypeBOP;
import biomesoplenty.core.BiomesOPlenty;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.ChunkProviderSettings;
import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

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
            return ChunkProviderSettings.Factory.jsonToFactory(generatorSettingsJson).func_177864_b().biomeSize;
        }
    }
    
    public static ResourceLocation getLocForBiome(BiomeGenBase biome)
    {
        return BiomeGenBase.biomeRegistry.getNameForObject(biome);
    }
    
    public static BiomeGenBase getBiomeForLoc(ResourceLocation location)
    {
        return BiomeGenBase.biomeRegistry.getObject(location);
    }

    private static final Function<ResourceLocation, Pair<ResourceLocation, BiomeGenBase>> MAPPING_FOR_LOCATION = new Function<ResourceLocation, Pair<ResourceLocation, BiomeGenBase>>()
    {
        @Nullable
        @Override
        public Pair<ResourceLocation, BiomeGenBase> apply(@Nullable ResourceLocation input)
        {
            return Pair.of(input, BiomeGenBase.biomeRegistry.getObject(input));
        }
    };

    public static List<BiomeGenBase> getRegisteredBiomes()
    {
        return Lists.newArrayList(BiomeGenBase.biomeRegistry.iterator());
    }
    
    public static BlockPos spiralOutwardsLookingForBiome(World world, BiomeGenBase biomeToFind, double startX, double startZ)
    {
        int sampleSpacing = 4 << BiomeUtils.getBiomeSize(world);
        int maxDist = sampleSpacing * 100;
        return spiralOutwardsLookingForBiome(world, biomeToFind, startX, startZ, maxDist, sampleSpacing);
    }
    
    // sample points in an archimedean spiral starting from startX,startY each one sampleSpace apart
    // stop when the specified biome is found (and return the position it was found at) or when we reach maxDistance (and return null)
    public static BlockPos spiralOutwardsLookingForBiome(World world, BiomeGenBase biomeToFind, double startX, double startZ, int maxDist, int sampleSpace)
    {
        
        if (maxDist <= 0 || sampleSpace <= 0) {throw new IllegalArgumentException("maxDist and sampleSpace must be positive");}
        BiomeProvider chunkManager = world.getBiomeProvider();
        double a = sampleSpace / Math.sqrt(Math.PI);
        double b = 2 * Math.sqrt(Math.PI);
        double x = 0;
        double z = 0;
        double dist = 0;
        int n = 0;
        for (n = 0; dist < maxDist; ++n)
        {
            double rootN = Math.sqrt(n);
            dist = a * rootN;
            x = startX + (dist * Math.sin(b * rootN));
            z = startZ + (dist * Math.cos(b * rootN));
            // chunkManager.genBiomes is the first layer returned from initializeAllBiomeGenerators()
            // chunkManager.biomeIndexLayer is the second layer returned from initializeAllBiomeGenerators(), it's zoomed twice from genBiomes (>> 2) this one is actual size
            // chunkManager.getBiomeGenAt uses biomeIndexLayer to get the biome
            BiomeGenBase[] biomesAtSample = chunkManager.getBiomeGenAt(null, (int)x, (int)z, 1, 1, false);
            if (biomesAtSample[0] == biomeToFind)
            {
                BiomesOPlenty.logger.info("Found "+biomeToFind.getBiomeName()+" after "+n+" samples, spaced "+sampleSpace+" blocks apart at ("+((int)x)+","+((int)z)+") distance "+((int)dist));
                return new BlockPos((int)x, 0, (int)z);
            }
        }
        BiomesOPlenty.logger.info("Failed to find "+biomeToFind.getBiomeName()+" gave up after "+n+" samples, spaced "+sampleSpace+" blocks apart distance "+((int)dist));
        return null;
    }
    
}
