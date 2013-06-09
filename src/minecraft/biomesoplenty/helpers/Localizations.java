package biomesoplenty.helpers;

import cpw.mods.fml.common.registry.LanguageRegistry;

public class Localizations 
{
	private static final String localizationLocation = "/mods/BiomesOPlenty/localizations/";

    public static String[] localeFiles = { localizationLocation + "en_US.xml" };
    
    public static boolean isXMLLanguageFile(String fileName) 
    {
        return fileName.endsWith(".xml");
    }

    public static String getLocaleFromFileName(String fileName) 
    {
        return fileName.substring(fileName.lastIndexOf('/') + 1, fileName.lastIndexOf('.'));
    }

    public String getLocalizedString(String key) 
    {
        return LanguageRegistry.instance().getStringLocalization(key);
    }
	
    public static void loadLanguages() 
    {	
        for (String localizationFile : localeFiles) 
        {
            LanguageRegistry.instance().loadLocalization(localizationFile, getLocaleFromFileName(localizationFile), isXMLLanguageFile(localizationFile));
        }
    }
}
