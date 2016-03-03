package biomesoplenty.client.gui;

import java.util.ArrayList;
import java.util.List;

import biomesoplenty.common.config.GameplayConfigurationHandler;
import biomesoplenty.common.config.MiscConfigurationHandler;
import biomesoplenty.common.remote.TrailManager;
import biomesoplenty.common.util.entity.PlayerUtil;
import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.config.DummyConfigElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;

public class GuiBOPConfig extends GuiConfig
{
    public GuiBOPConfig(GuiScreen parentScreen)
    {
        super(parentScreen, GuiBOPConfig.getConfigElements(), BiomesOPlenty.MOD_ID, false, false, "/biomesoplenty");
    }

    private static List<IConfigElement> getConfigElements()
    {
        List<IConfigElement> list = new ArrayList<IConfigElement>();

        List<IConfigElement> convenienceSettings = new ConfigElement(GameplayConfigurationHandler.config.getCategory(GameplayConfigurationHandler.convenienceSettings.toLowerCase())).getChildElements();
        List<IConfigElement> guiSettings = new ConfigElement(MiscConfigurationHandler.config.getCategory(MiscConfigurationHandler.guiSettings.toLowerCase())).getChildElements();
        List<IConfigElement> textureSettings = new ConfigElement(MiscConfigurationHandler.config.getCategory(MiscConfigurationHandler.textureSettings.toLowerCase())).getChildElements();
        List<IConfigElement> trailSettings = new ConfigElement(MiscConfigurationHandler.config.getCategory(MiscConfigurationHandler.trailSettings.toLowerCase())).getChildElements();

        list.add(new DummyConfigElement.DummyCategoryElement(I18n.translateToLocal("config.category.convenienceSettings.title"), "config.category.convenienceSettings", convenienceSettings));
        list.add(new DummyConfigElement.DummyCategoryElement(I18n.translateToLocal("config.category.guiSettings.title"), "config.category.guiSettings", guiSettings));
        list.add(new DummyConfigElement.DummyCategoryElement(I18n.translateToLocal("config.category.textureSettings.title"), "config.category.textureSettings", textureSettings));
        if (TrailManager.trailsMap.containsKey(PlayerUtil.getClientPlayerUUID()))
        {
            list.add(new DummyConfigElement.DummyCategoryElement(I18n.translateToLocal("config.category.trailSettings.title"), "config.category.trailSettings", trailSettings));
        }

        return list;
    }
}