/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.neoforge.datagen;

import biomesoplenty.core.BiomesOPlenty;
import biomesoplenty.init.ModBiomes;
import biomesoplenty.init.ModDamageTypes;
import biomesoplenty.init.ModJukeboxSongs;
import biomesoplenty.neoforge.datagen.provider.BOPDataMapProvider;
import biomesoplenty.neoforge.datagen.provider.BOPLootTableProvider;
import biomesoplenty.neoforge.datagen.provider.BOPRecipeProvider;
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
            .add(Registries.JUKEBOX_SONG, ModJukeboxSongs::bootstrap);

    @SubscribeEvent
    public static void onGatherData(GatherDataEvent.Client event)
    {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        var datapackProvider = generator.addProvider(true, new DatapackBuiltinEntriesProvider(output, lookupProvider, BUILDER, Set.of(BiomesOPlenty.MOD_ID)));

        // Recipes
        generator.addProvider(true, new BOPRecipeProvider.Runner(output, event.getLookupProvider()));

        // Loot
        generator.addProvider(true, BOPLootTableProvider.create(output, lookupProvider));

        // Data Maps
        generator.addProvider(true, new BOPDataMapProvider(output, lookupProvider));

        // Client
        generator.addProvider(true, new BOPModelProvider(output));
    }
}
