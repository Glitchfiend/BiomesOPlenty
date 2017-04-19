/*******************************************************************************
 * Copyright 2014-2017, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.biome.nether;

import biomesoplenty.api.biome.BiomeOwner;
import biomesoplenty.api.biome.IExtendedBiome;
import biomesoplenty.api.config.IBOPWorldSettings;
import biomesoplenty.api.config.IConfigObj;
import biomesoplenty.api.enums.BOPClimates;
import biomesoplenty.api.generation.GeneratorStage;
import biomesoplenty.api.generation.IGenerationManager;
import biomesoplenty.api.generation.IGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;

import java.util.Map;

public class BOPHellBiome implements IExtendedBiome
{
    @Override
    public void applySettings(IBOPWorldSettings settings) {

    }

    @Override
    public void configure(IConfigObj conf) {

    }

    @Override
    public BiomeOwner getBiomeOwner() {
        return null;
    }

    @Override
    public void addGenerator(String name, GeneratorStage stage, IGenerator generator) {

    }

    @Override
    public IGenerationManager getGenerationManager() {
        return null;
    }

    @Override
    public Map<BOPClimates, Integer> getWeightMap() {
        return null;
    }

    @Override
    public void clearWeights() {

    }

    @Override
    public void addWeight(BOPClimates climate, int weight) {

    }

    @Override
    public ResourceLocation getBeachLocation() {
        return null;
    }

    @Override
    public Biome getBaseBiome() {
        return null;
    }

    @Override
    public ResourceLocation getResourceLocation() {
        return null;
    }
}
