package biomesoplenty.common.eventhandler.gui;

import biomesoplenty.client.gui.GuiMainMenuBOP;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraftforge.client.event.GuiOpenEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class MainMenuOverrideEventHandler 
{
    @SubscribeEvent
    public void openMainMenu(GuiOpenEvent event)
    {
        if (event.gui instanceof GuiMainMenu)
        {
        	event.gui = new GuiMainMenuBOP();
        }
    }
}
