package biomesoplenty.client.gui;

import static net.minecraft.util.EnumChatFormatting.*;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.regex.Pattern;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiConfirmOpenLink;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.apache.commons.io.FileUtils;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class WorldTypeMessageGUI extends GuiScreen
{
    private GuiScreen parentGuiScreen;
    
    private File nameHashFile;
    private String nameHash;
    
    private String link = "http://www.reddit.com/r/biomesoplenty/comments/1v5ly1/logical_bug_reporting/";
    
    private static final ResourceLocation bopLogoTexture = new ResourceLocation("biomesoplenty:textures/gui/logo256.png");
    
    public WorldTypeMessageGUI(GuiScreen parentGuiScreen, File nameHashFile, String nameHash)
    {
        this.parentGuiScreen = parentGuiScreen;
        
        this.nameHashFile = nameHashFile;
        this.nameHash = nameHash;
    }
    
    @Override
    public void initGui()
    {
        Keyboard.enableRepeatEvents(true);
        this.buttonList.clear();
        this.buttonList.add(new GuiButton(0, this.width / 2 - 175, this.height - 24, 350, 20, I18n.format("OK")));
    }

    @Override
    public void onGuiClosed()
    {
        Keyboard.enableRepeatEvents(false);
    }

    @Override
    protected void actionPerformed(GuiButton button)
    {
        if (button.enabled)
        {
            if (button.id == 0)
            {
                try
                {
                    nameHashFile.createNewFile();
                    FileUtils.write(nameHashFile, nameHash + "StartupWarning".hashCode());
                } 
                catch (IOException e)
                {
                    e.printStackTrace();
                }
                
                this.mc.displayGuiScreen(this.parentGuiScreen);
            }
        }
    }
    
    @Override
    public void drawScreen(int x, int y, float renderPartialTicks)
    {
        this.drawDefaultBackground();
        
        this.drawCenteredString(this.fontRendererObj, "" + RED + StatCollector.translateToLocal("warning.bopStartup1"), this.width / 2, 82, 0xFFFFFF);
        this.drawCenteredString(this.fontRendererObj, "" + RED + StatCollector.translateToLocal("warning.bopStartup2"), this.width / 2, 94, 0xFFFFFF);
        this.drawCenteredString(this.fontRendererObj, "" + RED + StatCollector.translateToLocal("warning.bopStartup3"), this.width / 2, 106, 0xFFFFFF);
        
        this.drawCenteredString(this.fontRendererObj, "" + RED + StatCollector.translateToLocal("warning.bopStartup4"), this.width / 2, 132, 0xFFFFFF);
        this.drawCenteredString(this.fontRendererObj, "" + RED + StatCollector.translateToLocal("warning.bopStartup5"), this.width / 2, 144, 0xFFFFFF);
        
        this.drawCenteredString(this.fontRendererObj, "" + DARK_RED + StatCollector.translateToLocal("warning.bopStartup6"), this.width / 2, 168, 0xFFFFFF);
        
        GL11.glEnable(GL11.GL_BLEND);
        this.mc.getTextureManager().bindTexture(bopLogoTexture);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        
        this.drawTexturedModalRect(this.width / 2 - 168 / 2, 0, 0, 0, 168, 80);
        
        GL11.glDisable(GL11.GL_BLEND);
       
        super.drawScreen(x, y, renderPartialTicks);
    }
}
