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
import biomesoplenty.api.config.IConfigObj;
import biomesoplenty.api.enums.BOPClimates;
import biomesoplenty.api.enums.BOPFlowers;
import biomesoplenty.api.enums.BOPFoliage;
import biomesoplenty.api.generation.GeneratorStage;
import biomesoplenty.common.block.BlockBOPGrass;
import biomesoplenty.common.world.generator.GeneratorFlora;
import biomesoplenty.common.world.generator.GeneratorGrass;
import biomesoplenty.common.world.generator.GeneratorOreSingle;
import biomesoplenty.common.world.generator.GeneratorWeighted;
import biomesoplenty.common.world.generator.tree.GeneratorBush;
import biomesoplenty.common.world.generator.tree.GeneratorMahoganyTree;
import biomesoplenty.common.world.generator.tree.GeneratorTwigletTree;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityParrot;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;

public class BiomeGenOvergrownCliffs extends BOPOverworldBiome
{
    public IBlockState usualTopBlock;
    public IBlockState usualFillerBlock;
    public IBlockState alternateTopBlock;
    public IBlockState alternateFillerBlock;
    
    public BiomeGenOvergrownCliffs()
    {
        super("overgrown_cliffs", new PropsBuilder("Overgrown Cliffs").withGuiColour(8373350).withTemperature(0.95F).withRainfall(0.75F));

        // terrain
        this.terrainSettings.avgHeight(70).heightVariation(5, 100).octaves(1, 1, 1, 1, 0, 0).sidewaysNoise(0.0F);

        this.canGenerateRivers = false;
        this.canSpawnInBiome = false;
        this.canGenerateVillages = false;
        
        this.topBlock = BOPBlocks.grass.getDefaultState().withProperty(BlockBOPGrass.VARIANT, BlockBOPGrass.BOPGrassType.OVERGROWN_STONE);
        this.fillerBlock = Blocks.STONE.getDefaultState();
        this.usualTopBlock = this.topBlock;
        this.usualFillerBlock = this.fillerBlock;
        this.alternateTopBlock = Blocks.GRASS.getDefaultState();
        this.alternateFillerBlock = Blocks.DIRT.getDefaultState();
        
        this.addWeight(BOPClimates.TROPICAL, 3);
        
        this.beachBiomeLocation = null;

        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.add(new SpawnListEntry(EntityOcelot.class, 2, 1, 1));
        this.spawnableCreatureList.add(new SpawnListEntry(EntityParrot.class, 40, 1, 2));
        
        // trees
        GeneratorWeighted treeGenerator = new GeneratorWeighted(40.0F);
        this.addGenerator("trees", GeneratorStage.TREE, treeGenerator);
        treeGenerator.add("mahogany", 1, (new GeneratorMahoganyTree.Builder()).minHeight(5).maxHeight(10).create());
        treeGenerator.add("oak_bush", 8, (new GeneratorBush.Builder()).maxHeight(2).create());
        treeGenerator.add("jungle_twiglet", 2, (new GeneratorTwigletTree.Builder()).minHeight(2).maxHeight(2).log(BlockPlanks.EnumType.JUNGLE).leaves(BlockPlanks.EnumType.JUNGLE).trunkFruit(Blocks.COCOA.getDefaultState()).create());
        
        // flowers
        GeneratorWeighted flowerGenerator = new GeneratorWeighted(1.5F);
        this.addGenerator("flowers", GeneratorStage.FLOWERS, flowerGenerator);
        flowerGenerator.add("orange_cosmos", 4, (new GeneratorFlora.Builder().with(BOPFlowers.ORANGE_COSMOS).create()));
        flowerGenerator.add("dandelion", 1, (new GeneratorFlora.Builder().with(BlockFlower.EnumFlowerType.DANDELION).create()));
        flowerGenerator.add("poppy", 1, (new GeneratorFlora.Builder().with(BlockFlower.EnumFlowerType.POPPY).create()));
        
        // grasses
        GeneratorWeighted grassGenerator = new GeneratorWeighted(2.0F);
        this.addGenerator("grass", GeneratorStage.GRASS, grassGenerator);
        grassGenerator.add("shortgrass", 1, (new GeneratorGrass.Builder()).with(BOPFoliage.SHORTGRASS).create());
        grassGenerator.add("tallgrass", 1, (new GeneratorGrass.Builder()).with(BlockTallGrass.EnumType.GRASS).create());
        
        // gem
        this.addGenerator("emeralds", GeneratorStage.SAND, (new GeneratorOreSingle.Builder()).amountPerChunk(12).with(Blocks.EMERALD_ORE.getDefaultState()).create());
    }
    
    @Override
    public void configure(IConfigObj conf)
    {
        super.configure(conf);
        
        this.usualTopBlock = this.topBlock;
        this.usualFillerBlock = this.fillerBlock;
        this.alternateTopBlock = conf.getBlockState("alternateTopBlock", this.alternateTopBlock);
        this.alternateFillerBlock = conf.getBlockState("alternateFillerBlock", this.alternateFillerBlock);
    }
    
    @Override
    public void genTerrainBlocks(World world, Random rand, ChunkPrimer primer, int x, int z, double noise)
    {
        this.topBlock = (noise + rand.nextDouble() * 1.0D > 1.8D) ? this.alternateTopBlock : this.usualTopBlock;
        this.fillerBlock = (noise + rand.nextDouble() * 1.0D > 1.8D) ? this.alternateFillerBlock : this.usualFillerBlock;
        super.genTerrainBlocks(world, rand, primer, x, z, noise);
    }
}
