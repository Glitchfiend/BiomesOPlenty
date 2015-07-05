package biomesoplenty.client.gui;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiBOPPageManager
{
    private GuiBOPPageDelegate[] pages;
    private GuiBOPPageDelegate activePage;

    public GuiBOPPageManager(GuiBOPPageDelegate ... pages)
    {
        this.pages = pages;
        this.activePage = pages[0];
    }
    
    public void setup()
    {
        for (GuiBOPPageDelegate page : pages)
        {
            page.setup();
        }
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
            
            this.activePage.setVisible(false);
            newPage.setVisible(true);
            this.activePage = newPage;
        }
    }

    public void goToNextPage()
    {
        if (this.activePage.pageNumber < this.pages.length - 1)
        {
            int newPageNumber = this.activePage.pageNumber + 1;
            GuiBOPPageDelegate newPage = this.pages[newPageNumber];
            
            this.activePage.setVisible(false);
            newPage.setVisible(true);
            this.activePage = newPage;
        }
    }
}