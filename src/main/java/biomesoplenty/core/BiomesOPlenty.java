/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/

package biomesoplenty.core;

import biomesoplenty.api.BOPAPI;
import biomesoplenty.init.*;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.carver.WorldCarver;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(value = BiomesOPlenty.MOD_ID)
public class BiomesOPlenty
{
    public static final String MOD_ID = BOPAPI.MOD_ID;

    public static final DeferredRegister<Biome> BIOME_REGISTER = DeferredRegister.create(Registries.BIOME, BiomesOPlenty.MOD_ID);
    public static final DeferredRegister<Block> BLOCK_REGISTER = DeferredRegister.create(Registries.BLOCK, MOD_ID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_REGISTER = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, MOD_ID);
    public static final DeferredRegister<WorldCarver<?>> CARVER_REGISTER = DeferredRegister.create(Registries.CARVER, MOD_ID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TAB_REGISTER = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MOD_ID);
    public static final DeferredRegister<ConfiguredWorldCarver<?>> CONFIGURED_CARVER_REGISTER = DeferredRegister.create(Registries.CONFIGURED_CARVER, MOD_ID);
    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURE_REGISTER = DeferredRegister.create(Registries.CONFIGURED_FEATURE, MOD_ID);
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPE_REGISTER = DeferredRegister.create(Registries.ENTITY_TYPE, MOD_ID);
    public static final DeferredRegister<Feature<?>> FEATURE_REGISTER = DeferredRegister.create(Registries.FEATURE, MOD_ID);
    public static final DeferredRegister<Fluid> FLUID_REGISTER = DeferredRegister.create(Registries.FLUID, MOD_ID);
    public static final DeferredRegister<FluidType> FORGE_FLUID_REGISTER = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, MOD_ID);
    public static final DeferredRegister<Item> ITEM_REGISTER = DeferredRegister.create(Registries.ITEM, MOD_ID);
    public static final DeferredRegister<ParticleType<?>> PARTICLES_REGISTER = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, MOD_ID);
    public static final DeferredRegister<PlacedFeature> PLACED_FEATURE_REGISTER = DeferredRegister.create(Registries.PLACED_FEATURE, MOD_ID);
    public static final DeferredRegister<SoundEvent> SOUND_EVENT_REGISTER = DeferredRegister.create(Registries.SOUND_EVENT, MOD_ID);
    public static final DeferredRegister<DamageType> DAMAGE_TYPE_REGISTER = DeferredRegister.create(Registries.DAMAGE_TYPE, MOD_ID);

    public static BiomesOPlenty instance;
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    public BiomesOPlenty()
    {
        instance = this;

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::commonSetup);
        bus.addListener(this::clientSetup);
        bus.addListener(this::loadComplete);

        // Register events for deferred registers
        BIOME_REGISTER.register(bus);
        BLOCK_REGISTER.register(bus);
        BLOCK_ENTITY_REGISTER.register(bus);
        CARVER_REGISTER.register(bus);
        CREATIVE_TAB_REGISTER.register(bus);
        CONFIGURED_CARVER_REGISTER.register(bus);
        CONFIGURED_FEATURE_REGISTER.register(bus);
        ENTITY_TYPE_REGISTER.register(bus);
        FEATURE_REGISTER.register(bus);
        FLUID_REGISTER.register(bus);
        FORGE_FLUID_REGISTER.register(bus);
        ITEM_REGISTER.register(bus);
        PARTICLES_REGISTER.register(bus);
        PLACED_FEATURE_REGISTER.register(bus);
        SOUND_EVENT_REGISTER.register(bus);
        DAMAGE_TYPE_REGISTER.register(bus);

        // Initialize the config file first so other things can rely on it
        ModConfig.setup();

        ModBlocks.setup();
        ModItems.setup();
        ModFluids.setup();
        ModEntities.setup();
        ModFeatures.setup();
        ModBiomes.setup();
        ModParticles.setup();
        ModSounds.setup();

        // Initialize the creative tab last after blocks and items have been setup
        ModCreativeTab.setup();
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(() ->
        {
            ModBiomes.setupTerraBlender();
            ModVanillaCompat.setup();
            ModFluids.registerFluidInteractions();
        });
    }

    private void clientSetup(final FMLClientSetupEvent event)
    {
        event.enqueueWork(() ->
        {
            ModBlocks.registerWoodTypes();
            ModBlocks.setRenderTypes();
        });
    }

    private void loadComplete(final FMLLoadCompleteEvent event)
    {
        // Setup tags
        ModTags.setup();
    }
}
