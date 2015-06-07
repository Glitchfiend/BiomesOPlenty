/*******************************************************************************
 * Copyright 2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.biome.overworld;

import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.init.Blocks;
import net.minecraftforge.common.BiomeManager.BiomeType;
import biomesoplenty.api.biome.BOPBiome;
import biomesoplenty.api.biome.generation.GeneratorStage;
import biomesoplenty.api.biome.generation.GeneratorWeighted;
import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.block.BlockBOPLilypad;
import biomesoplenty.common.block.BlockCoral;
import biomesoplenty.common.block.BlockMud;
import biomesoplenty.common.enums.BOPGems;
import biomesoplenty.common.enums.BOPPlants;
import biomesoplenty.common.world.feature.GeneratorDoubleFlora;
import biomesoplenty.common.world.feature.GeneratorFlora;
import biomesoplenty.common.world.feature.GeneratorGrass;
import biomesoplenty.common.world.feature.GeneratorOreSingle;
import biomesoplenty.common.world.feature.GeneratorWaterside;

public class BiomeGenMarsh extends BOPBiome
{
   
    // TODO: fog color / closeness? what's that?
    // TODO: should there be foliage colors / water colors?
        
    public BiomeGenMarsh()
    {
        
        // terrain
        // TODO: the terrain seems to generate a bit higher than expected with these numbers.
        this.bopMinHeight = 56;
        this.bopMaxHeight = 66;
        this.sidewaysNoiseAmount = 0.0D;
        this.setOctaveWeights(5, 5, 0, 0, 1, 1);
        
        this.setColor(0x66A06E);
        this.setTemperatureRainfall(0.5F, 0.9F);
        
        this.addWeight(BiomeType.WARM, 7);
        
        this.spawnableCreatureList.clear(); // none of your regular farmyard critters here
        this.spawnableWaterCreatureList.clear();
        this.spawnableMonsterList.add(new SpawnListEntry(EntitySlime.class, 10, 1, 3));
        
        // lakes (it might seem obvious that lakes should be done at GeneratorStage.WATER_LAKES, but that is actually executed very late, after all plants and flowers
        // TODO: find a way to make the lakes shallower
        // TODO: put splotches of mud on the bottom? Either using a new GeneratorSplotches or perhaps by adding a lake parameter
        // this.addGenerator("lakes", GeneratorStage.SAND, (new GeneratorLakes.Builder()).amountPerChunk(1.5F).waterLakeForBiome(this).create());
        
        // mud
        this.addGenerator("mud", GeneratorStage.SAND_PASS2, (new GeneratorWaterside.Builder()).amountPerChunk(8).maxRadius(7).with(BOPBlocks.mud.getDefaultState().withProperty(BlockMud.VARIANT, BlockMud.MudType.MUD)).create());
        
        // other plants
        this.addGenerator("koru", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.1F).with(BOPPlants.KORU).create());
        
        // water plants
        this.addGenerator("duckweed", GeneratorStage.LILYPAD, (new GeneratorFlora.Builder()).amountPerChunk(0.5F).with(BlockBOPLilypad.LilypadType.DUCKWEED).generationAttempts(32).create());
        this.addGenerator("algae", GeneratorStage.LILYPAD, (new GeneratorFlora.Builder()).amountPerChunk(10.0F).replace(Blocks.water).with(BOPBlocks.coral.getDefaultState().withProperty(BlockCoral.VARIANT, BlockCoral.CoralType.ALGAE)).generationAttempts(32).create());
        this.addGenerator("water_reeds", GeneratorStage.LILYPAD, (new GeneratorFlora.Builder()).amountPerChunk(5.0F).with(BOPPlants.REED).generationAttempts(32).create());

        // grasses
        GeneratorWeighted grassGenerator = new GeneratorWeighted(20.0F);
        this.addGenerator("grass", GeneratorStage.GRASS, grassGenerator);
        grassGenerator.add("wheatgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.WHEATGRASS).create());
        grassGenerator.add("dampgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.DAMPGRASS).create());
        grassGenerator.add("tallgrass", 1, (new GeneratorGrass.Builder()).with(BlockTallGrass.EnumType.GRASS).create());
        grassGenerator.add("doublegrass", 10, (new GeneratorDoubleFlora.Builder()).with(BlockDoublePlant.EnumPlantType.GRASS).create());
        
        // gem
        this.addGenerator("malachite", GeneratorStage.SAND, (new GeneratorOreSingle.Builder()).amountPerChunk(12).with(BOPGems.MALACHITE).create());
        
   
    }
    
}