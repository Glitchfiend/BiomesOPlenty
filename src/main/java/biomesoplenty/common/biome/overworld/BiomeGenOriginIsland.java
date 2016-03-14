/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.biome.overworld;

import biomesoplenty.api.biome.BOPBiome;
import biomesoplenty.api.biome.generation.GeneratorStage;
import biomesoplenty.api.biome.generation.GeneratorWeighted;
import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.block.BlockBOPGrass;
import biomesoplenty.common.block.BlockBOPGrass.BOPGrassType;
import biomesoplenty.common.enums.BOPFlowers;
import biomesoplenty.common.enums.BOPTrees;
import biomesoplenty.common.world.BOPWorldSettings;
import biomesoplenty.common.world.feature.GeneratorFlora;
import biomesoplenty.common.world.feature.tree.GeneratorBasicTree;
import net.minecraft.block.BlockFlower.EnumFlowerType;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenOriginIsland extends BOPBiome
{
    public BiomeGenOriginIsland()
    {
        super("origin_island", new PropsBuilder("Origin Island").withGuiColour(10341485).withTemperature(0.6F).withRainfall(0.6F));

        // terrain
        this.terrainSettings.avgHeight(64).heightVariation(6, 25).sidewaysNoise(0.0D);

        this.skyColor = 8441086;
        
        this.canSpawnInBiome = false;
        this.canGenerateVillages = false;
        this.canGenerateRivers = false;
        
        this.theBiomeDecorator.grassPerChunk = -999;
        this.theBiomeDecorator.treesPerChunk = -999;
        
        clearWeights();
        
        this.spawnableWaterCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntitySheep.class, 12, 4, 4));
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityPig.class, 10, 4, 4));
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityChicken.class, 10, 4, 4));
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityCow.class, 8, 4, 4));
        this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntitySpider.class, 100, 4, 4));
        this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityZombie.class, 100, 4, 4));
        this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntitySkeleton.class, 100, 4, 4));
        this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityCreeper.class, 100, 4, 4));
        
        this.topBlock = BOPBlocks.grass.getDefaultState().withProperty(BlockBOPGrass.VARIANT, BOPGrassType.ORIGIN);

        // trees
        this.addGenerator("trees", GeneratorStage.TREE, (new GeneratorBasicTree.Builder()).amountPerChunk(5).minHeight(5).maxHeight(8).leaves(BOPTrees.ORIGIN).create());

        // flowers
        GeneratorWeighted flowerGenerator = new GeneratorWeighted(0.4F);
        this.addGenerator("flowers", GeneratorStage.GRASS, flowerGenerator);
        flowerGenerator.add("rose", 8, (new GeneratorFlora.Builder().with(BOPFlowers.ROSE).create()));
        flowerGenerator.add("yellow_flower", 10, (new GeneratorFlora.Builder().with(EnumFlowerType.DANDELION).create()));
    }
    
    
    @Override
    public void applySettings(BOPWorldSettings settings)
    {
        this.removeGenerator("roots");
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
