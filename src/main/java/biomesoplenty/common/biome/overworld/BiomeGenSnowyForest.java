/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.biome.overworld;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.block.BlockQueries;
import biomesoplenty.api.enums.BOPClimates;
import biomesoplenty.api.enums.BOPFlowers;
import biomesoplenty.api.enums.BOPFoliage;
import biomesoplenty.api.enums.BOPTrees;
import biomesoplenty.api.generation.GeneratorStage;
import biomesoplenty.common.block.BlockBOPDirt;
import biomesoplenty.common.block.BlockBOPFlatPlant;
import biomesoplenty.common.block.BlockBOPGrass;
import biomesoplenty.common.world.generator.GeneratorFlora;
import biomesoplenty.common.world.generator.GeneratorGrass;
import biomesoplenty.common.world.generator.GeneratorWeighted;
import biomesoplenty.common.world.generator.tree.GeneratorBasicTree;
import biomesoplenty.common.world.generator.tree.GeneratorBigTree;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.util.math.BlockPos;


public class BiomeGenSnowyForest extends BOPOverworldBiome
{
    public BiomeGenSnowyForest()
    {
        super("snowy_forest", new PropsBuilder("Snowy Forest").withGuiColour(0xABD6BC).withSnowEnabled().withTemperature(-0.25F).withRainfall(0.5F));

        // terrain
        this.terrainSettings.avgHeight(66).heightVariation(6, 21);
        
        this.topBlock = BOPBlocks.grass.getDefaultState().withProperty(BlockBOPGrass.VARIANT, BlockBOPGrass.BOPGrassType.SILTY);
        this.fillerBlock = BOPBlocks.dirt.getDefaultState().withProperty(BlockBOPDirt.VARIANT, BlockBOPDirt.BOPDirtType.SILTY);
        
        this.spawnableCreatureList.add(new SpawnListEntry(EntityRabbit.class, 4, 2, 3));
        
        this.canGenerateVillages = false;

        this.addWeight(BOPClimates.TUNDRA, 5);
         
        // trees
        GeneratorWeighted treeGenerator = new GeneratorWeighted(3);
        this.addGenerator("trees", GeneratorStage.TREE, treeGenerator);
        treeGenerator.add("oak", 3, (new GeneratorBasicTree.Builder()).leaves(BOPTrees.DEAD).create());
        treeGenerator.add("dying_tree", 1, (new GeneratorBigTree.Builder()).minHeight(5).maxHeight(12).foliageHeight(2).leaves(BOPTrees.DEAD).create());
        
        // grasses        
        GeneratorWeighted grassGenerator = new GeneratorWeighted(1.0F);
        this.addGenerator("grass", GeneratorStage.GRASS, grassGenerator);
        grassGenerator.add("shortgrass", 2, (new GeneratorGrass.Builder()).with(BOPFoliage.SHORTGRASS).create());
        grassGenerator.add("tallgrass", 1, (new GeneratorGrass.Builder()).with(BlockTallGrass.EnumType.GRASS).create());

        // other plants
        this.addGenerator("dead_leaf_piles", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.5F).placeOn(BlockQueries.darkFertile).with(BlockBOPFlatPlant.PlantType.DEADLEAFPILE).generationAttempts(64).create());
        
        // flowers
        GeneratorWeighted flowerGenerator = new GeneratorWeighted(0.3F);
        this.addGenerator("flowers", GeneratorStage.FLOWERS, flowerGenerator);
        flowerGenerator.add("violets", 1, (new GeneratorFlora.Builder().with(BOPFlowers.VIOLET).create()));
    }
    
    @Override
    public int getGrassColorAtPos(BlockPos pos)
    {
        return getModdedBiomeGrassColor(0xBCA165);
    }

    @Override
    public int getFoliageColorAtPos(BlockPos pos)
    {
        return getModdedBiomeFoliageColor(0xBCA165);
    }
}
