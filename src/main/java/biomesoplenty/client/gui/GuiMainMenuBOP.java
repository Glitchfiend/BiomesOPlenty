package biomesoplenty.client.gui;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import biomesoplenty.common.helpers.BOPReflectionHelper;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.util.ResourceLocation;

public class GuiMainMenuBOP extends GuiMainMenu
{
	public static ResourceLocation[] bopTitlePanoramaPaths = new ResourceLocation[] {new ResourceLocation("biomesoplenty:textures/gui/title/background/panorama_0.png"), new ResourceLocation("biomesoplenty:textures/gui/title/background/panorama_1.png"), new ResourceLocation("biomesoplenty:textures/gui/title/background/panorama_2.png"), new ResourceLocation("biomesoplenty:textures/gui/title/background/panorama_3.png"), new ResourceLocation("biomesoplenty:textures/gui/title/background/panorama_4.png"), new ResourceLocation("biomesoplenty:textures/gui/title/background/panorama_5.png")};
	
	public GuiMainMenuBOP()
	{
		super();
		
		BOPReflectionHelper.setPrivateFinalValue(GuiMainMenu.class, this, bopTitlePanoramaPaths, "titlePanoramaPaths", "field_73978_o");
	}
}