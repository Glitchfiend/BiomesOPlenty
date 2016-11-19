/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.world;
import java.util.Random;

import net.minecraft.util.math.MathHelper;
import net.minecraft.world.gen.NoiseGenerator;

public class NoiseGeneratorBOP extends NoiseGenerator
{
    private int[] permutations;
    
    public double offsetU;
    public double offsetV;
    public double offsetW;
    
    public NoiseGeneratorBOP()
    {
        this(new Random());
    }

    public NoiseGeneratorBOP(Random rand)
    {
        this.permutations = new int[512];
        this.offsetU = rand.nextDouble() * 256.0D;
        this.offsetV = rand.nextDouble() * 256.0D;
        this.offsetW = rand.nextDouble() * 256.0D;

        // fill first half of permutations array with sequential numbers 0 to 255
        for (int i = 0; i < 256; i++) {this.permutations[i] = i;}

        // randomly rearrange the numbers 0 to 255 and repeat the sequence
        for (int i = 0; i < 256; ++i)
        {
            int j = rand.nextInt(256 - i) + i;
            // swap numbers at positions i and j
            int k = this.permutations[i];
            this.permutations[i] = this.permutations[j];
            this.permutations[j] = k;
            // repeat permutations in second half
            this.permutations[i + 256] = this.permutations[i];
        }
    }
    

    // linear interpolation between a and b, where bias is the fraction (between 0 and 1)
    public final double lerp(double t, double a, double b)
    {
        return a + t * (b - a);
    }

    private static final double[] rc2_a = new double[] {1.0D, -1.0D, 1.0D, -1.0D, 1.0D, -1.0D,  1.0D, -1.0D, 0.0D, 0.0D,  0.0D,  0.0D, 1.0D, 0.0D, -1.0D,  0.0D};
    private static final double[] rc2_b = new double[] {0.0D, 0.0D,  0.0D,  0.0D, 1.0D,  1.0D, -1.0D, -1.0D, 1.0D, 1.0D, -1.0D, -1.0D, 0.0D, 1.0D,  0.0D, -1.0D};

    // return a random combination of a and b, from the following:
    // a, -a, a, -a, a+b, -a+b, a-b, -a-b, b, b, -b, -b, a, b, -a, -b
    public final double randomCombineTwo(int seed, double a, double b)
    {
        int j = seed & 15;
        return rc2_a[j] * a + rc2_b[j] * b;
    }

    private static final double[] rc3_a = new double[] {1.0D, -1.0D,  1.0D, -1.0D, 1.0D, -1.0D,  1.0D, -1.0D, 0.0D,  0.0D,  0.0D,  0.0D, 1.0D,  0.0D, -1.0D,  0.0D};
    private static final double[] rc3_b = new double[] {1.0D,  1.0D, -1.0D, -1.0D, 0.0D,  0.0D,  0.0D,  0.0D, 1.0D, -1.0D,  1.0D, -1.0D, 1.0D, -1.0D,  1.0D, -1.0D};
    private static final double[] rc3_c = new double[] {0.0D,  0.0D,  0.0D,  0.0D, 1.0D,  1.0D, -1.0D, -1.0D, 1.0D,  1.0D, -1.0D, -1.0D, 0.0D,  1.0D,  0.0D, -1.0D};
    
    // return a random combination of a,b and c, from the following:
    // a+b, -a+b, a-b, -a-b, a+c, -a+c, a-c, -a-c, b+c, -b+c, b-c, -b-c, a+b, -b+c, -a+b, -b-c
    // note that the coefficients have been chosen such that  randomCombineThree(seed, a, 0, c) is identically equal to randomCombineTwo(seed, a, c)
    public final double randomCombineThree(int seed, double a, double b, double c)
    {
        int j = seed & 15;
        return rc3_a[j] * a + rc3_b[j] * b + rc3_c[j] * c;
    }
    
    public double wraparound(double d)
    {
        long intD = MathHelper.floor(d);
        return d - intD + (intD % 0x1000000);        
    }

    
    
    // wavelength is in blocks
    public double[] populateNoiseArrayXZ(int chunkX, int chunkZ, int numX, int numZ, double wavelength, double amplitude)
    {
        int index = 0;
        double[] out = new double[numX * numZ];
        
        double sampleSpacing = 4.0D / wavelength; // each subchunk is 4x4 blocks
        double startU = ((double)(chunkX * 16) / wavelength) + this.offsetU;
        double startV = ((double)(chunkZ * 16) / wavelength) + this.offsetV;
        startU = this.wraparound(startU);
        startV = this.wraparound(startV);        

        for (int i = 0; i < numX; ++i)
        {
            double u = startU + (i * sampleSpacing);
            
            // separate integer and fractional parts
            int intU = (int)u;
            if (u < (double)intU) {--intU;}
            double fracU = u - (double)intU;
            intU = intU & 255;
            
            // smoothing function
            double tu = fracU * fracU * fracU * (fracU * (fracU * 6.0D - 15.0D) + 10.0D);
            
            // pick random permutations
            int permX0 = this.permutations[intU];
            int permX1 = this.permutations[intU + 1];

            for (int j = 0; j < numZ; ++j)
            {
                double v = startV + (j * sampleSpacing);
                
                // separate integer and fractional parts
                int intV = (int)v;
                if (v < (double)intV) {--intV;}
                double fracV = v - (double)intV;
                intV = intV & 255;
                
                // smoothing function
                double tv = fracV * fracV * fracV * (fracV * (fracV * 6.0D - 15.0D) + 10.0D);
               
                // pick random permutations
                int permZ0 = this.permutations[permX0] + intV;
                int permZ1 = this.permutations[permX1] + intV;
                
                // pick corner values
                double val00 = this.randomCombineTwo(this.permutations[permZ0], fracU, fracV);
                double val01 = this.randomCombineTwo(this.permutations[permZ1], fracU - 1.0D, fracV);
                double val10 = this.randomCombineTwo(this.permutations[permZ0 + 1], fracU, fracV - 1.0D);
                double val11 = this.randomCombineTwo(this.permutations[permZ1 + 1], fracU - 1.0D, fracV - 1.0D);
                
                // bilinear interpolation in u direction first (twice) then in v direction (once) to get the interpolated value
                double val0 = this.lerp(tu, val00, val01);
                double val1 = this.lerp(tu, val10, val11);
                double val = this.lerp(tv, val0, val1);
                
                // final value is multiplied by amplitude and added to the array
                out[index] = val * amplitude;
                index++;
            }
        }
        return out;
    }


