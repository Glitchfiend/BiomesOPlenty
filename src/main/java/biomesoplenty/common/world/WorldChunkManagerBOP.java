/*******************************************************************************
 * Copyright 2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.world;

import biomesoplenty.api.biome.BOPBiome;
import biomesoplenty.common.world.BOPWorldSettings.LandMassScheme;
import biomesoplenty.common.world.layer.*;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.gen.layer.*;

public class WorldChunkManagerBOP extends WorldChunkManager
{    
    public WorldChunkManagerBOP(long seed, WorldType worldType, String chunkProviderSettings)
    {
        super();
        if (!(worldType instanceof WorldTypeBOP))
        {
            throw new RuntimeException("WorldChunkManagerBOP requires a world of type WorldTypeBOP");
        }        
        
        // load the settings object
        // note on the client side, chunkProviderSettings is an empty string
        // I'm not sure if this is a bug or deliberate, but it might have some consequences when the biomes/genlayers are different between client and server
        // The same thing happens in vanilla minecraft
        System.out.println("settings for world: "+chunkProviderSettings);
        BOPWorldSettings settings = new BOPWorldSettings(chunkProviderSettings);        
        
        // loop through the biomes and apply the settings
        for (BiomeGenBase biome : BiomeGenBase.getBiomeGenArray())
        {
            if (biome == null) {continue;}
            if (biome instanceof BOPBiome)
            {
                ((BOPBiome)biome).applySettings(settings);
            }
        }
        
        // set up all the gen layers
        GenLayer[] agenlayer = setupBOPGenLayers(seed, (WorldTypeBOP)worldType, settings);
        agenlayer = getModdedBiomeGenerators(worldType, seed, agenlayer);
        this.genBiomes = agenlayer[0];
        this.biomeIndexLayer = agenlayer[1];
    }
    
    public WorldChunkManagerBOP(World world)
    {
        this(world.getSeed(), world.getWorldInfo().getTerrainType(), world.getWorldInfo().getGeneratorOptions());
    }
    
    // generate the regions of land and sea
    public static GenLayer initialLandAndSeaLayer(LandMassScheme scheme)
    {
        System.out.println("Setting up landmass "+scheme.name());
        GenLayer stack;
        
        switch(scheme)
        {
            case CONTINENTS:
                stack = new GenLayerIslandBOP(1L, 4);
                stack = new GenLayerFuzzyZoom(2000L, stack);
                stack = new GenLayerZoom(2001L, stack);
                stack = new GenLayerIslandBOP(3L, 20, stack);
                break;
                
            case ARCHIPELAGO:
                stack = new GenLayerAllSame(1L, 0);
                stack = new GenLayerRemoveTooMuchOcean(2L, stack);
                break;
        
            case VANILLA: default:
                stack = new GenLayerIsland(1L);
                stack = new GenLayerFuzzyZoom(2000L, stack);
                stack = new GenLayerRaggedEdges(1L, stack);
                stack = new GenLayerZoom(2001L, stack);
                stack = new GenLayerRaggedEdges(2L, stack);
                stack = new GenLayerRaggedEdges(50L, stack);
                stack = new GenLayerRaggedEdges(70L, stack);
                stack = new GenLayerRemoveTooMuchOcean(2L, stack);
                break;
        }
        
        stack = new GenLayerRaggedEdges(3L, stack);
        stack = new GenLayerZoom(2002L, stack);
        stack = new GenLayerZoom(2003L, stack);
        
        return stack;
    }
        
    // superimpose hot and cold regions an a land and sea layer
    public static GenLayer climateLayer(BOPWorldSettings settings, long worldSeed)
    {
        GenLayer temperature;
        switch(settings.tempScheme)
        {
            case LATITUDE: default:
                temperature = new GenLayerTemperatureLatitude(2L, 16, worldSeed);
                break;
            case SMALL_ZONES:
                temperature = new GenLayerTemperatureNoise(3L, worldSeed, 0.14D);
                break;
            case MEDIUM_ZONES:
                temperature = new GenLayerTemperatureNoise(4L, worldSeed, 0.08D);
                break;
            case LARGE_ZONES:
                temperature = new GenLayerTemperatureNoise(5L, worldSeed, 0.04D);
                break;
            case RANDOM:
                temperature = new GenLayerTemperatureRandom(6L);
                break;
        }
        
        GenLayer rainfall;
        switch(settings.rainScheme)
        {
            case SMALL_ZONES:
                rainfall = new GenLayerRainfallNoise(7L, worldSeed, 0.14D);
                break;
            case MEDIUM_ZONES: default:
                rainfall = new GenLayerRainfallNoise(8L, worldSeed, 0.08D);
                break;
            case LARGE_ZONES:
                rainfall = new GenLayerRainfallNoise(9L, worldSeed, 0.04D);
                break;
            case RANDOM:
                rainfall = new GenLayerRainfallRandom(10L);
                break;
        }

        GenLayer climate = new GenLayerClimate(103L, temperature, rainfall);        
        // stack = new GenLayerEdge(3L, stack, GenLayerEdge.Mode.SPECIAL);
        return climate;
    }
    
    // generate the regions of land and sea
    public static GenLayer secondaryLandMasses(GenLayer hotAndCold, LandMassScheme scheme)
    {
        GenLayer stack = hotAndCold;
        
        switch(scheme)
        {
            case CONTINENTS:
                stack = new GenLayerRaggedEdges(4L, stack);
                break;
                
            case ARCHIPELAGO:
                stack = new GenLayerArchipelago(1L, 2, stack);
                break;
        
            case VANILLA: default:
                stack = new GenLayerRaggedEdges(4L, hotAndCold);
                break;
        }
        
        return stack;
    }
    
    
    public static GenLayer allocateBiomes(long worldSeed, WorldTypeBOP worldType, BOPWorldSettings settings, GenLayer mainBranch, GenLayer riversAndSubBiomesInit, GenLayer climateLayer)
    {        
        // allocate the basic biomes        
        GenLayer stack = new GenLayerBiomeBOP(200L, mainBranch, climateLayer, settings);
        stack = GenLayerZoom.magnify(1000L, stack, 2);
        stack = new GenLayerBiomeEdgeBOP(1000L, stack);
        
        // use the hillsInit layer to change some biomes to sub-biomes like hills or rare mutated variants
        GenLayer subBiomesInit = GenLayerZoom.magnify(1000L, riversAndSubBiomesInit, 2);
        stack = new GenLayerSubBiomesBOP(1000L, stack, subBiomesInit, settings);
        return stack;
    }
    
    
    public static GenLayer[] setupBOPGenLayers(long worldSeed, WorldTypeBOP worldType, BOPWorldSettings settings)
    {
        
        int biomeSize = settings.biomeSize.getValue();
        int riverSize = 4;
        
        // first few layers just create areas of land and sea, continents and islands
        GenLayer mainBranch = initialLandAndSeaLayer(settings.landScheme);
        
        // add mushroom islands and deep oceans and other land masses        
        mainBranch = secondaryLandMasses(mainBranch, settings.landScheme);
        
        mainBranch = new GenLayerAddMushroomIsland(5L, mainBranch);
        mainBranch = new GenLayerDeepOcean(4L, mainBranch);
        
        // fork off a new branch as a seed for rivers and sub biomes
        GenLayer riversAndSubBiomesInit = new GenLayerRiverInit(100L, mainBranch);
         
        // create climate layer
        GenLayer climateLayer = climateLayer(settings, worldSeed);
        
        // allocate the biomes
        mainBranch = allocateBiomes(worldSeed, worldType, settings, mainBranch, riversAndSubBiomesInit, climateLayer);
        
        // do a bit more zooming, depending on biomeSize
        //mainBranch = new GenLayerRareBiome(1001L, mainBranch); - sunflower plains I think
        for (int i = 0; i < biomeSize; ++i)
        {
            mainBranch = new GenLayerZoom((long)(1000 + i), mainBranch);
            if (i == 0) {mainBranch = new GenLayerRaggedEdges(3L, mainBranch);}
            if (i == 1 || biomeSize == 1) {mainBranch = new GenLayerShore(1000L, mainBranch);}
        }
        mainBranch = new GenLayerSmooth(1000L, mainBranch);

        // develop the rivers branch
        GenLayer riversBranch = GenLayerZoom.magnify(1000L, riversAndSubBiomesInit, 2);
        riversBranch = GenLayerZoom.magnify(1000L, riversBranch, riverSize);
        riversBranch = new GenLayerRiver(1L, riversBranch);
        riversBranch = new GenLayerSmooth(1000L, riversBranch);
        
        // mix rivers into main branch
        GenLayer riverMixFinal = new GenLayerRiverMixBOP(100L, mainBranch, riversBranch);
        
        // finish biomes with Voronoi zoom
        GenLayer biomesFinal = new GenLayerVoronoiZoom(10L, riverMixFinal);
        
        riverMixFinal.initWorldGenSeed(worldSeed);
        biomesFinal.initWorldGenSeed(worldSeed);
        return new GenLayer[] {riverMixFinal, biomesFinal, riverMixFinal};
        
    }

    
    
}
