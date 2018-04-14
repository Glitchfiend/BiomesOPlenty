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

import biomesoplenty.api.config.IBOPWorldSettings;
import biomesoplenty.api.config.IConfigObj;
import biomesoplenty.common.util.config.BOPConfig;
import biomesoplenty.core.BiomesOPlenty;

public class BOPWorldSettings implements IBOPWorldSettings
{
    
    public static Gson serializer = new GsonBuilder().create();
    
    public enum LandMassScheme
    {
        VANILLA,
        CONTINENTS,
        ARCHIPELAGO
    }
    
    public enum TemperatureVariationScheme
    {
        LATITUDE,
        SMALL_ZONES,
        MEDIUM_ZONES,
        LARGE_ZONES,
        RANDOM
    }
    
    public enum RainfallVariationScheme
    {
        SMALL_ZONES,
        MEDIUM_ZONES,
        LARGE_ZONES,
        RANDOM
    }
    
    public enum BiomeSize
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
    public boolean generateRockFormations = true;
    public boolean generatePoisonIvy = false;
    public boolean generateBerryBushes = true;
    public boolean generateThorns = true;
    public boolean generateQuicksand = true;
    public boolean generateLiquidPoison = true;
    public boolean generateHotSprings = true;
    public boolean generateNetherHives = true;
    public boolean generateEndFeatures = true;
    
    
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
    public boolean useMansions;

    public float coordinateScale;
    public float heightScale;
    public float upperLimitScale;
    public float lowerLimitScale;
    public float mainNoiseScaleX;
    public float mainNoiseScaleY;
    public float mainNoiseScaleZ;

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
        obj.addProperty("generateRockFormations", this.generateRockFormations);
        obj.addProperty("generatePoisonIvy", this.generatePoisonIvy);
        obj.addProperty("generateBerryBushes", this.generateBerryBushes);
        obj.addProperty("generateThorns", this.generateThorns);
        obj.addProperty("generateQuicksand", this.generateQuicksand);
        obj.addProperty("generateLiquidPoison", this.generateLiquidPoison);
        obj.addProperty("generateHotSprings", this.generateHotSprings);
        obj.addProperty("generateNetherHives", this.generateNetherHives);
        obj.addProperty("generateEndFeatures", this.generateEndFeatures);
        obj.addProperty("mainNoiseScaleX", this.mainNoiseScaleX);
        obj.addProperty("mainNoiseScaleY", this.mainNoiseScaleY);
        obj.addProperty("mainNoiseScaleZ", this.mainNoiseScaleZ);
        obj.addProperty("coordinateScale", this.coordinateScale);
        obj.addProperty("heightScale", this.heightScale);
        obj.addProperty("upperLimitScale", this.upperLimitScale);
        obj.addProperty("lowerLimitScale", this.lowerLimitScale);

        return serializer.toJson(obj);
    }
    
    public void fromJson(String jsonString)
    {
        this.fromConfigObj(new BOPConfig.ConfigObj(jsonString));
    }
    
    public void fromConfigObj(IConfigObj worldConfig)
    {
        this.landScheme = worldConfig.getEnum("landScheme", this.landScheme, LandMassScheme.class);
        this.tempScheme = worldConfig.getEnum("tempScheme", this.tempScheme, TemperatureVariationScheme.class);
        this.rainScheme = worldConfig.getEnum("rainScheme", this.rainScheme, RainfallVariationScheme.class);
        this.biomeSize = worldConfig.getEnum("biomeSize", this.biomeSize, BiomeSize.class);
        this.amplitude = worldConfig.getFloat("amplitude", this.amplitude);
        this.generateBopGems = worldConfig.getBool("generateBopOre", this.generateBopGems);
        this.generateRockFormations = worldConfig.getBool("generateRockFormations", this.generateRockFormations);
        this.generatePoisonIvy = worldConfig.getBool("generatePoisonIvy", this.generatePoisonIvy);
        this.generateBerryBushes = worldConfig.getBool("generateBerryBushes", this.generateBerryBushes);
        this.generateThorns = worldConfig.getBool("generateThorns", this.generateThorns);
        this.generateQuicksand = worldConfig.getBool("generateQuicksand", this.generateQuicksand);
        this.generateLiquidPoison = worldConfig.getBool("generateLiquidPoison", this.generateLiquidPoison);
        this.generateHotSprings = worldConfig.getBool("generateHotSprings", this.generateHotSprings);
        this.generateNetherHives = worldConfig.getBool("generateNetherHives", this.generateNetherHives);
        this.generateEndFeatures = worldConfig.getBool("generateEndFeatures", this.generateEndFeatures);
        this.mainNoiseScaleX = worldConfig.getFloat("mainNoiseScaleX", this.mainNoiseScaleX);
        this.mainNoiseScaleY = worldConfig.getFloat("mainNoiseScaleY", this.mainNoiseScaleY);
        this.mainNoiseScaleZ = worldConfig.getFloat("mainNoiseScaleZ", this.mainNoiseScaleZ);
        this.coordinateScale = worldConfig.getFloat("coordinateScale", this.coordinateScale);
        this.heightScale = worldConfig.getFloat("heightScale", this.heightScale);
        this.upperLimitScale = worldConfig.getFloat("upperLimitScale", this.upperLimitScale);
        this.lowerLimitScale = worldConfig.getFloat("lowerLimitScale", this.lowerLimitScale);
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
        this.generateRockFormations = true; 
        this.generatePoisonIvy = false;  
        this.generateBerryBushes = true;  
        this.generateThorns = true;  
        this.generateQuicksand = true; 
        this.generateLiquidPoison = true; 
        this.generateHotSprings = true;
        this.generateNetherHives = true;
        this.generateEndFeatures = true;
        
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
        this.useMansions = true;
        this.useRavines = true;
        this.useWaterLakes = true;
        this.waterLakeChance = 4;
        this.useLavaLakes = true;
        this.lavaLakeChance = 80;
        this.useLavaOceans = false;

        this.mainNoiseScaleX = 80.0F;
        this.mainNoiseScaleY = 160.0F;
        this.mainNoiseScaleZ = 80.0F;
        this.coordinateScale = 684.412F;
        this.heightScale = 684.412F;
        this.upperLimitScale = 512.0F;
        this.lowerLimitScale = 512.0F;

        // Allow defaults to be overridden from file
        IConfigObj worldConfig = new BOPConfig.ConfigFileObj(new File(BiomesOPlenty.configDirectory, "world.json"));
        this.fromConfigObj(worldConfig);
        
    }

    @Override
    public boolean isEnabled(GeneratorType type)
    {
        switch (type)
        {
        case GEMS: 
            return this.generateBopGems;
        case ROCK_FORMATIONS: 
            return this.generateRockFormations;
        case POISON_IVY: 
            return this.generatePoisonIvy;
        case BERRY_BUSHES: 
            return this.generateBerryBushes;
        case THORNS: 
            return this.generateThorns;
        case QUICKSAND: 
            return this.generateQuicksand;
        case LIQUID_POISON: 
            return this.generateLiquidPoison;
        case HOT_SPRINGS: 
            return this.generateHotSprings;
        case NETHER_HIVES: 
            return this.generateNetherHives;
        case END_FEATURES:
            return this.generateEndFeatures;

        default:
            return true;
        }
    }    
}