    public void populateNoiseArray(int chunkX, int chunkZ, int numX, int numY, int numZ, double wavelength, double amplitude)
    {
        
        int index = 0;
        double[] out = new double[numX * numY * numZ];
        
        double sampleSpacing = 4.0D / wavelength; // each subchunk is 4x4 blocks
        double startU = ((double)(chunkX * 16) / wavelength) + this.offsetU;
        double startV = this.offsetV;
        double startW = ((double)(chunkZ * 16) / wavelength) + this.offsetW;
        startU = this.wraparound(startU);
        startV = this.wraparound(startV);
        startV = this.wraparound(startW);
        

        for (int i = 0; i < numX; ++i)
        {
            double u = startU + (i * sampleSpacing);
            
            // separate integer and fractional parts
            int intU = (int)u;
            if (u < (double)intU) {--intU;}
            double fracU = u - (double)intU;
            intU = intU & 255;
            
            // smoothing function
            double tu = fracU * fracU * fracU * (fracU * (fracU * 6.0D - 15.0D) + 10.0D);

            for (int j = 0; j < numZ; ++j)
            {                
                double v = startV + (j * sampleSpacing);
                
                // separate integer and fractional parts
                int intV = (int)v;
                if (v < (double)intV) {--intV;}
                double fracV = v - (double)intV;
                intV = intV & 255;
                
                // smoothing function
                double tv = fracV * fracV * fracV * (fracV * (fracV * 6.0D - 15.0D) + 10.0D);

                for (int k = 0; k < numY; ++k)
                {
                    double w = startW + (k * sampleSpacing);
                    
                    // separate integer and fractional parts
                    int intW = (int)w;
                    if (w < (double)intW) {--intW;}
                    double fracW = w - (double)intW;
                    intW = intW & 255;
                    
                    // smoothing function
                    double tw = fracW * fracW * fracW * (fracW * (fracW * 6.0D - 15.0D) + 10.0D);

                    // pick random permutations
                    int perm0 = this.permutations[intU] + intW;
                    int perm1 = this.permutations[perm0] + intV;
                    int perm2 = this.permutations[perm0 + 1] + intV;
                    int perm3 = this.permutations[intU + 1] + intW;
                    int perm4 = this.permutations[perm3] + intV;
                    int perm5 = this.permutations[perm3 + 1] + intV;
                    
                    // pick corner values
                    double val000 = this.randomCombineThree(this.permutations[perm1], u, fracW, fracV);
                    double val001 = this.randomCombineThree(this.permutations[perm4], u - 1.0D, fracW, fracV);
                    double val010 = this.randomCombineThree(this.permutations[perm2], u, fracW - 1.0D, fracV);
                    double val011 = this.randomCombineThree(this.permutations[perm5], u - 1.0D, fracW - 1.0D, fracV);
                    double val100 = this.randomCombineThree(this.permutations[perm1 + 1], u, fracW, fracV - 1.0D);
                    double val101 = this.randomCombineThree(this.permutations[perm4 + 1], u - 1.0D, fracW, fracV - 1.0D);
                    double val110 = this.randomCombineThree(this.permutations[perm2 + 1], u, fracW - 1.0D, fracV - 1.0D);
                    double val111 = this.randomCombineThree(this.permutations[perm5 + 1], u - 1.0D, fracW - 1.0D, fracV - 1.0D);
                    
                    // trilinear interpolation in u direction first (three times) then in w direction (twice) then in v direction (once) to get the interpolated value
                    double val00 = this.lerp(tu, val000, val001);
                    double val01 = this.lerp(tu, val010, val011);
                    double val10 = this.lerp(tu, val100, val101);
                    double val11 = this.lerp(tu, val110, val111);
                    double val0 = this.lerp(tw, val00, val01);
                    double val1 = this.lerp(tw, val10, val11);
                    double val = this.lerp(tv, val0, val1);
                    
                    // final value is multiplied by amplitude and added to the array
                    out[index] = val * amplitude;
                    index++;
                }
            }
        }
    }

}