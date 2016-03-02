/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.client.util;

import com.google.common.base.Function;

import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModel;

public class ModelUtils
{
    public static IBakedModel[] generateModelsForTextures(IModel model, TextureAtlasSprite[] textures) 
    {
        IBakedModel[] output = new IBakedModel[textures.length];
        
        for (int i = 0; i < output.length; i++)
        {
            final TextureAtlasSprite texture = textures[i];

            //Defines how TextureAtlasSprites are obtained whilst baking
            Function<ResourceLocation, TextureAtlasSprite> textureGetter = new Function<ResourceLocation, TextureAtlasSprite>()
            {
                public TextureAtlasSprite apply(ResourceLocation location)
                {
                    return texture;
                }
            };
            
            output[i] = model.bake(model.getDefaultState(), DefaultVertexFormats.ITEM, textureGetter);
        }
        
        return output;
    }
}
