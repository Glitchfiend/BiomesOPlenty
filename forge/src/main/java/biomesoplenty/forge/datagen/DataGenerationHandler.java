/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.forge.datagen;

import biomesoplenty.init.ModDamageTypes;
import biomesoplenty.util.worldgen.BOPFeatureUtils;
import biomesoplenty.util.worldgen.BOPPlacementUtils;
import biomesoplenty.worldgen.carver.BOPConfiguredCarvers;
import biomesoplenty.core.BiomesOPlenty;
import biomesoplenty.init.ModBiomes;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Set;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = BiomesOPlenty.MOD_ID)
public class DataGenerationHandler
{
    private static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
        .add(Registries.CONFIGURED_CARVER, BOPConfiguredCarvers::bootstrap)
        .add(Registries.CONFIGURED_FEATURE, BOPFeatureUtils::bootstrap)
        .add(Registries.PLACED_FEATURE, BOPPlacementUtils::bootstrap)
        .add(Registries.BIOME, ModBiomes::bootstrapBiomes)
        .add(Registries.DAMAGE_TYPE, ModDamageTypes::bootstrap);

    @SubscribeEvent
    public static void onGatherData(GatherDataEvent event)
    {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();

        generator.addProvider(event.includeServer(), new DatapackBuiltinEntriesProvider(output, event.getLookupProvider(), BUILDER, Set.of(BiomesOPlenty.MOD_ID)));
    }
}