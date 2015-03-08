/*******************************************************************************
 * Copyright 2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.biome;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import biomesoplenty.api.biome.BiomeOwner;
import biomesoplenty.api.biome.IExtendedBiome;
import biomesoplenty.api.biome.IGenerator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate;

public class ExtendedBiomeRegistry
{
	private static Map<BiomeGenBase, BiomeExtension> externalExtensions = new HashMap();
	
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
		public BiomeGenBase biome;
		private GenerationManager generationManager = new GenerationManager();
		
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
		public GenerationManager getGenerationManager()
		{
			return this.generationManager;
		}
	}
	
	public static class GenerationManager
	{
	    private Map<String, IGenerator<?>> generatorMap = new HashMap();
	    //TODO: Come up with a better sequencing system
	    private Map<String, Decorate.EventType> generatorSequenceMap = new HashMap();;
		
	    public void addGenerator(String key, IGenerator<?> generator, Decorate.EventType nextFeature)
	    {
	        this.generatorMap.put(key, generator);
	        this.generatorSequenceMap.put(key, nextFeature);
	    }
	    
	    public void addGenerator(String key, IGenerator<?> generator)
	    {
	        this.addGenerator(key, generator, Decorate.EventType.CUSTOM);
	    }

	    public void configureGenerators(Map<String, IGenerator<?>> generatorMap)
	    {
	        this.generatorMap.putAll(generatorMap);
	    }

	    public Map<String, IGenerator<?>> getGeneratorMap()
	    {
	        return Collections.unmodifiableMap(this.generatorMap);
	    }
	    
	    public Decorate.EventType getGeneratorStage(String key)
	    {
	        return generatorSequenceMap.get(key);
	    }
	}
}
