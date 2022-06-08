/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/

package biomesoplenty.core;

import biomesoplenty.client.handler.ColorHandler;
import biomesoplenty.client.handler.EntityRendererHandler;
import biomesoplenty.client.handler.FluidFogHandler;
import biomesoplenty.common.worldgen.BOPSurfaceRuleData;
import biomesoplenty.init.*;
import net.minecraft.core.Registry;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.levelgen.carver.WorldCarver;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.registries.DeferredRegister;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import terrablender.api.SurfaceRuleManager;

@Mod(value = BiomesOPlenty.MOD_ID)
public class BiomesOPlenty
{
    public static final String MOD_ID = "biomesoplenty";

    public static final DeferredRegister<Biome> BIOME_REGISTER = DeferredRegister.create(Registry.BIOME_REGISTRY, BiomesOPlenty.MOD_ID);
    public static final DeferredRegister<Block> BLOCK_REGISTER = DeferredRegister.create(Registry.BLOCK_REGISTRY, MOD_ID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_REGISTER = DeferredRegister.create(Registry.BLOCK_ENTITY_TYPE_REGISTRY, MOD_ID);
    public static final DeferredRegister<WorldCarver<?>> CARVER_REGISTER = DeferredRegister.create(Registry.CARVER_REGISTRY, MOD_ID);
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPE_REGISTER = DeferredRegister.create(Registry.ENTITY_TYPE_REGISTRY, MOD_ID);
    public static final DeferredRegister<Feature<?>> FEATURE_REGISTER = DeferredRegister.create(Registry.FEATURE_REGISTRY, MOD_ID);
    public static final DeferredRegister<Fluid> FLUID_REGISTER = DeferredRegister.create(Registry.FLUID_REGISTRY, MOD_ID);
    public static final DeferredRegister<Item> ITEM_REGISTER = DeferredRegister.create(Registry.ITEM_REGISTRY, MOD_ID);
    public static final  DeferredRegister<PaintingVariant> PAINTING_VARIANT_REGISTER = DeferredRegister.create(Registry.PAINTING_VARIANT_REGISTRY, MOD_ID);
    public static final DeferredRegister<SoundEvent> SOUND_EVENT_REGISTER = DeferredRegister.create(Registry.SOUND_EVENT_REGISTRY, MOD_ID);

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
        ENTITY_TYPE_REGISTER.register(bus);
        FEATURE_REGISTER.register(bus);
        FLUID_REGISTER.register(bus);
        ITEM_REGISTER.register(bus);
        SOUND_EVENT_REGISTER.register(bus);

        ModBlocks.setup();
        ModItems.setup();
        ModFluids.setup();
        ModEntities.setup();
        ModBiomes.setup();
        ModPaintings.setup();
        ModSounds.setup();
        ModConfig.setup();
        ModParticles.PARTICLES.register(FMLJavaModLoadingContext.get().getModEventBus());

        if (FMLEnvironment.dist == Dist.CLIENT)
        {
            bus.register(new ColorHandler());
            bus.register(new EntityRendererHandler());
            MinecraftForge.EVENT_BUS.register(new FluidFogHandler());
        }
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(() ->
        {
            // Register our surface rules
            SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, BiomesOPlenty.MOD_ID, BOPSurfaceRuleData.overworld());
            SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.NETHER, BiomesOPlenty.MOD_ID, BOPSurfaceRuleData.nether());

            // Setup Vanilla compat
            ModVanillaCompat.setup();
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
