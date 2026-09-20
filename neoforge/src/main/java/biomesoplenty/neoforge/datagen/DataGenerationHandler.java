/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.neoforge.datagen;

import biomesoplenty.core.BiomesOPlenty;
import biomesoplenty.init.*;
import biomesoplenty.neoforge.datagen.provider.*;
import biomesoplenty.util.worldgen.BOPFeatureUtils;
import biomesoplenty.util.worldgen.BOPPlacementUtils;
import biomesoplenty.worldgen.carver.BOPConfiguredCarvers;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Set;

@EventBusSubscriber(modid = BiomesOPlenty.MOD_ID)
public class DataGenerationHandler
{
    private static final RegistrySetBuilder WORLD_BUILDER = new RegistrySetBuilder()
            .add(Registries.CARVER, BOPConfiguredCarvers::bootstrap)
            .add(Registries.FEATURE, BOPFeatureUtils::bootstrap)
            .add(Registries.PLACED_FEATURE, BOPPlacementUtils::bootstrap)
            .add(Registries.BIOME, ModBiomes::bootstrapBiomes)
            .add(Registries.DAMAGE_TYPE, ModDamageTypes::bootstrap)
            .add(Registries.JUKEBOX_SONG, ModJukeboxSongs::bootstrap)
            .add(Registries.VILLAGER_TRADE, ModVillagerTrades::bootstrap)
            .add(Registries.TRIM_MATERIAL, ModTrimMaterials::bootstrap);

    private static final RegistrySetBuilder RELOADABLE_BUILDER = new RegistrySetBuilder()
            .add(Registries.LOOT_TABLE, BOPLootTableProvider.create())
            .add(BOPRecipeProvider.create());

    @SubscribeEvent
    public static void onGatherData(GatherDataEvent.Client event)
    {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();

        var worldProvider = generator.addProvider(true, DatapackBuiltinEntriesProvider.forWorldLayer(
                output, "BOP World Registries", event.getWorldLookupProvider(), WORLD_BUILDER, Set.of(BiomesOPlenty.MOD_ID)));

        generator.addProvider(true, DatapackBuiltinEntriesProvider.forReloadableLayer(
                output, "BOP Reloadable Registries", worldProvider.getRegistryProvider(), event.getReloadableLookupProvider(),
                RELOADABLE_BUILDER, Set.of(BiomesOPlenty.MOD_ID)));

        // Tags
        generator.addProvider(true, new BOPDamageTypeTagsProvider(output, worldProvider.getRegistryProvider()));
        generator.addProvider(true, new BOPVillagerTradesTagsProvider(output, worldProvider.getRegistryProvider()));

        // Client
        generator.addProvider(true, new BOPModelProvider(output));
    }
}
