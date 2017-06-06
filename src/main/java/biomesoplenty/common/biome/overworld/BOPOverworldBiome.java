/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.biome.overworld;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import biomesoplenty.api.biome.BiomeOwner;
import biomesoplenty.api.biome.IExtendedBiome;
import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.block.IBlockPosQuery;
import biomesoplenty.api.config.IBOPWorldSettings;
import biomesoplenty.api.config.IBOPWorldSettings.GeneratorType;
import biomesoplenty.api.config.IConfigObj;
import biomesoplenty.api.enums.BOPClimates;
import biomesoplenty.api.enums.BOPFlowers;
import biomesoplenty.api.enums.BOPPlants;
import biomesoplenty.api.generation.GeneratorStage;
import biomesoplenty.api.generation.IGenerator;
import biomesoplenty.common.biome.BOPBiome;
import biomesoplenty.common.block.BlockBOPMushroom;
import biomesoplenty.common.init.ModBiomes;
import biomesoplenty.common.util.biome.BiomeUtils;
import biomesoplenty.common.util.biome.GeneratorUtils.ScatterYMethod;
import biomesoplenty.common.util.block.BlockQuery;
import biomesoplenty.common.world.GenerationManager;
import biomesoplenty.common.world.TerrainSettings;
import biomesoplenty.common.world.generator.GeneratorColumns;
import biomesoplenty.common.world.generator.GeneratorFlora;
import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.block.BlockSand;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class BOPOverworldBiome extends BOPBiome
{
    public IBlockState seaFloorBlock = Blocks.DIRT.getDefaultState();
    
    public boolean canSpawnInBiome = true;
    public boolean canGenerateVillages = true;
    public boolean canGenerateRivers = true;
    
    public ResourceLocation beachBiomeLocation = BiomeUtils.getLocForBiome(Biomes.BEACH);
    
    public TerrainSettings terrainSettings = new TerrainSettings();
    public boolean noNeighborTerrainInfuence = false;
    public int avgDirtDepth = 3;

    public BOPOverworldBiome(String idName, PropsBuilder defaultBuilder)
    {
        super(idName, defaultBuilder);

        this.terrainSettings.setDefaults();

        this.theBiomeDecorator.treesPerChunk = -999;
        this.theBiomeDecorator.flowersPerChunk = -999;
        this.theBiomeDecorator.grassPerChunk = -999;
        this.theBiomeDecorator.sandPerChunk = -999;
        this.theBiomeDecorator.sandPerChunk2 = -999;
        //this.theBiomeDecorator.generateLakes = false;

        // roots
        this.addGenerator("roots", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(4.0F).with(BOPPlants.ROOT).create());

        IBlockPosQuery suitableStonePosition = BlockQuery.buildAnd().withAltitudeBetween(0, 55).blocks(Blocks.STONE).create();
        this.addGenerator("miners_delight", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.25F).generationAttempts(64).with(BOPFlowers.MINERS_DELIGHT).placeOn(suitableStonePosition).scatterYMethod(ScatterYMethod.BELOW_GROUND).create());
        this.addGenerator("glowshrooms", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(1.5F).generationAttempts(64).placeOn(suitableStonePosition).with(BOPBlocks.mushroom.getDefaultState().withProperty(BlockBOPMushroom.VARIANT, BlockBOPMushroom.MushroomType.GLOWSHROOM)).scatterYMethod(ScatterYMethod.BELOW_GROUND).create());
        this.addGenerator("stone_formations", GeneratorStage.FLOWERS,(new GeneratorColumns.Builder()).amountPerChunk(30.0F).generationAttempts(32).placeOn(suitableStonePosition).with(BOPBlocks.stone_formations.getDefaultState()).minHeight(1).maxHeight(5).randomDirection(true).scatterYMethod(ScatterYMethod.BELOW_GROUND).create());
    }
    
    @Override
    public void applySettings(IBOPWorldSettings settings)
    {
        if (!settings.isEnabled(GeneratorType.MUSHROOMS)) {this.removeGenerator("glowshrooms");}
        if (!settings.isEnabled(GeneratorType.PLANTS)) {this.removeGenerator("roots");}
        if (!settings.isEnabled(GeneratorType.FLOWERS)) {this.removeGenerator("miners_delight");}
        if (!settings.isEnabled(GeneratorType.ROCK_FORMATIONS)) {this.removeGenerator("stone_formations");}
    }
    
    @Override
    public void configure(IConfigObj conf)
    {
        super.configure(conf);
        // Allow basic properties to be overridden

        this.seaFloorBlock = conf.getBlockState("seaFloorBlock", this.seaFloorBlock);

        this.terrainSettings.avgHeight = conf.getFloat("averageHeight", (float)this.terrainSettings.avgHeight);
        this.terrainSettings.variationBelow = conf.getFloat("variationBelow", (float)this.terrainSettings.variationBelow);
        this.terrainSettings.variationAbove = conf.getFloat("variationAbove", (float)this.terrainSettings.variationAbove);
        
        this.canSpawnInBiome = conf.getBool("canSpawnInBiome", this.canSpawnInBiome);
        this.canGenerateVillages = conf.getBool("canGenerateVillages", this.canGenerateVillages);
        this.canGenerateRivers = conf.getBool("canGenerateRivers", this.canGenerateRivers);
        
        this.beachBiomeLocation = conf.getResourceLocation("beachBiomeLocation", this.beachBiomeLocation);
    }
    
    @Override
    public void genTerrainBlocks(World world, Random rand, ChunkPrimer primer, int x, int z, double stoneNoiseVal)
    {

        IBlockState topBlock = this.topBlock;
        IBlockState fillerBlock = this.fillerBlock;
        IBlockState seaFloorBlock = this.seaFloorBlock;

        boolean hitFloorYet = false;
        int topBlocksToFill = 0;
        int dirtBlocksToFill = 0;
        int seaFloorBlocksToFill = 0;
        int dirtDepth = Math.max(0, (int)(stoneNoiseVal / 3.0D + this.avgDirtDepth + rand.nextDouble() * 0.25D));
        int seaFloorDepth = 1 + rand.nextInt(2);
        
        int localX = x & 15;
        int localZ = z & 15;

        // start at the top and move downwards
        for (int y = 255; y >= 0; --y)
        {
            
            IBlockState state = primer.getBlockState(localZ, y, localX);
            
            // bedrock at the bottom
            if (y <= rand.nextInt(5))
            {
                primer.setBlockState(localZ, y, localX, Blocks.BEDROCK.getDefaultState());
                continue;
            }

            if (state.getMaterial() == Material.AIR)
            {
                // topBlocks and dirtBlocks can occur after any pocket of air
                topBlocksToFill = (topBlock == null ? 0 : 1);
                dirtBlocksToFill = dirtDepth;
                continue;
            }
            else if (!hitFloorYet && state.getMaterial() == Material.WATER)
            {
                // seaFloorBlocks can occur after surface water
                seaFloorBlocksToFill = seaFloorDepth;
            }
            
            if (state.getBlock() == Blocks.STONE)
            {
                hitFloorYet = true;
                if (topBlocksToFill > 0)
                {
                    if (y >= 62)
                    {
                        primer.setBlockState(localZ, y, localX, topBlock);
                    }
                    else if (y >= 56 - dirtDepth)
                    {
                        primer.setBlockState(localZ, y, localX, fillerBlock);
                    }
                    else
                    {
                        primer.setBlockState(localZ, y, localX, Blocks.GRAVEL.getDefaultState());
                        dirtBlocksToFill = 0;
                    }
                    topBlocksToFill--;
                }
                else if (seaFloorBlocksToFill > 0)
                {
                    primer.setBlockState(localZ, y, localX, seaFloorBlock);
                    --seaFloorBlocksToFill;
                }
                else if (dirtBlocksToFill > 0)
                {
                    primer.setBlockState(localZ, y, localX, fillerBlock);
                    --dirtBlocksToFill;

                    // add sandstone after a patch of sand
                    if (dirtBlocksToFill == 0 && fillerBlock.getBlock() == Blocks.SAND)
                    {
                        dirtBlocksToFill = rand.nextInt(4) + Math.max(0, y - 63);
                        fillerBlock = fillerBlock.getValue(BlockSand.VARIANT) == BlockSand.EnumType.RED_SAND ? Blocks.RED_SANDSTONE.getDefaultState() : Blocks.SANDSTONE.getDefaultState();
                    }
                    if (dirtBlocksToFill == 0 && fillerBlock.getBlock() == BOPBlocks.white_sand)
                    {
                        dirtBlocksToFill = rand.nextInt(4) + Math.max(0, y - 63);
                        fillerBlock = BOPBlocks.white_sandstone.getDefaultState();
                    }
                }
            }
        }
    }
    
    @Override
    public ResourceLocation getBeachLocation()
    {
        return this.beachBiomeLocation;
    }

    // the below two methods convert between our values and Vanilla.
    // they're mostly used for mods like Pioneer rather than us.

    @Override
    public float getBaseHeight()
    {
        return ((float)this.terrainSettings.avgHeight - 65.0F) / 17.0F;
    }

    @Override
    public float getHeightVariation()
    {
        // average the heightVariation values for above and below
        return (((float)this.terrainSettings.variationAbove - 7.0F) / (20.0F * 4.0F) + ((float)this.terrainSettings.variationBelow - 4.0F) / 20.0F) / 2.0F;
    }
}
