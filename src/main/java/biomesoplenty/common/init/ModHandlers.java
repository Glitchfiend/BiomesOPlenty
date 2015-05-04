/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.init;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import biomesoplenty.client.handler.*;
import biomesoplenty.common.handler.*;
import biomesoplenty.common.handler.decoration.*;
import biomesoplenty.common.network.BOPPacketHandler;

public class ModHandlers
{
    public static void init()
    {
        BOPPacketHandler.init();
        
        DecorateBiomeEventHandler decorateBiomeHandler = new DecorateBiomeEventHandler();
        MinecraftForge.EVENT_BUS.register(decorateBiomeHandler);
        MinecraftForge.TERRAIN_GEN_BUS.register(decorateBiomeHandler);
        MinecraftForge.EVENT_BUS.register(new DyeEventHandler());
        MinecraftForge.EVENT_BUS.register(new FlippersEventHandler());
        
        if (FMLCommonHandler.instance().getSide() == Side.CLIENT)
        {
            registerClientEvents();
        }
    }
    
    @SideOnly(Side.CLIENT)
    private static void registerClientEvents()
    {
        MinecraftForge.EVENT_BUS.register(new ModelBakeHandler());
        MinecraftForge.EVENT_BUS.register(new GuiEventHandler());
    }
}
