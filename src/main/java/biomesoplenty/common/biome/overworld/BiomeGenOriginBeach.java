/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.biome.overworld;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.config.IBOPWorldSettings;
import biomesoplenty.api.config.IBOPWorldSettings.GeneratorType;
import biomesoplenty.common.world.generator.GeneratorWeighted;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
 
public class BiomeGenOriginBeach extends BOPOverworldBiome
{
    public BiomeGenOriginBeach()
    {
        super("origin_beach", new PropsBuilder("Origin Beach").withGuiColour(10341485).withTemperature(0.6F).withRainfall(0.6F));

        // terrain
        this.terrainSettings.avgHeight(64).heightVariation(1, 1);
    
        this.skyColor = 8441086;
        
        this.canSpawnInBiome = false;
        this.canGenerateVillages = false;
        this.hasBiomeEssence = false;
        this.canGenerateRivers = false;
        
        this.spawnableWaterCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntitySpider.class, 100, 4, 4));
        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityZombie.class, 100, 4, 4));
        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntitySkeleton.class, 100, 4, 4));
        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityCreeper.class, 100, 4, 4));
        
        this.decorator.treesPerChunk = -999;
        this.decorator.deadBushPerChunk = 0;
        this.decorator.reedsPerChunk = 0;
        this.decorator.cactiPerChunk = 0;
        
        clearWeights();
        
        this.topBlock = Blocks.SAND.getDefaultState();
        this.fillerBlock = Blocks.SAND.getDefaultState();
    }
    
    @Override
    public void applySettings(IBOPWorldSettings settings)
    {
        this.removeGenerator("roots");
        this.removeGenerator("stone_formations");
        this.removeGenerator("glowshrooms");
        this.removeGenerator("miners_delight");
    }
    
    @Override
    public int getGrassColorAtPos(BlockPos pos)
    {
        return 10682207;
    }

    @Override
    public int getFoliageColorAtPos(BlockPos pos)
    {
        return 3866368;
    }
}
