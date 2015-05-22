/*******************************************************************************
 * Copyright 2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.entities.projectiles;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.lwjgl.opengl.GL11;

import biomesoplenty.common.item.ItemDart;

@SideOnly(Side.CLIENT)
public class RenderDart extends Render
{

    protected ResourceLocation[] textures;
    
    public RenderDart(RenderManager renderManager)
    {
        super(renderManager);
        int n = ItemDart.DartType.values().length;
        this.textures = new ResourceLocation[n];
        for (int i = 0; i < n; ++i)
        {
            this.textures[i] = new ResourceLocation("biomesoplenty:textures/entity/" + ItemDart.DartType.values()[i].getName() + ".png");
        }
    }

    public void doRender(EntityDart dart, double x, double y, double z, float entityYaw, float partialTicks)
    {
        this.bindEntityTexture(dart);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)x, (float)y, (float)z);
        GlStateManager.rotate(dart.prevRotationYaw + (dart.rotationYaw - dart.prevRotationYaw) * partialTicks - 90.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(dart.prevRotationPitch + (dart.rotationPitch - dart.prevRotationPitch) * partialTicks, 0.0F, 0.0F, 1.0F);
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        
        float shaft_u0 = 0.0F;
        float shaft_u1 = 0.5F;
        float shaft_v0 = 0.0F;
        float shaft_v1 = 0.15625F;
        
        float flights_u0 = 0.0F;
        float flights_u1 = 0.15625F;
        float flights_v0 = 0.15625F;
        float flights_v1 = 0.3125F;
         
        float scale = 0.05625F;
        GlStateManager.enableRescaleNormal();

        // render flights
        
        GlStateManager.rotate(45.0F, 1.0F, 0.0F, 0.0F);
        GlStateManager.scale(scale, scale, scale);
        GlStateManager.translate(-4.0F, 0.0F, 0.0F);
        
        GL11.glNormal3f(scale, 0.0F, 0.0F);
        worldrenderer.startDrawingQuads();
        worldrenderer.addVertexWithUV(-7.0D, -2.0D, -2.0D, (double)flights_u0, (double)flights_v0);
        worldrenderer.addVertexWithUV(-7.0D, -2.0D, 2.0D, (double)flights_u1, (double)flights_v0);
        worldrenderer.addVertexWithUV(-7.0D, 2.0D, 2.0D, (double)flights_u1, (double)flights_v1);
        worldrenderer.addVertexWithUV(-7.0D, 2.0D, -2.0D, (double)flights_u0, (double)flights_v1);
        tessellator.draw();
        
        GL11.glNormal3f(-scale, 0.0F, 0.0F);
        worldrenderer.startDrawingQuads();
        worldrenderer.addVertexWithUV(-7.0D, 2.0D, -2.0D, (double)flights_u0, (double)flights_v0);
        worldrenderer.addVertexWithUV(-7.0D, 2.0D, 2.0D, (double)flights_u1, (double)flights_v0);
        worldrenderer.addVertexWithUV(-7.0D, -2.0D, 2.0D, (double)flights_u1, (double)flights_v1);
        worldrenderer.addVertexWithUV(-7.0D, -2.0D, -2.0D, (double)flights_u0, (double)flights_v1);
        tessellator.draw();

        // render shaft
        
        for (int i = 0; i < 4; ++i)
        {
            GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
            GL11.glNormal3f(0.0F, 0.0F, scale);
            worldrenderer.startDrawingQuads();
            worldrenderer.addVertexWithUV(-8.0D, -2.0D, 0.0D, (double)shaft_u0, (double)shaft_v0);
            worldrenderer.addVertexWithUV(8.0D, -2.0D, 0.0D, (double)shaft_u1, (double)shaft_v0);
            worldrenderer.addVertexWithUV(8.0D, 2.0D, 0.0D, (double)shaft_u1, (double)shaft_v1);
            worldrenderer.addVertexWithUV(-8.0D, 2.0D, 0.0D, (double)shaft_u0, (double)shaft_v1);
            tessellator.draw();
        }

        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();
        super.doRender(dart, x, y, z, entityYaw, partialTicks);
    }

    protected ResourceLocation getEntityTexture(EntityDart dart)
    {
        return this.textures[dart.getDartType().ordinal()];
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return this.getEntityTexture((EntityDart)entity);
    }

    @Override
    public void doRender(Entity entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        this.doRender((EntityDart)entity, x, y, z, entityYaw, partialTicks);
    }
}