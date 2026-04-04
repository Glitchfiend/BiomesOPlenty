/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.neoforge.datagen;

import biomesoplenty.core.BiomesOPlenty;
import biomesoplenty.init.ModBiomes;
import biomesoplenty.init.ModDamageTypes;
import biomesoplenty.init.ModJukeboxSongs;
import biomesoplenty.init.ModVillagerTrades;
import biomesoplenty.neoforge.datagen.provider.*;
import biomesoplenty.util.worldgen.BOPFeatureUtils;
import biomesoplenty.util.worldgen.BOPPlacementUtils;
import biomesoplenty.worldgen.carver.BOPConfiguredCarvers;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = BiomesOPlenty.MOD_ID)
public class DataGenerationHandler
{
    private static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_CARVER, BOPConfiguredCarvers::bootstrap)
            .add(Registries.CONFIGURED_FEATURE, BOPFeatureUtils::bootstrap)
            .add(Registries.PLACED_FEATURE, BOPPlacementUtils::bootstrap)
            .add(Registries.BIOME, ModBiomes::bootstrapBiomes)
            .add(Registries.DAMAGE_TYPE, ModDamageTypes::bootstrap)
            .add(Registries.JUKEBOX_SONG, ModJukeboxSongs::bootstrap)
            .add(Registries.VILLAGER_TRADE, ModVillagerTrades::bootstrap);

    @SubscribeEvent
    public static void onGatherData(GatherDataEvent.Client event)
    {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();

        var datapackProvider = generator.addProvider(true, new DatapackBuiltinEntriesProvider(output, event.getLookupProvider(), BUILDER, Set.of(BiomesOPlenty.MOD_ID)));

        // Recipes
        generator.addProvider(true, new BOPRecipeProvider.Runner(output, datapackProvider.getRegistryProvider()));

        // Loot
        generator.addProvider(true, BOPLootTableProvider.create(output, datapackProvider.getRegistryProvider()));

        // Data Maps
        generator.addProvider(true, new BOPDataMapProvider(output, datapackProvider.getRegistryProvider()));

        // Tags
        generator.addProvider(true, new BOPDamageTypeTagsProvider(output, datapackProvider.getRegistryProvider()));
        generator.addProvider(true, new BOPVillagerTradesTagsProvider(output, datapackProvider.getRegistryProvider()));

        // Client
        generator.addProvider(true, new BOPModelProvider(output));
    }
}
