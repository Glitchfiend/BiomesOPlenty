/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.entities;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


@SideOnly(Side.CLIENT)
public class RenderSnail extends RenderLiving<EntitySnail>
{
    private static final ResourceLocation snailTextureLocation = new ResourceLocation("biomesoplenty:textures/entity/snail.png");

    public RenderSnail(RenderManager renderManager)
    {
        super(renderManager, new ModelSnail(), 0.25F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntitySnail entity)
    {
        return snailTextureLocation;
    }
}
