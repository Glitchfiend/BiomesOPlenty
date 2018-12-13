/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.biome.overworld;

import java.util.Random;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.block.BlockQueries;
import biomesoplenty.api.config.IConfigObj;
import biomesoplenty.api.enums.BOPClimates;
import biomesoplenty.api.enums.BOPFlowers;
import biomesoplenty.api.enums.BOPPlants;
import biomesoplenty.api.generation.GeneratorStage;
import biomesoplenty.common.block.BlockBOPDirt;
import biomesoplenty.common.block.BlockBOPGrass;
import biomesoplenty.common.world.generator.GeneratorColumns;
import biomesoplenty.common.world.generator.GeneratorDoubleFlora;
import biomesoplenty.common.world.generator.GeneratorFlora;
import biomesoplenty.common.world.generator.GeneratorGrass;
import biomesoplenty.common.world.generator.GeneratorOreSingle;
import biomesoplenty.common.world.generator.GeneratorWeighted;
import biomesoplenty.common.world.generator.tree.GeneratorBush;
import biomesoplenty.common.world.generator.tree.GeneratorTwigletTree;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenFossils;

public class BiomeGenScrubland extends BOPOverworldBiome
{
    public IBlockState usualTopBlock;
    public IBlockState alternateTopBlock;
    public IBlockState usualFillerBlock;
    public IBlockState alternateFillerBlock;
	
    public BiomeGenScrubland()
    {
        super("scrubland", new PropsBuilder("Scrubland").withGuiColour(0xE2CDA5).withTemperature(1.1F).withRainfall(0.1F).withRainDisabled());

        // terrain
        this.terrainSettings.avgHeight(68).heightVariation(10, 15);

        this.usualTopBlock = this.topBlock;
        this.usualFillerBlock = this.fillerBlock;
        this.alternateTopBlock = BOPBlocks.grass.getDefaultState().withProperty(BlockBOPGrass.VARIANT, BlockBOPGrass.BOPGrassType.SANDY);
        this.alternateFillerBlock = BOPBlocks.dirt.getDefaultState().withProperty(BlockBOPDirt.VARIANT, BlockBOPDirt.BOPDirtType.SANDY);
        
        this.canGenerateVillages = true;
        
        this.addWeight(BOPClimates.SAVANNA, 10);
        
        // trees & logs
        GeneratorWeighted treeGenerator = new GeneratorWeighted(4.0F);
        this.addGenerator("trees", GeneratorStage.TREE, treeGenerator);        
        treeGenerator.add("brush_twiglet", 5, (new GeneratorTwigletTree.Builder()).placeOn(BlockQueries.litFertileOrDry).minHeight(1).maxHeight(3).log(BlockPlanks.EnumType.ACACIA).leaves(BlockPlanks.EnumType.ACACIA).create());        
        treeGenerator.add("jungle_twiglet", 1, (new GeneratorTwigletTree.Builder()).minHeight(2).maxHeight(2).log(BlockPlanks.EnumType.JUNGLE).leaves(BlockPlanks.EnumType.JUNGLE).trunkFruit(Blocks.COCOA.getDefaultState()).create());
        treeGenerator.add("brush_bush", 2, (new GeneratorFlora.Builder()).placeOn(BlockQueries.litFertileOrDry).replace(Material.AIR).withNonDecayingLeaf(BlockPlanks.EnumType.SPRUCE).create());
        
        // grasses
        GeneratorWeighted grassGenerator = new GeneratorWeighted(4.0F);
        this.addGenerator("grass", GeneratorStage.GRASS, grassGenerator);
        grassGenerator.add("shortgrass", 3, (new GeneratorGrass.Builder()).with(BOPPlants.SHORTGRASS).create());
        grassGenerator.add("tallgrass", 2, (new GeneratorGrass.Builder()).with(BlockTallGrass.EnumType.GRASS).create()); 
        grassGenerator.add("doublegrass", 1, (new GeneratorDoubleFlora.Builder()).with(BlockDoublePlant.EnumPlantType.GRASS).create());
        
        // flowers
        GeneratorWeighted flowerGenerator = new GeneratorWeighted(0.3F);
        this.addGenerator("flowers", GeneratorStage.FLOWERS, flowerGenerator);
        flowerGenerator.add("wildflower", 4, (new GeneratorFlora.Builder()).with(BOPFlowers.WILDFLOWER).create());
        
        // other plants
        this.addGenerator("dead_bushes", GeneratorStage.FLOWERS, (new GeneratorFlora.Builder()).amountPerChunk(2.0F).with(Blocks.DEADBUSH.getDefaultState()).create());
    }
    
    @Override
    public void configure(IConfigObj conf)
    {
        super.configure(conf);
        
        this.usualTopBlock = this.topBlock;
        this.alternateTopBlock = conf.getBlockState("alternateTopBlock", this.alternateTopBlock);
        this.usualFillerBlock = this.fillerBlock;
        this.alternateFillerBlock = conf.getBlockState("alternateFillerBlock", this.alternateFillerBlock);
    }
    
    @Override
    public void genTerrainBlocks(World world, Random rand, ChunkPrimer primer, int x, int z, double noise)
    {
        this.topBlock = (noise + rand.nextDouble() * 1.0D > 1.9D) ? this.alternateTopBlock : this.usualTopBlock;
        this.fillerBlock = (noise + rand.nextDouble() * 1.0D > 1.9D) ? this.alternateFillerBlock : this.usualFillerBlock;
        super.genTerrainBlocks(world, rand, primer, x, z, noise);
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
}
