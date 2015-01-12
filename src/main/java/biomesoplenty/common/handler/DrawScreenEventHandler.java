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

import biomesoplenty.common.init.ModBiomes;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiCreateWorld;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.world.WorldType;
import net.minecraftforge.client.event.GuiScreenEvent.DrawScreenEvent;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class DrawScreenEventHandler 
{
	public static int blockCount = 0;
	
	@SubscribeEvent
	public void onDrawScreen(DrawScreenEvent.Post event)
	{
		GuiScreen screenGui = event.gui;
		
		if (screenGui instanceof GuiCreateWorld)
		{
			GuiCreateWorld createWorldGui = (GuiCreateWorld)screenGui;
			GuiButton mapTypeButton = createWorldGui.field_146320_D;
			int worldTypeIndex = createWorldGui.field_146331_K;
			
			if (mapTypeButton.isMouseOver() && WorldType.worldTypes[worldTypeIndex] == ModBiomes.worldTypeBOP)
			{
				List text = new ArrayList<String>();
				
				text.add("Progress:");
				text.add("Blocks: " + blockCount);
				text.add("Items: 0");
				text.add("Entities: 0");
				text.add("Biomes: 0");
				
				createWorldGui.drawHoveringText(text, event.mouseX, event.mouseY);
			}
		}
	}
}
