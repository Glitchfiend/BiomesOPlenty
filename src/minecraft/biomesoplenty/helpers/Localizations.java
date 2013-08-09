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
		new ResourceLocation(localizationLocation + "pl_PL.xml"), new ResourceLocation(localizationLocation + "fr_FR.xml"), new ResourceLocation(localizationLocation + "fr_CA.xml"), 
		new ResourceLocation(localizationLocation + "ja_JP.xml") };

	public static boolean isXMLLanguageFile(ResourceLocation fileName) 
	{
		return fileName.func_110623_a().endsWith(".xml");
	}

	public static String getLocaleFromFileName(ResourceLocation fileName) 
	{
		FMLCommonHandler.instance().getFMLLogger().log(Level.INFO, "[BiomesOPlenty] Localizations loaded for " + fileName.func_110623_a().substring(fileName.func_110623_a().lastIndexOf('/') + 1, fileName.func_110623_a().lastIndexOf('.')));
		return fileName.func_110623_a().substring(fileName.func_110623_a().lastIndexOf('/') + 1, fileName.func_110623_a().lastIndexOf('.'));
	}

	public String getLocalizedString(String key) 
	{
		return LanguageRegistry.instance().getStringLocalization(key);
	}

	public static void loadLanguages() 
	{	
		for (ResourceLocation localizationFile : localeFiles) 
		{
			LanguageRegistry.instance().loadLocalization(localizationFile.func_110623_a(), getLocaleFromFileName(localizationFile), isXMLLanguageFile(localizationFile));
		}

		for (int mat = 0; mat < materialTypes.length; mat++)
		{
			for (int type = 0; type < toolMaterialNames.length; type++)
			{
				String internalName = new StringBuilder().append("item.bop.tc.").append(materialTypes[mat]).append(".").append(toolMaterialNames[type]).append(".name").toString();
				String visibleName = new StringBuilder().append(toolMaterialNames[type]).append(materialNames[mat]).toString();
				LanguageRegistry.instance().addStringLocalization(internalName, "en_US", visibleName);
			}
		}
		
        for (int i = 0; i < shardNames.length; i++)
        {
            String internalName = "item.bop.tc.ToolShard." + toolMaterialNames[i] + ".name";
            String visibleName = shardNames[i];
            LanguageRegistry.instance().addStringLocalization(internalName, "en_US", visibleName);
        }
	}

    public static final String[] shardNames = new String[] { "Amethyst" };
	
	public static final String[] toolMaterialNames = new String[] { "Amethyst" };

	public static final String[] materialTypes = new String[] { "ToolRod", "PickaxeHead", "ShovelHead", "AxeHead", "SwordBlade", "LargeGuard", "MediumGuard", "Crossbar", "Binding", "FrypanHead", "SignHead", "LumberHead", "KnifeBlade", "ChiselHead", "ScytheBlade", "LumberHead", "ThickRod", "ThickBinding", "LargeSwordBlade", "LargePlate", "ExcavatorHead", "HammerHead", "FullGuard" };

	public static final String[] materialNames = new String[] { " Rod", " Pickaxe Head", " Shovel Head", " Axe Head", " Sword Blade", " Wide Guard", " Hand Guard", " Crossbar", " Binding", " Pan", " Board", " Broad Axe Head", " Knife Blade", " Chisel Head", " Scythe Blade", " Broad Axe Head", " Tough Tool Rod", " Tough Binding", " Large Sword Blade", " Large Plate", " Excavator Head", " Hammer Head", " Full Guard"};
}
