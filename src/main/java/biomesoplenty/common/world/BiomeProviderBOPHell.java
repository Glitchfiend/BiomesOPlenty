/*******************************************************************************
 * Copyright 2014-2017, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.world;

import biomesoplenty.common.world.layer.*;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.init.Biomes;
import net.minecraft.util.ReportedException;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.layer.*;

public class BiomeProviderBOPHell extends BiomeProvider
{

    public BiomeProviderBOPHell(long seed, WorldType worldType, String chunkProviderSettings)
    {
        super();

        // load the settings object
        // note on the client side, chunkProviderSettings is an empty string
        // I'm not sure if this is a bug or deliberate, but it might have some consequences when the biomes/genlayers are different between client and server
        // The same thing happens in vanilla minecraft
        System.out.println("settings for hell world: "+chunkProviderSettings);
        BOPWorldSettings settings = new BOPWorldSettings(chunkProviderSettings);

        // set up all the gen layers
        GenLayer[] genlayers = setupBOPGenLayers(seed, settings);
        this.genBiomes = genlayers[0];
        this.biomeIndexLayer = genlayers[1];
    }

    @Override
    public Biome[] getBiomesForGeneration(Biome[] biomes, int x, int z, int width, int height)
    {
        IntCache.resetIntCache();

        if (biomes == null || biomes.length < width * height)
        {
            biomes = new Biome[width * height];
        }

        int[] aint = this.genBiomes.getInts(x, z, width, height);

        try
        {
            for (int i = 0; i < width * height; ++i)
            {
                biomes[i] = Biome.getBiome(aint[i], Biomes.HELL);
            }

            return biomes;
        }
        catch (Throwable throwable)
        {
            CrashReport crashreport = CrashReport.makeCrashReport(throwable, "Invalid Biome id");
            CrashReportCategory crashreportcategory = crashreport.makeCategory("RawBiomeBlock");
            crashreportcategory.addCrashSection("biomes[] size", biomes.length);
            crashreportcategory.addCrashSection("x", x);
            crashreportcategory.addCrashSection("z", z);
            crashreportcategory.addCrashSection("w", width);
            crashreportcategory.addCrashSection("h", height);
            throw new ReportedException(crashreport);
        }
    }

    public BiomeProviderBOPHell(World world)
    {
        this(world.getSeed(), world.getWorldInfo().getTerrainType(), world.getWorldInfo().getGeneratorOptions());
    }

    public static GenLayer allocateBiomes(long worldSeed, BOPWorldSettings settings, GenLayer subBiomesInit)
    {
        // allocate the basic biomes
        GenLayer biomesLayer = new GenLayerBiomeBOPHell(200L, settings);

        // magnify everything (using the same seed)
        biomesLayer = new GenLayerZoom(1000L, biomesLayer);
        subBiomesInit = new GenLayerZoom(1000L, subBiomesInit);

        // magnify everything again (using the same seed)
        biomesLayer = new GenLayerZoom(1000L, biomesLayer);
        subBiomesInit = new GenLayerZoom(1000L, subBiomesInit);

        // add sub-biomes (like hills or rare mutated variants) seeded with subBiomesInit
        biomesLayer = new GenLayerSubBiomesBOPHell(1000L, biomesLayer, subBiomesInit);

        return biomesLayer;
    }


    public static GenLayer[] setupBOPGenLayers(long worldSeed, BOPWorldSettings settings)
    {
        int biomeSize = 3;

        // the nether doesn't have oceans, so make it all land
        GenLayer mainBranch = new GenLayerIslandBOP(1L, 1);

        // fork off a new branch as a seed for sub biomes (normally for rivers too, but they're not applicable here)
        GenLayer riversAndSubBiomesInit = new GenLayerRiverInit(100L, mainBranch);

        // allocate the biomes
        mainBranch = allocateBiomes(worldSeed, settings, riversAndSubBiomesInit);

        // do a bit more zooming, depending on biomeSize
        for (int i = 0; i < biomeSize; ++i)
        {
            mainBranch = new GenLayerZoom((long)(1000 + i), mainBranch);
            if (i == 0) {mainBranch = new GenLayerRaggedEdges(3L, mainBranch);}
        }
        mainBranch = new GenLayerSmooth(1000L, mainBranch);

        // finish biomes with Voronoi zoom
        GenLayer biomesFinal = new GenLayerVoronoiZoom(10L, mainBranch);

        mainBranch.initWorldGenSeed(worldSeed);
        biomesFinal.initWorldGenSeed(worldSeed);
        return new GenLayer[] {mainBranch, biomesFinal};
    }
}
