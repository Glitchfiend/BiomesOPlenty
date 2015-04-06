/*******************************************************************************
 * Copyright 2014-2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.handler.decoration;

import java.util.Map.Entry;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import biomesoplenty.api.biome.IExtendedBiome;
import biomesoplenty.api.biome.generation.IGenerator;
import biomesoplenty.common.biome.ExtendedBiomeRegistry;

public class DecorateBiomeEventHandler
{
    /*TODO: @SubscribeEvent
    public void onBiomeDecorate(DecorateBiomeEvent.Decorate event)
    {
        World world = event.world;
        BlockPos pos = event.pos.add(16, 0, 16);
        BiomeGenBase biome = world.getBiomeGenForCoords(pos);
        IExtendedBiome extendedBiome = ExtendedBiomeRegistry.getExtension(biome);

        if (extendedBiome != null)
        {
            GenerationManager generationManager = extendedBiome.getGenerationManager();

            for (Entry<String, IGenerator<?>> entry : generationManager.getGeneratorMap().entrySet())
            {
                String key = entry.getKey();
                IGenerator<?> generator = entry.getValue();

                if (generationManager.getGeneratorStage(key) == event.type)
                {
                    generator.generate(world, event.rand, event.pos);
                }
            }
        }
    }*/
}
