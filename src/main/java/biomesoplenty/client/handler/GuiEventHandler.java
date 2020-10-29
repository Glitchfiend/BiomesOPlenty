package biomesoplenty.client.handler;

import biomesoplenty.common.world.BOPWorldTypeUtil;
import biomesoplenty.core.BiomesOPlenty;
import biomesoplenty.init.ModBiomes;
import biomesoplenty.init.ModConfig;
import com.mojang.datafixers.util.Function4;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.*;
import net.minecraft.command.Commands;
import net.minecraft.resources.*;
import net.minecraft.util.datafix.codec.DatapackCodec;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.gen.settings.DimensionGeneratorSettings;
import net.minecraft.world.storage.FolderName;
import net.minecraft.world.storage.IServerConfiguration;
import net.minecraft.world.storage.SaveFormat;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Optional;
import java.util.function.Function;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class GuiEventHandler
{
    private static String levelId = null;
    private static ConfirmBackupScreen confirmScreen = null;

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onGuiOpened(GuiOpenEvent event)
    {
        Screen gui = event.getGui();
        Minecraft mc = Minecraft.getInstance();
        Screen prevScreen = mc.screen;

        // Retain the last level that was selected
        if (isDataReadScreen(gui))
        {
            if (prevScreen instanceof WorldSelectionScreen)
            {
                WorldSelectionScreen worldSelectionScreen = (WorldSelectionScreen)prevScreen;
                Optional<WorldSelectionList.Entry> entry = worldSelectionScreen.list.getSelectedOpt();

                if (entry.isPresent())
                {
                    levelId = entry.get().summary.getLevelId();
                }
            }
            else
            {
                cleanupGuiTracking();
            }
        }
        else if (gui instanceof ConfirmBackupScreen && levelId != null)
        {
            confirmScreen = (ConfirmBackupScreen)gui;

            // Don't show the confirmation screen immediately
            event.setCanceled(true);
        }
        else
        {
            cleanupGuiTracking();
        }

        // Default to the bop worldtype
        if (ModConfig.ClientConfig.useWorldType.get() && gui instanceof CreateWorldScreen && prevScreen instanceof WorldSelectionScreen)
        {
            WorldOptionsScreen optionsScreen = ((CreateWorldScreen)gui).worldGenSettingsComponent;
            optionsScreen.preset = Optional.of(ModBiomes.biomeGeneratorTypeScreenBOP);
            optionsScreen.settings = optionsScreen.preset.get().create(optionsScreen.registryHolder, optionsScreen.settings.seed(), optionsScreen.settings.generateFeatures(), optionsScreen.settings.generateBonusChest());
        }
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onGuiDraw(GuiScreenEvent.DrawScreenEvent event)
    {
        Screen gui = event.getGui();
        Minecraft mc = Minecraft.getInstance();

        // We need to check if the bop world type is being used after the save has been unlocked.
        // It is still locked during GuiOpenEvent.
        if (isDataReadScreen(gui) && levelId != null && confirmScreen != null)
        {
            // Skip the confirm screen if this is the bop world type
            //
            // isBopWorldType is commented out because worldDataLoader.apply in createPackManager
            // will break when any mod or datapack is on that uses json dimensiontypes. Why?
            // I don't know. Might be due to being on the client thread instead of server? Maybe it
            // is because this is being run before the server is made? Nevertheless, it may be best
            // to just cancel the confirm screen completely or recommend people to use a different mod
            // to always remove the confirm screen.
            if (true) // isBopWorldType(mc, levelId))
            {
                confirmScreen.listener.proceed(false, false);
            }
            else
            {
                // Otherwise show the confirm screen
                mc.setScreen(confirmScreen);
            }

            event.setCanceled(true);
            cleanupGuiTracking();
        }
    }

    private static void cleanupGuiTracking()
    {
        levelId = null;
        confirmScreen = null;
    }

    private static boolean isDataReadScreen(Screen gui)
    {
        // The data read screen is a dirt message screen.
        if (!(gui instanceof DirtMessageScreen))
            return false;

        ITextComponent title = gui.getTitle();

        // Ensure text component is set as expected
        if (!(title instanceof TranslationTextComponent) || !((TranslationTextComponent)title).getKey().equals("selectWorld.data_read"))
        {
            return false;
        }

        return true;
    }

    private static boolean isBopWorldType(Minecraft mc, String levelId)
    {
        try
        (
            SaveFormat.LevelSave save = mc.getLevelSource().createAccess(levelId);
            Minecraft.PackManager packManager = createPackManager(DynamicRegistries.builtin(), Minecraft::loadDataPacks, Minecraft::loadWorldData, save);
        )
        {
            DimensionGeneratorSettings settings = packManager.worldData().worldGenSettings();
            return BOPWorldTypeUtil.isUsingBopWorldType(settings);
        }
        catch (Exception exception)
        {
            BiomesOPlenty.logger.warn("Failed to load save.", (Throwable)exception);
            return false;
        }
    }

    private static Minecraft.PackManager createPackManager(DynamicRegistries.Impl registries, Function<SaveFormat.LevelSave, DatapackCodec> dataPackLoader, Function4<SaveFormat.LevelSave, DynamicRegistries.Impl, IResourceManager, DatapackCodec, IServerConfiguration> worldDataLoader, SaveFormat.LevelSave save)
    {
        DatapackCodec dataPackCodec = dataPackLoader.apply(save);
        ResourcePackList resourcePackList = new ResourcePackList(ResourcePackInfo::new, new ServerPackFinder(), new FolderPackFinder(save.getLevelPath(FolderName.DATAPACK_DIR).toFile(), IPackNameDecorator.WORLD));
        DataPackRegistries dataPackRegistries = new DataPackRegistries(Commands.EnvironmentType.INTEGRATED, 2);
        IServerConfiguration serverConfiguration = worldDataLoader.apply(save, registries, dataPackRegistries.getResourceManager(), dataPackCodec);
        return new Minecraft.PackManager(resourcePackList, dataPackRegistries, serverConfiguration);
    }
}
