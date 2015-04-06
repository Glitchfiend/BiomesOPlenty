/*******************************************************************************
 * Copyright 2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.biome;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeManager.BiomeType;
import biomesoplenty.api.biome.BiomeOwner;
import biomesoplenty.api.biome.IExtendedBiome;
import biomesoplenty.api.biome.generation.GenerationManager;
import biomesoplenty.api.biome.generation.GeneratorStage;
import biomesoplenty.api.biome.generation.IGenerator;

public class ExtendedBiomeRegistry
{
    private static Map<BiomeGenBase, BiomeExtension> externalExtensions = new HashMap();

    public static boolean isRegistered(BiomeGenBase biome)
    {
        return biome.biomeID != -1;
    }

    public static BiomeExtension createExtension(BiomeGenBase biome)
    {
        return externalExtensions.put(biome, new BiomeExtension(biome));
    }

    public static IExtendedBiome getExtension(BiomeGenBase biome)
    {
        if (biome instanceof IExtendedBiome)
        {
            return (IExtendedBiome)biome;
        }
        else if (externalExtensions.containsKey(biome))
        {
            return externalExtensions.get(biome);
        }

        return null;
    }

    public static class BiomeExtension implements IExtendedBiome
    {
        public final BiomeGenBase biome;
        private GenerationManager generationManager = new GenerationManager();
        private Map<BiomeType, Integer> weightMap = new HashMap<BiomeType, Integer>();

        private BiomeExtension(BiomeGenBase biome)
        {
            this.biome = biome;
        }

        @Override
        public BiomeOwner getBiomeOwner()
        {
            return BiomeOwner.OTHER;
        }

        @Override
        public void addGenerator(String name, GeneratorStage stage, IGenerator<?> generator)
        {
            this.generationManager.addGenerator(name, stage, generator);
        }
        
        @Override
        public GenerationManager getGenerationManager()
        {
            return this.generationManager;
        }
        
        @Override
        public Map<BiomeType, Integer> getWeightMap()
        {
            return this.weightMap;
        }
        
        @Override
        public void addWeight(BiomeType type, int weight)
        {
            this.weightMap.put(type, weight);
        }
        
        @Override
        public void clearWeights()
        {
            this.weightMap.clear();
        }
    }
}
