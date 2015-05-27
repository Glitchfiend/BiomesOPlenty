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
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import biomesoplenty.common.command.BOPCommand;
import biomesoplenty.common.init.*;

@Mod(modid = BiomesOPlenty.MOD_ID, name = BiomesOPlenty.MOD_NAME)
public class BiomesOPlenty
{
    public static final String MOD_NAME = "Biomes O' Plenty";
    public static final String MOD_ID = "BiomesOPlenty";

    @Instance(MOD_ID)
    public static BiomesOPlenty instance;

    @SidedProxy(clientSide = "biomesoplenty.core.ClientProxy", serverSide = "biomesoplenty.core.CommonProxy")
    public static CommonProxy proxy;

    public static Logger logger = LogManager.getLogger(MOD_ID);
    public static File configDirectory;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        configDirectory = new File(event.getModConfigurationDirectory(), "biomesoplenty");

        ModConfiguration.init(configDirectory);
        
        // setup blocks before items, because some items need to reference blocks in their constructors (eg seeds)
        ModBlocks.init();
        ModEntities.init();
        ModItems.init();
        ModPotions.init();
        
        ModGenerators.init();
        ModBiomes.init();
        ModHandlers.init();
        
        ModCrafting.init();
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
    
    @EventHandler
    public void serverStarting(FMLServerStartingEvent event)
    {
        event.registerServerCommand(new BOPCommand());
    }

    public File getConfigDirectory()
    {
        return configDirectory;
    }
}
