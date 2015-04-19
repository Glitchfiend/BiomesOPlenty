/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.init;

import net.minecraftforge.common.MinecraftForge;
import biomesoplenty.common.handler.DyeEventHandler;
import biomesoplenty.common.handler.GuiEventHandler;
import biomesoplenty.common.handler.decoration.DecorateBiomeEventHandler;

public class ModHandlers
{
    public static void init()
    {
        DecorateBiomeEventHandler decorateBiomeHandler = new DecorateBiomeEventHandler();
        MinecraftForge.EVENT_BUS.register(decorateBiomeHandler);
        MinecraftForge.TERRAIN_GEN_BUS.register(decorateBiomeHandler);
        MinecraftForge.EVENT_BUS.register(new GuiEventHandler());
        MinecraftForge.EVENT_BUS.register(new DyeEventHandler());
    }
}
