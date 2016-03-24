/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.handler.decoration;

import java.util.Random;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.api.biome.IExtendedBiome;
import biomesoplenty.api.biome.generation.GenerationManager;
import biomesoplenty.api.biome.generation.GeneratorStage;
import biomesoplenty.api.biome.generation.IGenerator;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class DecorateBiomeEventHandler
{
    @SubscribeEvent
    public void onPreBiomeDecorate(DecorateBiomeEvent.Pre event)
    {
        if (BOPBiomes.excludedDecoratedWorldTypes.contains(event.getWorld().getWorldType()))
            return;
        
        runGeneratorStage(event.getWorld(), event.getRand(), event.getPos(), GeneratorStage.PRE);
    }
    
    @SubscribeEvent
    public void onBiomeDecorate(DecorateBiomeEvent.Decorate event)
    {
        if (BOPBiomes.excludedDecoratedWorldTypes.contains(event.getWorld().getWorldType()))
            return;
        
        if (event.getType() != Decorate.EventType.CUSTOM)
        {
            boolean allow = runGeneratorStage(event.getWorld(), event.getRand(), event.getPos(), GeneratorStage.mapDecorateType(event.getType()));
            
            event.setResult(allow ? Result.ALLOW : Result.DENY);
        }
    }
    
    @SubscribeEvent
    public void onPostBiomeDecorate(DecorateBiomeEvent.Post event)
    {
        if (BOPBiomes.excludedDecoratedWorldTypes.contains(event.getWorld().getWorldType()))
            return;
        
        runGeneratorStage(event.getWorld(), event.getRand(), event.getPos(), GeneratorStage.POST);
    }
    
    @SubscribeEvent
    public void onPreGenerateOres(OreGenEvent.Pre event)
    {
        if (BOPBiomes.excludedDecoratedWorldTypes.contains(event.getWorld().getWorldType()))
            return;
        
        runGeneratorStage(event.getWorld(), event.getRand(), event.getPos(), GeneratorStage.ORE_PRE);
    }
    
    @SubscribeEvent
    public void onPostGenerateOres(OreGenEvent.Post event)
    { 
        if (BOPBiomes.excludedDecoratedWorldTypes.contains(event.getWorld().getWorldType()))
            return;
        
        runGeneratorStage(event.getWorld(), event.getRand(), event.getPos(), GeneratorStage.ORE_POST);
    }
    
    private static boolean runGeneratorStage(World world, Random random, BlockPos pos, GeneratorStage stage)
    {
        BiomeGenBase biome = world.getBiomeGenForCoords(pos.add(16, 0, 16));
        IExtendedBiome extendedBiome = BOPBiomes.REG_INSTANCE.getExtendedBiome(biome);
        
        if (extendedBiome != null)
        {
            GenerationManager generationManager = extendedBiome.getGenerationManager();
            
            for (IGenerator generator : generationManager.getGeneratorsForStage(stage))
            {
                generator.scatter(world, random, pos);
            }
            
            //Biomes should explicitly allow for the following by defining their own generators
            /*if (extendedBiome.getBiomeOwner() == BiomeOwner.BIOMESOPLENTY)
            {
                if (stage == GeneratorStage.PUMPKIN)
                {
                    return false;
                }
            }*/
        }

        return true;
    }
}
