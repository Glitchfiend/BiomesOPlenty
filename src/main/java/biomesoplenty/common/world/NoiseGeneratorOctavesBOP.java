/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.world;
import java.util.Random;

import net.minecraft.world.gen.NoiseGenerator;

public class NoiseGeneratorOctavesBOP extends NoiseGenerator
{
    private NoiseGeneratorBOP[] generatorCollection;
    private int numOctaves;

    public NoiseGeneratorOctavesBOP(Random rand, int numOctaves)
    {
        this.numOctaves = numOctaves;
        this.generatorCollection = new NoiseGeneratorBOP[numOctaves];

        for (int j = 0; j < numOctaves; ++j)
        {
            this.generatorCollection[j] = new NoiseGeneratorBOP(rand);
        }
    }

    public double[][] generateNoiseOctaves(int chunkX, int chunkZ, double firstWavelength)
    {
        // default to fetching a 5 x 5 array  (4 subchunks in a chunk)
        return this.generateNoiseOctaves(chunkX, chunkZ, 5, 5, firstWavelength);
    }
    
    public double[][] generateNoiseOctaves(int chunkX, int chunkZ, int numX, int numZ, double firstWavelength)
    {
        return this.generateNoiseOctaves(chunkX, chunkZ, numX, numZ, firstWavelength, 1.0D);
    }


    public double[][] generateNoiseOctaves(int chunkX, int chunkZ, int numX, int numZ, double firstWavelength, double amplitude)
    {
        
        double[][] out = new double[this.numOctaves][numX * numZ];

        // generate XZ noise arrays for each octave, all with the same amplitude, starting with the provided wavelength (in blocks) and doubling each time
        double wavelength = firstWavelength;
        for (int i = 0; i < this.numOctaves; ++i)
        {
            out[i] = this.generatorCollection[i].populateNoiseArrayXZ(chunkX, chunkZ, numX, numZ, wavelength, amplitude);   
            wavelength *= 2;
        }
        return out;
    }

}