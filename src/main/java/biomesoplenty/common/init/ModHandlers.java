/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.init;

import biomesoplenty.common.handler.BucketEventHandler;
import biomesoplenty.common.handler.FogEventHandler;
import biomesoplenty.common.handler.GrassPathEventHandler;
import biomesoplenty.common.handler.GuiEventHandler;
import biomesoplenty.common.handler.LeavesModelEventHandler;
import biomesoplenty.common.handler.LootTableEventHandler;
import biomesoplenty.common.handler.PotionCurseHandler;
import biomesoplenty.common.handler.SheepEventHandler;
import biomesoplenty.common.handler.SilkTouchEventHandler;
import biomesoplenty.common.handler.TrailsEventHandler;
import biomesoplenty.common.handler.UseHoeEventHandler;
import biomesoplenty.common.handler.VillageMaterialEventHandler;
import biomesoplenty.common.handler.decoration.DecorateBiomeEventHandler;
import biomesoplenty.common.network.BOPPacketHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModHandlers
{
    public static void init()
    {
        BOPPacketHandler.init();
        
        DecorateBiomeEventHandler decorateBiomeHandler = new DecorateBiomeEventHandler();
        MinecraftForge.EVENT_BUS.register(decorateBiomeHandler);
        MinecraftForge.TERRAIN_GEN_BUS.register(decorateBiomeHandler);
        MinecraftForge.TERRAIN_GEN_BUS.register(new VillageMaterialEventHandler());
        MinecraftForge.ORE_GEN_BUS.register(decorateBiomeHandler);
        MinecraftForge.EVENT_BUS.register(new BucketEventHandler());
        MinecraftForge.EVENT_BUS.register(new PotionCurseHandler());
        MinecraftForge.EVENT_BUS.register(new UseHoeEventHandler());
        MinecraftForge.EVENT_BUS.register(new GrassPathEventHandler());
        MinecraftForge.EVENT_BUS.register(new SheepEventHandler());
        MinecraftForge.EVENT_BUS.register(new SilkTouchEventHandler());
        MinecraftForge.EVENT_BUS.register(new LootTableEventHandler());

        if (FMLCommonHandler.instance().getSide() == Side.CLIENT)
        {
            registerClientEvents();
        }
    }
    
    @SideOnly(Side.CLIENT)
    private static void registerClientEvents()
    {
        MinecraftForge.EVENT_BUS.register(new GuiEventHandler());
        MinecraftForge.EVENT_BUS.register(new TrailsEventHandler());
        MinecraftForge.EVENT_BUS.register(new LeavesModelEventHandler());
        MinecraftForge.EVENT_BUS.register(new FogEventHandler());
    }
}
