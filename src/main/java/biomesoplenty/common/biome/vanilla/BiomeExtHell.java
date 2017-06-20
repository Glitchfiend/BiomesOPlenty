/*******************************************************************************
 * Copyright 2014-2017, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.biome.vanilla;

import biomesoplenty.api.config.IBOPWorldSettings;
import biomesoplenty.api.config.IBOPWorldSettings.GeneratorType;
import biomesoplenty.api.generation.GeneratorStage;
import biomesoplenty.common.world.generator.GeneratorHive;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeDecorator;

public class BiomeExtHell extends ExtendedBiomeWrapper
{
    public BiomeExtHell()
    {
        super(Biomes.HELL);

        // setup hell as a properly decorated biome
        Biomes.HELL.topBlock = Blocks.NETHERRACK.getDefaultState();
        Biomes.HELL.fillerBlock = Blocks.NETHERRACK.getDefaultState();

        Biomes.HELL.decorator = new BiomeDecorator();
        Biomes.HELL.decorator.treesPerChunk = -999;
        Biomes.HELL.decorator.flowersPerChunk = -999;
        Biomes.HELL.decorator.grassPerChunk = -999;
        Biomes.HELL.decorator.gravelPatchesPerChunk = -999;
        Biomes.HELL.decorator.sandPatchesPerChunk = -999;
        //this.theBiomeDecorator.generateLakes = false;

        this.addGenerator("hive", GeneratorStage.PRE, (new GeneratorHive.Builder()).amountPerChunk(0.2F).create());
    }
    
    @Override
    public void applySettings(IBOPWorldSettings settings)
    {
        if (!settings.isEnabled(GeneratorType.NETHER_HIVES)) {this.removeGenerator("hive");}
    }
}

