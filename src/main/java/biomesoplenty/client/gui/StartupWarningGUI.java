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

import org.apache.commons.io.FileUtils;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class StartupWarningGUI extends GuiScreen
{
    private GuiScreen parentGuiScreen;
    
    private File nameHashFile;
    private String nameHash;
    
    private String link = "http://www.reddit.com/r/biomesoplenty/comments/1v5ly1/logical_bug_reporting/";
    
    private static final ResourceLocation bopLogoTexture = new ResourceLocation("biomesoplenty:textures/gui/logo256.png");
    
    public StartupWarningGUI(GuiScreen parentGuiScreen, File nameHashFile, String nameHash)
    {
        this.parentGuiScreen = parentGuiScreen;
        
        this.nameHashFile = nameHashFile;
        this.nameHash = nameHash;
    }
    
    @Override
    public void initGui()
    {
        Keyboard.enableRepeatEvents(true);
        //TODO: buttonList
        this.field_146292_n.clear();
        //TODO: buttonList
        this.field_146292_n.add(new GuiButton(0, this.field_146294_l / 2 - 175, this.field_146295_m - 48, 350, 20, I18n.getStringParams("OK")));
        //TODO: buttonList
        this.field_146292_n.add(new GuiButton(1, this.field_146294_l / 2 - 175, this.field_146295_m - 24, 350, 20, I18n.getStringParams("Cancel")));
    }

    @Override
    //TODO:     onGuiClosed()
    public void func_146281_b()
    {
        Keyboard.enableRepeatEvents(false);
    }

    @Override
    //TODO:        actionPerformed()
    protected void func_146284_a(GuiButton button)
    {
        //TODO:     enabled
        if (button.field_146124_l)
        {
            //TODO:    id
            if (button.field_146127_k == 0)
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
                
                //TODO:             displayGuiScreen()
                this.field_146297_k.func_147108_a(this.parentGuiScreen);
            }
            //TODO:    id
            if (button.field_146127_k == 1)
            {
                System.exit(0);
            }
        }
    }
    
    @Override
    public void drawScreen(int x, int y, float renderPartialTicks)
    {
        //TODO: drawDefaultBackground()
        this.func_146276_q_();
        
        //TODO:                      fontRendererObj                                    width
        this.drawCenteredString(this.field_146289_q, "" + RED + "Biomes O' Plenty uses a custom worldtype for its biomes. When creating a world, it", this.field_146294_l / 2, 82, 0xFFFFFF);
        this.drawCenteredString(this.field_146289_q, "" + RED + "may be enabled by clicking the 'World Type' button under 'More World Options'", this.field_146294_l / 2, 94, 0xFFFFFF);
        this.drawCenteredString(this.field_146289_q, "" + RED + "until it displays 'Biomes O' Plenty'.", this.field_146294_l / 2, 106, 0xFFFFFF);
        
        this.drawCenteredString(this.field_146289_q, "" + RED + "The worldtype can be used on servers by changing the 'level-type' in", this.field_146294_l / 2, 132, 0xFFFFFF);
        this.drawCenteredString(this.field_146289_q, "" + RED + "sever.properties to 'BIOMESOP' (without quotes)", this.field_146294_l / 2, 144, 0xFFFFFF);
        
        this.drawCenteredString(this.field_146289_q, "" + DARK_RED + "This message will only display once.", this.field_146294_l / 2, 168, 0xFFFFFF);
        
        this.field_146297_k.getTextureManager().bindTexture(bopLogoTexture);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        
        //                              width                      x, y, u, v, width, height
        this.drawTexturedModalRect(this.field_146294_l / 2 - 168 / 2, 0, 0, 0, 168, 80);
        
        
        super.drawScreen(x, y, renderPartialTicks);
    }
    
    @Override
    public void drawCenteredString(FontRenderer fontRenderer, String string, int x, int y, int colour)
    {
        fontRenderer.drawStringWithShadow(string, x - fontRenderer.getStringWidth(string.replaceAll("\\P{InBasic_Latin}", "")) / 2, y, colour);
    }
}
