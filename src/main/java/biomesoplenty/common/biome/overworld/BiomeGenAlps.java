/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.biome.overworld;

import biomesoplenty.api.enums.BOPClimates;
import biomesoplenty.api.generation.GeneratorStage;
import biomesoplenty.common.world.generator.GeneratorOreSingle;
import net.minecraft.init.Blocks;

public class BiomeGenAlps extends BOPOverworldBiome
{
    public BiomeGenAlps()
    {
        super("alps", new PropsBuilder("Alps").withGuiColour(13421772).withSnowEnabled().withTemperature(-0.25F).withRainfall(0.3F));
        
        this.terrainSettings.avgHeight(198).heightVariation(12, 12).octaves(1, 1, 2, 2, 3, 3);
        this.topBlock = Blocks.SNOW.getDefaultState();
        this.fillerBlock = Blocks.SNOW.getDefaultState();

        this.canGenerateRivers = false;
        this.canSpawnInBiome = false;
        this.canGenerateVillages = false;
        
        this.beachBiomeLocation = null;
        
        this.addWeight(BOPClimates.TUNDRA, 3);
        this.addWeight(BOPClimates.COLD_DESERT, 5);
        
        this.avgDirtDepth = 8;

        this.spawnableCreatureList.clear();
        
        // gem
        this.addGenerator("emerald", GeneratorStage.SAND, (new GeneratorOreSingle.Builder()).amountPerChunk(12).with(Blocks.EMERALD_ORE.getDefaultState()).create());
    }
}
