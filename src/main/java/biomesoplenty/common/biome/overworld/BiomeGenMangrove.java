/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.biome.overworld;

import java.util.Random;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.block.BlockQueries;
import biomesoplenty.api.enums.BOPClimates;
import biomesoplenty.api.enums.BOPPlants;
import biomesoplenty.api.generation.GeneratorStage;
import biomesoplenty.common.block.BlockBOPPlant;
import biomesoplenty.common.world.generator.GeneratorColumns;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenFossils;
 
public class BiomeGenMangrove extends BOPOverworldBiome
{
    public BiomeGenMangrove()
    {
        super("mangrove", new PropsBuilder("Mangrove").withTemperature(0.85F).withRainfall(0.8F).withGuiColour(7251289));

        // terrain
        this.terrainSettings.avgHeight(61).heightVariation(4, 2).octaves(5, 5, 0, 0, 1, 1);

        //this.addWeight(BOPClimates.SUBTROPICAL, 3);
        
        this.topBlock = BOPBlocks.mud.getDefaultState();
        this.fillerBlock = BOPBlocks.mud.getDefaultState();
        this.seaFloorBlock = BOPBlocks.mud.getDefaultState();
        
        this.canSpawnInBiome = false;
        this.canGenerateVillages = false;
        this.canGenerateRivers = false;
        
        this.spawnableCreatureList.clear();
        
        this.beachBiomeLocation = null;
        
        //this.addGenerator("mangrove_tree", GeneratorStage.FLOWERS,(new GeneratorColumns.Builder()).amountPerChunk(20.0F).generationAttempts(24).placeOn(BlockQueries.suitableForReed).with(BlockBOPPlant.paging.getVariantState(BOPPlants.MANGROVE_ROOT)).minHeight(1).maxHeight(2).create());
    }
    
    @Override
    public void genTerrainBlocks(World world, Random rand, ChunkPrimer primer, int x, int z, double noise)
    {
        double d0 = GRASS_COLOR_NOISE.getValue((double)x * 0.25D, (double)z * 0.25D);

        if (d0 > 0.0D)
        {
            int i = x & 15;
            int j = z & 15;

            for (int k = 255; k >= 0; --k)
            {
                if (primer.getBlockState(j, k, i).getMaterial() != Material.AIR)
                {
                    if (k == 62 && primer.getBlockState(j, k, i).getBlock() != Blocks.WATER)
                    {
                        primer.setBlockState(j, k, i, WATER);
                    }

                    break;
                }
            }
        }

        this.generateBiomeTerrain(world, rand, primer, x, z, noise);
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
}
