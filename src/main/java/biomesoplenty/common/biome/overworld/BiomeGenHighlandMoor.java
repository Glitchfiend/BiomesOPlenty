/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.biome.overworld;

import java.util.Random;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.block.BlockQueries;
import biomesoplenty.api.enums.BOPClimates;
import biomesoplenty.api.enums.BOPFlowers;
import biomesoplenty.api.enums.BOPPlants;
import biomesoplenty.api.enums.BOPTrees;
import biomesoplenty.api.generation.GeneratorStage;
import biomesoplenty.common.block.BlockBOPCoral;
import biomesoplenty.common.block.BlockBOPDirt;
import biomesoplenty.common.block.BlockBOPDoublePlant;
import biomesoplenty.common.block.BlockBOPGrass;
import biomesoplenty.common.util.biome.GeneratorUtils.ScatterYMethod;
import biomesoplenty.common.world.generator.GeneratorDoubleFlora;
import biomesoplenty.common.world.generator.GeneratorFlora;
import biomesoplenty.common.world.generator.GeneratorGrass;
import biomesoplenty.common.world.generator.GeneratorLakes;
import biomesoplenty.common.world.generator.GeneratorOreSingle;
import biomesoplenty.common.world.generator.GeneratorWaterside;
import biomesoplenty.common.world.generator.GeneratorWeighted;
import biomesoplenty.common.world.generator.tree.GeneratorTwigletTree;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenFossils;

public class BiomeGenHighlandMoor extends BOPOverworldBiome
{    
    
    public BiomeGenHighlandMoor()
    {
        super("highland_moor", new PropsBuilder("Highland Moor").withGuiColour(0x71AA70).withTemperature(0.6F).withRainfall(0.6F));

        // terrain
        this.terrainSettings.avgHeight(100).heightVariation(35, 35).octaves(0, 1, 1, 3, 1, 0);

        //this.skyColor =0xA0C5D3;
        this.topBlock = BOPBlocks.grass.getDefaultState().withProperty(BlockBOPGrass.VARIANT, BlockBOPGrass.BOPGrassType.LOAMY);
        this.fillerBlock = BOPBlocks.dirt.getDefaultState().withProperty(BlockBOPDirt.VARIANT, BlockBOPDirt.BOPDirtType.LOAMY);
        
        this.canGenerateRivers = false;
        this.canGenerateVillages = false;
        
        this.beachBiomeLocation = null;
        
        this.clearWeights();
          
        // mud
        this.addGenerator("mud", GeneratorStage.SAND_PASS2, (new GeneratorWaterside.Builder()).amountPerChunk(2).maxRadius(7).with(BOPBlocks.mud.getDefaultState()).create());
        
        // lakes
        this.addGenerator("lakes", GeneratorStage.SAND, (new GeneratorLakes.Builder()).amountPerChunk(3.5F).waterLakeForBiome(this).create());
        
        // trees & logs
        GeneratorWeighted treeGenerator = new GeneratorWeighted(2.5F);
        this.addGenerator("trees", GeneratorStage.TREE, treeGenerator);        
        treeGenerator.add("brush_bush", 2, (new GeneratorFlora.Builder()).placeOn(BlockQueries.litFertileOrDry).replace(Material.AIR).withNonDecayingLeaf(BOPTrees.JACARANDA).generationAttempts(64).create());
        
        // grasses        
        GeneratorWeighted grassGenerator = new GeneratorWeighted(3.5F);
        this.addGenerator("grass", GeneratorStage.GRASS, grassGenerator);
        grassGenerator.add("tallgrass", 2, (new GeneratorGrass.Builder()).with(BlockTallGrass.EnumType.GRASS).create());
        grassGenerator.add("shortgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.SHORTGRASS).create());

        // shrooms
        this.addGenerator("brown_mushrooms", GeneratorStage.SHROOM,(new GeneratorFlora.Builder()).amountPerChunk(0.5F).generationAttempts(16).with(Blocks.BROWN_MUSHROOM.getDefaultState()).create());
        this.addGenerator("red_mushrooms", GeneratorStage.SHROOM,(new GeneratorFlora.Builder()).amountPerChunk(0.3F).generationAttempts(16).with(Blocks.RED_MUSHROOM.getDefaultState()).create());
        
        // other plants
        this.addGenerator("koru", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.7F).with(BOPPlants.KORU).create());
        this.addGenerator("flax", GeneratorStage.FLOWERS,(new GeneratorDoubleFlora.Builder()).amountPerChunk(0.75F).with(BlockBOPDoublePlant.DoublePlantType.FLAX).generationAttempts(6).create());
        this.addGenerator("algae", GeneratorStage.LILYPAD, (new GeneratorFlora.Builder()).amountPerChunk(5.0F).replace(Blocks.WATER).with(BOPBlocks.coral.getDefaultState().withProperty(BlockBOPCoral.VARIANT, BlockBOPCoral.CoralType.ALGAE)).scatterYMethod(ScatterYMethod.AT_GROUND).create());
    
        // gem
        this.addGenerator("emerald", GeneratorStage.SAND, (new GeneratorOreSingle.Builder()).amountPerChunk(12).with(Blocks.EMERALD_ORE.getDefaultState()).create());
    }
    
    @Override
    public void decorate(World worldIn, Random rand, BlockPos pos)
    {
        super.decorate(worldIn, rand, pos);

        if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, pos, net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.FOSSIL))
        if (rand.nextInt(64) == 0)
        {
            (new WorldGenFossils()).generate(worldIn, rand, pos);
        }
    }
    
    @Override
    public int getGrassColorAtPos(BlockPos pos)
    {
        return getModdedBiomeGrassColor(0x71AA70);
    }
    
    @Override
    public int getFoliageColorAtPos(BlockPos pos)
    {
        return getModdedBiomeFoliageColor(0x78C46D);
    }
}
