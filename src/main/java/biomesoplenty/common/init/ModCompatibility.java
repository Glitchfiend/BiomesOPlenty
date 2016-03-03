package biomesoplenty.common.init;

import java.util.List;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

import biomesoplenty.common.enums.BOPClimates;
import biomesoplenty.common.integration.ThaumcraftCompat;
import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

public class ModCompatibility 
{
    public static void postInit()
    {
        if (Loader.isModLoaded("Thaumcraft"))
        {
            try
            {
                ThaumcraftCompat.init();
            }
            catch (Exception e)
            {
                BiomesOPlenty.logger.error("There was an error while integrating Thaumcraft with Biomes O' Plenty", e);
            }
        }
        
        copyModBiomeWeights();
    }
    
    private static void copyModBiomeWeights()
    {
        try 
        {
            // An array containing lists of default biome entries for only standard BiomeTypes
            List<BiomeEntry>[] vanillaBiomes = (List<BiomeEntry>[])ReflectionHelper.findMethod(BiomeManager.class, null, new String[] { "setupBiomes" }).invoke(null);
            
            for (BiomeType type : BiomeManager.BiomeType.values())
            {                
                // Creates a mutable version of the current biome type's biome array and wraps entries to support .equals()
                List<WrappedBiomeEntry> entries = Lists.transform(Lists.newArrayList(BiomeManager.getBiomes(type)), WRAP_BIOME_ENTRIES);
                // Custom types may have been added, we only want the vanilla ones
                final List<WrappedBiomeEntry> vanillaEntries = type.ordinal() < vanillaBiomes.length ? Lists.transform(vanillaBiomes[type.ordinal()], WRAP_BIOME_ENTRIES) : (List)Lists.newArrayList();
                
                //Remove all default biomes from the entries list
                entries.removeAll(vanillaEntries);
                
                for (WrappedBiomeEntry wrappedEntry : entries)
                {
                    remapBiomeToBoP(wrappedEntry.biomeEntry.biome, type, wrappedEntry.biomeEntry.itemWeight);
                }
            }
        } 
        catch (Exception e) 
        {
            BiomesOPlenty.logger.error("An error has occurred whilst copying mod biomes");
            e.printStackTrace();
            return;
        }
    }
    
    //TODO: Make this more accurate, possibly analyze heights, temps, rainfall and/or biome dictionary tags
    private static void remapBiomeToBoP(BiomeGenBase biome, BiomeType type, int weight)
    {
        for (BOPClimates climate : BOPClimates.values())
        {
            if (climate.biomeType == type)
            {
                climate.addLandBiome(weight, biome);
            }
        }
    }
    
    public static final Function<BiomeEntry, WrappedBiomeEntry> WRAP_BIOME_ENTRIES = new Function<BiomeEntry, WrappedBiomeEntry>()
    {
        @Override
        public WrappedBiomeEntry apply(BiomeEntry input) 
        {
            return new WrappedBiomeEntry(input);
        } 
    };
    
    /**
     * Provides working equals functionality for BiomeEntries
     * */
    private static class WrappedBiomeEntry
    {
        private BiomeEntry biomeEntry;
        
        private WrappedBiomeEntry(BiomeEntry biomeEntry)
        {
            this.biomeEntry = biomeEntry;   
        }
        
        @Override
        public boolean equals(Object input)
        {
            if (input == null) return false;
            if (input == this) return true;
            if (!(input instanceof WrappedBiomeEntry)) return false;
            
            WrappedBiomeEntry other = (WrappedBiomeEntry)input;
            
            return other.biomeEntry.itemWeight == this.biomeEntry.itemWeight && other.biomeEntry.biome == this.biomeEntry.biome;
        }
    }
}
