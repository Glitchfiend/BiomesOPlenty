/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.client.handler;

import biomesoplenty.init.ModConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.worldselection.CreateWorldScreen;
import net.minecraft.client.gui.screens.worldselection.SelectWorldScreen;
import net.minecraft.client.gui.screens.worldselection.WorldGenSettingsComponent;
import net.minecraft.client.gui.screens.worldselection.WorldPreset;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ScreenOpenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Optional;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class GuiEventHandler
{
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onGuiOpened(ScreenOpenEvent event)
    {
        Screen gui = event.getScreen();
        Minecraft mc = Minecraft.getInstance();
        Screen prevScreen = mc.screen;

        // Default to the bop worldtype
        if (ModConfig.ClientConfig.useWorldType.get() && gui instanceof CreateWorldScreen && prevScreen instanceof SelectWorldScreen)
        {
            WorldGenSettingsComponent optionsScreen = ((CreateWorldScreen)gui).worldGenSettingsComponent;
            optionsScreen.preset = Optional.of(findBopBiomeGeneratorTypeScreen());
            optionsScreen.settings = optionsScreen.preset.get().create(optionsScreen.registryHolder, optionsScreen.settings.seed(), optionsScreen.settings.generateFeatures(), optionsScreen.settings.generateBonusChest());
        }
    }

    private static WorldPreset findBopBiomeGeneratorTypeScreen()
    {
        for (WorldPreset screen : WorldPreset.PRESETS)
        {
            // Skip screens that don't use a TranslationTextComponent because they're definitely not ours
            if (!(screen.description instanceof TranslatableComponent))
                continue;

            TranslatableComponent desc = (TranslatableComponent)screen.description;

            if (desc.getKey().equals("generator.biomesoplenty"))
            {
                return screen;
            }
        }

        throw new RuntimeException("Failed to locate biomesoplenty biome generator type screen!");
    }
}
