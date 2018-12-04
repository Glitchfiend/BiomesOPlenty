/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.biome.overworld;

import java.util.Random;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.block.BlockQueries;
import biomesoplenty.api.enums.BOPClimates;
import biomesoplenty.api.enums.BOPGems;
import biomesoplenty.api.enums.BOPPlants;
import biomesoplenty.api.generation.GeneratorStage;
import biomesoplenty.common.block.BlockBOPCoral;
import biomesoplenty.common.block.BlockBOPDirt;
import biomesoplenty.common.block.BlockBOPGrass;
import biomesoplenty.common.block.BlockBOPLilypad;
import biomesoplenty.common.util.biome.GeneratorUtils.ScatterYMethod;
import biomesoplenty.common.world.generator.GeneratorFlora;
import biomesoplenty.common.world.generator.GeneratorGrass;
import biomesoplenty.common.world.generator.GeneratorLakes;
import biomesoplenty.common.world.generator.GeneratorOreSingle;
import biomesoplenty.common.world.generator.GeneratorWaterside;
import biomesoplenty.common.world.generator.GeneratorWeighted;
import biomesoplenty.common.world.generator.tree.GeneratorBasicTree;
import biomesoplenty.common.world.generator.tree.GeneratorTaigaTree;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenFossils;

public class BiomeGenLandOfLakes extends BOPOverworldBiome
{
   
    // TODO: fog color / closeness? what's that?
    // TODO: should there be foliage colors / water colors?
        
    public BiomeGenLandOfLakes()
    {
        super("land_of_lakes", new PropsBuilder("Land of Lakes").withGuiColour(0x66A06E).withTemperature(0.5F).withRainfall(0.9F));

        // terrain
        this.terrainSettings.avgHeight(63).heightVariation(10, 10).octaves(5, 5, 0, 0, 1, 1).sidewaysNoise(0.1D); 
        
        this.topBlock = BOPBlocks.grass.getDefaultState().withProperty(BlockBOPGrass.VARIANT, BlockBOPGrass.BOPGrassType.LOAMY);
        this.fillerBlock = BOPBlocks.dirt.getDefaultState().withProperty(BlockBOPDirt.VARIANT, BlockBOPDirt.BOPDirtType.LOAMY);
        this.seaFloorBlock = BOPBlocks.mud.getDefaultState();
        
        this.canGenerateVillages = false;

        this.addWeight(BOPClimates.COLD_SWAMP, 3);
        
        if (BOPBiomes.gravel_beach.isPresent())
        {
        	this.beachBiomeLocation = ((BOPOverworldBiome)BOPBiomes.gravel_beach.get()).getResourceLocation();
        }
        
        // gravel
        this.addGenerator("gravel", GeneratorStage.SAND_PASS2, (new GeneratorWaterside.Builder()).amountPerChunk(4).maxRadius(7).with(Blocks.GRAVEL.getDefaultState()).create());
        
        // mud
        this.addGenerator("mud", GeneratorStage.SAND_PASS2, (new GeneratorWaterside.Builder()).amountPerChunk(8).maxRadius(7).with(BOPBlocks.mud.getDefaultState()).create());
        
        // lakes
        this.addGenerator("lakes", GeneratorStage.SAND, (new GeneratorLakes.Builder()).amountPerChunk(1.0F).waterLakeForBiome(this).create());
        
        // trees & logs
        GeneratorWeighted treeGenerator = new GeneratorWeighted(40);
        this.addGenerator("trees", GeneratorStage.TREE, treeGenerator);
        treeGenerator.add("oak", 5, (new GeneratorBasicTree.Builder()).create());
        treeGenerator.add("birch", 1, (new GeneratorBasicTree.Builder()).log(BlockPlanks.EnumType.BIRCH).leaves(BlockPlanks.EnumType.BIRCH).create());
        treeGenerator.add("spruce", 3, (new GeneratorTaigaTree.Builder()).maxHeight(13).create()); // TODO: implement pine cones
        
        // other plants
        this.addGenerator("dead_leaf_piles", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(1.0F).placeOn(BlockQueries.fertile).with(BOPPlants.DEADLEAFPILE).create());
        this.addGenerator("leaf_piles", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.5F).placeOn(BlockQueries.fertile).with(BOPPlants.LEAFPILE).generationAttempts(64).create());
        this.addGenerator("wild_rice", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(1.0F).with(BOPPlants.WILDRICE).generationAttempts(64).create());
        
        // water plants
        this.addGenerator("lily", GeneratorStage.LILYPAD, (new GeneratorFlora.Builder()).amountPerChunk(0.2F).with(Blocks.WATERLILY.getDefaultState()).create());
        this.addGenerator("medium_lily", GeneratorStage.LILYPAD, (new GeneratorFlora.Builder()).amountPerChunk(0.2F).with(BlockBOPLilypad.LilypadType.MEDIUM).create());
        this.addGenerator("small_lily", GeneratorStage.LILYPAD, (new GeneratorFlora.Builder()).amountPerChunk(0.2F).with(BlockBOPLilypad.LilypadType.SMALL).create());
        this.addGenerator("tiny_lily", GeneratorStage.LILYPAD, (new GeneratorFlora.Builder()).amountPerChunk(0.2F).with(BlockBOPLilypad.LilypadType.TINY).create());
        this.addGenerator("algae", GeneratorStage.LILYPAD, (new GeneratorFlora.Builder()).amountPerChunk(1.0F).replace(Blocks.WATER).with(BOPBlocks.coral.getDefaultState().withProperty(BlockBOPCoral.VARIANT, BlockBOPCoral.CoralType.ALGAE)).scatterYMethod(ScatterYMethod.AT_GROUND).create());
        this.addGenerator("water_reeds", GeneratorStage.LILYPAD, (new GeneratorFlora.Builder()).amountPerChunk(1.0F).with(BOPPlants.REED).generationAttempts(32).create());

        // grasses
        GeneratorWeighted grassGenerator = new GeneratorWeighted(10.0F);
        this.addGenerator("grass", GeneratorStage.GRASS, grassGenerator);
        grassGenerator.add("shortgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.SHORTGRASS).create());
        grassGenerator.add("mediumgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.MEDIUMGRASS).create());
        //grassGenerator.add("dampgrass", 5, (new GeneratorGrass.Builder()).with(BOPPlants.DAMPGRASS).create());
        grassGenerator.add("tallgrass", 5, (new GeneratorGrass.Builder()).with(BlockTallGrass.EnumType.GRASS).create());
        
        // gem
        this.addGenerator("malachite", GeneratorStage.SAND, (new GeneratorOreSingle.Builder()).amountPerChunk(12).with(BOPGems.MALACHITE).create());
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
        double noise = GRASS_COLOR_NOISE.getValue((double)pos.getX() * 0.0225D, (double)pos.getZ() * 0.0225D);
        return getModdedBiomeGrassColor(noise < -0.1D ? 13414508 : 13419628);
    }

    @Override
    public int getFoliageColorAtPos(BlockPos pos)
    {
        double noise = GRASS_COLOR_NOISE.getValue((double)pos.getX() * 0.0225D, (double)pos.getZ() * 0.0225D);
        return getModdedBiomeFoliageColor(noise < -0.1D ? 12766316 : 10730594);
    }  
}
