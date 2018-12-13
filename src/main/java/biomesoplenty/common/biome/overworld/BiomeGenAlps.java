/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.biome.overworld;

import com.google.common.base.CaseFormat;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.block.IBlockPosQuery;
import biomesoplenty.api.enums.BOPClimates;
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

public class BiomeGenAlps extends BOPOverworldBiome
{
    public enum AlpsType {ALPS, ALPS_FOOTHILLS}
    
    public AlpsType type;
    
    public BiomeGenAlps(AlpsType type)
    {
        super(type.name().toLowerCase(), new PropsBuilder(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, type.toString())).withGuiColour(13421772).withSnowEnabled().withTemperature(-0.25F).withRainfall(0.3F));
        
        this.type = type;
        
        // terrain
        switch (type)
        {
            case ALPS:
                this.terrainSettings.avgHeight(198).heightVariation(12, 12).octaves(1, 1, 2, 2, 3, 3);
                this.topBlock = Blocks.SNOW.getDefaultState();
                this.fillerBlock = Blocks.SNOW.getDefaultState();
                break;
                
            case ALPS_FOOTHILLS:
                this.terrainSettings.avgHeight(120).heightVariation(48, 64).octaves(0, 1, 1, 3, 1, 0);
                this.hasBiomeEssence = false;
                this.topBlock = BOPBlocks.grass.getDefaultState().withProperty(BlockBOPGrass.VARIANT, BlockBOPGrass.BOPGrassType.OVERGROWN_STONE);
                this.fillerBlock = Blocks.STONE.getDefaultState();
                break;
        }

        this.canGenerateRivers = false;
        this.canSpawnInBiome = false;
        this.canGenerateVillages = false;
        
        this.beachBiomeLocation = null;
        
        if (type == AlpsType.ALPS)
        {
            this.addWeight(BOPClimates.TUNDRA, 3);
            this.addWeight(BOPClimates.COLD_DESERT, 5);
        }
        
        this.avgDirtDepth = 8;

        this.spawnableCreatureList.clear();
        
        // trees & logs
        if (type == AlpsType.ALPS_FOOTHILLS)
        {
            this.spawnableCreatureList.add(new SpawnListEntry(EntityLlama.class, 5, 4, 6));
            
            IBlockPosQuery emptyStone = BlockQuery.buildAnd().withAirAbove().states(BOPBlocks.grass.getDefaultState().withProperty(BlockBOPGrass.VARIANT, BlockBOPGrass.BOPGrassType.OVERGROWN_STONE)).create();
            GeneratorWeighted treeGenerator = new GeneratorWeighted(0.5F);
            this.addGenerator("trees", GeneratorStage.TREE, treeGenerator);
            treeGenerator.add("fir", 1, (new GeneratorTaigaTree.Builder()).placeOn(emptyStone).log(BOPWoods.FIR).leaves(BOPTrees.FIR).minHeight(10).maxHeight(19).create());
        
            // hot springs
            this.addGenerator("hot_springs", GeneratorStage.SAND, (new GeneratorLakes.Builder()).amountPerChunk(0.5F).liquid(BOPBlocks.hot_spring_water).frozenLiquid((IBlockState)null).scatterYMethod(ScatterYMethod.AT_SURFACE).create());
        }
        
        // gem
        this.addGenerator("emerald", GeneratorStage.SAND, (new GeneratorOreSingle.Builder()).amountPerChunk(12).with(Blocks.EMERALD_ORE.getDefaultState()).create());
    }
}
