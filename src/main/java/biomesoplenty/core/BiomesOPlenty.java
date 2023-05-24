/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/

package biomesoplenty.core;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.item.BOPItems;
import biomesoplenty.init.*;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraft.world.item.*;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.carver.WorldCarver;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.util.MutableHashedLinkedMap;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.util.List;

@Mod(value = BiomesOPlenty.MOD_ID)
public class BiomesOPlenty
{
    public static final String MOD_ID = "biomesoplenty";

    public static final DeferredRegister<Biome> BIOME_REGISTER = DeferredRegister.create(Registries.BIOME, BiomesOPlenty.MOD_ID);
    public static final DeferredRegister<Block> BLOCK_REGISTER = DeferredRegister.create(Registries.BLOCK, MOD_ID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_REGISTER = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, MOD_ID);
    public static final DeferredRegister<WorldCarver<?>> CARVER_REGISTER = DeferredRegister.create(Registries.CARVER, MOD_ID);
    public static final DeferredRegister<ConfiguredWorldCarver<?>> CONFIGURED_CARVER_REGISTER = DeferredRegister.create(Registries.CONFIGURED_CARVER, MOD_ID);
    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURE_REGISTER = DeferredRegister.create(Registries.CONFIGURED_FEATURE, MOD_ID);
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPE_REGISTER = DeferredRegister.create(Registries.ENTITY_TYPE, MOD_ID);
    public static final DeferredRegister<Feature<?>> FEATURE_REGISTER = DeferredRegister.create(Registries.FEATURE, MOD_ID);
    public static final DeferredRegister<Fluid> FLUID_REGISTER = DeferredRegister.create(Registries.FLUID, MOD_ID);
    public static final DeferredRegister<FluidType> FORGE_FLUID_REGISTER = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, MOD_ID);
    public static final DeferredRegister<Item> ITEM_REGISTER = DeferredRegister.create(Registries.ITEM, MOD_ID);
    public static final DeferredRegister<ParticleType<?>> PARTICLES_REGISTER = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, MOD_ID);
    public static final DeferredRegister<PaintingVariant> PAINTING_VARIANT_REGISTER = DeferredRegister.create(Registries.PAINTING_VARIANT, MOD_ID);
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
        bus.addListener(this::registerTab);
        bus.addListener(this::registerItemsInVanillaTabs);

        // Register events for deferred registers
        BIOME_REGISTER.register(bus);
        BLOCK_REGISTER.register(bus);
        BLOCK_ENTITY_REGISTER.register(bus);
        CARVER_REGISTER.register(bus);
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
        ModPaintings.setup();
        ModSounds.setup();
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(() ->
        {
            ModBiomes.setupTerraBlender();
            ModVanillaCompat.setup();
        });
    }

    private void clientSetup(final FMLClientSetupEvent event)
    {
        event.enqueueWork(ModBlocks::registerWoodTypes);
    }

    private void loadComplete(final FMLLoadCompleteEvent event)
    {
        // Setup tags
        ModTags.setup();
    }

    private void registerTab(CreativeModeTabEvent.Register event)
    {
        List<RegistryObject<Item>> itemBlacklist = List.of(BOPItems.BOP_ICON);
        List<RegistryObject<Block>> blockBlacklist = List.of(BOPBlocks.BLOOD, BOPBlocks.SPANISH_MOSS_PLANT, BOPBlocks.GLOWWORM_SILK_STRAND,
                BOPBlocks.HANGING_COBWEB_STRAND, BOPBlocks.FLESH_TENDONS_STRAND, BOPBlocks.POTTED_ORIGIN_SAPLING, BOPBlocks.POTTED_FLOWERING_OAK_SAPLING,
                BOPBlocks.POTTED_RAINBOW_BIRCH_SAPLING, BOPBlocks.POTTED_YELLOW_AUTUMN_SAPLING, BOPBlocks.POTTED_ORANGE_AUTUMN_SAPLING,
                BOPBlocks.POTTED_MAPLE_SAPLING, BOPBlocks.POTTED_FIR_SAPLING, BOPBlocks.POTTED_REDWOOD_SAPLING, BOPBlocks.POTTED_WHITE_CHERRY_SAPLING,
                BOPBlocks.POTTED_PINK_CHERRY_SAPLING, BOPBlocks.POTTED_MAHOGANY_SAPLING, BOPBlocks.POTTED_JACARANDA_SAPLING, BOPBlocks.POTTED_PALM_SAPLING,
                BOPBlocks.POTTED_WILLOW_SAPLING, BOPBlocks.POTTED_DEAD_SAPLING, BOPBlocks.POTTED_MAGIC_SAPLING, BOPBlocks.POTTED_UMBRAN_SAPLING,
                BOPBlocks.POTTED_HELLBARK_SAPLING, BOPBlocks.POTTED_ROSE, BOPBlocks.POTTED_VIOLET, BOPBlocks.POTTED_LAVENDER, BOPBlocks.POTTED_WILDFLOWER,
                BOPBlocks.POTTED_ORANGE_COSMOS, BOPBlocks.POTTED_PINK_DAFFODIL, BOPBlocks.POTTED_PINK_HIBISCUS, BOPBlocks.POTTED_GLOWFLOWER,
                BOPBlocks.POTTED_WILTED_LILY, BOPBlocks.POTTED_BURNING_BLOSSOM, BOPBlocks.POTTED_SPROUT, BOPBlocks.POTTED_CLOVER, BOPBlocks.POTTED_TOADSTOOL,
                BOPBlocks.POTTED_GLOWSHROOM);

        event.registerCreativeModeTab(new ResourceLocation(BiomesOPlenty.MOD_ID, "main"), builder -> {
            builder.icon(() -> new ItemStack(BOPItems.BOP_ICON.get()))
            .title(Component.translatable("itemGroup.biomesoplenty"))
            .displayItems((displayParameters, output) -> {
                // Add blocks
                for (Field field : BOPBlocks.class.getFields())
                {
                    if (field.getType() != RegistryObject.class) continue;

                    try
                    {
                        RegistryObject<Block> block = (RegistryObject)field.get(null);
                        if (!blockBlacklist.contains(block))
                            output.accept(new ItemStack(block.get()));
                    }
                    catch (IllegalAccessException e) {}
                }

                // Add items
                for (Field field : BOPItems.class.getFields())
                {
                    if (field.getType() != RegistryObject.class) continue;

                    try
                    {
                        RegistryObject<Item> item = (RegistryObject)field.get(null);
                        if (!itemBlacklist.contains(item))
                            output.accept(new ItemStack(item.get()));
                    }
                    catch (IllegalAccessException ignored) {}
                }
            });
        });
    }

    private void registerItemsInVanillaTabs(CreativeModeTabEvent.BuildContents event) {
        MutableHashedLinkedMap<ItemStack, CreativeModeTab.TabVisibility> entries = event.getEntries();
        CreativeModeTab tab = event.getTab();
        if (tab == CreativeModeTabs.BUILDING_BLOCKS) {
            boolean b = false;
            Item previous = Items.WARPED_BUTTON;
            for (RegistryObject<Block> entry : BLOCK_REGISTER.getEntries()) {
                Block block = entry.get();
                if (block instanceof RotatedPillarBlock || b) {
                    Item item = block.asItem();
                    registerAfter(item, previous, entries);
                    previous = item;
                    b = !(block instanceof ButtonBlock);
                }
            }
        }
    }

    private void registerAfter(Item item, Item after, MutableHashedLinkedMap<ItemStack, CreativeModeTab.TabVisibility> entries) {
        entries.putAfter(item.getDefaultInstance(), after.getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
    }
}
