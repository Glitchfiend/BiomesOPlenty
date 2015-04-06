/*******************************************************************************
 * Copyright 2014-2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.handler.decoration;

import java.util.Random;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import biomesoplenty.api.biome.IExtendedBiome;
import biomesoplenty.api.biome.generation.GenerationManager;
import biomesoplenty.api.biome.generation.GeneratorStage;
import biomesoplenty.api.biome.generation.IGenerator;
import biomesoplenty.common.biome.ExtendedBiomeRegistry;

public class DecorateBiomeEventHandler
{
    @SubscribeEvent
    public void onPreBiomeDecorate(DecorateBiomeEvent.Pre event)
    {
        runGeneratorStage(event.world, event.rand, event.pos, GeneratorStage.PRE);
    }
    
    @SubscribeEvent
    public void onBiomeDecorate(DecorateBiomeEvent.Decorate event)
    {
        if (event.type != Decorate.EventType.CUSTOM)
        {
            runGeneratorStage(event.world, event.rand, event.pos, GeneratorStage.mapDecorateType(event.type));
        }
    }
    
    @SubscribeEvent
    public void onPostBiomeDecorate(DecorateBiomeEvent.Post event)
    {
        runGeneratorStage(event.world, event.rand, event.pos, GeneratorStage.POST);
    }
    
    private static void runGeneratorStage(World world, Random random, BlockPos pos, GeneratorStage stage)
    {
        BiomeGenBase biome = world.getBiomeGenForCoords(pos.add(16, 0, 16));
        IExtendedBiome extendedBiome = ExtendedBiomeRegistry.getExtension(biome);
        
        if (extendedBiome != null)
        {
            GenerationManager generationManager = extendedBiome.getGenerationManager();
            
            for (IGenerator generator : generationManager.getGeneratorsForStage(stage))
            {
                generator.scatter(world, random, pos);
            }
        }
    }
}
