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
import biomesoplenty.api.block.IBlockPosQuery;
import biomesoplenty.api.config.IConfigObj;
import biomesoplenty.api.enums.BOPClimates;
import biomesoplenty.api.enums.BOPFoliage;
import biomesoplenty.api.enums.BOPTrees;
import biomesoplenty.api.generation.GeneratorStage;
import biomesoplenty.common.block.BlockBOPGrass;
import biomesoplenty.common.util.biome.GeneratorUtils.ScatterYMethod;
import biomesoplenty.common.util.block.BlockQuery;
import biomesoplenty.common.world.generator.GeneratorFlora;
import biomesoplenty.common.world.generator.GeneratorGrass;
import biomesoplenty.common.world.generator.GeneratorSplotches;
import biomesoplenty.common.world.generator.GeneratorWaterside;
import biomesoplenty.common.world.generator.GeneratorWeighted;
import biomesoplenty.common.world.generator.tree.GeneratorBush;
import biomesoplenty.common.world.generator.tree.GeneratorTwigletTree;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.passive.EntityDonkey;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;

public class BiomeGenChaparral extends BOPOverworldBiome
{    
    public IBlockState usualTopBlock;
    public IBlockState usualFillerBlock;
    public IBlockState alternateTopBlock;
    public IBlockState alternateFillerBlock;
    
    public BiomeGenChaparral()
    {
        super("chaparral", new PropsBuilder("Chaparral").withGuiColour(0xC4D675).withTemperature(0.75F).withRainfall(0.15F));
        
        // terrain
        this.terrainSettings.avgHeight(80).heightVariation(10, 40);
        
        this.addWeight(BOPClimates.WARM_TEMPERATE, 3);
        this.addWeight(BOPClimates.DRY_TEMPERATE, 5);
        
        this.usualTopBlock = this.topBlock;
        this.usualFillerBlock = this.fillerBlock;
        this.alternateTopBlock = Blocks.STONE.getDefaultState();
        this.alternateFillerBlock = Blocks.STONE.getDefaultState();

        this.spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 1, 2, 6));
        this.spawnableCreatureList.add(new SpawnListEntry(EntityDonkey.class, 1, 1, 1));
        this.spawnableCreatureList.add(new SpawnListEntry(EntityRabbit.class, 4, 2, 3));
        
        this.canGenerateVillages = true;
        
        // sand
        this.addGenerator("sand", GeneratorStage.SAND_PASS2, (new GeneratorWaterside.Builder()).amountPerChunk(3).maxRadius(7).with(Blocks.SAND.getDefaultState()).create());
        
        // stone patches
        IBlockPosQuery emptyStoneOrGrass = BlockQuery.buildAnd().withAirAbove().states(this.topBlock, Blocks.STONE.getDefaultState()).create();
        this.addGenerator("stone_patches", GeneratorStage.SAND, (new GeneratorSplotches.Builder()).amountPerChunk(5).splotchSize(15).replace(emptyStoneOrGrass).with(BOPBlocks.grass.getDefaultState().withProperty(BlockBOPGrass.VARIANT, BlockBOPGrass.BOPGrassType.OVERGROWN_STONE)).scatterYMethod(ScatterYMethod.AT_SURFACE).create());
        
        // flowers
        GeneratorWeighted flowerGenerator = new GeneratorWeighted(0.6F);
        this.addGenerator("flowers", GeneratorStage.FLOWERS, flowerGenerator);
        flowerGenerator.add("dandelion", 7, (new GeneratorFlora.Builder().with(BlockFlower.EnumFlowerType.DANDELION).create()));
        flowerGenerator.add("poppy", 1, (new GeneratorFlora.Builder().with(BlockFlower.EnumFlowerType.POPPY).create()));
        
        // grasses
        GeneratorWeighted grassGenerator = new GeneratorWeighted(1.5F);
        this.addGenerator("grass", GeneratorStage.GRASS, grassGenerator);
        grassGenerator.add("tallgrass", 2, (new GeneratorGrass.Builder()).with(BlockTallGrass.EnumType.GRASS).create());
        grassGenerator.add("shortgrass", 1, (new GeneratorGrass.Builder()).with(BOPFoliage.SHORTGRASS).create());
        
        // trees
        GeneratorWeighted treeGenerator = new GeneratorWeighted(8.5F);
        this.addGenerator("trees", GeneratorStage.TREE, treeGenerator);
        treeGenerator.add("twiglet", 2, (new GeneratorTwigletTree.Builder()).minHeight(2).maxHeight(2).log(BlockPlanks.EnumType.OAK).leaves(BlockPlanks.EnumType.OAK).create());
        treeGenerator.add("oak_bush", 4, (new GeneratorBush.Builder()).maxHeight(2).create());
        treeGenerator.add("small_bush", 1, (new GeneratorFlora.Builder()).placeOn(this.topBlock).replace(Material.AIR).withNonDecayingLeaf(BOPTrees.DEAD).create());
        
        // other plants
        this.addGenerator("bushes", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(1.5F).with(BOPFoliage.BUSH).create());
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
        this.topBlock = (noise + rand.nextDouble() * 1.0D > 2.0D) ? this.alternateTopBlock : this.usualTopBlock;
        this.fillerBlock = (noise + rand.nextDouble() * 1.0D > 2.0D) ? this.alternateFillerBlock : this.usualFillerBlock;
        super.genTerrainBlocks(world, rand, primer, x, z, noise);
    }
}
