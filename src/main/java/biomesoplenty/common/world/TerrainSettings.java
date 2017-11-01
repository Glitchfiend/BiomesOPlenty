/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.world;

import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.Biome;
    
public class TerrainSettings
{
    public static enum PresetOctaveWeights
    {
        DEFAULT (new double[] {1 / 24.0D, 2 / 24.0D, 4 / 24.0D, 8 / 24.0D, 6 / 24.0D, 3 / 24.0D});
        
        private final double[] weights;
        private PresetOctaveWeights(double[] weights)
        {
            this.weights = weights;
        }
        public void copyWeights(double[] destination)
        {
            System.arraycopy(this.weights, 0, destination, 0, this.weights.length);
        }
    }
    
    public double avgHeight = 0.0D;
    public double variationBelow = 0.0D;
    public double variationAbove = 0.0D;
    public double sidewaysNoiseAmount = 0.0D;
    public double[] octaveWeights = new double[] {0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D};        
    public double minHeight = 0.0D;
    public double maxHeight = 255.0D;
    
    public TerrainSettings() {}
    
    public TerrainSettings setDefaults()
    {
        this.avgHeight = 64.0D;
        this.variationBelow = 10.0D;
        this.variationAbove = 20.0D;
        this.minHeight = 0.0D;
        this.maxHeight = 255.0D;
        this.sidewaysNoiseAmount = 0.4D;
        PresetOctaveWeights.DEFAULT.copyWeights(this.octaveWeights);
        return this;
    }
    
    public TerrainSettings avgHeight(double a)
    {
        this.avgHeight = a;
        return this;
    }
    
    public TerrainSettings sidewaysNoise(double a)
    {
        this.sidewaysNoiseAmount = MathHelper.clamp(a, 0.0F, 1.0F);
        return this;
    }
    
    public TerrainSettings heightVariation(double variation)
    {
        return this.heightVariation(variation, variation);
    }
    
    public TerrainSettings heightVariation(double varBelow, double varAbove)
    {
        this.variationBelow = varBelow;
        this.variationAbove = varAbove;
        return this;
    }
    
    public TerrainSettings minHeight(double minHeight)
    {
        this.minHeight = MathHelper.clamp(minHeight, 0.0D, 255.0D);
        return this;
    }
    
    public TerrainSettings maxHeight(double maxHeight)
    {
        this.maxHeight = MathHelper.clamp(maxHeight, 0.0D, 255.0D);
        return this;
    }
    
    public TerrainSettings octaves(double w0, double w1, double w2, double w3, double w4, double w5)
    {
        // standard weights for the octaves are 1,2,4,8,6,3
        double norm = 1 / (1 * w0 + 2 * w1 + 4 * w2 + 8 * w3 + 6 * w4 + 3 * w5);
        this.octaveWeights[0] = w0 * 1 * norm;
        this.octaveWeights[1] = w1 * 2 * norm;
        this.octaveWeights[2] = w2 * 4 * norm;
        this.octaveWeights[3] = w3 * 8 * norm;
        this.octaveWeights[4] = w4 * 6 * norm;
        this.octaveWeights[5] = w5 * 3 * norm;
        return this;
    }
    
    public static TerrainSettings forVanillaBiome(Biome biome)
    {
        // Transform vanilla height parameters into equivalent BOP terrain parameters
        // Note they're named appallingly - minHeight and maxHeight in the vanilla biomes should be called baseHeight and scale
        double avgHeight = 65 + 17 * biome.getBaseHeight();
        double variationAbove = 7 + 20 * 4 * biome.getHeightVariation();
        double variationBelow = 4 + 20 * biome.getHeightVariation();
        
        return (new TerrainSettings()).setDefaults().avgHeight(avgHeight).heightVariation(variationAbove, variationBelow).sidewaysNoise(0.9D);

    }

}   