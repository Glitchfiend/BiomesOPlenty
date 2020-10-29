package biomesoplenty.client.handler;

import biomesoplenty.init.ModBiomes;
import biomesoplenty.init.ModConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.CreateWorldScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.WorldOptionsScreen;
import net.minecraft.client.gui.screen.WorldSelectionScreen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Optional;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class GuiEventHandler
{
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onGuiOpened(GuiOpenEvent event)
    {
        Screen gui = event.getGui();
        Minecraft mc = Minecraft.getInstance();
        Screen prevScreen = mc.screen;

        // Default to the bop worldtype
        if (ModConfig.ClientConfig.useWorldType.get() && gui instanceof CreateWorldScreen && prevScreen instanceof WorldSelectionScreen)
        {
            WorldOptionsScreen optionsScreen = ((CreateWorldScreen)gui).worldGenSettingsComponent;
            optionsScreen.preset = Optional.of(ModBiomes.biomeGeneratorTypeScreenBOP);
            optionsScreen.settings = optionsScreen.preset.get().create(optionsScreen.registryHolder, optionsScreen.settings.seed(), optionsScreen.settings.generateFeatures(), optionsScreen.settings.generateBonusChest());
        }
    }
}