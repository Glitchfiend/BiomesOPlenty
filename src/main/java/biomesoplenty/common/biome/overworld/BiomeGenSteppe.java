/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.biome.overworld;

import java.util.Random;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.enums.BOPClimates;
import biomesoplenty.api.enums.BOPPlants;
import biomesoplenty.api.generation.GeneratorStage;
import biomesoplenty.common.block.BlockBOPDirt;
import biomesoplenty.common.block.BlockBOPGrass;
import biomesoplenty.common.world.generator.GeneratorFlora;
import biomesoplenty.common.world.generator.GeneratorGrass;
import biomesoplenty.common.world.generator.GeneratorOreSingle;
import biomesoplenty.common.world.generator.GeneratorWaterside;
import biomesoplenty.common.world.generator.GeneratorWeighted;
import net.minecraft.entity.passive.EntityDonkey;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityLlama;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenFossils;

public class BiomeGenSteppe extends BOPOverworldBiome
{
    public BiomeGenSteppe()
    {
        super("steppe", new PropsBuilder("Steppe").withGuiColour(13413215).withTemperature(0.75F).withRainfall(0.1F));

        // terrain
        this.topBlock = BOPBlocks.grass.getDefaultState().withProperty(BlockBOPGrass.VARIANT, BlockBOPGrass.BOPGrassType.SANDY);
        this.fillerBlock = BOPBlocks.dirt.getDefaultState().withProperty(BlockBOPDirt.VARIANT, BlockBOPDirt.BOPDirtType.SANDY);
        
        this.terrainSettings.avgHeight(90).heightVariation(8, 8);
        
        this.canSpawnInBiome = false;
        this.canGenerateVillages = true;
        
        this.addWeight(BOPClimates.DRY_TEMPERATE, 7);
        
        if (BOPBiomes.gravel_beach.isPresent())
        {
        	this.beachBiomeLocation = ((BOPOverworldBiome)BOPBiomes.gravel_beach.get()).getResourceLocation();
        }
        
        this.spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 5, 2, 6));
        this.spawnableCreatureList.add(new SpawnListEntry(EntityDonkey.class, 1, 1, 1));
        this.spawnableCreatureList.add(new SpawnListEntry(EntityLlama.class, 5, 4, 6));
        this.spawnableCreatureList.add(new SpawnListEntry(EntityRabbit.class, 4, 2, 3));

        // sand
        this.addGenerator("sand", GeneratorStage.SAND_PASS2, (new GeneratorWaterside.Builder()).amountPerChunk(3).maxRadius(7).with(Blocks.SAND.getDefaultState()).create());

        // other plants
        this.addGenerator("dead_bushes", GeneratorStage.DEAD_BUSH,(new GeneratorFlora.Builder()).amountPerChunk(0.3F).with(Blocks.DEADBUSH.getDefaultState()).generationAttempts(4).create());
        this.addGenerator("desertgrass", GeneratorStage.GRASS, (new GeneratorGrass.Builder()).amountPerChunk(12.0F).with(BOPPlants.DESERTGRASS).generationAttempts(8).create());
        
        // grasses
        GeneratorWeighted grassGenerator = new GeneratorWeighted(1.9F);
        this.addGenerator("grass", GeneratorStage.GRASS, grassGenerator);
        grassGenerator.add("shortgrass", 7, (new GeneratorGrass.Builder()).with(BOPPlants.SHORTGRASS).create());
        
        // gem
        this.addGenerator("emerald", GeneratorStage.SAND, (new GeneratorOreSingle.Builder()).amountPerChunk(12).with(Blocks.EMERALD_ORE.getDefaultState()).create());
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
    
    @Override
    public int getGrassColorAtPos(BlockPos pos)
    {
        double noise = GRASS_COLOR_NOISE.getValue((double)pos.getX() * 0.0225D, (double)pos.getZ() * 0.0225D);
        return getModdedBiomeGrassColor(noise < -0.1D ? 13741418 : 13018487);
    }

    @Override
    public int getFoliageColorAtPos(BlockPos pos)
    {
        double noise = GRASS_COLOR_NOISE.getValue((double)pos.getX() * 0.0225D, (double)pos.getZ() * 0.0225D);
        return getModdedBiomeFoliageColor(noise < -0.1D ? 13741418 : 13018487);
    }
}
