/*******************************************************************************
 * Copyright 2014-2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiListExtended;

public abstract class GuiBOPPageDelegate extends GuiListExtended
{
    public final GuiBOPPageManager pageManager;
    public final int pageNumber;
    
    public GuiBOPPageDelegate(GuiBOPPageManager pageManager, int pageNumber)
    {
        super(Minecraft.getMinecraft(), pageManager.width, pageManager.height, pageManager.top, pageManager.bottom, pageManager.slotHeight);
        
        this.pageManager = pageManager;
        this.pageNumber = pageNumber;
    }
    
    public abstract void setup();
    
    /**
     * Called just before the page is changed and this page becomes active. Provides an opportunity to
     * show all elements and perform any necessary setup before a change.
     */
    public void onPageSwapActive() {}
    
    /**
     * Called just before the page is changed and this page becomes inactive. Provides an opportunity to
     * hide all elements and perform any necessary cleanup before a change.
     */
    public void onPageSwapInactive() {}
    
    public abstract Gui getGui(int fieldId);
    
    public abstract int getSize();
    public abstract Gui func_178056_g();
    public void func_178062_a(char p_178062_1_, int p_178062_2_) {}
}
