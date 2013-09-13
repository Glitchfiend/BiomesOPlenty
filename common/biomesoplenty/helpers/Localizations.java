package biomesoplenty.helpers;

import java.util.logging.Level;

import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class Localizations 
{
	private static final String localizationLocation = "/assets/biomesoplenty/lang/";

	public static ResourceLocation[] localeFiles = { new ResourceLocation(localizationLocation + "en_US.xml"), new ResourceLocation(localizationLocation + "de_DE.xml"), 
		new ResourceLocation(localizationLocation + "nl_NL.xml"), new ResourceLocation(localizationLocation + "ru_RU.xml"), new ResourceLocation(localizationLocation + "zh_CN.xml"), 
		new ResourceLocation(localizationLocation + "pl_PL.xml"), new ResourceLocation(localizationLocation + "fr_FR.xml"), new ResourceLocation(localizationLocation + "fr_CA.xml")//, 
		/*new ResourceLocation(localizationLocation + "ja_JP.xml")*/ };

	public static boolean isXMLLanguageFile(ResourceLocation fileName) 
	{
		return fileName.getResourcePath().endsWith(".xml");
	}

	public static String getLocaleFromFileName(ResourceLocation fileName) 
	{
		FMLCommonHandler.instance().getFMLLogger().log(Level.INFO, "[BiomesOPlenty] Localizations loaded for " + fileName.getResourcePath().substring(fileName.getResourcePath().lastIndexOf('/') + 1, fileName.getResourcePath().lastIndexOf('.')));
		return fileName.getResourcePath().substring(fileName.getResourcePath().lastIndexOf('/') + 1, fileName.getResourcePath().lastIndexOf('.'));
	}

	public String getLocalizedString(String key) 
	{
		return LanguageRegistry.instance().getStringLocalization(key);
	}

	public static void loadLanguages() 
	{	
		for (ResourceLocation localizationFile : localeFiles) 
		{
			LanguageRegistry.instance().loadLocalization(localizationFile.getResourcePath(), getLocaleFromFileName(localizationFile), isXMLLanguageFile(localizationFile));
		}
	}
}
