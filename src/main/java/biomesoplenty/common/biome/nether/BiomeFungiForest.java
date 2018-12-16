/*******************************************************************************
 * Copyright 2014-2017, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.biome.nether;

import java.util.Random;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.block.IBlockPosQuery;
import biomesoplenty.api.config.IConfigObj;
import biomesoplenty.api.enums.BOPClimates;
import biomesoplenty.api.generation.GeneratorStage;
import biomesoplenty.common.block.BlockBOPGrass;
import biomesoplenty.common.block.BlockBOPMushroom;
import biomesoplenty.common.util.biome.GeneratorUtils.ScatterYMethod;
import biomesoplenty.common.util.block.BlockQuery;
import biomesoplenty.common.world.generator.GeneratorBigMushroom;
import biomesoplenty.common.world.generator.GeneratorFlora;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;

public class BiomeFungiForest extends BOPHellBiome
{
    public IBlockState usualTopBlock;
    public IBlockState alternateTopBlock;
    
    public BiomeFungiForest()
    {
        super("fungi_forest", new PropsBuilder("Fungi Forest").withGuiColour(0xA93C3E).withTemperature(2.0F).withRainfall(0.0F).withRainDisabled());

        this.addWeight(BOPClimates.HELL, 5);
        
        this.usualTopBlock = this.topBlock;
        this.alternateTopBlock = BOPBlocks.grass.getDefaultState().withProperty(BlockBOPGrass.VARIANT, BlockBOPGrass.BOPGrassType.MYCELIAL_NETHERRACK);

        // shrooms
        IBlockPosQuery surfaceBlocks = BlockQuery.buildOr().states(this.topBlock).create();
        this.addGenerator("glowshroom", GeneratorStage.SHROOM,(new GeneratorFlora.Builder()).amountPerChunk(1.5F).generationAttempts(16).with(BlockBOPMushroom.MushroomType.GLOWSHROOM).scatterYMethod(ScatterYMethod.NETHER_SURFACE).create());
        this.addGenerator("toadstools", GeneratorStage.SHROOM,(new GeneratorFlora.Builder()).amountPerChunk(1.0F).generationAttempts(16).with(BlockBOPMushroom.MushroomType.TOADSTOOL).scatterYMethod(ScatterYMethod.NETHER_SURFACE).create());
        this.addGenerator("red_mushrooms", GeneratorStage.SHROOM,(new GeneratorFlora.Builder()).amountPerChunk(5.0F).generationAttempts(16).with(Blocks.RED_MUSHROOM.getDefaultState()).scatterYMethod(ScatterYMethod.NETHER_SURFACE).create());
        this.addGenerator("brown_mushrooms", GeneratorStage.SHROOM,(new GeneratorFlora.Builder()).amountPerChunk(2.0F).generationAttempts(16).with(Blocks.BROWN_MUSHROOM.getDefaultState()).scatterYMethod(ScatterYMethod.NETHER_SURFACE).create());
        this.addGenerator("big_brown_mushroom", GeneratorStage.BIG_SHROOM,(new GeneratorBigMushroom.Builder()).amountPerChunk(2.0F).mushroomType(GeneratorBigMushroom.BigMushroomType.BROWN).scatterYMethod(ScatterYMethod.NETHER_SURFACE).placeOn(surfaceBlocks).create());
        this.addGenerator("big_red_mushroom", GeneratorStage.BIG_SHROOM,(new GeneratorBigMushroom.Builder()).amountPerChunk(30.0F).mushroomType(GeneratorBigMushroom.BigMushroomType.RED).scatterYMethod(ScatterYMethod.NETHER_SURFACE).minHeight(5).maxHeight(10).placeOn(surfaceBlocks).create());
    }
    
    @Override
    public void configure(IConfigObj conf)
    {
        super.configure(conf);
        
        this.usualTopBlock = this.topBlock;
        this.alternateTopBlock = conf.getBlockState("alternateTopBlock", this.alternateTopBlock);
    }
    
    @Override
    public void genTerrainBlocks(World world, Random rand, ChunkPrimer primer, int x, int z, double noise)
    {
        this.topBlock = (noise + rand.nextDouble() * 1.0D > 1.8D) ? this.alternateTopBlock : this.usualTopBlock;
        super.genTerrainBlocks(world, rand, primer, x, z, noise);
    }
}
