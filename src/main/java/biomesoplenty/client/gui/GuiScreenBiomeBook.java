package biomesoplenty.client.gui;

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
        this.buttonList.remove(2);
        this.buttonList.remove(1);
        
        int i = (this.width - 192) / 2;
        byte b0 = 2;
        this.buttonList.add(this.buttonNextPage = new GuiScreenBiomeBook.NextPageButton(1, i + 120, b0 + 154, true));
        this.buttonList.add(this.buttonPreviousPage = new GuiScreenBiomeBook.NextPageButton(2, i + 38, b0 + 154, false));
        
        pageLinks.clear();
        
        //TODO:                                      fontRendererObj
        this.pageLinks.add(new PageLink(3, i + 41 + (fontRendererObj.getStringWidth("Contents") / 2) + 7, b0 + 154, 0x6189CE, "Contents", 1, 2, 0));
        
        addLinks();
        
        for (PageLink link : pageLinks)
        {
            this.buttonList.add(link);
        }
        
        updatePageButtons();
    }
    
    @Override
    //TODO:        actionPerformed()
    protected void actionPerformed(GuiButton button)
    {
        super.actionPerformed(button);
        
        //TODO:    enabled
        if (button.enabled)
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
        if (isDrawing) this.mc.getTextureManager().bindTexture(biomeBookTexture);
        
        isDrawing = false;
        
        super.drawTexturedModalRect(x, y, u, v, width, height);
    }
    
    private void updatePageButtons()
    {
        int currentPage = getCurrentPage();
        int bookTotalPages = BOPReflectionHelper.getPrivateValue(GuiScreenBook.class, this, "bookTotalPages", "bookTotalPages");
        
        this.buttonNextPage.visible = currentPage < bookTotalPages - 1;
        this.buttonPreviousPage.visible = currentPage > 0;
        
        for (PageLink link : pageLinks)
        {
            link.updateLink();
        }
    }
    
    public void addLinks()
    {
        NBTTagList pages = BOPReflectionHelper.getPrivateValue(GuiScreenBook.class, this, "bookPages", "bookPages");
        
        Pattern pattern = Pattern.compile("<link>(.+?)</link>");
        
        for (int pageNo = 0; pageNo < pages.tagCount(); pageNo++)
        {
            String pageText = pages.getStringTagAt(pageNo);
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
                   
                   int i = (this.width - 192) / 2;
                   byte b0 = 2;
                   
                   //TODO:                                                   fontRendererObj
                   this.pageLinks.add(new PageLink(3 + pageLinks.size(), i + 34 + fontRendererObj.getStringWidth(lineText.split(originalLinkString)[0]), b0 + 31 + (line * 9), 0x6189CE, linkText, pageNo, 0, linkedPage));
                   
                   lineText = lineText.replace(originalLinkString , StringUtils.repeat(" ", linkText.length()));
               }
               
               lineSplitText[line] = lineText;
           }
           
           //TODO: setTagAt()?
           pages.func_150304_a(pageNo, new NBTTagString(StringUtils.join(lineSplitText)));
        }
        
        BOPReflectionHelper.setPrivateValue(GuiScreenBook.class, this, pages, "bookPages", "bookPages");
    }
    
    public int getCurrentPage()
    {
        return BOPReflectionHelper.getPrivateValue(GuiScreenBook.class, this, "currPage", "currPage");
    }
    
    public void setCurrentPage(int pageNo)
    {
        BOPReflectionHelper.setPrivateValue(GuiScreenBook.class, this, pageNo, "currPage", "currPage");
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
        public void drawButton(Minecraft minecraft, int mouseX, int mouseY)
        {
            //TODO:  drawButton
            if (this.visible)
            {
                //TODO:                             xPosition                        yPosition                       xPosition             width                           yPosition             height
                boolean isHovering = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
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
                this.drawTexturedModalRect(this.xPosition, this.yPosition, k, l, 23, 13);
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
            super(id, xPosition, yPosition, fontRendererObj.getStringWidth(text.replaceAll("\\P{InBasic_Latin}", "")), 12, "");
            
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
            this.enabled = display;
            //TODO: drawButton
            this.visible = display;
        }

        @Override
        //TODO:     drawButton()
        public void drawButton(Minecraft minecraft, int mouseX, int mouseY)
        {
            //TODO:  drawButton
            if (this.visible)
            {
                //TODO:                             xPosition                        yPosition                       xPosition             width                           yPosition             height
                boolean isHovering = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
                
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

                String moddedText = isHovering ? "" + EnumChatFormatting.UNDERLINE + text : text;
                
                //TODO:                 fontRendererObj             xPosition       yPosition                                                  
                this.drawCenteredString(fontRendererObj, moddedText, xPosition, yPosition, colour);
            }
        }
        
        @Override
        public void drawCenteredString(FontRenderer fontRenderer, String string, int x, int y, int colour)
        {
            fontRenderer.drawStringWithShadow(string, x, y, colour);
        }
    }
}
