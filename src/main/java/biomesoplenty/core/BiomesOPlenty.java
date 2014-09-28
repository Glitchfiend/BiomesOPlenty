/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.core;

import java.io.File;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import biomesoplenty.common.init.ModBlocks;
import biomesoplenty.common.init.ModItems;

@Mod(modid = BiomesOPlenty.MOD_ID, name = BiomesOPlenty.MOD_NAME)
public class BiomesOPlenty
{
	public static final String MOD_NAME = "Biomes O' Plenty";
	public static final String MOD_ID = "BiomesOPlenty";

	@Instance(MOD_ID)
	public static BiomesOPlenty instance;

	@SidedProxy(clientSide = "biomesoplenty.core.ClientProxy", serverSide = "biomesoplenty.core.CommonProxy")
	public static CommonProxy proxy;

	private File configDirectory;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		configDirectory = new File(event.getModConfigurationDirectory(), "biomesoplenty");

		//TODO: ModConfiguration.load();
		ModItems.init();
		ModBlocks.init();
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		proxy.registerRenderers();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{

	}

	public File getConfigDirectory()
	{
		return configDirectory;
	}
}
