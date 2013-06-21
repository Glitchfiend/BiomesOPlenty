package biomesoplenty.integration;

import mods.tinker.tconstruct.library.TConstructRegistry;
import net.minecraft.item.ItemStack;
import biomesoplenty.api.Blocks;
import biomesoplenty.integration.tinkersconstruct.TCItems;
import cpw.mods.fml.common.event.FMLInterModComms;

public class TConstructIntegration 
{	
	public static void init()
	{
		addMaterials();
		TCItems.init();
	}
	
	private static void addMaterials()
	{
        TConstructRegistry.addToolMaterial(150, "Amethyst", 4, 750, 800, 3, 1.3F, 2, 0f, "", "");
	}
}
