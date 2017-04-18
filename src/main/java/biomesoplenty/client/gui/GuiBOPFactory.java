package biomesoplenty.client.gui;

import biomesoplenty.common.config.GameplayConfigurationHandler;
import biomesoplenty.common.remote.TrailManager;
import biomesoplenty.common.util.entity.PlayerUtil;
import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.DefaultGuiFactory;
import net.minecraftforge.fml.client.config.DummyConfigElement.DummyCategoryElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;

import java.util.ArrayList;
import java.util.List;

import static biomesoplenty.common.config.GameplayConfigurationHandler.CONVENIENCE_SETTINGS;
import static biomesoplenty.common.config.MiscConfigurationHandler.*;

public class GuiBOPFactory extends DefaultGuiFactory
{
    public GuiBOPFactory()
    {
        super(BiomesOPlenty.MOD_ID, BiomesOPlenty.MOD_NAME);
    }

    @Override
    public GuiScreen createConfigGui(GuiScreen parentScreen)
    {
        return new GuiConfig(parentScreen, getConfigElements(), modid, false, false, title);
    }

    private static List<IConfigElement> getConfigElements()
    {
        List<IConfigElement> list = new ArrayList<IConfigElement>();

        List<IConfigElement> convenience_settings = new ConfigElement(GameplayConfigurationHandler.config.getCategory(CONVENIENCE_SETTINGS.toLowerCase())).getChildElements();
        List<IConfigElement> gui_settings = new ConfigElement(config.getCategory(GUI_SETTINGS.toLowerCase())).getChildElements();
        List<IConfigElement> texture_settings = new ConfigElement(config.getCategory(VISUAL_SETTINGS.toLowerCase())).getChildElements();
        List<IConfigElement> trail_settings = new ConfigElement(config.getCategory(TRAIL_SETTINGS.toLowerCase())).getChildElements();

        list.add(new DummyCategoryElement(I18n.translateToLocal("config.category.convenienceSettings.title"), "config.category.convenienceSettings", convenience_settings));
        list.add(new DummyCategoryElement(I18n.translateToLocal("config.category.guiSettings.title"), "config.category.guiSettings", gui_settings));
        list.add(new DummyCategoryElement(I18n.translateToLocal("config.category.textureSettings.title"), "config.category.textureSettings", texture_settings));
        if (TrailManager.trailsMap.containsKey(PlayerUtil.getClientPlayerUUID()))
        {
            list.add(new DummyCategoryElement(I18n.translateToLocal("config.category.trailSettings.title"), "config.category.trailSettings", trail_settings));
        }

        return list;
    }
}