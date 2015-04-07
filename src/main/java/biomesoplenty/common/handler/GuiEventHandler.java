/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.handler;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiCreateWorld;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.world.WorldType;
import net.minecraftforge.client.event.GuiScreenEvent.DrawScreenEvent;
import net.minecraftforge.client.event.GuiScreenEvent.InitGuiEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import biomesoplenty.common.config.MiscConfigurationHandler;
import biomesoplenty.common.init.ModBiomes;

public class GuiEventHandler
{
    public static int blockCount = 0;
    public static int itemCount = 0;
    public static int biomeCount = 0;

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onPreInitCreateWorld(InitGuiEvent.Pre event)
    {
        GuiScreen screenGui = event.gui;
        
        if (MiscConfigurationHandler.useBoPWorldTypeDefault && screenGui instanceof GuiCreateWorld)
        {
            GuiCreateWorld createWorldGui = (GuiCreateWorld)screenGui;
            
            createWorldGui.selectedIndex = ModBiomes.worldTypeBOP.getWorldTypeID();
        }
    }
    
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onDrawScreen(DrawScreenEvent.Post event)
    {
        GuiScreen screenGui = event.gui;

        if (screenGui instanceof GuiCreateWorld)
        {
            GuiCreateWorld createWorldGui = (GuiCreateWorld)screenGui;
            GuiButton mapTypeButton = createWorldGui.btnMapType;
            int worldTypeIndex = createWorldGui.selectedIndex;

            if (mapTypeButton.isMouseOver() && WorldType.worldTypes[worldTypeIndex] == ModBiomes.worldTypeBOP)
            {
                List text = new ArrayList<String>();

                text.add("Progress:");
                text.add("Blocks: " + blockCount);
                text.add("Items: " + itemCount);
                text.add("Entities: 0");
                text.add("Biomes: " + biomeCount);

                createWorldGui.drawHoveringText(text, event.mouseX, event.mouseY);
            }
        }
    }
}
