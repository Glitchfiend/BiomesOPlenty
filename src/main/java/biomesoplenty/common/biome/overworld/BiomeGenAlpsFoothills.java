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
import biomesoplenty.api.enums.BOPTrees;
import biomesoplenty.api.enums.BOPWoods;
import biomesoplenty.api.generation.GeneratorStage;
import biomesoplenty.common.block.BlockBOPGrass;
import biomesoplenty.common.util.biome.GeneratorUtils.ScatterYMethod;
import biomesoplenty.common.util.block.BlockQuery;
import biomesoplenty.common.world.generator.GeneratorLakes;
import biomesoplenty.common.world.generator.GeneratorOreSingle;
import biomesoplenty.common.world.generator.GeneratorWeighted;
import biomesoplenty.common.world.generator.tree.GeneratorTaigaTree;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.passive.EntityLlama;
import net.minecraft.init.Blocks;

public class BiomeGenAlpsFoothills extends BOPOverworldBiome
{
    public BiomeGenAlpsFoothills()
    {
        super("alps_foothills", new PropsBuilder("Alps Foothills").withGuiColour(13421772).withSnowEnabled().withTemperature(-0.25F).withRainfall(0.3F));
        
	    this.terrainSettings.avgHeight(120).heightVariation(48, 64).octaves(0, 1, 1, 3, 1, 0);
	    
	    this.topBlock = BOPBlocks.grass.getDefaultState().withProperty(BlockBOPGrass.VARIANT, BlockBOPGrass.BOPGrassType.OVERGROWN_STONE);
	    this.fillerBlock = Blocks.STONE.getDefaultState();
	    this.avgDirtDepth = 8;

        this.canGenerateRivers = false;
        this.canSpawnInBiome = false;
        this.canGenerateVillages = false;
        
        this.beachBiomeLocation = null;
        
        this.clearWeights();

        this.spawnableCreatureList.clear();
        this.spawnableCreatureList.add(new SpawnListEntry(EntityLlama.class, 5, 4, 6));
        
        IBlockPosQuery emptyStone = BlockQuery.buildAnd().withAirAbove().states(BOPBlocks.grass.getDefaultState().withProperty(BlockBOPGrass.VARIANT, BlockBOPGrass.BOPGrassType.OVERGROWN_STONE)).create();
        GeneratorWeighted treeGenerator = new GeneratorWeighted(0.5F);
        this.addGenerator("trees", GeneratorStage.TREE, treeGenerator);
        treeGenerator.add("fir", 1, (new GeneratorTaigaTree.Builder()).placeOn(emptyStone).log(BOPWoods.FIR).leaves(BOPTrees.FIR).minHeight(10).maxHeight(19).create());
    
        // hot springs
        this.addGenerator("hot_springs", GeneratorStage.SAND, (new GeneratorLakes.Builder()).amountPerChunk(0.5F).liquid(BOPBlocks.hot_spring_water).frozenLiquid((IBlockState)null).scatterYMethod(ScatterYMethod.AT_SURFACE).create());
        
        // gem
        this.addGenerator("emerald", GeneratorStage.SAND, (new GeneratorOreSingle.Builder()).amountPerChunk(12).with(Blocks.EMERALD_ORE.getDefaultState()).create());
    }
}
