/*******************************************************************************
 * Copyright 2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.biome.overworld;

import net.minecraft.block.BlockTallGrass;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraftforge.common.BiomeManager.BiomeType;
import biomesoplenty.api.biome.BOPBiome;
import biomesoplenty.api.biome.generation.GeneratorStage;
import biomesoplenty.api.biome.generation.GeneratorWeighted;
import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.block.BlockBOPDirt;
import biomesoplenty.common.block.BlockBOPDoublePlant;
import biomesoplenty.common.block.BlockBOPGrass;
import biomesoplenty.common.block.BlockCoral;
import biomesoplenty.common.block.BlockMud;
import biomesoplenty.common.enums.BOPFlowers;
import biomesoplenty.common.enums.BOPGems;
import biomesoplenty.common.enums.BOPPlants;
import biomesoplenty.common.world.feature.*;

public class BiomeGenMoor extends BOPBiome
{    
    
    public BiomeGenMoor()
    {
        
        // terrain
        this.bopMinHeight = 70;
        this.bopMaxHeight = 98;
        this.setOctaveWeights(0, 1, 1, 3, 1, 0);
        
        this.setTemperatureRainfall(0.5F, 1.0F);
        this.setColor(0x619365);
        this.waterColorMultiplier = 0x588276;
        this.skyColor =0xA0C5D3;
        this.topBlock = BOPBlocks.grass.getDefaultState().withProperty(BlockBOPGrass.VARIANT, BlockBOPGrass.BOPGrassType.LOAMY);
        this.fillerBlock = BOPBlocks.dirt.getDefaultState().withProperty(BlockBOPDirt.VARIANT, BlockBOPDirt.BOPDirtType.LOAMY);
        
        this.addWeight(BiomeType.COOL, 7);
        
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
          
        // mud
        this.addGenerator("mud", GeneratorStage.SAND_PASS2, (new GeneratorWaterside.Builder()).amountPerChunk(1).maxRadius(7).with(BOPBlocks.mud.getDefaultState().withProperty(BlockMud.VARIANT, BlockMud.MudType.MUD)).create());
        
        // lakes
        this.addGenerator("lakes", GeneratorStage.SAND, (new GeneratorLakes.Builder()).amountPerChunk(1.5F).waterLakeForBiome(this).create());
        
        // flowers
        GeneratorWeighted flowerGenerator = new GeneratorWeighted(0.6F);
        this.addGenerator("flowers", GeneratorStage.GRASS, flowerGenerator);
        flowerGenerator.add("swampflower", 1, (new GeneratorFlora.Builder()).with(BOPFlowers.SWAMPFLOWER).create());
        
        // grasses        
        GeneratorWeighted grassGenerator = new GeneratorWeighted(5.0F);
        this.addGenerator("grass", GeneratorStage.GRASS, grassGenerator);
        grassGenerator.add("tallgrass", 1, (new GeneratorGrass.Builder()).with(BlockTallGrass.EnumType.GRASS).create());
        grassGenerator.add("mediumgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.MEDIUMGRASS).create());
        grassGenerator.add("shortgrass", 2, (new GeneratorGrass.Builder()).with(BOPPlants.SHORTGRASS).create());
        grassGenerator.add("wheatgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.WHEATGRASS).create());
        grassGenerator.add("dampgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.DAMPGRASS).create());
        
        // shrooms
        this.addGenerator("brown_mushrooms", GeneratorStage.SHROOM,(new GeneratorFlora.Builder()).amountPerChunk(0.2F).with(Blocks.brown_mushroom.getDefaultState()).create());
        this.addGenerator("red_mushrooms", GeneratorStage.SHROOM,(new GeneratorFlora.Builder()).amountPerChunk(0.1F).with(Blocks.red_mushroom.getDefaultState()).create());
        
        // other plants
        this.addGenerator("koru", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.6F).with(BOPPlants.KORU).create());
        this.addGenerator("flax", GeneratorStage.FLOWERS,(new GeneratorDoubleFlora.Builder()).amountPerChunk(0.1F).with(BlockBOPDoublePlant.DoublePlantType.FLAX).generationAttempts(6).create());
        this.addGenerator("algae", GeneratorStage.LILYPAD, (new GeneratorFlora.Builder()).amountPerChunk(10.0F).replace(Blocks.water).with(BOPBlocks.coral.getDefaultState().withProperty(BlockCoral.VARIANT, BlockCoral.CoralType.ALGAE)).generationAttempts(32).create());
        
        // gem
        this.addGenerator("malachite", GeneratorStage.SAND, (new GeneratorOreSingle.Builder()).amountPerChunk(12).with(BOPGems.MALACHITE).create());
        
    }
    
    @Override
    public int getGrassColorAtPos(BlockPos pos)
    {
        return 0x619365;
    }
    
    @Override
    public int getFoliageColorAtPos(BlockPos pos)
    {
        return 0x619365;
    }
    
}
