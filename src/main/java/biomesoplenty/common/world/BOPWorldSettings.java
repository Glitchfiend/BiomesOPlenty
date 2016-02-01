/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.world;

import java.io.File;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import biomesoplenty.common.util.config.BOPConfig;
import biomesoplenty.core.BiomesOPlenty;

public class BOPWorldSettings
{
    
    public static Gson serializer = new GsonBuilder().create();
    
    public static enum LandMassScheme
    {
        VANILLA,
        CONTINENTS,
        ARCHIPELAGO;
    }
    
    public static enum TemperatureVariationScheme
    {
        LATITUDE,
        SMALL_ZONES,
        MEDIUM_ZONES,
        LARGE_ZONES,
        RANDOM;
    }
    
    public static enum RainfallVariationScheme
    {
        SMALL_ZONES,
        MEDIUM_ZONES,
        LARGE_ZONES,
        RANDOM;
    }
    
    public static enum BiomeSize
    {
        TINY (2),
        SMALL (3),
        MEDIUM (4),
        LARGE (5),
        HUGE (6);
        
        private final int value;
        BiomeSize(int value)
        {
            this.value = value;
        }
        public int getValue()
        {
            return this.value;
        }
    }
    
    // BOP World properties
    
    public LandMassScheme landScheme = LandMassScheme.VANILLA;
    public TemperatureVariationScheme tempScheme = TemperatureVariationScheme.MEDIUM_ZONES;
    public RainfallVariationScheme rainScheme = RainfallVariationScheme.MEDIUM_ZONES;
    public BiomeSize biomeSize = BiomeSize.MEDIUM;
    public float amplitude = 1.0F;
    public boolean generateBopGems = true;
    public boolean generatePoisonIvy = true;
    public boolean generateFlax = true;
    public boolean generateBerryBushes = true;
    public boolean generateWildCarrots = true;
    public boolean generateThorns = true;
    public boolean generateQuicksand = true;
    
    
    // Vanilla properties - not configurable (yet) but included for consistency with vanilla ChunkProviderSettings
    
    public int seaLevel;
    public boolean useCaves;
    public boolean useDungeons;
    public int dungeonChance;
    public boolean useStrongholds;
    public boolean useVillages;
    public boolean useMineShafts;
    public boolean useTemples;
    public boolean useMonuments;
    public boolean useRavines;
    public boolean useWaterLakes;
    public int waterLakeChance;
    public boolean useLavaLakes;
    public int lavaLakeChance;
    public boolean useLavaOceans;

    
    public BOPWorldSettings()
    {
        this.setDefault();
    }
    
    public BOPWorldSettings(String jsonString)
    {
        this.setDefault();
        this.fromJson(jsonString);
    }
    
    public String toJson()
    {
        JsonObject obj = new JsonObject();
        obj.addProperty("landScheme", this.landScheme.name().toLowerCase());
        obj.addProperty("tempScheme", this.tempScheme.name().toLowerCase());
        obj.addProperty("rainScheme", this.rainScheme.name().toLowerCase());
        obj.addProperty("biomeSize", this.biomeSize.name().toLowerCase());
        obj.addProperty("amplitude", this.amplitude);
        obj.addProperty("generateBopOre", this.generateBopGems);
        obj.addProperty("generatePoisonIvy", this.generatePoisonIvy);
        obj.addProperty("generateFlax", this.generateFlax);
        obj.addProperty("generateBerryBushes", this.generateBerryBushes);
        obj.addProperty("generateWildCarrots", this.generateWildCarrots);
        obj.addProperty("generateThorns", this.generateThorns);
        obj.addProperty("generateQuicksand", this.generateQuicksand);
        
        return serializer.toJson(obj);
    }
    
    public void fromJson(String jsonString)
    {
        this.fromConfigObj(new BOPConfig.ConfigObj(jsonString));
    }
    
    public void fromConfigObj(BOPConfig.IConfigObj worldConfig)
    {
        this.landScheme = worldConfig.getEnum("landScheme", this.landScheme, LandMassScheme.class);
        this.tempScheme = worldConfig.getEnum("tempScheme", this.tempScheme, TemperatureVariationScheme.class);
        this.rainScheme = worldConfig.getEnum("rainScheme", this.rainScheme, RainfallVariationScheme.class);
        this.biomeSize = worldConfig.getEnum("biomeSize", this.biomeSize, BiomeSize.class);
        this.amplitude = worldConfig.getFloat("amplitude", this.amplitude);
        this.generateBopGems = worldConfig.getBool("generateBopOre", this.generateBopGems);
        this.generatePoisonIvy = worldConfig.getBool("generatePoisonIvy", this.generatePoisonIvy);
        this.generateFlax = worldConfig.getBool("generateFlax", this.generateFlax);
        this.generateBerryBushes = worldConfig.getBool("generateBerryBushes", this.generateBerryBushes);
        this.generateWildCarrots = worldConfig.getBool("generateWildCarrots", this.generateWildCarrots);
        this.generateThorns = worldConfig.getBool("generateThorns", this.generateThorns);
        this.generateQuicksand = worldConfig.getBool("generateQuicksand", this.generateQuicksand);
    }
    
    public void setDefault()
    {
        
        // BOP default values
        this.landScheme = LandMassScheme.VANILLA;
        this.tempScheme = TemperatureVariationScheme.MEDIUM_ZONES;
        this.rainScheme = RainfallVariationScheme.MEDIUM_ZONES;
        this.biomeSize = BiomeSize.MEDIUM;
        this.amplitude = 1.0F;
        this.generateBopGems = true;    
        this.generatePoisonIvy = true;  
        this.generateFlax = true;  
        this.generateBerryBushes = true;  
        this.generateWildCarrots = true;  
        this.generateThorns = true;  
        this.generateQuicksand = true;  
        
        // Vanilla default values
        this.seaLevel = 63;
        this.useCaves = true;
        this.useDungeons = true;
        this.dungeonChance = 8;
        this.useStrongholds = true;
        this.useVillages = true;
        this.useMineShafts = true;
        this.useTemples = true;
        this.useMonuments = true;
        this.useRavines = true;
        this.useWaterLakes = true;
        this.waterLakeChance = 4;
        this.useLavaLakes = true;
        this.lavaLakeChance = 80;
        this.useLavaOceans = false;
        
        
        // Allow defaults to be overridden from file
        BOPConfig.IConfigObj worldConfig = new BOPConfig.ConfigFileObj(new File(BiomesOPlenty.configDirectory, "world.json"));
        this.fromConfigObj(worldConfig);
        
    }
    
    
}