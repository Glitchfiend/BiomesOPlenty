/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.init;

import biomesoplenty.common.handler.AchievementEventHandler;
import biomesoplenty.common.handler.BucketEventHandler;
import biomesoplenty.common.handler.DyeEventHandler;
import biomesoplenty.common.handler.FlippersEventHandler;
import biomesoplenty.common.handler.GuiEventHandler;
import biomesoplenty.common.handler.ItemEventHandler;
import biomesoplenty.common.handler.TrailsEventHandler;
import biomesoplenty.common.handler.UseHoeEventHandler;
import biomesoplenty.common.handler.decoration.DecorateBiomeEventHandler;
import biomesoplenty.common.handler.potion.PotionParalysisEventHandler;
import biomesoplenty.common.handler.potion.PotionPossessionEventHandler;
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
        MinecraftForge.ORE_GEN_BUS.register(decorateBiomeHandler);
        MinecraftForge.EVENT_BUS.register(new DyeEventHandler());
        MinecraftForge.EVENT_BUS.register(new FlippersEventHandler());
        MinecraftForge.EVENT_BUS.register(new BucketEventHandler());
        MinecraftForge.EVENT_BUS.register(new PotionParalysisEventHandler());
        MinecraftForge.EVENT_BUS.register(new PotionPossessionEventHandler());
        MinecraftForge.EVENT_BUS.register(new ItemEventHandler());
        MinecraftForge.EVENT_BUS.register(new UseHoeEventHandler());
        FMLCommonHandler.instance().bus().register(new AchievementEventHandler());
        
        if (FMLCommonHandler.instance().getSide() == Side.CLIENT)
        {
            registerClientEvents();
        }
    }
    
    @SideOnly(Side.CLIENT)
    private static void registerClientEvents()
    {
        MinecraftForge.EVENT_BUS.register(new GuiEventHandler());
        FMLCommonHandler.instance().bus().register(new TrailsEventHandler());
    }
}
