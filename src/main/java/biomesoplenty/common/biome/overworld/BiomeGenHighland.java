/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.biome.overworld;

import biomesoplenty.api.enums.BOPClimates;
import biomesoplenty.api.enums.BOPFoliage;
import biomesoplenty.api.generation.GeneratorStage;
import biomesoplenty.common.world.generator.GeneratorDoubleFlora;
import biomesoplenty.common.world.generator.GeneratorGrass;
import biomesoplenty.common.world.generator.GeneratorOreSingle;
import biomesoplenty.common.world.generator.GeneratorWeighted;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.init.Blocks;

public class BiomeGenHighland extends BOPOverworldBiome
{
        
    public BiomeGenHighland()
    {
        super("highland", new PropsBuilder("Highland").withGuiColour(0x7CAD66).withTemperature(0.6F).withRainfall(0.6F));

        // terrain
        this.terrainSettings.avgHeight(100).heightVariation(35, 35).octaves(0, 1, 1, 3, 1, 0);
        
        this.canGenerateRivers = false;
        this.canGenerateVillages = false;
        
        this.addWeight(BOPClimates.COOL_TEMPERATE, 5);
        
        this.beachBiomeLocation = null;
        
        // grasses
        GeneratorWeighted grassGenerator = new GeneratorWeighted(10);
        this.addGenerator("grass", GeneratorStage.GRASS, grassGenerator);
        grassGenerator.add("shortgrass", 1, (new GeneratorGrass.Builder()).with(BOPFoliage.SHORTGRASS).create());
        grassGenerator.add("tallgrass", 1, (new GeneratorGrass.Builder()).with(BlockTallGrass.EnumType.GRASS).create());
        grassGenerator.add("doublegrass", 4, (new GeneratorDoubleFlora.Builder()).with(BlockDoublePlant.EnumPlantType.GRASS).create());
 
        // gem
        this.addGenerator("emerald", GeneratorStage.SAND, (new GeneratorOreSingle.Builder()).amountPerChunk(12).with(Blocks.EMERALD_ORE.getDefaultState()).create());
    }
}