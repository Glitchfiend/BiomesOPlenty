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
import biomesoplenty.api.block.IBlockPosQuery;
import biomesoplenty.api.enums.BOPClimates;
import biomesoplenty.api.enums.BOPFoliage;
import biomesoplenty.api.enums.BOPPlants;
import biomesoplenty.api.enums.BOPTrees;
import biomesoplenty.api.enums.BOPWoods;
import biomesoplenty.api.generation.GeneratorStage;
import biomesoplenty.common.block.BlockBOPCoral;
import biomesoplenty.common.block.BlockBOPDoublePlant;
import biomesoplenty.common.util.biome.GeneratorUtils.ScatterYMethod;
import biomesoplenty.common.util.block.BlockQuery;
import biomesoplenty.common.world.generator.GeneratorColumns;
import biomesoplenty.common.world.generator.GeneratorDoubleFlora;
import biomesoplenty.common.world.generator.GeneratorFlora;
import biomesoplenty.common.world.generator.GeneratorGrass;
import biomesoplenty.common.world.generator.GeneratorSplatter;
import biomesoplenty.common.world.generator.GeneratorWaterside;
import biomesoplenty.common.world.generator.GeneratorWeighted;
import biomesoplenty.common.world.generator.tree.GeneratorBayouTree;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenFossils;

public class BiomeGenBayou extends BOPOverworldBiome
{    
    
    public BiomeGenBayou()
    {
        super("bayou", new PropsBuilder("Bayou").withGuiColour(0x7DAD51).withTemperature(0.95F).withRainfall(0.9F).withWaterColor(0xFFD932));
        
        // terrain
        this.terrainSettings.avgHeight(62).heightVariation(6, 3).octaves(1, 1, 1, 1, 0, 0).sidewaysNoise(0.0F);
        
        //this.skyColor = 0xACC4BC;
        this.seaFloorBlock = BOPBlocks.mud.getDefaultState();
        
        this.canSpawnInBiome = false;
        this.canGenerateRivers = false;
        this.canGenerateVillages = false;
        
        this.beachBiomeLocation = null;

        this.addWeight(BOPClimates.SUBTROPICAL, 10);
        
        this.spawnableMonsterList.add(new SpawnListEntry(EntitySlime.class, 10, 1, 3));
        
        // mud
        IBlockPosQuery emptyGrassOrDirt = BlockQuery.buildAnd().withAirAbove().states(this.topBlock, this.fillerBlock).create();
        this.addGenerator("mud", GeneratorStage.SAND_PASS2, (new GeneratorWaterside.Builder()).amountPerChunk(1).maxRadius(3).with(BOPBlocks.mud.getDefaultState()).create());
        this.addGenerator("mud_splatter", GeneratorStage.SAND, (new GeneratorSplatter.Builder()).amountPerChunk(0.2F).replace(emptyGrassOrDirt).with(BOPBlocks.mud.getDefaultState()).create());
        
        // trees & logs
        GeneratorWeighted treeGenerator = new GeneratorWeighted(8);
        this.addGenerator("trees", GeneratorStage.TREE, treeGenerator);
        treeGenerator.add("willow", 3, (new GeneratorBayouTree.Builder()).log(BOPWoods.WILLOW).leaves(BOPTrees.WILLOW).minHeight(6).maxHeight(12).minLeavesRadius(1).leavesGradient(2).create());
        treeGenerator.add("willow_large", 1, (new GeneratorBayouTree.Builder()).log(BOPWoods.WILLOW).leaves(BOPTrees.WILLOW).minHeight(10).maxHeight(18).minLeavesRadius(2).leavesGradient(3).create());
        
        // grasses
        GeneratorWeighted grassGenerator = new GeneratorWeighted(2.0F);
        this.addGenerator("grass", GeneratorStage.GRASS, grassGenerator);
        grassGenerator.add("shortgrass", 1, (new GeneratorGrass.Builder()).with(BOPFoliage.SHORTGRASS).create());
        grassGenerator.add("tallgrass", 2, (new GeneratorGrass.Builder()).with(BlockTallGrass.EnumType.GRASS).create());
        grassGenerator.add("doublegrass", 1, (new GeneratorDoubleFlora.Builder()).with(BlockDoublePlant.EnumPlantType.GRASS).create());
        
        // other plants
        this.addGenerator("koru", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.3F).with(BOPFoliage.KORU).create());
        this.addGenerator("cattail", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(6.0F).with(BOPPlants.CATTAIL).create());
        this.addGenerator("double_cattail", GeneratorStage.FLOWERS, (new GeneratorDoubleFlora.Builder()).amountPerChunk(8.0F).with(BlockBOPDoublePlant.DoublePlantType.TALL_CATTAIL).create());
        this.addGenerator("sugar_cane", GeneratorStage.FLOWERS,(new GeneratorColumns.Builder()).amountPerChunk(3.0F).generationAttempts(24).placeOn(BlockQueries.litFertileWaterside).with(Blocks.REEDS.getDefaultState()).minHeight(1).maxHeight(3).create());
        
        // water plants
        this.addGenerator("lily", GeneratorStage.LILYPAD, (new GeneratorFlora.Builder()).amountPerChunk(2.5F).with(Blocks.WATERLILY.getDefaultState()).create());
        this.addGenerator("algae", GeneratorStage.LILYPAD, (new GeneratorFlora.Builder()).amountPerChunk(4.0F).replace(Blocks.WATER).with(BOPBlocks.coral.getDefaultState().withProperty(BlockBOPCoral.VARIANT, BlockBOPCoral.CoralType.ALGAE)).scatterYMethod(ScatterYMethod.AT_GROUND).create());
        this.addGenerator("water_reeds", GeneratorStage.LILYPAD, (new GeneratorFlora.Builder()).amountPerChunk(0.8F).with(BOPPlants.REED).generationAttempts(32).create());
        this.addGenerator("watergrass", GeneratorStage.LILYPAD, (new GeneratorFlora.Builder()).amountPerChunk(2.0F).with(BOPPlants.WATERGRASS).generationAttempts(32).create());
        
        // shrooms
        this.addGenerator("red_mushrooms", GeneratorStage.SHROOM,(new GeneratorFlora.Builder()).amountPerChunk(0.3F).generationAttempts(16).with(Blocks.RED_MUSHROOM.getDefaultState()).create());
        this.addGenerator("brown_mushrooms", GeneratorStage.SHROOM,(new GeneratorFlora.Builder()).amountPerChunk(0.5F).generationAttempts(16).with(Blocks.BROWN_MUSHROOM.getDefaultState()).create());
        
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
        return getModdedBiomeGrassColor(0x7DAD51);
    }
    
    @Override
    public int getFoliageColorAtPos(BlockPos pos)
    {
        return getModdedBiomeFoliageColor(0x9DDD66);
    }
}
