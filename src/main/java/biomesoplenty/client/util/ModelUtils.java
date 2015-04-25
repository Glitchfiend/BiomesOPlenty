/*******************************************************************************
 * Copyright 2014-2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.client.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.client.resources.model.SimpleBakedModel;
import net.minecraft.util.EnumFacing;
import biomesoplenty.client.texture.TextureAnimationFrame;

import com.google.common.collect.Lists;

public class ModelUtils
{
    public static IBakedModel[] generateModelsForTextures(IBakedModel model, TextureAtlasSprite[] textures) 
    {
        IBakedModel[] output = new IBakedModel[textures.length];
        
        for (int i = 0; i < output.length; i++)
        {
            TextureAtlasSprite texture = textures[i];
            SimpleBakedModel.Builder builder = new SimpleBakedModel.Builder(model, texture);
            
            builder.setTexture(texture);
            output[i] = builder.makeBakedModel();
        }
        
        return output;
    }
    
    public static List createFaceLists() 
    {
        List list = new ArrayList(EnumFacing.values().length);
        
        for (int i = 0; i < EnumFacing.values().length; i++) 
        {
            list.add(i, Lists.newLinkedList());
        }

        return list;
    }
}
