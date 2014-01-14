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
    public void confirmClicked(boolean par1, int par2)
    {
        if (par2 == 13)
        {
            if (par1)
            {
                try
                {
                    Class oclass = Class.forName("java.awt.Desktop");
                    Object object = oclass.getMethod("getDesktop", new Class[0]).invoke((Object)null, new Object[0]);
                    oclass.getMethod("browse", new Class[] {URI.class}).invoke(object, new Object[] {new URI(this.link)});
                }
                catch (Throwable throwable)
                {
                    //field_146974_g.error("Couldn\'t open link", throwable);
                }
            }

            //TODO: mc          displayGuiScreen
            this.field_146297_k.func_147108_a(this);
        }
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
    protected void mouseClicked(int x, int y, int eventButton)
    {
        super.mouseClicked(x, y, eventButton);
        
        int middleOfScreen = this.field_146294_l / 2;
        int middleOfText = this.field_146289_q.getStringWidth(link) / 2;

        if (x >= middleOfScreen - middleOfText && x <= middleOfScreen + middleOfText && y >= 108 && y <= 108 + 12)
        {
            GuiConfirmOpenLink guiconfirmopenlink = new GuiConfirmOpenLink(this, this.link, 13, true);
            guiconfirmopenlink.func_146358_g();

            //TODO: mc          displayGuiScreen()
            this.field_146297_k.func_147108_a(guiconfirmopenlink);
        }
    }
    
    @Override
    public void drawScreen(int x, int y, float renderPartialTicks)
    {
        String linkFormatting = "" + GREEN + UNDERLINE + ITALIC;
        
        //TODO: drawDefaultBackground()
        this.func_146276_q_();
        
        //EnumChatFormatting.
        
        //TODO:                      fontRendererObj                                    width
        this.drawCenteredString(this.field_146289_q, "" + YELLOW + BOLD + "WARNING: " + DARK_RED + "Biomes O' Plenty for 1.7 is NOT yet stable. Do not expect everything", this.field_146294_l / 2, 82, 0xFFFFFF);       
        this.drawCenteredString(this.field_146289_q, "" + DARK_RED + "to be perfect.", this.field_146294_l / 2, 94, 0xFFFFFF); 
        
        this.drawCenteredString(this.field_146289_q, linkFormatting + link, this.field_146294_l / 2, 120, 0xFFFFFF);
        this.drawCenteredString(this.field_146289_q, "" + GOLD + BOLD + UNDERLINE + "Biomes O' Plenty Worldtype", this.field_146294_l / 2, 156, 0xFFFFFF);
        this.drawCenteredString(this.field_146289_q, "" + RED + "Biomes O' Plenty uses a custom worldtype for its biomes. When creating a world, it", this.field_146294_l / 2, 180, 0xFFFFFF);
        this.drawCenteredString(this.field_146289_q, "" + RED + "may be enabled by clicking the 'World Type' button under 'More World Options'", this.field_146294_l / 2, 192, 0xFFFFFF);
        this.drawCenteredString(this.field_146289_q, "" + RED + "until it displays 'Biomes O' Plenty'.", this.field_146294_l / 2, 204, 0xFFFFFF);
        
        this.drawCenteredString(this.field_146289_q, "" + RED + "The worldtype can be used on servers by changing the 'level-type' in", this.field_146294_l / 2, 230, 0xFFFFFF);
        this.drawCenteredString(this.field_146289_q, "" + RED + "sever.properties to 'BIOMESOP' (without quotes)", this.field_146294_l / 2, 242, 0xFFFFFF);
        
        this.drawCenteredString(this.field_146289_q, "" + DARK_RED + "This message will only display once.", this.field_146294_l / 2, 266, 0xFFFFFF);
        
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
