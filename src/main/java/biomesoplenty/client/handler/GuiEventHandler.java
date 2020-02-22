/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.client.handler;

import biomesoplenty.core.BiomesOPlenty;
import biomesoplenty.init.ModBiomes;
import biomesoplenty.init.ModConfig;
import net.minecraft.client.gui.screen.CreateWorldScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class GuiEventHandler
{
    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void onPreInitCreateWorld(GuiScreenEvent.InitGuiEvent.Pre event)
    {
        Screen screenGui = event.getGui();

        if (ModConfig.ClientConfig.useWorldType.get() && screenGui instanceof CreateWorldScreen)
        {
            CreateWorldScreen createWorldGui = (CreateWorldScreen)screenGui;

            // Do not change back when returning from the 'Customize' screen
            if (createWorldGui.levelTypeIndex == WorldType.NORMAL.getId())
                createWorldGui.levelTypeIndex = ModBiomes.worldType.getId();
        }
    }
}
