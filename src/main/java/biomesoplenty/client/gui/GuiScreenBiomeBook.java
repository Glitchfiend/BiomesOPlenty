/*package biomesoplenty.client.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreenBook;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;

import org.apache.commons.lang3.StringUtils;
import org.lwjgl.opengl.GL11;

import biomesoplenty.common.helpers.BOPReflectionHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GuiScreenBiomeBook extends GuiScreenBook
{
    private static ResourceLocation biomeBookTexture = new ResourceLocation("biomesoplenty:textures/gui/screen/biomebookgui.png");
    
    private GuiScreenBiomeBook.NextPageButton buttonNextPage;
    private GuiScreenBiomeBook.NextPageButton buttonPreviousPage;
    
    private List<PageLink> pageLinks = new ArrayList();
  
    private boolean isDrawing;
    
    public GuiScreenBiomeBook(EntityPlayer player, ItemStack itemStack)
    {
        super(player, itemStack, false);
    }
    
    @Override
    public void initGui()
    {
        super.initGui();
        
        //TODO: buttonList
        this.field_146292_n.remove(2);
        this.field_146292_n.remove(1);
        
        int i = (this.field_146294_l - 192) / 2;
        byte b0 = 2;
        this.field_146292_n.add(this.buttonNextPage = new GuiScreenBiomeBook.NextPageButton(1, i + 120, b0 + 154, true));
        this.field_146292_n.add(this.buttonPreviousPage = new GuiScreenBiomeBook.NextPageButton(2, i + 38, b0 + 154, false));
        
        pageLinks.clear();
        
        //TODO:                                      fontRendererObj
        this.pageLinks.add(new PageLink(3, i + 41 + (field_146289_q.getStringWidth("Contents") / 2) + 7, b0 + 154, 0x6189CE, "Contents", 1, 2, 0));
        
        addLinks();
        
        for (PageLink link : pageLinks)
        {
            this.field_146292_n.add(link);
        }
        
        updatePageButtons();
    }
    
    @Override
    //TODO:        actionPerformed()
    protected void func_146284_a(GuiButton button)
    {
        super.func_146284_a(button);
        
        //TODO:    enabled
        if (button.field_146124_l)
        {
            if (button instanceof PageLink)
            {
                PageLink link = (PageLink)button;
                
                setCurrentPage(link.pageNumber);
            }
            
            updatePageButtons();
        }
    }

    @Override
    public void drawScreen(int x, int y, float renderPartialTicks)
    {
        isDrawing = true;
        
        super.drawScreen(x, y, renderPartialTicks);
    }
    
    @Override
    public void drawTexturedModalRect(int x, int y, int u, int v, int width, int height)
    {
        //TODO:             minecraft
        if (isDrawing) this.field_146297_k.getTextureManager().bindTexture(biomeBookTexture);
        
        isDrawing = false;
        
        super.drawTexturedModalRect(x, y, u, v, width, height);
    }
    
    private void updatePageButtons()
    {
        int currentPage = getCurrentPage();
        int bookTotalPages = BOPReflectionHelper.getPrivateValue(GuiScreenBook.class, this, "field_146476_w", "field_146476_w");
        
        this.buttonNextPage.field_146125_m = currentPage < bookTotalPages - 1;
        this.buttonPreviousPage.field_146125_m = currentPage > 0;
        
        for (PageLink link : pageLinks)
        {
            link.updateLink();
        }
    }
    
    public void addLinks()
    {
        NBTTagList pages = BOPReflectionHelper.getPrivateValue(GuiScreenBook.class, this, "field_146483_y", "field_146483_y");
        
        Pattern pattern = Pattern.compile("<link>(.+?)</link>");
        
        for (int pageNo = 0; pageNo < pages.tagCount(); pageNo++)
        {
            String pageText = pages.func_150307_f(pageNo);
            String[] lineSplitText = pageText.split("(?<=[\\n])");
            
           for (int line = 0; line < lineSplitText.length; line++)
           {
               String lineText = lineSplitText[line];
               
               Matcher matcher = pattern.matcher(lineText);
               
               while (matcher.find())
               {
                   String originalLinkText = matcher.group(1);
                   String[] splitLinkText = originalLinkText.split("<");
                   
                   String linkText = splitLinkText[0];
                   
                   int linkedPage = Integer.parseInt(splitLinkText[1].split(">")[0]);
                   
                   String originalLinkString = "<link>" + originalLinkText + "</link>";
                   
                   int i = (this.field_146294_l - 192) / 2;
                   byte b0 = 2;
                   
                   //TODO:                                                   fontRendererObj
                   this.pageLinks.add(new PageLink(3 + pageLinks.size(), i + 34 + field_146289_q.getStringWidth(lineText.split(originalLinkString)[0]), b0 + 31 + (line * 9), 0x6189CE, linkText, pageNo, 0, linkedPage));
                   
                   lineText = lineText.replace(originalLinkString , StringUtils.repeat(" ", linkText.length()));
               }
               
               lineSplitText[line] = lineText;
           }
           
           //TODO: setTagAt()?
           pages.func_150304_a(pageNo, new NBTTagString(StringUtils.join(lineSplitText)));
        }
        
        BOPReflectionHelper.setPrivateValue(GuiScreenBook.class, this, pages, "field_146483_y", "field_146483_y");
    }
    
    public int getCurrentPage()
    {
        return BOPReflectionHelper.getPrivateValue(GuiScreenBook.class, this, "field_146484_x", "field_146484_x");
    }
    
    public void setCurrentPage(int pageNo)
    {
        BOPReflectionHelper.setPrivateValue(GuiScreenBook.class, this, pageNo, "field_146484_x", "field_146484_x");
    }
    
    @SideOnly(Side.CLIENT)
    static class NextPageButton extends GuiButton
    {
        private final boolean nextPage;

        public NextPageButton(int id, int xPosition, int yPosition, boolean nextPage)
        {
            super(id, xPosition, yPosition, 23, 13, "");
            this.nextPage = nextPage;
        }

        @Override
        //TODO:     drawButton()
        public void func_146112_a(Minecraft minecraft, int mouseX, int mouseY)
        {
            //TODO:  drawButton
            if (this.field_146125_m)
            {
                //TODO:                             xPosition                        yPosition                       xPosition             width                           yPosition             height
                boolean isHovering = mouseX >= this.field_146128_h && mouseY >= this.field_146129_i && mouseX < this.field_146128_h + this.field_146120_f && mouseY < this.field_146129_i + this.field_146121_g;
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                minecraft.getTextureManager().bindTexture(GuiScreenBiomeBook.biomeBookTexture);
                int k = 0;
                int l = 192;

                if (isHovering)
                {
                    k += 23;
                }

                if (!this.nextPage)
                {
                    l += 13;
                }

                //TODO:                         xPosition            yPosition          
                this.drawTexturedModalRect(this.field_146128_h, this.field_146129_i, k, l, 23, 13);
            }
        }
    }

    @SideOnly(Side.CLIENT)
    class PageLink extends GuiButton
    {
        private int colour;
        private String text;
        private int displayPage;
        private int displayType;
        public int pageNumber;
        
        public PageLink(int id, int xPosition, int yPosition, int colour, String text, int displayPage, int displayType, int pageNumber)
        {
            //TODO:                         fontRendererObj
            super(id, xPosition, yPosition, field_146289_q.getStringWidth(text.replaceAll("\\P{InBasic_Latin}", "")), 12, "");
            
            this.colour = colour;
            this.text = text;
            this.displayPage = displayPage;
            this.displayType = displayType;
            this.pageNumber = pageNumber;
        }
        
        public void updateLink()
        {
            boolean display =
            (displayType == 0 && getCurrentPage() == displayPage) ||
            (displayType == 1) ||
            (displayType == 2 && getCurrentPage() >= displayPage);
            
            //TODO: enabled
            this.field_146124_l = display;
            //TODO: drawButton
            this.field_146125_m = display;
        }

        @Override
        //TODO:     drawButton()
        public void func_146112_a(Minecraft minecraft, int mouseX, int mouseY)
        {
            //TODO:  drawButton
            if (this.field_146125_m)
            {
                //TODO:                             xPosition                        yPosition                       xPosition             width                           yPosition             height
                boolean isHovering = mouseX >= this.field_146128_h && mouseY >= this.field_146129_i && mouseX < this.field_146128_h + this.field_146120_f && mouseY < this.field_146129_i + this.field_146121_g;
                
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

                String moddedText = isHovering ? "" + EnumChatFormatting.UNDERLINE + text : text;
                
                //TODO:                 fontRendererObj             xPosition       yPosition                                                  
                this.drawCenteredString(field_146289_q, moddedText, field_146128_h, field_146129_i, colour);
            }
        }
        
        @Override
        public void drawCenteredString(FontRenderer fontRenderer, String string, int x, int y, int colour)
        {
            fontRenderer.drawStringWithShadow(string, x, y, colour);
        }
    }
}*/
