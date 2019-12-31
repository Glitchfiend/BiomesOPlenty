/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.core;

import biomesoplenty.common.command.BOPCommand;
import biomesoplenty.init.*;
import net.minecraft.server.dedicated.ServerProperties;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(value = BiomesOPlenty.MOD_ID)
public class BiomesOPlenty
{
    public static final String MOD_ID = "biomesoplenty";

    public static BiomesOPlenty instance;
    public static CommonProxy proxy = DistExecutor.runForDist(() -> ClientProxy::new, () -> CommonProxy::new);

    public static Logger logger = LogManager.getLogger(MOD_ID);

    public BiomesOPlenty()
    {
    	instance = this;

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::dedicatedServerSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::loadComplete);
        MinecraftForge.EVENT_BUS.addListener(this::serverStarting);

        ModConfig.setup();
    }

    public void dedicatedServerSetup(FMLDedicatedServerSetupEvent event)
    {
        ServerProperties serverProperties = event.getServerSupplier().get().getProperties();

        if (ModConfig.ServerConfig.useWorldType.get())
        {
            logger.info(String.format("Injecting biomesoplenty world type into server.properties. Original value: %s", serverProperties.levelType.getName()));
            serverProperties.properties.setProperty("level-type", "biomesoplenty");
            serverProperties.levelType = ModBiomes.worldType;
        }
        else
        {
            logger.info("Biomes O' Plenty is installed on this server but generation is disabled.");
        }
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        ModBiomes.setup();
        ModVanillaCompat.setup();
    }

    private void clientSetup(final FMLClientSetupEvent event)
    {
        ModEntities.registerRendering();
    }

    private void loadComplete(final FMLLoadCompleteEvent event) // PostRegistrationEven
    {
        proxy.init();
        //GenLayerVisualizer.run();
        ModCompatibility.setup();
    }

    public void serverStarting(FMLServerStartingEvent evt)
    {
        logger.info("Registering BoP commands...");
        new BOPCommand(evt.getCommandDispatcher());
    }
}
