package tan.handler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.ForgeSubscribe;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;

public class RenderOverlayEventHandler
{
    public ResourceLocation overlayLocation = new ResourceLocation("toughasnails:textures/overlay/overlay.png");
    
    @ForgeSubscribe
    public void render(RenderGameOverlayEvent.Pre event)
    {
        ScaledResolution scaledRes = event.resolution;
        Minecraft minecraft = Minecraft.getMinecraft();
        FontRenderer fontRenderer = minecraft.fontRenderer;
        
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        bindTexture(overlayLocation);
        {
            NBTTagCompound tanData = minecraft.thePlayer.getEntityData().getCompoundTag("ToughAsNails");
            
            int temperature = MathHelper.floor_float(tanData.getFloat("Temp"));

            int temperatureXPos = scaledRes.getScaledWidth() / 2 - 8;
            int temperatureYPos = scaledRes.getScaledHeight() - 52;
            
            minecraft.mcProfiler.startSection("temperatureBall");
            {   
                this.drawTexturedModalRect(temperatureXPos, temperatureYPos, 16, 0, 16, 16);
                GL11.glColor4f(1.0F, 1.0F, 1.0F, temperature / 100F + 0.5F);
                this.drawTexturedModalRect(temperatureXPos, temperatureYPos, 0, 0, 16, 16);
            }
            minecraft.mcProfiler.endSection();
            
            minecraft.mcProfiler.startSection("temperatureLevel");
            {
                GL11.glPushMatrix();
                {
                    String text = temperature + "°C";
                    
                    GL11.glTranslatef((float)(temperatureXPos - (fontRenderer.getStringWidth(text) / 2) + 12), (float)(temperatureYPos + 6), 0.0F);
                    GL11.glScalef(0.65F, 0.65F, 0.0F);
                    
                    drawStringWithBorder(fontRenderer, text, 0, 0, 0, 16777215);
                }
                GL11.glPopMatrix();
            }
            minecraft.mcProfiler.endSection();
        }
        bindTexture(new ResourceLocation("minecraft:textures/gui/icons.png"));
    }

    public static void bindTexture(ResourceLocation resourceLocation)
    {
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(resourceLocation);
    }
    
    public void drawStringWithBorder(FontRenderer fontrenderer, String string, int x, int y, int borderColour, int colour)
    {
        fontrenderer.drawString(string, x + 1, y, borderColour);
        fontrenderer.drawString(string, x - 1, y, borderColour);
        fontrenderer.drawString(string, x, y + 1, borderColour);
        fontrenderer.drawString(string, x, y - 1, borderColour);
        fontrenderer.drawString(string, x, y, colour);
    }

    public void drawTexturedModalRect(int x, int y, int u, int v, int width, int height)
    {
        float f = 0.00390625F;
        float f1 = 0.00390625F;
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(x + 0, y + height, 0.0, (u + 0) * f, (v + height) * f1);
        tessellator.addVertexWithUV(x + width, y + height, 0.0, (u + width) * f, (v + height) * f1);
        tessellator.addVertexWithUV(x + width, y + 0, 0.0, (u + width) * f, (v + 0) * f1);
        tessellator.addVertexWithUV(x + 0, y + 0, 0.0, (u + 0) * f, (v + 0) * f1);
        tessellator.draw();
    }
}
