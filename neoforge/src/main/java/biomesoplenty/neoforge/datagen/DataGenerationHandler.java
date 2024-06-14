/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.neoforge.datagen;

import biomesoplenty.core.BiomesOPlenty;
import biomesoplenty.init.ModDamageTypes;
import biomesoplenty.init.ModJukeboxSongs;
import biomesoplenty.neoforge.datagen.provider.BOPLootTableProvider;
import biomesoplenty.neoforge.datagen.provider.BOPRecipeProvider;
import biomesoplenty.util.worldgen.BOPFeatureUtils;
import biomesoplenty.util.worldgen.BOPPlacementUtils;
import biomesoplenty.worldgen.carver.BOPConfiguredCarvers;
import biomesoplenty.init.ModBiomes;
import net.minecraft.core.Cloner;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.registries.RegistriesDatapackGenerator;
import net.minecraft.resources.RegistryDataLoader;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = BiomesOPlenty.MOD_ID)
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
    public static void onGatherData(GatherDataEvent event)
    {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        var datapackProvider = generator.addProvider(event.includeServer(), new RegistriesDatapackGenerator(output, event.getLookupProvider().thenApply(r -> constructRegistries(r, BUILDER)), Set.of(BiomesOPlenty.MOD_ID)));

        // Recipes
        generator.addProvider(event.includeServer(), new BOPRecipeProvider(output, lookupProvider));

        // Loot
        generator.addProvider(event.includeServer(), BOPLootTableProvider.create(output, lookupProvider));
    }

    private static HolderLookup.Provider constructRegistries(HolderLookup.Provider original, RegistrySetBuilder datapackEntriesBuilder)
    {
        Cloner.Factory clonerFactory = new Cloner.Factory();
        var builderKeys = new HashSet<>(datapackEntriesBuilder.getEntryKeys());
        RegistryDataLoader.WORLDGEN_REGISTRIES.stream().forEach(data -> {
            // Add keys for missing registries
            if (!builderKeys.contains(data.key()))
                datapackEntriesBuilder.add(data.key(), context -> {});

            data.runWithArguments(clonerFactory::addCodec);
        });

        return datapackEntriesBuilder.buildPatch(RegistryAccess.fromRegistryOfRegistries(BuiltInRegistries.REGISTRY), original, clonerFactory).patches();
    }
}
