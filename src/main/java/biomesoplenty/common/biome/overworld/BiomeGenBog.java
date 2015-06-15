/*******************************************************************************
 * Copyright 2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.biome.overworld;

import net.minecraft.block.BlockPlanks;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.util.BlockPos;
import net.minecraftforge.common.BiomeManager.BiomeType;
import biomesoplenty.api.biome.BOPBiome;
import biomesoplenty.api.biome.generation.GeneratorStage;
import biomesoplenty.api.biome.generation.GeneratorWeighted;
import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.world.feature.GeneratorWaterside;
import biomesoplenty.common.world.feature.tree.GeneratorCypressTree;

public class BiomeGenBog extends BOPBiome
{    
    
    public BiomeGenBog()
    {
        // terrain
        this.bopMinHeight = 58;
        this.bopMaxHeight = 80;
        
        this.setColor(0xD8935F);
        this.setTemperatureRainfall(0.5F, 0.9F);
        this.seaFloorBlock = BOPBlocks.mud.getDefaultState();

        this.addWeight(BiomeType.COOL, 7);
        
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableMonsterList.add(new SpawnListEntry(EntitySlime.class, 10, 1, 3));
        
        // mud
        this.addGenerator("mud", GeneratorStage.SAND_PASS2, (new GeneratorWaterside.Builder()).amountPerChunk(4).maxRadius(7).with(BOPBlocks.mud.getDefaultState()).create());
        
        // trees & logs
        GeneratorWeighted treeGenerator = new GeneratorWeighted(8);
        this.addGenerator("trees", GeneratorStage.TREE, treeGenerator);
        treeGenerator.add("dark_oak", 2, (new GeneratorCypressTree.Builder()).log(BlockPlanks.EnumType.DARK_OAK).leaves(BlockPlanks.EnumType.DARK_OAK).minHeight(5).maxHeight(10).create());
        treeGenerator.add("birch", 1, (new GeneratorCypressTree.Builder()).log(BlockPlanks.EnumType.BIRCH).leaves(BlockPlanks.EnumType.BIRCH).minHeight(5).maxHeight(10).create());

        
    }
    
    @Override
    public int getGrassColorAtPos(BlockPos pos)
    {
        return 0xD8935F;
    }
    
    @Override
    public int getFoliageColorAtPos(BlockPos pos)
    {
        return 0xDAE579;
    }
    
   
}
