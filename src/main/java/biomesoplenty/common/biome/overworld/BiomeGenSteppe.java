/*******************************************************************************
 * Copyright 2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.biome.overworld;

import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraftforge.common.BiomeManager.BiomeType;
import biomesoplenty.api.biome.BOPBiome;
import biomesoplenty.api.biome.generation.GeneratorStage;
import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.block.BOPPlantEnums.AllPlants;
import biomesoplenty.common.block.BlockBOPPlant;
import biomesoplenty.common.block.BlockGem;
import biomesoplenty.common.block.BlockGem.GemType;
import biomesoplenty.common.world.feature.GeneratorGrass;
import biomesoplenty.common.world.feature.GeneratorOreSingle;

public class BiomeGenSteppe extends BOPBiome
{
    private static final Height biomeHeight = new Height(0.1F, 0.4F);

    public BiomeGenSteppe()
    {
        this.setHeight(biomeHeight);
        this.setColor(13413215);
        this.setTemperatureRainfall(0.7F, 0.05F);
        
        this.addWeight(BiomeType.DESERT, 5);
        
        this.spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 5, 2, 6));
        
        this.addGenerator("dead_bushes", GeneratorStage.DEAD_BUSH, new GeneratorGrass(3, Blocks.deadbush.getDefaultState(), 4));
        this.addGenerator("grass", GeneratorStage.GRASS, new GeneratorGrass(15, BlockBOPPlant.paging.getVariantState(AllPlants.SHORTGRASS)));
        this.addGenerator("ruby", GeneratorStage.SAND, new GeneratorOreSingle(BOPBlocks.gem_ore.getDefaultState().withProperty(BlockGem.VARIANT, GemType.RUBY), 12, 4, 32));
    }
    
    @Override
    public int getGrassColorAtPos(BlockPos pos)
    {
        double noise = field_180281_af.func_151601_a((double)pos.getX() * 0.0225D, (double)pos.getZ() * 0.0225D);
        return noise < -0.1D ? 13214328 : 12885629;
    }

    @Override
    public int getFoliageColorAtPos(BlockPos pos)
    {
        double noise = field_180281_af.func_151601_a((double)pos.getX() * 0.0225D, (double)pos.getZ() * 0.0225D);
        return noise < -0.1D ? 13214328 : 12885629;
    }
}
