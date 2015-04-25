/*******************************************************************************
 * Copyright 2014-2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.client.util;

import biomesoplenty.client.texture.TextureAnimationFrame;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TextureUtils
{
    public static final String TEXTURES_BASE_PATH = "textures";
    
    //TODO: Remove frameCount argument
    @SideOnly(Side.CLIENT)
    public static TextureAnimationFrame[] splitAnimatedTexture(TextureMap textureMap, String iconName, int frameCount)
    {
        TextureAnimationFrame[] output = new TextureAnimationFrame[frameCount];
        
        for (int i = 0; i < frameCount; i++)
        {
            String textureLocation = iconName + "_static_" + i;
            TextureAnimationFrame frameTexture = new TextureAnimationFrame(textureLocation, iconName, i);
            
            output[i] = frameTexture;
            textureMap.setTextureEntry(textureLocation, frameTexture);

        }
        
        return output;
    }
    
    public static ResourceLocation completeResourceLocation(ResourceLocation location, int mode)
    {
        return mode == 0 ? new ResourceLocation(location.getResourceDomain(), String.format("%s/%s%s", new Object[] {TEXTURES_BASE_PATH, location.getResourcePath(), ".png"})) : new ResourceLocation(location.getResourceDomain(), String.format("%s/mipmaps/%s.%d%s", new Object[] {TEXTURES_BASE_PATH, location.getResourcePath(), Integer.valueOf(mode), ".png"}));
    }
}
