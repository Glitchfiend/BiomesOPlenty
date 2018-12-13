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
import biomesoplenty.api.generation.GeneratorStage;
import biomesoplenty.common.block.BlockBOPCoral;
import biomesoplenty.common.util.biome.BiomeUtils;
import biomesoplenty.common.util.biome.GeneratorUtils.ScatterYMethod;
import biomesoplenty.common.world.generator.GeneratorColumns;
import biomesoplenty.common.world.generator.GeneratorFlora;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
 
public class BiomeGenKelpForest extends BOPOverworldBiome
{
    public BiomeGenKelpForest()
    {
        super("kelp_forest", new PropsBuilder("Kelp Forest").withGuiColour(27468));

        // terrain
        this.terrainSettings.avgHeight(40).heightVariation(5, 5);
    
        this.canSpawnInBiome = false;
        this.canGenerateVillages = false;
        this.canGenerateRivers = false;
        this.hasBiomeEssence = false;
        
        this.beachBiomeLocation = BiomeUtils.getLocForBiome(Biomes.OCEAN);
        
        clearWeights();

        // algae
        this.addGenerator("algae", GeneratorStage.LILYPAD, (new GeneratorFlora.Builder()).amountPerChunk(3.0F).replace(Blocks.WATER).with(BOPBlocks.coral.getDefaultState().withProperty(BlockBOPCoral.VARIANT, BlockBOPCoral.CoralType.ALGAE)).scatterYMethod(ScatterYMethod.AT_GROUND).create());
         
        // kelp
        this.addGenerator("kelp", GeneratorStage.LILYPAD, (new GeneratorColumns.Builder()).amountPerChunk(5.0F).replace(BlockQueries.waterCovered).placeOn(BlockQueries.groundBlocks).with(BOPBlocks.seaweed.getDefaultState()).scatterYMethod(ScatterYMethod.AT_GROUND).create());
        this.addGenerator("kelp_tall", GeneratorStage.LILYPAD, (new GeneratorColumns.Builder()).amountPerChunk(6.0F).replace(BlockQueries.waterCovered).placeOn(BlockQueries.groundBlocks).with(BOPBlocks.seaweed.getDefaultState()).minHeight(6).maxHeight(12).scatterYMethod(ScatterYMethod.AT_GROUND).create());
    }
}
