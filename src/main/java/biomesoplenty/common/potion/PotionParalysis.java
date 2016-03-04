/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.potion;

import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PotionParalysis extends Potion
{
    public static ResourceLocation bopPotionEffectTexture = new ResourceLocation("biomesoplenty:textures/potions/status-icons.png");
    
    public PotionParalysis(boolean badEffect, int color)
    {
        super(badEffect, color);
        // status icon in position 2 row 0
        this.setIconIndex(1, 0);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public int getStatusIconIndex()
    {
        Minecraft.getMinecraft().getTextureManager().bindTexture(bopPotionEffectTexture);
        return super.getStatusIconIndex();
    }

    @Override
    public boolean isReady(int duration, int amplifier)
    {
        return duration >= 1;
    }

}