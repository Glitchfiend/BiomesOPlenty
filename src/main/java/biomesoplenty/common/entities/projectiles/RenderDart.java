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
    
    public RenderDart(RenderManager p_i46193_1_)
    {
        super(p_i46193_1_);
        int n = ItemDart.DartType.values().length;
        this.textures = new ResourceLocation[n];
        for (int i = 0; i < n; ++i)
        {
            this.textures[i] = new ResourceLocation("biomesoplenty:textures/entity/" + ItemDart.DartType.values()[i].getName() + ".png");
        }        
    }

    // TODO: de-obfuscate
    public void doRender(EntityDart dart, double p_180551_2_, double p_180551_4_, double p_180551_6_, float p_180551_8_, float p_180551_9_)
    {
        this.bindEntityTexture(dart);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)p_180551_2_, (float)p_180551_4_, (float)p_180551_6_);
        GlStateManager.rotate(dart.prevRotationYaw + (dart.rotationYaw - dart.prevRotationYaw) * p_180551_9_ - 90.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(dart.prevRotationPitch + (dart.rotationPitch - dart.prevRotationPitch) * p_180551_9_, 0.0F, 0.0F, 1.0F);
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        byte b0 = 0;
        float f2 = 0.0F;
        float f3 = 0.5F;
        float f4 = (float)(0 + b0 * 10) / 32.0F;
        float f5 = (float)(5 + b0 * 10) / 32.0F;
        float f6 = 0.0F;
        float f7 = 0.15625F;
        float f8 = (float)(5 + b0 * 10) / 32.0F;
        float f9 = (float)(10 + b0 * 10) / 32.0F;
        float f10 = 0.05625F;
        GlStateManager.enableRescaleNormal();

        GlStateManager.rotate(45.0F, 1.0F, 0.0F, 0.0F);
        GlStateManager.scale(f10, f10, f10);
        GlStateManager.translate(-4.0F, 0.0F, 0.0F);
        GL11.glNormal3f(f10, 0.0F, 0.0F);
        worldrenderer.startDrawingQuads();
        worldrenderer.addVertexWithUV(-7.0D, -2.0D, -2.0D, (double)f6, (double)f8);
        worldrenderer.addVertexWithUV(-7.0D, -2.0D, 2.0D, (double)f7, (double)f8);
        worldrenderer.addVertexWithUV(-7.0D, 2.0D, 2.0D, (double)f7, (double)f9);
        worldrenderer.addVertexWithUV(-7.0D, 2.0D, -2.0D, (double)f6, (double)f9);
        tessellator.draw();
        GL11.glNormal3f(-f10, 0.0F, 0.0F);
        worldrenderer.startDrawingQuads();
        worldrenderer.addVertexWithUV(-7.0D, 2.0D, -2.0D, (double)f6, (double)f8);
        worldrenderer.addVertexWithUV(-7.0D, 2.0D, 2.0D, (double)f7, (double)f8);
        worldrenderer.addVertexWithUV(-7.0D, -2.0D, 2.0D, (double)f7, (double)f9);
        worldrenderer.addVertexWithUV(-7.0D, -2.0D, -2.0D, (double)f6, (double)f9);
        tessellator.draw();

        for (int i = 0; i < 4; ++i)
        {
            GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
            GL11.glNormal3f(0.0F, 0.0F, f10);
            worldrenderer.startDrawingQuads();
            worldrenderer.addVertexWithUV(-8.0D, -2.0D, 0.0D, (double)f2, (double)f4);
            worldrenderer.addVertexWithUV(8.0D, -2.0D, 0.0D, (double)f3, (double)f4);
            worldrenderer.addVertexWithUV(8.0D, 2.0D, 0.0D, (double)f3, (double)f5);
            worldrenderer.addVertexWithUV(-8.0D, 2.0D, 0.0D, (double)f2, (double)f5);
            tessellator.draw();
        }

        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();
        super.doRender(dart, p_180551_2_, p_180551_4_, p_180551_6_, p_180551_8_, p_180551_9_);
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