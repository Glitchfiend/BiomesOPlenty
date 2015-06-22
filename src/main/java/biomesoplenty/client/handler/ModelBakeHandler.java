/*******************************************************************************
 * Copyright 2014-2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.client.handler;

import java.util.ArrayList;
import java.util.List;

import biomesoplenty.client.model.ModelBiomeFinder;
import biomesoplenty.client.texture.TextureAnimationFrame;
import biomesoplenty.client.util.TextureUtils;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.util.IRegistry;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ModelBakeHandler
{
    public static final ModelResourceLocation BIOME_FINDER = new ModelResourceLocation("biomesoplenty:biome_finder", "inventory");
    
    public static List<String> fluidsToTextureStitch = new ArrayList<String>();
    
    private TextureAnimationFrame[] biomeFinderFrames;
    
    @SubscribeEvent
    public void onTextureStitch(TextureStitchEvent.Pre event)
    {
        TextureMap map = event.map;
        biomeFinderFrames = TextureUtils.splitAnimatedTexture(map, "biomesoplenty:items/biome_finder", 12);
    }
    
    @SubscribeEvent
    public void onTextureStitch(TextureStitchEvent.Post event)
    {
        TextureMap map = event.map;
    }
    
    @SubscribeEvent
    public void onModelBake(ModelBakeEvent event)
    {
        IRegistry modelRegistry = event.modelRegistry;
        IBakedModel biomeFinderModel = (IBakedModel)modelRegistry.getObject(BIOME_FINDER);
        
        modelRegistry.putObject(BIOME_FINDER, new ModelBiomeFinder(biomeFinderModel, biomeFinderFrames));
    }
}
