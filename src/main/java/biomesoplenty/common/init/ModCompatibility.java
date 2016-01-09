package biomesoplenty.common.init;

import biomesoplenty.common.integration.ThaumcraftCompat;
import biomesoplenty.core.BiomesOPlenty;
import net.minecraftforge.fml.common.Loader;

public class ModCompatibility 
{
    public static void postInit()
    {
        if (Loader.isModLoaded("Thaumcraft"))
        {
            try
            {
                ThaumcraftCompat.init();
            }
            catch (Exception e)
            {
                BiomesOPlenty.logger.error("There was an error while integrating Thaumcraft with Biomes O' Plenty", e);
            }
        }
    }
}
