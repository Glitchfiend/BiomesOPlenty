/*******************************************************************************
 * Copyright 2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.world;

import java.io.File;

import biomesoplenty.common.util.config.BOPConfig;
import biomesoplenty.common.world.layer.GenLayerBiomeBOP;
import biomesoplenty.common.world.layer.GenLayerHeatLatitude;
import biomesoplenty.common.world.layer.GenLayerHeatRandom;
import biomesoplenty.common.world.layer.GenLayerSubBiomesBOP;
import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerAddIsland;
import net.minecraft.world.gen.layer.GenLayerAddMushroomIsland;
import net.minecraft.world.gen.layer.GenLayerAddSnow;
import net.minecraft.world.gen.layer.GenLayerBiomeEdge;
import net.minecraft.world.gen.layer.GenLayerDeepOcean;
import net.minecraft.world.gen.layer.GenLayerEdge;
import net.minecraft.world.gen.layer.GenLayerFuzzyZoom;
import net.minecraft.world.gen.layer.GenLayerIsland;
import net.minecraft.world.gen.layer.GenLayerRareBiome;
import net.minecraft.world.gen.layer.GenLayerRemoveTooMuchOcean;
import net.minecraft.world.gen.layer.GenLayerRiver;
import net.minecraft.world.gen.layer.GenLayerRiverInit;
import net.minecraft.world.gen.layer.GenLayerRiverMix;
import net.minecraft.world.gen.layer.GenLayerShore;
import net.minecraft.world.gen.layer.GenLayerSmooth;
import net.minecraft.world.gen.layer.GenLayerVoronoiZoom;
import net.minecraft.world.gen.layer.GenLayerZoom;

public class WorldChunkManagerBOP extends WorldChunkManager
{
    // TODO: ability to vary landmass creation - eg continents, archipelago etc    
    
    public WorldChunkManagerBOP(long seed, WorldType worldType, String chunkProviderSettings)
    {
        super();
        if (!(worldType instanceof WorldTypeBOP))
        {
            throw new RuntimeException("WorldChunkManagerBOP requires a world of type WorldTypeBOP");
        }
        
        this.field_180301_f = chunkProviderSettings;
        BOPConfig.IConfigObj worldConfig = new BOPConfig.ConfigFileObj(new File(BiomesOPlenty.configDirectory, "world.json"));
        
        GenLayer[] agenlayer = setupBOPGenLayers(seed, (WorldTypeBOP)worldType, chunkProviderSettings, worldConfig);
        agenlayer = getModdedBiomeGenerators(worldType, seed, agenlayer);
        
        this.genBiomes = agenlayer[0];
        this.biomeIndexLayer = agenlayer[1];
    }
    
    public WorldChunkManagerBOP(World world)
    {
        this(world.getSeed(), world.getWorldInfo().getTerrainType(), world.getWorldInfo().getGeneratorOptions());
    }
    
    // generate the regions of land and sea
    public static GenLayer initialLandAndSeaLayer()
    {
        GenLayer stack = new GenLayerIsland(1L);
        stack = new GenLayerFuzzyZoom(2000L, stack);
        stack = new GenLayerAddIsland(1L, stack);
        stack = new GenLayerZoom(2001L, stack);
        stack = new GenLayerAddIsland(2L, stack);
        stack = new GenLayerAddIsland(50L, stack);
        stack = new GenLayerAddIsland(70L, stack);
        stack = new GenLayerRemoveTooMuchOcean(2L, stack);
        return stack;
    }
    
    public enum TemperatureVariationScheme {VANILLA, RANDOM, LATITUDE}
    
    // superimpose hot and cold regions an a land and sea layer
    public static GenLayer addHotAndColdRegions(GenLayer landAndSea, TemperatureVariationScheme scheme, long worldSeed)
    {
        GenLayer stack;
        switch (scheme)
        {
            
            // The 'random' scheme places small hot and cold regions all over the map completely at random
            // this results in biomes scattered randomly like in Minecraft before v1.7
            case RANDOM:
                stack = new GenLayerAddIsland(3L, landAndSea);
                stack = new GenLayerZoom(2002L, stack);
                stack = new GenLayerZoom(2002L, stack);
                stack = new GenLayerHeatRandom(2L, stack);
                stack = new GenLayerEdge(3L, stack, GenLayerEdge.Mode.SPECIAL);
                break;
                
            // The 'latitude' scheme causes temperature to depend on latitude (as it does on Earth)
            // the result is bands of temperature in the East-West direction
            // travelling North/South you find different temperatures, travelling East/West you find different biomes of a similar temperature
            case LATITUDE:
                stack = new GenLayerAddIsland(3L, landAndSea);
                stack = new GenLayerZoom(2002L, stack);
                stack = new GenLayerHeatLatitude(2L, stack, 10, worldSeed);
                stack = new GenLayerEdge(3L, stack, GenLayerEdge.Mode.SPECIAL);
                stack = new GenLayerZoom(2002L, stack);
                break;
                
            // The 'vanilla' scheme results in large temperature regions, arranged semi-randomly (extremes of temperature rarely touch)
            // this is the minecraft default scheme and causes biomes for similar temperatures to be clustered together
            case VANILLA: default:
                stack = new GenLayerAddSnow(2L, landAndSea);
                stack = new GenLayerAddIsland(3L, stack);
                stack = new GenLayerEdge(2L, stack, GenLayerEdge.Mode.COOL_WARM);
                stack = new GenLayerEdge(2L, stack, GenLayerEdge.Mode.HEAT_ICE);
                stack = new GenLayerEdge(3L, stack, GenLayerEdge.Mode.SPECIAL);
                stack = new GenLayerZoom(2002L, stack);
                stack = new GenLayerZoom(2003L, stack);
                break;
                
        }

        return stack;
    }
    
    public static GenLayer allocateBiomes(long worldSeed, WorldTypeBOP worldType, String chunkProviderSettingsJson, GenLayer hotAndCold, GenLayer riversAndSubBiomesInit)
    {        
        // allocate the basic biomes        
        GenLayer stack = new GenLayerBiomeBOP(200L, hotAndCold, worldType, chunkProviderSettingsJson);
        stack = GenLayerZoom.magnify(1000L, stack, 2);
        stack = new GenLayerBiomeEdge(1000L, stack);
        
        // use the hillsInit layer to change some biomes to sub-biomes like hills or rare mutated variants
        GenLayer subBiomesInit = GenLayerZoom.magnify(1000L, riversAndSubBiomesInit, 2);
        stack = new GenLayerSubBiomesBOP(1000L, stack, subBiomesInit);
        return stack;
    }
    
    
    public static GenLayer[] setupBOPGenLayers(long worldSeed, WorldTypeBOP worldType, String chunkProviderSettingsJson, BOPConfig.IConfigObj conf)
    {
        
        int biomeSize = 4;
        int riverSize = 4;
        
        // first few layers just create areas of land and sea, continents and islands
        GenLayer mainBranch = initialLandAndSeaLayer();
        
        // now add hot and cold regions (and two zooms)
        TemperatureVariationScheme scheme = conf.getEnum("temperatureVariationScheme", TemperatureVariationScheme.VANILLA, TemperatureVariationScheme.class);
        mainBranch = addHotAndColdRegions(mainBranch, scheme, worldSeed);
        
        // add mushroom islands and deep oceans
        mainBranch = new GenLayerAddIsland(4L, mainBranch);
        mainBranch = new GenLayerAddMushroomIsland(5L, mainBranch);
        mainBranch = new GenLayerDeepOcean(4L, mainBranch);
        
        // fork off a new branch as a seed for rivers and sub biomes
        GenLayer riversAndSubBiomesInit = new GenLayerRiverInit(100L, mainBranch);
         
        // allocate the biomes
        mainBranch = allocateBiomes(worldSeed, worldType, chunkProviderSettingsJson, mainBranch, riversAndSubBiomesInit);
        
        // do a bit more zooming, depending on biomeSize
        mainBranch = new GenLayerRareBiome(1001L, mainBranch);
        for (int i = 0; i < biomeSize; ++i)
        {
            mainBranch = new GenLayerZoom((long)(1000 + i), mainBranch);
            if (i == 0) {mainBranch = new GenLayerAddIsland(3L, mainBranch);}
            if (i == 1 || biomeSize == 1) {mainBranch = new GenLayerShore(1000L, mainBranch);}
        }
        mainBranch = new GenLayerSmooth(1000L, mainBranch);

        // develop the rivers branch
        GenLayer riversBranch = GenLayerZoom.magnify(1000L, riversAndSubBiomesInit, 2);
        riversBranch = GenLayerZoom.magnify(1000L, riversBranch, riverSize);
        riversBranch = new GenLayerRiver(1L, riversBranch);
        riversBranch = new GenLayerSmooth(1000L, riversBranch);
        
        // mix rivers into main branch
        GenLayer riverMixFinal = new GenLayerRiverMix(100L, mainBranch, riversBranch);
        
        // finish biomes with Voronoi zoom
        GenLayer biomesFinal = new GenLayerVoronoiZoom(10L, riverMixFinal);
        
        riverMixFinal.initWorldGenSeed(worldSeed);
        biomesFinal.initWorldGenSeed(worldSeed);
        return new GenLayer[] {riverMixFinal, biomesFinal, riverMixFinal};
        
    }

    
    
}
