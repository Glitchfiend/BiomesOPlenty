/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.biome.overworld;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.block.IBlockPosQuery;
import biomesoplenty.api.enums.BOPClimates;
import biomesoplenty.api.enums.BOPPlants;
import biomesoplenty.api.generation.GeneratorStage;
import biomesoplenty.common.block.BlockBOPGrass;
import biomesoplenty.common.util.biome.GeneratorUtils.ScatterYMethod;
import biomesoplenty.common.util.block.BlockQuery;
import biomesoplenty.common.world.generator.GeneratorGrass;
import biomesoplenty.common.world.generator.GeneratorOreSingle;
import biomesoplenty.common.world.generator.GeneratorSplatter;
import biomesoplenty.common.world.generator.GeneratorSplotches;
import biomesoplenty.common.world.generator.GeneratorWeighted;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.init.Blocks;

public class BiomeGenCrag extends BOPOverworldBiome
{    
    public BiomeGenCrag()
    {
        super("crag", new PropsBuilder("Crag").withGuiColour(5209457).withTemperature(0.5F).withRainfall(0.5F));
        
        // terrain
        this.terrainSettings.avgHeight(100).heightVariation(80, 200).minHeight(40).sidewaysNoise(0.7F);

        this.canSpawnInBiome = false;
        this.canGenerateVillages = false;
        this.canGenerateRivers = false;
        
        this.beachBiomeLocation = null;

        this.addWeight(BOPClimates.COLD_SWAMP, 2);

        this.spawnableCreatureList.clear();
        
        this.topBlock = Blocks.GRAVEL.getDefaultState();
        this.fillerBlock = Blocks.STONE.getDefaultState();
        
        // gravel, cobblestone, overgrown stone
        IBlockPosQuery surface = BlockQuery.buildAnd().withAirAbove().states(this.topBlock).create();
        this.addGenerator("overgrown_stone_patches", GeneratorStage.SAND, (new GeneratorSplotches.Builder()).amountPerChunk(24).splotchSize(16).placeOn(surface).replace(surface).with(BOPBlocks.grass.getDefaultState().withProperty(BlockBOPGrass.VARIANT, BlockBOPGrass.BOPGrassType.OVERGROWN_STONE)).scatterYMethod(ScatterYMethod.AT_SURFACE).create());
        this.addGenerator("cobblestone_splatter", GeneratorStage.SAND, (new GeneratorSplatter.Builder()).amountPerChunk(4.0F).replace(surface).with(Blocks.COBBLESTONE.getDefaultState()).create());
        this.addGenerator("stone_splatter", GeneratorStage.SAND, (new GeneratorSplatter.Builder()).amountPerChunk(4.0F).replace(surface).with(Blocks.STONE.getDefaultState()).create());
        
        // grasses
        GeneratorWeighted grassGenerator = new GeneratorWeighted(4.0F);
        this.addGenerator("grass", GeneratorStage.GRASS, grassGenerator);
        grassGenerator.add("tallgrass", 1, (new GeneratorGrass.Builder()).with(BlockTallGrass.EnumType.GRASS).generationAttempts(128).create());
        grassGenerator.add("shortgrass", 2, (new GeneratorGrass.Builder()).with(BOPPlants.SHORTGRASS).create());
        grassGenerator.add("mediumgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.MEDIUMGRASS).create());
        //grassGenerator.add("dampgrass", 2, (new GeneratorGrass.Builder()).with(BOPPlants.DAMPGRASS).generationAttempts(128).create());
        
        // gem
        this.addGenerator("emeralds", GeneratorStage.SAND, (new GeneratorOreSingle.Builder()).amountPerChunk(12).with(Blocks.EMERALD_ORE.getDefaultState()).create());
        
    }
}
