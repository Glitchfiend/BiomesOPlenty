/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.entities;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


@SideOnly(Side.CLIENT)
public class RenderWasp extends RenderLiving<EntityWasp>
{
    private static final ResourceLocation waspTextureLocation = new ResourceLocation("biomesoplenty:textures/entity/wasp.png");

    public RenderWasp(RenderManager renderManager)
    {
        super(renderManager, new ModelWasp(), 0.5F);
    }

    @Override
    protected void preRenderCallback(EntityWasp entity, float partialTickTime)
    {
        GL11.glRotatef(180F, 0.0F, 1.0F, 0.0F);
        GL11.glTranslatef(0.0F, 0.75F, 0.0F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityWasp entity)
    {
        return waspTextureLocation;
    }
}
