/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.client.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import biomesoplenty.client.model.ModelBiomeFinder;
import biomesoplenty.client.model.ModelFlowerBasket;
import biomesoplenty.client.texture.TextureAnimationFrame;
import biomesoplenty.client.util.TextureUtils;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.util.IRegistry;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ModelBakeHandler
{
    public static final ModelResourceLocation BIOME_FINDER_LOC = new ModelResourceLocation("biomesoplenty:biome_finder", "inventory");
    
    public static final ModelResourceLocation FLOWER_BASKET_LOC = new ModelResourceLocation("biomesoplenty:flower_basket", "inventory");
    public static final ModelResourceLocation FLOWER_BASKET_EMPTY_LOC = new ModelResourceLocation("biomesoplenty:flower_basket_empty", "inventory");
    public static final ModelResourceLocation FLOWER_BASKET_FULL_LOC = new ModelResourceLocation("biomesoplenty:flower_basket_full", "inventory");
    
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
    public void onModelBake(ModelBakeEvent event) throws IOException
    {
    	IRegistry<ModelResourceLocation, IBakedModel> modelRegistry = event.modelRegistry;
    	ModelLoader modelLoader = event.modelLoader;
    	
    	//NOTE: If there are issues with this in the future, it may be useful to investigate ItemLayerModel
    	
    	//Get the existing model defined by the json file
    	IModel biomeFinderModel = modelLoader.getModel(BIOME_FINDER_LOC);
    	//Replace the existing model with our new flexible one
        modelRegistry.putObject(BIOME_FINDER_LOC, new ModelBiomeFinder(biomeFinderModel, biomeFinderFrames));
        modelRegistry.putObject(FLOWER_BASKET_LOC, new ModelFlowerBasket(modelRegistry.getObject(FLOWER_BASKET_EMPTY_LOC), modelRegistry.getObject(FLOWER_BASKET_FULL_LOC)));
    }
}
