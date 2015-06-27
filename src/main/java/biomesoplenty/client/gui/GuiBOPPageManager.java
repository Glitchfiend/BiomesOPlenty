package biomesoplenty.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiBOPPageManager extends GuiListExtended
{
    private GuiBOPPageDelegate[] pages;
    private GuiBOPPageDelegate activePage;

    public GuiBOPPageManager(Minecraft mc, int width, int height, int top, int bottom, int slotHeight)
    {
        super(mc, width, height, top, bottom, slotHeight);

        this.field_148163_i = false;
    }

    public void setup()
    {
        for (GuiBOPPageDelegate page : pages)
        {
            page.setup();
        }
        
        this.activePage = pages[0];
    }

    public void setPages(GuiBOPPageDelegate ... pages)
    {
        this.pages = pages;
    }
    
    public GuiBOPPageDelegate getActivePage()
    {
        return this.activePage;
    }

    public int getNumPages()
    {
        return this.pages.length;
    }

    public void gotToPrevPage()
    {
        if (this.activePage.pageNumber > 0)
        {
            int newPageNumber = this.activePage.pageNumber - 1;
            GuiBOPPageDelegate newPage = this.pages[newPageNumber];
            
            this.activePage.onPageSwapInactive();
            newPage.onPageSwapActive();
            this.activePage = newPage;
            this.amountScrolled = 0.0F;
        }
    }

    public void goToNextPage()
    {
        if (this.activePage.pageNumber < this.pages.length - 1)
        {
            int newPageNumber = this.activePage.pageNumber + 1;
            GuiBOPPageDelegate newPage = this.pages[newPageNumber];
            
            this.activePage.onPageSwapInactive();
            newPage.onPageSwapActive();
            this.activePage = newPage;
            this.amountScrolled = 0.0F;
            this.amountScrolled = 0.0F;
        }
    }
    
    public Gui getGui(int fieldId)
    {
        return this.activePage.getGui(fieldId);
    }
    
    public Gui func_178056_g()
    {
        return this.activePage.func_178056_g();
    }

    public void func_178062_a(char p_178062_1_, int p_178062_2_)
    {
        this.activePage.func_178062_a(p_178062_1_, p_178062_2_);
    }
    
    @Override
    public boolean mouseClicked(int mouseX, int mouseY, int mouseEvent)
    {
        return this.activePage.mouseClicked(mouseX, mouseY, mouseEvent);
    }

    @Override
    public IGuiListEntry getListEntry(int rowNum)
    {
        return this.activePage.getListEntry(rowNum);
    }

    @Override
    protected int getSize()
    {
        return this.activePage.getSize();
    }
}