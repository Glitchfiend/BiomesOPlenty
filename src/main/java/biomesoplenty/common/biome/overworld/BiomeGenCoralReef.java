/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.biome.overworld;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.generation.GeneratorStage;
import biomesoplenty.common.block.BlockBOPCoral;
import biomesoplenty.common.util.biome.BiomeUtils;
import biomesoplenty.common.util.biome.GeneratorUtils.ScatterYMethod;
import biomesoplenty.common.world.generator.GeneratorFlora;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
 
public class BiomeGenCoralReef extends BOPOverworldBiome
{
    public BiomeGenCoralReef()
    {
        super("coral_reef", new PropsBuilder("Coral Reef").withGuiColour(18285));
        
        // terrain
        this.terrainSettings.avgHeight(45).heightVariation(5, 10); 
    
        this.canSpawnInBiome = false;
        this.canGenerateVillages = false;
        this.canGenerateRivers = false;
        
        this.hasBiomeEssence = false;
        
        this.beachBiomeLocation = BiomeUtils.getLocForBiome(Biomes.OCEAN);
        
        clearWeights();

        // coral
        this.addGenerator("pink_coral", GeneratorStage.LILYPAD, (new GeneratorFlora.Builder()).amountPerChunk(15.0F).replace(Blocks.WATER).with(BOPBlocks.coral.getDefaultState().withProperty(BlockBOPCoral.VARIANT, BlockBOPCoral.CoralType.PINK)).scatterYMethod(ScatterYMethod.AT_GROUND).create());
        this.addGenerator("orange_coral", GeneratorStage.LILYPAD, (new GeneratorFlora.Builder()).amountPerChunk(15.0F).replace(Blocks.WATER).with(BOPBlocks.coral.getDefaultState().withProperty(BlockBOPCoral.VARIANT, BlockBOPCoral.CoralType.ORANGE)).scatterYMethod(ScatterYMethod.AT_GROUND).create());
        this.addGenerator("blue_coral", GeneratorStage.LILYPAD, (new GeneratorFlora.Builder()).amountPerChunk(15.0F).replace(Blocks.WATER).with(BOPBlocks.coral.getDefaultState().withProperty(BlockBOPCoral.VARIANT, BlockBOPCoral.CoralType.BLUE)).scatterYMethod(ScatterYMethod.AT_GROUND).create());
        this.addGenerator("glowing_coral", GeneratorStage.LILYPAD, (new GeneratorFlora.Builder()).amountPerChunk(15.0F).replace(Blocks.WATER).with(BOPBlocks.coral.getDefaultState().withProperty(BlockBOPCoral.VARIANT, BlockBOPCoral.CoralType.GLOWING)).scatterYMethod(ScatterYMethod.AT_GROUND).create());
        this.addGenerator("algae", GeneratorStage.LILYPAD, (new GeneratorFlora.Builder()).amountPerChunk(3.0F).replace(Blocks.WATER).with(BOPBlocks.coral.getDefaultState().withProperty(BlockBOPCoral.VARIANT, BlockBOPCoral.CoralType.ALGAE)).scatterYMethod(ScatterYMethod.AT_GROUND).create());
    }
}
