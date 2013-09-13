package biomesoplenty.integration;

import cpw.mods.fml.common.Loader;

public class BOPCrossIntegration {
	
	public static void preInit()
	{
	}

	public static void init()
	{
		if (Loader.isModLoaded("BuildCraft|Transport"))
		{
			try {
				BCIntegration.init();
			}
			catch (Exception e) {
				System.out.println("[BiomesOPlenty] There was an error while integrating BuildCraft|Transport with Biomes O' Plenty!");
				e.printStackTrace(System.err);
			}
		}
		
		if (Loader.isModLoaded("Thaumcraft"))
		{
			try {
				ThaumcraftIntegration.init();
			}
			catch (Exception e) {
				System.out.println("[BiomesOPlenty] There was an error while integrating Thaumcraft with Biomes O' Plenty!");
				e.printStackTrace(System.err);
			}
		}

        if (Loader.isModLoaded("TreeCapitator"))
        {
            try {
                TreeCapitatorIntegration.init();
            }
            catch (Exception e) {
                System.out.println("[BiomesOPlenty] There was an error while integrating TreeCapitator with Biomes O' Plenty!");
                e.printStackTrace(System.err);
            }
        }
        if (Loader.isModLoaded("ForgeMicroblock"))
        {
            try {
                MultipartIntegration.init();
            }
            catch (Exception e) {
                System.out.println("[BiomesOPlenty] There was an error while integrating Forge Multipart with Biomes O' Plenty!");
                e.printStackTrace(System.err);
            }
        }
	}
	
	public static void postInit()
	{
		if (Loader.isModLoaded("Thaumcraft"))
		{
			try {
				ThaumcraftIntegration.postInit();
			}
			catch (Exception e) {
				System.out.println("[BiomesOPlenty] There was an error while integrating Thaumcraft with Biomes O' Plenty!");
				e.printStackTrace(System.err);
			}
		}

		if (Loader.isModLoaded("Forestry"))
		{
			try {
				ForestryIntegration.init();
			}
			catch (Exception e) {
				System.out.println("[BiomesOPlenty] There was an error while integrating Forestry with Biomes O' Plenty!");
				e.printStackTrace(System.err);
			}
		}

		if (Loader.isModLoaded("ThermalExpansion"))
		{
			try {
				TEIntegration.init();
			}
			catch (Exception e) {
				System.out.println("[BiomesOPlenty] There was an error while integrating Thermal Expansion with Biomes O' Plenty!");
				e.printStackTrace(System.err);
			}
		}
		
		if (Loader.isModLoaded("MineFactoryReloaded"))
        {
            try {
                MFRIntegration.init();
            }
            catch (Exception e) {
                System.out.println("[BiomesOPlenty] There was an error while integrating MineFactory Reloaded with Biomes O' Plenty!");
                e.printStackTrace(System.err);
            }
        }
	}
}
